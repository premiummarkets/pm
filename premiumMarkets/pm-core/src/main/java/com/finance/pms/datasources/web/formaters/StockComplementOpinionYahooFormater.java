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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.screening.ScreeningSupplementedStock;

public class StockComplementOpinionYahooFormater extends LineFormater {

	private static PatternProperties PATTERNS;
	
	private Pattern meanRec;
	private Pattern meanTarget;
	private Pattern nbOpinions;
	private Integer lineNumber;
	
	private Boolean isEmpty = false;

	
	public StockComplementOpinionYahooFormater(String url, ScreeningSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);
		lineNumber = 1;

		try {
			if (null == StockComplementOpinionYahooFormater.PATTERNS)
				StockComplementOpinionYahooFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		
		meanRec = Pattern.compile(StockComplementOpinionYahooFormater.PATTERNS.getProperty("yahooopinioncompletion"));
		nbOpinions =  Pattern.compile(StockComplementOpinionYahooFormater.PATTERNS.getProperty("yahoonumberopinions"));
		meanTarget =  Pattern.compile(StockComplementOpinionYahooFormater.PATTERNS.getProperty("yahoomeantargetcompletion"));
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		lineNumber ++;

		LOGGER.trace(line);
		ScreeningSupplementedStock stockPart = (ScreeningSupplementedStock) params.get(0);

		if (stockPart.isNOTSetYahooTargetPrice()) {
			Matcher mTargetP = meanTarget.matcher(line);

			if (mTargetP.find()) {
				String mTargetPRes = mTargetP.group(1).replace(",", "");
				if ("NaN".equals(mTargetPRes)) {
					stockPart.setYahooTargetPrice(BigDecimal.ZERO);
				} else {
					stockPart.setYahooTargetPrice(new BigDecimal(mTargetPRes));
				}
			}
		}
		if (stockPart.isNOTSetYahooMeanRecommendations()) {
			Matcher mRec = meanRec.matcher(line);
			if (mRec.find()) {
				String rmRecRes = mRec.group(1);
				if ("NaN".equals(rmRecRes)) {
					stockPart.setYahooMeanRecommendations(BigDecimal.ZERO);
				} else {
					stockPart.setYahooMeanRecommendations(new BigDecimal(rmRecRes));
				}

			}
		}

		//This is a stopper
		if (!stockPart.isNOTSetYahooMeanRecommendations() && !stockPart.isNOTSetYahooTargetPrice()) {
			Matcher mTarg = nbOpinions.matcher(line);
			if (mTarg.find()) {
				Integer nbOps = new Integer(mTarg.group(1));
				if (nbOps < 5) {
					stockPart.setYahooMeanRecommendations(BigDecimal.ZERO);
					stockPart.setYahooTargetPrice(BigDecimal.ZERO);
				} 
				endScrapping(stockPart);
			}		
		}

		return null;
	}

	private void endScrapping(ScreeningSupplementedStock stockPart) throws StopParseFoundException {
		
			if (stockPart.isNOTSetYahooMeanRecommendations() && stockPart.isNOTSetYahooTargetPrice()) {
				isEmpty = true;
			}
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		
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
