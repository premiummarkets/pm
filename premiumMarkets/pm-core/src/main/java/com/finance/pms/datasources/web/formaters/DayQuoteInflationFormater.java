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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteInflationFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	
	protected Currency currency; 

	private Pattern endTable;
	private Pattern fullYearPattern;
	
	private Integer year;
	private List<Validatable> validatables;
	
	private Calendar startDate;

	public DayQuoteInflationFormater(MyUrl url, Stock stock, String currency, Date startDate) {
		super(url);
		params.add(stock);
		params.add(currency);
		
		this.currency = Currency.valueOf(currency);
	
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate);
		
		this.validatables = new ArrayList<Validatable>();
		

		try {
			if (null == DayQuoteInflationFormater.PATTERNS) DayQuoteInflationFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		endTable = Pattern.compile(DayQuoteInflationFormater.PATTERNS.getProperty("inflationrateendtable"));
		fullYearPattern = Pattern.compile(DayQuoteInflationFormater.PATTERNS.getProperty("inflationrateyearline"));
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		LOGGER.trace(line);
		
		Matcher fitYear = fullYearPattern.matcher(line);
		if (fitYear.find()) {
			year = Integer.valueOf(fitYear.group(1));
			if (year >= startDate.get(Calendar.YEAR)) {
				for (int i = 2; i <= 13; i++) {
					String value = fitYear.group(i);
					if (!"&nbsp;".equals(value)) {
						LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, i - 2);
						calendar.set(Calendar.DAY_OF_MONTH, 1);
						calendar.set(Calendar.HOUR_OF_DAY, 0);
						calendar.set(Calendar.MINUTE, 0);
						calendar.set(Calendar.SECOND, 0);
						calendar.set(Calendar.MILLISECOND, 0);
						mainQuery.add(calendar.getTime());
						mainQuery.add(new BigDecimal(value));
						mainQuery.add(new BigDecimal(value));
						mainQuery.add(new BigDecimal(value));
						mainQuery.add(new BigDecimal(value));
						mainQuery.add(Long.valueOf(0));
						validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
					}
				}
			}
		}

		if (year != null)	{

			Matcher fitEnd = endTable.matcher(line);
			if (fitEnd.find()) {
				return validatables;
			}
		}

		return null;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}
	
	
}
