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
import java.util.StringTokenizer;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;


// TODO: Auto-generated Javadoc
/**
 * The Class StockListBoursormaFormater.
 * 
 * @author Guillaume Thoreton
 */

public class StockListBoursormaFormater extends LineFormater {
	
	/** The stock categorie. */
	StockCategories stockCategorie;
	
	/** The providers types. */
	MarketQuotationProviders marketQuotationsProviders;
	
	/**
	 * Instantiates a new stock list boursorma formater.
	 * 
	 * @param url the url
	 * @param stockCategorie the stock categorie
	 * @param marketQuotationsProviders the market quotations providers
	 * 
	 * @author Guillaume Thoreton
	 */
	public StockListBoursormaFormater(String url, StockCategories stockCategorie,MarketQuotationProviders marketQuotationsProviders) {
		super(new MyUrl(url));
		this.stockCategorie = stockCategorie;
		this.marketQuotationsProviders = marketQuotationsProviders;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.db.HttpSource.LineFormater#formatLine()
	 */
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		StringTokenizer strt = new StringTokenizer(line,"\t");
		List<Validatable> retour = new ArrayList<Validatable>();
		assert strt.countTokens() == 8;

		try {
			if (strt.countTokens() != 8) {
				LOGGER.warn("Wrong file format while proceding web stock list : "+line);
			} else {
				//TODO Set proper market
				//    		retour = new Stock(strt.nextToken(),null, stockCategorie,new SymbolMarketQuotationProvider(marketQuotationsProviders,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
				//    				MarketListProviders.UNKNOWN);
				//    		retour.setName(strt.nextToken());
				retour.add(new Stock(strt.nextToken(),null,strt.nextToken(),false,stockCategorie,
						new SymbolMarketQuotationProvider(marketQuotationsProviders,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
						Market.EURONEXT,"",TradingMode.CONTINUOUS,0l));
			}
			return retour;
		} catch (InvalidAlgorithmParameterException e) {
			throw new StopParseErrorException(retour,e.toString(),"");
		}
	}


	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.formaters.LineFormater#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((stockCategorie == null) ? 0 : stockCategorie.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.formaters.LineFormater#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StockListBoursormaFormater other = (StockListBoursormaFormater) obj;
		if (stockCategorie == null) {
			if (other.stockCategorie != null)
				return false;
		} else if (!stockCategorie.equals(other.stockCategorie))
			return false;
		return true;
	}
	
	
	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}
	
	
}

