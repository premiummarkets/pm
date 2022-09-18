/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.http.HttpException;
import org.python.util.PythonInterpreter;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DayQuoteYahooPythonFormater;
import com.finance.pms.datasources.web.formaters.StopParseErrorException;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.SharesList;


/**
 * The MarketListProvider is implemented here solely to handle the UNKNOWN market list (stocks in no particular lists)
 */
public class ProvidersYahooPython extends Providers implements QuotationProvider, MarketListProvider {

    private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPython.class);
    
    private Path python_py;

    protected ProvidersYahooPython() {
        super();
    }

    public ProvidersYahooPython(String pathToProps) {
        super();
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
    
    public void close() {
    	try {
			Files.delete(python_py);
		} catch (IOException e) {
			System.out.println("Error closing ProvidersYahooPython : " + e);
			e.printStackTrace();
		}
    }

    @Override
    public void getQuotes(Stock stock, Date start, Date end) throws HttpException, SQLException {

        if (stock.getSymbol() == null) throw new RuntimeException("Error : no Symbol for " + stock.toString());

        TreeSet<Validatable> queries = initValidatableSet();
        try {
	        @SuppressWarnings("unchecked")
			List<Validatable> readPage = filterToEndDate(end, (Collection<? extends ValidatableDated>) readPythonPage(stock, start, end));
	        if (readPage == null) throw new HttpException();
	        queries.addAll(readPage);
        } catch (IOException e1) {
        	LOGGER.error(e1);
            return;
        }

        LOGGER.guiInfo("Getting last quotes: Number of new quotations for " + stock.getSymbol() + ": " + queries.size());
        LOGGER.info(queries);

        try {
            ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
            tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
            DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
        } catch (SQLException e) {
            LOGGER.error("Yahoo quotations SQL error trying: " + stock + " between " + start + " and " + end, e);
            throw e;
        }

    }

    private List<Validatable> readPythonPage(Stock stock, Date start, Date end) throws IOException {
    	
    	Calendar startDayMidNight = Calendar.getInstance();
		startDayMidNight.setTime(end);
//		startDayMidNight.add(Calendar.DAY_OF_YEAR, 1);
//		Date endPlusOne = startDayMidNight.getTime();
    	
    	DayQuoteYahooPythonFormater dsf = new DayQuoteYahooPythonFormater(null, stock, stock.getMarketValuation().getCurrency().name());
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
		String symbol = stock.getSymbol();
		if ('^' != symbol.charAt(0) && stock.getCategory().equals(StockCategories.INDICES_OTHER)) symbol = "^"+symbol;
		
		ProcessBuilder pb = new ProcessBuilder("python3", python_py.toString(), symbol, dateFormat.format(start), dateFormat.format(end));
		Process p = pb.start();
		 
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		List<Validatable> validatables = new ArrayList<Validatable>();
		while ((line = in.readLine()) != null) {
			try {
				LOGGER.info("line: " + line);
				validatables.addAll(dsf.formatLine(line));
			} catch (StopParseErrorException e) {
				LOGGER.warn(e);
				throw new IOException(
						"Stop parsing response from python for " + symbol + " : " + ((StopParseErrorException) e).getMessage() + "\n" +
								"Reason : " + ((StopParseErrorException) e).getReason());

			} catch (AssertionError| Exception e) {
				LOGGER.debug("Ignoring line :" + line);
			}
		}
		
		LOGGER.info("validatable: " + validatables.stream().map(v -> v.toDataBase()));
		return validatables;
	}

	/**
     * FIXME yfinance import error
     * FIXME PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);
     * @param stock
     * @param start
     * @param end
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws HttpException
     */
    @SuppressWarnings("unused")     //Test method
	private List<Validatable> readPythonPageJython(Stock stock, Date start, Date end) throws InvalidAlgorithmParameterException, HttpException {

        try (PythonInterpreter python = new PythonInterpreter()) {
        	
        	String pythonScript = 
        	"import yfinance as yf\n" +
        	"\n" +
        	"def download_quotes():\n" +
        	    "	msft = yf.Ticker(\"MSFT\")\n" +
        	    "	\n" +
        	    "	# get stock info\n" +
        	    "	print(msft.info)\n" +
        	    "	\n" +
        	    "	# get historical market data\n" +
        	    "	hist = msft.history(period=\"5d\")\n" +
        	    "	\n" +
        	    "	print(hist.to_string())";

       
        	python.exec(pythonScript);

        }
        
        return new ArrayList<>();
    }

    public List<Validatable> readPage(Stock stock, MyUrl url, Date  start) throws HttpException {
    	 // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {

        //No check available for Yahoo
        if (!stock.isFieldSet("isin") || !stock.isFieldSet("symbol") || !stock.isFieldSet("name")) {
            LOGGER.warn("No completion check on symbol, isin, name is available for the Yahoo provider. Please provide the full info (symbol, isin, name) for each stock : "+stock);

        } else {
            List<Validatable> listReq = new ArrayList<Validatable>();

            if (!stockList.contains(stock)) { // not already in base

                //check for last former quotation
                Date pastLastQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false);
                stock.setLastQuote(pastLastQuotationDate);

                LOGGER.info("New ticker : "+stock.toString()+" and will be added with last quote : "+ pastLastQuotationDate);

                listReq.add(stock);
                stockList.add(stock);

            } else { // already in base : update name
                stockList.get(stockList.indexOf(stock)).setName(stock.getName());
            }

            try {
                DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReq);
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }
    }

    @Override
    public String getStockRefName(Stock ticker){
        return ticker.getSymbol();
    }

    @Override
    public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stocksInDB) throws HttpException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStockListFromWeb(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
        // TODO Auto-generated method stub

    }

    @Override
    public MarketQuotationProviders defaultMarketQuotationProviders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addIndices(SortedSet<Indice> indices, Boolean replace) {
        // TODO Auto-generated method stub

    }

    @Override
    public SortedSet<Indice> getIndices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SharesListId getSharesListIdEnum() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void retrieveStockListFromBase(StockList dbStockList) {
        // TODO Auto-generated method stub

    }

    @Override
    public Stock supplement(Stock stock) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SharesList loadSharesListForThisListProvider() {
        SharesList shareList = PortfolioMgr.getInstance().getPortfolioDAO().loadShareList(SharesListId.UNKNOWN.name());
        if (shareList == null) shareList = new SharesList(SharesListId.UNKNOWN.name());
        return shareList;
    }

    @Override
    public StockList retreiveStockListFromFile(String pathToFileList, StockList dbStockList) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void retrieveScreeningInfo(Collection<Stock> shareListInDB) {
        // TODO Auto-generated method stub

    }

	@Override
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
