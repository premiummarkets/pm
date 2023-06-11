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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.SharesList;


/**
 * The Class Providers.
 * 
 * @author Guillaume Thoreton
 */
public abstract class Providers extends Observable implements MyBeanFactoryAware {

	private static MyLogger LOGGER = MyLogger.getLogger(Providers.class);

	protected HttpSource httpSource;
	
	protected BeanFactory beanAwareFactory;
	protected Set<Observer> observers;

	//TODO mv to ProvidersList
	protected String sharesListId;
	protected static final Map<String,SharesListId> shareListIds = new HashMap<>();
		
	static	{

		shareListIds.put("ALLMARKETS", new SharesListId(
				"ALLMARKETS", "allMarkets", ProvidersTypes.ALLMARKETS, 
				"Complete market as available. This may not be fully up to date. The format is as follow  ALL:<MARKET ID>.\n Where MARKET ID is like : "+ Arrays.asList(Market.values())+"\n", 
				"Complete market aggregation as available", true, true, SharesListId.sharesListIdOptionsForAllMarkets()));
		shareListIds.put("YAHOOINDICES", new SharesListId(
				"YAHOOINDICES", "yahooIndices", ProvidersTypes.YAHOOINDICES, "Your custom index format is <INDICE ID>:<MARKET ID>.\n " +
				"Where INDICE ID like NDX, FTLC, SBF250 ...\n" +
				"(these are yahoo indices like the one you can find at http://finance.yahoo.com/indices for instance.)\n " +
				"and MARKET ID is like : "+ Arrays.asList(Market.values()) + ".\n" +
				"(for instance : NDX:NASDAQ,NY:NYSE,FTLC:LSE,SBF250:EURONEXT... standing for nasdaq-100, nyse comp index, ftse 350 ...)\n" +
				"You can specify several indices comma separated and build you own composite list.\n" +
				"If left blank, it will aggregate all the pre-existing YAHOO indices available in your database.\n", 
				"YAHOO INDICES components aggregation", true, true, SharesListId.sharesListIdOptionsForYahooIndices()));
		shareListIds.put("REUTERSINDICES", new SharesListId(
				"REUTERSINDICES", "reutersIndices", ProvidersTypes.REUTERSINDICES, "Your custom index format is <INDICE ID>:<MARKET ID>.\n " +
				"Where INDICE ID like NDX, FTLC, SBF250 ...\n" +
				"(these are yahoo indices like the one you can find at http://finance.yahoo.com/indices for instance.)\n " +
				"and MARKET ID is like : "+ Arrays.asList(Market.values()) + ".\n" +
				"(for instance : NDX:NASDAQ,NY:NYSE,FTLC:LSE,SBF250:EURONEXT... standing for nasdaq-100, nyse comp index, ftse 350 ...)\n" +
				"You can specify several indices comma separated and build you own composite list.\n" +
				"If left blank, it will aggregate all the pre-existing REUTERS indices available in your database.\n", 
				"REUTERS INDICES components aggregation", true, true, SharesListId.sharesListIdOptionsForYahooIndices()));
		shareListIds.put("NSEINDIAINDICES", new SharesListId(
				"NSEINDIAINDICES", "nseIndices", ProvidersTypes.NSEINDIAINDICES, "Your custom index format is <INDICE ID>:<MARKET ID>.\n " +
				"Where INDICE ID known so far are NIFTY, JRNIFTYLISR, CNX100, CNX200, CNX500, NIFTYMIDCAP50, CNXMIDCAP, CNXSMALLCAP\n" +
				"(these are nse indices you can find at nseindia.com)\n " +
				"and MARKET ID  has to be "+Market.NSEINDIA+".\n" +
				"(for instance : NIFTY:NSE, CNX100:NSE, ... standing for cnx nifty, cnx 100 ...)\n" +
				"You can specify several indices comma separated and build you own composite list.\n" +
				"If left blank, it will aggregate all the pre-existing NSE indices available in your database.\n", 
				"NSE India INDICES components aggregation", true, true,
				new String[]{"NIFTY:NSEINDIA", "NIFTY:NSEINDIA", "JRNIFTYLISR:NSEINDIA", "CNX100:NSEINDIA", "CNX200:NSEINDIA", "CNX500:NSEINDIA", "NIFTYMIDCAP50:NSEINDIA", "CNXMIDCAP:NSEINDIA", "CNXSMALLCAP:NSEINDIA"}));
		shareListIds.put("BSE", new SharesListId("BSE", "bse", ProvidersTypes.BSE, "No comment", "All BSE from bseindia.com", false, true, new String[0]));
		
		shareListIds.put("STOCKSYMBOLAPI", new SharesListId(
				"STOCKSYMBOLAPI", "stockSymbolApi", ProvidersTypes.STOCKSYMBOLAPI, 
				"Markets and indices prodived by the https://stock-symbol.herokuapp.com/ api. The format is  <INDEX ID>:<MARKET ID>.\n", 
				"STOCK SYMBOL API market aggregation", true, true, SharesListId.sharesListIdOptionsForStockSymbolApi()));
		
		shareListIds.put("SLICKSTOCKPYTHONINDICES", new SharesListId(
				"SLICKSTOCKPYTHONINDICES", "slickStockPythonIndices", ProvidersTypes.SLICKSTOCKPYTHONINDICES, 
				"Markets and indices prodived by the https://www.slickcharts.com/{indice}.\n", 
				"Slickcharts indices", true, true, new String[] {"sp500:SP"}));

		//Broken
		shareListIds.put("GOOGLENYSE", new SharesListId("GOOGLENYSE", "nyse", ProvidersTypes.GOOGLE, "No comment", "All NYSE from google.com", false, true,new String[0]));
		shareListIds.put("GOOGLEAMEX", new SharesListId("GOOGLEAMEX", "amex", ProvidersTypes.GOOGLE, "No comment", "All AMEX from google.com", false, true, new String[0]));
		shareListIds.put("EURONEXT", new SharesListId("EURONEXT", "euronext", ProvidersTypes.EURONEXT, "No comment", "All EURONEXT shares from euronext.com", false, true, new String[0]));
		shareListIds.put("BOURSORAMA", new SharesListId("BOURSORAMA", "boursorama", ProvidersTypes.BOURSORAMA, "No comment", "All EURONEXT shares from boursorama.com", false, true, new String[0]));
		shareListIds.put("NASDAQ", new SharesListId("NASDAQ", "nasdaq", ProvidersTypes.NASDAQ, "No comment", "All NASDAQ from nasdaq.com", false, true, new String[0]));
		shareListIds.put("ASX", new SharesListId("ASX", "asx", ProvidersTypes.ASX, "No comment", "All ASX from asx.com.au", false, true,new String[0]));

		//Other (ie quotations only providers)
		shareListIds.put("UNKNOWN", SharesListId.UNKNOWN);
		
	}
	
	public static QuotationProvider getInstance(String providerCmdName) {
		String providerBeanName = providerCmdName + "ProviderSource";
		Providers beanSingleton = (Providers) SpringContext.getSingleton().getBean(providerBeanName);
		return (QuotationProvider) beanSingleton;
	}
		
	protected Providers() {
		observers = new HashSet<Observer>();
	}


    public abstract String getStockRefName(Stock stock);

    public abstract void retrieveAndCompleteStockInfo(Stock stock, StockList stockList);

	protected void buildLookupDeleteReq(List<Validatable> deleteS, List<Validatable> deleteL, Stock s) {
		
		final Query delL = new Query();
		delL.addValue(s.getSymbol());
		delL.addValue(s.getIsin());
		deleteL.add(new Stock(s) {
			private static final long serialVersionUID = 7934372449508920451L;

			@Override
			public Query toDataBase() {
				return delL;
			}
		});
		final Query delS = new Query();
		delS.addValue(s.getSymbol());
		delS.addValue(s.getIsin());
		deleteS.add(new Stock(s) {
			private static final long serialVersionUID = -9104197966111622791L;

			@Override
			public Query toDataBase() {
				return delS;
			}
		});
		
	}

	public void setSharesListId(String shareListId) {
		SharesListId staticSharesListDef = Providers.shareListIds.get(shareListId);
		if (staticSharesListDef != null) SharesListId.addSharesListId(staticSharesListDef);
		this.sharesListId = shareListId;
	}

	protected void updatingShareListInDB(SharesList shareList, final Set<Stock> sharesListStocks) {
		for (Object stockLtmp : sharesListStocks) {
			Stock ss  = DataSource.getInstance().getShareDAO().loadStockBy(((Stock)stockLtmp).getSymbol(), ((Stock)stockLtmp).getIsin());
			if (null != ss) {
				shareList.addShare(ss);
			} else {
				LOGGER.error(((Stock)stockLtmp).getSymbol()+" / "+((Stock)stockLtmp).getIsin()+" was in the stock list but is not in the Db ?", new Exception());
			}
		}
		PortfolioMgr.getInstance().getPortfolioDAO().saveOrUpdatePortfolio(shareList);
	}
	
	protected Boolean isStartAfterTodaysClose(Date start) {
		Calendar endOffTradingDay = Calendar.getInstance();
		endOffTradingDay.setTime(start);
		endOffTradingDay.set(Calendar.HOUR_OF_DAY,17);
		if (endOffTradingDay.getTime().after(new Date())) {
			return true;
		}
		return false;
	}

	protected TreeSet<ValidatableDated> initValidatableSet() {
		TreeSet<ValidatableDated> queries = new TreeSet<ValidatableDated>() ;
		return queries;
	}

	protected Date extractLastDateFrom(TreeSet<Validatable> queries) {
		Date lastQuote = null;
		if (!queries.isEmpty()) lastQuote = ((DailyQuotation) queries.last()).getQuoteDate();
		return lastQuote;
	}


	public HttpSource getHttpSource() {
		return httpSource;
	}

	public void setBeanFactory(BeanFactory beanAwareFactory) throws BeansException {
		this.beanAwareFactory = beanAwareFactory;
		
	}

	public BeanFactory getBeanFactory() {
		return beanAwareFactory;
	}
	
	public void addObservers(Set<Observer> observers) {
		if (observers != null) {
			this.observers.addAll(observers);
		} 
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		try {
			Providers newInstance = this.getClass().getDeclaredConstructor().newInstance();
			newInstance.httpSource = this.httpSource;
			newInstance.sharesListId = this.sharesListId;
			return newInstance;
		} catch (Exception e) {
			throw new CloneNotSupportedException(e.toString());
		}
	}

}
