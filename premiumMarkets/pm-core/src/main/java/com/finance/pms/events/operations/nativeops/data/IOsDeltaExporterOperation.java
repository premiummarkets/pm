package com.finance.pms.events.operations.nativeops.data;

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
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.math3.util.Precision;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.InputFileChecker;
import com.finance.pms.datasources.files.OvewriteDeltaException;
import com.finance.pms.datasources.files.SeriesPrinter;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
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
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

/**
 * The full data file is a permanent local file
 * Only the requested delta is sent back
 * 
 * @author guil
 *
 */
public class IOsDeltaExporterOperation extends FileExporter implements CachableOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(IOsDeltaExporterOperation.class);
	
	private static final int IS_APPEND_IDX = 3;
	private static final int DELTA_FILE_IDX = 1;
	private static final int HEADER_PREFIX_IDX = 2;
	private static final int FIRST_INPUT = 4;
	
	
	public IOsDeltaExporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsDeltaExporterOperation() {
		this("iosDeltaExporter", "Exports all datasets delta to the given file path and returns a file path, copy of the requested delta. Only accept NaN for the last rows of the last column.",
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
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double rounding = ((NumberValue)inputs.get(0)).getNumberValue();
		String baseFilePath = extractedFileRootPath(((StringValue) inputs.get(DELTA_FILE_IDX)).getValue(targetStock));
		String headersPrefix = ((StringValue) inputs.get(HEADER_PREFIX_IDX)).getValue(targetStock);
		
		Boolean append = Boolean.valueOf(((StringValue) inputs.get(IS_APPEND_IDX)).getValue(targetStock));
		if	(append && (!Files.exists(Path.of(URI.create("file://" + baseFilePath))) || new File(baseFilePath).length() == 0)) {//INIT has been set. This means append was requested but failed.
			append = false; //for this run
		}
		
		try {

			//Check and transform
			@SuppressWarnings("unchecked")
			List<? extends NumericableMapValue> developpedInputs = (List<? extends NumericableMapValue>) inputs.subList(FIRST_INPUT, inputs.size());
			List<String> inputsOperandsRefs = ValueManipulator.extractOperandFormulaeShort(targetStock, thisCallStack, getOperands().subList(FIRST_INPUT, getOperands().size()), developpedInputs);
			
			Set<Date> knownMissingKeys = targetStock.missingData();
			Map<InputToArrayReturn, SortedMap<Date, double[]>> inputListToArray = ValueManipulator.inputListToArray(targetStock, developpedInputs, false, false, inputsOperandsRefs.size() -1);
			SortedMap<Date, double[]> factorisedInput = inputListToArray.get(InputToArrayReturn.RESULTS);
			
			//Append or over write
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
			
			//NaNs Error check
			if (inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).keySet().stream().anyMatch(k -> !knownMissingKeys.contains(k))) {
				String nansLines = inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).entrySet().stream()
																			.map(e -> e.getKey() + ": " + Arrays.asList(Arrays.toString(e.getValue())).toString())
																			.reduce((a, e) -> a + " " + e)
																			.orElse("");
				List<Date> nansDates = inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS).keySet().stream().filter(k -> !knownMissingKeys.contains(k)).collect(Collectors.toList());
				String nansResultSummary = inputListToArray.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().size()).reduce((a, e) -> a + " " + e).orElse("");
				String nansDetails = nansResultSummary + ". Unexpected: " + nansLines.substring(0, Math.min(nansLines.length(), 15000));
				
				if (LOGGER.isInfoEnabled()) {
					//SeriesPrinter.printo(baseFilePath + "_ERRONEOUS_" + InputToArrayReturn.RESULTS, headersPrefixes, series);
					LinkedHashMap<String, SortedMap<Date, double[]>> unexpectedNansSeries = new LinkedHashMap<>();
					unexpectedNansSeries.put(headersPrefix, inputListToArray.get(InputToArrayReturn.OTHERUNEXPECTEDNANS));
					SeriesPrinter.printo(baseFilePath + "_ERRONEOUS_" + InputToArrayReturn.OTHERUNEXPECTEDNANS, headersPrefixes, unexpectedNansSeries);
				}
				
				//throw new Exception("Unexpected NaN data in series (known NaNs " + knownMissingKeys + "). Summary: " + nansDates + ". Details: " + nansDetails);
				LOGGER.warn("Unexpected NaN data in series (known NaNs " + knownMissingKeys + "). Summary: " + nansDates + ". Details: " + nansDetails);
			}
			
			//Effective print
			String deltaFile;
			if (append) {
				synchronized (LOGGER) {
					try {
						baseFilePath = SeriesPrinter.appendto(baseFilePath, headersPrefixes, series);
					} catch (OvewriteDeltaException e) {
						LOGGER.warn("Invalid delta file content: " + e.getFilePath() + ". Overwriting ..");
						try {
							Files.delete(Path.of(URI.create("file://" + e.getFilePath())));
						} catch (IOException e2) {
							LOGGER.error(e2, e2);
						}
						baseFilePath = SeriesPrinter.printo(baseFilePath, headersPrefixes, series);
					}
					deltaFile = createDeltaFile(targetStock, parentRequiredStartShift, baseFilePath);
				}
			} else {
				synchronized (LOGGER) {
					baseFilePath = SeriesPrinter.printo(baseFilePath, headersPrefixes, series);
					deltaFile = createDeltaFile(targetStock, parentRequiredStartShift, baseFilePath);
				}
			}
			
			//Quotation match check
			InputFileChecker.checkInputAgainstQuotations(
					deltaFile, targetStock.getStock(), ValidityFilter.getFilterFor(this.getRequiredStockData()), 
					0, this.getLagAmount(targetStock, thisCallStack, getOperands()), knownMissingKeys);
			
			
			return new StringValue(deltaFile);

		} catch (Exception e) {
			LOGGER.error(this.getReference() + ": " + e, e);
		}

		return new StringValue("NONE");
	}

	//Should return the delta: starting from required parentStartShift 
	private String createDeltaFile(TargetStockInfo targetStock, int parentRequiredStartShift, String baseFilePath) throws IOException, ParseException, FileNotFoundException, NotEnoughDataException {
		
		Date startDate = targetStock.getStartDate(parentRequiredStartShift);
		Date endDate = targetStock.getEndDate();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		LOGGER.info("Extracting delta of " + baseFilePath + ", from " + simpleDateFormat.format(startDate) + " to " + simpleDateFormat.format(endDate));
		
		List<String> reversedContent = new ArrayList<>();
		try (ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(new File(baseFilePath))) {
			Date date = DateFactory.getNowEndDate();
			
			String dateStr = simpleDateFormat.format(date);
			do {
				String readLine = reversedLinesFileReader.readLine();
				dateStr = readLine.split(",")[0];
				if (!"date".equals(dateStr.trim()) && (date = simpleDateFormat.parse(dateStr)).compareTo(endDate) <= 0) {
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
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent) {
		
		int lagAmount = 0;
		try {
			lagAmount = getLagAmount(targetStock, thisCallStack, getOperands());
		} catch (Exception e) {
			LOGGER.warn("Can't calculate the lag amount in order to append to the delta file. Will overwrite ..");
		}
		LOGGER.info("Delta input start NaN required left shift: " + lagAmount);
		
		try {
			
//			Stock stock = targetStock.getStock();
//			Date startDate = targetStock.getStartDate(thisOutputRequiredStartShiftByParent);
//			Date endDate = targetStock.getEndDate();
//			int spanInDataPoints = QuotationsFactories.getFactory().nbDataPointsBetweenFor(stock, startDate, endDate, ValidityFilter.getFilterFor(this.getRequiredStockData()));
//			if (spanInDataPoints <= lagAmount) 
//				throw new NotEnoughDataException(stock, startDate, endDate, "Span is too small for the inherent lag of the operation: " + spanInDataPoints + "<=" + lagAmount, null);

			int shift = deltaShiftFix(targetStock, thisCallStack, thisOutputRequiredStartShiftByParent, lagAmount);
			return lagAmount + shift;
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new RuntimeException(e);
		}

	}

	private int deltaShiftFix(TargetStockInfo targetStock,  List<StackElement> thisCallStack, int thisOutputRequiredStartShiftFromParent, int lagAmount) throws FileNotFoundException, IOException, ParseException, NotEnoughDataException {
		
		SimpleDateFormat dflog = new SimpleDateFormat("yyyy-MM-dd");

		int leftShiftGapDataPoints = 0;
		int rightShiftGapDataPoints = 0;
		Boolean isInit = false;
		
		Date startDateShifted = targetStock.getStartDate(thisOutputRequiredStartShiftFromParent);
		Date startDate = targetStock.getStartDate(0);
		Date endDate = targetStock.getEndDate();
		StringValue parameter = (StringValue) getOperands().get(DELTA_FILE_IDX).run(targetStock, thisCallStack, 0);
		String baseFilePath = extractedFileRootPath(parameter.getValue(targetStock));
		
		Boolean isAppending = Boolean.valueOf(((StringValue) getOperands().get(IS_APPEND_IDX).getOrRunParameter(targetStock, thisCallStack).orElseThrow()).getValue(targetStock));
		if (isAppending) {
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			boolean fileExists = Files.exists(Path.of(URI.create("file://" + baseFilePath))) && new File(baseFilePath).length() > 0;
			if (!fileExists) {
				LOGGER.info("Re initialising. File does not exists: " + baseFilePath); 
				isInit = true;
			} else {
				
				try {
					
					ValidityFilter filterFor = ValidityFilter.getFilterFor(this.getRequiredStockData());
					
					//XXX Does this can work here as the delta is not calculated yet?
					//InputFileChecker.checkInputAgainstQuotations(baseFilePath, targetStock.getStock(), filterFor, 0, this.getLagAmount(getOperands()), new HashSet<>());
					
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
							LOGGER.info("Re initialising. File is empty: " + baseFilePath); 
							isInit = true;
						}
						//start || end < first date
						else if (startDate.before(firstLineDate) || endDate.before(firstLineDate)) { 
							throw new NotEnoughDataException(targetStock.getStock(), "Start or End date is beyond first quoation.", null);
						}
						//start beyond (< first date) and first date <= end <= last date
						//or
						//first date <= start && end <= last date
						else if (endDate.compareTo(lastLineDate) <= 0) {
							if (startDateShifted.before(firstLineDate)) { //start is beyond: fixing shift to first date
								int fixedShift = QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), firstLineDate, startDate, filterFor);
								rightShiftGapDataPoints = - (thisOutputRequiredStartShiftFromParent - fixedShift);
								rightShiftGapDataPoints += -QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), firstLineDate, endDate, filterFor);
							} else {
								rightShiftGapDataPoints = -QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), startDateShifted, endDate, filterFor);
							}
							LOGGER.info("end <= last date. We have all we can gets. [start, end] needs removing. Shift in data points (negative => right): " + rightShiftGapDataPoints);
						}
						//start beyond (< first date) && last date < end ie the range is bigger then the file and spans on both sides
						//Or
						//first date <= start < end && last date <= end
						else if (startDateShifted.before(lastLineDate) && endDate.after(lastLineDate)) {
							if (startDateShifted.before(firstLineDate)) { //start is beyond
								int fixedShift = QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), firstLineDate, startDate, filterFor);
								rightShiftGapDataPoints = -(thisOutputRequiredStartShiftFromParent - fixedShift);
								rightShiftGapDataPoints += -QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), firstLineDate, lastLineDate, filterFor);
							} else {
								rightShiftGapDataPoints = -QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), startDateShifted, lastLineDate, filterFor);
							}
							LOGGER.info("start < last date && last date < end. Only ]last date, end] needs filling in. [start, last date] needs removing. Shift in data points (negative => right): " + rightShiftGapDataPoints);
						}
						//last date < start
						else if (startDateShifted.after(lastLineDate)) {
							leftShiftGapDataPoints = QuotationsFactories.getFactory().nbDataPointsBetweenFor(targetStock.getStock(), lastLineDate, startDateShifted, filterFor);
							LOGGER.info("last date < start. ]last date, end] needs filling in. ]last date, start[ needs adding. Shift in data points (positive => left): " + leftShiftGapDataPoints);
						}
						
						if ((thisOutputRequiredStartShiftFromParent + leftShiftGapDataPoints + rightShiftGapDataPoints) < 0) {//XXX Fixing discrepancies
							LOGGER.warn("Fixing potential righ shift discrepancy to parent shift value: parent " + thisOutputRequiredStartShiftFromParent + " is < calculated right shift " + -(leftShiftGapDataPoints + rightShiftGapDataPoints));
							leftShiftGapDataPoints = 0;
							rightShiftGapDataPoints = -thisOutputRequiredStartShiftFromParent;
						}

						//Final verdict
						LOGGER.info(
								"APPENDING: " + !isInit + ". " +
								"Using " + baseFilePath + ". " +
								"Delta shift fix TRANSLATED in DATA POINTS: " + (leftShiftGapDataPoints + rightShiftGapDataPoints) + " resulting in start " + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + leftShiftGapDataPoints + rightShiftGapDataPoints)) + ". " +
								"Adding input shift " + lagAmount + " required from this in DATA POINTS: " + (lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints) + " resulting in start " + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + lagAmount +leftShiftGapDataPoints + rightShiftGapDataPoints)) + ". " +
								"Delta file boundaries: ["+ dflog.format(firstLineDate) + "," + dflog.format(lastLineDate) + "]. " +
								"Requested boundaries: ["+ dflog.format(startDateShifted) + "," + dflog.format(endDate) + "]. " +
								"Resulting outputs ranges: " + 
								"delta file + operands Output: " +
									"[" + dflog.format(firstLineDate) + "," + dflog.format(lastLineDate) + "] + " +
									"[" + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints)) + "," + dflog.format(endDate) + "] > " + 
								"Input required by parent: [" + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent))  + "," + dflog.format(endDate) + "]");

					}
					
				} catch (NotEnoughDataException | NoQuotationsException e) {
					LOGGER.warn("Using " + baseFilePath + ". Append was requested but is not possible: " + e);
					isInit = true;
				}
			}
			
		} else {
			LOGGER.info("Re initialising. Appending was not requested. isAppending: " + isAppending); 
			isInit = true;
		}
		
		if (isInit) {
			
			try {
				Files.delete(Path.of(URI.create("file://" + baseFilePath)));
			} catch (IOException e) {
				LOGGER.warn("Reinitialisation is " + isInit + " and previous file does not exist (or was deleted previously): " + e);
			}
			
			leftShiftGapDataPoints = (int) TimeUnit.DAYS.convert(DateFactory.midnithDate(new Date()).getTime() - DateFactory.dateAtZero().getTime(), TimeUnit.MILLISECONDS);
			
			LOGGER.info("NOT APPENDING (ie re INIT). Append was requested but is NOT POSSIBLE as the file is empty, inexistent or corrupted. " +
					"Using " + baseFilePath + ". " +
					"Delta shift fix TRANSLATED in DATA POINTS: " + (leftShiftGapDataPoints + rightShiftGapDataPoints) + ": " + 
					dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + leftShiftGapDataPoints + rightShiftGapDataPoints)) + ". " +
					"+ input shift " + lagAmount + " required from this: " + (lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints) + ": " +  ": " + 
					dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + leftShiftGapDataPoints + rightShiftGapDataPoints + lagAmount)) + ". " +
					"Requested boundaries: ["+ dflog.format(startDateShifted) + "," + dflog.format(endDate) + "]. " +
					"Resulting outputs ranges: " + 
					"operands Output: " +
						"[" + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent + lagAmount + leftShiftGapDataPoints + rightShiftGapDataPoints)) + "," + dflog.format(endDate) + "] > " + 
					"Input required by parent: [" + dflog.format(targetStock.getStartDate(thisOutputRequiredStartShiftFromParent))  + "," + dflog.format(endDate) + "]");
		}

		int shift = leftShiftGapDataPoints + rightShiftGapDataPoints;
		return shift;
	}
	
	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStockOpt, Optional<String> userOperationName) {
		try {
			if (targetStockOpt.isPresent()) {
				TargetStockInfo targetStock = targetStockOpt.get();
				getOperands().get(DELTA_FILE_IDX).getOrRunParameter(targetStock).ifPresent(rootFileValue -> {
					try {
						String rootFileFullPath = extractedFileRootPath(((StringValue) rootFileValue).getValue(targetStock));
						if (rootFileFullPath != null) {
	
							Path rootFile = Path.of(URI.create("file://" + rootFileFullPath));
							LOGGER.info("Deleting file local copy: " + rootFile.toString());
							boolean exist = Files.exists(rootFile);
							if (exist) {
								try {
									Files.delete(rootFile);
								} catch (IOException e) {
									LOGGER.error(e, e);
								}
							}
						}
					} catch (Exception e1) {
						LOGGER.error("Can't create path from " + rootFileValue, e1);
					}
				});
			}
		} catch(Exception e) {
			LOGGER.warn(e);
		}
	}
	
	@Override
	public Optional<String> calculationStatus(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		StringValue rootFileValue = (StringValue) getOperands().get(DELTA_FILE_IDX).getOrRunParameter(targetStock, thisCallStack).orElse(null);
		String rootFileFullPath = extractedFileRootPath(((StringValue) rootFileValue).getValue(targetStock));
		return Optional.of(this.getReference() + ": " + rootFileFullPath);
	}

	@Override
	public Integer operationNaturalShift() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		String thisShortName = "iod";
		String opsFormulaeShort = super.toFormulaeShort(targetStock, thisCallStack, this.getOperands());
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

	@Override
	public boolean isDataShiftSensitive() {
		return true;
	}

	@Override
	public Operation getFilePathOperand() {
		return this.getOperands().get(DELTA_FILE_IDX);
	}
	
	@Override
	public Operation getHeaderPrefixOperand() {
		return this.getOperands().get(HEADER_PREFIX_IDX);
	}
	
	
	
}
