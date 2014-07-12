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
package com.finance.pms.alerts;

public enum AlertOnThresholdType {

	AVG_BUY_PRICE ("Average cost per unit reached"), //Should be updated when avg price changes
	ABOVE_PRICE_CHANNEL ("Above channel threshold reached"), //Updated when triggered
	BELOW_PRICE_CHANNEL ("Below channel threshold reached"), //Updated when triggered
	ABOVE_TAKE_PROFIT_LIMIT ("Above take profit threshold limit"), //Should be updated when avg price changes and stays when triggered
	BELOW_ZERO_WEIGHTED_PROFIT_LIMIT ("Below take 0% weighted profit limit"), //Should be updated when inflation changes and swap up/down when triggered -
	MANUALUP ("User threshold up reached"),
	MANUALDOWN ("User threshold down reached");
	
	String text;

	private AlertOnThresholdType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}	
}
