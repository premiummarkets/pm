package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Precision;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.formaters.DayQuoteYahooPythonFormater;
import com.finance.pms.events.quotations.Quotations;
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
		
		try {
			//File validity checks
			String fileRootPath = getPmFileFixFolder();
			if (fileRootPath == null || fileRootPath.isEmpty()) {
				LOGGER.info("File root path is not set. Ignoring yahoo fix.");
				return;
			}
			
			//Retrieve
			String webFilePathName = retreiveWebData(fileRootPath);
			if (webFilePathName == null || webFilePathName.isEmpty()) {
				LOGGER.info("Fix has laready been applied. Ignoring yahoo fix.");
				return;
			}
			
			//Compare and update
			Quotations quotations = QuotationsFactories.getFactory().getRawQuotationsInstance(stock, start, end, false, Currency.NAN, 0, ValidityFilter.SPLITFREE);
			SortedMap<Date, double[]> dbData = QuotationsFactories.getFactory().buildExactMapFromQuotationsOHLCV(quotations);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			TreeSet<ValidatableDated> validatables = new TreeSet<ValidatableDated>();
			DayQuoteYahooPythonFormater dsf = new DayQuoteYahooPythonFormater(null, stock, stock.getMarketValuation().getCurrency().name());
			
			SortedMap<Date, double[]> webData = new TreeMap<>();
			try (BufferedReader in = new BufferedReader(new FileReader(new File(webFilePathName)));) {
				
				String line = in.readLine(); //header
				while ((line = in.readLine()) != null) {
					if (line.isEmpty()) continue;
					String[] split = line.split(",");
					Date date = dateFormat.parse(split[0]);
					//skipping 1rst and last columns
					double[] value = Arrays.asList(split).subList(1, split.length-1).stream().mapToDouble(s -> Double.valueOf(s)).toArray();
					webData.put(date, value);
					
					double[] dbValue = dbData.get(date);
					List<ValidatableDated> formatLine = dsf.formatLine(line).stream().map(v -> (ValidatableDated) v).collect(Collectors.toList());
					if (dbValue == null) {
						LOGGER.warn("Missing date in db: " + dateFormat.format(date));
						validatables.addAll(formatLine);
					} else {
						for(int i = 0; i < dbValue.length; i++) {
							if (Precision.round(value[i], 4) != dbValue[i]) {
								LOGGER.warn("Inconstistent " + i + " at " + dateFormat.format(date) + ": web " + value[i] + " V db " + dbValue[i]);
								validatables.addAll(formatLine);
								break;
							}
						}
					}
				}
			}
			
			LOGGER.info("Count of descrepencies in db: " + validatables.size());
			LOGGER.debug(validatables);
			
			List<ValidatableDated> ohlcvValids = validatables.stream().filter(ohlcv -> !ohlcv.getDate().after(end)).collect(Collectors.toList());
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<ValidatableDated>(ohlcvValids), new ArrayList<>());
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
	}

	private String retreiveWebData(String fileRootPath) throws IOException {
		
		//File exists checks
		String webQuotesPathname = fileRootPath + File.separator + stock.getSymbol() + "_quotes.csv";
		Path path = Path.of(URI.create("file://" + webQuotesPathname));
		if (Files.exists(path)) {
			LOGGER.warn("File " + webQuotesPathname + " already exists. Please move/delete. Adjust dates, and append");
			return null;
		}
    	
		//Web call
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ProcessBuilder pb = new ProcessBuilder("python3", python_py.toString(), stock.getSymbol(), dateFormat.format(start), dateFormat.format(end));
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
