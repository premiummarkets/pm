package com.finance.pms.events.operations.nativeops.data;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.CachableOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.operations.util.ValueManipulator.InputToArrayReturn;

public class IOsExporterOperation extends FileExporter implements CachableOperation {

	private static final int FIRST_INPUT = 3;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsExporterOperation.class);
	
	private String trainingFiles;  //XXX not singleton compatible
	
	
	public IOsExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsExporterOperation() {
		this("iosExporter", "Exports all input datasets to a file. The file name is generated: <runtime-id>_ <stock-symbol> _k_training_ <random-id> .csv",
				new NumberOperation("number", "rouding", "Rouding precision", new NumberValue(Double.NaN)),
				new StringOperation("string", "filePath", "Export file path prefix", new StringValue("")),
				new StringOperation("string", "headerPrefixe", "Prefix of the column headers", new StringValue("")),
				new DoubleMapOperation("data", "datasets", "Datasets to export (usually a list of iosAssembler)", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public IOsExporterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double rounding = ((NumberValue)inputs.get(0)).getNumberValue();
		String exportFilePrefix = ((StringValue) inputs.get(1)).getValue(targetStock);
		String headersPrefix = ((StringValue) inputs.get(2)).getValue(targetStock);
		
		String fileSuffix = UUID.randomUUID().toString() + ".csv"; //targetStock.getStock().getSymbol() + "_k_training_" + UUID.randomUUID();
		String filePath = extractedFileRootPath(exportFilePrefix + "_" + fileSuffix);

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(targetStock, getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			Set<Date> knownMissingKeys = targetStock.missingData();
			Map<InputToArrayReturn, SortedMap<Date, double[]>> inputListToArray = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, false, inputsOperandsRefs.size() -1);
			SortedMap<Date, double[]> factorisedInput = inputListToArray.get(InputToArrayReturn.RESULTS);
			
			if (inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).keySet().stream().anyMatch(k -> !knownMissingKeys.contains(k))) {
				throw new Exception("Unexpected NaN data in series (known NaNs " + knownMissingKeys + "): " + inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).keySet().stream()
																							.filter(k -> !knownMissingKeys.contains(k))
																							.collect(Collectors.toList()));
			}
			
			if (!Double.isNaN(rounding)) {
				factorisedInput = factorisedInput.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
					return Arrays.stream(e.getValue()).map(d -> {
						@SuppressWarnings("deprecation") int roundHalfEven = BigDecimal.ROUND_HALF_EVEN;
						return Precision.round(d, rounding.intValue(), roundHalfEven);
					}).toArray();
				}, (a,b) -> a, TreeMap::new));
			}
			
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			series.put(headersPrefix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(headersPrefix, inputsOperandsRefs);
			filePath = SeriesPrinter.printo(filePath, headersPrefixes, series);
			trainingFiles = filePath;
			return new StringValue(filePath);
		} catch (Exception e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		}

		return new StringValue("NONE");
	}
	
	private String extractedFileRootPath(String fileRootPath) {
		if (!fileRootPath.startsWith(File.separator)) {
			fileRootPath = System.getProperty("installdir") + File.separator + fileRootPath;
		}
		return fileRootPath;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}
	
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return false;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
		if (trainingFiles != null) {
			try {
				Path deltaFile = Path.of(URI.create("file://" + trainingFiles));
				LOGGER.info("Deleting file local copy: " + deltaFile.toString());
				boolean exist = Files.exists(deltaFile);
				if (exist) {
					try {
						Files.delete(deltaFile);
					} catch (IOException e) {
						LOGGER.error(e, e);
					}
				}
			} catch (Exception e1) {
				LOGGER.error("Can't create path from " + trainingFiles, e1);
			}
		}
	}
	
	@Override
	public Integer operationNaturalShift() {
		return 0;
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		String thisShortName = "ioe";
		String opsFormulaeShort = super.toFormulaeShort(targetStock);
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

}
