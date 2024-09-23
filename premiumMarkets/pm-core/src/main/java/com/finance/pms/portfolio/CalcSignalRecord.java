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
package com.finance.pms.portfolio;

import java.util.Date;

import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.SymbolEvents;

public class CalcSignalRecord {
	
	enum Status {
		OPEN,DONE,CANCELED,ERROR;
	}

	private String portfolioName;
	private Date signalDate;
	private TransactionType movement;
	private SymbolEvents eventList;
	private EmailFilterEventSource source;

	private Status status;


	public CalcSignalRecord(String portfolioName, Date date, TransactionType movement, SymbolEvents eventList, EmailFilterEventSource source) {
		super();
		this.portfolioName = portfolioName;
		this.signalDate = date;
		this.movement = movement;
		this.eventList = eventList;
		this.source = source;
		this.status = Status.OPEN;
	}

	public String getPortfolioName() {
		return portfolioName;
	}
	public Date getSignalDate() {
		return signalDate;
	}
	public void setSignalDate(Date date) {
		this.signalDate = date;
	}
	public TransactionType getMovement() {
		return movement;
	}
	public void setMovement(TransactionType movement) {
		this.movement = movement;
	}
	public SymbolEvents getEventList() {
		return eventList;
	}

	@Override
	public String toString() {
		return "\n Portfolio\t: " + portfolioName 
				+ "\n Stock\t\t: " + eventList.getStock() 
				+ "\n Detection date\t: " + signalDate 
				+ "\n " + ((eventList != null)? eventList.toAutoPortfolioLog():"Events\t\t:\n\t\tNo event")
				+ "\n"
				+ "\n Movement\t\t: " + movement;
	}

	public EmailFilterEventSource getSource() {
		return source;
	}

	public Stock getStock() {
		return eventList.getStock();
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
