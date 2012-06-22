/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ObserverMsg;


// TODO: Auto-generated Javadoc
/**
 * The Class QuotationUpdate.
 * 
 * @author Guillaume Thoreton
 */
public class QuotationUpdate {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(QuotationUpdate.class);
	//DEBUG
	/** The dlance. */
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
		getQuotes(stockList);
		
	}
	

	public void getQuotesAndNewForShareListFromWeb(String sharesListName, String marketQuotationProvider, Set<Indice> indices) {
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices,false);
		getQuotesAndNewForShareListFromWeb(provider, marketQuotationProvider);
		
	}

	/**
	 * @param provider
	 * @param marketQuotationsProvider
	 */
	private void getQuotesAndNewForShareListFromWeb(Providers provider, String marketQuotationsProvider) {
		
		StockList stockList = new StockList();
		provider.retrieveStockListFromBase(stockList);
		
		StockList bdStockList =  new StockList();
		bdStockList.addAll(stockList);
		
		provider.retrieveStockListFromWeb(MarketQuotationProviders.valueOfCmd(marketQuotationsProvider), stockList);
		
		stockList.removeAll(bdStockList);
		getQuotes(stockList);
	}

	/**
	 * Gets the quotes from file.
	 * 
	 * @param pathToFileList the path to list
	 * @param sharesList the provider
	 * 
	 * @return the quotes from file
	 */
	public void getQuotesForListInFile(String pathToFileList, String sharesList) {
		
		StockList dbStockList = new StockList();
		Providers.getInstance(sharesList).retrieveStockListFromBase(dbStockList);
		StockList retreivedStockListFromFile = Providers.getInstance(sharesList).retreiveStockListFromFile(pathToFileList, dbStockList);
		getQuotes(retreivedStockListFromFile);
		
	}
	
	public Collection<Stock> getQuotesFor(Collection<Stock> stocks) {
		StockList stockList = new StockList();
		stockList.addAll(stocks);
		getQuotes(stockList);
		
		return stocks;
	}
	
	public Collection<Stock> getQuotesForMonitored() {
		StockList stockList = new StockList();
		Collection<Stock> symbols = DataSource.getInstance().getShareDAO().loadMonitoredShares();
		stockList.addAll(symbols);
		getQuotes(stockList);
		
		return symbols;
	}
	
	public Collection<Stock> getQuotesForAllUserPortfoliosAndMonitored() {
		
		//load stocks for all user portfolios
		StockList userPortoflioStocks = new StockList();
		Collection<Stock> userPortoflioSymbols = DataSource.getInstance().getShareDAO().loadAllUserPortoflioShares();
		userPortoflioStocks.addAll(userPortoflioSymbols);
		getQuotes(userPortoflioStocks);
		
		//return monitored
		StockList monitoredStocks = new StockList();
		Collection<Stock> monitoredSymbols = DataSource.getInstance().getShareDAO().loadMonitoredShares();
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
		getQuotes(stockList);
	}
	
	public void getQuotesForSharesListInDB(String sharesListName, Set<Indice> indices) {
		
		StockList stockList = new StockList();
		
		Providers provider =  Providers.getInstance(sharesListName);
		provider.addIndices(indices, false);
		sharesListName = sharesListName+Indice.formatName(provider.getIndices());
		Collection<Stock> symbols = DataSource.getInstance().loadStocksList(sharesListName);
		
		stockList.addAll(symbols);
		getQuotes(stockList);
		
	}

	/**
	 * Gets the quotes.
	 * 
	 * @param stockList the sl
	 * 
	 * @return the quotes
	 */
	public void getQuotes(StockList stockList) {
		
		Iterator<Stock> stlIt = stockList.iterator();
		
		dlance = System.currentTimeMillis();
		for (Observer observer : observers) {
			//observer.update(null, "init " + stockList.size());
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, stockList.size()));
		}
		
		ExecutorService executor = Executors.newFixedThreadPool((new Integer(MainPMScmd.getPrefs().get("quotationretrieval.semaphore.nbthread", "20"))));
		while (stlIt.hasNext()) {
			Stock stock = stlIt.next();
			
			LOGGER.debug("Fetching quotations for Ticker : " + stock);
			GetQuotation command = new GetQuotation(QuotationsFactories.getFactory().getValidQuotationDateBefore(EventSignalConfig.getNewDate()), stock);
			for (Observer observer : observers) {
				command.addObserver(observer);
			}
			
			executor.execute(command);
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
		
		LOGGER.debug("Download duration : " + (System.currentTimeMillis() - dlance) / (1000 * 60) + " minutes");
		
	}

	/**
	 * Clean quotes.
	 * 
	 * @param pathToList the path to list
	 * @param provider the provider
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	public void cleanQuotes(String pathToList, String provider) {
		StockList stockList = new StockList();
		//init des stocks base
		Providers.getInstance(provider).retrieveStockListFromBase(stockList);
		//init stock fichier (insert les nouvelles en base)
		Providers.getInstance(provider).retreiveStockListFromFile(pathToList,stockList);
		//cleanObsoleteQuotes();
	}


	public void addObservers(Set<Observer> observers) {
		if (observers != null) {
			this.observers.addAll(observers);
		}
	}

}
