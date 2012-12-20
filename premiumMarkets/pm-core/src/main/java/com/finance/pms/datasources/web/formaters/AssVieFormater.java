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
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;

public class AssVieFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	
	private Pattern stockLine;
	private Boolean done = Boolean.FALSE;
	
	public AssVieFormater(String url) {
		super(new MyUrl(url));
		
		try {
			if (null == AssVieFormater.PATTERNS)
				AssVieFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		stockLine = Pattern.compile(AssVieFormater.PATTERNS.getProperty("assVieStockLine"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		List<Validatable> ret = new ArrayList<Validatable>();

		if (!done) {
			Matcher fit;

			LOGGER.trace(line);
			fit = stockLine.matcher(line);
			if (fit.find()) {
				String name = fit.group(2).trim();
				String isin = fit.group(1).trim();

				try {
					Validatable stock = 
							new Stock(isin, isin, name, true,
							StockCategories.SICAV, new SymbolMarketQuotationProvider(MarketQuotationProviders.INVESTIR,SymbolNameResolver.UNKNOWNEXTENSIONCLUE), 
							new MarketValuation(Market.EURONEXT),
							"",TradingMode.CONTINUOUS,0l);
					
					ret.add(stock);
				} catch (InvalidAlgorithmParameterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
		}

		return ret;
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}
}
