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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.ConfigThreadLocal;

//TODO To facilitate error handling : move initialisations from the constructor to calculateEventsFor method
public class SecondPassIndicatorCalculationThread extends IndicatorsCalculationThread {
	
	private static MyLogger LOGGER = MyLogger.getLogger(SecondPassIndicatorCalculationThread.class);
	private Map<EventDefinition, Class<EventCompostionCalculator>> availableSecondPassIndicatorCalculators;
	private List<EventInfo> secondPassWantedCalculations;

	protected SecondPassIndicatorCalculationThread(
			Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, 
			Set<Observer> observers, Map<EventDefinition, Class<EventCompostionCalculator>> availableSecondPassIndicatorCalculators,
			Boolean keepCache, Queue eventQueue, JmsTemplate jmsTemplate, Boolean persistEvents, Boolean persistTrainingEvents) throws NotEnoughDataException {
		super(stock, startDate, endDate, eventListName, calculationCurrency, observers, keepCache, persistEvents, persistTrainingEvents, eventQueue, jmsTemplate);
		
		this.availableSecondPassIndicatorCalculators = availableSecondPassIndicatorCalculators;
	
	}

	@Override
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(SymbolEvents symbolEventsForStock, final Observer... observers) throws IncompleteDataSetException {
		
		final Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		Boolean isDataSetComplete = true;
		String failingCalculators = "";
		for (EventDefinition eventDefinition : availableSecondPassIndicatorCalculators.keySet()) {
			if (checkWanted(eventDefinition)) {

				final Class<EventCompostionCalculator> eventCompositionCalculator = availableSecondPassIndicatorCalculators.get(eventDefinition);

				List<EventInfo> eventInfos = subEventInfosForRequested(eventDefinition);
				for (final EventInfo eventInfo : eventInfos) {
					try {
						EventCompostionCalculator instanciatedECC = instanciateECC(eventInfo, eventCompositionCalculator, observers);
						eventCalculations.add(instanciatedECC);
					} catch (InvocationTargetException e) {
						isDataSetComplete = false;
						failingCalculators = failingCalculators + eventInfo.getEventDefinitionRef() + ", ";
						symbolEventsForStock.addCalculationOutput(eventInfo, new TreeMap<Date, double[]>());
					} catch (Throwable e) {
						isDataSetComplete = false;
						failingCalculators = failingCalculators + eventInfo.getEventDefinitionRef() + ", ";
						symbolEventsForStock.addCalculationOutput(eventInfo, new TreeMap<Date, double[]>());
						LOGGER.warn(e,e);
					}
				}
			}
		}
		
		if (!isDataSetComplete) throw new IncompleteDataSetException(stock, eventCalculations, "Second pass data set is incomplete for "+stock+ ". Are failing : "+failingCalculators);
		
		return eventCalculations;
	}


	private List<EventInfo> subEventInfosForRequested(EventDefinition eventDefinition) {
		
		List<EventInfo> ret = new ArrayList<EventInfo>();
		switch(eventDefinition) {
		case PARAMETERIZED :
			ret.addAll(EventDefinition.loadAllParameterized());
			break;
		default : 
			ret.add(eventDefinition);
		}
		return ret;
	}

	@Override
	protected void setCalculationParameters() {
		secondPassWantedCalculations = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getIndepIndicators();
	}
	
	private EventCompostionCalculator instanciateECC(EventInfo eventInfo, Class<EventCompostionCalculator> eventCompositionCalculator, Observer[] observers) throws Throwable {
		
		try {
			
			Constructor<EventCompostionCalculator> constructor = eventCompositionCalculator.getConstructor(EventInfo.class, Stock.class, Date.class, Date.class, Currency.class, String.class, Boolean.class, Observer[].class);
			return constructor.newInstance(eventInfo, stock, startDate, endDate, calculationCurrency, eventListName, persistTrainingEvents, observers);
		
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof WarningException || e.getCause() instanceof NotEnoughDataException) {
				if (e.getCause() instanceof NotEnoughDataException || (e.getCause().getCause() != null && e.getCause().getCause() instanceof NotEnoughDataException) ) {
					LOGGER.warn("Failed calculation : Not Enough Data " + warnMessage(eventInfo.toString(), startDate, endDate));
				} else {
					LOGGER.warn("Failed calculation : " + warnMessage(eventInfo.toString(), startDate, endDate)+ " cause : \n" + e.getCause());
				}
				if (e.getCause() != null) throw e.getCause();
			} else if (e.getCause() instanceof ErrorException) {
				LOGGER.error(stock + " second pass calculation error ", e);
				if (e.getCause() != null) throw e.getCause();
			} else {
				LOGGER.error(
						String.format("%s second pass calculation un handled error.\n"
						+ "Parameters :\n"
						+ "event def = %s, start date = %s, end date = %s, calc currency = %s, event list name = '%s', is training persisted = %s ", stock, eventInfo, startDate, endDate, calculationCurrency, eventListName, persistTrainingEvents), e);
				if (e.getCause() != null) throw e.getCause();
			}
			if (e.getCause() != null) throw e.getCause(); else throw e;
		} catch (Exception e) {
			LOGGER.error(stock+ " second pass calculation error ", e);
			throw e;
		}
	}

	@Override
	protected List<EventInfo> getWantedEventCalculations() {
		return secondPassWantedCalculations;
	}
	
	@Override
	public void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist) {

		for (EventDefinition eventDefinition : availableSecondPassIndicatorCalculators.keySet()) {
			if (checkWanted(eventDefinition)) {
				
				LOGGER.info("Cleaning "+eventDefinition+" events BEFORE STORING NEW RESULTS for "+eventListName+" and "+ stock.getFriendlyName() +" from "+datedeb + " to "+datefin);
				
				List<EventInfo> subEventInfosForRequested = subEventInfosForRequested(eventDefinition);
				EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, datedeb, datefin, persist, subEventInfosForRequested.toArray(new EventInfo[0]));
				
			}
		}
	}
	
}
