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
package com.finance.pms;

import java.security.InvalidAlgorithmParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.IndicatorAnalysisCalculationRunnableMessage;
import com.finance.pms.events.calculation.IndicatorsCalculationService;
import com.finance.pms.events.pounderationrules.LatestEventsPonderationRule;
import com.finance.pms.events.pounderationrules.LatestEventsScreennerBuyAlertOnlyPonderationRule;
import com.finance.pms.portfolio.AutoPortfolio;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.threads.ConfigThreadLocal;


/**
 * The Class IndicatorCalculationServiceMain.
 * 
 * @author Guillaume Thoreton
 */
public class IndicatorCalculationServiceMain {
	
	public static final String COMMAND_LINE_ANALYSIS = "cmdLineAnalysis";
	public static final String AUTOPORTFOLIO = "AutoPortfolio";
	public static final String UI_ANALYSIS = "UiAnalysis";
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(IndicatorCalculationServiceMain.class);

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {

		SpringContext springContext = new SpringContext(args[0]);
		try { 
			final IndicatorsCalculationService[] analyzers = new IndicatorsCalculationService[2];
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			LOGGER.info("Usage : ...");
			LOGGER.info(" pathtodb.properties -default <talib> <mas> true|false |-exportcsv|"
					+ "-specificdates mas|talib dstart dend <symbols...>|-cmds mas|talib <-db> <symbols...>");
			StringBuffer str = new StringBuffer("Your command : java -jar xxxx ");
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			LOGGER.info(str);

			List<String> argList = Arrays.asList(args);
			Boolean mas = argList.contains("mas");
			Boolean talib = argList.contains("talib");
			Boolean alerts = argList.contains("alerts");
			Boolean defaultCal = argList.contains("-default");
			Boolean cmds = argList.contains("-cmds");
			Boolean specDates = argList.contains("-specificDates");
			Boolean db = argList.contains("-db");
			Boolean monitoredOnly = argList.contains("-monitoredOnly");

			String periodType = MainPMScmd.getPrefs().get("events.periodtype","daily");
			if (!cmds && !specDates && !defaultCal) {
				LOGGER.error("Invalid command line parameters "+args);
				Runtime.getRuntime().exit(0);
			}
			
			ConfigThreadLocal.set("eventSignal", new EventSignalConfig());
			ConfigThreadLocal.set("indicatorParams", new IndicatorsConfig());

			//springContext.setDataSource(args[0]);
			ArrayList<String> springconf = new ArrayList<String>(Arrays.asList(new String[] { "/connexions.xml", "/swtclients.xml" }));
			
			//Analyse Ctx
			if (mas) {
				springContext.setMasSource(args[0]);
				springconf.add("masanalysisservices.xml");
				springContext.loadBeans(springconf.toArray(new String[0]));
				springContext.refresh();
				analyzers[0] = (IndicatorsCalculationService) springContext.getBean("mas");
			}
			if (talib) {
				springconf.add("talibanalysisservices.xml");
				springContext.loadBeans(springconf.toArray(new String[0]));
				springContext.refresh();
				analyzers[0] = (IndicatorsCalculationService) springContext.getBean("talib");
			}
			if (alerts) {
				//FIXME
				System.out.println("The service has disapered ?");
				springconf.add("alertsanalysisservices.xml");
				springContext.loadBeans(springconf.toArray(new String[0]));
				springContext.refresh();
				analyzers[0] = (IndicatorsCalculationService) springContext.getBean("alerts");
				throw new NotImplementedException("The alerts and Indicators are not compliant anymore?");
			}
			
			////default dates (An offset will be added specific for each analyze in the analyze methods)
			final Date datefin = new Date();
			final Date datedeb = DataSource.getInstance().getLastEventDateForAnalyse(AUTOPORTFOLIO);
			LOGGER.info("Events calculation date range based on last event calculated : from "+datedeb+" to "+datefin);

			if (defaultCal) {//default dates and full
				List<Stock>  shareList = null;
				if (mas || talib || alerts) {
					if (monitoredOnly) {
						shareList = DataSource.getInstance().getShareDAO().loadMonitoredStocks();
					}
					AbstractAnalysisClientRunnableMessage runnableMessage = new IndicatorAnalysisCalculationRunnableMessage(SpringContext.getSingleton(), analyzers[0], AUTOPORTFOLIO, periodType, shareList, datedeb, datefin);
					Thread thread = new Thread(runnableMessage);
					thread.start();
					while (thread.isAlive());
				}
			}

			if (!defaultCal) {//specific dates or other params
				if (cmds || specDates) {
					LOGGER.info(":))). Be aware that only one calculation service (mas or talib) is permitted for -specificdates or -cmds ...");
				}
				try {
					int sdi = args.length;
					if (specDates) {//db.properties  mas|talib -specificdates  datedebut datefin (yyyy/MM/dd) <list stocks>	
						sdi = argList.indexOf("-specificDates");
						datedeb.setTime(dateFormat.parse(args[++sdi]).getTime());
						datefin.setTime(dateFormat.parse(args[++sdi]).getTime());
					}
					if (cmds) {//db.properties  mas|talib -cmds -db|nodb <list stocks>		
						sdi = argList.indexOf("-cmds");
						if (db)
							sdi++;
					}
					
					//Analyse
					List<Stock> shareList = new ArrayList<Stock>();
					if (monitoredOnly) {
						shareList = DataSource.getInstance().getShareDAO().loadMonitoredStocks();
					} else {	
						for (int i = ++sdi; i < args.length; i++) {
							Stock share = DataSource.getInstance().loadStockBySymbol(args[i].toUpperCase());
							if (share != null) shareList.add(share);
						}
					}
					
					//creation of a temporary portfolio for signals on event calculations
					Integer sellEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getSellEventTriggerThreshold(); 
					Integer buyEventTriggerThreshold = ((EventSignalConfig)ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getBuyEventTriggerThreshold();
					AutoPortfolio autoPortfolio = PortfolioMgr.getInstance().getOrCreateAutoPortfolio(
							COMMAND_LINE_ANALYSIS, new LatestEventsScreennerBuyAlertOnlyPonderationRule(COMMAND_LINE_ANALYSIS),
							new LatestEventsPonderationRule(sellEventTriggerThreshold,buyEventTriggerThreshold));
					
					try {
						PortfolioMgr.getInstance().addPortfolio(autoPortfolio);
					} catch (InvalidAlgorithmParameterException e1) {
						LOGGER.debug("",e1);
					}
					
					IndicatorAnalysisCalculationRunnableMessage indicatorAnalyser = 
						new IndicatorAnalysisCalculationRunnableMessage(springContext, analyzers[0], COMMAND_LINE_ANALYSIS, periodType, shareList, datedeb, datefin);
					indicatorAnalyser.runIndicatorsCalculationPassOne(true, "auto");
					
				} catch (Exception e) {
					LOGGER.error("", e);
				}
			}
			
		} catch(Exception e) {
			LOGGER.error("",e);
		} finally {
			springContext.close();
		}
	}

//	/**
//	 * Gets the date moins n jours.
//	 * 
//	 * @param now the now
//	 * @param days the days
//	 * 
//	 * @return the date moins n jours
//	 */
//	public static Date getDateMoinsNJours(Date now, Integer days) {
//		GregorianCalendar gcal = new GregorianCalendar();
//		gcal.setTime(now);
//		QuotationsFactories.getFactory().incrementDate(gcal, -days);
//		return gcal.getTime();
//	}
}
