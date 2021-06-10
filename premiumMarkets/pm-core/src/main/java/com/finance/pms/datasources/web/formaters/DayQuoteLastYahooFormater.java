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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteLastYahooFormater extends LineFormater {

	private static PatternProperties PATTERNS;
	
	private Pattern lastQuote;
	LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
	
	public DayQuoteLastYahooFormater(String url, Stock stock) {
		super(new MyUrl(url));		
		params.add(stock);

		try {
			if (null == DayQuoteLastYahooFormater.PATTERNS)
				DayQuoteLastYahooFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		String symbol = stock.getSymbol().replace(".", "\\.").toLowerCase();
		lastQuote = Pattern.compile(DayQuoteLastYahooFormater.PATTERNS.getProperty("yahoolastquote", symbol));
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		LOGGER.trace(line);
		
		Matcher fitQuotation;
		fitQuotation = lastQuote.matcher(line);
		if (fitQuotation.find()) {
//			quotation = new BigDecimal(numberFormat.parse(fitQuotation.group(1)).doubleValue()).setScale(2,RoundingMode.HALF_EVEN);
//			mainQuery.add(date);
//			mainQuery.add(open);
//			mainQuery.add(high);
//			mainQuery.add(low);
//			mainQuery.add(close);
//			mainQuery.add(volume);
//			validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
//			throw new StopParseFoundException(stockPart);
			
		} 
			
		return null;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}


}
