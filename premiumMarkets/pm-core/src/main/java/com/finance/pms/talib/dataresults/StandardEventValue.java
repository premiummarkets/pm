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
package com.finance.pms.talib.dataresults;

import java.util.Date;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;



// TODO: Auto-generated Javadoc
/**
 * The Class IndicatorEventValue.
 * 
 * @author Guillaume Thoreton
 */
public class StandardEventValue extends EventValue {
	
	
	private static final long serialVersionUID = -5855030085997834992L;
	
	/**
	 * Instantiates a new indicator event value.
	 * 
	 * @param date the date
	 * @param eventDef the event def
	 * @param eventType the eventtype
	 * @author Guillaume Thoreton
	 */
	public StandardEventValue(Date date, EventInfo eventDef, EventType eventType, String eventListName){
		super(date, eventDef, eventType, eventListName);
		
	}
	
	public StandardEventValue(Date date, EventType eventType, EventInfo eventDef, String message, String eventListName){
		super(date, eventDef, eventType, message, eventListName);

	}
	
	public StandardEventValue(Date date, String eventInfoReference, String eventTypeChar, String message, String eventListName) throws NoSuchFieldException{
		super(date, EventDefinition.valueOfEventInfo(eventInfoReference), EventType.valueOf(eventTypeChar.charAt(0)), message, eventListName);

	}

	public StandardEventValue(EventKey eventKey, String message, String eventListName) {
		super(eventKey.getDate(), eventKey.getEventInfo(), eventKey.getEventType(), message, eventListName);
	}

}