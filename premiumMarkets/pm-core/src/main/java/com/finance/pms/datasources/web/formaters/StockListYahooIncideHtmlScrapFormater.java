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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;

public class StockListYahooIncideHtmlScrapFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	
	private MarketValuation market;
	private Pattern yahooIndiceStockElementPattern;

	
	public StockListYahooIncideHtmlScrapFormater(String myUrl, MarketValuation market) {
		super(new MyUrl(myUrl));
		this.market = market;
		
		try {
			if (null == StockListYahooIncideHtmlScrapFormater.PATTERNS)
				StockListYahooIncideHtmlScrapFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}
		yahooIndiceStockElementPattern = Pattern.compile(StockListYahooIncideHtmlScrapFormater.PATTERNS.getProperty("yahooindicestockelement"));
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		LOGGER.info(line);
		List<Validatable> retour = new ArrayList<Validatable>();
		
		try {
			Matcher yahooIndiceStockElementMatcher;
			yahooIndiceStockElementMatcher = yahooIndiceStockElementPattern.matcher(line);
			while (yahooIndiceStockElementMatcher.find()) {
				String symbol = yahooIndiceStockElementMatcher.group(1);
				String name = yahooIndiceStockElementMatcher.group(2);
				retour.add(
						new Stock(symbol, symbol, name, true, StockCategories.DEFAULT_CATEGORY,
						new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
						market,"", TradingMode.CONTINUOUS, 0L));
			}
		} catch (InvalidAlgorithmParameterException e) {
			throw new StopParseErrorException(retour,e.toString(),"");
		} 

		return retour;
	}
	
	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}
}
