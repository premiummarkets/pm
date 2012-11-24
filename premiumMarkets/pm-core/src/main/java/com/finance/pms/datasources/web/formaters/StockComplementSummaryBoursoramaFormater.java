/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.screening.TrendSupplementedStock;

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

	public StockComplementSummaryBoursoramaFormater(String url, TrendSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementSummaryBoursoramaFormater.PATTERNS) StockComplementSummaryBoursoramaFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		
		dividends = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaDiv"));
		valueDiv = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaValueDiv"));

		bna = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaBNA"));
		valueBNA = Pattern.compile(StockComplementSummaryBoursoramaFormater.PATTERNS.getProperty("boursoramaValueBNA"));

		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		TrendSupplementedStock stockPart = (TrendSupplementedStock) params.get(0);
		LOGGER.trace(line);
		
		Matcher divValueMatcher = valueDiv.matcher(line);
		grabDiv(line, stockPart, divValueMatcher);
		
		Matcher bnaValueMatcher = valueBNA.matcher(line);
		grabBna(line, stockPart, bnaValueMatcher);

		if (yeDiv == 2) {
			LOGGER.debug("Found bourso PEG for "+stockPart.getSymbol()+ " : BNA "+stockPart.getBoursoBNA()+" and estBNA "+stockPart.getBoursoEstBNA()+" and div "+stockPart.getDividend());
			Validatable validatable = stockPart;
			validatable.setState(Validatable.VALID);
			throw new StopParseFoundException(validatable);
		}

		return null;
	}
	
	private void grabDiv(String line, TrendSupplementedStock stockPart, Matcher valueMatcher) {
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

	/**
	 * @param line
	 * @param stockPart
	 * @param bnaValueMatcher
	 */
	private void grabBna(String line, TrendSupplementedStock stockPart, Matcher bnaValueMatcher) {
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
				LOGGER.debug("Bourso BNA y1 for "+stockPart.getSymbol()+" : "+year1BnaGRealise);
				stockPart.setBoursoBNA(year1BnaGRealise);
				yeBNA++;
			}
			break;
		case 2 :
			if (bnaValueMatcher.find()) {
				year2Bna = extractPattern(bnaValueMatcher);
				LOGGER.debug("Bourso BNA y2 for "+stockPart.getSymbol()+" : "+year2Bna);
				stockPart.setBoursoEstBNA(year2Bna);
				yeBNA++;
			}
			break;
		case 3 :
			if (bnaValueMatcher.find()) {
				year3Bna = extractPattern(bnaValueMatcher);
				LOGGER.debug("Bourso BNA y3 for "+stockPart.getSymbol()+" : "+year3Bna);
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
		value = (valueStr.equals("ND"))? BigDecimal.ZERO : new BigDecimal(valueStr).setScale(4, BigDecimal.ROUND_DOWN);
		valueMatcher.reset();
		return value;
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}

	@Override
	public Boolean isEmptyValue() {
		return isEmpty;
	}

}
