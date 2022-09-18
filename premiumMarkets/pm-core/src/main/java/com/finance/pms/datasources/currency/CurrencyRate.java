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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Currency;

@Entity
@Table(name="CURRENCYRATE")
@IdClass(CurrencyRateId.class)
public class CurrencyRate extends Validatable implements ValidatableDated {
	
	private static final long serialVersionUID = 1L;
	
	private Currency fromCurrency;
	private Currency toCurrency;
	private Date date;
	private BigDecimal rate;
	
	//hibernate
	@SuppressWarnings("unused")
	private CurrencyRate() {
		
	}
	
	public CurrencyRate(CurrencyRate other) {
		this.fromCurrency = other.fromCurrency;
		this.toCurrency = other.toCurrency;
		this.date = other.date;
		this.rate = other.rate;
	}

	public CurrencyRate(Currency fromCurrency, Currency toCurrency, Date date, BigDecimal rate) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.date = date;
		this.rate = rate;
	}

	public CurrencyRate(Currency fromCurrency, Currency toCurrency, Date date, String rate) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.date = date;
		this.rate = new BigDecimal(rate).setScale(10, RoundingMode.HALF_EVEN);
	}

	
	@Override
	public Query toDataBase() {
		throw new NotImplementedException();
	}

	public int compareTo(Validatable otherCurrency) {
		Integer currencyCompare = fromCurrency.compareTo(((CurrencyRate)otherCurrency).getFromCurrency());
		if (currencyCompare == 0) {
			Integer toCurrencyCompare = toCurrency.compareTo(((CurrencyRate)otherCurrency).getToCurrency());
			if (toCurrencyCompare == 0) {
				return date.compareTo(((CurrencyRate)otherCurrency).getDate());
			}
		}
		return currencyCompare;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	@Id
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Id
	@Enumerated(EnumType.ORDINAL)
	public Currency getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(Currency fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	@Id
	@Enumerated(EnumType.ORDINAL)
	public Currency getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(Currency toCurrency) {
		this.toCurrency = toCurrency;
	}
	
	
	
	public String toString(){
		return this.fromCurrency+" ; "+this.toCurrency+" ; "+this.date+" ; "+this.rate;
	}

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
		CurrencyRate other = (CurrencyRate) obj;
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
