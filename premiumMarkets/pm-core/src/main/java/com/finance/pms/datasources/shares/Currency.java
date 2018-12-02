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

import java.io.Serializable;

public enum Currency  implements Serializable {

	NAN("NAN", "NAN"), EUR("Euro", "Euro"), USD("U.S. Dollar", "US Dollar"), AUD("Australian Dollar", "Australian Dollar"), GBP("U.K. Pound Sterling", "British Pound"), INR("Indian Rupee", "Indian Rupee"), CAD("Canadian Dollar", "Canadian Dollar"), 
	TWD("New Taiwan Dollar", "Taiwan New Dollar"), NZD("New Zealand Dollar", "New Zealand Dollar"), ILS("Israeli New Sheqel", "Israeli Shekel"), HKD("Hong Kong Dollar", "Hong Kong Dollar"), KRW("South Korean Won", "South Korean Won"), SGD("Singapore Dollar", "Singapore Dollar"), IDR("Indonesian Rupiah", "Indonesian Rupiah"),
	CHF("Swiss Franc", "Swiss Franc"),
	KES("Kenyan Shilling","Kenyan Shilling");

	private String imfCurrencyName;
	private String xRateCurrencyName;

	private Currency(String imfCurrencyName, String xRateCurrencyName) {
		this.imfCurrencyName = imfCurrencyName;
		this.xRateCurrencyName = xRateCurrencyName;
	}

	public String getImfCurrencyName() {
		return imfCurrencyName;
	}

	public String getxRateCurrencyName() {
		return xRateCurrencyName;
	}



}
