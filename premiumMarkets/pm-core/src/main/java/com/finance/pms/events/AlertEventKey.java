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

import java.util.Date;

import com.finance.pms.alerts.AlertType;

public class AlertEventKey implements EventKey {
	
	private static final long serialVersionUID = -2973299473993380525L;
	
	private Date date;
	private EventDefinition eventdef;
	private EventType eventType;
	private AlertType alertType;
	
	public AlertEventKey(Date date, EventDefinition eventdef, EventType eventType, AlertType alertType) {
		super();
		this.date = date;
		this.eventdef = eventdef;
		this.eventType = eventType;
		this.alertType = alertType;
	}
	
	public AlertEventKey(Date date,Integer eventdefId, String eventType, String alertType) {
		super();
		this.date = date;
		this.eventdef = EventDefinition.valueOf(eventdefId);
		this.eventType = EventType.valueOf(eventType.charAt(0));
		this.alertType = AlertType.valueOf(alertType);
	}

	
	public Date getDate() {
		return date;
	}

	
	public Comparable<?> getEventDefId() {
		return eventdef;
	}

	
	public Comparable<?> getEvenType() {
		return eventType;
	}
	
	public AlertType getAlertType() {
		return alertType;
	}

	
	public String toString() {
		return "AlertEventKey [date=" + date + ", eventdef=" + eventdef + ", eventType=" + eventType + ", alertType=" + alertType
				+ "]";
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertType == null) ? 0 : alertType.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((eventdef == null) ? 0 : eventdef.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertEventKey other = (AlertEventKey) obj;
		if (alertType != other.alertType)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventType != other.eventType)
			return false;
		if (eventdef != other.eventdef)
			return false;
		return true;
	}

	
	public Comparable<?> getEventDefExtra() {
		return alertType;
	}
	
}
