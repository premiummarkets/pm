/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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
package com.finance.pms.events;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


// TODO: Auto-generated Javadoc
/**
 * The Class EventValue.
 * 
 * @author Guillaume Thoreton
 */
public class EventValue implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2037828749889092912L;
	
	/** The date. */
	protected Date date;

	/** The event type. */
	protected EventType eventType;
	
	/** The event def. */
	protected EventDefinition eventDef;

	protected String message = "";

	private String eventListName;


	public EventValue(Date date, EventDefinition eventDef, EventType eventType, String message, String eventListName) {
		super();
		this.date = date;
		this.eventDef = eventDef;
		this.eventType = eventType;
		this.message = message;
		this.eventListName = eventListName;
	}
	

	public EventValue(Date date, EventDefinition eventDef, EventType eventType, String eventListName) {
		super();
		this.date = date;
		this.eventDef = eventDef;
		this.eventType = eventType;
		this.message = "";
		this.eventListName = eventListName;
	}

	/**
	 * To export.
	 * 
	 * @param eventsList
	 *            the events list
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	public String toExport(Map<Integer, String> eventsList) {
		DateFormat df = new SimpleDateFormat("yyy/MM/dd");
		return df.format(date) + ";" + eventsList.get(eventDef) + ";" + eventType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date)+" -> "+eventDef.getEventDef()+" : "+eventType+" : "+message;
	}
	
	/**
	 * To email.
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	public String toEmail() {
		DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		return eventDef.getEventDef()+" on the "+df.format(date)+ ((this.message != "")?" :\n"+message:".");
	}

	/**
	 * Gets the event def id.
	 * 
	 * @return the event def id
	 */
	public Integer getEventDefId() {
		return this.eventDef.getEventDefId();
	}

	/**
	 * Gets the event type.
	 * 
	 * @return the event type
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * Sets the event def.
	 * 
	 * @param eventDef the new event def
	 */
	public void setEventDef(EventDefinition eventDef) {
		this.eventDef = eventDef;
	}

	/**
	 * Sets the event type.
	 * 
	 * @param eventType the new event type
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the event def.
	 * 
	 * @return the event def
	 */
	public EventDefinition getEventDef() {
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
		} else if (!date.equals(other.date))
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
