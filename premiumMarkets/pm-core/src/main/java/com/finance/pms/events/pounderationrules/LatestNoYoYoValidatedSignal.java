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

import java.util.Date;
import java.util.Set;

import com.finance.pms.alerts.AlertOnThresholdType;
import com.finance.pms.events.AlertEventKey;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;

public class LatestNoYoYoValidatedSignal extends LatestEventsSignal {

	private Set<String> filteredEventDef;
	private int nbBullish;
	private int nbBearish;
	private int nbTrendChange;

	public LatestNoYoYoValidatedSignal(Set<String> eventDefs) {
		super(true, true);
		this.filteredEventDef = eventDefs;
	}

	@Override
	protected Boolean isFilteredEvent(EventValue eventValue) {
		return filteredEventDef.contains(eventValue.getEventDef().getEventDefinitionRef());
	}

	@Override
	//Remember that the events are screened backward.
	public Integer addFilteredEvent(EventKey eventKey, EventValue eventValue) {

		if (eventKey.getEventInfo().equals(EventDefinition.ALERTTHRESHOLD)) {
			AlertOnThresholdType alertType = ((AlertEventKey) eventKey).getAlertType();
			if (alertType.equals(AlertOnThresholdType.ABOVE_TAKE_PROFIT_LIMIT) || alertType.equals(AlertOnThresholdType.BELOW_MAX_LOSS_LIMIT)) {
				this.signalWeight--;
				recordAsTriggeringEvent(eventKey.getDate(), eventKey.getEventInfo());
				return 1; //Adding up
			}
			//Too volatile
//			if (alertType.equals(AlertOnThresholdType.BELOW_ZERO_WEIGHTED_PROFIT_LIMIT)) { 
//				nbBearish++;
//				recordAsTriggeringEvent(eventKey.getDate(), eventKey.getEventInfo());
//				return 1;  //Adding up
//			}
		} else {
			if (eventKey.getEventType().equals(EventType.BEARISH)) {
				nbBearish++;
				checkTrendChange(eventValue);
				recordAsTriggeringEvent(eventKey.getDate(), eventKey.getEventType(), eventKey.getEventInfo());
				return 1; //We carry on to detect yoyos
			} else if (eventKey.getEventType().equals(EventType.BULLISH)) {
				nbBullish++;
				checkTrendChange(eventValue);
				recordAsTriggeringEvent(eventKey.getDate(), eventKey.getEventType(), eventKey.getEventInfo());
				return 1; //We carry on to detect yoyos
			}
		}

		return 0;
	}

	private void checkTrendChange(EventValue eventValue) {
		if (lastParsedEventType != eventValue.getEventType()) nbTrendChange ++;
		lastParsedEventType = eventValue.getEventType();
	}

	private void recordAsTriggeringEvent(Date date, EventInfo eventDefinition) {
		if (this.latestRelevantEventDate == null) this.latestRelevantEventDate = date;
		this.parsedEventDefs.add(eventDefinition);
	}


	public int getNbBullish() {
		return nbBullish;
	}

	public int getNbBearish() {
		return nbBearish;
	}

	public int getNbTrendChange() {
		return nbTrendChange;
	}

}
