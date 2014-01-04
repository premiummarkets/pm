/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.events.operations.conditional;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class EventDataValue extends Value<SortedMap<EventKey, EventValue>> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventDataValue.class);
	
	private SortedMap<EventKey, EventValue> eventData;


	public EventDataValue() {
		super();
		eventData = new TreeMap<EventKey, EventValue>();
	}

	public EventDataValue(SortedMap<EventKey, EventValue> eventData) {
		super();
		this.eventData = eventData;
	}


	@Override
	public SortedMap<EventKey, EventValue> getValue(TargetStockInfo targetStock) {
		return eventData;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+eventData.size() + ((eventData.size() > 0)?", first key "+eventData.firstKey()+ ", last key "+eventData.lastKey():"");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			EventDataValue clone = (EventDataValue) super.clone();
			clone.eventData = (SortedMap<EventKey, EventValue>) ((TreeMap<EventKey, EventValue>)this.eventData).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
