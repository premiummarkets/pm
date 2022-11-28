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

public enum TradingMode {
	
	UNKNOWN ("Unknown", 7/5),
	CONTINUOUS ("Continuous", 7/5),
	DOUBLE_CALL_AUCTION("Double call auction", 7/5),
	CALL_AUCTION ("Call auction", 7/5),
	NON_STOP("Non Stop", 1);
	
	
	String tradingModeLabel;
	double dataPointsFactor;

	private TradingMode(String tradingModeLabel, double dataPointsFactor) {
		this.tradingModeLabel = tradingModeLabel;
		this.dataPointsFactor = dataPointsFactor;
	}

	public String getTradingModeLabel() {
		return tradingModeLabel;
	}
	
	/**
	 *  data points factor to calendar days
	 */
	public double getDataPointFactor() {
		return dataPointsFactor;
	}
	
	public static TradingMode valueOfLabel(String st) {
		TradingMode eValues[] = TradingMode.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].tradingModeLabel.equals(st)) return eValues[i];
		}
		throw new IllegalArgumentException(
	            "No enum const TradingMode : " + st);
	}
	
	
}
