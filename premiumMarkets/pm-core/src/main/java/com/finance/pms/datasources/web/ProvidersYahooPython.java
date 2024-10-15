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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpException;

import com.finance.pms.MainPMScmd;
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
import com.finance.pms.datasources.web.formaters.YahooPyQuotation;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.SharesList;


/**
 * The MarketListProvider is implemented here solely to handle the UNKNOWN market list (stocks in no particular lists)
 */
public abstract class ProvidersYahooPython extends Providers implements QuotationProvider, MarketListProvider {

    private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPython.class);

    protected ProvidersYahooPython(String pathToProps) {
        super();
    }

    @Override
    public void getQuotes(Stock stock, Date start, Date end) throws HttpException, SQLException {

        if (stock.getSymbol() == null) throw new RuntimeException("Error: no Symbol for " + stock.toString());
        
        //Adding on day as it yfinance is [start,end[
        Date tomorrow = DateUtils.addDays(end, +1);
       
        List<ValidatableDated> readPage = null;
        Integer maxAttempts = Integer.valueOf(MainPMScmd.getMyPrefs().get("quotationretrieval.semaphore.maxattempts", "1"));
		int cpt = maxAttempts;
        while (readPage == null && cpt > 0) {
			try {
				cpt--;
				readPage = readPythonPage(stock, start, tomorrow).stream().map(v -> (ValidatableDated) v).collect(Collectors.toList());
			} catch (IOException e1) {
				LOGGER.warn(e1);
				try {
					Thread.sleep((maxAttempts-cpt) * 1000);
				} catch (InterruptedException e) {
					LOGGER.warn(e, e);
				}
			} 
		}
        
		if (readPage == null) throw new HttpException();
        
		readPage = filterToEndDateInclusive(end, readPage); //[start,end] considering end is today
		
		TreeSet<ValidatableDated> queries = initValidatableSet();
        queries.addAll(readPage);

        LOGGER.guiInfo("Getting last quotes: Number of new quotations for " + stock.getSymbol() + ": " + queries.size());
        LOGGER.info(queries);
        try {
            ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
            tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME, TableLocker.LockMode.NOLOCK));
            DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<ValidatableDated>(queries), tablet2lock);
        } catch (SQLException e) {
            LOGGER.error("Yahoo quotations SQL error trying: " + stock + " between " + start + " and " + end, e);
            throw e;
        }
		
		QuotationUnit latestInference = null;
		Date marketClose = DateFactory.endDateFix(DateFactory.getNowEndDate(), stock.getMarket().getUTCTimeLag(), stock.getTradingMode());
		if (end.compareTo(marketClose) > 0) { //Considering end is today: Looking for latest daily
			//Need to check if end is present
			 if (readPage.size() == 0 || readPage.get(readPage.size() - 1).getDate().compareTo(end) < 0) { //end is missing
				 Date tomorrowPlusOne = DateUtils.addDays(tomorrow, +1);
				 try {
					latestInference = readPythonIntradayPage(stock, end, tomorrowPlusOne);
				} catch (IOException e) {
					LOGGER.error("Yahoo intraday error trying: " + stock + " between " + end + " and " + tomorrowPlusOne, e);
					throw new HttpException(e.toString(), e);
				}
			}
		}
        
		if (latestInference != null) {
			DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnit(latestInference);
		}

    }

    protected abstract QuotationUnit readPythonIntradayPage(Stock stock, Date quotationDate, Date tomorrowPlusOne) throws IOException;

	private List<Validatable> readPythonPage(Stock stock, Date start, Date end) throws IOException {
    	
    	DayQuoteYahooPythonFormater dsf = new DayQuoteYahooPythonFormater(null, stock, stock.getMarketValuation().getCurrency().name());
    	
		String symbol = stock.getSymbol();
		if ('^' != symbol.charAt(0) && stock.getCategory().equals(StockCategories.INDICES_OTHER)) symbol = "^" + symbol;
		
    	InputStream processInputStream = readInput(symbol, start, end, false, false);

		List<Validatable> validatables = new ArrayList<Validatable>();
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(processInputStream));) {
			String line = null;
			while ((line = in.readLine()) != null) {
				try {
					LOGGER.info("line: " + line);
					List<Validatable> lineValidatables = dsf.formatLine(line);
					
					if (lineValidatables.size() > 1) throw new Exception("Invalid DailyQuotation line: " + line);
					
					YahooPyQuotation yq;
					if (lineValidatables.size() > 0 && (yq = (YahooPyQuotation) lineValidatables.get(0)).getSplit() != 1d) {
						validatables = validatables.stream()
								.map(v -> ((YahooPyQuotation) v).reverseSplit(yq.getSplit()))
								.collect(Collectors.toList());
					}
					
					validatables.addAll(lineValidatables);
				} catch (StopParseErrorException e) {
					LOGGER.warn(e);
					LOGGER.warn("Py output: " + line);
					while ((line = in.readLine()) != null) {
						LOGGER.warn("Py output: " + line);
					}
	
				} catch (AssertionError| Exception e) {
					LOGGER.warn("Ignoring line: " + line);
				}
			}
		}
		
		LOGGER.info("validatable: " + validatables.stream().map(v -> v.toDataBase().toString()).reduce((a, e) -> a + " / " + e));
		return validatables;
		
	}

	protected abstract InputStream readInput(String symbol, Date start, Date end, Boolean isPeriod, Boolean isIntraday) throws IOException;
	

    public List<Validatable> readPage(Stock stock, MyUrl url, Date  start) throws HttpException {
    	 // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {

        //No check available for Yahoo
        if (!stock.isFieldSet("isin") || !stock.isFieldSet("symbol") || !stock.isFieldSet("name")) {
            LOGGER.warn("No completion check on symbol, isin, name is available for the Yahoo provider. Please provide the full info (symbol, isin, name) for each stock: "+stock);

        } else {
            List<Validatable> listReq = new ArrayList<Validatable>();

            if (!stockList.contains(stock)) { // not already in base

                //check for last former quotation
                Date pastLastQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false);
                LOGGER.info("New ticker: " + stock.toString() + " and will be added with last quote: " + pastLastQuotationDate);

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
        return SharesListId.UNKNOWN;
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
