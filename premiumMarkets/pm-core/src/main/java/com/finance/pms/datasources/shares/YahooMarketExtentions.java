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
package com.finance.pms.datasources.shares;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Enum YahooMarketExtentions.
 * 
 * @author Guillaume Thoreton
 */
public enum  YahooMarketExtentions implements Serializable {
	
	/** The PAR. */
	PAR ("PA"),
	
	/** The AMS. */
	AMS ("AS"),
	
	/** The BRU. */
	BRU ("BR"),
	
	/** The LIS. */
	LIS ("LS"),
	
	/** The LON. */
	LON ("L"),
	
	ASX ("AX"),
	
	BSE ("BO"),
	
	/** The NYSE. */
	NYSE (SymbolNameResolver.UNKNOWNEXTENSION),
	
	/** The AMEX. */
	AMEX (SymbolNameResolver.UNKNOWNEXTENSION),
	
	/** The AMEX. */
	NASDAQ (SymbolNameResolver.UNKNOWNEXTENSION),
	
	/** The UNKNOWN. */
	NN (SymbolNameResolver.UNKNOWNEXTENSION), 
	
	TSX ("TO"),
	
	EURONEXT ("NX");
	
	
	/** The nyse extension. */
	private String marketExtension;
	
	/**
	 * Instantiates a new markets.
	 * 
	 * @param specificMarketExtension the specific market extension
	 * 
	 * @author Guillaume Thoreton
	 */
	private YahooMarketExtentions(String specificMarketExtension) {
		this.marketExtension = specificMarketExtension;
	}

	/**
	 * Gets the nyse extension.
	 * 
	 * @return the nyse extension
	 */
	public String getSpecificMarketExtension() {
		return marketExtension;
	}
	
	/**
	 * Gets the market extension.
	 * 
	 * @param extensionClue the extension clue
	 * 
	 * @return the market extension
	 */
	public static String getMarketExtension(String extensionClue) {
		return YahooMarketExtentions.valueOf(extensionClue).getSpecificMarketExtension();
	}
	
	/**
	 * Gets the extension clue.
	 * 
	 * @param extension the extension
	 * 
	 * @return the extension clue
	 */
	public static String getExtensionClue(String extension) {
		YahooMarketExtentions eValues[] = YahooMarketExtentions.values();
		for (int i = 0; i < eValues.length; i++) {
			if (eValues[i].marketExtension.equals(extension))
				return eValues[i].toString();
		}
		return SymbolNameResolver.UNKNOWNEXTENSIONCLUE;
	}

}
