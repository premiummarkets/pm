/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.threads.ObserverMsg;

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

	public void getQuotesForListInCmd(List<String> listSymbols, String shareList, String quotationsProvider) {
		
		StockList stockList = new StockList();
		Providers.getInstance(shareList).retrieveStockListFromCmdLine(listSymbols, stockList, quotationsProvider);
		try {
			getQuotes(stockList);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
		
	}
	
	public void getQuotesAndNewForShareListFromWeb(String sharesListName, String marketQuotationProvider, Set<Indice> indices) throws HttpException {
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices, true);
		getQuotesAndNewForShareListFromWeb(provider, marketQuotationProvider);
		
	}

	private void getQuotesAndNewForShareListFromWeb(Providers provider, String marketQuotationsProvider) throws HttpException {
		
		StockList existingDBStocks = new StockList();
		provider.retrieveStockListFromBase(existingDBStocks);
		
		StockList bdStockList =  new StockList();
		bdStockList.addAll(existingDBStocks);
		
		StockList shareListStocks = provider.retrieveStockListFromWeb(MarketQuotationProviders.valueOfCmd(marketQuotationsProvider), existingDBStocks);
		
		try {
			getQuotes(shareListStocks);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
	}

	public void getQuotesForListInFile(String pathToFileList, String sharesList) {
		
		StockList dbStockList = new StockList();
		Providers.getInstance(sharesList).retrieveStockListFromBase(dbStockList);
		StockList retreivedStockListFromFile = Providers.getInstance(sharesList).retreiveStockListFromFile(pathToFileList, dbStockList);
		
		try {
			getQuotes(retreivedStockListFromFile);
		} catch (QuotationUpdateException e) {
			retreivedStockListFromFile.removeAll(e.getStockNotFound().keySet());
			LOGGER.warn("Could not find the following : "+e.getNoNewQuotations());
		}
		
	}
	
	public List<Stock> getQuotesForListInFile(String pathToFileList, Providers sharesList) {
		
		StockList dbStockList = new StockList();
		sharesList.retrieveStockListFromBase(dbStockList);
		StockList retreivedStockListFromFile = sharesList.retreiveStockListFromFile(pathToFileList, dbStockList);
		
		try {
			getQuotes(retreivedStockListFromFile);
		} catch (QuotationUpdateException e) {
			retreivedStockListFromFile.removeAll(e.getStockNotFound().keySet());
		}
		
		return retreivedStockListFromFile;
		
	}
	
	public Stock getQuotesForUiForm(Providers sharesList, Stock newStock) throws InvalidAlgorithmParameterException, QuotationUpdateException {
	
		StockList dbStockList = new StockList();
		sharesList.retrieveStockListFromBase(dbStockList);
		sharesList.retrieveAndCompleteStockInfo(newStock, dbStockList);
		
		try {
			getQuotes(new StockList(new HashSet<Stock>(Arrays.asList(new Stock[]{newStock}))), true);
		} catch (QuotationUpdateException e) {
			throw e;
		}

		return newStock;

	}
	
	public Collection<Stock> getQuotesFor(Collection<Stock> stocks) throws QuotationUpdateException {
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
		} catch (QuotationUpdateException e) {
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
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
		
		//return monitored
		StockList monitoredStocks = new StockList();
		Collection<Stock> monitoredSymbols = DataSource.getInstance().getShareDAO().loadMonitoredStocks();
		monitoredStocks.addAll(monitoredSymbols);
		return monitoredStocks;
	}

	public void getQuotesForCurrentListInDB() {
		
		StockList stockList = new StockList();
		Collection<Stock> symbols = DataSource.getInstance().loadStocksForCurrentShareList();
		
		stockList.addAll(symbols);
		try {
			getQuotes(stockList);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
	}
	
	public void getQuotesForSharesListInDB(String sharesListName, Set<Indice> indices) {
		
		StockList stockList = new StockList();
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices, true);
		sharesListName = sharesListName+Indice.formatSet(provider.getIndices());
		Collection<Stock> symbols = DataSource.getInstance().loadStocksList(sharesListName);
		
		stockList.addAll(symbols);
		try {
			getQuotes(stockList);
		} catch (QuotationUpdateException e) {
			LOGGER.warn(e);
		}
		
	}

	public void getQuotes(StockList stockList) throws QuotationUpdateException {
		this.getQuotes(stockList, false);
	}

	public void getQuotes(StockList stockList, Boolean reset) throws QuotationUpdateException {
		
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
			GetQuotation command = new GetQuotation(EventSignalConfig.getNewDate(), stock, reset);
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
				LOGGER.error(shutdownNow, new Exception());
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow, e);
		}
		
		QuotationUpdateException exceptions = new QuotationUpdateException("Unable to update quotations");
		for (Future<GetQuotationResult> getQuotationRes : getQuoteRess) {
			try {
				GetQuotationResult getQuotationResult = getQuotationRes.get();
				if (!getQuotationResult.hasNewQuotations) {
					exceptions.noNewQuotations.add(getQuotationResult.stock);
				}
				if (getQuotationResult.isSuccessfulUpdate != null && !getQuotationResult.isSuccessfulUpdate) {
					exceptions.updateFailed.put(getQuotationResult.stock, getQuotationResult.failureCause);
				}
				if (!getQuotationResult.hasPreviousQuotations) {
					exceptions.stockNotFound.put(getQuotationResult.stock, getQuotationResult.failureCause);
				}
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			} catch (ExecutionException e) {
				LOGGER.error(e,e);
			}
		}

		if (!exceptions.stockNotFound.isEmpty()) {
			throw exceptions;
		}
		
		LOGGER.debug("Download duration : " + (System.currentTimeMillis() - dlance) / (1000 * 60) + " minutes");
		
	}
	
	public class QuotationUpdateException extends Exception {
		
		private static final long serialVersionUID = 3632464675497253714L;
		
		List<Stock> noNewQuotations;
		Map<Stock,Exception> updateFailed;
		Map<Stock,Exception> stockNotFound;
		
		public QuotationUpdateException(String message) {
			super(message);
			this.noNewQuotations = new ArrayList<Stock>();
			this.updateFailed = new HashMap<Stock, Exception>();
			this.stockNotFound = new HashMap<Stock, Exception>();
		}
		
		@Override
		public String getMessage() {
			return super.getMessage()+"\n"+this.toString();
		}
		
		
		public String toString() {
			String ret = "";
			if (!noNewQuotations.isEmpty()) {
				ret = ret + "\nNo new quotations found for : ";
				for (Stock stock : noNewQuotations) {
					ret = ret + stock.getFriendlyName() + "\n";
				}
			}
			if (!updateFailed.isEmpty()) {
				ret = ret + "\nCan't update : ";
				for (Stock stock : updateFailed.keySet()) {
					Exception exception = updateFailed.get(stock);
					ret = ret + stock.getFriendlyName() + ((exception != null)?((exception.getMessage() != null)?" because \n\t"+ exception.getMessage():" because \n]t"+ exception.toString()):"") +"\n";
				}
			}
			if (!stockNotFound.isEmpty()) {
				ret = ret + "\nNo historical quotations available for : ";
				for (Stock stock : stockNotFound.keySet()) {
					Exception exception = stockNotFound.get(stock);
					ret = ret + stock.getFriendlyName() + ((exception != null)?((exception.getMessage() != null)?" because \n\t"+exception.getMessage():" because \n\t"+exception.toString()):"") +"\n";
				}
			}
			return ret;
		}

		public List<Stock> getNoNewQuotations() {
			return noNewQuotations;
		}

		public Map<Stock, Exception> getUpdateFailed() {
			return updateFailed;
		}

		public Map<Stock, Exception> getStockNotFound() {
			return stockNotFound;
		}
	}

	public void addObservers(Set<Observer> observers) {
		if (observers != null) {
			this.observers.addAll(observers);
		}
	}

}
