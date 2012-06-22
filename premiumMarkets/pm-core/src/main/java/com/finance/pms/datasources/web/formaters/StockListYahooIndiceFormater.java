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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.shares.YahooMarketExtentions;
import com.finance.pms.datasources.web.MyUrl;


// TODO: Auto-generated Javadoc
/**
 * The Class StockListNASDAQFormater.
 * 
 * @author Guillaume Thoreton
 */
public class StockListYahooIndiceFormater extends LineFormater {
	
	/** The providers types. */
	private Market market;
	
	public StockListYahooIndiceFormater(String myUrl, Market market) {
		super(new MyUrl(myUrl));
		this.market = market;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.formaters.LineFormater#formatLine(java.lang.String)
	 */
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		//3IN.L,103.30, 4:35PM,10/27/2009,+0.80,102.50,103.30,101.10,368790 
		List<Validatable> retour = new ArrayList<Validatable>();
		
		String columnDelim = ",";
		String textDelim = "\"";
	
		//Name,	Symbol,	Security Type,	Shares Outstanding,	Market Value (millions),	Description (as filed with the SEC)																																																																																																																																																																																																																																																										

    	try {
			String[] strArray = line.split(columnDelim);
			if (strArray.length == 1) {
			    LOGGER.trace("Comment line : "+line);
			} else if (strArray.length != 9) {
				LOGGER.debug("Wrong file format while proceding web stock list : " + line+". Ignoring line");
			} else {
				retour.add(new Stock(
						removeTextDelim(strArray[0],textDelim), 
						removeTextDelim(strArray[0],textDelim),
						null, 
						false,
						StockCategories.DEFAULT_CATEGORY,
						new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
						market,"", TradingMode.CONTINUOUS, 0L));
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
		if (!ret.contains(".") 
				&& !market.getMarketExtention().equals(YahooMarketExtentions.NN)
				&& !market.getMarketExtention().getSpecificMarketExtension().equals(SymbolNameResolver.UNKNOWNEXTENSION)) {
			ret = ret.concat("."+market.getMarketExtention().getSpecificMarketExtension());
			LOGGER.debug("Applied extention "+market.getMarketExtention().getSpecificMarketExtension()+" to symbol "+txt);
		}
		if (ret.contains(".NX")) {
			ret = ret.replace(".NX", "."+YahooMarketExtentions.PAR.getSpecificMarketExtension());
		}
		return ret.replaceAll(textDeli, "");
	}
	
	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}
}
