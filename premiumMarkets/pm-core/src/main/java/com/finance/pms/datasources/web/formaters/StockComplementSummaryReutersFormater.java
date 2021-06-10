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

public class StockComplementSummaryReutersFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;
	
	private Pattern eps;
	private Pattern epsValue;
	private boolean foundEPS;

	public StockComplementSummaryReutersFormater(String url, ScreeningSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementSummaryReutersFormater.PATTERNS)
				StockComplementSummaryReutersFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		
		eps = Pattern.compile(StockComplementSummaryReutersFormater.PATTERNS.getProperty("reutersEPS"));
		epsValue = Pattern.compile(StockComplementSummaryReutersFormater.PATTERNS.getProperty("reutersEPSValue"));
		foundEPS = false;
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		ScreeningSupplementedStock stockPart = (ScreeningSupplementedStock) params.get(0);
		LOGGER.trace(line);
		
		Matcher fitEPS = eps.matcher(line);
		if (!foundEPS && fitEPS.find()) {
			foundEPS = true;
		}
		
		if (foundEPS && stockPart.isNOTSetReutersEPS()) {
			Matcher fitEPSValue = epsValue.matcher(line);
			if (fitEPSValue.find()) {
				String epsValueStr = fitEPSValue.group(1);
				BigDecimal epsd;
				if ((epsValueStr.equals("--") || epsValueStr.equals("NA"))) {
					epsd = BigDecimal.ZERO;
				} else {
					epsd= new BigDecimal(epsValueStr).setScale(4, RoundingMode.HALF_EVEN);
				}
				
				//stockPart.setReutersEPS(epsd.multiply(new BigDecimal(stockPart.getMarket().getCurrency().getToRatedUnitFactor())));
				stockPart.setReutersEPS(epsd.multiply(stockPart.getMarketValuation().getCurrencyFactor()));
			}
		}

		if (!stockPart.isNOTSetReutersEPS()) {
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
