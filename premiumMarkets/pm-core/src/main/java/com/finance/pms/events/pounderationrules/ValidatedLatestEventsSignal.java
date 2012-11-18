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
package com.finance.pms.events.pounderationrules;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;

public class ValidatedLatestEventsSignal extends LatestEventsSignal {

	private EventDefinition filteredEventDef;

	public ValidatedLatestEventsSignal(EventDefinition filteredEventDef) {
		super(false, true);
		this.filteredEventDef = filteredEventDef;
	}

	@Override
	public Integer addFilteredEvent(EventValue eventValue) {
		
//		boolean isSameTrend = isSameTrend(eventValue);
//		if (!isSameTrend) {
//			if (eventValue.getEventType().equals(EventType.BEARISH) || eventValue.getEventType().equals(EventType.NONE)) {//Change from bearish to bullish as we parse from the latest (inv. chronological)
//				this.signalWeight++;
//			} else if (eventValue.getEventType().equals(EventType.BULLISH) || eventValue.getEventType().equals(EventType.NONE)) { //vice versa
//				this.signalWeight--;
//			} 
//		}
		if (eventValue.getEventType().equals(EventType.BEARISH)) {
			this.signalWeight--;
		} else if (eventValue.getEventType().equals(EventType.BULLISH)) {
			this.signalWeight++;
		}
		listTriggeringEvent(eventValue, eventValue.getEventType(), eventValue.getEventDef());
		
		return 1;	
	}
	
	
	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {
		return eventValue.getEventDef().equals(filteredEventDef);
	}

//	@Override
//	protected boolean isSameTrend(EventValue eventValue) {
//		if (this.lastParsedEventType == null || this.lastParsedEventType.equals(EventType.NONE)) return false;
//		return  this.lastParsedEventType.equals(eventValue.getEventType());
//	}

}
