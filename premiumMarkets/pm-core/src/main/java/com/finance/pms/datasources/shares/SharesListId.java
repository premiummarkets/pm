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
package com.finance.pms.datasources.shares;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.ProvidersTypes;

// TODO: Auto-generated Javadoc
/**
 * The Enum MarketListProviders.
 * Defines a market list providers
 * Links each market place with on to many web stock provider 
 * 
 * @author Guillaume Thoreton
 */
public enum SharesListId {

	UNKNOWN ("unknown",ProvidersTypes.NONE,"No market"),
	EURONEXT ("euronext",ProvidersTypes.EURONEXT,"All Euronext shares from www.euronext.com"),
	BOURSORAMA ("boursorama",ProvidersTypes.BOURSORAMA,"All Euronext shares from www.boursorama.com"),
	NASDAQ ("nasdaq",ProvidersTypes.NASDAQ,"All nasdaq from www.nasdaq.com"),
	ASX ("asx",ProvidersTypes.ASX,"All ASX from www.asx.com.au"),
	BSE ("bse",ProvidersTypes.BSE,"All BSE form bseindia.com"),
	GOOGLENYSE ("nyse",ProvidersTypes.GOOGLE,"All NYSE from google"),
	GOOGLEAMEX ("amex",ProvidersTypes.GOOGLE, "All AMEX from google"),
	YAHOOINDICES ("yahooIndices",ProvidersTypes.YAHOOINDICES, "Fill in the box bellow to create your custom Yahoo indices below.");
	

	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(SharesListId.class);

	private String sharesListCmdParam;
	private ProvidersTypes providersType;
	private String description;


	/**
	 * Instantiates a new market list providers.
	 * 
	 * @param market the market
	 * @param providersType the providers type
	 * @param cmdParam the cmd param
	 * 
	 * @author Guillaume Thoreton
	 */
	private SharesListId(String cmdParam, ProvidersTypes providersType, String content) {
		this.providersType = providersType;
		this.sharesListCmdParam = cmdParam;
		this.description=content;
	}
	
	/**
	 * Value of cmd.
	 * 
	 * @param st the st
	 * 
	 * @return the market list providers
	 * 
	 * @author Guillaume Thoreton
	 */
	public static SharesListId valueOfCmd(String st) {
		SharesListId eValues[] = SharesListId.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].sharesListCmdParam.equals(st)) return eValues[i];
		}
		throw new IllegalArgumentException(
				"No enum const MarketListProviders." + st);
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
			SharesListId.valueOfCmd(prov);
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
		String[] retour = new String[SharesListId.values().length];
		for (int i=0; i < SharesListId.values().length; i++) {
			retour[i] = SharesListId.values()[i].sharesListCmdParam;
		}
		return retour;	
	}

	/**
	 * Gets the cmd param.
	 * 
	 * @return the cmd param
	 */
	public String getSharesListCmdParam() {
		return sharesListCmdParam;
	}


	/**
	 * Gets the providers type.
	 * 
	 * @return the providers type
	 */
	public ProvidersTypes getProvidersType() {
		return providersType;
	}
	


	public String getDescription() {
		return description;
	}


}
