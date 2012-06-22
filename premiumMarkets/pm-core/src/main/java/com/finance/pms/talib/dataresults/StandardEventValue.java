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
package com.finance.pms.talib.dataresults;

import java.util.Date;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;



// TODO: Auto-generated Javadoc
/**
 * The Class IndicatorEventValue.
 * 
 * @author Guillaume Thoreton
 */
public class StandardEventValue extends EventValue {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5855030085997834992L;
	/**
	 * Instantiates a new indicator event value.
	 * 
	 * @param date the date
	 * @param eventDef the event def
	 * @param eventtype the eventtype
	 * @author Guillaume Thoreton
	 */
	public StandardEventValue(Date date,EventDefinition eventDef, EventType eventtype, String eventListName){
		super(date,eventDef,eventtype, eventListName);
		
	}
	
	public StandardEventValue(Date date,EventType eventtype, EventDefinition eventDef, String message, String eventListName){
		super(date,eventDef,eventtype,message,eventListName);

	}
	
	public StandardEventValue(Date date, Integer eventDefId, String eventtype, String message, String eventListName){
		super(date,EventDefinition.valueOf(eventDefId),EventType.valueOf(eventtype.charAt(0)),message, eventListName);

	}

	public StandardEventValue(StandardEventKey eventKey, String message, String eventListName) {
		super(eventKey.getDate(), (EventDefinition)eventKey.getEventDefId(), (EventType)eventKey.getEvenType(), message,eventListName);
	}
	
	

	
	

}