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
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

/**
 * The Class StockComplementFormater.
 * 
 * @author Guillaume Thoreton
 */
public class StockComplementBoursoFormater extends LineFormater {
	
	private static PatternProperties PATTERNS; 

	public StockComplementBoursoFormater(String url, Stock stockPart) {
		super(new MyUrl(url));
		params.add(stockPart);
	}

	public Stock getStock() {
		return (Stock) this.params.get(0);
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		Stock stockPart = (Stock) params.get(0);
		
		try {
			if (null == StockComplementBoursoFormater.PATTERNS)
				StockComplementBoursoFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
			throw new StopParseErrorException("Error : pattern file not found : resources/patterns.properties", e.getMessage());
		}
		
		Pattern codePattern;
		Pattern namePattern;
		Matcher fit;
		//TODO Validation of an already check => returns a Validatable object
		if (stockPart.isFieldSet("isin") && stockPart.isFieldSet("symbol") && stockPart.isFieldSet("name")) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		}
		//New share! Some fields have not been set yet => returns a query object //TODO build the query in the caller
		if (!(stockPart.getIsin() == null) && !stockPart.getIsin().equals("") && !stockPart.getIsin().equals(Stock.MISSINGCODE)) {
			codePattern = Pattern.compile(StockComplementBoursoFormater.PATTERNS.getProperty("symbolcompletionbyisin", stockPart.getIsin()));
			fit = codePattern.matcher(line);
			if (fit.matches()) {
				try {
					stockPart.setSymbol(fit.group(1));
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.debug("", e);
					throw new StopParseErrorException("Invalide match symbol/isin while setting symbol", e.getMessage());
				}
			}
		}
		if (!(stockPart.getSymbol() == null) && !stockPart.getSymbol().equals("") && !stockPart.getSymbol().equals(Stock.MISSINGCODE)) {
			codePattern = Pattern
					.compile(StockComplementBoursoFormater.PATTERNS.getProperty("isincompletionbysymbol", stockPart.getSymbol()));
			fit = codePattern.matcher(line);
			if (fit.matches()) {
				try {
					stockPart.setIsin(fit.group(1));
				} catch (InvalidAlgorithmParameterException e) {
					LOGGER.debug("", e);
					throw new StopParseErrorException("Invalide match symbol/isin while setting isin", e.getMessage());
				}
			}
		}
		if (stockPart.getName() == null || stockPart.getName().equals("")) {
			namePattern = Pattern.compile(StockComplementBoursoFormater.PATTERNS.getProperty("namecompletion1"));
			fit = namePattern.matcher(line);
			if (fit.matches()) {
				stockPart.setName(fit.group(1));
			}
		}
		if (stockPart.getName() == null || stockPart.getName().equals("")) {
			namePattern = Pattern.compile(StockComplementBoursoFormater.PATTERNS.getProperty("namecompletion2"));
			fit = namePattern.matcher(line);
			if (fit.matches()) {
				stockPart.setName(fit.group(1));
			}
		}
		if (stockPart.isFieldSet("isin") && stockPart.isFieldSet("symbol") && stockPart.isFieldSet("name")) {
			Validatable v = stockPart;
			v.setState(Validatable.VALID);
			throw new StopParseFoundException(v);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StockComplementBoursoFormater other = (StockComplementBoursoFormater) obj;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}
}
