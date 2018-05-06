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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;

public abstract class Signal {

	protected Integer signalWeight;
	protected Collection<String> eventDefList;
	protected Date latestRelevantEventDate;
	protected Set<EventInfo> parsedEventDefs;

	public Signal(Collection<String> eventDefList) {
		this.signalWeight = 0;
		this.eventDefList = eventDefList;
		this.parsedEventDefs = new HashSet<EventInfo>();
	}

	public Signal() {
		this.signalWeight = 0;
		this.eventDefList = new ArrayList<String>();
	}

	public Integer getSignalWeight() {
		return signalWeight;
	}

	public abstract Integer addEvent(EventKey eventKey, EventValue eventValue);

	public Date getLatestRelevantEventDate() {
		return latestRelevantEventDate;
	}

	public Set<EventInfo> getParsedEventDefs() {
		return parsedEventDefs;
	}

	protected void setSignalWeight(Integer signalWeight) {
		this.signalWeight = signalWeight;
	}


}
