/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

public class StockComplementYahooFormater extends LineFormater {
	
	/** The PATTERNS. */
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
		nameIsinPattern = Pattern.compile(StockComplementYahooFormater.PATTERNS.getProperty("yahoonameisincompletion", symbol));
		nameNoIsinPattern = Pattern.compile(StockComplementYahooFormater.PATTERNS.getProperty("yahoonamenoisincompletion", symbol));
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		Stock stockPart = (Stock) params.get(0);
		LOGGER.trace(line);
		
		Matcher fitNameIsin;
		fitNameIsin = nameIsinPattern.matcher(line);
		if (fitNameIsin.find()) {
			parsedName = fitNameIsin.group(1);
			parsedIsin = fitNameIsin.group(2);
		
			
		} else {
			Matcher fitNameNoIsin;
			fitNameNoIsin = nameNoIsinPattern.matcher(line);
			if (fitNameNoIsin.find()) {
				parsedName = fitNameNoIsin.group(1);
				parsedIsin = stockPart.getSymbol();
			
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
	public Boolean canHaveEmptyResults() {
		return false;
	}


}
