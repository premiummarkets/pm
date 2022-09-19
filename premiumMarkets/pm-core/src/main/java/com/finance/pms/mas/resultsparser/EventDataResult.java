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
package com.finance.pms.mas.resultsparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.mas.dataresults.DataResultKey;



/**
 * The Class EventDataResult.
 * 
 * @author Guillaume Thoreton
 */
public class EventDataResult implements Result {

	private Date date;
	private Integer eventdef;
	private char eventtype;

	/**
	 * Instantiates a new event data result.
	 * 
	 * @param date the date
	 * @param unknownfield the unknownfield
	 * @param eventdef the eventdef
	 * @param eventtype the eventtype
	 * 
	 * @author Guillaume Thoreton
	 */
	EventDataResult(String date,String unknownfield, String eventdef, String eventtype) {
		try {
			this.date = new SimpleDateFormat("yyyyMMdd").parse(date);
		} catch (ParseException e) {
			LOGGER.error("Error parsing Event data Result :"+this.toString(),e);
		}
		this.eventdef = Integer.valueOf(eventdef);
		this.eventtype = eventtype.charAt(0);
	}

	public Object getKey() {
		return new DataResultKey(date,eventdef);
	}

	public Object getValue() {
		return new EventValue(date, EventDefinition.valueOf(eventdef), EventType.valueOf(eventtype),"");
	}


	@Override
	public String toString() {
		return date.toString() + " ; " + eventdef + " ; " + eventtype;
	}
}
