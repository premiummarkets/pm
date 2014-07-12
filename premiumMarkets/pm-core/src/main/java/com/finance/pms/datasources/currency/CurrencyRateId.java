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
package com.finance.pms.datasources.currency;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.finance.pms.datasources.shares.Currency;

public class CurrencyRateId implements Serializable {
	
	private static final long serialVersionUID = 4715208720118947441L;
	
	@Enumerated(EnumType.ORDINAL)
	private Currency toCurrency;
	@Enumerated(EnumType.ORDINAL)
	private Currency fromCurrency;
	private Date date;
	
	public Currency getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(Currency currency) {
		this.toCurrency = currency;
	}
	
	public Currency getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(Currency fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((toCurrency == null) ? 0 : toCurrency.hashCode());
//		result = prime * result + ((date == null) ? 0 : date.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CurrencyRateId other = (CurrencyRateId) obj;
//		if (toCurrency == null) {
//			if (other.toCurrency != null)
//				return false;
//		} else if (!toCurrency.equals(other.toCurrency))
//			return false;
//		if (date == null) {
//			if (other.date != null)
//				return false;
//		} else if (date.compareTo(other.date) != 0)
//			return false;
//		return true;
//	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fromCurrency == null) ? 0 : fromCurrency.hashCode());
		result = prime * result + ((toCurrency == null) ? 0 : toCurrency.hashCode());
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
		CurrencyRateId other = (CurrencyRateId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fromCurrency != other.fromCurrency)
			return false;
		if (toCurrency != other.toCurrency)
			return false;
		return true;
	}
	

	
	
}
