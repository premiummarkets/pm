/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.datasources.quotation;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.GetQuotation.GetQuotationResult;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.threads.ObserverMsg;


// TODO: Auto-generated Javadoc
/**
 * The Class QuotationUpdate.
 * 
 * @author Guillaume Thoreton
 */
public class QuotationUpdate {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(QuotationUpdate.class);
	
	//DEBUG
	public static long dlance;
	private Set<Observer> observers = new HashSet<Observer>();

	/**
	 * Gets the quotes from cmd.
	 * 
	 * @param listSymbols the list symbols
	 * @param shareList the provider
	 * @param quotationsProvider TODO
	 * @return the quotes from cmd
	 */
	public void getQuotesForListInCmd(List<String> listSymbols, String shareList, String quotationsProvider) {
		
		StockList stockList = new StockList();
		Providers.getInstance(shareList).retrieveStockListFromCmdLine(listSymbols, stockList, quotationsProvider);
		try {
			getQuotes(stockList);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
		
	}
	

	public void getQuotesAndNewForShareListFromWeb(String sharesListName, String marketQuotationProvider, Set<Indice> indices) throws HttpException {
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices,false);
		getQuotesAndNewForShareListFromWeb(provider, marketQuotationProvider);
		
	}

	/**
	 * @param provider
	 * @param marketQuotationsProvider
	 * @throws HttpException 
	 */
	private void getQuotesAndNewForShareListFromWeb(Providers provider, String marketQuotationsProvider) throws HttpException {
		
		StockList existingDBStocks = new StockList();
		provider.retrieveStockListFromBase(existingDBStocks);
		
		StockList bdStockList =  new StockList();
		bdStockList.addAll(existingDBStocks);
		
		StockList shareListStocks = provider.retrieveStockListFromWeb(MarketQuotationProviders.valueOfCmd(marketQuotationsProvider), existingDBStocks);
		
		try {
			getQuotes(shareListStocks);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
	}

	/**
	 * Gets the quotes from file.
	 * 
	 * @param pathToFileList the path to list
	 * @param sharesList the provider
	 * 
	 * @return the quotes from file
	 * @throws StockNotFoundException 
	 */
	public void getQuotesForListInFile(String pathToFileList, String sharesList) throws StockNotFoundException, InputMismatchException {
		
		StockList dbStockList = new StockList();
		Providers.getInstance(sharesList).retrieveStockListFromBase(dbStockList);
		StockList retreivedStockListFromFile = Providers.getInstance(sharesList).retreiveStockListFromFile(pathToFileList, dbStockList);
		getQuotes(retreivedStockListFromFile);
		
	}
	
	public List<Stock> getQuotesForListInFile(String pathToFileList, Providers sharesList) throws StockNotFoundException, InputMismatchException {
		
		StockList dbStockList = new StockList();
		sharesList.retrieveStockListFromBase(dbStockList);
		StockList retreivedStockListFromFile = sharesList.retreiveStockListFromFile(pathToFileList, dbStockList);
		getQuotes(retreivedStockListFromFile);
		
		return retreivedStockListFromFile;
		
	}
	
	public Stock getQuotesForUi(
			Providers sharesList, String isin, String symbol, String name, 
			StockCategories category, MarketQuotationProviders quoteProvider, MarketValuation market) throws InvalidAlgorithmParameterException, StockNotFoundException {

		StockList dbStockList = new StockList();
		sharesList.retrieveStockListFromBase(dbStockList);

		Stock newStock = new Stock(
				isin, symbol, name, false, category,
				new SymbolMarketQuotationProvider(quoteProvider,SymbolNameResolver.UNKNOWNEXTENSIONCLUE), market,
				"",TradingMode.CONTINUOUS,0l);
		sharesList.retrieveAndCompleteStockInfo(newStock, dbStockList);

		getQuotes(new StockList(new HashSet<Stock>(Arrays.asList(new Stock[]{newStock}))));
		
		return newStock;

	}
	
	public Collection<Stock> getQuotesFor(Collection<Stock> stocks) throws StockNotFoundException {
		StockList stockList = new StockList();
		stockList.addAll(stocks);
		getQuotes(stockList);

		return stocks;
	}
	
	public Collection<Stock> getQuotesForMonitored() {
		StockList stockList = new StockList();
		Collection<Stock> symbols = DataSource.getInstance().getShareDAO().loadMonitoredStocks();
		stockList.addAll(symbols);
		
		try {
			getQuotes(stockList);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
		
		return symbols;
	}
	
	public Collection<Stock> getQuotesForAllUserPortfoliosAndMonitored() {
		
		//load stocks for all user portfolios
		StockList userPortoflioStocks = new StockList();
		Collection<Stock> userPortoflioSymbols = DataSource.getInstance().getShareDAO().loadAllUserPortoflioStocks();
		userPortoflioStocks.addAll(userPortoflioSymbols);
		try {
			getQuotes(userPortoflioStocks);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
		
		//return monitored
		StockList monitoredStocks = new StockList();
		Collection<Stock> monitoredSymbols = DataSource.getInstance().getShareDAO().loadMonitoredStocks();
		monitoredStocks.addAll(monitoredSymbols);
		return monitoredStocks;
	}
	
	/**
	 * Gets the quotes from db.
	 * 
	 * @return the quotes from db
	 */
	public void getQuotesForCurrentListInDB() {
		
		StockList stockList = new StockList();
		Collection<Stock> symbols = DataSource.getInstance().loadStocksForCurrentShareList();
		
		stockList.addAll(symbols);
		try {
			getQuotes(stockList);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
	}
	
	public void getQuotesForSharesListInDB(String sharesListName, Set<Indice> indices) {
		
		StockList stockList = new StockList();
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices, false);
		sharesListName = sharesListName+Indice.formatSet(provider.getIndices());
		Collection<Stock> symbols = DataSource.getInstance().loadStocksList(sharesListName);
		
		stockList.addAll(symbols);
		try {
			getQuotes(stockList);
		} catch (StockNotFoundException e) {
			LOGGER.warn(e);
		}
		
	}

	/**
	 * Gets the quotes.
	 * 
	 * @param stockList the sl
	 * 
	 * @return the quotes
	 * @throws StockNotFoundException 
	 */
	public void getQuotes(StockList stockList) throws StockNotFoundException {
		
		Iterator<Stock> stlIt = stockList.iterator();
		
		dlance = System.currentTimeMillis();
		for (Observer observer : observers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, stockList.size()));
		}
		
		ExecutorService executor = Executors.newFixedThreadPool((new Integer(MainPMScmd.getPrefs().get("quotationretrieval.semaphore.nbthread", "20"))));
		List<Future<GetQuotationResult>> getQuoteRess = new ArrayList<Future<GetQuotationResult>>();
		while (stlIt.hasNext()) {
			Stock stock = stlIt.next();
			
			LOGGER.debug("Fetching quotations for Ticker : " + stock);
			GetQuotation command = new GetQuotation(EventSignalConfig.getNewDate(), stock);
			for (Observer observer : observers) {
				command.addObserver(observer);
			}

			Future<GetQuotationResult> getQuoteRes = executor.submit(command);
			getQuoteRess.add(getQuoteRes);
		}

		executor.shutdown();
		try {
			boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow,new Exception());
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow,e);
		}
		
		
		StockNotFoundException exceptions = new StockNotFoundException("Unable to find stocks");
		for (Future<GetQuotationResult> getQuotationRes : getQuoteRess) {
			try {
				GetQuotationResult getQuotationResult = getQuotationRes.get();
				if (!getQuotationResult.hasQuotations) {
					exceptions.invalidStocks.add(getQuotationResult.stock);
				}
				if (!getQuotationResult.isSuccessfulUpdate) {
					exceptions.notUpdated.put(getQuotationResult.stock, getQuotationResult.failureCause);
				}
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			} catch (ExecutionException e) {
				LOGGER.error(e,e);
			}
		}
		if (!exceptions.invalidStocks.isEmpty() || !exceptions.notUpdated.isEmpty() ) {
			throw exceptions;
		}
		
		LOGGER.debug("Download duration : " + (System.currentTimeMillis() - dlance) / (1000 * 60) + " minutes");
		
	}
	
	public class StockNotFoundException extends Exception {
		private static final long serialVersionUID = 3632464675497253714L;
		List<Stock> invalidStocks;
		Map<Stock,Exception> notUpdated;
		
		public StockNotFoundException(String message) {
			super(message);
			this.invalidStocks = new ArrayList<Stock>();
			this.notUpdated = new HashMap<Stock, Exception>();
		}
		
		@Override
		public String getMessage() {
			return super.getMessage()+"\n"+this.toString();
		}
		
		
		public String toString() {
			String ret = "";
			if (!invalidStocks.isEmpty()) {
				ret = ret + "\nNo quotations found for : ";
				for (Stock exp : invalidStocks) {
					ret = ret + exp.getFriendlyName() + "\n";
				}
			}
			if (!notUpdated.isEmpty()) {
				ret = ret + "\nCan't update : ";
				for (Stock exp : notUpdated.keySet()) {
					ret = ret + exp.getFriendlyName() + " because "+ notUpdated.get(exp).getMessage() +"\n";
				}
			}
			return ret;
		}

		public List<Stock> getInvalidStocks() {
			return invalidStocks;
		}

		public Map<Stock, Exception> getNotUpdated() {
			return notUpdated;
		}
	}

	public void addObservers(Set<Observer> observers) {
		if (observers != null) {
			this.observers.addAll(observers);
		}
	}

}
