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
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class StockComplementYahooFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	
	private Pattern nameIsinPattern;
	private Pattern nameNoIsinPattern;
	
	private String parsedIsin; 
	private String parsedName; 

	
	public StockComplementYahooFormater(String url, Stock stockPart) {
		super(new MyUrl(url));		
		params.add(stockPart);

		try {
			if (null == StockComplementYahooFormater.PATTERNS)
				StockComplementYahooFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}
		String symbol = stockPart.getSymbol().replace(".", "\\.");
		String isinPatProperty = String.format(StockComplementYahooFormater.PATTERNS.getProperty("yahoonameisincompletion"), symbol);
		nameIsinPattern = Pattern.compile(isinPatProperty);
		String noIsinPatproperty = String.format(StockComplementYahooFormater.PATTERNS.getProperty("yahoonamenoisincompletion"), symbol);
		nameNoIsinPattern = Pattern.compile(noIsinPatproperty);
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		Stock stockPart = (Stock) params.get(0);
		LOGGER.trace(line);
		
		Matcher fitNameIsin;
		fitNameIsin = nameIsinPattern.matcher(line);
		if (fitNameIsin.find()) {
			parsedName = fitNameIsin.group(1).trim();
			parsedIsin = fitNameIsin.group(2).trim();
		
			
		} else {
			Matcher fitNameNoIsin;
			fitNameNoIsin = nameNoIsinPattern.matcher(line);
			if (fitNameNoIsin.find()) {
				parsedName = fitNameNoIsin.group(1).trim();
				parsedIsin = stockPart.getSymbol().trim();
			
			}
			
		}
		
		if (parsedIsin != null && parsedName != null) {
			setIsin(stockPart);
			stockPart.setName(parsedName);
			stockPart.setState(Validatable.VALID);
			throw new StopParseFoundException(stockPart);
		}

		return null;
	}

	/**
	 * @param stockPart
	 * @throws StopParseErrorException
	 */
	private void setIsin(Stock stockPart) throws StopParseErrorException {
		String oldIsin = stockPart.getIsin();
		try {
			stockPart.setIsin(parsedIsin);
		} catch (InvalidAlgorithmParameterException e) {
			try {
				if (oldIsin != null) {
					stockPart.setIsin(oldIsin);
				} else {
					stockPart.setIsin(Stock.MISSINGCODE);
				}
			} catch (InvalidAlgorithmParameterException e1) {
				LOGGER.debug("",e);
			}
			throw new StopParseErrorException("Invalid match scrip code while setting scrip id", e.getMessage());
		}
	}
	
	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}


}
