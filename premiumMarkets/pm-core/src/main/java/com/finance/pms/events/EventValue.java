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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


/**
 * The Class EventValue.
 * 
 * @author Guillaume Thoreton
 */
public class EventValue implements Serializable {
	
	private static final long serialVersionUID = -2037828749889092912L;

	protected Date date;
	protected EventType eventType;
	protected EventInfo eventDef;
	protected String message = "";

	private String eventListName;

	public EventValue(Date date, EventInfo eventDef, EventType eventType, String message, String eventListName) {
		super();
		this.date = date;
		this.eventDef = eventDef;
		this.eventType = eventType;
		this.message = message;
		this.eventListName = eventListName;
	}
	

	public EventValue(Date date, EventInfo eventDef, EventType eventType, String eventListName) {
		super();
		this.date = date;
		this.eventDef = eventDef;
		this.eventType = eventType;
		this.message = "";
		this.eventListName = eventListName;
	}


	public String toExport(Set<String> eventsList) {
		DateFormat df = new SimpleDateFormat("yyy/MM/dd");
		return df.format(date) + ";" + eventDef.getEventDefinitionRef() + ";" + eventType;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date)+" -> "+eventDef.getEventReadableDef()+" : "+eventType+" : "+message;
	}
	

	public String toEmail() {
		DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		return eventType.toString()+" : "+eventDef.getEventReadableDef()+" on the "+df.format(date) + ((this.message != "")?" :\n"+message:".");
	}

	public Integer getEventDefId() {
		return this.eventDef.getEventDefId();
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventDef(EventDefinition eventDef) {
		this.eventDef = eventDef;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Date getDate() {
		return date;
	}
	
	
	public void setDate(Date date) {
		this.date = date;
	}

	public EventInfo getEventDef() {
		return eventDef;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventDef == null) ? 0 : eventDef.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventValue other = (EventValue) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (date.compareTo(other.date) != 0)
			return false;
		if (eventDef == null) {
			if (other.eventDef != null)
				return false;
		} else if (!eventDef.equals(other.eventDef))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}


	public String getEventListName() {
		return eventListName;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
