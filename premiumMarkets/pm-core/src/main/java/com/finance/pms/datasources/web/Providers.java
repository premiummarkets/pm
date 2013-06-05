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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;
import java.util.prefs.Preferences;

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



// TODO: Auto-generated Javadoc
/**
 * The Class Providers.
 * 
 * @author Guillaume Thoreton
 */
public abstract class Providers  implements MyBeanFactoryAware {


	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(Providers.class);
	
	/** The Constant prefdbpath. */
	private  static final String prefdbpath = "com.finance.pms.db";
	
	/** The prefs. */
	protected  static Preferences prefs = Preferences.userRoot().node(prefdbpath);

	/** The http source. */
	protected HttpSource httpSource;
	
	protected ShareDAO shareDAO;
	protected PortfolioDAO portfolioDAO;

	protected String sharesListId;

	protected BeanFactory beanAwareFactory;
	
	protected Set<Observer> observers;
	
	/**
	 * Instantiates a new providers.
	 * 
	 * @author Guillaume Thoreton
	 */
	protected Providers() {
		observers = new HashSet<Observer>();
	}

	public static Providers getInstance(String sharesListName) {
		
		String providerBeanName = sharesListName+"ProviderSource";
		Providers provider = (Providers) SpringContext.getSingleton().getBean(providerBeanName);
		Set<Indice> indices = Indice.parseString(MainPMScmd.getPrefs().get("quotes.listproviderindices", ""));
		provider.addIndices(indices,false);
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

	/**
	 * Gets the quotes.
	 * 
	 * @param ticker the ticker
	 * @param start the start
	 * @param end the end
	 * 
	 * @return the quotes
	 * 
	 * @throws SQLException the SQL exception
	 * @throws HttpException 
	 */
	public abstract void getQuotes(Stock ticker, Date start, Date end) throws SQLException, HttpException; 
	
	
	/**
	 * Retreive stock list from web.
	 * 
	 * @param stockList the stock list
	 * @param marketQuotationsProviders the market quotations providers
	 * 
	 * @return the stock list
	 * 
	 * @author Guillaume Thoreton
	 * @throws HttpException 
	 */
	public abstract StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stocksInDB) throws HttpException;
	
	/**
	 * Retreive stock list from cmd line.
	 * 
	 * @param listStocks the list stocks
	 * @param stockList the stock list
	 * @param quotationsProvider TODO
	 * @return the stock list
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider);
	
    /**
     * Retreive stock list from base.
     * 
     * @param stockList the stock list
     * 
     * @author Guillaume Thoreton
     */
	public void retrieveStockListFromBase(StockList stockList) {
		
    		LOGGER.info("From Base : ");
    		int initSize= stockList.size();
    		
    		stockList.addAll(shareDAO.loadShares(new NullShareFilter()));
    		LOGGER.guiInfo("Number of stocks in the database on the " + new Date() + " : " + (stockList.size() - initSize));
    }
    
    
    /**
     * Update stock list from web.
     * 
     * @param marketQuotationsProviders the market quotations providers
     * 
     * @author Guillaume Thoreton
     * @throws HttpException 
     */
    public void updateStockListFromWeb(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
    	
    	StockList existingDBStocks = new StockList();
    	this.retrieveStockListFromBase(existingDBStocks);
		this.retrieveStockListFromWeb(marketQuotationsProviders, existingDBStocks);
    }
    
    /**
     * Gets the stock ref name.
     * 
     * @param stock the stock
     * 
     * @return the stock ref name
     */
    public abstract String getStockRefName(Stock stock);
    
    /**
     * Retreive and complete stock info.
     * 
     * @param s the s
     * @param stockList the stock list
     * 
     * @author Guillaume Thoreton
     */
    public abstract void retrieveAndCompleteStockInfo(Stock s, StockList stockList);
    
    public abstract void retrieveScreeningInfo(Collection<Stock> shareListInDB);

	/**
	 * Builds the lookup delete req.
	 * 
	 * @param deleteS the delete s
	 * @param deleteL the delete l
	 * @param s the s
	 * 
	 * @author Guillaume Thoreton
	 */
	protected void buildLookupDeleteReq(List<Validatable> deleteS, List<Validatable> deleteL, Stock s) {
		final Query delL = new Query();
		delL.addValue(s.getSymbol());
		delL.addValue(s.getIsin());
		deleteL.add(new Stock(s) {
			/**
			 * 
			 */
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
			/**
			 * 
			 */
			private static final long serialVersionUID = -9104197966111622791L;

			@Override
			public Query toDataBase() {
				return delS;
			}
		});
	}

	
	/**
	 * Retreive stock list from file.
	 * 
	 * @param pathToList the path to list
	 * @param stockList the stock list
	 * 
	 * @return the stock list
	 * 
	 * @author Guillaume Thoreton
	 */
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
	/**
	 * @return
	 */
	protected TreeSet<Validatable> initValidatableSet() {
		TreeSet<Validatable> queries = new TreeSet<Validatable>() ;
		return queries;
	}


	/**
	 * @param lastQuote
	 * @param queries
	 * @return
	 */
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
