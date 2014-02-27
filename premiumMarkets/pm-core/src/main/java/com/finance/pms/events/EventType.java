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
package com.finance.pms.events;

import java.io.Serializable;


/**
 * The Enum EventType.
 * 
 * @author Guillaume Thoreton
 */
public enum EventType implements Serializable {
	
	BEARISH("bearich", -1, 's'),
	NONE("neutral", 0, 'n'),
	BULLISH("bullish", 1, 'b'),
	INFO("info", 3, 'i');
	
	//Mas
	//public static final char[] eventTypes = {'b','s','n','o'}; 
	//"b": buy signal; "s": sell signal; "n": neutral signal; "o": other

	private final String eventType;
	private final Integer eventTypeIndex;
	private final Character eventTypeChar;

	private EventType(String arg0,Integer arg1,Character arg2) {
		eventType=arg0;
		eventTypeIndex=arg1;
		eventTypeChar=arg2;
	}

	public static EventType valueOf(Integer ordinal){
		return EventType.values()[ordinal];
	}

	public static EventType valueOf(Character et) {
		et = Character.toLowerCase(et);
		EventType eValues[] = EventType.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].getEventTypeChar().equals(et)) 
					return eValues[i];
		}
		throw new IllegalArgumentException("No enum const EventType." + et);
	}

	public String getEventType() {
		return eventType;
	}

	public Character getEventTypeChar() {
		return eventTypeChar;
	}
	
	public Integer getEventTypeIndex() {
		return eventTypeIndex;
	}
	
	
}
