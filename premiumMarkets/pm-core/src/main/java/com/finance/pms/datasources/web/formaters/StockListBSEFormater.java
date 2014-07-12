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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.shares.YahooMarketExtentions;
import com.finance.pms.datasources.web.MyUrl;

public class StockListBSEFormater extends LineFormater {
	
	
	/** The stock categorie. */
	StockCategories stockCategorie;
	
	/** The providers types. */
	MarketQuotationProviders marketQuotationsProviders;
	
	public StockListBSEFormater(String url, StockCategories stockCategorie,MarketQuotationProviders marketQuotationsProviders) {
		super(new MyUrl(url));
		this.stockCategorie = stockCategorie;
		this.marketQuotationsProviders = marketQuotationsProviders;
	
	}
	
	
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		List<Validatable> retour = new ArrayList<Validatable>();
		
		String columnDelim = ",";
		//0
		//SC_CODE,SC_NAME,SC_GROUP,SC_TYPE,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,NO_TRADES,NO_OF_SHRS,NET_TURNOV,TDCLOINDI
		//1
		//20,20MICRONS,EQ,INE144J01019,12.67,0.00,12.67,5.88,0.00,18.55
		//2
		//SC_CODE	SC_NAME	SC_GROUP	SC_TYPE	OPEN	HIGH	LOW	CLOSE	LAST	PREVCLOSE	NO_TRADES	NO_OF_SHRS	NET_TURNOV	TDCLOINDI
    	try {
			String[] strArray = line.split(columnDelim);
			if (strArray.length == 1) {
			    LOGGER.trace("Comment line : "+line);
			} else if ((strArray.length != 14 &&  strArray.length != 13) || "SC_CODE".equals(strArray[0])) {
				LOGGER.debug("Wrong file format while proceding web stock list : " + line+". Ignoring line");
			} else {
				retour.add(new Stock(
						strArray[0],
						strArray[0], 
						strArray[1].trim(), 
						true, this.stockCategorie,
						new SymbolMarketQuotationProvider(this.marketQuotationsProviders,YahooMarketExtentions.BSE.toString()),
						new MarketValuation(Market.BSE),
						"",TradingMode.CONTINUOUS,0l));
			}
			return retour;
			
		} catch (InvalidAlgorithmParameterException e) {
			throw new StopParseErrorException(retour,e.toString(),"");
		}
	}
	
	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
