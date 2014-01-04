/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.screening.ScreeningSupplementedStock;

public class StockComplementFinancialsReutersFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;

	private Pattern value;

	private Pattern yield;
	private Boolean yeYield = false;
	private Boolean isDivSetOrReset = false;
	

	private Pattern estEPSTtl;
	private Pattern estEPS;
	private Integer yeEstEPSCpt = 0;
	
	private Boolean yePayout = false;
	private Pattern reuterPayoutRatio;

	private BigDecimal nbOpinions;


	public StockComplementFinancialsReutersFormater(String url, ScreeningSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementFinancialsReutersFormater.PATTERNS)
				StockComplementFinancialsReutersFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		estEPSTtl = Pattern.compile(StockComplementFinancialsReutersFormater.PATTERNS.getProperty("reutersEstEPSTtl"));
		estEPS = Pattern.compile(StockComplementFinancialsReutersFormater.PATTERNS.getProperty("reutersEstEPS"));
		yield = Pattern.compile(StockComplementFinancialsReutersFormater.PATTERNS.getProperty("reutersYield"));
		reuterPayoutRatio = Pattern.compile(StockComplementFinancialsReutersFormater.PATTERNS.getProperty("reutersPayoutRatio"));
		
		value = Pattern.compile(StockComplementFinancialsReutersFormater.PATTERNS.getProperty("reutersValue"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		ScreeningSupplementedStock stockPart = (ScreeningSupplementedStock) params.get(0);
		LOGGER.trace(line);
		
		Matcher valueMatcher;
		valueMatcher = value.matcher(line);
		
		grabEstEPS(line, stockPart, valueMatcher);
	
		grabYield(line, stockPart, valueMatcher);
	
		payoutRatio(line, stockPart, valueMatcher);

		if  (!stockPart.isNOTSetReutersEstEPS() && !stockPart.isNOTSetReutersPayoutRatio()) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		}

		return null;
	}

	private void grabEstEPS(String line, ScreeningSupplementedStock stockPart, Matcher valueMatcher) {

		if (yeEstEPSCpt <= 3 && stockPart.isNOTSetReutersEstEPS()) {

			
			if (yeEstEPSCpt == 0 ) {
				Matcher estEPSTtlMatcher = estEPSTtl.matcher(line);
				if (estEPSTtlMatcher.find()) yeEstEPSCpt++; 
			} else if (yeEstEPSCpt == 1) {
				Matcher estEPSMatcher = estEPS.matcher(line);
				if (estEPSMatcher.find()) yeEstEPSCpt++; 
			} else if (yeEstEPSCpt == 2 && valueMatcher.find()) {
				nbOpinions = extractPattern(valueMatcher);
				yeEstEPSCpt++; 
			} else if ( yeEstEPSCpt == 3 && nbOpinions != null && valueMatcher.find()) {
				if (nbOpinions.intValue() < 3) {
					stockPart.setReutersEstEPS(BigDecimal.ZERO);
				} else {
					BigDecimal estEPSValue = extractPattern(valueMatcher);
					stockPart.setReutersEstEPS(estEPSValue);
				}
				yeEstEPSCpt++;
			}
		}
	}
	
	private void payoutRatio(String line, ScreeningSupplementedStock stockPart, Matcher valueMatcher) {
		BigDecimal payoutRatioValue;
		if (yePayout && stockPart.isNOTSetReutersPayoutRatio()) {
			if (valueMatcher.find()) {
				payoutRatioValue = extractPattern(valueMatcher).movePointLeft(2);
				stockPart.setReutersPayoutRatio(payoutRatioValue);
			}
		}
		Matcher payoutMatcher = reuterPayoutRatio.matcher(line);
		if (!yePayout && payoutMatcher.find()) {
			yePayout  = true; 
		}
	}

	private void grabYield(String line, ScreeningSupplementedStock stockPart, Matcher valueMatcher) {
		BigDecimal yieldValue;
		if (yeYield && !isDivSetOrReset) {
			if (valueMatcher.find()) {
				yieldValue = extractPattern(valueMatcher).setScale(4).movePointLeft(2);
				stockPart.setReutersYield(yieldValue);
				isDivSetOrReset = true;
			}
		}
		Matcher yieldMatcher= yield.matcher(line);
		if (!isDivSetOrReset && !yeYield && yieldMatcher.find()) {
			yeYield  = true; 
		}
	}


	private BigDecimal extractPattern(Matcher valueMatcher) {
		BigDecimal value;
		String valueStr = valueMatcher.group(1);
		value = (valueStr.equals("--") || valueStr.equals("NA"))? BigDecimal.ZERO : new BigDecimal(valueStr).setScale(4, BigDecimal.ROUND_DOWN);
		valueMatcher.reset();
		return value;
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}

}
