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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteLastYahooFormater extends LineFormater {
	
	/** The PATTERNS. */
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
//			quotation = new BigDecimal(numberFormat.parse(fitQuotation.group(1)).doubleValue()).setScale(2,BigDecimal.ROUND_DOWN);
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
	public Boolean canHaveEmptyResults() {
		return true;
	}


}
