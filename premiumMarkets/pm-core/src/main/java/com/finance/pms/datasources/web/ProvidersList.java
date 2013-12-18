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
package com.finance.pms.datasources.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.ShareListMgr;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.ScreeningSupplementedStock;
import com.finance.pms.threads.ObserverMsg;

public abstract class ProvidersList extends Providers implements MarketListProvider {

	private class StockSupplementRunnable extends Observable implements Callable<ScreeningSupplementedStock>{

		private final Stock stockFromWeb;
		private boolean trendSuppNeeded;

		private StockSupplementRunnable(Stock stockFromWeb, boolean trendSuppNeeded) {
		
			this.stockFromWeb = stockFromWeb;
			this.trendSuppNeeded = trendSuppNeeded;
		}

		@Override
		public ScreeningSupplementedStock call() {

			try {

				Validatable stockFromWebCompleted = supplement(stockFromWeb);
				
				if (stockFromWebCompleted != null) { //Enough completion was found to make a valid share

					LOGGER.info("Supplemented Ticker " + stockFromWebCompleted.toString());
					
					//Add trend info
					ScreeningSupplementedStock trendStock = new ScreeningSupplementedStock((Stock) stockFromWebCompleted);
					if (trendSuppNeeded) retrieveScreeningInfoForShare(trendStock);
					
					return trendStock;
				}

			} finally {
				this.setChanged();
				this.notifyObservers();
			}
			
			return null;
		}
	}


	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersList.class);
	
//	@Autowired()
//	@Qualifier("shareListMgr")
//	ShareListMgr shareListMgr;
	
	protected ProvidersList() {
		super();
	}

	@Override
	public void getQuotes(Stock ticker, Date start, Date end) throws SQLException {
		throw new UnsupportedOperationException("Please use another provider then a share list holder for that.");
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
				
				DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReq);
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}


	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		throw new UnsupportedOperationException("Please use another provider then a share list holder for that.");
	}

	@Override
	//@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class, value="hibernateTx") => lock time out perf_supp V portfolio ??? + proxy interface error
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList dbStocks) throws HttpException {
		
		String shareListDescrTxt = this.getSharesListIdEnum()+" with indices "+this.getIndices();
		LOGGER.warn("From Web - "+this.getClass().getSimpleName()+" ( "+shareListDescrTxt+") : ", true);
		
		//Share list
		SharesList existingSharesList = loadSharesListForThisListProvider();

		//Fetch		
		Set<Stock> listAsFromWeb  = fetchStockList(marketQuotationsProviders);
		
		LOGGER.guiInfo("Number of stocks retrieved from web for " + shareListDescrTxt + " on the " + EventSignalConfig.getNewDate() + " : " + listAsFromWeb.size());
		LOGGER.guiInfo("Number of stocks in the list " + shareListDescrTxt + " on the " + EventSignalConfig.getNewDate() + " : " + existingSharesList.getListShares().size());
		LOGGER.guiInfo("All stocks in the database - updating " + shareListDescrTxt + " - on the " + EventSignalConfig.getNewDate() + " : " + dbStocks.size());
		LOGGER.warn(
				"Before update of : "+ shareListDescrTxt +", initial from web : " + listAsFromWeb.size() +", initial in db "+ dbStocks.size() + 
				", initial in "+shareListDescrTxt+" share list : " +existingSharesList.getListShares().size(), true);
		
		//Completing and adding new from web
		Set<ScreeningSupplementedStock> supplementedStockFromWeb = new ConcurrentSkipListSet<ScreeningSupplementedStock>();

		Boolean trendSuppNeeded = new Boolean(MainPMScmd.getPrefs().get("marketlistretrieval.trendSuppNeeded","false"));
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("marketlistretrieval.semaphore.nbthread","20")));
		for (Observer observer : observers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, listAsFromWeb.size()));
		}
		
		
		List<Future<ScreeningSupplementedStock>> completedTrendSuppStocksFutures = new ArrayList<Future<ScreeningSupplementedStock>>();	
		List<Stock> inDBNewInList = new ArrayList<Stock>();
		for (final Stock stockFromWeb : listAsFromWeb) {
			
			Stock foundStock = null;
			
			//Look in share list
			PortfolioShare shareForLienientRefs = existingSharesList.getShareForLienientRefs(stockFromWeb.getSymbol(), stockFromWeb.getIsin());
			
			//Look in exiting db
			if (shareForLienientRefs == null) {
				foundStock = dbStocks.findLenientRefs(stockFromWeb.getSymbol(), stockFromWeb.getIsin());
				
				//This is a new stock
				if (foundStock == null) {
					
					//Completion of new stock, only if the stock requires supplement of info or is not complete
					if (supplementRequiered(stockFromWeb) || trendSuppNeeded) {
						LOGGER.info("Ticker from web not in database needs supplement and to be added to the market list ("+shareListDescrTxt+") : " + stockFromWeb);
						StockSupplementRunnable stockSupRunnable = new StockSupplementRunnable(stockFromWeb, trendSuppNeeded);
						for (Observer observer : observers) {
							stockSupRunnable.addObserver(observer);
						}
						completedTrendSuppStocksFutures.add(executor.submit(stockSupRunnable));
					
					}  else {//We add it as is
						LOGGER.info("Valid ticker from web not in database needs to be added to the market list ("+shareListDescrTxt+") : " + stockFromWeb);
						supplementedStockFromWeb.add(new ScreeningSupplementedStock(stockFromWeb));//We will need to add it in db as well
						existingSharesList.addShare(stockFromWeb);
						//inDBNewInList.add(foundStock);
						
					}
					
				} else {//The stock is in the db We add to the share list
					LOGGER.info("Valid ticker is in the database but needs to be added to the market list ("+shareListDescrTxt+") : " + foundStock);
					existingSharesList.addShare(foundStock);
					inDBNewInList.add(foundStock);
				} 
				
			}
			
		} //End for loop on share list
		
		executor.shutdown();
		try {
			boolean awaitTermination = executor.awaitTermination(1, TimeUnit.HOURS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow,new Exception());
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow,e);
		}
		
		for (Future<ScreeningSupplementedStock> future : completedTrendSuppStocksFutures) {
			try {
				ScreeningSupplementedStock trendSupplementedStock = future.get();
				supplementedStockFromWeb.add(trendSupplementedStock);
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
		}

		//Addition of new stocks in DB
		LOGGER.guiInfo("Number of supplemented tickers from web : " + supplementedStockFromWeb.size());
			
		try {
			if (supplementedStockFromWeb.size() > 0) {
				Set<Stock> newStockRequestsSet = new ConcurrentSkipListSet<Stock>();
				
				for (ScreeningSupplementedStock trendSuppStock : supplementedStockFromWeb) {
					
					Stock stock = trendSuppStock.getStock();
					
					//Update last quotation
					Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock);//Is there any quotation from a removed share?
					if (formerQuotationDate.after(DateFactory.dateAtZero())) {
						LOGGER.warn("Adding Supplemented stock "+stock+" in data base but it has already quotations until "+formerQuotationDate);
					}
					stock.setLastQuote(formerQuotationDate);
					
					//Adding stock for update in db
					newStockRequestsSet.add(stock);
					
					//Add stocks to shares list (double check that no already ther after completion)
					PortfolioShare shareForStock = existingSharesList.getShareForLienientSymbol(stock.getSymbol());
					if (shareForStock == null) {
						LOGGER.info("Supplemented ticker is in the data base but needs to be added to the market list ("+shareListDescrTxt+") : " + stock);
						existingSharesList.addShare(stock);
						
					}
					
				}
				LOGGER.guiInfo("Number of new tickers added to the list : " + (inDBNewInList.size() + newStockRequestsSet.size()));
			
				shareDAO.saveOrUpdateStocks(newStockRequestsSet);
				shareDAO.saveOrUpdateStockTrendInfo(supplementedStockFromWeb);
			}
		} catch (Exception e) {
			LOGGER.error("Can't update stock info for "+existingSharesList.getName()+". new supplemented Stocks : "+supplementedStockFromWeb,e);
		}	
		
		//Remove obsolete stocks from list
		Set<PortfolioShare> tobeRemovedFromList = new HashSet<PortfolioShare>();
		if (!listAsFromWeb.isEmpty()) {
			for (PortfolioShare existingInList : existingSharesList.getListShares().values()) {
				Boolean found=false;
				for (Stock stockFromWeb : listAsFromWeb) {
					//this is the lenient match
					if (stockFromWeb.getIsin().equals(existingInList.getIsin()) || stockFromWeb.getSymbol().equals(existingInList.getSymbol()) || stockFromWeb.getSymbol().equals(existingInList.getIsin()) || stockFromWeb.getIsin().equals(existingInList.getSymbol())) {
						found=true;
						break;
					}
				}
				if (!found) {
					LOGGER.info(existingInList.getStock() + " is not in " + shareListDescrTxt + " and will be removed from the list");
					tobeRemovedFromList.add(existingInList);
				}
			}
			
			LOGGER.guiInfo("Number of old tickers removed from the list : " + tobeRemovedFromList.size());
			if (tobeRemovedFromList.size() > 0) {
				if (new Double(tobeRemovedFromList.size()) <= (ShareListMgr.PERCENT_THRESHOLD/100d)*(new Double(existingSharesList.getListShares().size()))) {
					existingSharesList.removeShares(tobeRemovedFromList);
				} else {
					LOGGER.error(
						//"The number of tickers retrieved for "+listProviderDescr+" is "+listFromWebSize +
						"The number of old tickers to be removed from "+existingSharesList.getName()+" : " + tobeRemovedFromList.size()+ " is more than "+ShareListMgr.PERCENT_THRESHOLD+"% of the existing list size "+existingSharesList.getListShares().size()+".\n" +
						"Maybe the list is faulty and no remove will be done. Please check the log for urls used for that list.\n" +
						"Tickers to be removed are : "+tobeRemovedFromList);
				}
			}
			
		}
		
		
		//Share list
		try {
			portfolioDAO.saveOrUpdatePortfolio(existingSharesList);
		} catch (Exception e) {
			LOGGER.error("Can't save share list : "+existingSharesList.getName(), e);
		}
		LOGGER.guiInfo("Number of stocks in the list after update for " + shareListDescrTxt + " on the " + EventSignalConfig.getNewDate() + " : " + existingSharesList.getListShares().size());
		LOGGER.warn(
				"After update of : "+ shareListDescrTxt +", initial from web : " + listAsFromWeb.size() +", initial in db "+ dbStocks.size() + 
				", in "+shareListDescrTxt+" list : " +existingSharesList.getListShares().size() +
				", supplemented (was not in db or was corrupted) "+supplementedStockFromWeb.size() + ", already in bd added to share list "+inDBNewInList.size() +
				", asked for removal "+ tobeRemovedFromList.size(), true);
		
		
		return new StockList(existingSharesList.toStocksSet());
	}


	/**
	 * @param stockWeb
	 * @return
	 */
	private boolean supplementRequiered(final Stock stockWeb) {
		return 
			!stockWeb.isFieldSet("isin")|| !stockWeb.isFieldSet("symbol") || !stockWeb.isFieldSet("name") || !stockWeb.isFieldSet("sectorHint") || stockWeb.getSymbol().equals(stockWeb.getIsin());
	}


	protected abstract Stock supplement(Stock stock);
	
	protected abstract Comparator<Stock> getNewStockComparator();

	protected abstract LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders);

	
	protected abstract Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException;

	public abstract void retrieveScreeningInfoForShare(ScreeningSupplementedStock trendSupStock);
	
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		
		//Adding new from web
		int cpt = 0;
		int nbShares = shareList.size();
		final Set<ScreeningSupplementedStock> listTrendIns = new ConcurrentSkipListSet<ScreeningSupplementedStock>();
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("screeninginforetrieval.semaphore.nbthread","20")));
		for (final Stock stock : shareList) {

			Thread t = new Thread(new Runnable() {

				public void run() {
					try {
						//Add trend info
						ScreeningSupplementedStock trendStock = new ScreeningSupplementedStock(stock);
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
		
		shareDAO.saveOrUpdateStockTrendInfo(listTrendIns);

	}
	
}
