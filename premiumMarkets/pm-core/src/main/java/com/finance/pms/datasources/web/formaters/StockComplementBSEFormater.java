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
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class StockComplementBSEFormater extends LineFormater {
	
	/** The PATTERNS. */
	private static PatternProperties PATTERNS;
	
	private Pattern codePatternExtended;
	private Pattern codePatternShort;
	
	
	public StockComplementBSEFormater(String url, Stock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);

		try {
			if (null == StockComplementBSEFormater.PATTERNS)
				StockComplementBSEFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		String name = stockPart.getName().replace("(", "\\(").replace(")","\\)");
		codePatternExtended = Pattern.compile(StockComplementBSEFormater.PATTERNS.getProperty("extendscripidbyname", name));
		String shortN = name.split(" ")[0];
		codePatternShort= Pattern.compile(StockComplementBSEFormater.PATTERNS.getProperty("extendscripidbyname", shortN.substring(0,(shortN.length()<4)?shortN.length():4)));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		Stock stockPart = (Stock) params.get(0);	

		Matcher fit;

		LOGGER.trace(line);
		fit = codePatternExtended.matcher(line);
		if (fit.find()) {
			setSymbol(stockPart, fit);
		} else {
			fit = codePatternShort.matcher(line);
			if (fit.find()) {
				setSymbol(stockPart, fit);
			}
		}

		if (stockPart.isFieldSet("isin") && stockPart.isFieldSet("symbol") && stockPart.isFieldSet("name")) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		}

		return null;
	}

	/**
	 * @param stockPart
	 * @param fit
	 * @throws StopParseErrorException
	 */
	private void setSymbol(Stock stockPart, Matcher fit) throws StopParseErrorException {
		try {
			stockPart.setSymbol(fit.group(1));
		} catch (InvalidAlgorithmParameterException e) {
			LOGGER.debug("", e);
			throw new StopParseErrorException("Invalid match scrip code while setting scrip id", e.getMessage());
		}
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return true;
	}
}
