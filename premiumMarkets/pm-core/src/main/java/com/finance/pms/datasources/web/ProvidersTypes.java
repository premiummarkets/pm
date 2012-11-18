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
package com.finance.pms.datasources.web;

// TODO: Auto-generated Javadoc
/**
 * The Enum ProvidersTypes.
 * 
 * @author Guillaume Thoreton
 */
public enum ProvidersTypes {
	
	/** The NONE. */
	NONE ("none","None"),
	
	/** The YAHOO. */
	YAHOO ("yahoo","Yahoo"),
	
	/** The BOURSORAMA. */
	BOURSORAMA ("boursorama","Boursorama"),
	
	/** The YAHOOFR. */
	YAHOOFR ("yahoofr","YahooFR"),
	
	/** The EURONEXT. */
	EURONEXT ("euronext","EuroNext"),
	
	/** The EURONEXT. */
	NASDAQ ("nasdaq","NASDAQ"),
	
	ASX ("asx","ASX"),
	
	BSE ("bse","BSE"),
	
	/** The EURONEXT. */
	GOOGLE ("google","Google"),
	
	YAHOOINDICES ("yahooindex","YahooIndex"), 
	
	INVESTIR("investir","Investir");
	
	
	/** The class name. */
	private String className;
	
	
	/**
	 * Instantiates a new providers types.
	 * 
	 * @param cmdParam the cmd param
	 * @param className the class name
	 * 
	 * @author Guillaume Thoreton
	 */
	private ProvidersTypes(String cmdParam, String className) {
		this.className = className;
	}
	

	/**
 * Gets the class name.
 * 
 * @return the class name
 */
	public String getClassName() {
		return className;
	}
	
	

}
