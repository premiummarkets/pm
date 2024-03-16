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
package com.finance.pms.datasources.shares;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

/**
 * The Enum MarketQuotationProviders.
 * 
 * @author Guillaume Thoreton
 */
public enum MarketQuotationProviders_old {
	
	DEFAULT (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(), "yahoo"),
	YAHOO (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(), "yahoo"),
	GOOGLE (ProvidersTypes.GOOGLE, new GoogleSymbolNameResolver(), "google"),
	INVESTIR (ProvidersTypes.INVESTIR, new InvestirSymbolNameResolver(), "investir"),
	INFLATION (ProvidersTypes.INFLATION, new InvestirSymbolNameResolver(), "inflation"),
	CURRENCY (ProvidersTypes.CURRENCY, new InvestirSymbolNameResolver(), "currency"),
	INTERESTRATES (ProvidersTypes.INTERESTRATES, new InvestirSymbolNameResolver(), "interestrates"),
	HOUSEPRICES (ProvidersTypes.HOUSEPRICES, new InvestirSymbolNameResolver(), "houseprices");
	
	
	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);
	
	private SymbolNameResolver symbolNameResolver;
	private String cmdParam;

	private MarketQuotationProviders_old(ProvidersTypes providersType, SymbolNameResolver marketQuotationProviderExtentions, String cmdParam) {
		this.symbolNameResolver = marketQuotationProviderExtentions;
		this.cmdParam = cmdParam;
	}
	
	public static MarketQuotationProviders_old valueOfCmd(String cmdParam) {
		MarketQuotationProviders_old eValues[] = MarketQuotationProviders_old.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].cmdParam.equals(cmdParam)) return eValues[i];
		}
		throw new IllegalArgumentException("No enum const ProvidersTypes." + cmdParam);
	}

	public static boolean checkProvider(String prov) {
		boolean contains = false;
		try {
			MarketQuotationProviders_old.valueOfCmd(prov);
			contains = true;
		} catch (IllegalArgumentException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Wrong provider :" + prov, e);
			contains = false;
		}
		return contains;
	}

	public static String[] cmdsValues() {
		String[] retour = new String[MarketQuotationProviders_old.values().length];
		for (int i=0; i < MarketQuotationProviders_old.values().length; i++) {
			retour[i] = MarketQuotationProviders_old.values()[i].cmdParam;
		}
		return retour;	
	}

	public String getCmdParam() {
		return cmdParam;
	}

	public SymbolNameResolver getSymbolNameResolver() {
		return symbolNameResolver;
	}

}
