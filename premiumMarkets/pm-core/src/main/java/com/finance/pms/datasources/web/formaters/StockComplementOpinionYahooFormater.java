/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

public class StockComplementOpinionYahooFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;
	
	private Pattern meanRec;
	private Pattern meanTarget;
	private Pattern nbOpinions;
	private Integer lineNumber;
	
	private Boolean isEmpty = false;

	
	public StockComplementOpinionYahooFormater(String url, TrendSupplementedStock stockPart) {
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
		TrendSupplementedStock stockPart = (TrendSupplementedStock) params.get(0);

		if (stockPart.isNOTSetYahooTargetPrice()) {
			Matcher mTargetP = meanTarget.matcher(line);

			if (mTargetP.find()) {
				String mTargetPRes = mTargetP.group(1);
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

	/**
	 * @param stockPart
	 * @throws StopParseFoundException
	 */
	private void endScrapping(TrendSupplementedStock stockPart) throws StopParseFoundException {
		
			if (stockPart.isNOTSetYahooMeanRecommendations() && stockPart.isNOTSetYahooTargetPrice()) {
				isEmpty = true;
			}
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		
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
