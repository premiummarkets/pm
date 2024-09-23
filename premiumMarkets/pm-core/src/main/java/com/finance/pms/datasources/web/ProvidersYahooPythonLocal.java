package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.IntraDayQuotation;
import com.finance.pms.datasources.web.formaters.IntraDayQuoteFormater;
import com.finance.pms.datasources.web.formaters.StopParseErrorException;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;

public class ProvidersYahooPythonLocal extends ProvidersYahooPython {
	
    private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPythonLocal.class);
	
	private Path python_py;


	public ProvidersYahooPythonLocal(String pathToProps) {
        super(pathToProps);
		try {
			python_py = Files.createTempFile("yahooQuotes", "py");
			try (InputStream stream = ProvidersYahooPython.class.getResourceAsStream("/yahooQuotes/main.py")) {
				Files.copy(stream, python_py, StandardCopyOption.REPLACE_EXISTING);
				PosixFileAttributeView view = Files.getFileAttributeView(python_py, PosixFileAttributeView.class);
				if (view != null) {
					Set<PosixFilePermission> perms = view.readAttributes().permissions();
					if (perms.add(PosixFilePermission.OWNER_EXECUTE)) {
						view.setPermissions(perms);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
    }
	
    @Override
	public void getQuotes(Stock stock, Date start, Date end) throws HttpException, SQLException {
    	
        YahooPythonQuotationFixer yahooPythonQuotationFixer = new YahooPythonQuotationFixer(python_py, stock, end);
        yahooPythonQuotationFixer.fixQuotations();
        
		super.getQuotes(stock, start, end);
	}
    
	protected InputStream readInput(String symbol, Date start, Date end, Boolean isPeriod, Boolean isIntraday) throws IOException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pythonExec = MainPMScmd.getMyPrefs().get("quotes.pythonPath", "python3");
		String intraday = (isIntraday)?"1d":"NotDefined";
		String period = (isPeriod)?inferPeriod(start, end):"NotDefined";
		LOGGER.info(String.format("Running Yahoo py: %s %s %s %s %s %s %s", pythonExec, python_py.toString(), symbol, intraday, period, dateFormat.format(start), dateFormat.format(end)));
		ProcessBuilder pb = new ProcessBuilder(pythonExec, python_py.toString(), symbol, period, intraday, dateFormat.format(start), dateFormat.format(end));
		pb.redirectErrorStream(true);
		Process process = pb.start();
		InputStream processInputStream = process.getInputStream();
		return processInputStream;
	}

	//yfinance period parameter
	private String inferPeriod(Date start, Date end) {
		if (end.compareTo(DateFactory.getNowEndDate()) < 0) {
			return "NotDefined";
		}
		
		long diffInDays = TimeUnit.DAYS.convert(start.getTime() - end.getTime(), TimeUnit.MILLISECONDS);
		//Valid periods: 1d,5d,1mo,3mo,6mo,1y,2y,5y,10y,ytd,max
	    String period = "1d";
		if (diffInDays <= 1) {
			period = "1d";
		}
		else if (diffInDays <= 5) {
			period = "5d";
		}
		else if (diffInDays <= 30) {
			period = "1mo";
		}
		else if (diffInDays <= 90) {
			period = "3mo";
		}
		else if (diffInDays <= 180) {
			period = "6mo";
		}
		else if (diffInDays <= 365) {
			period = "1y";
		}
		else if (diffInDays <= 730) {
			period = "2y";
		}
		else if (diffInDays <= 1825) {
			period = "5y";
		}
		else if (diffInDays <= 3650) {
			period = "10y";
		}
		else {
			period = "max";
		}
		return period;
	}
    
    public void close() {
    	try {
			Files.delete(python_py);
		} catch (IOException e) {
			LOGGER.error("Error closing ProvidersYahooPython: " + e, e);
		}
    }

    /**
     * tomorrow considering quotationDate is today
     * tomorrowPlusOne as yfinance returns [quotationDate,tomorrowPlusOne[ => will return intervals from quotationDate:00:00 to tomorrow:24:00:00 - interval
     */
	@Override
	protected QuotationUnit readPythonIntradayPage(Stock stock, Date quotationDate, Date tomorrowPlusOne)  throws IOException {
		
		IntraDayQuoteFormater dsf = new IntraDayQuoteFormater(stock);
    	
		String symbol = stock.getSymbol();
		if ('^' != symbol.charAt(0) && stock.getCategory().equals(StockCategories.INDICES_OTHER)) symbol = "^" + symbol;
		
    	InputStream processInputStream = readInput(symbol, quotationDate, tomorrowPlusOne, false, true);

		List<IntraDayQuotation> intraDayQuotations = new ArrayList<IntraDayQuotation>();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(processInputStream));) {
			String line = null;
			while ((line = in.readLine()) != null) {
				try {
					LOGGER.info("line: " + line);
					IntraDayQuotation lineValidatables = dsf.formatLine(line);
					intraDayQuotations.add(lineValidatables);
				} catch (StopParseErrorException e) {
					LOGGER.warn(e);
					while ((line = in.readLine()) != null) {
						LOGGER.warn(line);
					}
					throw new IOException(
							"Stop parsing response from python for " + symbol + " between " + quotationDate + " and " + tomorrowPlusOne + ": " + 
							((StopParseErrorException) e).getMessage() + ". " +
							"Reason: " + ((StopParseErrorException) e).getReason());
	
				} catch (AssertionError| Exception e) {
					if (LOGGER.isDebugEnabled()) LOGGER.debug("Ignoring line: " + line);
				}
			}
		}
		
		if (intraDayQuotations.size() == 0) {
			throw new IOException("No intraday quotations found for " + symbol + " between " + quotationDate + " and " + tomorrowPlusOne);
		}
		Date tomorrow = DateUtils.addDays(quotationDate, 1);
		List<ValidatableDated> validIntraDayQuotations = filterToEndDateInclusive(tomorrow, intraDayQuotations); //Includes the first interval of tomorrow at midnight :00:00
		LOGGER.info("Valid intarday quotations: " + validIntraDayQuotations);
		
		//Reduction over potentially several intervals  the quotationDate day
		BigDecimal open = ((IntraDayQuotation) validIntraDayQuotations.get(0)).getOpen(); //First interval open
		BigDecimal high = validIntraDayQuotations.stream().map(e -> ((IntraDayQuotation) e).getHigh()).reduce((a, e) -> (e.compareTo(a) >= 0)?e:a).orElse(open);
		BigDecimal low = validIntraDayQuotations.stream().map(e -> ((IntraDayQuotation) e).getLow()).reduce((a, e) -> (e.compareTo(a) <= 0)?e:a).orElse(open);
		BigDecimal close = ((IntraDayQuotation) validIntraDayQuotations.get(intraDayQuotations.size() - 1)).getClose(); //Last interval close
		
		//Long volume = intraDayQuotations.stream().map(e -> e.getVolume()).reduce((a, e) -> a + e).orElse(0l); //may not be accurate?
		Long volume = 0l;
		if (intraDayQuotations.size() == 1) { //1d intraday interval
			volume = intraDayQuotations.get(0).getVolume();
		}
		
		QuotationUnit quotationUnit = new QuotationUnit(stock, stock.getMarketValuation().getCurrency(), quotationDate, open, high, low, close, volume, ORIGIN.USER, BigDecimal.ONE, null);
		LOGGER.info("Infered quotation unit from intraday: " + quotationUnit);
		return quotationUnit;
	}

}
