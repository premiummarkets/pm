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
package com.finance.pms.events.pounderationrules;

import java.util.HashMap;
import java.util.HashSet;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.talib.dataresults.AlertEventValue;
import com.finance.pms.threads.ConfigThreadLocal;

public class LatestEventsSignal extends Signal {
	
	static MyLogger LOGGER = MyLogger.getLogger(LatestEventsSignal.class);

	protected EventType lastParsedEventType;
	private Boolean alerts;
	private Boolean indicators;

	public LatestEventsSignal(Boolean alerts, Boolean indicators) {
		super(new HashMap<Integer, String>());
		this.alerts = alerts;
		this.indicators = indicators;
	}

	@Override
	public Integer addEvent(EventValue eventValue) {

		if (isFilteredEvent(eventValue)) {//Filtered
			return addFilteredEvent(eventValue);
		} else { // Not filtered
			return 0;
		}
	}
	
	protected Integer addFilteredEvent(EventValue eventValue) {
		
		if (isSameTrend(eventValue)) { //Same trend
			if (!isAlreadyChecked(eventValue)) {
				//Good : increment
				listTriggeringEvent(eventValue, eventValue.getEventType(), eventValue.getEventDef());
				incrementWeight(eventValue);
				return 1;
			} else {//Same event def : ignore
				return 0;
			}
		} else { //Different trend
			return eventTypeDivergence(eventValue);
		}
	}

	/**
	 * @param eventValue
	 * @return
	 */
	protected boolean isAlreadyChecked(EventValue eventValue) {
		return parsedEventDefs.contains(eventValue.getEventDef());
	}


	/**
	 * @param eventValue
	 * @return
	 */
	protected boolean isSameTrend(EventValue eventValue) {
		return this.lastParsedEventType == null || this.lastParsedEventType.equals(eventValue.getEventType()) || this.lastParsedEventType.equals(EventType.NONE);
	}

	protected void incrementWeight(EventValue eventValue) {
		if (EventDefinition.ALERTTHRESHOLD.equals(eventValue.getEventDef())&&  eventValue.getEventType().equals(EventType.BEARISH)) {
			this.signalWeight = this.signalWeight + ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold();
		} else if (EventDefinition.ALERTTHRESHOLD.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.BULLISH)) {
			this.signalWeight = this.signalWeight + ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold();
		} else if (EventDefinition.SCREENER.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.BEARISH)) {
			this.signalWeight = this.signalWeight +((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold() - ((AlertEventValue) eventValue).extractAdditionalRankWeight();
		} else if (EventDefinition.SCREENER.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.BULLISH)) {
			this.signalWeight = this.signalWeight + ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold() + ((AlertEventValue) eventValue).extractAdditionalRankWeight();
		} else if (eventValue.getEventType().equals(EventType.BULLISH)) {
			this.signalWeight++;
		} else if (eventValue.getEventType().equals(EventType.BEARISH)) {
			this.signalWeight--;
		}
	}
	
	/**
	 * @param eventValue
	 * @return
	 */
	protected Integer eventTypeDivergence(EventValue eventValue) {
		//Different event type (ie Trend) : stop - we are only interested in the latest indicators trend or potentially some other alerts if nothing else
		if (isAlert(eventValue)) {
			resetTriggeringEvent(eventValue, eventValue.getEventType(), eventValue.getEventDef());
			incrementWeight(eventValue);
			return 1;
		} else {
			return -1;
		}
		
	}

	/**
	 * @param eventValue
	 * @param currentEventType
	 * @param currentEventDef
	 * @return
	 */
	protected void listTriggeringEvent(EventValue eventValue, EventType eventType, EventDefinition eventDefinition) {
			if (this.latestEventDate == null) this.latestEventDate = eventValue.getDate();
			this.lastParsedEventType = eventType;
			this.parsedEventDefs.add(eventDefinition);
	}
	
	private void resetTriggeringEvent(EventValue eventValue, EventType eventType, EventDefinition eventDefinition) {
		
		signalWeight = 0;
		
		this.latestEventDate = eventValue.getDate();
		this.lastParsedEventType = eventValue.getEventType();
		this.parsedEventDefs = new HashSet<EventDefinition>();
		this.parsedEventDefs.add(eventValue.getEventDef());
}


	/**
	 * @param eventValue
	 * @param isOkEvent
	 * @return
	 */
	protected Boolean isFilteredEvent(EventValue eventValue) {
		
		if (eventValue.getEventType().equals(EventType.INFO)) return false;
		if (eventValue.getEventType().equals(EventType.NONE)) return false;
		
		if (alerts && isAlert(eventValue)) {
			return true;
		}
		
		Boolean isOkEvent = false;
		if (indicators && eventValue.getEventType().equals(EventType.BEARISH)) {
			for (String si : ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellIndicators()) {
				isOkEvent  = eventValue.getEventDef().name().equals(si);
				if (isOkEvent) return true;
			}
		} else if (indicators && eventValue.getEventType().equals(EventType.BULLISH)) {
			for (String si : ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyIndicators()) {
				isOkEvent = eventValue.getEventDef().name().equals(si);
				if (isOkEvent) return true;
			}
		}
		return isOkEvent;
	}

	/**
	 * @param eventValue
	 * @return
	 */
	private boolean isAlert(EventValue eventValue) {
		return (eventValue.getEventDef().equals(EventDefinition.ALERTTHRESHOLD) || eventValue.getEventDef().equals(EventDefinition.SCREENER));
	}

}




