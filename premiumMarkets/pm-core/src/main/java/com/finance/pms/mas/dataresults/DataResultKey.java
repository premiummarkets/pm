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
package com.finance.pms.mas.dataresults;

import java.util.Date;

import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;


/**
 * The Class DataResultKey.
 * 
 * @author Guillaume Thoreton
 */
public class DataResultKey extends EventKey {
	

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

	public EventInfo getEventInfo() {
		// FIXME
		return null;
	}

	public EventType getEventType() {
		// FIXME
		return null;
	}
	
	public String getEventInfoExtra() {
		return null;
	}	

	public String toString() {
		return "DataResultKey [date=" + date + ", eventdef=" + eventDefId + "]";
	}
	
}