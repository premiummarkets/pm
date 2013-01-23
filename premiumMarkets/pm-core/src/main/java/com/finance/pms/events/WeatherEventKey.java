
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

public class WeatherEventKey implements EventKey {

	private static final long serialVersionUID = 1806331274610032030L;

	private Date date;
	private EventDefinition eventdef;
	private EventType eventType;
	private String hint;
	
	

	public WeatherEventKey(Date date, EventDefinition eventdef, EventType eventType, String hint) {
		super();
		this.date = date;
		this.eventdef = eventdef;
		this.eventType = eventType;
		this.hint = hint;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEventDefId() {
		return eventdef;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEvenType() {
		return eventType;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getEventDefExtra() {
		return hint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((eventdef == null) ? 0 : eventdef.hashCode());
		result = prime * result + ((hint == null) ? 0 : hint.hashCode());
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
		WeatherEventKey other = (WeatherEventKey) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventType != other.eventType)
			return false;
		if (eventdef != other.eventdef)
			return false;
		if (hint == null) {
			if (other.hint != null)
				return false;
		} else if (!hint.equals(other.hint))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeatherEventKey [date=" + date + ", eventdef=" + eventdef + ", eventType=" + eventType + ", hint=" + hint + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(EventKey o) {
		
		int dateCompare = date.compareTo(o.getDate());
		if (dateCompare == 0) {
			int evtTypecompare = eventType.compareTo((EventType) o.getEvenType());
			if (evtTypecompare == 0) {
				int evtDefCompare = eventdef.compareTo((EventDefinition) o.getEventDefId());
				if (evtDefCompare == 0) {
					return getEventDefExtra().compareTo(o.getEventDefExtra());
				}
			}
			return evtTypecompare;
		}
		
		return dateCompare;
		
	}
	

}
