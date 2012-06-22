/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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

//	<td align="center" style="font-size:X-Small;white-space:nowrap;">2011</td><td align="right" style="font-size:X-Small;white-space:nowrap;">220.223</td><td align="right" style="font-size:X-Small;white-space:nowrap;">221.309</td><td align="right" style="font-size:X-Small;white-space:nowrap;">223.467</td><td align="right" style="font-size:X-Small;white-space:nowrap;">224.906</td><td align="right" style="font-size:X-Small;white-space:nowrap;">225.964</td><td align="right" style="font-size:X-Small;white-space:nowrap;">225.722</td><td align="right" style="font-size:X-Small;white-space:nowrap;">225.922</td><td align="right" style="font-size:X-Small;white-space:nowrap;">226.545</td><td align="right" style="font-size:X-Small;white-space:nowrap;">226.889</td><td align="right" style="font-size:X-Small;white-space:nowrap;">226.421</td><td align="right" style="font-size:X-Small;white-space:nowrap;">226.230</td><td align="right" style="font-size:X-Small;white-space:nowrap;">&nbsp;</td><td align="right" style="font-size:X-Small;white-space:nowrap;">&nbsp;</td>

    
	public DayQuoteInflationFormater(MyUrl url, Stock stock, String currency) {
		super(url);
		params.add(stock);
		params.add(currency);
		
		this.currency = Currency.valueOf(currency);
		this.validatables = new ArrayList<Validatable>();

		try {
			if (null == DayQuoteInflationFormater.PATTERNS)
				DayQuoteInflationFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		endTable = Pattern.compile(DayQuoteInflationFormater.PATTERNS.getProperty("inflationrateendtable"));
		fullYearPattern = Pattern.compile(DayQuoteInflationFormater.PATTERNS.getProperty("inflationrateyearline"));
		
	}
	
//	return "INSERT INTO " + QUOTATIONS.TABLE_NAME + " ( "+ QUOTATIONS.DATE_FIELD + " , "
//	+ QUOTATIONS.DAY_OPEN_FIELD + " , " + QUOTATIONS.DAY_HIGH_FIELD + " , " + QUOTATIONS.DAY_LOW_FIELD + " , "
//	+ QUOTATIONS.DAY_CLOSE_FIELD + " , " + QUOTATIONS.DAY_VOLUME_FIELD + " , " + QUOTATIONS.CURRENCY_FIELD + " , "
//	+ QUOTATIONS.SYMBOL_FIELD + " , " + QUOTATIONS.ISIN_FIELD + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		LOGGER.trace(line);
		
		Matcher fitYear = fullYearPattern.matcher(line);
		if (fitYear.find()) {
			year = new Integer(fitYear.group(1));
			for (int i = 2; i < 13; i++) {
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
					mainQuery.add(new Long(0));
					validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
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
	public Boolean canHaveEmptyResults() {
		return false;
	}
	
	
}
