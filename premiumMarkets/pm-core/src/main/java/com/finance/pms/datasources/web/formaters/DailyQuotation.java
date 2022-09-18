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
package com.finance.pms.datasources.web.formaters;

import java.util.Date;
import java.util.LinkedList;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Stock;

public class DailyQuotation extends Stock implements ValidatableDated {

	private static final long serialVersionUID = 1L;
	
	Date quoteDate;
	LinkedList<Comparable<?>> mainQuery;
	String currency;

	public DailyQuotation(LinkedList<Comparable<?>> mainQuery, Stock stock, String currency) {
		super(stock);
		this.mainQuery = mainQuery;
		this.quoteDate = (Date) mainQuery.getFirst();
		this.currency = currency;
	}
	
	//Date,O,L,H,C,V
	public Comparable<?> getClose() {
		return mainQuery.get(4);
	}

	@Override
	public Query toDataBase() {
		Query iq = new Query();
		iq.addValuesList(mainQuery);
		iq.addValue(currency);
		iq.addValue(((Stock)this).getSymbol());
		iq.addValue(((Stock)this).getIsin());	
		return iq;
	}
	
	@Override
	public String toString() {
		return super.toString() + "; Quotation date :" + quoteDate + "; " + mainQuery;
	}
	
	public Date getQuoteDate() {
		return quoteDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((quoteDate == null) ? 0 : quoteDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyQuotation other = (DailyQuotation) obj;
		if (quoteDate == null) {
			if (other.quoteDate != null)
				return false;
		} else if (quoteDate.compareTo(other.quoteDate) != 0)
			return false;
		return true;
	}

	@Override
	public int compareTo(Validatable o) {
		return quoteDate.compareTo(((DailyQuotation)o).quoteDate);
	}

	@Override
	public Date getDate() {
		return getQuoteDate();
	}
	
}