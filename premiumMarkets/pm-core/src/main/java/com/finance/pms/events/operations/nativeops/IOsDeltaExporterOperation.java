package com.finance.pms.events.operations.nativeops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

/**
 * The full data file is a permanent local file
 * Only the requested delta is sent back
 * 
 * @author guil
 *
 */
public class IOsDeltaExporterOperation extends StringerOperation {
	
	private static final int APPEND_IDX = 3;
	private static final int DELTA_FILE_IDX = 1;
	private static final int FIRST_INPUT = 4;
	private static MyLogger LOGGER = MyLogger.getLogger(IOsDeltaExporterOperation.class);
	
	
	public IOsDeltaExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsDeltaExporterOperation() {
		this("iosDeltaExporter", "Exports all datasets delta to the given file path and returns a file path, copy of the requested delta.",
				new NumberOperation("number", "rouding", "Rouding precision", new NumberValue(Double.NaN)),
				new StringOperation("string", "filePath", "Base file path containing the full output. This must be constant between runs.", new StringValue("")),
				new StringOperation("string", "headerPrefixe", "Prefix of the column headers. This must be constant between runs.", new StringValue("")),
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
		
		Double rounding = ((NumberValue)inputs.get(0)).getNumberValue();
		String baseFilePath = extractedFileRootPath(((StringValue) inputs.get(DELTA_FILE_IDX)).getValue(targetStock));
		String headersPrefix = ((StringValue) inputs.get(2)).getValue(targetStock);
		Boolean append = Boolean.valueOf(((StringValue) inputs.get(APPEND_IDX)).getValue(targetStock));
		
		boolean fileExists = Files.exists(Path.of(URI.create("file://" + baseFilePath))) && new File(baseFilePath).length() > 0;
		if (!fileExists) {
			append = false;
			LOGGER.warn("Append was requested but is not possible as the file is empty or inexistent: " + baseFilePath);
		}

		try {
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			SortedMap<Date, double[]> factorisedInput = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, true);
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			if (!Double.isNaN(rounding)) {
				factorisedInput = factorisedInput.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
					return Arrays.stream(e.getValue()).map(d -> Precision.round(d, rounding.intValue(), BigDecimal.ROUND_HALF_EVEN)).toArray();
				}, (a,b) -> a, TreeMap::new));
			}
			
			LinkedHashMap<String, SortedMap<Date, double[]>> series = new LinkedHashMap<>();
			series.put(headersPrefix, factorisedInput);
			LinkedHashMap<String, List<String>> headersPrefixes = new LinkedHashMap<>();
			headersPrefixes.put(headersPrefix, inputsOperandsRefs);
			if (append) {
				SeriesPrinter.appendto(baseFilePath, headersPrefixes, series);
			} else {
				SeriesPrinter.printo(baseFilePath, headersPrefixes, series);
			}
			
			//TODO expended against sliding
			//return the delta: parentStartShift = thisStartShift - this.operandsRequiredStartShift()
			int parentStartShift = thisStartShift - this.operandsRequiredStartShift();
			
			String headers;
			try (BufferedReader fileReader = new BufferedReader(new FileReader(new File(baseFilePath)))) {
				headers = fileReader.readLine();
			}
			
			List<String> reversedContent = new ArrayList<>();
			try (ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(new File(baseFilePath))) {
				for (int i = parentStartShift-1; i >= 0; i--) {
					reversedContent.add(reversedLinesFileReader.readLine());
				}
			}
			
			String extension = FilenameUtils.getExtension(baseFilePath);
			String deltaFile = baseFilePath.replaceAll("." + extension, "_" + UUID.randomUUID() + "." + extension);
			try (PrintStream printStream = new PrintStream(new File(deltaFile))) {
				printStream.println(headers);
				for (int i = parentStartShift-1; i >= 0; i--) {
					printStream.println(reversedContent.get(i));
				}
			}
			
			return new StringValue(deltaFile);
			
		} catch (Exception e) {
			LOGGER.error(this.getReference() + " : " + e, e);
		}

		return new StringValue("None");
	}

	private String extractedFileRootPath(String fileRootPath) {
		if (!fileRootPath.startsWith(File.separator)) {
			fileRootPath = System.getProperty("installdir") + File.separator + fileRootPath;
		}
		return fileRootPath;
	}

	@Override
	public int operandsRequiredStartShift() {
		int lagAmount = getLagAmount(getOperands());
		LOGGER.info("Delta input start NaN required left shift: " + lagAmount);
		return lagAmount;
	}
	
	private int getLagAmount(List<Operation> operations) {
		if (operations.isEmpty()) return 0;
		return operations.stream()
			.map(o -> Math.max((o instanceof LaggingOperation)?((LaggingOperation) o).rightLagAmount():0, getLagAmount(o.getOperands())))
			.reduce(0, (a, e) -> Math.max(a, e));
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... deltaFiles) {
		if (deltaFiles != null) {
			for (int i = 0; i < deltaFiles.length; i++) {
				try {
					Path deltaFile = Path.of(URI.create("file://" + deltaFiles[i]));
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
					LOGGER.error("Can't create path from " + deltaFiles[i], e1);
				}
			}
		}
	}

}
