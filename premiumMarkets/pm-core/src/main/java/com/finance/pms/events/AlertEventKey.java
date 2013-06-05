/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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

import com.finance.pms.alerts.AlertOnThresholdType;

public class AlertEventKey extends EventKey {
	
	private static final long serialVersionUID = -2973299473993380525L;
	
	private Date date;
	private EventInfo eventdef;
	private EventType eventType;
	private AlertOnThresholdType alertType;
	
	public AlertEventKey(Date date, EventInfo eventdef, EventType eventType, AlertOnThresholdType alertType) {
		super();
		this.date = date;
		this.eventdef = eventdef;
		this.eventType = eventType;
		this.alertType = alertType;
	}
	
	public AlertEventKey(Date date, String eventInfoReference, String eventType, String alertType) throws NoSuchFieldException {
		super();
		this.date = date;
		//this.eventdef = EventDefinition.valueOfEventInfo(eventInfoReference);
		this.eventdef = EventDefinition.valueOf(eventInfoReference);
		this.eventType = EventType.valueOf(eventType.charAt(0));
		this.alertType = AlertOnThresholdType.valueOf(alertType);
	}

	
	public Date getDate() {
		return date;
	}

	
	public EventInfo getEventInfo() {
		return eventdef;
	}

	
	public EventType getEventType() {
		return eventType;
	}
	
	public AlertOnThresholdType getAlertType() {
		return alertType;
	}

	
	public String toString() {
		return "AlertEventKey [date=" + date + ", eventdef=" + eventdef + ", eventType=" + eventType + ", alertType=" + alertType + "]";
	}

	@Override
	public String getEventInfoExtra() {
		return alertType.name();
	}

}
