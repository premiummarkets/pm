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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventSource;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.threads.ConfigThreadLocal;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;

public abstract class IndicatorsCalculationThread extends EventsCalculationThread {
	
	private static MyLogger LOGGER = MyLogger.getLogger(IndicatorsCalculationThread.class);

	protected Stock stock;
	protected Boolean export;
	protected Boolean persistTrainingEvents;
	private Boolean persistEvents;

	protected IndicatorsCalculationThread(Stock stock, Date startDate, Date endDate, String eventListName, Currency  calculationCurrency, 
											Set<Observer> observers, Boolean export, Boolean keepCache, Boolean persistEvents, Boolean persistTrainingEvents, 
											Queue eventQueue, JmsTemplate jmsTemplate) throws NotEnoughDataException {
		super(startDate, endDate, eventListName, calculationCurrency, observers, keepCache, eventQueue, jmsTemplate);
		
		this.stock = stock;
		this.export = export;
		this.persistTrainingEvents = persistTrainingEvents;
		this.persistEvents = persistEvents;
		
		//setCalculationParameters();
	}

	protected abstract void setCalculationParameters();


	public SymbolEvents call() throws IncompleteDataSetException {
		
		SymbolEvents ret = new SymbolEvents(stock);
		
		try {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME,this.configs.get(Config.INDICATOR_PARAMS_NAME));

			LOGGER.debug("Analysing events for "+stock+", starting at "+startDate);
			
			setCalculationParameters();
			Set<EventCompostionCalculator> eventsCalculators = initIndicatorsAndCalculators(observers.toArray(new Observer[]{}));
			Map<EventKey, EventValue> eventDataAggregation = calculateEventsForEachDateAndIndicatorComp(eventsCalculators, startDate, endDate, persistEvents);

			for (EventValue eventValue : eventDataAggregation.values()) {
				EventSource source = (eventValue.getEventDef().equals(EventDefinition.WEATHER))? EventSource.PMWeather : EventSource.PMTAEvents;
				this.sendEvent(stock, eventListName, eventValue, source);
			}
			
			ret.addEventResultElement(eventDataAggregation, EventDefinition.getEventDefList());
			
			for (EventCompostionCalculator eventCompostionCalculator : eventsCalculators) {
				ret.addCalculationOutput(eventCompostionCalculator.getEventDefinition(), eventCompostionCalculator.calculationOutput());
			}
			
			LOGGER.debug("end analyse "+stock+" from "+startDate+" to "+endDate);
			
		} catch (IncompleteDataSetException e) {
			throw e;
			
		} catch (Exception e) {
			LOGGER.error("ERROR : While calcuting Events for "+stock+", analysis "+this.eventListName+" and dates "+startDate+" to "+endDate, e);
			throw new IncompleteDataSetException(stock, e.getMessage());
			
		} finally {
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
		}
		
		return ret;
		
	}

	abstract protected Set<EventCompostionCalculator> initIndicatorsAndCalculators(Observer... observers) throws NotEnoughDataException, IncompleteDataSetException;

	/**
	 * @param startDate
	 * @param endDate
	 * @param simpleDateFormat
	 * @return
	 */
	protected String warnMessage(String calculatorName, Date startDate, Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("%s can't be calculated for %s between %s and %s", calculatorName, stock.getName(), simpleDateFormat.format(startDate), simpleDateFormat.format(endDate));
	}

	/**
	 * @return
	 */
	protected boolean checkWanted(EventDefinition eventDefinition) {
		 List<EventDefinition> wantedEventCalculators = getWantedEventCalculations();
		return (wantedEventCalculators != null && wantedEventCalculators.contains(eventDefinition));
	}

	protected abstract List<EventDefinition> getWantedEventCalculations();
	
}
