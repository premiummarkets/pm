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
package com.finance.pms.events;

import java.io.Serializable;


/**
 * The Enum EventType.
 * 
 * @author Guillaume Thoreton
 */
public enum EventType implements Serializable {

	BEARISH("bearish", -1, 's', 2),
	NONE("neutral", 0, 'n', 1),
	BULLISH("bullish", 1, 'b', 3),
	INFO("info", 3, 'i', 0);

	//Mas
	//public static final char[] eventTypes = {'b','s','n','o'}; 
	//"b": buy signal; "s": sell signal; "n": neutral signal; "o": other
	
	public static final int SIGNIFICANT_LN = EventType.values().length - 1; // -1 for INFO event type;

	private final String eventType;
	private final Integer eventTypeIndex;
	private final Character eventTypeChar;
	private final int chartPos;

	private EventType(String eventType, Integer eventTypeIndex, Character eventTypeChar, int chartPos) {
		this.eventType = eventType;
		this.eventTypeIndex = eventTypeIndex;
		this.eventTypeChar = eventTypeChar;
		this.chartPos = chartPos;
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
	
	public int getChartPos() {
		return chartPos;
	}

	public static Boolean consistencyCheck() {
		return (BEARISH.ordinal() == 0 && NONE.ordinal() == 1 && BULLISH.ordinal() == 2);
	}
}
