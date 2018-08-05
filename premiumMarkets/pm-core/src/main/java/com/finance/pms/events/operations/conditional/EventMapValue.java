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
package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.StringableMapValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.ChartableMapValue;

@XmlRootElement
public class EventMapValue extends ChartableMapValue implements StringableMapValue {

	protected static MyLogger LOGGER = MyLogger.getLogger(EventMapValue.class);

	private SortedMap<EventKey, EventValue> eventData;

	public EventMapValue() {
		super();
		eventData = new TreeMap<EventKey, EventValue>();
	}

	public EventMapValue(SortedMap<EventKey, EventValue> eventData) {
		super();
		this.eventData = eventData;
	}


	@Override
	public SortedMap<Date, Double> getValue(TargetStockInfo targetStockInfo) {

		return eventData.keySet().stream().collect(Collectors.toMap(e -> e.getDate(), e -> {
			EventType t = e.getEventType();
			switch(t) {
			case BULLISH : return 1d;
			case BEARISH : return -1d;
			default : return 0d;
			}
		}, (a,b) -> a + b, TreeMap::new)); //XXX there may be merge conflicts as Date is not a unique key in SortedMap<EventKey, EventValue>

	}

	public SortedMap<EventKey, EventValue> getEventMap() {
		return eventData;
	}

	@Override
	public SortedMap<Date, String> getValueAsStringMap() {
		return eventData.keySet().stream().collect(Collectors.toMap(e -> e.getDate(), e -> e.getEventType().toString(), (a,b) -> EventType.NONE.toString(), TreeMap::new));
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+eventData.size() + ((eventData.size() > 0)?", first key "+eventData.firstKey()+ ", last key "+eventData.lastKey():"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			EventMapValue clone = (EventMapValue) super.clone();
			clone.eventData = (SortedMap<EventKey, EventValue>) ((TreeMap<EventKey, EventValue>)this.eventData).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public List<Date> getDateKeys() {
		return eventData.keySet().stream().map(k -> k.getDate()).collect(Collectors.toList());
	}

}
