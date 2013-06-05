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
package com.finance.pms.datasources.db;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
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
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketValuation;
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
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.WeatherEventKey;
import com.finance.pms.events.calculation.DateFactory;
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

	public static String DB_PATH_NNAME;


	/**
	 * Instantiates a new data source.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 */
	public DataSource() {
		
		QUOTATIONS.TABLE_NAME = MainPMScmd.getPrefs().get("quotations", "QUOTATIONS");
		QUOTATIONS.SYMBOL_FIELD = MainPMScmd.getPrefs().get("symbol", "SYMBOL");
		QUOTATIONS.ISIN_FIELD = MainPMScmd.getPrefs().get("isin", "ISIN");
		QUOTATIONS.DATE_FIELD = MainPMScmd.getPrefs().get("date", "DATE");
		QUOTATIONS.DAY_OPEN_FIELD = MainPMScmd.getPrefs().get("open", "OPENVALUE");
		QUOTATIONS.DAY_CLOSE_FIELD = MainPMScmd.getPrefs().get("close", "CLOSEVALUE");
		QUOTATIONS.DAY_HIGH_FIELD = MainPMScmd.getPrefs().get("high", "HIGH");
		QUOTATIONS.DAY_LOW_FIELD = MainPMScmd.getPrefs().get("low", "LOW");
		QUOTATIONS.DAY_VOLUME_FIELD = MainPMScmd.getPrefs().get("volume", "VOLUME");
		QUOTATIONS.CURRENCY_FIELD = MainPMScmd.getPrefs().get("currency", "CURRENCY");
		SHARES.TABLE_NAME = MainPMScmd.getPrefs().get("shares", "SHARES");
		SHARES.ISIN_FIELD = MainPMScmd.getPrefs().get("lookup.isin", "ISIN");
		SHARES.SYMBOL_FIELD = MainPMScmd.getPrefs().get("lookup.symbol", "SYMBOL");
		SHARES.NAME_FIELD = MainPMScmd.getPrefs().get("lookup.name", "NAME");
		SHARES.REMOVABLE = MainPMScmd.getPrefs().get("lookup.removable", "REMOVABLE");
		SHARES.CATEGORY = MainPMScmd.getPrefs().get("lookup.category", "CATEGORY");
		SHARES.LASTQUOTE = MainPMScmd.getPrefs().get("lookup.lastquote", "LASTQUOTE");
		SHARES.QUOTATIONPROVIDER = MainPMScmd.getPrefs().get("lookup.provider", "QUOTATIONPROVIDER");
		SHARES.MARKET = MainPMScmd.getPrefs().get("lookup.market", "MARKETLISTPROVIDER");
		PORTFOLIO.TABLE_NAME = MainPMScmd.getPrefs().get("portfolio.table", "PORTFOLIO");
		PORTFOLIO.SYMBOL_FIELD = MainPMScmd.getPrefs().get("portfolio.symbol", "SYMBOL");
		PORTFOLIO.QUANTIY_FIELD = MainPMScmd.getPrefs().get("portfolio.quantity", "QUANTITY");
		PORTFOLIO.DATE_FIELD = MainPMScmd.getPrefs().get("portfolio.buydate", "BUYDATE");
		PORTFOLIO.CASHIN_FIELD = MainPMScmd.getPrefs().get("portfolio.cashin", "CASHIN");
		PORTFOLIO.CASHOUT_FIELD = MainPMScmd.getPrefs().get("portfolio.cashout", "CASHOUT");
		PORTFOLIO.NAME_FIELD = MainPMScmd.getPrefs().get("portfolio.name", "NAME");
		PORTFOLIO.MONITOR_FIELD = MainPMScmd.getPrefs().get("portfolio.monitor", "MONITOR");
		EVENTS.EVENTS_TABLE_NAME = MainPMScmd.getPrefs().get("events.table", "EVENTS");
		EVENTS.SYMBOL_FIELD = MainPMScmd.getPrefs().get("events.symbol", "SYMBOL");
		EVENTS.ISIN_FIELD = MainPMScmd.getPrefs().get("events.isin", "ISIN");
		EVENTS.ACCURACY_FIELD = MainPMScmd.getPrefs().get("events.accuracy", "ACCURACY");
		EVENTS.DATE_FIELD = MainPMScmd.getPrefs().get("events.date", "DATE");
		EVENTS.EVENTDEFID_FIELD = MainPMScmd.getPrefs().get("events.eventdefid", "EVENTDEFID");
		EVENTS.EVENTDEF_FIELD = MainPMScmd.getPrefs().get("events.eventdef", "EVENTDEF");
		EVENTS.EVENTDEFEXTENSION_FIELD = "EVENTDEFEXTENSION";
		EVENTS.EVENTTYPE_FIELD = MainPMScmd.getPrefs().get("events.type", "EVENTTYPE");
		EVENTS.ANALYSE_NAME = MainPMScmd.getPrefs().get("events.type", "ANALYSENAME");
		
		if (singleton == null) {
			LOGGER.debug("Number of Long batch DB Threads :" + new Integer(MainPMScmd.getPrefs().get("db.poolsize", "10")));
			threadPool = new PoolSemaphore((new Integer(MainPMScmd.getPrefs().get("db.poolsize", "10"))).intValue(), this, false);
			singleton = this;
		}
	}
	
	public static String dbPathNname() {
		return DB_PATH_NNAME;
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
			//System.exit(1);
		} catch (TimeoutException e) {
			LOGGER.error("Unable to get Connection. Is data base started? Thread lock ?", e);
		}
		LOGGER.error("Unable to get Connection. For one reason or the other ...");
		//System.exit(1);
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
			LOGGER.error("ERROR releasing connection : ", e);
			LOGGER.debug(e.getCause());
			LOGGER.debug(e.getNextException());
			
			try {
				conn = DataSource.getInstance().getThreadPool().reconnectSource(conn);
			} catch (Exception e1) {
				LOGGER.error(e,e);
			}
			
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
				retour = DateFactory.dateAtZero();
				LOGGER.warn("No value in data base : " + stock + " is a new ticker");
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			LOGGER.error("Query : " +  sqlQuery + "Param : " + stock,e);
			retour = null;
		} finally {
			DataSource.realesePoolConnection(scnx);
		}
		return retour;
	}
	
	
	public Set<Stock> loadStocksForCurrentShareList() {
		String currentMarket =  MainPMScmd.getPrefs().get("quotes.listprovider", "euronext");
		Providers provider = Providers.getInstance(currentMarket);
		return loadStocksList(currentMarket + Indice.formatSet(provider.getIndices()));
	}

	public  Set<Stock> loadStocksList(String shareList) {
		if (shareList == null) {
			throw new IncompleteArgumentException("shareList");
		}
		
		SharesList sharesList = portfolioDAO.loadShareList(shareList);
		return sharesList.toStocksSet();
		
	}
	
	public Collection<Stock> loadAllStocks() {
		return shareDAO.loadAllStocks();
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
				Stock st = new Stock(rs.getString(2), rs.getString(1),StockCategories.DEFAULT_CATEGORY,new SymbolMarketQuotationProvider(),new MarketValuation(Market.UNKNOWN));
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
							//Market.valueOf(rs.getString(SHARES.MARKET).trim()),
							new MarketValuation(Market.valueOf(rs.getString(SHARES.MARKET).trim()), rs.getBigDecimal(SHARES.CURRENCYFACTOR), Currency.valueOf(rs.getString(SHARES.CURRENCY).trim())),
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
	public List<SymbolEvents> loadEventsByDate(String eventsTableName, Date startDate, Date endDate, Set<EventInfo> eventDefinitions, String... eventListNames) {
		
		String eventListConstraint = " ( ";
		String sep = "'";
		for (String eventlist : eventListNames) {
			eventListConstraint = eventListConstraint + sep + eventlist;
			sep = "' , '";
		}
		eventListConstraint += "' ) ";
		
		Query select = new Query(
				"SELECT "
				+ eventsTableName + ".*," 
				+ SHARES.TABLE_NAME+ ".*" 
				+ " FROM " + eventsTableName + "," + SHARES.TABLE_NAME 
				+ " WHERE "
				+ eventsTableName +"."+ EVENTS.DATE_FIELD + " >= ? AND " 
				+ eventsTableName +"."+ EVENTS.DATE_FIELD + " <= ? AND " 
				+ eventsTableName +"."+ EVENTS.ANALYSE_NAME + " in "+eventListConstraint
				+ eventDefinitionConstraint(eventDefinitions)+ " AND "
				+ eventsTableName +"."+ EVENTS.EVENTTYPE_FIELD + " <> '"+EventType.INFO.getEventTypeChar()+"' AND " 
				+ SHARES.TABLE_NAME + "."+ SHARES.SYMBOL_FIELD + "=" + eventsTableName +"."+ EVENTS.SYMBOL_FIELD + " AND "
				+ SHARES.TABLE_NAME + "." + SHARES.ISIN_FIELD + "=" + eventsTableName +"."+ EVENTS.ISIN_FIELD ) {
			
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
							new MarketValuation(Market.valueOf(rs.getString(SHARES.MARKET).trim()), rs.getBigDecimal(SHARES.CURRENCYFACTOR), Currency.valueOf(rs.getString(SHARES.CURRENCY).trim())),
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
		
		LOGGER.debug(select.getQuery()+" with startDate : "+startDate+ " and endDate :	"+endDate);
		List<? extends Object> lret = exectuteSelect(Object.class, select);
		
		return (List<SymbolEvents>) lret;
	}
	

	public SymbolEvents loadEventsByDate(String eventsTableName, final Stock stock, Date startDate, Date endDate, Set<EventInfo> eventDefinitions, String... eventListNames) {
		
		String eventListConstraint = " ( ";
		{
			String sep = "'";
			for (String eventlist : eventListNames) {
				eventListConstraint = eventListConstraint + sep + eventlist;
				sep = "' , '";
			}
			eventListConstraint += "' ) ";
		}
		
		Query select = new Query(
				"SELECT "
				+ "*"
				+ " FROM " + eventsTableName
				+ " WHERE "
				+ EVENTS.DATE_FIELD + " >= ? AND " 
				+ EVENTS.DATE_FIELD + " <= ? AND " 
				+ EVENTS.ANALYSE_NAME + " in "+eventListConstraint
				+ eventDefinitionConstraint(eventDefinitions)+ " AND "
				+ EVENTS.EVENTTYPE_FIELD + " <> '"+EventType.INFO.getEventTypeChar()+"' AND " 
				+ EVENTS.SYMBOL_FIELD + " = ? AND "
				+ EVENTS.ISIN_FIELD+" = ?") {
			
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
		
		Integer eventDefId = rs.getInt(EVENTS.EVENTDEFID_FIELD);
		EventKey eventKey;
		EventValue eventValue;
		
		try {
			if (EventDefinition.ALERTTHRESHOLD.equals(EventDefinition.valueOf(eventDefId))) {
				eventKey = new AlertEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getString(EVENTS.EVENTDEF_FIELD).trim(), rs.getString(EVENTS.EVENTTYPE_FIELD).trim(), rs.getString(EVENTS.EVENTDEFEXTENSION_FIELD).trim());
				eventValue = new AlertEventValue(rs.getDate(EVENTS.DATE_FIELD), 
							rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
							rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
							rs.getString(EVENTS.MESSAGE_FIELD),
							rs.getString(EVENTS.ANALYSE_NAME));
			} else if (EventDefinition.SCREENER.equals(EventDefinition.valueOf(eventDefId))) {
				eventKey = new StandardEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getString(EVENTS.EVENTDEF_FIELD).trim(), rs.getString(EVENTS.EVENTTYPE_FIELD).trim());
				eventValue = new AlertEventValue(rs.getDate(EVENTS.DATE_FIELD), 
						rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
						rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
						rs.getString(EVENTS.MESSAGE_FIELD),
						rs.getString(EVENTS.ANALYSE_NAME));
			} else if (EventDefinition.WEATHER.equals(EventDefinition.valueOf(eventDefId))) {
				eventKey = new WeatherEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getString(EVENTS.EVENTDEF_FIELD).trim(), rs.getString(EVENTS.EVENTTYPE_FIELD).trim(), rs.getString(EVENTS.EVENTDEFEXTENSION_FIELD).trim());
				eventValue = new StandardEventValue(rs.getDate(EVENTS.DATE_FIELD), 
						rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
						rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
						rs.getString(EVENTS.MESSAGE_FIELD),
						rs.getString(EVENTS.ANALYSE_NAME));
			} else if (EventDefinition.PARAMETERIZED.equals(EventDefinition.valueOf(eventDefId))) {
				eventKey = new ParameterizedEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getString(EVENTS.EVENTDEF_FIELD).trim(), rs.getString(EVENTS.EVENTTYPE_FIELD).trim());
				eventValue = new StandardEventValue(rs.getDate(EVENTS.DATE_FIELD), 
						rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
						rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
						rs.getString(EVENTS.MESSAGE_FIELD),
						rs.getString(EVENTS.ANALYSE_NAME));
			} else {
				eventKey = new StandardEventKey(rs.getDate(EVENTS.DATE_FIELD), rs.getString(EVENTS.EVENTDEF_FIELD).trim(), rs.getString(EVENTS.EVENTTYPE_FIELD).trim().toLowerCase());
				eventValue = new StandardEventValue(rs.getDate(EVENTS.DATE_FIELD), 
							rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
							rs.getString(EVENTS.EVENTTYPE_FIELD).trim().toLowerCase(),
							rs.getString(EVENTS.MESSAGE_FIELD),
							rs.getString(EVENTS.ANALYSE_NAME));
			}
			
			sers.addEventResultElement(eventKey, eventValue, rs.getString(EVENTS.EVENTDEF_FIELD).trim());
			
		} catch (NoSuchFieldException e) {
			LOGGER.warn("Event definition not found in this configuration (db.properties and user calculators) : "+e);
		}
		
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
					+ EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.EVENTDEF_FIELD+","
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
					+ EVENTS.EVENTS_TABLE_NAME + "." + EVENTS.EVENTDEF_FIELD + " = ? "
				+ " ORDER BY "+EVENTS.EVENTS_TABLE_NAME+"."+EVENTS.DATE_FIELD+ " DESC ") {

					@Override
					public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
						try {
							EventValue eventValue = new AlertEventValue(
											rs.getDate(EVENTS.DATE_FIELD), 
											rs.getString(EVENTS.EVENTDEF_FIELD).trim(),
											rs.getString(EVENTS.EVENTTYPE_FIELD).trim(),
											rs.getString(EVENTS.MESSAGE_FIELD),
											rs.getString(EVENTS.ANALYSE_NAME));
							retour.add(eventValue);
						} catch (NoSuchFieldException e) {
							LOGGER.warn("Event definition not found in this configuration : "+e);
						}
					}
			
		};
		select.addValue(listEventName);
		select.addValue(stock.getIsin());
		select.addValue(stock.getSymbol());
		select.addValue(EventDefinition.SCREENER.getEventDefinitionRef());
		
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
	
	public void cleanEventsForIndicators(String eventsTableName, String analyseName, Date start, Date end, EventInfo ...eventDefinitions) {

		try {
			
			String eventDefConstraint = eventDefinitionConstraint(eventDefinitions);
			Query iq = new Query(DataSource.EVENTS.getDelete(eventsTableName) + eventDefConstraint);
			iq.addValue(analyseName);
			iq.addValue(start);
			iq.addValue(end);
			executeUpdate(iq, 600);
			
		} catch (SQLException e) {
			LOGGER.warn("Ignoring deletion error : ",e);
		}
	}
	
	public void cleanEventsForAnalysisName(String eventTableName, String analyseName) {
		
		try {
			
			Query iq = new Query("DELETE FROM "+ eventTableName + " WHERE "+EVENTS.ANALYSE_NAME+" = ?");
			iq.addValue(analyseName);
			executeUpdate(iq, 600);
			
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
	}
	
	public void cleanEventsForAnalysisNameAndStock(String eventTableName, Stock stock, String analyseName, Date start, Date end, EventInfo ...eventDefinitions) {

		try {

			String eventDefConstraint = eventDefinitionConstraint(eventDefinitions);

			String preparedQuery = 
					"DELETE FROM "+ eventTableName + " WHERE "+
					EVENTS.SYMBOL_FIELD+"= ? AND "+ EVENTS.ISIN_FIELD+"= ? AND "+EVENTS.ANALYSE_NAME+" = ? AND "+EVENTS.DATE_FIELD+" >= ? AND "+EVENTS.DATE_FIELD+" <= ?"+ eventDefConstraint;

			Query iq = new Query(preparedQuery);
			iq.addValue(stock.getSymbol());
			iq.addValue(stock.getIsin());
			iq.addValue(analyseName);
			iq.addValue(start);
			iq.addValue(end);
			executeUpdate(iq, 600);
			
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
	}
	
	
	private String eventDefinitionConstraint(Set<EventInfo> eventDefinitions) {
		if (eventDefinitions !=null) return eventDefinitionConstraint(eventDefinitions.toArray(new EventInfo[0]));
		return "";
	}

	/**
	 * @param eventDefinitions
	 * @return
	 */
	private String eventDefinitionConstraint(EventInfo... eventDefinitions) {
		if (eventDefinitions.length == 0) {
			return "";
		} else {
			String eventDefConstraint = " AND "+EVENTS.EVENTDEF_FIELD+" in ( ";
			String sep = " ";
			for (EventInfo eventDefinition : eventDefinitions) {
				eventDefConstraint += sep + "'" + eventDefinition.getEventDefinitionRef()+ "'";
				sep = " , ";
			}
			eventDefConstraint += " ) ";
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
	
	public int executeUpdate(Query query, int queryTimeOutInSec) throws SQLException {

		LOGGER.trace("Query : "+query.getQuery());
		LOGGER.trace("Params : "+query.getParameterValues());
		Long t0 = System.currentTimeMillis();

		MyDBConnection scnx = this.getConnection(null);
		String sqlQueryString = query.getQuery();
		
		int rs = -1;	
		try {
			
			PreparedStatement pst;
			Connection conn = scnx.getConn();
			
			pst = conn.prepareStatement(sqlQueryString);
			pst.setQueryTimeout(queryTimeOutInSec);
			
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
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			String stackString = "";
			if (LOGGER.isDebugEnabled()) {
				for (StackTraceElement stackTraceElement : stackTrace) {
					stackString = stackString + "\n"+ stackTraceElement.toString();
				}
			}
			LOGGER.info("Db connexion 	url : " + connectionURL.replaceAll("password=[^&]*", "password=xxxxx")+" -  Stack trace : "+stackString);
		} 

		catch (SQLException e) {
			LOGGER.debug("SQL ERROR; Connection URL : " + connectionURL);
			LOGGER.debug("Data Base not started !? :" + e);
			LOGGER.debug("cause : " + e.getCause() + "\n next : " + e.getNextException(),e);
			nbExceptions++;
			if (nbExceptions < PoolSemaphore.NUMBER_OF_CONNEXION_TRY) {
				throw new RestartServerException("Data Base not started or already in use.");
			} else {
				LOGGER.error("Can't start Data Base!?",e);
				throw new RuntimeException("Can't start Data Base!?",e);
			}
		}
		nbExceptions = 0;
		return conn;
	}

	/**
	 * @return
	 */
	private String connectionUrl() {
		String connectionURL;

		String protocol = MainPMScmd.getPrefs().get("software", "derby");
		connectionURL = "jdbc:" + protocol;

		connectionURL = connectionURL + ":" + DataSource.dbPathNname();

		String username = MainPMScmd.getPrefs().get("username", "nouserspecified");
		if (!username.isEmpty() && !username.equals("nouserspecified"))
			connectionURL += "?user=" + username + "&password=" + MainPMScmd.getPrefs().get("password", "password");
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
			LOGGER.error("Error while doing insert : " + debug, e);
			throw e;
		} finally {
			DataSource.realesePoolConnection(sdbcnx);
		}
		return resReq;
	}
	
	public int[] executeBlockWithTimeStamp(Collection<Validatable> qL, String preparedQuery, boolean preLockRequiered, String tableToLock) throws SQLException {
		
		MyDBConnection sdbcnx = this.getConnection(null);
		int[] resReq = {};
		String debug = "";
		PreparedStatement pst = sdbcnx.getConn().prepareStatement(preparedQuery);
		try {
			
			if (preLockRequiered) {
				int cpt = 0;
				while (cpt < 10) {
					try {
						pst.execute("LOCK TABLES "+tableToLock+" WRITE");
						break;
					} catch (Exception e) {
						LOGGER.warn("Attempt to lock table "+tableToLock+" nb "+cpt+" as failed. I will retry up to 10 times every minutes.",e,true);
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e1) {
							LOGGER.error(e,e);
						}
						cpt++;
					}
				}
			}
			
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
			
			if (!e.getCause().getMessage().toLowerCase().contains("duplicate"))  {
				LOGGER.error("Error while doing " + preparedQuery + ". Details (in debug only): " + debug, e);
			} else {
				LOGGER.error("Duplicate key while doing " + preparedQuery + ". Details (in debug only): " + debug, e);
			}
			throw e;
			
		} finally {
			
			try {
				if (preLockRequiered) pst.execute("UNLOCK TABLES");
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
			
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
		return executeBlock(qL, preparedQuery);
	}

	/**
	 * Execute long batch.
	 * 
	 * @param insertQueries the queries
	 * @param insertStatement the statement
	 * @param tablesLocked the tables locked
	 * 
	 * @throws SQLException the SQL exception
	 * 
	 * @author Guillaume Thoreton
	 * @param updateStatement 
	 */
	public void executeInsertOrUpdateQuotations(List<Validatable> insertQueries, List<TableLocker> tablesLocked) throws SQLException {
		
		MyDBConnection sdbcnx = this.getConnection(null);
		Collection<Validatable> updateQueries = new ArrayList<Validatable>();
		Collection<Validatable> remainingInserts = new ArrayList<Validatable>();
		int[] updateRess = new int[0];
		Boolean tableLocked = false;
		Statement s = sdbcnx.getConn().createStatement();
		try {
			
			for (int i = 0; i < tablesLocked.size(); i++) {
				if (!tablesLocked.get(i).getLockModeValue().equals(TableLocker.LockMode.NOLOCK)) {
					s.execute("LOCK TABLE " + tablesLocked.get(i).getTableName()+" "+ tablesLocked.get(i).getLockModeValue().getLockMode());
					tableLocked = true;
					LOGGER.debug("Lock on table : " + tablesLocked.get(i).getTableName() + " : "+ tablesLocked.get(i).getLockModeValue().getLockMode());
				}
			}
			
			for (Validatable validatable : insertQueries) {
				
				final List<Object> insertParams = validatable.toDataBase().getParameterValues();
				
				Validatable updteQ = new StockToDB() {

					private static final long serialVersionUID = 7116230546055867637L;

					@Override
					public Query toDataBase() {
					
						//set
						Query qupdate = new Query();
						qupdate.addValue(insertParams.get(1));
						qupdate.addValue(insertParams.get(2));
						qupdate.addValue(insertParams.get(3));
						qupdate.addValue(insertParams.get(4));
						qupdate.addValue(insertParams.get(5));
						qupdate.addValue(insertParams.get(6));

						//where
						qupdate.addValue(insertParams.get(7));
						qupdate.addValue(insertParams.get(8));
						qupdate.addValue(insertParams.get(0));

						return qupdate;
						
					}

					@Override
					public String toString() {
						 return "["+insertParams.get(1)+","+insertParams.get(2)+","+insertParams.get(3)+","+insertParams.get(4)+","+insertParams.get(5)+","+insertParams.get(6)+","+insertParams.get(7)+","+insertParams.get(8)+","+insertParams.get(0)+"]";
					}
				};
				
				updateQueries.add(updteQ);
			}	
			updateRess = DataSource.getInstance().executeBlock(sdbcnx, updateQueries, DataSource.QUOTATIONS.getUPDATE());
			
			for (int i = 0; i < updateRess.length; i++) {
				if (updateRess[i] == 0) { 
					remainingInserts.add(insertQueries.get(i));
				}
			}
			DataSource.getInstance().executeBlock(sdbcnx, remainingInserts, DataSource.QUOTATIONS.getINSERT());
			
		} catch (Exception e) {
			LOGGER.error(
					"Error updating quotations :\n" +
					"Update request params :\n"+
							printHugeCollection(updateQueries)+"\n" +
					"Insert request params :\n"+
							printHugeCollection(remainingInserts)+"\n" +
					"Update return was " +
							Arrays.toString(updateRess)
					, e);
			
			throw new SQLException(e);
			
		} finally {
			try {
				if (tableLocked) s.execute("UNLOCK TABLES");
			} catch (Exception e) {
				LOGGER.error(e,e);
			}
			DataSource.realesePoolConnection(sdbcnx);
		}
	}

	public static String printHugeCollection(Collection<Validatable> collection) {
		
		StringBuffer buffer = new StringBuffer();
		for (Validatable validatable : collection) {
			buffer.append(validatable+"\n");
		}
		
		return buffer.toString();
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
		
		public static String getUPDATE() {
			return "UPDATE " + QUOTATIONS.TABLE_NAME + 
					" set " + 
					QUOTATIONS.DAY_OPEN_FIELD + "= ? , " + QUOTATIONS.DAY_HIGH_FIELD + " =? , " + QUOTATIONS.DAY_LOW_FIELD + "=? , " + QUOTATIONS.DAY_CLOSE_FIELD + "=? , " +
					QUOTATIONS.DAY_VOLUME_FIELD + " = ? , " + QUOTATIONS.CURRENCY_FIELD + " = ? " +
					" where "+ QUOTATIONS.SYMBOL_FIELD + "= ? and " + QUOTATIONS.ISIN_FIELD + "= ? and "+ QUOTATIONS.DATE_FIELD + "= ? ";
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
		
		public static String CURRENCYFACTOR="CURRENCYFACTOR";
		public static String CURRENCY="CURRENCY";
		public static String SECTOR_HINT = "SECTOR_HINT";
		public static String TRADING_MODE = "TRADING_MODE";
		public static String CAPITALISATION = "CAPITALISATION";

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
		

		public static String getINSERT(String eventTableName) {
			return "INSERT INTO " + eventTableName + " ( " + EVENTS.SYMBOL_FIELD + ", " + EVENTS.ISIN_FIELD + ", "
					+ EVENTS.ACCURACY_FIELD + " , " + EVENTS.DATE_FIELD + " , " + EVENTS.EVENTDEFID_FIELD + " , "
					+ EVENTS.EVENTDEF_FIELD + " , " + EVENTS.EVENTDEFEXTENSION_FIELD + " , " + EVENTS.EVENTTYPE_FIELD + "," + EVENTS.MESSAGE_FIELD + "," + EVENTS.ANALYSE_NAME 
					+"  ) VALUES (?,?,?,?,?,?,?,?,?,?)";
		}

		public static String getUPDATE(String eventTableName) {
			return "UPDATE " + eventTableName + " SET " 
			+ EVENTS.ACCURACY_FIELD + "= ? , " 
			+ EVENTS.EVENTDEFID_FIELD + "= ? , " + EVENTS.EVENTTYPE_FIELD + " = ? , " + EVENTS.MESSAGE_FIELD + "= ? "
					+ " WHERE " 
					+ EVENTS.SYMBOL_FIELD + " = ? AND "+ EVENTS.ISIN_FIELD + " = ? AND " 
					+ EVENTS.DATE_FIELD + " = ? AND " + EVENTS.EVENTDEF_FIELD + " = ? AND " + EVENTS.EVENTDEFEXTENSION_FIELD + " = ? AND "
					+ EVENTS.ANALYSE_NAME + " = ?";
		}
		
		public static String getDelete(String eventTableName) {
			return "DELETE FROM "+ eventTableName + " WHERE "+EVENTS.ANALYSE_NAME+" = ? AND "+EVENTS.DATE_FIELD+" >= ? AND "+EVENTS.DATE_FIELD+" <= ?";
		}
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#connect(int)
	 */
	public SourceClient connect(int connectionId) throws RestartServerException {
		
		MyDBConnection ret = null;
		Connection connection = this.connect(false);
		ret = new MyDBConnection(connection);
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
	public int crashResart(int connectionId) {
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#shutdownSource(com.finance.pms.threads.SourceClient, int)
	 */
	public void shutdownSource(SourceClient sourceClient, int connectionId) {
		try {
			((MyDBConnection) sourceClient).getConn().close();
		} catch (SQLException e) {
			LOGGER.warn("Couldn't close DB connection. Probably staled or alreday closed.");
		}
	}
	
	public void shutdownSource(SourceClient sourceClient) {
		try {
			((MyDBConnection) sourceClient).getConn().close();
		} catch (SQLException e) {
			LOGGER.warn("ERROR : couldn't close DB connection. Probably staled or alreday closed.");
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

	public void executeInsertOrUpdateQuotations(ArrayList<Validatable> insertQueries) throws SQLException {
		ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
		tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
		executeInsertOrUpdateQuotations(insertQueries, tablet2lock);
		
	}


}
