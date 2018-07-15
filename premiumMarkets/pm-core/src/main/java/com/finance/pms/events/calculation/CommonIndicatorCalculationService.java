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
package com.finance.pms.events.calculation;

import java.security.InvalidAlgorithmParameterException;
import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
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
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.TunedConfMgr;
import com.finance.pms.threads.ObserverMsg;


/**
 * The Class TalibIndicatorCalculationService.
 * 
 * @author Guillaume Thoreton
 */
public class CommonIndicatorCalculationService extends IndicatorsCalculationService {

	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(CommonIndicatorCalculationService.class);

	private Map<EventDefinition, Class<IndicatorsOperator>> availSecondPIndCalculators;

	/**
	 * Instantiates a new talib indicator calculation service.
	 * Initialized in spring context
	 */
	public CommonIndicatorCalculationService(Map<EventDefinition, Class<IndicatorsOperator>> availableSecondPassIndicatorCalculators, JmsTemplate jmsTemplate, Queue eventQueue) {
		this.eventQueue = eventQueue;
		this.jmsTemplate = jmsTemplate;
		this.availSecondPIndCalculators = availableSecondPassIndicatorCalculators;

	}


	@Override
	protected Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> analyseSymbolCollection(
			Collection<Stock> symbols, Date dateDeb, Date dateFin, Currency calculationCurrency, String eventListName, 
			String periodType, Boolean keepCache, Integer passNumber, String passOneCalcMode, Observer...observers) 
					throws InvalidAlgorithmParameterException, IncompleteDataSetException {

		//TODO deal with the periodType
		if (!periodType.equals("daily")) throw new UnsupportedOperationException("Period != daily. Fix me!");

		if (null == dateDeb || null == dateFin) {
			throw new InvalidAlgorithmParameterException("",new Throwable());
		}

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateDeb);
			Integer minDiff = new Integer("-"+MainPMScmd.getMyPrefs().get("talib.daysbackwardday","10"));
			QuotationsFactories.getFactory().incrementDate(calendar, -minDiff);
			dateDeb =  calendar.getTime();

		} catch (NumberFormatException e) {
			LOGGER.error("Invalid number of days backward : "+"-"+MainPMScmd.getMyPrefs().get("talib.daysbackwardday","10"),e);
		}

		LOGGER.debug("Events calculation real date range : from "+dateDeb+" to "+dateFin);
		return allEventsCalculation(symbols, dateDeb, dateFin, calculationCurrency, eventListName, passNumber, passOneCalcMode, observers);

	}


	private Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> allEventsCalculation(
			Collection<Stock> stList, Date startDate, Date endDate, 
			Currency calculationCurrency, String eventListName, int passNumber, String passOneCalcMode, Observer... observers) 
					throws IncompleteDataSetException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy HH:mm:ss");

		Map<Stock,Map<EventInfo, SortedMap<Date, double[]>>> calculatedOutputReturn =  new HashMap<Stock, Map<EventInfo, SortedMap<Date, double[]>>>(1);

		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getMyPrefs().get("indicatorcalculator.semaphore.nbthread","20")));
		List<Future<SymbolEvents>> futures = new ArrayList<Future<SymbolEvents>>();
		int obsSize = stList.size() + 1;
		for (Observer observer : observers) {
			observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.INITMSG, obsSize));
		}
		Set<Observer> obsSet = new HashSet<Observer>(Arrays.asList(observers));

		Boolean isDataSetComplete = true;
		for (final Stock stock : stList) {

			try {
				LOGGER.guiInfo("Calculation requested : pass "+passNumber+" events for stock "+stock.toString()+ " between "+dateFormat.format(startDate)+" and "+dateFormat.format(endDate));

				final Queue eventQueue = this.eventQueue;
				final JmsTemplate jmsTemplate = this.jmsTemplate;

				IndicatorsCalculationThread calculationRunnableTarget = null;
				Currency stockCalcCurrency = (calculationCurrency == null)? stock.getMarketValuation().getCurrency() : calculationCurrency;

				//Adjust calculation date to available quotes
				Date adjustedStartDate = TunedConfMgr.getInstance().minimumStartDate(stock);
				if (adjustedStartDate.before(startDate) || adjustedStartDate.equals(startDate)) {
					adjustedStartDate = startDate;
				} else {
					LOGGER.info("Start date calculation adjusted : pass "+passNumber+" events for stock "+stock.toString()+ " is now starting on "+adjustedStartDate);
				}
				if (adjustedStartDate.after(endDate) || adjustedStartDate.equals(endDate)) {
					LOGGER.warn("Not enough quotations to calculate (invalid date bounds) : pass "+passNumber+" events for stock "+stock.toString()+ " between "+adjustedStartDate+" and "+endDate);
					continue;
				}
				Date adjustedEndDate = TunedConfMgr.getInstance().maximumEndDate(stock);
				if (adjustedEndDate.after(endDate) || adjustedEndDate.equals(endDate)) {
					adjustedEndDate = endDate;
				} else {
					LOGGER.info("End Date calculation adjusted : pass "+passNumber+" events for stock "+stock.toString()+ " is now ending on  "+adjustedEndDate);
				}
				if (adjustedEndDate.before(adjustedStartDate) || adjustedEndDate.equals(adjustedStartDate)) {
					LOGGER.warn("Not enough quotations to calculate (invalid date bounds) : pass "+passNumber+" events for stock "+stock.toString()+ " between "+adjustedStartDate+" and "+adjustedEndDate);
					continue;
				}

				LOGGER.info("Final dates adjusted : pass "+passNumber+" events for stock "+stock.toString()+ " are now from "+adjustedStartDate+" to "+adjustedEndDate);
				//Calculations
				if (passNumber == 1) {//pass 1

					calculationRunnableTarget = new FirstPassIndicatorCalculationThread(
							stock, adjustedStartDate, adjustedEndDate, stockCalcCurrency, eventListName, obsSet,
							passOneCalcMode, eventQueue, jmsTemplate);

				} else {//pass 2

					calculationRunnableTarget = new SecondPassIndicatorCalculationThread(
							stock, adjustedStartDate, adjustedEndDate, stockCalcCurrency, eventListName, obsSet,
							availSecondPIndCalculators, eventQueue, jmsTemplate);

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
			LOGGER.error(shutdownNow, e);
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
				Map<EventInfo, SortedMap<Date, double[]>> calculationOutput = se.getCalculationOutputs();
				if (calculationOutput == null) calculationOutput = new HashMap<EventInfo, SortedMap<Date,double[]>>();
				calculatedOutputReturn.put(se.getStock(), calculationOutput);

			} catch (Exception e1) {

				isDataSetComplete = false;

				if (e1.getCause() != null && e1.getCause() instanceof IncompleteDataSetException) {
					LOGGER.warn(e1,e1);
					failingStocks.addAll(((IncompleteDataSetException) e1.getCause()).getFailingStocks());
					allEvents.addAll(((IncompleteDataSetException) e1.getCause()).getSymbolEvents());
					Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> calculatedOutput = ((IncompleteDataSetException) e1.getCause()).getCalculatedOutputs();
					calculatedOutputReturn.putAll(calculatedOutput);
				} else {
					LOGGER.error(e1, e1);
				}

			}
		}

		try {

			if (LOGGER.isInfoEnabled()) {
				try {
					int nbEvents = 0;
					String eventDefs = "";
					String stocksNames = "";
					String sep= "";
					for (SymbolEvents se : allEvents) {
						stocksNames = stocksNames + sep + se.getStock().getSymbol();
						eventDefs = eventDefs + sep + se.getEventDefList().toString();
						sep = ",";
						nbEvents = nbEvents + se.getSortedDataResultList().size();
					}
					String stocksHint = (allEvents.size() > 1)?allEvents.size()+ " stocks":stocksNames;
					LOGGER.info("Storing pass "+passNumber+" for "+stocksHint+", analysis "+ eventListName+ ", event defs (in SymbolEvents.getEventDefList())"+ eventDefs +", from "+startDate+" to "+endDate);
					LOGGER.guiInfo("Storing pass "+passNumber+" ("+nbEvents + " events for "+stocksHint+"), from "+dateFormat.format(startDate)+" to "+dateFormat.format(endDate));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}

			EventsResources.getInstance().crudCreateEvents(allEvents, eventListName);

			for (Observer observer : observers) {
				observer.update(null, new ObserverMsg(null, ObserverMsg.ObsKey.NONE));
			}

		} catch (Exception e) {	
			isDataSetComplete = false;

			if (e.getCause() != null && (e.getCause() instanceof SQLIntegrityConstraintViolationException || e.getCause() instanceof BatchUpdateException)) {
				LOGGER.warn("Intercepted : "+e+" -> IncompleteDataset");
			} else {
				LOGGER.error(e,e);
			}

		}

		if (!isDataSetComplete) {
			throw new IncompleteDataSetException(failingStocks, allEvents, calculatedOutputReturn, "All Indicators couldn't be calculated properly. This may invalidates the dataset for further usage. Stock concerned : "+failingStocks);
		}

		return calculatedOutputReturn;
	}
}
