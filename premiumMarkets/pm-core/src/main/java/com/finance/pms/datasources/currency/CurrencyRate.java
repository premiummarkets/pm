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
package com.finance.pms.datasources.currency;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;

@Entity
@IdClass(CurrencyRateId.class)
public class CurrencyRate extends Validatable {
	
	private static final long serialVersionUID = 1L;
	
	private Currency toCurrency;
	private Currency fromCurrency;
	private Date date;
	private BigDecimal rate;
	
	//hibernate
	@SuppressWarnings("unused")
	private CurrencyRate() {
		
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
		this.rate = new BigDecimal(rate).setScale(4,BigDecimal.ROUND_DOWN);
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


}
