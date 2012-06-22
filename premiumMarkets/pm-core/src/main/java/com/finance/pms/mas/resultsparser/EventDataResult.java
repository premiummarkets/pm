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
package com.finance.pms.mas.resultsparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finance.pms.mas.dataresults.DataResultKey;
import com.finance.pms.mas.dataresults.DataResultValue;



// TODO: Auto-generated Javadoc
/**
 * The Class EventDataResult.
 * 
 * @author Guillaume Thoreton
 */
public class EventDataResult implements Result {
	
	/** The Constant dfevent. */
	//public static final DateFormat dfevent = new SimpleDateFormat("yyyyMMdd");
	
	/** The date. */
	private Date date;
	
	/** The eventdef. */
	private Integer eventdef;
	//private Integer eventtype;
	/** The eventtype. */
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
		this.eventdef = new Integer(eventdef);
		this.eventtype = eventtype.charAt(0);
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.mas.resultsparser.Result#getKey()
	 */
	public Object getKey() {
		//return new DataResultKey(date,eventtype);
		return new DataResultKey(date,eventdef);
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.mas.resultsparser.Result#getValue()
	 */
	public Object getValue() {
		return new DataResultValue(date,eventdef, eventtype);
	}
	
//	public static char[] getEventTypes() {
//		Arrays.sort(eventTypes);
//		return eventTypes;
//	}
	
	/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
	public String toString() {
		return date.toString()+" ; "+eventdef+" ; "+eventtype;
	}
}
