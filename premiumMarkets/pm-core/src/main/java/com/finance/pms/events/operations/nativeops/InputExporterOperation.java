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

public class InputExporterOperation extends Operation {

	private static final int FIRST_INPUT = 1;
	private static MyLogger LOGGER = MyLogger.getLogger(InputExporterOperation.class);
	
	
	public InputExporterOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public InputExporterOperation() {
		this("outputExporter", "Exports all inputs to a file.",
				new StringOperation("string", "file name prefix", "Will prefix a random file name", new StringValue("")),
				new DoubleMapOperation("data", "inputs", "Inputs to export", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public InputExporterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		String filePrefix = ((StringValue) inputs.get(0)).getValue(targetStock);

		@SuppressWarnings("unchecked")
		List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
		SortedMap<Date, double[]> factorisedInput = ValueManipulator.buildOneInput(targetStock, developpedInputs);
		List<String> inputsOperandsRefs = ValueManipulator.buildOperandReferences(inputs.size(), getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);

		try {
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			String filename = filePrefix + "_" + this.getReference() + "_" + UUID.randomUUID();
			series.put(filePrefix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(filePrefix, inputsOperandsRefs);
			String filePath = SeriesPrinter.printo(filename, "tmp", headersPrefixes, series);
			return new StringValue(filePath);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new StringValue("None");
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
		//Nothing have exports have a unique name
	}

}
