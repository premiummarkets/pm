/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
package com.finance.pms.datasources.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.TrendSupplementedStock;
import com.finance.pms.threads.ObserverMsg;

public abstract class ProvidersList extends Providers implements MarketListProvider {

	private class StockSupplementRunnable extends Observable implements Runnable{
		private final NavigableSet<Stock> sortedExistingStocksSet;
		private final SharesList existingSharesList;
		private final Set<Stock> newStocksRequestsSet;

		private final Stock stockFromWeb;
		private final Set<TrendSupplementedStock> newTrendsRequestsSet;

		private StockSupplementRunnable(Stock stockFromWeb, NavigableSet<Stock> sortedExistingStocksSet, SharesList existingSharesList, 
										Set<Stock> newStocksRequestsSet, Set<TrendSupplementedStock> newTrendsRequestsSet) {
			this.sortedExistingStocksSet = sortedExistingStocksSet;
			this.existingSharesList = existingSharesList;
			this.newStocksRequestsSet = newStocksRequestsSet;
			this.stockFromWeb = stockFromWeb;
			this.newTrendsRequestsSet = newTrendsRequestsSet;
		}

		public void run() {

			try {

				Validatable stockFromWebCompleted = supplement(stockFromWeb);
				
				if (stockFromWebCompleted != null) { //Enough completion was found to make a valid share

					Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations((Stock)stockFromWebCompleted);//so the stock was already in db after all?
					((Stock) stockFromWebCompleted).setLastQuote(formerQuotationDate);

					LOGGER.info("Ticker " + stockFromWebCompleted.toString() + " is new and will be added with last quote : "+ formerQuotationDate);
					newStocksRequestsSet.add((Stock)stockFromWebCompleted);

					//Add trend info
					TrendSupplementedStock trendStock = new TrendSupplementedStock((Stock) stockFromWebCompleted);
					retrieveScreeningInfoForShare(trendStock);
					newTrendsRequestsSet.add(trendStock);

					//Add new stock to existing stock list
					sortedExistingStocksSet.add((Stock)stockFromWebCompleted);
					
					//Add new stock to the shares list
					existingSharesList.addShare((Stock) stockFromWeb);
				}

			} finally {
				this.setChanged();
				this.notifyObservers();
			}				
		}
	}


	static MyLogger LOGGER = MyLogger.getLogger(ProvidersList.class);
	
	protected ProvidersList() {
		super();
	}

	@Override
	public void getQuotes(Stock ticker, Date start, Date end) throws SQLException {
		throw new UnsupportedOperationException("Please use an other provider then a share list holder for that.");
	}

	@Override
	public abstract String getStockRefName(Stock stock);

	@Override
	public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {
		
		//No check available by default
		if (!stock.isFieldSet("isin") || !stock.isFieldSet("symbol") || !stock.isFieldSet("name")) {
			stock.resetStock(supplement(stock));
		} else {
			List<Validatable> listReq = new ArrayList<Validatable>();
			if (!stockList.contains(stock)) { // not already in base	
				
				//check for last former quotation
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock);
				stock.setLastQuote(formerQuotationDate);
				
				LOGGER.info("New ticker : "+stock.toString()+" and will be added with last quote : "+ formerQuotationDate);
				
				listReq.add(stock);
				stockList.add(stock);
				
			} else { //already in base : update name
				stockList.get(stockList.indexOf(stock)).setName(stock.getName());
			}
			try {
				DataSource.getInstance().executeBlock(listReq, DataSource.SHARES.getINSERT());
			} catch (SQLException e) {
				LOGGER.warn("Warning, this ticker is already in database. Only quotations will be updated. Sql :"+ e.getMessage() + " cause : " + e.getCause());
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}


	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		throw new UnsupportedOperationException("Please use an other provider then a share list holder for that.");
	}

	@Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders,StockList existingStocks) {
		LOGGER.info("From Web - "+this.getClass().getSimpleName()+" : ");
		
		//Existing stock set
		final NavigableSet<Stock> sortedExistingStocksSet = sortedExistingStockSet(existingStocks);
		
		//Share list
		final SharesList existingSharesList = loadSharesListForThisListProvider();

		//Fetch		
		Set<Stock> listAsFromWeb  = fetchStockList(marketQuotationsProviders);
		int nbStockAsFromWeb = listAsFromWeb.size();
		LOGGER.guiInfo("Number of stocks retreived from web for "+ this.getSharesListIdEnum()+" on the " + new Date() + " : " + nbStockAsFromWeb);

		
		//Completing and adding new from web
		final Set<Stock> newStocksRequestsSet = new ConcurrentSkipListSet<Stock>();
		final Set<TrendSupplementedStock> newTrendsRequestsSet = new ConcurrentSkipListSet<TrendSupplementedStock>();
		int cpt = 0;
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("marketlistretrieval.semaphore.nbthread","20")));
		for (Observer observer : observers) {
			//observer.update(null, "init "+listAsFromWeb.size());
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, listAsFromWeb.size()));
		}
		
		for (final Stock stockFromWeb : listAsFromWeb) {
			
			//Completion of new stock, only if the stock requires supplement of info
			if (!sortedExistingStocksSet.contains(stockFromWeb) && supplementRequiered(stockFromWeb)) {//this is a new stock

				StockSupplementRunnable stockSupRunnable = new StockSupplementRunnable(stockFromWeb, sortedExistingStocksSet, existingSharesList, newStocksRequestsSet, newTrendsRequestsSet);
				for (Observer observer : observers) {
					stockSupRunnable.addObserver(observer);
				}
				
				Thread thread = new Thread(stockSupRunnable);
				executor.execute(thread);

				LOGGER.debug("Done : "+((++cpt)*100/nbStockAsFromWeb)+" % ");
				
			} else { //this is an existing stock

				//Add existing stocks to shares list if not already there
				PortfolioShare shareForStock = existingSharesList.getShareForLienientSymbol(stockFromWeb.getSymbol());
				if (shareForStock == null) {
					LOGGER.info("This ticker is in the data base but needs to be added to the market list ("+existingSharesList.getName()+") : " + stockFromWeb);
					NavigableSet<Stock> existingStockMatchingFromWeb = sortedExistingStocksSet.subSet(stockFromWeb, true, stockFromWeb, true);
					existingSharesList.addShare(existingStockMatchingFromWeb.first());
				}
			}
			
		} //End for loop on share list

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

		//Addition of new stocks in DB
		LOGGER.guiInfo("Number of new tickers to be added : " + newStocksRequestsSet.size());
		LOGGER.info("New Tickers to be added : " + newStocksRequestsSet);
		try {
			if (newStocksRequestsSet.size() > 0) shareDAO.saveOrUpdateShare(newStocksRequestsSet);
			if (newTrendsRequestsSet.size() > 0) shareDAO.saveOrUpdateShareTrendInfo(newTrendsRequestsSet);
		} catch (Exception e) {
			LOGGER.error("",e);
		}	
		
		//Share list
		portfolioDAO.saveOrUpdatePortfolio(existingSharesList);
		
		return new StockList(sortedExistingStocksSet);
	}

	/**
	 * @param existingStocks
	 * @return
	 */
	private NavigableSet<Stock> sortedExistingStockSet(StockList existingStocks) {
		final NavigableSet<Stock> existingStocksSet = new ConcurrentSkipListSet<Stock>(getNewStockComparator());
		existingStocksSet.addAll(existingStocks);
		return existingStocksSet;
	}

	/**
	 * @param stockWeb
	 * @return
	 */
	private boolean supplementRequiered(final Stock stockWeb) {
		return 
			!stockWeb.isFieldSet("isin")|| !stockWeb.isFieldSet("symbol") || !stockWeb.isFieldSet("name") || !stockWeb.isFieldSet("sectorHint");
	}


	protected abstract Stock supplement(Stock stock);
	
	protected abstract Comparator<Stock> getNewStockComparator();

	protected abstract LineFormater getFormater(String url,Market market, MarketQuotationProviders marketQuotationsProviders);

	
	protected abstract Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders);

	public abstract void retrieveScreeningInfoForShare(TrendSupplementedStock trendSupStock);
	
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		
		//Adding new from web
		int cpt = 0;
		int nbShares = shareList.size();
		final Set<TrendSupplementedStock> listTrendIns = new ConcurrentSkipListSet<TrendSupplementedStock>();
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("screeninginforetrieval.semaphore.nbthread","20")));
		for (final Stock stock : shareList) {

			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						//Add trend info
						TrendSupplementedStock trendStock = new TrendSupplementedStock(stock);
						LOGGER.guiInfo("Updating screening info : updating opinions for "+stock.getSymbol());
						retrieveScreeningInfoForShare(trendStock);
						listTrendIns.add(trendStock);
						
					} finally  {
					}
				}				
			});

			executor.execute(t);

			LOGGER.debug("Done : "+((++cpt)*100/nbShares)+" % ");
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
		
		shareDAO.saveOrUpdateShareTrendInfo(listTrendIns);

	}
	
}
