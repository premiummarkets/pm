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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.MyUrl;

public class CurrencyImfHistoryFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	private static MyLogger LOGGER = MyLogger.getLogger(CurrencyImfHistoryFormater.class);

	private Pattern imfDateLine;
	private Pattern imfFromCurrencyLine;
	private Pattern imfToCurrencyLine;
	
	private Currency fromCurrency;
	private Currency toCurrency;
	private ArrayList<Date> datesList;
	
	private Boolean fromUsdPerCurrency = Boolean.FALSE;
	private ArrayList<BigDecimal> fromCurrencyRateList;
	
	private Boolean toUsdPerCurrency = Boolean.FALSE;
	private ArrayList<BigDecimal> toCurrencyRateList;
	
	private Boolean done = Boolean.FALSE;



	public CurrencyImfHistoryFormater(Currency fromCurrency, Currency toCurrency, String url) {
		super(new MyUrl(url));
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		
		datesList = new ArrayList<Date>();
		fromCurrencyRateList = new ArrayList<BigDecimal>();
		toCurrencyRateList = new ArrayList<BigDecimal>();
		
		try {
			if (null == CurrencyImfHistoryFormater.PATTERNS)
				CurrencyImfHistoryFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}

		imfDateLine = Pattern.compile(CurrencyImfHistoryFormater.PATTERNS.getProperty("imfdateline"));
		imfFromCurrencyLine = Pattern.compile(fromCurrency.getImfCurrencyName()+CurrencyImfHistoryFormater.PATTERNS.getProperty("imfCurrencyLine"));
		imfToCurrencyLine = Pattern.compile(toCurrency.getImfCurrencyName()+CurrencyImfHistoryFormater.PATTERNS.getProperty("imfCurrencyLine"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		List<Validatable> ret = new ArrayList<Validatable>();
		
		if (!done) {
			//line
			Matcher fit;
			try {
				LOGGER.trace(line);
				//date line
				fit = imfDateLine.matcher(line);
				if (fit.find()) {
					String dates = fit.group(1).trim();
					String[] datesArray = dates.split("\t");
					for (String date : datesArray) {
						datesList.add(new SimpleDateFormat("MMM dd, yyyy").parse(date));
					}
				} else {
					//from currency line
					Matcher fit2 = imfFromCurrencyLine.matcher(line);
					if (fit2.find()) {

						String calculationType = fit2.group(1);
						if ("(1)".equals(calculationType)) {
							fromUsdPerCurrency = true;
						}

						String rates = fit2.group(2);
						String[] ratesArray = rates.trim().split("\t");
						for (String rate : ratesArray) {
							BigDecimal rateDecimal = ("NA".equals(rate))?BigDecimal.ONE.negate():new BigDecimal(rate);
							fromCurrencyRateList.add(rateDecimal);
						}

					} else {
						//to currency line
						Matcher fit3 = imfToCurrencyLine.matcher(line);
						if (fit3.find()) {

							String calculationType = fit3.group(1);
							if ("(1)".equals(calculationType)) {
								toUsdPerCurrency = true;
							}

							String rates = fit3.group(2);
							String[] ratesArray = rates.trim().split("\t");
							for (String rate : ratesArray) {
								BigDecimal rateDecimal = ("NA".equals(rate))?BigDecimal.ONE.negate():new BigDecimal(rate);
								toCurrencyRateList.add(rateDecimal);
							}
						}
					}
				}

				if (datesList.size() != 0 && toCurrencyRateList.size() != 0 && fromCurrencyRateList.size() != 0) {
					for (int i = 0; i < datesList.size(); i++) {

						BigDecimal fromRate = fromCurrencyRateList.get(i);
						BigDecimal toRate = toCurrencyRateList.get(i);

						if (fromRate.compareTo(BigDecimal.ZERO) >= 0 && toRate.compareTo(BigDecimal.ZERO) >= 0 ) {
							BigDecimal rate = BigDecimal.ONE;
							if (toUsdPerCurrency && fromUsdPerCurrency) {
								rate = fromRate.divide(toRate, 10, RoundingMode.HALF_EVEN);
							} else if (!toUsdPerCurrency && !fromUsdPerCurrency) {
								rate = toRate.divide(fromRate, 10, RoundingMode.HALF_EVEN);
							} else if (!toUsdPerCurrency && fromUsdPerCurrency) {
								rate = fromRate.multiply(toRate).setScale(10, RoundingMode.HALF_EVEN);
							} else if (toUsdPerCurrency && !fromUsdPerCurrency) {
								rate = BigDecimal.ONE.divide(fromRate.multiply(toRate), 10, RoundingMode.HALF_EVEN);
							}
							Validatable rateLine = new CurrencyRate(fromCurrency, toCurrency, datesList.get(i), rate);
							ret.add(rateLine);
						}
					}
					datesList = new ArrayList<Date>();
					fromCurrencyRateList = new ArrayList<BigDecimal>();
					toCurrencyRateList = new ArrayList<BigDecimal>();
				}

			} catch (ParseException e) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("",e);
			} 
		}
		
		return ret;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
