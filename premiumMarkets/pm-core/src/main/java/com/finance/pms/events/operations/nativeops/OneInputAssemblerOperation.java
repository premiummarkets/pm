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

public class OneInputAssemblerOperation extends ArrayMapOperation {

	private static final int FIRST_INPUT = 1;
	private static MyLogger LOGGER = MyLogger.getLogger(OneInputAssemblerOperation.class);
	

	public OneInputAssemblerOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public OneInputAssemblerOperation(String reference, String description, Operation ... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public OneInputAssemblerOperation() {
		this("oneInputAssembler", "Assembles several inputs into one input into one output array.",
				new StringOperation("boolean", "isExportToFile", "If true, exports the result to a file.", new StringValue("FALSE")),
				new DoubleMapOperation("data", "inputs", "Inputs to assemble in one", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public OneInputAssemblerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Boolean isExport = Boolean.valueOf(((StringValue) inputs.get(0)).getValue(targetStock));

		@SuppressWarnings("unchecked")
		List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
		SortedMap<Date, double[]> factorisedInput = ValueManipulator.buildOneInput(targetStock, developpedInputs);
		List<String> inputsOperandsRefs = ValueManipulator.buildOperandReferences(inputs.size(), getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);

		try {
			if (isExport) {
				LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
				String key = UUID.randomUUID() + "_" + this.getReference();
				series.put(key, factorisedInput);
				LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
				headersPrefixes.put(key, inputsOperandsRefs);
				SeriesPrinter.printo(key, "tmp", headersPrefixes, series);
			}
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new DoubleArrayMapValue(factorisedInput, inputsOperandsRefs, 0);
	}

	@Override
	public int operationStartDateShift() {
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
