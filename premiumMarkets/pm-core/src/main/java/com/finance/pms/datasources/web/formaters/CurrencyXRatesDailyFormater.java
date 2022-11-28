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

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.calculation.DateFactory;

public class CurrencyXRatesDailyFormater extends LineFormater {

	private static PatternProperties PATTERNS;
	
	private Pattern fromCurrencyPattern;
	private Pattern toCurrencyPattern;

	private Currency fromCurrency;
	private Currency toCurrency;

	private Date date;
	private BigDecimal fromCurrencyRate;
	private BigDecimal toCurrencyRate;

	
	public CurrencyXRatesDailyFormater(Currency fromCurrency, Currency toCurrency, Date date, String url) {
		super(new MyUrl(url));
		
		this.date = DateFactory.midnithDate(date);
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		
		try {
			if (null == CurrencyXRatesDailyFormater.PATTERNS) CurrencyXRatesDailyFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}

		fromCurrencyPattern = Pattern.compile(String.format(CurrencyXRatesDailyFormater.PATTERNS.getProperty("xRatesCurrencyLine"), fromCurrency.name()));
		toCurrencyPattern = Pattern.compile(String.format(CurrencyXRatesDailyFormater.PATTERNS.getProperty("xRatesCurrencyLine"), toCurrency.name()));
		
		if (fromCurrency.equals(Currency.USD)) fromCurrencyRate = BigDecimal.ONE;
		if (toCurrency.equals(Currency.USD)) toCurrencyRate = BigDecimal.ONE;
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		Matcher fit;

		LOGGER.trace(line);
		if (fromCurrencyRate == null)  {
			fit = fromCurrencyPattern.matcher(line);
			if (fit.find()) {
				String rate = fit.group(1);
				fromCurrencyRate = ("NA".equals(rate))?BigDecimal.ONE.negate():new BigDecimal(rate);
			} 
		}
		if (toCurrencyRate == null ){
			fit = toCurrencyPattern.matcher(line);
			if (fit.find()) {
				String rate = fit.group(1);
				toCurrencyRate = ("NA".equals(rate))?BigDecimal.ONE.negate():new BigDecimal(rate);
			}
		}

		if (fromCurrencyRate != null && toCurrencyRate !=null) {
			BigDecimal rate = toCurrencyRate.divide(fromCurrencyRate, 10, RoundingMode.HALF_EVEN);
			Validatable rateLine = new CurrencyRate(fromCurrency, toCurrency, date, rate);
			throw new StopParseFoundException(rateLine);
		}

		return null;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}
}
