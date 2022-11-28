package com.finance.pms.events.operations.nativeops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
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
public class IOsDeltaExporterOperation extends StringerOperation implements CachableOperation {
	
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
				new StringOperation("string", "filePath", "Export delta file path full name with extension.", new StringValue("")),
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
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double rounding = ((NumberValue)inputs.get(0)).getNumberValue();
		String baseFilePath = extractedFileRootPath(((StringValue) inputs.get(DELTA_FILE_IDX)).getValue(targetStock));
		String headersPrefix = ((StringValue) inputs.get(2)).getValue(targetStock);
		
		Boolean append;
		String appedString = ((StringValue) inputs.get(APPEND_IDX)).getValue(targetStock);
		if	("INIT".equals(appedString)) {
			append = false;
			getOperands().get(APPEND_IDX).setParameter(new StringValue(Boolean.TRUE.toString()));
		} else {
			append = Boolean.valueOf(appedString);
		}
		
		try {
			//Append or over write
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
				synchronized (LOGGER) {
					baseFilePath = SeriesPrinter.appendto(baseFilePath, headersPrefixes, series);
					return  new StringValue(createDeltaFile(targetStock, parentRequiredStartShift, baseFilePath));
				}
			} else {
				baseFilePath = SeriesPrinter.printo(baseFilePath, headersPrefixes, series);
				return  new StringValue(createDeltaFile(targetStock, parentRequiredStartShift, baseFilePath));
			}
			
		} catch (Exception e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		}

		return new StringValue("NONE");
	}

	private String createDeltaFile(TargetStockInfo targetStock, int parentRequiredStartShift, String baseFilePath) throws IOException, ParseException, FileNotFoundException {
		//Return the delta: from parentStartShift 
		Date startDate = targetStock.getStartDate(parentRequiredStartShift);
		Date endDate = targetStock.getEndDate();
		List<String> reversedContent = new ArrayList<>();
		try (ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(new File(baseFilePath))) {
			Date date = DateFactory.getNowEndDate();
			String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(date);
			do {
				String readLine = reversedLinesFileReader.readLine();
				dateStr = readLine.split(",")[0];
				if (!"date".equals(dateStr.trim()) && (date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr)).compareTo(endDate) <= 0) {
					reversedContent.add(readLine);
				}
			} while (!"date".equals(dateStr.trim()) && date.compareTo(startDate) > 0);
		}
		
		String headers;
		try (BufferedReader fileReader = new BufferedReader(new FileReader(new File(baseFilePath)))) {
			headers = fileReader.readLine();
		}
		
		String extension = FilenameUtils.getExtension(baseFilePath);
		String deltaFile = baseFilePath.replaceAll("." + extension, "_" + UUID.randomUUID() + "." + extension);
		try (PrintStream printStream = new PrintStream(new File(deltaFile))) {
			printStream.println(headers);
			for (int i = reversedContent.size()-1; i >= 0; i--) {
				printStream.println(reversedContent.get(i));
			}
		}
		return deltaFile;
	}

	private String extractedFileRootPath(String fileRootPath) {
		if (!fileRootPath.startsWith(File.separator)) {
			fileRootPath = System.getProperty("installdir") + File.separator + fileRootPath;
		}
		return fileRootPath;
	}

	@Override
	/**
	 * The INIT will always calculate from the farthest left shift and we assume the delta file has always been initialised this way.
	 */
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		
		int lagAmount = getLagAmount(getOperands());
		LOGGER.info("Delta input start NaN required left shift: " + lagAmount);
		
		int shift = deltaShiftFix(targetStock, thisParentStartShift, lagAmount);

		return lagAmount + shift;

	}

	private int deltaShiftFix(TargetStockInfo targetStock, int thisParentStartShift, int lagAmount) {
		
		double dataPointFactor = targetStock.getStock().getTradingMode().getDataPointFactor();

		int leftShiftGapDataPoints = 0;
		int rightShiftGapDataPoints = 0;
		Boolean isInit = false;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Date startDateParentShifted = targetStock.getStartDate(thisParentStartShift);
		Date endDate = targetStock.getEndDate();
		StringValue parameter = (StringValue) getOperands().get(DELTA_FILE_IDX).getParameter();
		String baseFilePath = extractedFileRootPath(parameter.getValue(targetStock));
		
		if (Boolean.valueOf(((StringValue) getOperands().get(APPEND_IDX).getParameter()).getValue(targetStock))) {
			
			boolean fileExists = Files.exists(Path.of(URI.create("file://" + baseFilePath))) && new File(baseFilePath).length() > 0;
			if (!fileExists) {
				isInit = true;
			} else {
				try (BufferedReader fileReader = new BufferedReader(new FileReader(new File(baseFilePath))); 
						ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(new File(baseFilePath))) {
					
					//File checks
					fileReader.readLine(); //headers

					//First line
					final String firstLine = fileReader.readLine();
					final String firstDateStr = (firstLine != null)?firstLine.split(",")[0]: null;
					final Date firstLineDate = (firstDateStr != null)? df.parse(firstDateStr):null;

					//Last line
					final String lastLine = reversedLinesFileReader.readLine();
					final String lastDateStr = (lastLine != null)? lastLine.split(",")[0] : null;
					final Date lastLineDate = (firstDateStr != null && !"date".equals(lastDateStr))? df.parse(lastDateStr):null;

					//Check date intersections 
					if (firstLineDate == null || lastLineDate == null) {
						isInit = true;
					}
					//start < first date and last date < end => no need to search in the file (short cut)
					else if (startDateParentShifted.before(firstLineDate) && lastLineDate.before(endDate)) {
						int startToLastDateInCalendarDays = 
								(int) -TimeUnit.DAYS.convert(lastLineDate.getTime() - startDateParentShifted.getTime(), TimeUnit.MILLISECONDS);
						rightShiftGapDataPoints =  (int) (startToLastDateInCalendarDays * dataPointFactor);
						LOGGER.info("start < first date and last date < end. Shift in data points (positive => left): " + leftShiftGapDataPoints);
					}
					//start > last date => fill from last date to end => add the ]last date,start[ left shift gap (positive)
					else if (startDateParentShifted.compareTo(lastLineDate) > 0) {
						int lastDateTostartInCalendarDays = 
								(int) TimeUnit.DAYS.convert(startDateParentShifted.getTime() - lastLineDate.getTime(), TimeUnit.MILLISECONDS);
						leftShiftGapDataPoints = ((int) (lastDateTostartInCalendarDays * dataPointFactor)); //+ 10 for potential gaps ??
						LOGGER.info("start > last date. Shift in data points (positive => left): " + leftShiftGapDataPoints);
					}
					else { //first date or beyond <= start <= last date => remove the -[start,last date] right shift gap (negative)
						//Where are the start and end date in the file
						String currentDateStr = lastDateStr;
						Boolean found = false;
						int startToLastDateInDataPoints = 0;
						int endToLastDateInDataPoints = 0; //XXX An attempt to avoid start > end
						while (currentDateStr != null && !"date".equals(currentDateStr)) {
							Date currentDateLineDate = df.parse(currentDateStr);
							startToLastDateInDataPoints = startToLastDateInDataPoints - 1;
							if (currentDateLineDate.compareTo(endDate) > 0) endToLastDateInDataPoints = endToLastDateInDataPoints -1;
							if (currentDateLineDate.compareTo(startDateParentShifted) <= 0) {
								found = true;
								break;
							}
							String nextLine = reversedLinesFileReader.readLine();
							currentDateStr = (nextLine != null)? nextLine.split(",")[0] : null;
						}
						if (!found) { //The required start is beyond stock quotations start
							int requiredDateToFirstPossibleDateGapInclusiveInCalendarDays = 
									(int) -TimeUnit.DAYS.convert(firstLineDate.getTime() - startDateParentShifted.getTime(), TimeUnit.MILLISECONDS);
							int requiredDateToFirstPossibleDateGapInclusiveInDataPoints = (int) (requiredDateToFirstPossibleDateGapInclusiveInCalendarDays * dataPointFactor);
							rightShiftGapDataPoints = 	requiredDateToFirstPossibleDateGapInclusiveInDataPoints + 
														startToLastDateInDataPoints - endToLastDateInDataPoints;
							LOGGER.info("The required start is beyond stock quotations. Shift in calendar data points (negative => right): " + 
									requiredDateToFirstPossibleDateGapInclusiveInDataPoints + "+" + 
									startToLastDateInDataPoints + "+" + endToLastDateInDataPoints);
						} else {
							rightShiftGapDataPoints = startToLastDateInDataPoints - endToLastDateInDataPoints;
							LOGGER.info("first date <= start <= last date. Shift in calendar data points (negative => right): " + startToLastDateInDataPoints + "-" + endToLastDateInDataPoints);
						}			
					}

					//Finally
					if (!isInit) {
						LOGGER.info(
								"Using " + baseFilePath + ". " +
										"Delta shift fix TRANSLATED in data POINTS: " + (leftShiftGapDataPoints + rightShiftGapDataPoints) + ". " +
										"With added input shift required from this: " + (lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints) + ". " +
										"Delta file boundaries: ["+ df.format(firstLineDate) + "," + df.format(lastLineDate) + "]. " +
										"Requested boundaries: ["+ df.format(startDateParentShifted) + "," + df.format(endDate) + "]. " +
										"Resulting dates: Operands output (delta + output from operands calculation) > this Input (required from this) > this Output (Input required from parent) => " + 
										"[" + df.format(firstLineDate) + "," + df.format(lastLineDate) + "] + " +
										"[" + df.format(targetStock.getStartDate(thisParentStartShift + lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints)) + "," + df.format(endDate) + "] > " + 
										"[" + df.format(targetStock.getStartDate(thisParentStartShift + lagAmount)) + "," + df.format(endDate) + "] > " +
										"[" + df.format(targetStock.getStartDate(thisParentStartShift))  + "," + df.format(endDate) + "]");
					} else {
						LOGGER.warn("Using " + baseFilePath + ". " +
								"Append was requested but is not possible. First line date: " + df.format(firstLineDate) + ", last line date: " + df.format(lastLineDate) + 
								". Start requested: " + df.format(startDateParentShifted) + ", end requested: " + df.format(endDate));
					}

				} catch (Exception e) {
					LOGGER.error(e, e);
					throw new RuntimeException(e);
				}
			}
			
		} else {
			isInit = true;
			try {
				Files.delete(Path.of(URI.create("file://" + baseFilePath)));
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
		
		if (isInit) {
			getOperands().get(APPEND_IDX).setParameter(new StringValue("INIT"));
			leftShiftGapDataPoints = (int) TimeUnit.DAYS.convert(DateFactory.midnithDate(new Date()).getTime() - DateFactory.dateAtZero().getTime(), TimeUnit.MILLISECONDS);
			LOGGER.info("Append was requested but is not possible as the file is empty or inexistent: " +
					"Using " + baseFilePath + ". " +
					"Delta shift fix in data points: " + (leftShiftGapDataPoints + rightShiftGapDataPoints) + ". " +
					"With added input shift required from this: " + (lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints) + ". " +
					"Resulting dates: Input (required from operands calculation) > Ouput (from this) > Input (required from this parent) => " + 
					"[" + df.format(targetStock.getStartDate(thisParentStartShift + lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints)) + "," + df.format(endDate) + "] > " + 
					"[" + df.format(targetStock.getStartDate(thisParentStartShift + lagAmount)) + "," + df.format(endDate) + "] > " +
					"[" + df.format(startDateParentShifted)  + "," + df.format(endDate) + "]");
		}

		int shift = leftShiftGapDataPoints + rightShiftGapDataPoints;
		return shift;
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
	
	@Override
	public Integer operationNaturalShift() {
		// TODO Auto-generated method stub
		return 0;
	}

}
