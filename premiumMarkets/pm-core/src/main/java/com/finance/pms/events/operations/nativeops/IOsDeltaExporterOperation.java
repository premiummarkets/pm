package com.finance.pms.events.operations.nativeops;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

public class IOsDeltaExporterOperation extends StringerOperation {
	
	private static final int FIRST_INPUT = 3;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsDeltaExporterOperation.class);
	
	
	public IOsDeltaExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsDeltaExporterOperation() {
		this("iosDeltaExporter", "Exports all datasets to a given file path and returns the same file path.",
				new StringOperation("string", "filePath", "Exact file path of the output. Must be consistent between runs ..", new StringValue("")),
				new StringOperation("string", "headerSuffix", "Suffix of the headers", new StringValue("")),
				new StringOperation("boolean", "append", "True if we append. False for overwrite", new StringValue("TRUE")),
				new DoubleMapOperation("data", "datasets", "Datasets to export (typically a list of iosAssembler)", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsDeltaExporterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String fileRootPath = ((StringValue) inputs.get(0)).getValue(targetStock);
		String headerSuffix = ((StringValue) inputs.get(1)).getValue(targetStock);
		Boolean append = Boolean.valueOf(((StringValue) inputs.get(2)).getValue(targetStock));
		
		if (!fileRootPath.startsWith(File.separator)) {
			fileRootPath = System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator +  fileRootPath;
		}

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, true);
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			series.put(headerSuffix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(headerSuffix, inputsOperandsRefs);
			if (append) {
				SeriesPrinter.appendto(fileRootPath, headersPrefixes, series);
			} else {
				SeriesPrinter.printo(fileRootPath, headersPrefixes, series);
			}
			
			return new StringValue(fileRootPath);
			
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
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		//TODO delete the added lines?
	}

}
