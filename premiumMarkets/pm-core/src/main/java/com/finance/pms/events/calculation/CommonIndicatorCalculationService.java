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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
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


	/* (non-Javadoc)
	 * @see com.finance.pms.events.calculation.IndicatorCalculationService#analyseSymbolCollection(java.lang.String, java.util.Date, java.util.Date, java.util.Collection)
	 */
	@Override
	protected void analyseSymbolCollection(Collection<Stock> symbols, Date dateDeb, Date dateFin, Currency calculationCurrency, String eventListName, 
										   String periodType, Boolean keepCache, Integer passNumber, Boolean export, Boolean persistEvents) throws InvalidAlgorithmParameterException, IncompleteDataSetException {
		
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
		this.allEventsCalculation(symbols, dateDeb, dateFin, calculationCurrency, eventListName, keepCache, passNumber, export, persistEvents);
		
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
	 * @throws IncompleteDataSetException 
	 */
	private void allEventsCalculation(Collection<Stock> stList, final Date startDate, final Date endDate, Currency calculationCurrency, final String eventListName, 
									  final Boolean keepCache, int passNumber, Boolean export, Boolean persistEvents) throws IncompleteDataSetException {
		
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("indicatorcalculator.semaphore.nbthread","20")));
		List<Future<SymbolEvents>> futures = new ArrayList<Future<SymbolEvents>>();
		for (Observer observer : observers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, stList.size()));
		}
		
		Boolean isDataSetComplete = true;
		for (final Stock stock : stList) {
			
			try {
				LOGGER.guiInfo("Calculating pass "+passNumber+" events for stock "+stock.toString()+ " between "+startDate+" and "+endDate);

				final Queue eventQueue = this.eventQueue;
				final JmsTemplate jmsTemplate = this.jmsTemplate;

				IndicatorsCalculationThread calculationRunnableTarget;
				Currency stockCalcCurrency = (calculationCurrency == null)?stock.getMarket().getCurrency():calculationCurrency;

				if (passNumber == 2) {
					calculationRunnableTarget = new SecondPassIndicatorCalculationThread(
																						stock, startDate, endDate, stockCalcCurrency, eventListName, observers,
																						availSecondPIndCalculators, export, keepCache, eventQueue, jmsTemplate, persistEvents);
				} else {
					calculationRunnableTarget = new FirstPassIndicatorCalculationThread(
																						stock, startDate, endDate, stockCalcCurrency, eventListName, observers,
																						keepCache, eventQueue, jmsTemplate);
				}
				
				Future<SymbolEvents> submitedRunnable = (Future<SymbolEvents>) executor.submit(calculationRunnableTarget);
				futures.add(submitedRunnable);

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
				LOGGER.error(shutdownNow,new Exception());
				isDataSetComplete = false;
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow,e);
			isDataSetComplete = false;
		}
		
		try {
			List<SymbolEvents> allEvents = new ArrayList<SymbolEvents>();
			for (Future<SymbolEvents> future : futures) {
				allEvents.add(future.get());
			}
			EventsResources.getInstance().storeEvents(allEvents, persistEvents, eventListName);
			
		} catch (InterruptedException e) {
			LOGGER.error(e,e);
			isDataSetComplete = false;
		} catch (ExecutionException e) {
			LOGGER.error(e,e);
			isDataSetComplete = false;
		}
		
		if (!isDataSetComplete) {
			throw new IncompleteDataSetException("All Indicators couldn't be calculated properly. This may invalidates the dataset for further usage.");
		}
	}
}
