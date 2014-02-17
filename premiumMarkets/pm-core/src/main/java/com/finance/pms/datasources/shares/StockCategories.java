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


/**
 * The Enum StockCategories.
 * 
 * @author Guillaume Thoreton
 */
public enum StockCategories {
	
	DEFAULT_CATEGORY ("ALL","TOUT_ACT"),
	INDICES_EURONEXT ("INDICES","1007"),
	SICAV ("SICAV","TOUT_ACT"),
	TRACKERS ("TRACKERS","2210"),
	INDICES_OTHER ("INDICES_Y",""), 
	CURRENCY_RATE ("CurrencyRate","");

	private String category;
	private String boursoramaUrlCatHint;
	
	private StockCategories(String category,String boursoramaUrlMarche) {
		this.category = category;
		this.boursoramaUrlCatHint = boursoramaUrlMarche;
	}

	public String getCategory() {
		return category;
	}

	public static String[] stringValues() {
		String[] retour = new String[StockCategories.values().length];
		for (int i=0; i < StockCategories.values().length; i++) {
			retour[i] = StockCategories.values()[i].getCategory();
		}
		return retour;	
	}

	public static StockCategories valueOfString(String st) {
		StockCategories eValues[] = StockCategories.values();
		for (int i=0; i < eValues.length; i++) {
			if (eValues[i].category.equals(st)) return eValues[i];
		}
		throw new IllegalArgumentException(
	            "No enum const StockCategories." + st);
	}

	public String getBoursoramaUrlCatHint() {
		return boursoramaUrlCatHint;
	}

	public void setBoursoramaUrlCatHint(String boursoramaUrlMarche) {
		this.boursoramaUrlCatHint = boursoramaUrlMarche;
	}
	
	
}
