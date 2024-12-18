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
package com.finance.pms.datasources.shares;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class MarketValuation implements Serializable {
	
	private static final long serialVersionUID = 3044051617700381321L;

	Market market;
	BigDecimal currencyFactor;
	Currency currency;

	//Hibernate
	@SuppressWarnings("unused")
	private MarketValuation() {
		super();
	}

	public MarketValuation(Market market) {
		super();
		this.market = market;
		this.currency = market.getDefaultCurrency();
		this.currencyFactor = market.getDefaultCurrencyFactor();
	}

	public MarketValuation(Market market, BigDecimal currencyFactor) {
		super();
		this.market = market;
		this.currency = market.getDefaultCurrency();
		this.currencyFactor = currencyFactor;
	}

	public MarketValuation(Market market, BigDecimal currencyFactor, Currency currency) {
		super();
		this.market = market;
		this.currencyFactor = currencyFactor;
		this.currency = currency;
	}

	@Enumerated(EnumType.STRING)
	public Market getMarket() {
		return market;
	}

	public BigDecimal getCurrencyFactor() {
		return currencyFactor;
	}

	@Enumerated(EnumType.STRING)
	public Currency getCurrency() {
		return currency;
	}

	@SuppressWarnings("unused")
	private void setMarket(Market market) {
		this.market = market;
	}

	@SuppressWarnings("unused")
	private void setCurrencyFactor(BigDecimal currencyFactor) {
		this.currencyFactor = currencyFactor;
	}

	@SuppressWarnings("unused")
	private void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public BigDecimal translateToBaseCurrencyUnit(BigDecimal amount) {
		return amount.divide(this.currencyFactor, 10, RoundingMode.HALF_EVEN);
	}

}
