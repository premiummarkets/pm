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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventType;
import com.finance.pms.screening.ScreeningSupplementExporter;


public class AlertEventValue extends StandardEventValue {

	private static final long serialVersionUID = 1L;
	public static final Integer TOP_ADDITIONAL_WEIGHT = ScreeningSupplementExporter.TOP_RANK*10;

	public AlertEventValue(Date date, String eventDef, String eventtype, String message, String eventListName) throws NoSuchFieldException {
		super(date, eventDef, eventtype, message, eventListName);
	}

	public AlertEventValue(Date date, EventInfo eventDef, EventType eventtype, String message, String eventListName) {
		super(date, eventDef, eventtype, message, eventListName);
	}
	
	public AlertEventValue(StandardEventKey eventKey, String message, String eventListName) {
		super(eventKey, message, eventListName);
	}
	
	public AlertEventValue(Date date, EventType eventtype, EventDefinition eventDef, String message, String eventListName) {
		super(date, eventDef, eventtype, message, eventListName);
	}
	
	public Integer extractAdditionalRankWeight() {
		
		String rankString = "NAN";
		Pattern pattern = Pattern.compile("--([0-9]*)--");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			rankString = matcher.group(1);
		}
		
		if (EventDefinition.SCREENER.equals(this.eventDef) && (EventType.BULLISH.equals(this.eventType) || EventType.NONE.equals(this.eventType)) ) {
			return Math.max(0, TOP_ADDITIONAL_WEIGHT - Integer.valueOf(rankString));
		}
		
		return Integer.valueOf(rankString);
		
	}
}
