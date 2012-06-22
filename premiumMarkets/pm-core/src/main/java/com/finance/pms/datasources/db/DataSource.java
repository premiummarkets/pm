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
package com.finance.pms.datasources.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.IncompleteArgumentException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.AlertEventKey;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.mas.RestartServerException;
import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.talib.dataresults.AlertEventValue;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;


// TODO: Auto-generated Javadoc
/**
 * The Class DataSource.
 * 
 * @author Guillaume Thoreton
 */
public class DataSource implements SourceConnector , ApplicationContextAware {

	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(DataSource.class);
	
	/** The singleton. */
	private static DataSource singleton;
	
	/** The connect not commited. */
	private Connection connectNotCommited; // = null;
	
	/** The thread pool. */
	private PoolSemaphore threadPool;
	
	/** The nb exceptions. */
	private Integer nbExceptions = 0;
	
	
	private PortfolioDAO portfolioDAO;
	private ShareDAO shareDAO;

	private ApplicationContext applicationContext;


	/**
	 * Instantiates a new data source.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 */
	public DataSource(String pathToprops) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream((new File(pathToprops))));
			// Connection
			if (props.containsKey("dbpath"))
				MainPMScmd.getPrefs().put("dbpath", props.getProperty("dbpath"));
			if (props.containsKey("software"))
				MainPMScmd.getPrefs().put("software", props.getProperty("software"));
			if (props.containsKey("driver"))
				MainPMScmd.getPrefs().put("driver", props.getProperty("driver"));
			if (props.containsKey("host"))
				MainPMScmd.getPrefs().put("host", props.getProperty("host"));
			if (props.containsKey("port"))
				MainPMScmd.getPrefs().put("port", props.getProperty("port"));
			if (props.containsKey("database"))
				MainPMScmd.getPrefs().put("database", props.getProperty("database"));
			if (props.containsKey("username"))
				MainPMScmd.getPrefs().put("username", props.getProperty("username"));
			if (props.containsKey("password"))
				MainPMScmd.getPrefs().put("password", props.getProperty("password"));
			if (props.containsKey("db.poolsize"))
				MainPMScmd.getPrefs().put("db.poolsize", props.getProperty("db.poolsize"));
			// Tables shares
			if (props.containsKey("shares"))
				MainPMScmd.getPrefs().put("shares", props.getProperty("shares"));
			if (props.containsKey("symbol"))
				MainPMScmd.getPrefs().put("symbol", props.getProperty("symbol"));
			if (props.containsKey("isin"))
				MainPMScmd.getPrefs().put("isin", props.getProperty("isin"));
			if (props.containsKey("sicovam"))
				MainPMScmd.getPrefs().put("sicovam", props.getProperty("sicovam"));
			if (props.containsKey("name"))
				MainPMScmd.getPrefs().put("name", props.getProperty("name"));
			if (props.containsKey("date"))
				MainPMScmd.getPrefs().put("date", props.getProperty("date"));
			if (props.containsKey("open"))
				MainPMScmd.getPrefs().put("open", props.getProperty("open"));
			if (props.containsKey("close"))
				MainPMScmd.getPrefs().put("close", props.getProperty("close"));
			if (props.containsKey("high"))
				MainPMScmd.getPrefs().put("high", props.getProperty("high"));
			if (props.containsKey("low"))
				MainPMScmd.getPrefs().put("low", props.getProperty("low"));
			if (props.containsKey("volume"))
				MainPMScmd.getPrefs().put("volume", props.getProperty("volume"));
			if (props.containsKey("currency"))
				MainPMScmd.getPrefs().put("currency", props.getProperty("currency"));
			if (props.containsKey("lookup"))
				MainPMScmd.getPrefs().put("lookup", props.getProperty("lookup"));
			if (props.containsKey("lookup.symbol"))
				MainPMScmd.getPrefs().put("lookup.symbol", props.getProperty("lookup.symbol"));
			if (props.containsKey("lookup.isin"))
				MainPMScmd.getPrefs().put("lookup.isin", props.getProperty("lookup.isin"));
			if (props.containsKey("lookup.name"))
				MainPMScmd.getPrefs().put("lookup.name", props.getProperty("lookup.name"));
			if (props.containsKey("lookup.removable"))
				MainPMScmd.getPrefs().put("lookup.removable", props.getProperty("lookup.removable"));
			if (props.containsKey("lookup.category"))
				MainPMScmd.getPrefs().put("lookup.category", props.getProperty("lookup.category"));
			if (props.containsKey("lookup.lastquote"))
				MainPMScmd.getPrefs().put("lookup.lastquote", props.getProperty("lookup.lastquote"));
			// Tables lard feuille
			if (props.containsKey("portfolio.table"))
				MainPMScmd.getPrefs().put("portfolio.table", props.getProperty("portfolio.table"));
			if (props.containsKey("portfolio.symbol"))
				MainPMScmd.getPrefs().put("portfolio.symbol", props.getProperty("portfolio.symbol"));
			if (props.containsKey("portfolio.quantity"))
				MainPMScmd.getPrefs().put("portfolio.quantity", props.getProperty("portfolio.quantity"));
			if (props.containsKey("portfolio.cashin"))
				MainPMScmd.getPrefs().put("portfolio.cashin", props.getProperty("portfolio.cashin"));
			if (props.containsKey("portfolio.cashout"))
				MainPMScmd.getPrefs().put("portfolio.cashout", props.getProperty("portfolio.cashout"));
			if (props.containsKey("portfolio.name"))
				MainPMScmd.getPrefs().put("portfolio.name", props.getProperty("portfolio.name"));
			// Tables Events
			if (props.containsKey("events.table"))
				MainPMScmd.getPrefs().put("events.table", props.getProperty("events.table"));
			if (props.containsKey("events.symbol"))
				MainPMScmd.getPrefs().put("events.symbol", props.getProperty("events.symbol"));
			if (props.containsKey("events.accuracy"))
				MainPMScmd.getPrefs().put("events.accuracy", props.getProperty("events.accuracy"));
			if (props.containsKey("events.state"))
				MainPMScmd.getPrefs().put("events.state", props.getProperty("events.state"));
			if (props.containsKey("events.date"))
				MainPMScmd.getPrefs().put("events.date", props.getProperty("events.date"));
			if (props.containsKey("events.eventdefid"))
				MainPMScmd.getPrefs().put("events.eventdefid", props.getProperty("events.eventdefid"));
			if (props.containsKey("events.eventdef"))
				MainPMScmd.getPrefs().put("events.eventdef", props.getProperty("events.eventdef"));
			if (props.containsKey("events.type"))
				MainPMScmd.getPrefs().put("events.type", props.getProperty("events.type"));
			// Semaphore
			putInPrefs("alertcalculator.semaphore.nbthread",props);
			putInPrefs("indicatorcalculator.semaphore.nbthread",props);
			putInPrefs("currencyconverter.semaphore.nbthread",props);
			putInPrefs("marketlistretrieval.semaphore.nbthread",props);
			putInPrefs("screeninginforetrieval.semaphore.nbthread",props);
			putInPrefs("quotationretrieval.semaphore.nbthread",props);
			putInPrefs("trendeventscalculation.semaphore.nbthread",props);
			putInPrefs("indicatortunning.semaphore.nbthread",props);

			//Analyse
			if (props.containsKey("mas.daysbackwardday"))
				MainPMScmd.getPrefs().put("mas.daysbackwardday", props.getProperty("mas.daysbackwardday"));
			if (props.containsKey("talib.daysbackwardday"))
				MainPMScmd.getPrefs().put("talib.daysbackwardday", props.getProperty("talib.daysbackwardday"));
			
			//Quotes
			if (props.containsKey("quotes.listfile"))
				MainPMScmd.getPrefs().put("quotes.listfile", props.getProperty("quotes.listfile"));
			if (props.containsKey("quotes.lastlistfetch"))
				MainPMScmd.getPrefs().put("quotes.lastlistfetch", props.getProperty("quotes.lastlistfetch"));
			if (props.containsKey("quotes.lastfetch"))
				MainPMScmd.getPrefs().put("quotes.lastfetch", props.getProperty("quotes.lastfetch"));
			if (props.containsKey("quotes.lastanalyse"))
				MainPMScmd.getPrefs().put("quotes.lastanalyse", props.getProperty("quotes.lastanalyse"));
			if (props.containsKey("quotes.provider"))
				MainPMScmd.getPrefs().put("quotes.provider", props.getProperty("quotes.provider"));
			if (props.containsKey("quotes.listprovider"))
				MainPMScmd.getPrefs().put("quotes.listprovider", props.getProperty("quotes.listprovider"));
			if (props.containsKey("analyse.mas.enable"))
				MainPMScmd.getPrefs().put("analyse.mas.enable", props.getProperty("analyse.mas.enable"));
			if (props.containsKey("quotes.sendeventfromui"))
				MainPMScmd.getPrefs().put("quotes.sendeventfromui", props.getProperty("quotes.sendeventfromui"));
			if (props.containsKey("quotes.yahoo.indices"))
				MainPMScmd.getPrefs().put("quotes.yahoo.indices", props.getProperty("quotes.yahoo.indices"));
			
			//Events
			if (props.containsKey("event.stoploss"))
				MainPMScmd.getPrefs().put("event.stoploss", props.getProperty("event.stoploss"));
			if (props.containsKey("event.sellalert"))
				MainPMScmd.getPrefs().put("event.sellalert", props.getProperty("event.sellalert"));
			if (props.containsKey("event.buytrigger"))
				MainPMScmd.getPrefs().put("event.buytrigger", props.getProperty("event.buytrigger"));
			if (props.containsKey("event.selltrigger"))
				MainPMScmd.getPrefs().put("event.selltrigger", props.getProperty("event.selltrigger"));
			if (props.containsKey("event.maxloss"))
				MainPMScmd.getPrefs().put("event.maxloss", props.getProperty("event.maxloss"));
			
			if (props.containsKey("event.indicators"))
				MainPMScmd.getPrefs().put("event.indicators", props.getProperty("event.indicators"));
			if (props.containsKey("event.sellindicators"))
				MainPMScmd.getPrefs().put("event.sellindicators", props.getProperty("event.sellindicators"));
			if (props.containsKey("event.buyindicators"))
				MainPMScmd.getPrefs().put("event.buyindicators", props.getProperty("event.buyindicators"));
			putInPrefs("event.indepIndicators",props);
			
			if (props.containsKey("mail.infoalert.activated"))
				MainPMScmd.getPrefs().put("mail.infoalert.activated", props.getProperty("mail.infoalert.activated"));
			if (props.containsKey("event.backwarddayspan"))
				MainPMScmd.getPrefs().put("event.backwarddayspan", props.getProperty("event.backwarddayspan"));
			if (props.containsKey("event.expectedrate"))
				MainPMScmd.getPrefs().put("event.expectedrate", props.getProperty("event.expectedrate"));
			putInPrefs("event.nbPassMax",props);
			
			
			//Neural
			if (props.containsKey("event.buyponderationrule"))
				MainPMScmd.getPrefs().put("event.buyponderationrule", props.getProperty("event.buyponderationrule"));
			if (props.containsKey("event.sellponderationrule"))
				MainPMScmd.getPrefs().put("event.sellponderationrule", props.getProperty("event.sellponderationrule"));
			if (props.containsKey("perceptron.autoretrain"))
				MainPMScmd.getPrefs().put("perceptron.autoretrain", props.getProperty("perceptron.autoretrain"));
			if (props.containsKey("perceptron.filterTolerance"))
				MainPMScmd.getPrefs().put("perceptron.filterTolerance", props.getProperty("perceptron.filterTolerance"));
			putInPrefs("perceptron.trainingNbSlices",props);
			putInPrefs("perceptron.maxNbOfTrainingYears",props);
			putInPrefs("perceptron.minNbOfTrainingYears",props);
			putInPrefs("perceptron.expectedSmothingSMAPeriod",props);
			putInPrefs("perceptron.perceptronMinMonthEvents",props);
			
			//tune 
			putInPrefs("perceptron.retuneSpan", props);
			putInPrefs("perceptron.retuneFreq", props);
			putInPrefs("tuning.configs",props);
			
			//Q fact
			putInPrefs("bean.quotationFactory", props);

			//Indicators
			putInPrefs("indicators.smareversalsmaperiod",props);
			putInPrefs("indicators.stddevsmaperiod",props);
			putInPrefs("indicators.variancesmaperiod",props);
			
			putInPrefs("indicators.macd.fastperiod",props);
			putInPrefs("indicators.macd.slowperiod",props);
			putInPrefs("indicators.macd.signal",props);
			
			putInPrefs("indicators.variation.period",props);
			putInPrefs("indicators.variation.spandiff",props);
			
			putInPrefs("indicators.variance.period",props);
			putInPrefs("indicators.variance.spandiff",props);
			putInPrefs("indicators.variance.minvalid",props);
			
			//Screnner Trend
			if (props.containsKey("trend.sellthreshold"))
				MainPMScmd.getPrefs().put("trend.sellthreshold", props.getProperty("trend.sellthreshold"));
			if (props.containsKey("trend.buythreshold"))
				MainPMScmd.getPrefs().put("trend.buythreshold", props.getProperty("trend.buythreshold"));
			
			//Gnu
			if (props.containsKey("gnurepport.dateformat"))
				MainPMScmd.getPrefs().put("gnurepport.dateformat", props.getProperty("gnurepport.dateformat"));
			
			//End date test
			putInPrefs("test.endDate", props);
			
			//Event cache
			putInPrefs("event.cache", props);
			
			MainPMScmd.getPrefs().flush();
		} catch (Exception e) {
			LOGGER.error("Couldn't find propertie file. Check the propeties path",e);
		}
		QUOTATIONS.TABLE_NAME = MainPMScmd.getPrefs().get("quotations", "quotations");
		QUOTATIONS.SYMBOL_FIELD = MainPMScmd.getPrefs().get("symbol", "symbol");
		QUOTATIONS.ISIN_FIELD = MainPMScmd.getPrefs().get("isin", "isin");
		QUOTATIONS.DATE_FIELD = MainPMScmd.getPrefs().get("date", "date");
		QUOTATIONS.DAY_OPEN_FIELD = MainPMScmd.getPrefs().get("open", "openvalue");
		QUOTATIONS.DAY_CLOSE_FIELD = MainPMScmd.getPrefs().get("close", "closevalue");
		QUOTATIONS.DAY_HIGH_FIELD = MainPMScmd.getPrefs().get("high", "high");
		QUOTATIONS.DAY_LOW_FIELD = MainPMScmd.getPrefs().get("low", "low");
		QUOTATIONS.DAY_VOLUME_FIELD = MainPMScmd.getPrefs().get("volume", "volume");
		QUOTATIONS.CURRENCY_FIELD = MainPMScmd.getPrefs().get("currency", "currency");
		SHARES.TABLE_NAME = MainPMScmd.getPrefs().get("shares", "shares");
		SHARES.ISIN_FIELD = MainPMScmd.getPrefs().get("lookup.isin", "isin");
		SHARES.SYMBOL_FIELD = MainPMScmd.getPrefs().get("lookup.symbol", "symbol");
		SHARES.NAME_FIELD = MainPMScmd.getPrefs().get("lookup.name", "name");
		SHARES.REMOVABLE = MainPMScmd.getPrefs().get("lookup.removable", "removable");
		SHARES.CATEGORY = MainPMScmd.getPrefs().get("lookup.category", "category");
		SHARES.LASTQUOTE = MainPMScmd.getPrefs().get("lookup.lastquote", "lastquote");
		SHARES.QUOTATIONPROVIDER = MainPMScmd.getPrefs().get("lookup.provider", "quotationprovider");
		SHARES.MARKET = MainPMScmd.getPrefs().get("lookup.market", "marketlistprovider");
		PORTFOLIO.TABLE_NAME = MainPMScmd.getPrefs().get("portfolio.table", "portfolio");
		PORTFOLIO.SYMBOL_FIELD = MainPMScmd.getPrefs().get("portfolio.symbol", "symbol");
		PORTFOLIO.QUANTIY_FIELD = MainPMScmd.getPrefs().get("portfolio.quantity", "quantity");
		PORTFOLIO.DATE_FIELD = MainPMScmd.getPrefs().get("portfolio.buydate", "buydate");
		PORTFOLIO.CASHIN_FIELD = MainPMScmd.getPrefs().get("portfolio.cashin", "cashin");
		PORTFOLIO.CASHOUT_FIELD = MainPMScmd.getPrefs().get("portfolio.cashout", "cashout");
		PORTFOLIO.NAME_FIELD = MainPMScmd.getPrefs().get("portfolio.name", "name");
		PORTFOLIO.MONITOR_FIELD = MainPMScmd.getPrefs().get("portfolio.monitor", "monitor");
		EVENTS.EVENTS_TABLE_NAME = MainPMScmd.getPrefs().get("events.table", "events");
		EVENTS.SYMBOL_FIELD = MainPMScmd.getPrefs().get("events.symbol", "symbol");
		EVENTS.ISIN_FIELD = MainPMScmd.getPrefs().get("events.isin", "isin");
		EVENTS.ACCURACY_FIELD = MainPMScmd.getPrefs().get("events.accuracy", "accuracy");
		EVENTS.DATE_FIELD = MainPMScmd.getPrefs().get("events.date", "date");
		EVENTS.EVENTDEFID_FIELD = MainPMScmd.getPrefs().get("events.eventdefid", "eventdefid");
		EVENTS.EVENTDEF_FIELD = MainPMScmd.getPrefs().get("events.eventdef", "eventdef");
		EVENTS.EVENTDEFEXTENSION_FIELD = "EVENTDEFEXTENSION";
		EVENTS.EVENTTYPE_FIELD = MainPMScmd.getPrefs().get("events.type", "eventtype");
		EVENTS.ANALYSE_NAME = MainPMScmd.getPrefs().get("events.type", "analysename");
		
		if (singleton == null) {
			LOGGER.debug("Number of Long batch DB Threads :" + new Integer(MainPMScmd.getPrefs().get("db.poolsize", "10")));
			threadPool = new PoolSemaphore((new Integer(MainPMScmd.getPrefs().get("db.poolsize", "10"))).intValue(), this, false);
			singleton = this;
		}
	}

	/**
	 * @param property 
	 * @param props
	 */
	private void putInPrefs(String property, Properties props) {
		if (props.containsKey(property))
			MainPMScmd.getPrefs().put(property, props.getProperty(property));
	}

	/**
	 * Gets the single instance of DataSource.
	 * 
	 * @return single instance of DataSource
	 */
	public static DataSource getInstance() {
		if (singleton != null) {
			if (singleton.portfolioDAO == null) {
				singleton.portfolioDAO = (PortfolioDAO) singleton.applicationContext.getBean("portfolioDAO");
			}
			if (singleton.shareDAO == null) {
				singleton.shareDAO = (ShareDAO) singleton.applicationContext.getBean("shareDAO");
			}
			return singleton;
		} else {
			throw new RuntimeException("Data source not initialized");
		}
	}

	/**
	 * Gets the connection from pool.
	 * 
	 * @return the connection from pool
	 */
	public static MyDBConnection getConnectionFromPool() {
		MyDBConnection ret;
		try {
			ret = (MyDBConnection) DataSource.getInstance().getThreadPool().getResource();
			return ret;
		} catch (InterruptedException e) {
			LOGGER.error("Unable to get Connection. Is data base started?", e);
			System.exit(1);
		} catch (TimeoutException e) {
			LOGGER.error("Unable to get Connection. Is data base started? Thread lock ?", e);
		}
		LOGGER.error("Unable to get Connection. For one reason or the other ...");
		System.exit(1);
		return null;
	}

	/**
	 * Realese pool connection.
	 * 
	 * @param conn the conn
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void realesePoolConnection(SourceClient conn) {
		try {
			((MyDBConnection) conn).getConn().commit();
		} catch (SQLException e) {
			// ((MyDBConnection) conn).getConn().rollback();
			LOGGER.error("ERROR releasing connection : ",e);
			LOGGER.debug(e.getCause());
			LOGGER.debug(e.getNextException());
		}
		DataSource.getInstance().getThreadPool().releaseResource(conn);
	}

	// metier
	/**
	 * Gets the last quotation.
	 * 
	 * @param stock the stock
	 * 
	 * @return the last quotation
	 */
	public Date getLastQuotationDateFromShares(Stock stock) {
		String query = "SELECT " + SHARES.LASTQUOTE + " FROM " + SHARES.TABLE_NAME + " WHERE " + SHARES.SYMBOL_FIELD + " = ? AND " + SHARES.ISIN_FIELD + " = ? ";
		return this.getLastFormerQuote(stock, query);
	}

	/**
	 * Gets the max quotation.
	 * 
	 * @param stock the stock
	 * 
	 * @return the max quotation
	 */
	public Date getLastQuotationDateFromQuotations(Stock stock) {
		
		String endConstraint = testEndConstraint();
		
		String q = "select " + QUOTATIONS.DATE_FIELD + " from " + QUOTATIONS.TABLE_NAME + " where "
		+ QUOTATIONS.SYMBOL_FIELD + " = ? AND " + QUOTATIONS.ISIN_FIELD + " = ? "+endConstraint+" order by "+QUOTATIONS.DATE_FIELD+" desc ";
		return this.getLastFormerQuote(stock, q);
	}

	/**
	 * @return
	 */
	private String testEndConstraint() {
		
		String endConstraint = "";
		
		try {
			if (EventSignalConfig.ENDDATE != null) {
				endConstraint = " AND "+QUOTATIONS.DATE_FIELD+ " <= '" + new SimpleDateFormat("yyyy-MM-dd").format(EventSignalConfig.getNewDate())+ "' ";
			}
		} catch (IllegalArgumentException e) {
			LOGGER.debug("No test past end date specified because : "+e);
		}
		
		return endConstraint;
		
	}
	
	public Date getFirstQuotationDateFromQuotations(Stock stock) {
		String q = "select " + QUOTATIONS.DATE_FIELD + " from " + QUOTATIONS.TABLE_NAME + " where "
		+ QUOTATIONS.SYMBOL_FIELD + " = ? AND " + QUOTATIONS.ISIN_FIELD + " = ? order by "+QUOTATIONS.DATE_FIELD+" asc ";
		return this.getLastFormerQuote(stock, q);
	}


	/**
	 * Gets the last former quote.
	 * 
	 * @param stock the stock
	 * @param sqlQuery the sql query
	 * 
	 * @return the last former quote
	 */
	private Date getLastFormerQuote(Stock stock, String sqlQuery) {
		Date retour;
		MyDBConnection scnx = this.getConnection(true);
		try {
			PreparedStatement pst;
			pst = scnx.getConn().prepareStatement(sqlQuery);
			pst.setMaxRows(1);
			pst.setString(1, stock.getSymbol());
			pst.setString(2, stock.getIsin());
			
			ResultSet rs;
			rs = pst.executeQuery();
			Date date = (rs.next()) ? rs.getDate(1) : null;
			
			if (date != null) {
				retour = date;
				LOGGER.debug("Last ticker date in data base for " + stock.getSymbol() + " : " + date.toString());
			} else {
				// retour = "19700101";
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				retour = df.parse("19700101");
				LOGGER.warn("No value in data base : " + stock + " is a new ticker");
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			LOGGER.error("Query : " +  sqlQuery + "Param : " + stock,e);
			retour = null;
		} catch (ParseException e) {
			LOGGER.error("Date formating ERROR while reading data base :" + e,e);
			retour = null;
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		return retour;
	}
	
	
	public Set<Stock> loadStocksForCurrentShareList() {
		String currentMarket =  MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
		Providers provider = Providers.getInstance(currentMarket);
		return loadStocksList(currentMarket + Indice.formatName(provider.getIndices()));
	}

	public  Set<Stock> loadStocksList(String shareList) {
		if (shareList == null) {
			throw new IncompleteArgumentException("shareList");
		}
		
		SharesList sharesList = portfolioDAO.loadShareList(shareList);
		return sharesList.toStocksSet();
		
	}
	
	public Collection<Stock> loadAllStocks() {
		return shareDAO.loadAllShares();
	}

	/**
	 * Gets the orphan shares.
	 * 
	 * @return the orphan shares
	 */
	@Deprecated
	public StockList getOrphanShares() {
		StockList retour = new StockList();
		MyDBConnection scnx = this.getConnection(true);
		//TODO proper request.
		String sqlQuery = new String(
				"select distinct quotations.symbol, quotations.isin from quotations left outer join shares "+
				"on quotations.symbol = shares.symbol and quotations.isin = shares.isin " +
				"where shares.symbol is null or shares.isin is null " );
		try {
			ResultSet rs;
			PreparedStatement pst;
			pst = scnx.getConn().prepareStatement(sqlQuery);
			rs = pst.executeQuery();
			while (rs.next()) {
				Stock st = new Stock(rs.getString(2), rs.getString(1),StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),Market.UNKNOWN);
				retour.add(st);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			LOGGER.error("Query : " + sqlQuery,e);
			retour = null;
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		return retour;
	}

	/**
	 * Load stock by symbol.
	 * 
	 * @param symbol the symbol
	 * 
	 * @return the stock
	 * 
	 * @author Guillaume Thoreton
	 */
	public Stock loadStockBySymbol(String symbol) {
		MyDBConnection scnx = this.getConnection(true);
		String sqlQuery = new String("SELECT * FROM " + SHARES.TABLE_NAME + " WHERE " + SHARES.SYMBOL_FIELD + " = ?");
		Stock retour = null;
		try {
			ResultSet rs;
			PreparedStatement pst;
			pst = scnx.getConn().prepareStatement(sqlQuery);
			//String symbollike = (symbol.contains("."))?symbol+"%":symbol+".%";
			String symbollike = symbol;
			pst.setString(1, symbollike);
			rs = pst.executeQuery();
			if (rs.next()) {
				try {
					retour = new Stock(rs.getString(SHARES.ISIN_FIELD).trim(),rs.getString(SHARES.SYMBOL_FIELD).trim(), rs
							.getString(SHARES.NAME_FIELD).trim(), rs.getBoolean(SHARES.REMOVABLE), StockCategories.valueOf(rs
							.getString(SHARES.CATEGORY).trim()), rs.getDate(SHARES.LASTQUOTE), 
							new SymbolMarketQuotationProvider(rs.getString(SHARES.QUOTATIONPROVIDER).trim(),rs.getString(SHARES.SYMBOL_FIELD).trim()),
							Market.valueOf(rs.getString(SHARES.MARKET).trim()),
							rs.getString(SHARES.SECTOR_HINT),
							TradingMode.valueOf(rs.getString(SHARES.TRADING_MODE).trim()),
							rs.getLong(SHARES.CAPITALISATION));
					
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.error("", e);
				}
				if (rs.next()) {
					//throw new IOException("WARN : multiple values in DB for share : " + symbol);
					LOGGER.warn("WARN : multiple values in DB for share : " + symbol);
				}
			} else {
				throw new IOException("WARN : No value in DB for symbol : " + symbol);
			}
			rs.close();
			pst.close();
		} catch (IOException ioe) {
			LOGGER.error("Could not load share value for :" + ioe,ioe);
		} catch (SQLException e) {
			LOGGER.error("SQL ERROR while loading stock. Query : " + sqlQuery,e);
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		return retour;
	}
	
	/**
	 * Load limited striped quotations.
	 * 
	 * @param stock the stock
	 * @param firstDate the nb elements
	 * @param order the order
	 * 
	 * @return the striped quotations
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<QuotationUnit> loadStripedQuotationsAfter(Stock stock, Date firstDate) {
		
		Query query = new Query(
				"select distinct " + QUOTATIONS.TABLE_NAME + ".* from " + QUOTATIONS.TABLE_NAME + " where "
				+ QUOTATIONS.TABLE_NAME + "." + QUOTATIONS.SYMBOL_FIELD + " = ? AND "
				+ QUOTATIONS.TABLE_NAME + "." + QUOTATIONS.ISIN_FIELD + " = ?  AND "
				+ QUOTATIONS.DATE_FIELD + " >=  ?  "+testEndConstraint()+" order by date asc ") {
			
			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				
				retour.add(new QuotationUnit(rs.getDate(QUOTATIONS.DATE_FIELD), rs.getBigDecimal(QUOTATIONS.DAY_OPEN_FIELD), 
						rs.getBigDecimal(QUOTATIONS.DAY_HIGH_FIELD), rs.getBigDecimal(QUOTATIONS.DAY_LOW_FIELD),
						rs.getBigDecimal(QUOTATIONS.DAY_CLOSE_FIELD), rs.getLong(QUOTATIONS.DAY_VOLUME_FIELD)));
			}
		};
		query.addValue(stock.getSymbol());
		query.addValue(stock.getIsin());
		query.addValue(firstDate);
		
		List<? extends Object> retour = this.exectuteSelect(ArrayList.class,query);
		return (ArrayList<QuotationUnit>) retour;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<QuotationUnit> loadNStripedQuotationsBefore(Stock stock, Date refDate, Integer indexShift, boolean includeRefDate) {
		
		String infOrEqual = " < ";
		if (includeRefDate) {
			infOrEqual = " <= ";
		}
		
		Query query = new Query("select distinct " + QUOTATIONS.TABLE_NAME + ".* from " + QUOTATIONS.TABLE_NAME + " where "
				+ QUOTATIONS.TABLE_NAME + "." + QUOTATIONS.SYMBOL_FIELD + " = ? AND "
				+ QUOTATIONS.TABLE_NAME + "." + QUOTATIONS.ISIN_FIELD + " = ?  AND "
				+ QUOTATIONS.DATE_FIELD +infOrEqual+" ? order by date desc") {

			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				retour.add(new QuotationUnit(rs.getDate(QUOTATIONS.DATE_FIELD), rs.getBigDecimal(QUOTATIONS.DAY_OPEN_FIELD), 
						rs.getBigDecimal(QUOTATIONS.DAY_HIGH_FIELD), rs.getBigDecimal(QUOTATIONS.DAY_LOW_FIELD),
						rs.getBigDecimal(QUOTATIONS.DAY_CLOSE_FIELD), rs.getLong(QUOTATIONS.DAY_VOLUME_FIELD)));
			}
		};
		
		query.addValue(stock.getSymbol());
		query.addValue(stock.getIsin());
		query.addValue(refDate);
		List<? extends Object> retour = this.executeQuery(query, indexShift);
		
		NavigableSet<QuotationUnit> quotationUnits = new TreeSet<QuotationUnit>((ArrayList<QuotationUnit>) retour);
		return new ArrayList<QuotationUnit>(quotationUnits);
	}

	// Events
	/**
	 * Gets the symbol monitor level.
	 * 
	 * @param symbol the symbol
	 * 
	 * @return the symbol monitor level
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public MonitorLevel getSymbolMonitorLevel(String symbol) {
		Query select = new Query(
				"SELECT " + PORTFOLIO.TABLE_NAME + "." + PORTFOLIO.MONITOR_FIELD 
				+ " FROM "+ PORTFOLIO.TABLE_NAME 
				+ " WHERE " + PORTFOLIO.TABLE_NAME + "." + PORTFOLIO.SYMBOL_FIELD	+ "= ?") {
			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				retour.add(rs.getInt(PORTFOLIO.MONITOR_FIELD));
			}
		};
		select.addValue(symbol);
		Integer ret = 0;
		List<? extends Object> l = exectuteSelect(Object.class,select);
		List<Integer> li = (List<Integer>) l;
		for (Integer i : li) {
			ret = Math.max(i, ret);
		}
		return MonitorLevel.values()[ret];
		
	}

	/**
	 * Load events by date.
	 * 
	 * @param startDate the date
	 * 
	 * @return the list< symbol events>
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unchecked")
	public List<SymbolEvents> loadEventsByDate(Date startDate, Date endDate, String... eventListNames) {
		
		String eventListConstraint = " ( ";
		String sep = "'";
		for (String eventlist : eventListNames) {
			eventListConstraint = eventListConstraint + sep + eventlist;
			sep = "' , '";
		}
		eventListConstraint += "' ) ";
		
		Query select = new Query(
				"SELECT "
				+ EVENTS.EVENTS_TABLE_NAME + ".*," 
				+ SHARES.TABLE_NAME+ ".*" 
				+ " FROM " + EVENTS.EVENTS_TABLE_NAME + "," + SHARES.TABLE_NAME 
				+ " WHERE "
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.DATE_FIELD + " >= ? AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.DATE_FIELD + " <= ? AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ANALYSE_NAME + " in "+eventListConstraint+" AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.EVENTTYPE_FIELD + " <> '"+EventType.INFO.getEventTypeChar()+"' AND " 
				+ SHARES.TABLE_NAME + "."+ SHARES.SYMBOL_FIELD + "=" + EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.SYMBOL_FIELD + " AND "
				+ SHARES.TABLE_NAME + "." + SHARES.ISIN_FIELD + "=" + EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ISIN_FIELD ) {
			
			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				try {
					SymbolEvents se = new SymbolEvents(
						new Stock(
							rs.getString(SHARES.ISIN_FIELD).trim(), 
							rs.getString(SHARES.SYMBOL_FIELD).trim(),
							rs.getString(SHARES.NAME_FIELD).trim(), 
							null,
							StockCategories.valueOf(rs.getString(SHARES.CATEGORY).trim()), 
							rs.getDate(SHARES.LASTQUOTE),
							new SymbolMarketQuotationProvider(rs.getString(SHARES.QUOTATIONPROVIDER).trim(),
							rs.getString(SHARES.SYMBOL_FIELD).trim()),
							Market.valueOf(rs.getString(SHARES.MARKET).trim()),
							rs.getString(SHARES.SECTOR_HINT),
							TradingMode.valueOf(rs.getString(SHARES.TRADING_MODE).trim()),
							rs.getLong(SHARES.CAPITALISATION)
						), 
						EventState.valueOf(rs.getInt(EVENTS.ACCURACY_FIELD)));
					
					if (!retour.contains(se)) retour.add(se);
					SymbolEvents sers = (SymbolEvents) retour.get(retour.indexOf(se));
					readEventFromRS(rs, sers);
					
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.error("", e);
					throw new SQLException(e.getMessage());
				}
			}
		};
		
		select.addValue(startDate);
		select.addValue(endDate);
		
		LOGGER.debug(select.getQuery()+" with startDate :"+startDate+ " and endDate :	"+endDate);
		List<? extends Object> lret = exectuteSelect(Object.class, select);
		
		return (List<SymbolEvents>) lret;
	}
	
	public SymbolEvents loadEventsByDate(final Stock stock, Date startDate, Date endDate, String... eventListNames) {
		
		String eventListConstraint = " ( ";
		String sep = "'";
		for (String eventlist : eventListNames) {
			eventListConstraint = eventListConstraint + sep + eventlist;
			sep = "' , '";
		}
		eventListConstraint += "' ) ";
		
		Query select = new Query(
				"SELECT "
				+ EVENTS.EVENTS_TABLE_NAME + ".*"
				+ " FROM " + EVENTS.EVENTS_TABLE_NAME
				+ " WHERE "
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.DATE_FIELD + " >= ? AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.DATE_FIELD + " <= ? AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ANALYSE_NAME + " in "+eventListConstraint+" AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.EVENTTYPE_FIELD + " <> '"+EventType.INFO.getEventTypeChar()+"' AND " 
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.SYMBOL_FIELD + " = ? AND "
				+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ISIN_FIELD+" = ?") {
			
			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				try {
					SymbolEvents se = new SymbolEvents(stock, EventState.valueOf(rs.getInt(EVENTS.ACCURACY_FIELD)));
					
					if (!retour.contains(se)) retour.add(se);
					SymbolEvents sers = (SymbolEvents) retour.get(retour.indexOf(se));
					readEventFromRS(rs, sers);
					
				} catch (Exception e) {
					LOGGER.error("", e);
					throw new SQLException(e);
				}
			}
		};
		
		select.addValue(startDate);
		select.addValue(endDate);
		select.addValue(stock.getSymbol());
		select.addValue(stock.getIsin());
		
		LOGGER.debug(select.getQuery()+" with startDate :"+startDate+ " and endDate :	"+endDate);
		List<? extends Object> lret = exectuteSelect(Object.class, select);
		
		if (lret.size() == 0) return  new SymbolEvents(stock, EventState.STATE_TERMINATED);
		if (lret.size() > 1) {
			LOGGER.error("request returnd more than one value "+select, new Throwable());
			return  new SymbolEvents(stock, EventState.STATE_TERMINATED);
		}
		return (SymbolEvents) lret.get(0);
	}
	
	private void readEventFromRS(ResultSet rs, SymbolEvents sers) throws SQLException {
		
		Integer eventType = rs.getInt(EVENTS.EVENTDEFID_FIELD);
		EventKey eventKey;
		EventValue eventValue;
		
		if (EventDefinition.ALERTTHRESHOLD.equals(EventDefinition.valueOf(eventType))) {
			eventKey = new AlertEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getInt(EVENTS.EVENTDEFID_FIELD), rs.getString(EVENTS.EVENTTYPE_FIELD), rs.getString(EVENTS.EVENTDEFEXTENSION_FIELD));
			eventValue = new AlertEventValue(rs.getDate(EVENTS.DATE_FIELD), 
						rs.getInt(EVENTS.EVENTDEFID_FIELD),
						rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
						rs.getString(EVENTS.MESSAGE_FIELD),
						rs.getString(EVENTS.ANALYSE_NAME));
		} else if (EventDefinition.SCREENER.equals(EventDefinition.valueOf(eventType))) {
			eventKey = new StandardEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getInt(EVENTS.EVENTDEFID_FIELD), rs.getString(EVENTS.EVENTTYPE_FIELD));
			eventValue = new AlertEventValue(rs.getDate(EVENTS.DATE_FIELD), 
					rs.getInt(EVENTS.EVENTDEFID_FIELD),
					rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
					rs.getString(EVENTS.MESSAGE_FIELD),
					rs.getString(EVENTS.ANALYSE_NAME));
		} else {
			eventKey = new StandardEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getInt(EVENTS.EVENTDEFID_FIELD), rs.getString(EVENTS.EVENTTYPE_FIELD));
			eventValue = new StandardEventValue(rs.getDate(EVENTS.DATE_FIELD), 
						rs.getInt(EVENTS.EVENTDEFID_FIELD),
						rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
						rs.getString(EVENTS.MESSAGE_FIELD),
						rs.getString(EVENTS.ANALYSE_NAME));
		}
		
		sers.addEventResultElement(eventKey, eventValue, rs.getString(EVENTS.EVENTDEF_FIELD).trim());
		
	}
	
	
	public EventValue getLastTrendEventFor(Stock stock, String listEventName, Date endDate, EventType...eventTypes) {
		
		String eventTypeConstraint = " ( ";
		String sep = "'";
		for (EventType eventType : eventTypes) {
			eventTypeConstraint = eventTypeConstraint + sep + eventType.getEventTypeChar();
			sep = "' , '";
		}
		eventTypeConstraint += "' ) ";
		
		Query select = new Query(
				"SELECT "
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.ANALYSE_NAME+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.DATE_FIELD+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.EVENTDEFID_FIELD+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.EVENTTYPE_FIELD+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.ISIN_FIELD+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.MESSAGE_FIELD+","
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.SYMBOL_FIELD
				+ " FROM " + EVENTS.EVENTS_TABLE_NAME 
				+ " WHERE "
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ANALYSE_NAME + " = ? AND " 
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.EVENTTYPE_FIELD +" in "+eventTypeConstraint+" AND " 
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.ISIN_FIELD + " = ? AND " 
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.SYMBOL_FIELD + " = ? AND " 
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.EVENTDEFID_FIELD + " = ? "
				+ " ORDER BY "+EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.DATE_FIELD+ " DESC ") {

					@Override
					public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
						EventValue eventValue = new AlertEventValue(
										rs.getDate(EVENTS.DATE_FIELD), 
										rs.getInt(EVENTS.EVENTDEFID_FIELD),
										rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
										rs.getString(EVENTS.MESSAGE_FIELD),
										rs.getString(EVENTS.ANALYSE_NAME));
						retour.add(eventValue);
					}
			
		};
		select.addValue(listEventName);
		select.addValue(stock.getIsin());
		select.addValue(stock.getSymbol());
		select.addValue(EventDefinition.SCREENER.getEventDefId());
		
		LOGGER.debug(select.getQuery());
		List<EventValue> eventValues = exectuteSelect(EventValue.class, select);
		if (eventValues.size() == 0) return null; 

		EventValue eventValue = null;
		for (int i = 0; i < eventValues.size(); i++) {
			if (eventValues.get(i).getDate().after(endDate)) {
				continue;
			} else {
				eventValue = eventValues.get(i);
				break;
			}
		}
		return eventValue;
	
	}

	/**
	 * Gets the last event date.
	 * 
	 * @return the last event date
	 */
	public Date getLastEventDateForAnalyse(String analyseName) {
		String q = new String("Select max(" + EVENTS.DATE_FIELD + ") from " + EVENTS.EVENTS_TABLE_NAME + " where "+EVENTS.ANALYSE_NAME + " like ? ");			
		Date retour;
		MyDBConnection scnx = this.getConnection(true);
		try {
			ResultSet rs;
			PreparedStatement pst;
			pst = scnx.getConn().prepareStatement(q);
			pst.setString(1,analyseName);
			rs = pst.executeQuery();
			Date d = (rs.next()) ? rs.getDate(1) : null;
			if (d != null) {
				retour = d;
				LOGGER.debug("Last event date in data base : " + d.toString());
			} else {
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				retour = df.parse("19700101");
				LOGGER.debug("No events in data base");
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			LOGGER.error("Query : " + q,e);
			retour = null;
		} catch (ParseException e) {
			LOGGER.error("Date formating ERROR while reading data base :" + e,e);
			retour = null;
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		return retour;
	}
	
	public void cleanEventsForAnalysisName(final String analyseName, final Date start, final Date end, EventDefinition ...eventDefinitions) {

		try {
			
			String eventDefConstraint = eventDefinitionConstraint(eventDefinitions);
			Query iq = new Query(DataSource.EVENTS.getDelete() + eventDefConstraint);
			iq.addValue(analyseName);
			iq.addValue(start);
			iq.addValue(end);
			executeUpdate(iq);
			
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
	}
	
	public void cleanEventsForAnalysisName(final String analyseName) {
		
		try {
			
			Query iq = new Query("DELETE FROM "+ EVENTS.EVENTS_TABLE_NAME + " WHERE "+EVENTS.ANALYSE_NAME+" = ?");
			iq.addValue(analyseName);
			executeUpdate(iq);
			
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
	}
	
	public void cleanEventsForAnalysisNameAndStock(final Stock stock, final String analyseName, final Date start, final Date end, EventDefinition ...eventDefinitions) {

		try {

			String eventDefConstraint = eventDefinitionConstraint(eventDefinitions);

			String preparedQuery = 
					"DELETE FROM "+ EVENTS.EVENTS_TABLE_NAME + " WHERE "+
					EVENTS.SYMBOL_FIELD+"= ? AND "+ EVENTS.ISIN_FIELD+"= ? AND "+EVENTS.ANALYSE_NAME+" = ? AND "+EVENTS.DATE_FIELD+" >= ? AND "+EVENTS.DATE_FIELD+" <= ?"+ eventDefConstraint;

			Query iq = new Query(preparedQuery);
			iq.addValue(stock.getSymbol());
			iq.addValue(stock.getIsin());
			iq.addValue(analyseName);
			iq.addValue(start);
			iq.addValue(end);
			executeUpdate(iq);
			
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
	}

	/**
	 * @param eventDefinitions
	 * @return
	 */
	private String eventDefinitionConstraint(EventDefinition... eventDefinitions) {
		if (eventDefinitions.length == 0) {
			return "";
		} else {
			String eventDefConstraint = " AND EVENTDEFID in ( ";
			String sep = " ";
			for (EventDefinition eventDefinition : eventDefinitions) {
				eventDefConstraint += sep + eventDefinition.getEventDefId();
				sep = " , ";
			}
			eventDefConstraint += " )";
			return eventDefConstraint;
		}
	}
	
	/**
	 * Exectute select.
	 * 
	 * @param query the q
	 * 
	 * @return the list< object>
	 * 
	 * @author Guillaume Thoreton
	 */
	public <T> List<T> exectuteSelect(Class<T> retClass,Query query) {
		return this.executeQuery(query,0);
	}

	/**
	 * Exectute select.
	 * 
	 * @param query the q
	 * @param maxRow the max row
	 * 
	 * @return the list< object>
	 * 
	 * @author Guillaume Thoreton
	 * @param isolationLevel 
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> executeQuery(Query query, int maxRow) {
		
		LOGGER.trace("Query : "+query.getQuery());
		LOGGER.trace("Params : "+query.getParameterValues());
		Long t0 = System.currentTimeMillis();
		
		ArrayList<T> retour = new ArrayList<T>();
		String sqlQueryString = query.getQuery();
		MyDBConnection scnx = this.getConnection(true);
		try {
			ResultSet rs;
			PreparedStatement pst;
			Connection conn = scnx.getConn();
			
			pst = conn.prepareStatement(sqlQueryString);
			pst.setMaxRows(maxRow);
			
			for (int i = 0; i < query.getParameterValues().size(); i++) {
				setObject(query, pst, i);
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				query.resultParse((List<Object>)retour, rs);
			}
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			LOGGER.error("Query : " + sqlQueryString,e);
			LOGGER.debug("",e);
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		
		LOGGER.trace("Execution time (s) : "+(System.currentTimeMillis()-t0)/1000);
		
		return retour;
	}
	
	private int executeUpdate(Query query) throws SQLException {

		LOGGER.trace("Query : "+query.getQuery());
		LOGGER.trace("Params : "+query.getParameterValues());
		Long t0 = System.currentTimeMillis();

		MyDBConnection scnx = this.getConnection(null);
		String sqlQueryString = query.getQuery();
		
		int rs = -1;	
		try {
			
			PreparedStatement pst;
			Connection conn = scnx.getConn();
			
			//Dead lock Debug
//			for (Object o : query.getParameterValues()) {
//				String param;
//				if (o instanceof Date) {
//					param = "'"+new SimpleDateFormat("yyyy-MM-dd").format(o)+"'";
//				} else if (o instanceof Number){
//					param = o.toString();
//				} else {
//					param = "'"+o.toString()+"'";
//				}
//				sqlQueryString = sqlQueryString.replaceFirst("\\?", param);
//			}
			
			pst = conn.prepareStatement(sqlQueryString);
			
			for (int i = 0; i < query.getParameterValues().size(); i++) {
				setObject(query, pst, i);
			}
			
			rs = pst.executeUpdate();
	
			pst.close();

		} catch (SQLException e) {
			LOGGER.error("Error in query :"+sqlQueryString, e);
			throw e;
		} finally {
			DataSource.realesePoolConnection(scnx);
		}

		LOGGER.trace("Execution time (s) : "+(System.currentTimeMillis()-t0)/1000);

		return rs;
	}


	// technic
	/**
	 * Gets the connection.
	 * 
	 * @param autocommit the autocommit
	 * 
	 * @return the connection
	 */
	private MyDBConnection getConnection(Boolean autocommit) {
		return DataSource.getConnectionFromPool();
	}

	/**
	 * Commit main.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void commitMain() {
		try {
			this.connectNotCommited.commit();
		} catch (SQLException e) {
			LOGGER.debug("", e);
			throw new RuntimeException("Can't commit Query. Sorry. :" + e + "\n REASON : " + e.getNextException());
		}
	}

	/**
	 * Roll back main.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void rollBackMain() {
		try {
			this.connectNotCommited.rollback();
		} catch (SQLException e) {
			LOGGER.debug("", e);
			throw new RuntimeException("Can't roolback Query. Sorry. :" + e + "\n REASON : " + e.getNextException());
		}
	}

	/**
	 * Connect.
	 * 
	 * @param autocommit the autocommit
	 * 
	 * @return the connection
	 * 
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	private Connection connect(boolean autocommit) throws RestartServerException { 
		String connectionURL = null;
		Connection conn;
		try {

			// Set up the connection
			connectionURL = connectionUrl();
			conn = DriverManager.getConnection(connectionURL);
			conn.setAutoCommit(autocommit);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			LOGGER.debug("Db connection isolation : "+conn.getTransactionIsolation()+". Autocommit : "+conn.getAutoCommit());
			LOGGER.debug("Db connexion 	url : " + connectionURL);
		} 

		catch (SQLException e) {
			LOGGER.debug("SQL ERROR; Conneciton URL : " + connectionURL);
			LOGGER.debug("Data Base not started !? :" + e);
			LOGGER.debug("cause : " + e.getCause() + "\n next : " + e.getNextException(),e);
			nbExceptions++;
			if (nbExceptions < PoolSemaphore.NUMBER_OF_CONNEXION_TRY) {
				throw new RestartServerException("Data Base not started or already in use.");
			} else {
				LOGGER.error("Can't start Data Base!?",e);
			}
			return null;
		}
		nbExceptions = 0;
		return conn;
	}

	/**
	 * @return
	 */
	private String connectionUrl() {
		String connectionURL;
		connectionURL = "jdbc:" + MainPMScmd.getPrefs().get("software", "derby");
		connectionURL = connectionURL + ":" + MainPMScmd.getPrefs().get("dbpath", "derby/");
		connectionURL = connectionURL + MainPMScmd.getPrefs().get("database", "premiummarkets");
		if (!MainPMScmd.getPrefs().get("username", "nouserspecified").equals("nouserspecified"))
			connectionURL += "?user=" + MainPMScmd.getPrefs().get("username", "nouserspecified") + "&password=" + MainPMScmd.getPrefs().get("password", "password");
		return connectionURL;
	}

	/**
	 * Execute block.
	 * 
	 * @param qL the q l
	 * @param preparedQuery the prepared query
	 * 
	 * @return the int[]
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public int[] executeBlock(List<Validatable> qL, String preparedQuery) throws SQLException {
		return this.executeBlock(this.getConnection(null), qL, preparedQuery);
	}

	/**
	 * Execute block.
	 * 
	 * @param sdbcnx the sdbcnx
	 * @param qL the q l
	 * @param preparedQuery the prepared query
	 * 
	 * @return the int[]
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 */
	private int[] executeBlock(MyDBConnection sdbcnx, Collection<Validatable> qL, String preparedQuery) throws SQLException {
		int[] resReq = {};
		String debug = "";
		try {
			PreparedStatement pst = sdbcnx.getConn().prepareStatement(preparedQuery);
			Iterator<Validatable> qIt = qL.iterator();
			LOGGER.debug("Number of query in batch :" + qL.size() + " for Statement :" + preparedQuery);
			while (qIt.hasNext()) {
				debug = "";
				Query query = ((Validatable) qIt.next()).toDataBase();
				for (int i = 0; i < query.getParameterValues().size(); i++) {
					debug = debug + "," + query.getParameterValues().get(i);
					this.setObject(query, pst, i);
				}
				LOGGER.trace("Parameters : "+debug);
				pst.addBatch();
			}
			if (qL.size() > 0) resReq = pst.executeBatch();
			
		} catch (SQLException e) {
			if (!e.getCause().getMessage().contains("duplicate key"))  {
				LOGGER.error("Error while doing insert : " + debug, e);
			} else {
				LOGGER.error("Duplicate key while doing insert : " + debug, e);
			}
			throw e;
		} finally {
			DataSource.realesePoolConnection(sdbcnx);
		}
		return resReq;
	}
	
	public int[] executeBlockWithTimeStamp(Collection<Validatable> qL, String preparedQuery) throws SQLException {
		
		MyDBConnection sdbcnx = this.getConnection(null);
		int[] resReq = {};
		String debug = "";
		try {
			PreparedStatement pst = sdbcnx.getConn().prepareStatement(preparedQuery);
			Iterator<Validatable> qIt = qL.iterator();
			LOGGER.debug("Number of query in batch :" + qL.size() + " for Statement :" + preparedQuery);
			while (qIt.hasNext()) {
				debug = "";
				Query query = ((Validatable) qIt.next()).toDataBase();
				for (int i = 0; i < query.getParameterValues().size(); i++) {
					if (LOGGER.isDebugEnabled()) debug = debug + "," + query.getParameterValues().get(i);
					this.setObjectWithTimeStamp(query, pst, i);
				}
				LOGGER.trace("Parameters : "+debug);
				pst.addBatch();
			}
			if (qL.size() > 0) resReq = pst.executeBatch();
			
		} catch (SQLException e) {
			if (!e.getCause().getMessage().contains("duplicate key"))  {
				LOGGER.error("Error while doing " + preparedQuery + ". Details (in debug only): " + debug, e);
			} else {
				LOGGER.error("Duplicate key while doing " + preparedQuery + ". Details (in debug only): " + debug, e);
			}
			throw e;
		} finally {
			DataSource.realesePoolConnection(sdbcnx);
		}
		return resReq;
	}
	
	/**
	 * Sets the object.
	 * 
	 * @param query the q
	 * @param pst the pst
	 * @param i the i
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 */
	private void setObject(Query query, PreparedStatement pst, int i) throws SQLException {
		Object o = query.getParameterValues().get(i);
		if (o instanceof java.util.Date) {
			java.sql.Date sqld = new java.sql.Date(((java.util.Date)o).getTime());
			pst.setDate(i + 1, sqld);
		}
		else
			pst.setObject(i + 1, o);
	}
	
	private void setObjectWithTimeStamp(Query query, PreparedStatement pst, int i) throws SQLException {
		Object o = query.getParameterValues().get(i);
		if (o instanceof java.util.Date) {
			java.sql.Timestamp sqld = new java.sql.Timestamp(((java.util.Date)o).getTime());
			pst.setTimestamp(i + 1, sqld);
		}
		else
			pst.setObject(i + 1, o);
	}

	/**
	 * Execute update block.
	 * 
	 * @param qL the q l
	 * @param preparedQuery the prepared query
	 * 
	 * @return the int[]
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public int[] executeUpdateBlock(ArrayList<Validatable> qL, String preparedQuery, ArrayList<Validatable> s4UqL, String selectForUpDateQ) throws SQLException {
		
//		for (Validatable validatable : s4UqL) {
//			Query query = validatable.toDataBase();
//			query.setQuery(selectForUpDateQ);
//			exectuteSelect(Object.class, query);
//		}
		return executeBlock(qL, preparedQuery);
	}

	/**
	 * Execute long batch.
	 * 
	 * @param queries the queries
	 * @param statement the statement
	 * @param tablesLocked the tables locked
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void executeLongBatch(Collection<Validatable> queries, String statement, List<TableLocker> tablesLocked) throws SQLException {
		
		MyDBConnection sdbcnx = this.getConnection(null);
		try {
			Statement s = sdbcnx.getConn().createStatement();
			for (int i = 0; i < tablesLocked.size(); i++) {
				if (!tablesLocked.get(i).getLockModeValue().equals(TableLocker.LockMode.NOLOCK)) {
					s.execute("LOCK TABLE " + tablesLocked.get(i).getTableName()+ tablesLocked.get(i).getLockModeValue().getLockMode());
					LOGGER.debug("Lock on table : " + tablesLocked.get(i).getTableName() + " : "+ tablesLocked.get(i).getLockModeValue().getLockMode());
				}
			}
			DataSource.getInstance().executeBlock(sdbcnx, queries, statement);
		} catch (SQLException e) {
			if (!e.getCause().getMessage().contains("duplicate key"))  {
				LOGGER.error("Insertion exception :" + e,e);
			}
			throw e;
		} finally {
			DataSource.realesePoolConnection(sdbcnx);
		}
	}

	/**
	 * The Class QUOTATIONS.
	 * 
	 * @author Guillaume Thoreton
	 */
	public static class QUOTATIONS {
		
		/** The TABL e_ name. */
		public static String TABLE_NAME;
		
		/** The SYMBO l_ field. */
		public static String SYMBOL_FIELD;
		
		/** The ISI n_ field. */
		public static String ISIN_FIELD;
		
		/** The DAT e_ field. */
		public static String DATE_FIELD;
		
		/** The DA y_ ope n_ field. */
		public static String DAY_OPEN_FIELD;
		
		/** The DA y_ clos e_ field. */
		public static String DAY_CLOSE_FIELD;
		
		/** The DA y_ hig h_ field. */
		public static String DAY_HIGH_FIELD;
		
		/** The DA y_ lo w_ field. */
		public static String DAY_LOW_FIELD;
		
		/** The DA y_ volum e_ field. */
		public static String DAY_VOLUME_FIELD;
		
		/** The CURRENC y_ field. */
		public static String CURRENCY_FIELD;

		/**
		 * Gets the iNSERT.
		 * 
		 * @return the iNSERT
		 */
		public static String getINSERT() {
			return "INSERT INTO " + QUOTATIONS.TABLE_NAME + " ( "+ QUOTATIONS.DATE_FIELD + " , "
					+ QUOTATIONS.DAY_OPEN_FIELD + " , " + QUOTATIONS.DAY_HIGH_FIELD + " , " + QUOTATIONS.DAY_LOW_FIELD + " , "
					+ QUOTATIONS.DAY_CLOSE_FIELD + " , " + QUOTATIONS.DAY_VOLUME_FIELD + " , " + QUOTATIONS.CURRENCY_FIELD + " , "
					+ QUOTATIONS.SYMBOL_FIELD + " , " + QUOTATIONS.ISIN_FIELD + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}

		/**
		 * Gets the dELETE.
		 * 
		 * @return the dELETE
		 */
		public static String getDELETE() {
			return "DELETE FROM " + QUOTATIONS.TABLE_NAME + " where " + QUOTATIONS.SYMBOL_FIELD + " = ? AND " + QUOTATIONS.ISIN_FIELD+ " = ? ";
		}

		/**
		 * Gets the uPDATEREFERENCE.
		 * 
		 * @return the uPDATEREFERENCE
		 */
		public static String getUPDATEREFERENCE() {
			return "UPDATE " + QUOTATIONS.TABLE_NAME + " set " + QUOTATIONS.SYMBOL_FIELD + " = ?  where " + QUOTATIONS.ISIN_FIELD+ " = ? ";
		}

		/**
		 * Gets the date param.
		 * 
		 * @param q the q
		 * 
		 * @return the date param
		 */
		public static Date getDateParam(Query q) {
			return (Date) q.getParameterValues().get(0);
		}

		/**
		 * Gets the insert cols.
		 * 
		 * @return the insert cols
		 */
		public static List<String> getInsertCols() {
			List<String> l = new ArrayList<String>();
			//l.add(DataSource.QUOTATIONS.SICOVAM_FIELD);
			l.add(DataSource.QUOTATIONS.DATE_FIELD);
			l.add(DataSource.QUOTATIONS.DAY_OPEN_FIELD);
			l.add(DataSource.QUOTATIONS.DAY_HIGH_FIELD);
			l.add(DataSource.QUOTATIONS.DAY_LOW_FIELD);
			l.add(DataSource.QUOTATIONS.DAY_CLOSE_FIELD);
			l.add(DataSource.QUOTATIONS.DAY_VOLUME_FIELD);
			l.add(DataSource.QUOTATIONS.CURRENCY_FIELD);
			l.add(DataSource.QUOTATIONS.SYMBOL_FIELD);
			l.add(DataSource.QUOTATIONS.ISIN_FIELD);
			return l;
		}
	}

	/**
	 * The Class SHARES.
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	public static class SHARES {
		
		/** The TABLE _ name. */
		public static String TABLE_NAME;
		/** The SYMBOL _ field. */
		public static String SYMBOL_FIELD;
		/** The ISIN _ field. */
		public static String ISIN_FIELD;
		/** The NAME _ field. */
		public static String NAME_FIELD;
		/** The REMOVABLE. */
		public static String REMOVABLE;
		/** The CATEGORY. */
		public static String CATEGORY;
		/** The LASTQUOTE. */
		public static String LASTQUOTE;
		/** The PROVIDER. */
		public static String QUOTATIONPROVIDER;
		/** The MARKET. */
		public static String MARKET;
		public static String SECTOR_HINT = "SECTOR_HINT";
		public static String TRADING_MODE = "TRADING_MODE";
		public static String CAPITALISATION = "CAPITALISATION";

		/**
		 * Gets the iNSERT.
		 * 
		 * @return the iNSERT
		 */
		@Deprecated
		public static String getINSERT() {
			return "INSERT INTO " + SHARES.TABLE_NAME + " ( " 
					+ SHARES.SYMBOL_FIELD + " , " + SHARES.ISIN_FIELD + " , "
					+ SHARES.NAME_FIELD + " , " + SHARES.REMOVABLE + " , " 
					+ SHARES.CATEGORY + " , " + SHARES.LASTQUOTE + " , "
					+ SHARES.QUOTATIONPROVIDER + " , " + SHARES.MARKET + " , "
					+ SHARES.SECTOR_HINT + " , " + SHARES.TRADING_MODE + " , "
					+ SHARES.CAPITALISATION + "  ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		}

		/**
		 * Gets the dELETE.
		 * 
		 * @return the dELETE
		 */
		@Deprecated
		public static String getDELETE() {
			return "DELETE FROM " + SHARES.TABLE_NAME + " where " + SHARES.SYMBOL_FIELD + " = ? AND " + SHARES.ISIN_FIELD + " = ? ";
		}

		/**
		 * Gets the uPDATELASTQUOTE.
		 * 
		 * @return the uPDATELASTQUOTE
		 */
		@Deprecated
		public static String getUPDATELASTQUOTE() {
			return "UPDATE " + SHARES.TABLE_NAME + " set " + SHARES.LASTQUOTE + " = ? " + " where " + SHARES.SYMBOL_FIELD
					+ " = ? AND " + SHARES.ISIN_FIELD + " = ? ";
		}

		/**
		 * Gets the uPDATELASTQUOTEANDNAME.
		 * 
		 * @return the uPDATELASTQUOTEANDNAME
		 */
		@Deprecated
		public static String getUPDATELASTQUOTEANDNAME() {
			return "UPDATE " + SHARES.TABLE_NAME + " set " + SHARES.LASTQUOTE + " = ? ," + SHARES.NAME_FIELD + " = ? "
					+ " where " + SHARES.SYMBOL_FIELD + " = ? AND " + SHARES.ISIN_FIELD + " = ? ";
		}

		/**
		 * Gets the uPDATEREFERENCE.
		 * 
		 * @return the uPDATEREFERENCE
		 */
		@Deprecated
		public static String getUPDATEREFERENCE() {
			return "UPDATE " + SHARES.TABLE_NAME + " set " + SHARES.SYMBOL_FIELD + " = ? ," + SHARES.NAME_FIELD + " = ? "
					+ " where " + SHARES.ISIN_FIELD + " = ? ";
		}
		
	}

	/**
	 * The Class PORTFOLIO.
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	public static class PORTFOLIO {
		
		/** The PORTFOLI o_ tabl e_ name. */
		public static String TABLE_NAME;
		
		/** The SYMBO l_ field. */
		public static String SYMBOL_FIELD;
		
		public static String DATE_FIELD;
		
		/** The QUANTI y_ field. */
		public static String QUANTIY_FIELD;
		
		/** The CASHI n_ field. */
		public static String CASHIN_FIELD;
		
		/** The CASHOU t_ field. */
		public static String CASHOUT_FIELD;
		
		/** The NAM e_ field. */
		public static String NAME_FIELD;
		
		/** The MONITO r_ field. */
		public static String MONITOR_FIELD;

		 
	}

	/**
	 * The Class EVENTS.
	 * 
	 * @author Guillaume Thoreton
	 */
	public static class EVENTS {
		
		public static String EVENTS_TABLE_NAME;
		public static String SYMBOL_FIELD;
		public static String ISIN_FIELD;
		public static String ACCURACY_FIELD;
		public static String DATE_FIELD;
		public static String EVENTDEFID_FIELD;
		public static String EVENTDEF_FIELD;
		public static String EVENTDEFEXTENSION_FIELD;
		public static String EVENTTYPE_FIELD;
		public static String ANALYSE_NAME;
		public static String MESSAGE_FIELD ="MESSAGE";
		

		/**
		 * Gets the iNSERT.
		 * 
		 * @return the iNSERT
		 */
		public static String getINSERT() {
			return "INSERT INTO " + EVENTS.EVENTS_TABLE_NAME + " ( " + EVENTS.SYMBOL_FIELD + ", " + EVENTS.ISIN_FIELD + ", "
					+ EVENTS.ACCURACY_FIELD + " , " + EVENTS.DATE_FIELD + " , " + EVENTS.EVENTDEFID_FIELD + " , "
					+ EVENTS.EVENTDEF_FIELD + " , " + EVENTS.EVENTDEFEXTENSION_FIELD + " , " + EVENTS.EVENTTYPE_FIELD + "," + EVENTS.MESSAGE_FIELD + "," + EVENTS.ANALYSE_NAME 
					+"  ) VALUES (?,?,?,?,?,?,?,?,?,?)";
		}

		/**
		 * Gets the uPDATE.
		 * 
		 * @return the uPDATE
		 */
		public static String getUPDATE() {
			return "UPDATE " + EVENTS.EVENTS_TABLE_NAME + " SET " 
			+ EVENTS.ACCURACY_FIELD + "= ? , " 
			+ EVENTS.EVENTDEF_FIELD + "= ? , " + EVENTS.EVENTTYPE_FIELD + " = ? , " + EVENTS.MESSAGE_FIELD + "= ? "
					+ " WHERE " 
					+ EVENTS.SYMBOL_FIELD + " = ? AND "+ EVENTS.ISIN_FIELD + " = ? AND " 
					+ EVENTS.DATE_FIELD + " = ? AND " + EVENTS.EVENTDEFID_FIELD + " = ? AND " + EVENTS.EVENTDEFEXTENSION_FIELD + " = ? AND "
					+ EVENTS.ANALYSE_NAME + " = ?";
		}
		
		public static String getDelete() {
			return "DELETE FROM "+ EVENTS.EVENTS_TABLE_NAME + " WHERE "+EVENTS.ANALYSE_NAME+" = ? AND "+EVENTS.DATE_FIELD+" >= ? AND "+EVENTS.DATE_FIELD+" <= ?";
		}
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#connect(int)
	 */
	public SourceClient connect(int connectionId) throws RestartServerException {
		
		MyDBConnection ret = null;
		Connection c = this.connect(false);
		ret = new MyDBConnection(c);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#getThreadPool()
	 */
	public PoolSemaphore getThreadPool() {
		return threadPool;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#restartSource(int)
	 */
	public int restartSource(int connectionId) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#shutdownSource(com.finance.pms.threads.SourceClient, int)
	 */
	public void shutdownSource(SourceClient sourceClient, int connectionId) {
		try {
			((MyDBConnection) sourceClient).getConn().close();
		} catch (SQLException e) {
			LOGGER.error("ERROR : couldn't close DB connection", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.finance.pms.db.HttpSource#stopThreads()
	 */
	/**
	 * Stop threads.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void stopThreads() {
		this.threadPool.stopThreads();
		
		LOGGER.info("All Sql Done ... Bye");
		DataSource.singleton = null;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	public ShareDAO getShareDAO() {
		return shareDAO;
	}

}
