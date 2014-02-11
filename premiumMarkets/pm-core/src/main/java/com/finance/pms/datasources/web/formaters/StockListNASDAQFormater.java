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
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;


/**
 * The Class StockListNASDAQFormater.
 * 
 * @author Guillaume Thoreton
 */
public class StockListNASDAQFormater extends LineFormater {
	

	StockCategories stockCategorie;
	MarketQuotationProviders marketQuotationsProviders;
	
	/**
	 * Instantiates a new stock list euro next formater.
	 * 
	 * @param url the url
	 * @param stockCategorie the stock categorie
	 * @param marketQuotationsProviders the market quotations providers
	 * 
	 * @author Guillaume Thoreton
	 */
	public StockListNASDAQFormater(String url, StockCategories stockCategorie,MarketQuotationProviders marketQuotationsProviders) {
		super(new MyUrl(url));
		this.stockCategorie = stockCategorie;
		this.marketQuotationsProviders = marketQuotationsProviders;
	}
	
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		List<Validatable> retour = new ArrayList<Validatable>();
		
		String columnDelim = "\",\"";
		String textDelim = "\"";
	
		//Name,	Symbol,	Security Type,	Shares Outstanding,	Market Value (millions),	Description (as filed with the SEC)																																																																																																																																																																																																																																																										

    	try {
			String[] strArray = line.split(columnDelim);
			if (strArray.length == 1) {
				//FIXME handle line break in descriptions => handle several lines ??
			    LOGGER.trace("Comment line : "+line);
			} else if (strArray.length != 6 || "Name".equals(removeTextDelim(strArray[0], textDelim))) {
				LOGGER.debug("Wrong file format while proceding web stock list : " + line+". Ignoring line");
			} else {
				retour.add(new Stock(
						removeTextDelim(strArray[1],textDelim),
						removeTextDelim(strArray[1],textDelim), 
						removeTextDelim(strArray[0],textDelim), 
						true,
						this.stockCategorie,
						new SymbolMarketQuotationProvider(this.marketQuotationsProviders, SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
						new MarketValuation(Market.NASDAQ),
						"",TradingMode.CONTINUOUS,0l));
			}
			return retour;
			
		} catch (InvalidAlgorithmParameterException e) {
			throw new StopParseErrorException(retour,e.toString(),"");
		}
	}
	
	/**
	 * Removes the text delim.
	 * 
	 * @param txt the txt
	 * @param textDeli the text deli
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	private String removeTextDelim(String txt, String textDeli) {
		String ret = txt;
		return ret.replaceAll(textDeli, "");
	}
	
	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}
}
