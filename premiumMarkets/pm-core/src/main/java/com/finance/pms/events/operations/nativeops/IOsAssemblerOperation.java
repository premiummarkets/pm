package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.UUID;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

public class IOsAssemblerOperation extends ArrayMapOperation {

	private static final int FIRST_INPUT = 2;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsAssemblerOperation.class);
	

	public IOsAssemblerOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public IOsAssemblerOperation(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsAssemblerOperation() {
		this("iosAssembler", "Assembles several inputs into one input array. No NaN permited.",
				new StringOperation("boolean", "isExportToFile", "If true, exports the result to a file.", new StringValue("FALSE")),
				new StringOperation("boolean", "allowTrailingNaN", "If NaN are allowed at the end of the data set (usefull for targets)", new StringValue("FALSE")),
				new DoubleMapOperation("data", "datasets", "Datasets to assemble in one", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Boolean isExport = Boolean.valueOf(((StringValue) inputs.get(0)).getValue(targetStock));
		Boolean allowTrailingNaN = Boolean.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, developpedInputs, allowTrailingNaN);
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandReferences(getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			if (isExport) {
				LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
				String fileName = "iosAssembler_" + this.getReference() + "_" + UUID.randomUUID();
				series.put(this.getReference(), factorisedInput);
				LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
				headersPrefixes.put(this.getReference(), inputsOperandsRefs);
				SeriesPrinter.printo(fileName, "tmp", headersPrefixes, series);
			}
			
			return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}
		
		return new DoubleArrayMapValue();

	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		//Nothing specific to this operation
	}

}
