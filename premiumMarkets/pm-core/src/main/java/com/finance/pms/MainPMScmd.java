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
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3)
// Source File Name: MainPMScmd.java

package com.finance.pms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Indice;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.ProvidersInflation;


/**
 * The Class MainPMScmd.
 * 
 * @author Guillaume Thoreton
 */
public class MainPMScmd {	

	static Logger LOGGER = Logger.getLogger(MainPMScmd.class);
	private static MyPreference myPrefs = null;


	public MainPMScmd() {
	}


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
			if (getInflationData) {
//				Stock inflationStock = new Stock(
//						ProvidersInflation.SYMBOL, ProvidersInflation.SYMBOL, ProvidersInflation.SYMBOL,
//						true, StockCategories.INDICES_OTHER, DateFactory.dateAtZero(),
//						new SymbolMarketQuotationProvider(), new MarketValuation(Market.NYSE),"None",TradingMode.UNKNOWN,0L);
				Stock inflationStock = ProvidersInflation.inflationStock();
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


	public static void commandParamsPrinter(String[] args, @SuppressWarnings("rawtypes") Class mainClazz, Date runningStartDate) {
		StringBuffer str = new StringBuffer("Your command : java -jar com.finance.pm.jar "+mainClazz.getName()+" ");
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		LOGGER.warn(str);
		LOGGER.warn("Started at "+runningStartDate);
	}

	public static MyPreference getMyPrefs() {
		if (myPrefs == null)  {
			myPrefs = new MyPreference();
		}
		return myPrefs;
	}
	
	static Preferences prefRoot() {
		return Preferences.userRoot();
	}
	
	public static class MyPreference {

		private Preferences prefs;
		private Boolean needsFlush;
		private Properties sessionPrefs;

		public MyPreference() {
			try {
				String prefdbpath = "com.finance.pms"+System.getProperty("installdir").replaceAll("/", ".");
				prefs = prefRoot().node(prefdbpath);
			} catch (Exception e) {
				LOGGER.warn("prefs node name has been reduced to com.finance.pms because : "+e);
				try {
					prefs = prefRoot().node("com.finance.pms");
				} catch (Exception e1) {
					LOGGER.error("Can't initialise prefs", e1);
				}
			}
			
			sessionPrefs = new Properties();
			if (prefs != null) {
				try {
					for (String key : prefs.keys()) {
						sessionPrefs.put(key, prefs.get(key, ""));
					}
					needsFlush = false;
				} catch (Exception e) {
					LOGGER.warn(e, e);
				}
			}
		}
		
		@Override
		public String toString() {
			String toStr = prefs.absolutePath() + " : ";
			try {
				String[] keys = prefs.keys();
				for (String key : keys) {
					toStr = toStr + key + " = " + prefs.get(key, "Undefined")+ ";";
				}
			} catch (BackingStoreException e) {
				toStr = toStr + e.toString();
			}
			return "Prefs =" + toStr + "]";
		}
		
		

		private Object get(String key, Object alt, PrefsGetter<? extends Object> pg) {
			Object fromSessionPrefs = getFromSessionPrefs(key, alt);
			if (fromSessionPrefs == null) {
				Object fromBackingStore = fromBackingStore(key, alt, pg); 
				if (fromBackingStore != null) {
					return fromBackingStore;
				} else {
					return alt;
				}
			} else {
				return fromSessionPrefs;
			}
		}

		private Object fromBackingStore(String key, Object alt, PrefsGetter<? extends Object> pg) {
			if (prefs != null) {
				if (needsFlush ) {//This should never happen actually
					flushy();
				} 
				return pg.get(key);
			} else {
				return alt;
			}
		}

		private Object getFromSessionPrefs(String key, Object alt) {
			Object object = sessionPrefs.get(key);
			if (object != null && (alt == null || object.getClass().equals(alt.getClass())) ) {
				return object;
			} else {
				return alt;
			}
		} 

		private void put(String key, Object value, PrefsSetter<? extends Object> ps) {
			if (prefs != null) {
				ps.put(key);
				needsFlush = true;
			} 
			sessionPrefs.put(key, value);
		}

		public void remove(String key) {
			if (prefs != null) {
				prefs.remove(key);
				needsFlush = true;
			} 
			sessionPrefs.remove(key);
		}

		public Boolean flushy() {
			if (prefs != null) {
				try {
					prefs.flush();
					needsFlush = false;
				} catch (Exception e) {
					LOGGER.warn("Can't flush prefs", e);
				}
				return needsFlush;
			}
			return null;
		}
		

		public String get(String key, String alt) {
			return (String) get(key, alt, new PrefsGetter<String>(alt) {

				@Override
				String get(String key) {
					return prefs.get(key, alt);
				}
				
			});
		}

		public Double getDouble(String key, Double alt) {
			return (Double) get(key, alt, new PrefsGetter<Double>(alt) {

				@Override
				Double get(String key) {
					return prefs.getDouble(key, alt);
				}
				
			});
		}

		public Boolean getBoolean(String key, Boolean alt) {
			return (Boolean) get(key, alt, new PrefsGetter<Boolean>(alt) {

				@Override
				Boolean get(String key) {
					return prefs.getBoolean(key, alt);
				}
				
			});
		}

		public Integer getInt(String key, Integer alt) {
			return (Integer) get(key, alt, new PrefsGetter<Integer>(alt) {

				@Override
				Integer get(String key) {
					return prefs.getInt(key, alt);
				}
				
			});
		}
		

		public void put(String key, String alt) {
			put(key, alt, new PrefsSetter<String>(alt) {

				@Override
				void put(String key) {
					prefs.put(key, alt);	
				}
			});
		}

		public void putBoolean(String key, Boolean alt) {
			put(key, alt, new PrefsSetter<Boolean>(alt) {

				@Override
				void put(String key) {
					prefs.putBoolean(key, alt);	
				}
			});
		}

		public void putDouble(String key, Double alt) {
			put(key, alt, new PrefsSetter<Double>(alt) {

				@Override
				void put(String key) {
					prefs.putDouble(key, alt);	
				}
			});
		}

		public void putInt(String key, Integer alt) {
			put(key, alt, new PrefsSetter<Integer>(alt) {

				@Override
				void put(String key) {
					prefs.putInt(key, alt);	
				}
			});
		}
		
		private abstract class PrefsGetter<T> {
			T alt;
			public PrefsGetter(T alt) {
				this.alt = alt;
			}
			abstract T get(String key);
		}
		
		private abstract class PrefsSetter<T> {
			T alt;
			public PrefsSetter(T alt) {
				this.alt = alt;
			}
			abstract void put(String key);
		}
		
	}
}
