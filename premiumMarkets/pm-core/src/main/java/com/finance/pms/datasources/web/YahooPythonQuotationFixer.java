package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.DayQuoteYahooPythonFormater;
import com.finance.pms.datasources.web.formaters.YahooPyQuotation;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.SplitOption;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class YahooPythonQuotationFixer {
	
	private static MyLogger LOGGER = MyLogger.getLogger(YahooPythonQuotationFixer.class);
	
	private Path python_py;
	private Stock stock;
	private Date start;
	private Date end;
	

	public YahooPythonQuotationFixer(Path python_py, Stock stock, Date end) {
		super();
		this.python_py = python_py;
		this.stock = stock;
		this.start = fixStart();
		this.end = end;
	}

	private String getPmFileFixFolder() {
		String rootPath = MainPMScmd.getMyPrefs().get("quotes.yahooFixDumpRootPath", "");
		if (rootPath.isEmpty()) {
			return null;
		}
		return rootPath;
	}
	
	public void fixQuotations() {
		
		LOGGER.info("Entering fixer for: " + stock + " from " + start + " to " + end);
		
		try {
			//File validity checks
			String fileRootPath = getPmFileFixFolder();
			if (fileRootPath == null || fileRootPath.isEmpty()) {
				LOGGER.info("File root path is not set. Ignoring yahoo fix.");
				return;
			}
			
			//Retrieve
			String webFilePathName = fileRootPath + File.separator + stock.getSymbol() + "_quotes.csv";
			Boolean fileExists = fileExists(webFilePathName);
			if (fileExists) {
				LOGGER.info("Fix has already been applied. Ignoring yahoo fix.");
				return;
			} else {
				retreiveWebData(webFilePathName);
			}
			
			//Build webData maps
			DayQuoteYahooPythonFormater dsf = new DayQuoteYahooPythonFormater(null, stock, stock.getMarketValuation().getCurrency().name());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");			
			List<ValidatableDated> webValidatables = new ArrayList<ValidatableDated>();
			try (BufferedReader in = new BufferedReader(new FileReader(new File(webFilePathName)));) {
				
				String line = in.readLine(); //header
				while ((line = in.readLine()) != null) {
					if (line.isEmpty()) continue;
					try {
						List<ValidatableDated> lineValidatables = dsf.formatLine(line).stream().map(v -> (ValidatableDated) v).collect(Collectors.toList());
						YahooPyQuotation yq;
						if (lineValidatables.size() > 0 && (yq = (YahooPyQuotation)lineValidatables.get(0)).getSplit() != 1d) {
							webValidatables = webValidatables.stream()
									.map(v -> ((YahooPyQuotation) v).reverseSplit(yq.getSplit()))
									.collect(Collectors.toList());
						}
						webValidatables.addAll(lineValidatables);
					} catch (Exception e) {
						LOGGER.warn("Error reading line: " + line + " for " + stock.getSymbol() + ", from " + start + " to " + end + ". " + e.toString());
					}
				}
			}
			
			//Compare with db
			Quotations quotations = QuotationsFactories.getFactory().getRawQuotationsInstance(stock, start, end, false, Currency.NAN, 0, SplitOption.SPLITFREE, ValidityFilter.CLOSE);
			SortedMap<Date, double[]> dbData = QuotationsFactories.getFactory().buildExactMapFromQuotationsOHLCV(quotations);
			
			TreeSet<ValidatableDated> fixedValidatables = new TreeSet<ValidatableDated>();
			Iterator<ValidatableDated> iterator = webValidatables.iterator();
			while (iterator.hasNext()) {
				ValidatableDated webValid = iterator.next();
				Date date = webValid.getDate();
				double[] dbValue = dbData.get(date);
				if (dbValue == null) {
					LOGGER.warn("Missing date in db: " + dateFormat.format(date));
					fixedValidatables.add(webValid);
				} else {
					List<Comparable<?>> webValue = ((YahooPyQuotation) webValid).getOHLCV();
					Boolean inconsistent = false;
					for(int i = 0; i < dbValue.length; i++) {
						double doubleValue = (i<dbValue.length-1)?((BigDecimal) webValue.get(i)).doubleValue():((Long) webValue.get(i)).doubleValue();
						if (Precision.round(doubleValue, 4) != dbValue[i]) {
							LOGGER.warn("Inconstistent " + i + " at " + dateFormat.format(date) + ": web " + webValue.get(i) + " V db " + dbValue[i]);
							fixedValidatables.add(webValid);
							inconsistent = true;
							break;
						}
					}
					if (!inconsistent) { //check if split is set
						Integer closestIndexBeforeOrAtDateOrIndexZero = quotations.getClosestIndexBeforeOrAtDateOrIndexZero(0, date);
						QuotationUnit quotationUnit = quotations.get(closestIndexBeforeOrAtDateOrIndexZero);
						if (quotationUnit.getDbStoredSplit() == null) {
							LOGGER.warn("Split not set at " + dateFormat.format(date) + ": web " + webValid + " V db " + quotationUnit + "(" + Arrays.toString(dbValue) + ")");
							fixedValidatables.add(webValid);
						}
					}
				}
			}
			
			LOGGER.info("Count of descrepencies in db: " + fixedValidatables.size());
			if (LOGGER.isDebugEnabled()) LOGGER.debug(fixedValidatables);
			
			List<ValidatableDated> ohlcvsValids = fixedValidatables.stream().filter(ohlcvs -> !ohlcvs.getDate().after(end)).collect(Collectors.toList());
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<ValidatableDated>(ohlcvsValids), new ArrayList<>());
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
	}

	private String retreiveWebData(String webQuotesPathname) throws IOException {
    	
		//Web call
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String symbol = stock.getSymbol();
		if ('^' != symbol.charAt(0) && stock.getCategory().equals(StockCategories.INDICES_OTHER)) symbol = "^" + symbol;
		
		ProcessBuilder pb = new ProcessBuilder("python3", python_py.toString(), symbol, dateFormat.format(start), dateFormat.format(end));
		Process p = pb.start();
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(new File(webQuotesPathname)));
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));){
			
			String line = null;
			while ((line = in.readLine()) != null) {
				out.write(line);
				out.newLine();
			}
			out.flush();
		
		} catch (IOException e) {
			LOGGER.error(e, e);
			throw e;
		}
		
		return webQuotesPathname;
	}

	private Boolean fileExists(String webQuotesPathname) {
		//File exists checks
		Path path = Path.of(URI.create("file://" + webQuotesPathname));
		if (Files.exists(path)) {
			LOGGER.warn("File " + webQuotesPathname + " already exists. Please move/delete. Adjust dates, and append");
			return true;
		}
		return false;
	}
	
	private Date fixStart() {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date minStart = dateFormat.parse("1970-01-01");
			
			Date firstQuotationDateFromQuotations = DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
			if (minStart.before(firstQuotationDateFromQuotations)) {
				return minStart;
			}
			return firstQuotationDateFromQuotations;
		} catch (Exception e) {
			LOGGER.error(e, e);
			return null;
		}
		
	}

}
