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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.screening.ScreeningSupplementedStock;

public class StockComplementSummaryYahooFormater extends LineFormater {

	private static PatternProperties PATTERNS;

	private Pattern divPattern;
	private Boolean isDivSetOrReset;
	
	private Pattern eps;
	
	public StockComplementSummaryYahooFormater(String url, ScreeningSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);
		
		try {
			if (null == StockComplementSummaryYahooFormater.PATTERNS)
				StockComplementSummaryYahooFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		divPattern = Pattern.compile(StockComplementSummaryYahooFormater.PATTERNS.getProperty("yahoodividendcompletion"));
		isDivSetOrReset = false;
		
		eps = Pattern.compile(StockComplementSummaryYahooFormater.PATTERNS.getProperty("yahooEPS"));
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		ScreeningSupplementedStock stockPart = (ScreeningSupplementedStock) params.get(0);
		LOGGER.trace(line);
		
		Matcher fitDiv = divPattern.matcher(line);
		if (fitDiv.find()) {
			String div = fitDiv.group(1).replaceAll(",", "");
			BigDecimal divd;
			if (div.equals("N/A")) {
				divd = BigDecimal.ZERO;
			} else {
				divd= new BigDecimal(div).setScale(2, RoundingMode.HALF_EVEN);
			}
			stockPart.setDividend(divd);
			isDivSetOrReset = true;
		}
		
		Matcher fitEPSValue = eps.matcher(line);
		if (fitEPSValue.find()) {
			String eps = fitEPSValue.group(1);
			BigDecimal epsd;
			if (eps.equals("N/A")) {
				epsd = BigDecimal.ZERO;
			} else {
				epsd= new BigDecimal(eps).setScale(4, RoundingMode.HALF_EVEN);
			}
			
			//stockPart.setYahooEPS(epsd.multiply(new BigDecimal(stockPart.getMarketValuation().getCurrency().getToRatedUnitFactor())));
			stockPart.setYahooEPS(epsd.multiply(stockPart.getMarketValuation().getCurrencyFactor()));
		}

		if (isDivSetOrReset && !stockPart.isNOTSetYahooEPS()) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		}

		return null;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

}
