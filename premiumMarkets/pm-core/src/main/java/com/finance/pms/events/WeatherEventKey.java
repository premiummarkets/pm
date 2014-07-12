
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

import java.util.Date;

public class WeatherEventKey extends EventKey {

	private static final long serialVersionUID = 1806331274610032030L;

	private Date date;
	private EventInfo eventdef;
	private EventType eventType;
	private String hint;
	
	

	public WeatherEventKey(Date date, EventInfo eventdef, EventType eventType, String hint) {
		super();
		this.date = date;
		this.eventdef = eventdef;
		this.eventType = eventType;
		this.hint = hint;
	}

	public WeatherEventKey(Date date, String eventInfoReference, String eventType, String hint) throws NoSuchFieldException {
		super();
		this.date = date;
		//this.eventdef = EventDefinition.valueOfEventInfo(eventInfoReference);
		this.eventdef = EventDefinition.valueOf(eventInfoReference);
		this.eventType = EventType.valueOf(eventType.charAt(0));
		this.hint = hint;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public EventInfo getEventInfo() {
		return eventdef;
	}

	@Override
	public EventType getEventType() {
		return eventType;
	}

	@Override
	public String getEventInfoExtra() {
		return hint;
	}

	@Override
	public String toString() {
		return "WeatherEventKey [date=" + date + ", eventdef=" + eventdef + ", eventType=" + eventType + ", hint=" + hint + "]";
	}

}
