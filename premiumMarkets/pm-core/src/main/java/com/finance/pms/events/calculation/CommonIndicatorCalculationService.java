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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.DataResultReversedComparator;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.TunedConf;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.events.scoring.TunedConfMgr.CalcStatus;
import com.finance.pms.events.scoring.TunedConfMgr.CalculationBounds;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;


// TODO: Auto-generated Javadoc
/**
 * The Class TalibIndicatorCalculationService.
 * 
 * @author Guillaume Thoreton
 */
public class CommonIndicatorCalculationService extends IndicatorsCalculationService {
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(CommonIndicatorCalculationService.class);
	
	private Map<EventDefinition, Class<EventCompostionCalculator>> availSecondPIndCalculators;
	
	/**
	 * Instantiates a new talib indicator calculation service.
	 * Initialized in spring context
	 * 
	 * @param jmsTemplate the jms template
	 * @param eventQueue the event queue
	 * 
	 * @author Guillaume Thoreton
	 */
	//Initialized in spring context
	public CommonIndicatorCalculationService(Map<EventDefinition, Class<EventCompostionCalculator>> availableSecondPassIndicatorCalculators, JmsTemplate jmsTemplate, Queue eventQueue) {
		this.eventQueue = eventQueue;
		this.jmsTemplate = jmsTemplate;
		this.availSecondPIndCalculators = availableSecondPassIndicatorCalculators;

	}


	@Override
	protected Map<Stock,Map<EventDefinition, SortedMap<Date, double[]>>> analyseSymbolCollection(
			Collection<Stock> symbols, Date dateDeb, Date dateFin, Currency calculationCurrency, String eventListName, 
			String periodType, Boolean keepCache, Integer passNumber, Boolean export, Boolean persistEvents,  String passOneCalcMode, Observer...observers) 
					throws InvalidAlgorithmParameterException, IncompleteDataSetException {
		
		//TODO deal with the periodType
		if (!periodType.equals("daily")) throw new UnsupportedOperationException("Period != daily. Fix me!");
		
		if (null == dateDeb || null == dateFin) {
			throw new InvalidAlgorithmParameterException("",new Throwable());
		}
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateDeb);
			Integer minDiff = new Integer("-"+MainPMScmd.getPrefs().get("talib.daysbackwardday","10"));
			QuotationsFactories.getFactory().incrementDate(calendar, -minDiff);
			dateDeb =  calendar.getTime();
			
		} catch (NumberFormatException e) {
			LOGGER.error("Invalid number of days backward : "+"-"+MainPMScmd.getPrefs().get("talib.daysbackwardday","10"),e);
		}
		
		LOGGER.debug("Events calculation real date range : from "+dateDeb+" to "+dateFin);
		return this.allEventsCalculation(symbols, dateDeb, dateFin, calculationCurrency, eventListName, keepCache, passNumber, export, persistEvents, passOneCalcMode, observers);
		
	}

	/**
	 * Calculate all macd.
	 * @param stList the st list
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param calculationCurrency 
	 * @param passNumber 
	 * @param export 
	 * 
	 * @author Guillaume Thoreton
	 * @param persistEvents 
	 * @param observers 
	 * @param passOneCalcMode 
	 * @return 
	 * @throws IncompleteDataSetException 
	 */
	private Map<Stock,Map<EventDefinition, SortedMap<Date, double[]>>> allEventsCalculation(
				Collection<Stock> stList, Date startDate, Date endDate, 
				Currency calculationCurrency, String eventListName, Boolean keepCache, int passNumber, Boolean export, Boolean persistEvents, String passOneCalcMode, Observer... observers) 
				throws IncompleteDataSetException {
		
		Map<Stock,Map<EventDefinition, SortedMap<Date, double[]>>> calculatedOutputReturn =  new HashMap<Stock, Map<EventDefinition, SortedMap<Date, double[]>>>(1);
		Map<Stock,TunedConf> tunedConfs = new HashMap<Stock, TunedConf>();
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("indicatorcalculator.semaphore.nbthread","20")));
		List<Future<SymbolEvents>> futures = new ArrayList<Future<SymbolEvents>>();
		for (Observer observer : observers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, stList.size()));
		}
		Set<Observer> obsSet = new HashSet<Observer>(Arrays.asList(observers));
		
		Boolean isDataSetComplete = true;
		for (final Stock stock : stList) {
			
			try {
				LOGGER.guiInfo("Calculating pass "+passNumber+" events for stock "+stock.toString()+ " between "+startDate+" and "+endDate);

				final Queue eventQueue = this.eventQueue;
				final JmsTemplate jmsTemplate = this.jmsTemplate;

				IndicatorsCalculationThread calculationRunnableTarget = null;
				Currency stockCalcCurrency = (calculationCurrency == null)? stock.getMarketValuation().getCurrency() : calculationCurrency;
				
				if (passNumber == 1) {//pass 1
					
					//Date bounds setting
					TunedConf tunedConf = TunedConfMgr.getInstance().loadUniqueNoRetuneConfig(stock, ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getConfigListFileName());
					CalculationBounds calculationBounds = TunedConfMgr.getInstance().new CalculationBounds(CalcStatus.IGNORE, startDate, endDate);
					if (passOneCalcMode.equals("auto")) {
						endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
						calculationBounds = TunedConfMgr.getInstance().autoCalcAndSetDatesBounds(tunedConf, stock, startDate, endDate);
					}
					if (passOneCalcMode.equals("reset")) {
						endDate = TunedConfMgr.getInstance().endDateConsistencyCheck(tunedConf, stock, endDate);
						tunedConf.setLastCalculationStart(startDate);
						tunedConf.setLastCalculationEnd(endDate);
						calculationBounds = TunedConfMgr.getInstance().new CalculationBounds(CalcStatus.RESET, startDate, endDate);
					}
					
					//if We inc or reset, tuned conf last event will need update : We add it in the map
					if (calculationBounds.getCalcStatus().equals(CalcStatus.INC) || calculationBounds.getCalcStatus().equals(CalcStatus.RESET)) {
						tunedConfs.put(stock, tunedConf);
					}
					
					//Calculation
					if (!calculationBounds.getCalcStatus().equals(CalcStatus.NONE)) {
						
						calculationRunnableTarget = new FirstPassIndicatorCalculationThread(
								stock, calculationBounds.getPmStart(), calculationBounds.getPmEnd(), stockCalcCurrency, eventListName, obsSet,
								keepCache, eventQueue, jmsTemplate, persistEvents);
						
					} else {
						
						LOGGER.info(
								"Pass 1 events recalculation requested for "+stock.getSymbol()+" using analysis "+eventListName+" from "+startDate+" to "+endDate+". "+
								"No recalculation needed calculaiton bound is "+ calculationBounds.toString());
					}
					
					
				} else {// pass 2
					
					calculationRunnableTarget = new SecondPassIndicatorCalculationThread(
							stock, startDate, endDate, stockCalcCurrency, eventListName, obsSet,
							availSecondPIndCalculators, export, keepCache, eventQueue, jmsTemplate, persistEvents, true);
					
					
				}
				
				if (calculationRunnableTarget != null) {
					Future<SymbolEvents> submitedRunnable = (Future<SymbolEvents>) executor.submit(calculationRunnableTarget);
					futures.add(submitedRunnable);
				}

			} catch (Throwable e) {
				LOGGER.error(e,e);
				isDataSetComplete = false;
			}
		}
		
		executor.shutdown();
		try {
			boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow, new Exception());
				isDataSetComplete = false;
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow,e);
			isDataSetComplete = false;
		}
		
		List<SymbolEvents> allEvents = new ArrayList<SymbolEvents>();
		List<Stock> failingStocks = new ArrayList<Stock>();
		for (Future<SymbolEvents> future : futures) {

			try {

				SymbolEvents se = future.get();
				
				//Events
				allEvents.add(se);
				
				//Output
				Map<EventDefinition, SortedMap<Date, double[]>> calculationOutput = se.getCalculationOutput();
				if (calculationOutput == null) calculationOutput = new HashMap<EventDefinition, SortedMap<Date,double[]>>();
				calculatedOutputReturn.put(se.getStock(), calculationOutput);

				//Save date bounds if exists
				if (passNumber == 1 && se.getDataResultList().size() > 0) {
					TunedConf tunedConf = tunedConfs.get(se.getStock());
					if (tunedConf != null) TunedConfMgr.getInstance().updateConf(tunedConf, se.getStock(), se.getSortedDataResultList(new DataResultReversedComparator()).get(0).getDate());
				}

			} catch (Exception e1) {

				isDataSetComplete = false;
				if (e1.getCause() instanceof IncompleteDataSetException) {
					failingStocks.addAll(((IncompleteDataSetException) e1.getCause()).getFailingStocks());
					allEvents.addAll(((IncompleteDataSetException) e1.getCause()).getSymbolEvents());
					calculatedOutputReturn.putAll(((IncompleteDataSetException) e1.getCause()).getCalculatedOutput());
				} else {
					LOGGER.error(e1,e1);
				}
			}
		}
		
		LOGGER.info("Storing "+allEvents.size()+" sets of events, from "+startDate+" to "+endDate);
		EventsResources.getInstance().crudCreateEvents(allEvents, persistEvents, eventListName, false, null);
		
		
		if (!isDataSetComplete) {
			throw new IncompleteDataSetException(failingStocks, allEvents, calculatedOutputReturn, "All Indicators couldn't be calculated properly. This may invalidates the dataset for further usage. Stock concerned : "+failingStocks);
		}
		
		return calculatedOutputReturn;
	}
}
