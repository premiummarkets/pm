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

public class StockComplementOpinionBoursoramaFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;
	
	private Pattern meanRec;
	private Pattern meanTarget;

	private int lineNumber;
	private Pattern nbOpinions;
	
	private Boolean isEmpty = false;

	
	public StockComplementOpinionBoursoramaFormater(String url, TrendSupplementedStock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementOpinionBoursoramaFormater.PATTERNS)
				StockComplementOpinionBoursoramaFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		
		meanRec = Pattern.compile(StockComplementOpinionBoursoramaFormater.PATTERNS.getProperty("boursoopinioncompletion"));
		nbOpinions =  Pattern.compile(StockComplementOpinionBoursoramaFormater.PATTERNS.getProperty("boursoramanumberopinions"));
		meanTarget =  Pattern.compile(StockComplementOpinionBoursoramaFormater.PATTERNS.getProperty("boursomeantargetcompletion"));
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		lineNumber ++;

		LOGGER.trace(line);

		TrendSupplementedStock stockPart = (TrendSupplementedStock) params.get(0);

		if (stockPart.isNOTSetBoursoMeanRecommendations() && 100 <= lineNumber && lineNumber <= 500) {
			Matcher mRec = meanRec.matcher(line);
			if (mRec.find()) {
				stockPart.setBoursoMeanRecommendations(new BigDecimal(mRec.group(1)));
			}
		}
		if (stockPart.isNOTSetBoursoTargetPrice() &&  100 <= lineNumber && lineNumber <= 500) {
			Matcher mTarg = meanTarget.matcher(line);
			if (mTarg.find()) {
				stockPart.setBoursoTargetPrice(new BigDecimal(mTarg.group(1).replaceAll(",", "")));
			}
		}
		
		//Careful that this is not a stopper and opinions will be valid if not matched
		if (!stockPart.isNOTSetBoursoMeanRecommendations() && !stockPart.isNOTSetBoursoTargetPrice() &&  200 <= lineNumber && lineNumber <= 600) {
			Matcher mTarg = nbOpinions.matcher(line);
			if (mTarg.find()) {
				Integer nbOps = new Integer(mTarg.group(1));
				if (nbOps < 3) {
					stockPart.setBoursoMeanRecommendations(BigDecimal.ZERO);
					stockPart.setBoursoTargetPrice(BigDecimal.ZERO);
				}
				endScrapping(stockPart);
			}
		}

		if (600 < lineNumber) endScrapping(stockPart);	

		return null;
	}

	/**
	 * @param stockPart
	 * @throws StopParseFoundException
	 */
	private void endScrapping(TrendSupplementedStock stockPart) throws StopParseFoundException {
		
		if (!stockPart.isNOTSetBoursoMeanRecommendations() && !stockPart.isNOTSetBoursoTargetPrice()) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		} else
			if (!stockPart.isNOTSetBoursoMeanRecommendations()) {
				stockPart.setBoursoTargetPrice(BigDecimal.ZERO);
				Validatable v = stockPart;
				v.setState(Validatable.VALID);
				throw new StopParseFoundException(v);
			} else
				if (!stockPart.isNOTSetBoursoTargetPrice()) {
					stockPart.setBoursoMeanRecommendations(BigDecimal.ZERO);
					Validatable v = stockPart;
					v.setState(Validatable.VALID);
					throw new StopParseFoundException(v);
				} else {
					stockPart.setBoursoTargetPrice(BigDecimal.ZERO);
					stockPart.setBoursoMeanRecommendations(BigDecimal.ZERO);
					Validatable v = stockPart;
					v.setState(Validatable.VALID);
					isEmpty = true;
					throw new StopParseFoundException(v);
				}
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
