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
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name: MainPMScmd.java

package com.finance.pms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.calculation.DateFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class MainPMScmd.
 * 
 * @author Guillaume Thoreton
 */
public class MainPMScmd {	

	/** The Constant prefdbpath. */
	//public static final String prefdbpath = "com.finance.pms"+ System.getProperty("installdir");
	
	/** The Constant prefs. */
	//private static final Preferences prefs = Preferences.userRoot().node(prefdbpath);

	
	/** The LOGGER. */
	//protected static MyLogger LOGGER = Logger.getLogger(MainPMScmd.class);
	protected static Logger LOGGER = Logger.getLogger(MainPMScmd.class);

	/**
	 * Instantiates a new main pm scmd.
	 * 
	 * @author Guillaume Thoreton
	 */
	public MainPMScmd() {
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String args[]) {

		String dbProps = new String();
		String stockListFile = new String();
		String sharesListName = new String();
		String marketQuotationProvider = new String();
		QuotationUpdate dbHi;

		commandParamsPrinter(args, MainPMScmd.class, EventSignalConfig.getNewDate());

		long t0 = System.currentTimeMillis();
		LOGGER.info("Start time " + t0);

		try {
			List<String> argList = Arrays.asList(args);
			Boolean db = argList.contains("DB");
			dbProps = (db) ? args[1] : args[0];
			sharesListName = (db) ? args[2] : args[1];
			Boolean updateSharesListAndQuotes = argList.contains("-updateStocksAndQuotes");
			Boolean updateSharesList = argList.contains("-updateSharesList");
			Boolean validateStocks = argList.contains("-validateStocks");
			Boolean updateQuotesForMonitoredOnly = argList.contains("-updateMonitoredOnly");
			Boolean updateFromFile = argList.contains("-fileStocks");
			Boolean updateQuotesForSharesList = argList.contains("-majQuotations");
			Boolean cleanStocks = argList.contains("-cleanStocks");
			Boolean updateFromCmd = argList.contains("-cmdStocks");
			Boolean quoteProvider = argList.contains("-quoteProvider");
			Boolean yahooIndicesSymbols = argList.contains("-yahooIndicesSymbol");
			Boolean getInflationData = argList.contains("-getInflationData");

			SpringContext springContext = new SpringContext(dbProps);
			//springContext.setDataSource(dbProps);
			springContext.loadBeans("/connexions.xml", "/swtclients.xml");
			springContext.refresh();
			
			if (!SharesListId.checkProvider(sharesListName)) {
				LOGGER.error("Market list provider not available : " + sharesListName + " \n");
				String validProv =" ";
				for (String string :  SharesListId.cmdsValues() ) {
					validProv += string +" ";
				}
				LOGGER.error("Valid providers : " +validProv+ " \n");
			}
			
			dbHi = new QuotationUpdate();
			if (quoteProvider) {
				marketQuotationProvider = args[argList.indexOf("-quoteProvider")+1];
				if (!MarketQuotationProviders.checkProvider(marketQuotationProvider)) {
					LOGGER.error("Quotation provider not available : " + sharesListName );
					LOGGER.error("Valid providers : " + MarketQuotationProviders.cmdsValues());
					marketQuotationProvider = sharesListName;
				} 
			} else {
				LOGGER.warn("You should specify a Provider for the quotation via the option -quoteProvider (ex : -quoteProvider yahoo)");
				marketQuotationProvider = sharesListName;
			}
			
			Set<Indice> indices = new TreeSet<Indice>();
			if (yahooIndicesSymbols) {
				int i = argList.indexOf("-yahooIndicesSymbol")+1;
				indices = Indice.parseString(args[i]);
			}
			if (updateSharesListAndQuotes) {
				dbHi.getQuotesAndNewForShareListFromWeb(sharesListName, marketQuotationProvider,indices);
			}
			if (updateSharesList) {
				LOGGER.info("Updating share list only");
				Providers.getInstance(sharesListName).updateStockListFromWeb(MarketQuotationProviders.valueOfCmd(marketQuotationProvider));
			}
			if (updateQuotesForMonitoredOnly) {
				dbHi.getQuotesForMonitored();
			}
			if (updateFromFile) {
				stockListFile = args[argList.indexOf("-fileStocks")+1];
				dbHi.getQuotesForListInFile(stockListFile, sharesListName);
			}
			if (updateFromCmd) {
				List<String> argsList = new ArrayList<String>();
				int i = argList.indexOf("-cmdStocks")+1;
				for (; i < args.length; i++) {
					argsList.add(args[i]);
				}
				dbHi.getQuotesForListInCmd(argsList, sharesListName, marketQuotationProvider);
			}
			if (cleanStocks) {
				LOGGER.error("Nothing here. Stock cleanStocks should now be an admin task \n");
			} 
			if (validateStocks) {
				LOGGER.error("Nothing here. Stock validation should now come with majStocks \n");
			}
			if (updateQuotesForSharesList) {
				dbHi.getQuotesForSharesListInDB(sharesListName, indices);
			}
			if(getInflationData) {
				Stock inflationStock = new Stock(
						ProvidersInflation.SYMBOL,ProvidersInflation.SYMBOL,ProvidersInflation.SYMBOL,
						new Boolean(false),StockCategories.INDICES_OTHER, DateFactory.dateAtZero(),
						new SymbolMarketQuotationProvider(),new MarketValuation(Market.NYSE),"None",TradingMode.UNKNOWN,0L);
				DataSource.getInstance().getShareDAO().saveOrUpdateStock(inflationStock);
				Providers.getInstance("inflation").getQuotes(inflationStock, null, null);
			}
			
		} catch (Exception e) {
			LOGGER.info("Usage :");
			LOGGER.info(" - No parameter : load intra day for ticker file specified in qm.propreties");
			System.out.println(" - One date yyyymmdd : historical data from dd/mm/yyyy for ticker file specified in qm.propreties");
			LOGGER.info(" - Two dates yyyymmdd yyyy'mm'dd': historical data from dd/mm/yyyy to dd'/mm'/yyyy'");
			LOGGER.info(" - 'eod' : don't know");
			LOGGER.info(" - 'DB' : indicates data base requirement");
			LOGGER.info("	- /path/db.properties : data base and http connection parameters");
			LOGGER.info("	- 'yahoo'||'yahoofr'||'boursorama' : available providers.");
			LOGGER.info("	- '-fileStocks' /path/stocks.txt : stocks list for historical values");
			LOGGER.info("	- '-majStocks' (optional) : same as previous but also retreive historical values from all the stocks contained in data base");
			LOGGER.info("	- 'or -cleanStocks' (optional) : remove old value stocks that are no longuer referenced in DB or stock file");
			LOGGER.info(" - 'DB' /path/db.properties 'yahoo'||'yahoofr'||'boursorama' '-initStocks'  : initialise the stocks list in database from the web");
			LOGGER.info(" - 'DB' /path/db.properties 'yahoo'||'yahoofr'||'boursorama' '-validateStocks'  : validate the stocks list contained in database");
			LOGGER
					.info(" - 'DB' /path/db.properties 'yahoo'||'yahoofr'||'boursorama' '-cmdStocks' <codes NYSE> : retrieve historical values for the stocks on commande line");
			LOGGER.info("\n		-------------------------------Exemples------------------------------- ");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -fileStocks /root/tmp/stocks.txt -majStocks");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -fileStocks /root/tmp/stocks.txt -cleanStocks");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties yahoo -fileStocks /root/tmp/stocks.txt");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -initStocks");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -validateStocks");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -cmdStocks FTE IBM");
			LOGGER.info("java -jar com.finance.pm.jar DB /opt/com.finance.pms/db.properties boursorama -yahooIndicesSymbol FTCHI:EURONEXT,FTSE:LSE");
			LOGGER.info(e,e);
		}

		long t1 = System.currentTimeMillis();
		LOGGER.info("End time " + t1);
		LOGGER.info("Length :" + ((t1 - t0) / (1000 * 60)) + " minutes ");

		// Arret des potentielles threads
		SpringContext.getSingleton().close();
	}

	/**
	 * @param args
	 * @param runningStartDate 
	 */
	public static void commandParamsPrinter(String[] args, @SuppressWarnings("rawtypes") Class mainClazz, Date runningStartDate) {
		StringBuffer str = new StringBuffer("Your command : java -jar com.finance.pm.jar "+mainClazz.getName()+" ");
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		LOGGER.warn(str);
		LOGGER.warn("Started at "+runningStartDate);
	}

	public static Preferences getPrefs() {
		Preferences prefs;
		try {
			//String prefdbpath = "com.finance.pms"+System.getProperty("installdir").replaceAll("/", ".");
			String prefdbpath = "com.finance.pms."+System.getProperty("installdir").hashCode();
			prefs = Preferences.userRoot().node(prefdbpath);
		} catch (Exception e) {
			prefs = Preferences.userRoot().node("com.finance.pms");
			e.printStackTrace();
		}
		return prefs;
	}
}
