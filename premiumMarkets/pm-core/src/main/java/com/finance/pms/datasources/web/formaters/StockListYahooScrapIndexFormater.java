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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;

public class StockListYahooScrapIndexFormater extends LineFormater {
	
	Pattern pattern = Pattern.compile(".{1,50}<b><a href=\"http://uk.finance.yahoo.com/q\\?s=(.{3,20})\">(.{3,20})</a></b></td><td class=\"yfnc_tabledata1\"><small>(.{0,20})</small></td>.{1,50}");	
	
	public StockListYahooScrapIndexFormater(String url) {
		super(new MyUrl(url));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		//<tr>
		//<td class="yfnc_tabledata1"><b><a href="/q?s=888.L">888.L</a></b></td>
		//<td class="yfnc_tabledata1"><small>888 HOLDINGS</small></td>
		//<td class="yfnc_tabledata1" align="center" nowrap="nowrap"><b>93.00 p</b> <nobr><small>4:35PM </small></nobr></td>
		//<td class="yfnc_tabledata1" align="center" nowrap="nowrap"><img src="http://l.yimg.com/a/i/us/fi/03rd/up_g.gif" alt="Up" border="0" height="14" width="10"> <b style="color: rgb(0, 136, 0);">0.20 (0.22%)</b></td>
		//<td class="yfnc_tabledata1" align="right">499,011</td>
		//</tr>
		
		List<Validatable> retour= new ArrayList<Validatable>();
		
		Matcher fit = pattern.matcher(line);
		while (fit.find()) {
			String symbol = fit.group(1);
			String name = fit.group(3);
			
			 try {
				retour.add(new Stock(symbol, symbol, name, false,
							StockCategories.INDICES_OTHER,
							new SymbolMarketQuotationProvider(MarketQuotationProviders.DEFAULT,null),
							Market.UNKNOWN,"", TradingMode.UNKNOWN, 0L));
				
			} catch (InvalidAlgorithmParameterException e) {
				LOGGER.debug("", e);
				throw new StopParseErrorException("Invalid match scrip code while setting scrip id", e.getMessage());
			}
		}
		
		return retour;
		
	}
	
	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}
	
}
