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
import java.util.Objects;

import com.finance.pms.datasources.shares.Stock;

public class EventMessageObject implements Serializable {

	private static final long serialVersionUID = 805246012496439241L;

	private String eventListName;
	private String portfolios;
	private Stock stock;

	private EventKey eventKey;
	private EventValue eventValue;

	private Date calculationDate;




	public EventMessageObject(String eventListName, String portfolios, Stock stock, Date calculationDate, EventKey eventKey, EventValue eventValue) {
		super();
		this.eventListName = eventListName;
		this.calculationDate = calculationDate;
		this.eventKey = eventKey;
		this.eventValue = eventValue;
		this.stock = stock;
		this.portfolios = portfolios;
	}

	public Stock getStock() {
		return stock;
	}
	
	public EventKey getEventKey() {
		return eventKey;
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
	
	public String getPortfolios() {
		return portfolios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventListName, eventValue, stock);
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
		return Objects.equals(eventListName, other.eventListName) && Objects.equals(eventValue, other.eventValue) && Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "EventMessageObject [eventListName=" + eventListName + ", portfolios=" + portfolios + ", stock=" + stock + 
				", eventKey=" + eventKey + ", eventValue=" + eventValue + ", calculationDate=" + calculationDate + "]";
	}
	
	

}
