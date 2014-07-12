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

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.events.calculation.NullShareFilter;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.portfolio.SharesList;


/**
 * The Class Providers.
 * 
 * @author Guillaume Thoreton
 */
public abstract class Providers extends Observable implements MyBeanFactoryAware {

	private static MyLogger LOGGER = MyLogger.getLogger(Providers.class);

	protected HttpSource httpSource;
	
	protected ShareDAO shareDAO;
	protected PortfolioDAO portfolioDAO;

	protected String sharesListId;

	protected BeanFactory beanAwareFactory;
	
	protected Set<Observer> observers;
	
	protected Providers() {
		observers = new HashSet<Observer>();
	}

	public static Providers getInstance(String sharesListName) {
		
		String providerBeanName = sharesListName+"ProviderSource";
		Providers provider = (Providers) SpringContext.getSingleton().getBean(providerBeanName);
		Set<Indice> indices = Indice.parseString(MainPMScmd.getMyPrefs().get("quotes.listproviderindices", ""));
		provider.addIndices(indices, false);
		return provider;
		
	}
	
	public static Providers setupProvider(String item) {
		int indexOfFirstComma = item.indexOf(",");
		//extract and set base
		String shareListBaseName = item;
		Set<Indice> indices = new HashSet<Indice>();
		if (indexOfFirstComma != -1) {
			shareListBaseName = item.substring(0, indexOfFirstComma).trim();
			//extract and set indices
			indices = Indice.parseString(item.substring(indexOfFirstComma+1).trim());
		}
		SharesListId sharesListId = SharesListId.valueOf(shareListBaseName);
		Providers provider = Providers.getInstance(sharesListId.getSharesListCmdParam());
		provider.addIndices(indices, true);
		return provider;
	}
	
	public static String providerShareListName(Providers provider) {
		return provider.getSharesListIdEnum().name()+Indice.formatSet(provider.getIndices());
	}
	
	public void setShareDAO(ShareDAO shareDAO) {
		this.shareDAO = shareDAO;
	}
	public void setPortfolioDAO(PortfolioDAO portfolioDAO) {
		this.portfolioDAO = portfolioDAO;
	}

	public abstract void getQuotes(Stock ticker, Date start, Date end) throws SQLException, HttpException; 
	
	public abstract StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stocksInDB) throws HttpException;

	public abstract StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider);

	public void retrieveStockListFromBase(StockList stockList) {
		
    		LOGGER.info("From Base : ");
    		int initSize= stockList.size();
    		
    		stockList.addAll(shareDAO.loadShares(new NullShareFilter()));
    		LOGGER.guiInfo("Number of stocks in the database on the " + new Date() + " : " + (stockList.size() - initSize));
    }
 
    public void updateStockListFromWeb(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
    	
    	StockList existingDBStocks = new StockList();
    	this.retrieveStockListFromBase(existingDBStocks);
		this.retrieveStockListFromWeb(marketQuotationsProviders, existingDBStocks);
    }

    public abstract String getStockRefName(Stock stock);

    public abstract void retrieveAndCompleteStockInfo(Stock s, StockList stockList);
    
    public abstract void retrieveScreeningInfo(Collection<Stock> shareListInDB);

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

	public StockList retreiveStockListFromFile(String pathToList, StockList stockList) throws InputMismatchException {
		LOGGER.debug("From File : ");
		//init des stocks fichier
		StockList fileStockList = new StockList(pathToList);
		
		for (Stock stock : fileStockList) {
			stock.retrieveStock(stockList,this.getSharesListIdEnum().getSharesListCmdParam());
		}

		return fileStockList;
	}
	

	public SharesListId getSharesListIdEnum() {
		return SharesListId.valueOf(this.sharesListId);
	}

	public void setSharesListId(String shareListId) {
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
		portfolioDAO.saveOrUpdatePortfolio(shareList);
	}
	
	public SharesList loadSharesListForThisListProvider() {
		return initSharesList(this.getSharesListIdEnum().name(), Indice.formatSet(this.getIndices()));
	}

	protected SharesList initSharesList(String sharesListName, String nameExtention) {
		SharesList shareList = portfolioDAO.loadShareList(sharesListName+nameExtention);
		if (shareList == null) shareList = new SharesList(sharesListName+nameExtention);
		return shareList;
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
	
	public abstract void addIndice(Indice indice);
	
	public abstract void addIndices(Set<Indice> indices, Boolean replace);
	
	public abstract Set<Indice> getIndices();

	protected TreeSet<Validatable> initValidatableSet() {
		TreeSet<Validatable> queries = new TreeSet<Validatable>() ;
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

}
