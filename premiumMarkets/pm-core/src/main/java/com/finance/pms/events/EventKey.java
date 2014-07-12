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
import java.util.Date;

/**
 * The Interface EventKey.
 * 
 * @author Guillaume Thoreton
 */
public abstract class EventKey implements Serializable, Comparable<EventKey> {

	private static final long serialVersionUID = -328520642861639149L;

	public abstract Date getDate();
	public abstract EventInfo getEventInfo();
	public abstract EventType getEventType();
	public abstract String getEventInfoExtra();
	
	@Override
	final public int compareTo(EventKey o) {
		
		int cmp = getDate().compareTo(o.getDate());
		if (cmp == 0) {
			cmp = getEventInfo().compareTo(o.getEventInfo());
			if (cmp == 0) {
				cmp = getEventInfoExtra().toString().compareTo(o.getEventInfoExtra().toString());
				if (cmp == 0) {
					cmp = getEventType().compareTo((EventType) o.getEventType());
				}
			}
		}
		
		return cmp;
	}
	
	@Override
	final public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
		result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
		result = prime * result + ((getEventInfo() == null) ? 0 : getEventInfo().hashCode());
		result = prime * result + ((getEventInfoExtra() == null) ? 0 : getEventInfoExtra().hashCode());
		return result;
	}

	@Override
	final public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventKey other = (EventKey) obj;
		if (getDate() == null) {
			if (other.getDate() != null)
				return false;
		} else if (getDate().compareTo(other.getDate()) != 0)
			return false;
		if (getEventType() != other.getEventType())
			return false;
		if (getEventInfo() != other.getEventInfo())
			return false;
		if (getEventInfoExtra() == null) {
			if (other.getEventInfoExtra() != null)
				return false;
		} else if (!getEventInfoExtra().equals(other.getEventInfoExtra()))
			return false;
		return true;
	}

}
