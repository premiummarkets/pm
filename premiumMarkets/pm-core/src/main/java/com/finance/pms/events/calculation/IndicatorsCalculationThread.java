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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;

public abstract class IndicatorsCalculationThread extends EventsCalculationThread {
	
	private static MyLogger LOGGER = MyLogger.getLogger(IndicatorsCalculationThread.class);

	protected Stock stock;
	protected Boolean persistTrainingEvents;
	private Boolean persistEvents;

	protected IndicatorsCalculationThread(Stock stock, Date startDate, Date endDate, String eventListName, Currency  calculationCurrency, 
											Set<Observer> observers, Boolean keepCache, Boolean persistEvents, Boolean persistTrainingEvents, 
											Queue eventQueue, JmsTemplate jmsTemplate) throws NotEnoughDataException {
		
		super(startDate, endDate, eventListName, calculationCurrency, observers, keepCache, eventQueue, jmsTemplate);
		
		this.stock = stock;
		this.persistTrainingEvents = persistTrainingEvents;
		this.persistEvents = persistEvents;
	}

	protected abstract void setCalculationParameters();


	public SymbolEvents call() throws IncompleteDataSetException {
		
		SymbolEvents symbolEventsForStock = new SymbolEvents(stock);
		List<IncompleteDataSetException> dataSetExceptions = new ArrayList<IncompleteDataSetException>();
		
		try {
			
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));

			LOGGER.debug("Analysing events for "+stock+", starting at "+startDate);
			
			setCalculationParameters();
			calculate(symbolEventsForStock, dataSetExceptions);
			
			LOGGER.debug("End analyse "+stock+" from "+startDate+" to "+endDate);
			
		} catch (Exception e) {
			LOGGER.error("UnHandled error : While calculating Events for "+stock+", analysis "+eventListName+" and dates "+startDate+" to "+endDate, e);
			throw new IncompleteDataSetException(stock, symbolEventsForStock, "UnHandled error : " + e.getMessage());
			
		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}
		
		if (dataSetExceptions.size() > 0) {
			throw new IncompleteDataSetException(stock, symbolEventsForStock, "Invalid data set for "+stock.getFriendlyName()+" may invalidate further usage.");
		}
		
		return symbolEventsForStock;
		
	}

	protected void calculate(SymbolEvents symbolEventsForStock, List<IncompleteDataSetException> dataSetExceptions) throws NotEnoughDataException, InvalidAlgorithmParameterException {
		
		Set<EventCompostionCalculator> eventsCalculators;
		
		LOGGER.info("Effective recalculation (potentially incremental) for "+stock+" will occur from "+startDate+" to "+endDate);
		
		//Init calculators
		try {
			eventsCalculators = initIndicatorsAndCalculators(symbolEventsForStock, observers.toArray(new Observer[]{}));
		} catch (IncompleteDataSetException e) {
			dataSetExceptions.add(e);
			eventsCalculators = e.getValidEventCalculators();
		}
		
		//Run calculators
		try {
			calculateEventsForEachDateAndIndicatorComp(eventsCalculators, symbolEventsForStock, startDate, endDate, persistEvents, stock);
		} catch (IncompleteDataSetException e) {
			dataSetExceptions.add(e);
		}
		
	}

	abstract protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(SymbolEvents symbolEventsForStock, Observer... observers) throws IncompleteDataSetException;
	
	private void calculateEventsForEachDateAndIndicatorComp(Set<EventCompostionCalculator> evtCalculators, final SymbolEvents symbolEventsForStock, final Date datedeb, final Date datefin, Boolean persist, final Stock stock) throws IncompleteDataSetException { 

		try {
			cleanEventsFor(this.eventListName, datedeb, datefin, persist);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		
		Boolean incomplete = false;
		ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getMyPrefs().get("indicEventsCalculator.semaphore.eventthread","1")));
		final List<EventInfo> failing = new ArrayList<EventInfo>();
		for (final EventCompostionCalculator evtCalculator: evtCalculators ) {

				Runnable runnable = new Runnable() {
					public void run() {
						
						try {
							
							ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, IndicatorsCalculationThread.this.configs.get(Config.EVENT_SIGNAL_NAME));
							ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, IndicatorsCalculationThread.this.configs.get(Config.INDICATOR_PARAMS_NAME));

							Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, datedeb, datefin, true, stock.getMarketValuation().getCurrency(), evtCalculator.getStartShift(), evtCalculator.quotationsValidity());
							SortedMap<EventKey, EventValue> calculatedEventsForCalculator = evtCalculator.calculateEventsFor(quotations, IndicatorsCalculationThread.this.eventListName);
							
							if (calculatedEventsForCalculator != null && !calculatedEventsForCalculator.isEmpty()) {
								//Add events to total
								symbolEventsForStock.addEventResultElement(calculatedEventsForCalculator, EventDefinition.loadMaxPassPrefsEventInfo());

								//Add events to composer and send
								SymbolEvents symbolEventsForStockAndCalculator = new SymbolEvents(stock);
								symbolEventsForStockAndCalculator.addEventResultElement(calculatedEventsForCalculator, evtCalculator.getEventDefinition());
								sendEvent(eventListName, symbolEventsForStockAndCalculator, evtCalculator.getSource(), calculatedEventsForCalculator.lastKey().getEventType(), evtCalculator.getEventDefinition());
							}
							//Add output to total
							symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), evtCalculator.calculationOutput());
						
						} catch (NoQuotationsException e) {
							LOGGER.warn(e);
							failing.add(evtCalculator.getEventDefinition());
							symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), new TreeMap<Date, double[]>());
						} catch (Exception e) {
							LOGGER.error(e , e);
							failing.add(evtCalculator.getEventDefinition());
							symbolEventsForStock.addCalculationOutput(evtCalculator.getEventDefinition(), new TreeMap<Date, double[]>());
						}
					}
				};
				executor.execute(runnable);
			
		}
		
		executor.shutdown();
		
		try {
			boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
			if (!awaitTermination) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow, new Exception());
				incomplete = true;
			}
		} catch (InterruptedException e) {
			List<Runnable> shutdownNow = executor.shutdownNow();
			LOGGER.error(shutdownNow, e);
			incomplete = true;
		}
		
		if (incomplete || !failing.isEmpty()) throw new IncompleteDataSetException(stock, symbolEventsForStock, "Some calculations have failed! Are failing : "+failing);
		
	}

	protected String warnMessage(String calculatorName, Date startDate, Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("%s can't be calculated for %s between %s and %s", calculatorName, stock.getName(), simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
	}

	protected boolean checkWanted(EventDefinition eventDefinition) {
		 List<EventInfo> wantedEventCalculators = getWantedEventCalculations();
		return (wantedEventCalculators != null && wantedEventCalculators.contains(eventDefinition));
	}

	protected abstract List<EventInfo> getWantedEventCalculations();
	
}
