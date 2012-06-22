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
package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteGoogleFormater extends DayQuoteFormater {
	
	public DayQuoteGoogleFormater(MyUrl url,Stock  stock, String currency) {
		super(url,stock,currency);
	}
	
	//specific
	@Override 
	protected LinkedList<Comparable<?>> mainQuery(String line) {
		//Date,Open,High,Low,Close,Volume
		//3-Oct-08,5.59,5.59,5.06,5.07,196420
		
		String[] strt = line.split(",");
		DateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
		LinkedList<Comparable<?>> tokenisedLine = new LinkedList<Comparable<?>>();
		for (int i =0; i < strt.length; i++) {
			String field = strt[i];
			if (field != null) {
				switch (i) {
					case 0:
						try {
							tokenisedLine.add(df1.parse(field));
						} catch (ParseException e) {
							throw new RuntimeException("Date Format ERROR while parsing google values :" + e);
						}
						break;
					case 1:
					case 2:
					case 3:
					case 4:
						tokenisedLine.add(new BigDecimal(field));
						break;
					case 5 :
						tokenisedLine.add(new Long(field));	
						break;
					default:
						tokenisedLine.add(field);
				}
			} else {
				tokenisedLine.add(field);
			}
		}
		return tokenisedLine;
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return true;
	}
	
	
}
