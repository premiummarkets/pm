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

public class StockComplementSummaryBoursoramaFormater extends LineFormater {

	private static PatternProperties PATTERNS;

	private Pattern dividends;
	private int yeDiv;
	private Pattern valueDiv;
	
	private Pattern bna;
	private int yeBNA;
	private BigDecimal year1BnaGRealise;
	private BigDecimal year2Bna;
	private BigDecimal year3Bna;
	private Pattern valueBNA;
	
	private Boolean isEmpty = false;

	public StockComplementSummaryBoursoramaFormater(MyUrl myUrl) {
		super(myUrl);
	}

	public StockComplementSummaryBoursoramaFormater(String url, ScreeningSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementSummaryBoursoramaFormater.PATTERNS) StockComplementSummaryBoursoramaFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}
		
		dividends = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaDiv"));
		valueDiv = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaValueDiv"));

		bna = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaBNA"));
		valueBNA = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaValueBNA"));

		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		ScreeningSupplementedStock stockPart = (ScreeningSupplementedStock) params.get(0);
		LOGGER.trace(line);
		
		Matcher divValueMatcher = valueDiv.matcher(line);
		grabDiv(line, stockPart, divValueMatcher);
		
		Matcher bnaValueMatcher = valueBNA.matcher(line);
		grabBna(line, stockPart, bnaValueMatcher);

		if (yeDiv == 2) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Found bourso PEG for "+stockPart.getSymbol()+ " : BNA "+stockPart.getBoursoBNA()+" and estBNA "+stockPart.getBoursoEstBNA()+" and div "+stockPart.getDividend());
			Validatable validatable = stockPart;
			validatable.setState(Validatable.VALID);
			throw new StopParseFoundException(validatable);
		}

		return null;
	}
	
	private void grabDiv(String line, ScreeningSupplementedStock stockPart, Matcher valueMatcher) {
		BigDecimal div;
		if (yeDiv == 1) {
			if (valueMatcher.find()) {
				div = extractPattern(valueMatcher);
				if (div.compareTo(BigDecimal.ZERO) == 0) {
					isEmpty = true;
				}
				stockPart.setDividend(div);
				yeDiv++;
			}
		}
		
		Matcher divMatcher = dividends.matcher(line);
		if (yeDiv == 0 && divMatcher.find()) {
			yeDiv++; 
		}
	}

	private void grabBna(String line, ScreeningSupplementedStock stockPart, Matcher bnaValueMatcher) {
		switch (yeBNA) {
		case 0 :
			Matcher bnaMatcher = bna.matcher(line);
			if (bnaMatcher.find()) {
				yeBNA++;
			}
			break;
		case 1 :
			if (bnaValueMatcher.find()) {
				year1BnaGRealise = extractPattern(bnaValueMatcher);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Bourso BNA y1 for "+stockPart.getSymbol()+" : "+year1BnaGRealise);
				stockPart.setBoursoBNA(year1BnaGRealise);
				yeBNA++;
			}
			break;
		case 2 :
			if (bnaValueMatcher.find()) {
				year2Bna = extractPattern(bnaValueMatcher);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Bourso BNA y2 for "+stockPart.getSymbol()+" : "+year2Bna);
				stockPart.setBoursoEstBNA(year2Bna);
				yeBNA++;
			}
			break;
		case 3 :
			if (bnaValueMatcher.find()) {
				year3Bna = extractPattern(bnaValueMatcher);
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Bourso BNA y3 for "+stockPart.getSymbol()+" : "+year3Bna);
				yeBNA++;
			}
			break;
		default :
				//Nothing
		}
	}

	
	private BigDecimal extractPattern(Matcher valueMatcher) {
		BigDecimal value;
		String valueStr = valueMatcher.group(1).replace("<.*strong>", "").trim();
		value = (valueStr.equals("ND"))? BigDecimal.ZERO : new BigDecimal(valueStr).setScale(4, RoundingMode.HALF_EVEN);
		valueMatcher.reset();
		return value;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

	@Override
	public Boolean isResultValueEqNA() {
		return isEmpty;
	}

}
