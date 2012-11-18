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
package com.finance.pms.datasources.shares;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

// TODO: Auto-generated Javadoc
/**
 * The Enum MarketQuotationProviders.
 * 
 * @author Guillaume Thoreton
 */
public enum MarketQuotationProviders {
	
	DEFAULT (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(),"yahoo"),
	YAHOO (ProvidersTypes.YAHOO, new YahooSymbolNameResolver(),"yahoo"),
	//BOURSORAMA (ProvidersTypes.BOURSORAMA, new BoursoramaSymbolNameResolver(),"boursorama"),
	//YAHOOFR (ProvidersTypes.YAHOOFR,new YahooSymbolNameResolver(),"yahoofr"),
	GOOGLE (ProvidersTypes.GOOGLE,new GoogleSymbolNameResolver(),"google"),
	//YAHOOINDICES (ProvidersTypes.GOOGLE,new YahooSymbolNameResolver(),"yahooIndices"),
	INVESTIR (ProvidersTypes.INVESTIR,new InvestirSymbolNameResolver(),"investir");
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);
	
	private SymbolNameResolver symbolNameResolver;
	private ProvidersTypes providersType;
	private String cmdParam;

	/**
	 * Instantiates a new market quotation providers.
	 * 
	 * @param providersType the providers type
	 * @param marketQuotationProviderExtentions the market quotation provider extentions
	 * @param cmdParam the cmd param
	 * 
	 * @author Guillaume Thoreton
	 */
	private MarketQuotationProviders(ProvidersTypes providersType, SymbolNameResolver marketQuotationProviderExtentions, String cmdParam) {
		this.providersType = providersType;
		this.symbolNameResolver = marketQuotationProviderExtentions;
		this.cmdParam = cmdParam;
	}
	
	/**
	 * Value of cmd.
	 * 
	 * @param cmdParam the st
	 * 
	 * @return the market quotation providers
	 * 
	 * @author Guillaume Thoreton
	 */
	public static MarketQuotationProviders valueOfCmd(String cmdParam) {
		MarketQuotationProviders eValues[] = MarketQuotationProviders.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].cmdParam.equals(cmdParam)) return eValues[i];
		}
		throw new IllegalArgumentException(
	            "No enum const ProvidersTypes." + cmdParam);
	}
	
	/**
	 * Check provider.
	 * 
	 * @param prov the prov
	 * 
	 * @return true, if successful
	 * 
	 * @author Guillaume Thoreton
	 */
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
	
	/**
	 * Cmds values.
	 * 
	 * @return the string[]
	 * 
	 * @author Guillaume Thoreton
	 */
	public static String[] cmdsValues() {
		String[] retour = new String[MarketQuotationProviders.values().length];
		for (int i=0; i < MarketQuotationProviders.values().length; i++) {
			retour[i] = MarketQuotationProviders.values()[i].cmdParam;
		}
		return retour;	
	}
	
	/**
	 * Gets the cmd param.
	 * 
	 * @return the cmd param
	 */
	public String getCmdParam() {
		return cmdParam;
	}
	
	public ProvidersTypes getProvidersType() {
		return providersType;
	}

	
	/**
	 * Gets the symbol name resolver.
	 * 
	 * @return the symbol name resolver
	 */
	public SymbolNameResolver getSymbolNameResolver() {
		return symbolNameResolver;
	}

}
