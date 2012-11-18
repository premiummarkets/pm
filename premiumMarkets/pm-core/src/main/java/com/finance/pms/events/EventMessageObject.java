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
import java.util.Date;

import com.finance.pms.datasources.shares.Stock;

public class EventMessageObject implements Serializable {

	private static final long serialVersionUID = 805246012496439241L;
	
	private Stock stock;
	private EventValue eventValue;
	
	private String eventListName;
	private Date calculationDate;
	
	
	public EventMessageObject(String eventListName, Date calculationDate, EventValue eventValue, Stock stock) {
		super();
		this.eventListName = eventListName;
		this.calculationDate = calculationDate;
		this.eventValue = eventValue;
		this.stock = stock;
	}
	
	


	public Stock getStock() {
		return stock;
	}




	public EventValue getEventValue() {
		return eventValue;
	}




	public String getEventListName() {
		return eventListName;
	}




	public Date getCalculationDate() {
		return calculationDate;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventListName == null) ? 0 : eventListName.hashCode());
		result = prime * result + ((eventValue == null) ? 0 : eventValue.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		EventMessageObject other = (EventMessageObject) obj;
		if (eventListName == null) {
			if (other.eventListName != null)
				return false;
		} else if (!eventListName.equals(other.eventListName))
			return false;
		if (eventValue == null) {
			if (other.eventValue != null)
				return false;
		} else if (!eventValue.equals(other.eventValue))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

}
