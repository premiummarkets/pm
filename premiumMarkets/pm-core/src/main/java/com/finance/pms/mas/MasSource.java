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
package com.finance.pms.mas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventState;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.mas.resultsparser.EventDataResultFormat;
import com.finance.pms.mas.resultsparser.EventListResultFormat;
import com.finance.pms.mas.resultsparser.ResultsParser;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;


// TODO: Auto-generated Javadoc
/**
 * The Class MasSource.
 * 
 * @author Guillaume Thoreton
 */
public class MasSource implements SourceConnector {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MasSource.class);
	
	/** The singleton. */
	private static MasSource singleton;
	
	/** The NBTHREADSMAS. */
	protected static int NBTHREADSMAS;
	// Client request IDs
	/** The Constant Market_data_request. */
	public static final int Market_data_request = 1;
	
	/** The Constant Indicator_data_request. */
	public static final int Indicator_data_request = 2;
	
	/** The Constant Trading_period_type_request. */
	public static final int Trading_period_type_request = 3;
	
	/** The Constant Market_list_request. */
	public static final int Market_list_request = 4;
	
	/** The Constant Indicator_list_request. */
	public static final int Indicator_list_request = 5;
	
	/** The Constant Session_change_request. */
	public static final int Session_change_request = 7;
	
	/** The Constant EVENT_DATA_REQUEST. */
	public static final int EVENT_DATA_REQUEST = 9;
	
	/** The Constant EVENT_LIST_REQUEST. */
	public static final int EVENT_LIST_REQUEST = 10;
	// Subtokens
	/** The Constant STARTDATE. */
	public static final String STARTDATE = "start_date";
	
	/** The Constant ENDDATE. */
	public static final String ENDDATE = "end_date";
	// Field separators
	/** The Constant MESSAGE_FIELD_SEPARATOR. */
	public static final String MESSAGE_FIELD_SEPARATOR = "\t";
	
	/** The Constant Message_date_field_separator. */
	public static final String Message_date_field_separator = "";
	
	/** The Constant Message_time_field_separator. */
	public static final String Message_time_field_separator = "";
	// daily period type is needed for initialization.
	/** The Constant PERIODS. */
	public static final String[] PERIODS = { "daily", "weekly", "monthly", "quarterly", "yearly", "hourly", "1-minute",
			"2-minute", "5-minute", "10-minute", "15-minute", "20-minute", "30-minute" };
	//Variables
	//private  MasConnection connection;
	//private  Map eventList;
	/** The thread pool. */
	private PoolSemaphore threadPool;
	
	/** The num port map. */
	private Map<Integer, Integer> numPortMap;
	
	/** The process map. */
	private Map<Integer, MasProcess> processMap;
	
	//init dates
	/** The Period dates. */
	private Map<String, Period> PeriodDates;

	/**
	 * Instantiates a new mas source.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 */
	private MasSource(String pathToprops) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream((new File(pathToprops))));
			//Connection
			if (props.containsKey("mas.hostname"))
				MainPMScmd.getPrefs().put("mas.hostname", props.getProperty("mas.hostname"));
			if (props.containsKey("mas.port"))
				MainPMScmd.getPrefs().put("mas.port", props.getProperty("mas.port"));
			//			if (props.containsKey("mas.startdate.daily"))
			//				MainPMScmd.prefs.put("mas.startdate.daily", props.getProperty("mas.startdate.daily"));
			//			if (props.containsKey("mas.enddate.daily"))
			//				MainPMScmd.prefs.put("mas.enddate.daily", props.getProperty("mas.enddate.daily"));
			//			 if (props.containsKey("mas.period"))
			//			 MainPMScmd.prefs.put("mas.period", props.getProperty("mas.period"));
			if (props.containsKey("mas.poolsize"))
				MainPMScmd.getPrefs().put("mas.poolsize", props.getProperty("mas.poolsize"));
			if (props.containsKey("mas.ctimeout"))
				MainPMScmd.getPrefs().put("mas.ctimeout", props.getProperty("mas.ctimeout"));
			if (props.containsKey("mas.server.cmd"))
				MainPMScmd.getPrefs().put("mas.server.cmd", props.getProperty("mas.server.cmd"));
			if (props.containsKey("mas.server.env"))
				MainPMScmd.getPrefs().put("mas.server.env", props.getProperty("mas.server.env"));
			if (props.containsKey("mas.server.dir"))
				MainPMScmd.getPrefs().put("mas.server.dir", props.getProperty("mas.server.dir"));
			if (props.containsKey("mas.server.wait"))
				MainPMScmd.getPrefs().put("mas.server.wait", props.getProperty("mas.server.wait"));
			if (props.containsKey("mas.logserver"))
				MainPMScmd.getPrefs().put("mas.logserver", props.getProperty("mas.logserver"));
			
			MainPMScmd.getPrefs().flush();
		} catch (Exception e) {
			LOGGER.error("Couldn't find propertie file. Check the propeties path",e);
		}
		
		if(this.numPortMap == null) {
			this.numPortMap = new HashMap<Integer, Integer>();
		}
		if (this.processMap == null) {
			this.processMap = new HashMap<Integer, MasProcess>();
		}
		
		if (singleton == null)
			MasSource.singleton = this;
	}

	/**
	 * Instantiates a new mas source.
	 * 
	 * @param pathToprops the path toprops
	 * @param startServers the start servers
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasSource(String pathToprops, Boolean startServers) {
		this(pathToprops);
		
		MasSource.NBTHREADSMAS = (new Integer(MainPMScmd.getPrefs().get("mas.poolsize", "10"))).intValue();
		
		if (startServers) {		
			startAllSources();
		}
		
		threadPool = new PoolSemaphore(MasSource.NBTHREADSMAS, this, false);
		
		if (singleton == null)
			MasSource.singleton = this;
	}

	/**
	 * Start all sources.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void startAllSources() {
		if (this.numPortMap == null && this.processMap == null) {
			this.numPortMap = new HashMap<Integer, Integer>();
			this.processMap = new HashMap<Integer, MasProcess>();
			LOGGER.info("Number of mas Threads :" + MasSource.NBTHREADSMAS);
			for (int threadnum = 0; threadnum < MasSource.NBTHREADSMAS; threadnum++) {
				this.startSource(threadnum);
			}
			try {
				synchronized (this) {
					wait((new Integer(MainPMScmd.getPrefs().get("mas.server.wait", "5000"))));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				LOGGER.error("", e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("", e);
			}
		}
	}

	
	/**
	 * Sets the init analyze dates.
	 * 
	 * @param startDate the start date
	 * @param endDate the end date
	 * 
	 * @author Guillaume Thoreton
	 */
	public void setInitAnalyzeDates(String startDate,String endDate) {
//		this.startDate = startDate;
//		this.endDate = endDate;
		
		//initialisation des date par p�riodes
		PeriodDates = new HashMap<String, Period>();
		PeriodDates.put(PERIODS[0], new Period(PERIODS[0],
				//							MainPMScmd.prefs.get("mas.startdate.daily","now - 30 days"),
				//							MainPMScmd.prefs.get("mas.enddate.daily","now")));
											startDate, endDate));
		//TODO les autres periodes que daily ...
		
	}
	
	
	/**
	 * Gets the connection.
	 * 
	 * @param connectionId the connection id
	 * 
	 * @return the connection
	 * 
	 * @throws RestartServerException the restart server exception
	 */
	private MasConnection getConnection(int connectionId) throws RestartServerException {
		
		if (null == PeriodDates) {
			LOGGER.error("MasSource analyze range must be initialize after constructor call and DataSource init. Sorry for that!");
			throw new RuntimeException("MasSource analyze range must be initialize after constructor call and DataSource init. Sorry for that!");
		}
		
		//initalisation de la connexion server.
		String loginParam = PeriodDates.get(PERIODS[0]).getStartDate() + "\t" + PeriodDates.get(PERIODS[0]).getEndDate();
		//numero de port
		int portNum = getNextAvailablePort(connectionId);
		this.numPortMap.put(new Integer(connectionId), new Integer(portNum));
		try {
			return new MasConnection(MainPMScmd.getPrefs().get("mas.hostname", "localhost"), new Integer(portNum), loginParam, Integer
					.parseInt(MainPMScmd.getPrefs().get("mas.ctimeout", "20000")));
		} catch (IOException e) {
			LOGGER.debug("", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Restart connection.
	 * 
	 * @param con the con
	 * 
	 * @return the mas connection
	 * 
	 * @author Guillaume Thoreton
	 */
	public static MasConnection restartConnection(MasConnection con) {
		try {
			return (MasConnection) MasSource.getInstance().getThreadPool().reconnectSource(con);
		} catch (InterruptedException e) {
			LOGGER.error("", e);
		} catch (TimeoutException e) {
			LOGGER.error("", e);
		}
		return con;
	}

	/**
	 * Gets the connection from pool.
	 * 
	 * @return the connection from pool
	 */
	public static MasConnection getConnectionFromPool() {
		MasConnection ret;
		try {
			ret = (MasConnection) MasSource.getInstance().getThreadPool().getResource();
			return ret;
		} catch (InterruptedException e) {
			LOGGER.error("Unable to get Connection. Is mas started?", e);
			System.exit(1);
		} catch (TimeoutException e) {
			LOGGER.error("Unable to get Connection. Is mas started? Bla bla", e);
		}
		LOGGER.error("Unable to get Connection. For one reason or the other ...");
		System.exit(1);
		return null;
	}

	/**
	 * Gets the next available port.
	 * 
	 * @param connectionId the connection id
	 * 
	 * @return the next available port
	 */
	private int getNextAvailablePort(int connectionId) {
		int portNum;
		if (this.numPortMap.containsKey(new Integer(connectionId))) {
			Integer oldport = this.numPortMap.get(new Integer(connectionId));
			portNum = oldport.intValue() + MasSource.NBTHREADSMAS;
		} else {
			portNum = Integer.parseInt(MainPMScmd.getPrefs().get("mas.port", "13579")) + connectionId;
		}
		return portNum;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#connect(int)
	 */
	public SourceClient connect(int connectionId) throws RestartServerException {
		return this.getConnection(connectionId);
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#shutdownSource(com.finance.pms.threads.SourceClient, int)
	 */
	public void shutdownSource(SourceClient c, int connectionId) {
		MasConnection mc = (MasConnection) c;
		//logout and diconnect
		try {
			mc.logout();
		} catch (IOException e) {
			LOGGER.error("", e);
		}
		if (this.processMap.containsKey(new Integer(connectionId))) {
			MasProcess mp = this.processMap.get(new Integer(connectionId));
			LOGGER.debug("Stop pss " + this.numPortMap.get(new Integer(connectionId)));
			mp.stopProcess();
			this.processMap.remove((new Integer(connectionId)));
		}
	}

	/**
	 * Gets the single instance of MasSource.
	 * 
	 * @return single instance of MasSource
	 */
	public static MasSource getInstance() {
		if (singleton == null) {
			throw new RuntimeException("Mas source not initialized");
			// new MasSource();
		}
		return singleton;
	}

	//	Send a request Event_list_request	
	/**
	 * Send event list request.
	 * 
	 * @param period_type the period_type
	 * @param symbol the symbol
	 * @param connection the connection
	 * 
	 * @return the map< integer, string>
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * @throws ApplicativeException the applicative exception
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, String> sendEventListRequest(String period_type, String symbol, MasConnection connection)
			throws IOException, RestartServerException, ApplicativeException {
		//Recuperation de la list des ev�nements
		String eventLIstReq = symbol + MESSAGE_FIELD_SEPARATOR + period_type;
		Map<Integer, String> el = new HashMap<Integer, String>();
		DataInspector di = connection.sendRequest(EVENT_LIST_REQUEST, eventLIstReq);
		String results = di.getMessage();
		if (di.getAckNumber() == MasConnection.OK) {
			el = (Map<Integer, String>) ResultsParser.resultParser(results, new EventListResultFormat());
			return el;
		} else {
			LOGGER.warn("Wrong list event results for symbol :" + symbol);
			LOGGER.warn("Raison " + results);
			//ed = ResultsParser.resultParser(results,new EventDataResultFormat());
			throw new ApplicativeException("Error retreiving event result list : " + results);
		}
	}

	/**
	 * Realese pool connection.
	 * 
	 * @param connection the connection
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void realesePoolConnection(MasConnection connection) {
		MasSource.getInstance().getThreadPool().releaseResource(connection);
	}

	// Send a request Event_list_request
	/**
	 * Send event data request.
	 * 
	 * @param period_type the period_type
	 * @param stock the stock
	 * @param datedeb the datedeb
	 * @param datefin the datefin
	 * @param connection the connection
	 * 
	 * @return the symbol events
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * @throws ApplicativeException the applicative exception
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings("unchecked")
	public SymbolEvents sendEventDataRequest(
			String period_type, Stock stock, String datedeb, String datefin,
			MasConnection connection, String analyseName) 
	throws IOException, RestartServerException, ApplicativeException {
		//String stocktrunc = stock.getSymbol().toLowerCase().substring(0,stock.getSymbol().length()-3);
		String stocktrunc = stock.getSymbol().toLowerCase();
		Map<Integer, String> el = sendEventListRequest(period_type, stocktrunc, connection);
		Map<EventKey, EventValue> ed = new HashMap<EventKey, EventValue>();
		SymbolEvents se;
		if (el.size() > 0) {
			//Cr�ation de la requete d'analyse. //cf. DATE_PARSING_UTILITIES
			StringBuffer eventsString = new StringBuffer(stocktrunc + MESSAGE_FIELD_SEPARATOR + datedeb + MESSAGE_FIELD_SEPARATOR
					+ datefin + MESSAGE_FIELD_SEPARATOR);
			Set<Integer> eventKeySet = el.keySet();
			Iterator<Integer> eventKeySetIt = eventKeySet.iterator();
			while (eventKeySetIt.hasNext()) {
				Integer eventR = (Integer) eventKeySetIt.next();
				//eventsString.append(((Result) el.get(eventR)).getKey());
				eventsString.append(eventR);
				if (eventKeySetIt.hasNext())
					eventsString.append(MESSAGE_FIELD_SEPARATOR);
			}
			//Execution de l'analyse
			DataInspector di = connection.sendRequest(EVENT_DATA_REQUEST, eventsString.toString());
			String results = di.getMessage();
			if (di.getAckNumber() == MasConnection.OK) {
				ed = (Map<EventKey, EventValue>) ResultsParser.resultParser(results, new EventDataResultFormat());
			} else {
				LOGGER.warn("Wrong data results for symbol : " + stocktrunc);
				LOGGER.warn("Response Ack : " + di.getAckNumber());
				LOGGER.warn("Raison : " + results);
				//ed = ResultsParser.resultParser(results,new EventDataResultFormat());
				throw new IOException("Error retreiving event result data : " + results);
			}
		}
		se = new SymbolEvents(stock, ed, el, EventState.STATE_TERMINATED);
		return se;
	}

	/**
	 * The Class Period.
	 * 
	 * @author Guillaume Thoreton
	 */
	public class Period {
		
		/** The period. */
		private String period;
		
		/** The start date. */
		private String startDate;
		
		/** The end date. */
		private String endDate;

		/**
		 * Instantiates a new period.
		 * 
		 * @param p the p
		 * @param s the s
		 * @param e the e
		 * 
		 * @author Guillaume Thoreton
		 */
		Period(String p, String s, String e) {
			period = p;
			startDate = s;
			endDate = e;
		}

		/**
		 * Instantiates a new period.
		 * 
		 * @param p the p
		 * @param s the s
		 * 
		 * @author Guillaume Thoreton
		 */
		Period(String p, String s) {
			period = p;
			startDate = s;
			endDate = "now";
		}

		/**
		 * Gets the start date.
		 * 
		 * @return the start date
		 */
		public String getStartDate() {
			return STARTDATE + "\t" + period + "\t" + startDate;
		}

		/**
		 * Gets the end date.
		 * 
		 * @return the end date
		 */
		public String getEndDate() {
			return ENDDATE + "\t" + period + "\t" + endDate;
		}
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#getThreadPool()
	 */
	public PoolSemaphore getThreadPool() {
		return threadPool;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.db.HttpSource#stopThreads()
	 */
	/**
	 * Stop threads.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void stopThreads() {
		if (null != this.threadPool) {
			this.threadPool.stopThreads();
			LOGGER.info("All MAS Done ...");
		}
		this.destroy();
		LOGGER.info("All MAS Stoped ... Bye");
		MasSource.singleton = null;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.threads.SourceConnector#restartSource(int)
	 */
	public int crashResart(int connectionId) {
		if (this.processMap.containsKey(new Integer(connectionId))) {
			MasProcess mp = this.processMap.get(new Integer(connectionId));
			mp.stopProcess();
		}
		startSource(connectionId);
		// sleep time
		int sleep = (new Integer(MainPMScmd.getPrefs().get("mas.server.wait", "5000")));
		return sleep;
	}
	
	/**
	 * Destroy.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void destroy() {
		for (MasProcess mp : this.processMap.values()) {
			LOGGER.debug("Destroying mas process left running ");
			mp.stopProcess();
		}
	}
	

	/**
	 * Start source.
	 * 
	 * @param connectionId the connection id
	 * 
	 * @author Guillaume Thoreton
	 */
	private void startSource(int connectionId) {
		//cmd
		int portNum = getNextAvailablePort(connectionId);
		String cmd[] = { MainPMScmd.getPrefs().get("mas.server.cmd", "/usr/local/opt/Prgs/mas/bin/mas"), portNum + "", "-p", "-b" };
		//env
		LOGGER.debug("\n\nYour home : " + System.getenv("HOME"));
		//dir (for cores and messages)
		File dir = new File(MainPMScmd.getPrefs().get("mas.server.dir", System.getenv("HOME") + "/tmp"));
		File outputfile = new File(dir, "output4thread_" + connectionId + ".log");
		//exec
		LOGGER.debug("\n\nStarting server (Thread " + connectionId + ") : " + Arrays.toString(cmd));
		try {
			//Run
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.directory(dir);
			pb.redirectErrorStream(true);
			Map<String, String> m = pb.environment();
			m.put("MAS_DIRECTORY", System.getenv("HOME") + "/.mas");
			Process p = pb.start();
			//Log
			MasProcessLogging mpl = new MasProcessLogging(p, outputfile, connectionId);
			MasProcess mp = new MasProcess(p, mpl);
			if (new Boolean(MainPMScmd.getPrefs().get("mas.logserver","false"))) mpl.start();
			this.processMap.put(new Integer(connectionId), mp);
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
	}
}
