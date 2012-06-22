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

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.talib.dataresults.AlertEventValue;
import com.finance.pms.threads.ConfigThreadLocal;

public class LatestEventsScreenerBuySignal extends LatestEventsSignal {

	Integer additionalRankWeight;
	
	public LatestEventsScreenerBuySignal(Stock stock, String portfolioName, Boolean alerts, Boolean indicators) {
		super(alerts,indicators);
		this.additionalRankWeight = 0;

	}
	
	@Override
	public Integer addEvent(EventValue eventValue) {
		if (isBullishTrend() && EventDefinition.SCREENER.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.NONE)) {
				listTriggeringEvent(eventValue, EventType.BULLISH, EventDefinition.SCREENER);
				this.additionalRankWeight =  ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getBuyEventTriggerThreshold() + ((AlertEventValue) eventValue).extractAdditionalRankWeight();
				return 1;
		} else {
			return super.addEvent(eventValue);
		}
	}
	
	Boolean isBullishTrend() {
		return this.lastParsedEventType == null || this.lastParsedEventType.equals(EventType.BULLISH) || this.lastParsedEventType.equals(EventType.NONE);
	}
	

	/**
	 * @param eventValue
	 * @param isOkEvent
	 * @return
	 */
	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {

		if ((eventValue.getEventType().equals(EventType.BULLISH) || eventValue.getEventType().equals(EventType.NONE)) && eventValue.getEventDef().equals(EventDefinition.SCREENER)) {
			return true;
		} else {
			return super.isFilteredEvent(eventValue);
		}
	}	
	
	
	
}
