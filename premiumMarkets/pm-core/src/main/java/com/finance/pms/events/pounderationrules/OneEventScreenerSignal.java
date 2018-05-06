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
package com.finance.pms.events.pounderationrules;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.talib.dataresults.AlertEventValue;
import com.finance.pms.threads.ConfigThreadLocal;


public abstract class OneEventScreenerSignal extends LatestEventsSignal {
	
	private Integer additionalRankWeight;
	private AbstractSharesList portfolio;
	private Stock stock;
	private Boolean isSignalBullish;
	private Boolean isInTopScreened;

	public OneEventScreenerSignal(Stock stock, String portfolioName) {
		super(false, false);
		this.portfolio = PortfolioMgr.getInstance().getPortfolio(portfolioName);
		this.stock = stock;
		this.additionalRankWeight = 0;
		this.isSignalBullish = false;
		this.isInTopScreened = false;
	}

	@Override
	public Integer addEvent(EventKey eventKey, EventValue eventValue) {
		
		if (isFilteredEvent(eventValue)) {
			//Bearish event occurred
			if (getTriggeringEventDef().equals(eventValue.getEventDef()) &&  eventValue.getEventType().equals(EventType.BEARISH) && portfolio.getListShares().containsKey(this.stock)) { 
				listTriggeringEvent(eventValue.getDate(), EventType.BEARISH, getTriggeringEventDef());
				this.signalWeight = ((EventSignalConfig)ConfigThreadLocal.get("eventSignal")).getSellEventTriggerThreshold();
				return -1;
			} 
			
			//Bullish event occurred
			if (getTriggeringEventDef().equals(eventValue.getEventDef()) &&  eventValue.getEventType().equals(EventType.BULLISH)){
				listTriggeringEvent(eventValue.getDate(), EventType.BULLISH, getTriggeringEventDef());
				//Ok now we also need a share in top screener to buy ie screener with type none.
				isSignalBullish = true;

			} 
			
			//Is it a top screened share?
			if (EventDefinition.SCREENER.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.NONE)) {
				listTriggeringEvent(eventValue.getDate(), EventType.BULLISH, EventDefinition.SCREENER);
				this.additionalRankWeight = ((AlertEventValue) eventValue).extractAdditionalRankWeight();
				isInTopScreened = true;
			}
			
			return 1;
		} else { // Not filtered
			this.lastParsedEventType = EventType.NONE;
			return 0;
		}
	}


	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {
		return eventValue.getEventDef().equals(getTriggeringEventDef()) || (EventDefinition.SCREENER.equals(eventValue.getEventDef()) && eventValue.getEventType().equals(EventType.NONE));
	}
	
	public Integer getAdditionalRankWeight() {
		return additionalRankWeight;
	}

	public Boolean getIsSignalBullish() {
		return isSignalBullish;
	}

	public Boolean getIsInTopScreened() {
		return isInTopScreened;
	}
	

	abstract EventDefinition getTriggeringEventDef();
	
}
