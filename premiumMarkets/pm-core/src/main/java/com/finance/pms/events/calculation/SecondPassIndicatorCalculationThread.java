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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

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
import com.finance.pms.threads.ConfigThreadLocal;

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
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(final Observer... observers) throws IncompleteDataSetException {
		
		final Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		Boolean isDataSetComplete = true;
		for (EventDefinition eventDefinition : availableSecondPassIndicatorCalculators.keySet()) {
			if (checkWanted(eventDefinition)) {
				try {
					
					final Class<EventCompostionCalculator> eventCompositionCalculator = availableSecondPassIndicatorCalculators.get(eventDefinition);
					
					List<EventInfo> eventInfos = subEventInfosForRequested(eventDefinition);
					
					for (final EventInfo eventInfo : eventInfos) {
						EventCompostionCalculator instanciatedECC = instanciateECC(eventInfo, eventCompositionCalculator, observers);
						eventCalculations.add(instanciatedECC);
					}
					
				} catch (InvocationTargetException e) {
					
					isDataSetComplete = false;
					
				} catch (Exception e) {
					
					LOGGER.warn(e,e);
					isDataSetComplete = false;
					
				}
			}
		}
		
		if (!isDataSetComplete) throw new IncompleteDataSetException(stock, eventCalculations, "Second pass data set is incomplete for "+stock);
		
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
	
	private EventCompostionCalculator instanciateECC(EventInfo eventInfo, Class<EventCompostionCalculator> eventCompositionCalculator, Observer[] observers) throws Exception {
		
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
			} else if (e.getCause() instanceof ErrorException) {
				LOGGER.error(stock+ " second pass calculation error ",e);
			} else {
				LOGGER.error(stock+ " second pass calculation unhandled error ",e);
			}
			throw e;
		} catch (Exception e) {
			LOGGER.error(stock+ " second pass calculation error ",e);
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
				
				LOGGER.info("cleaning "+eventDefinition+" events BEFORE STORING NEW RESULTS for "+eventListName+" and "+ stock.getFriendlyName() +" from "+datedeb + " to "+datefin);
				
				List<EventInfo> subEventInfosForRequested = subEventInfosForRequested(eventDefinition);
				EventsResources.getInstance().crudDeleteEventsForStock(stock, eventListName, datedeb, datefin, persist, subEventInfosForRequested.toArray(new EventInfo[0]));
				
			}
		}
	}
	
}
