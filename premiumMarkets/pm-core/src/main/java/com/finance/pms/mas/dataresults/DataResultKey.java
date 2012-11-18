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
package com.finance.pms.mas.dataresults;

import java.util.Date;

import com.finance.pms.events.EventKey;



// TODO: Auto-generated Javadoc
/**
 * The Class DataResultKey.
 * 
 * @author Guillaume Thoreton
 */
public class DataResultKey implements EventKey {
	

	private static final long serialVersionUID = 7449740441898110511L;
	
	private Date date;
	private Integer eventDefId;
	
	public DataResultKey(Date date, Integer eventDefId){
		this.date = date;
		this.eventDefId = eventDefId;
	}
	
	public Date getDate() {
		return date;
	}

	public Comparable<Integer> getEventDefId() {
		return eventDefId;
	}
	
	@SuppressWarnings("rawtypes")
	public Comparable getEvenType() {
		// FIXME
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Comparable getEventDefExtra() {
		return null;
	}	

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventDefId == null) ? 0 : eventDefId.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataResultKey other = (DataResultKey) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventDefId == null) {
			if (other.eventDefId != null)
				return false;
		} else if (!eventDefId.equals(other.eventDefId))
			return false;
		return true;
	}

	public String toString() {
		return "DataResultKey [date=" + date + ", eventdef=" + eventDefId + "]";
	}
	
}