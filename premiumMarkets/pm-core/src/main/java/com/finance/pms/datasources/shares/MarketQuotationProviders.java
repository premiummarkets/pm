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
package com.finance.pms.datasources.shares;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

/**
 * The Enum MarketQuotationProviders.
 * 
 * @author Guillaume Thoreton
 */
public enum MarketQuotationProviders {
	
	DEFAULT (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(),"yahoo"),
	YAHOO (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(),"yahoo"),
	GOOGLE (ProvidersTypes.GOOGLE,new GoogleSymbolNameResolver(),"google"),
	INVESTIR (ProvidersTypes.INVESTIR,new InvestirSymbolNameResolver(),"investir"),
	INFLATION (ProvidersTypes.INFLATION,new InvestirSymbolNameResolver(),"inflation"),
	CURRENCY (ProvidersTypes.CURRENCY,new InvestirSymbolNameResolver(),"currency");
	
	
	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);
	
	private SymbolNameResolver symbolNameResolver;
	private ProvidersTypes providersType;
	private String cmdParam;

	private MarketQuotationProviders(ProvidersTypes providersType, SymbolNameResolver marketQuotationProviderExtentions, String cmdParam) {
		this.providersType = providersType;
		this.symbolNameResolver = marketQuotationProviderExtentions;
		this.cmdParam = cmdParam;
	}
	
	public static MarketQuotationProviders valueOfCmd(String cmdParam) {
		MarketQuotationProviders eValues[] = MarketQuotationProviders.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].cmdParam.equals(cmdParam)) return eValues[i];
		}
		throw new IllegalArgumentException("No enum const ProvidersTypes." + cmdParam);
	}

	public static boolean checkProvider(String prov) {
		boolean contains = false;
		try {
			MarketQuotationProviders.valueOfCmd(prov);
			contains = true;
		} catch (IllegalArgumentException e) {
			LOGGER.debug("Wrong provider :" + prov, e);
			contains = false;
		}
		return contains;
	}

	public static String[] cmdsValues() {
		String[] retour = new String[MarketQuotationProviders.values().length];
		for (int i=0; i < MarketQuotationProviders.values().length; i++) {
			retour[i] = MarketQuotationProviders.values()[i].cmdParam;
		}
		return retour;	
	}

	public String getCmdParam() {
		return cmdParam;
	}
	
	public ProvidersTypes getProvidersType() {
		return providersType;
	}

	public SymbolNameResolver getSymbolNameResolver() {
		return symbolNameResolver;
	}

}
