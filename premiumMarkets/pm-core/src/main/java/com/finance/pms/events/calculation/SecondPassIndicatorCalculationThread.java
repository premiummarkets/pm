/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
import com.finance.pms.threads.ConfigThreadLocal;

public class SecondPassIndicatorCalculationThread extends IndicatorsCalculationThread {
	
	private static MyLogger LOGGER = MyLogger.getLogger(SecondPassIndicatorCalculationThread.class);
	private Map<EventDefinition, Class<EventCompostionCalculator>> availableSecondPassIndicatorCalculators;
	private List<EventDefinition> secondPassWantedCalculations;

	protected SecondPassIndicatorCalculationThread(
			Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, 
			Set<Observer> observers, Map<EventDefinition, Class<EventCompostionCalculator>> availableSecondPassIndicatorCalculators,
			Boolean export, Boolean keepCache, Queue eventQueue, JmsTemplate jmsTemplate, Boolean persistEvents, Boolean persistTrainingEvents) throws NotEnoughDataException {
		super(stock, startDate, endDate, eventListName, calculationCurrency, observers, export, keepCache, persistEvents, persistTrainingEvents, eventQueue, jmsTemplate);
		
		this.availableSecondPassIndicatorCalculators = availableSecondPassIndicatorCalculators;
	
	}

	@Override
	protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(Observer... observers) {
		
		Set<EventCompostionCalculator> eventCalculations = new HashSet<EventCompostionCalculator>();
		
		for (EventDefinition eventDefinition : availableSecondPassIndicatorCalculators.keySet()) {
			if (checkWanted(eventDefinition)) {
				try {
					EventCompostionCalculator instanciatedECC = instanciateECC(eventDefinition, availableSecondPassIndicatorCalculators.get(eventDefinition), observers);
					eventCalculations.add(instanciatedECC);
				} catch (Exception e) {
					LOGGER.warn(e);
				}
			}
		}
		
		return eventCalculations;
	}

	@Override
	protected void setCalculationParameters() {
		secondPassWantedCalculations = ((EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME)).getIndepIndicators();
	}
	
	private EventCompostionCalculator instanciateECC(EventDefinition eventDefinition, Class<EventCompostionCalculator> eventCompositionCalculator, Observer[] observers) throws Exception {
		EventCompostionCalculator ret;
		try {
			
			Constructor<EventCompostionCalculator> constructor = eventCompositionCalculator.getConstructor(Stock.class,Date.class,Date.class,Currency.class,String.class,Boolean.class,Boolean.class, Observer[].class);
			ret = constructor.newInstance(stock, startDate, endDate, calculationCurrency, eventListName, export, persistTrainingEvents, observers);
			return ret;
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof WarningException) {
				LOGGER.warn("Failed calculation : " + warnMessage(eventDefinition.toString(), startDate, endDate)+ " cause : \n" + e.getCause());
			} else if (e.getCause() instanceof NotEnoughDataException ) {
				LOGGER.warn("Failed calculation : NotEnoughDataException!! " + warnMessage(eventDefinition.toString(), startDate, endDate));
			} else if (e.getCause() instanceof ErrorException) {
				LOGGER.error(stock+ " second pass calculation error ",e);
			}
			throw e;
		} catch (Exception e) {
			LOGGER.error(stock+ " second pass calculation error ",e);
			throw e;
		}
	}

	@Override
	protected List<EventDefinition> getWantedEventCalculations() {
		return secondPassWantedCalculations;
	}
	
}
