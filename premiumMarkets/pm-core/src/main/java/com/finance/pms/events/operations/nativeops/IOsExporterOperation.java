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

public class IOsExporterOperation extends StringerOperation {

	private static final int FIRST_INPUT = 2;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsExporterOperation.class);
	
	
	public IOsExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsExporterOperation() {
		this("iosExporter", "Exports all assembled datasets to a file.",
				new StringOperation("string", "file name path", "Path of the output", new StringValue("")),
				new StringOperation("string", "file name prefix", "Will prefix a random file name", new StringValue("")),
				new DoubleMapOperation("data", "datasets", "Datasets to export (usually a list of iosAssembler)", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsExporterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		String fileRootPath = ((StringValue) inputs.get(0)).getValue(targetStock);
		String filePrefix = ((StringValue) inputs.get(1)).getValue(targetStock);

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, true);
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			String fileName = filePrefix + "_" + "k_training" + "_" + UUID.randomUUID(); // filePrefix + "_" + this.getReference() + "_" + UUID.randomUUID();
			series.put(filePrefix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(filePrefix, inputsOperandsRefs);
			String filePath = SeriesPrinter.printo(fileName, fileRootPath, headersPrefixes, series);
			return new StringValue(filePath);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new StringValue("None");
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
		//Nothing as exports have a unique name
	}

}
