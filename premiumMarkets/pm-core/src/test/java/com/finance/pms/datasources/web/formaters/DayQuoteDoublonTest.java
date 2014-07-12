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
package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteDoublonTest extends DayQuoteFormater {
	
	
	public DayQuoteDoublonTest(MyUrl url,Stock  stock, String currency) {
		super(url,stock,currency);
	}
	
	@Override
	protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseException {
		
		String[] strt = line.split(",");
		
		//Wed Aug 20 00:00:00 BST 2003
		SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

		
		//[01/02/2009 01:23:14:288] TRACE: com.finance.pms.datasources.db.DataSource.trace : Parameters : ,
		//Tue Mar 08 00:00:00 GMT 2005,30.2,30.2,30.05,30.05,9400,EUR,AFO.PA,FR0000044612
		
//		"INSERT INTO " + QUOTATIONS.TABLE_NAME + " ( "+ QUOTATIONS.DATE_FIELD + " , "
//		+ QUOTATIONS.DAY_OPEN_FIELD + " , " + QUOTATIONS.DAY_HIGH_FIELD + " , " + QUOTATIONS.DAY_LOW_FIELD + " , "
//		+ QUOTATIONS.DAY_CLOSE_FIELD + " , " + QUOTATIONS.DAY_VOLUME_FIELD + " , " + QUOTATIONS.CURRENCY_FIELD + " , "
//		+ QUOTATIONS.SYMBOL_FIELD + " , " + QUOTATIONS.ISIN_FIELD + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		LinkedList<Comparable<?>> strtL = new LinkedList<Comparable<?>>();
		int i =0;
		for (String s : strt) {
			
			if (s != null) {
				switch (i) {
				case 1:
					try {
						strtL.add(df1.parse(s));
					} catch (Exception e) {
						throw new RuntimeException("Date Format ERROR while parsing yahoo values :" + e);
					}
					break;
				case 2:
					strtL.add(new BigDecimal(s));
					break;
				case 3:
					strtL.add(new BigDecimal(s));
					break;
				case 4:
					strtL.add(new BigDecimal(s));
					break;
				case 5:
					strtL.add(new BigDecimal(s));
					break;
				case 6 :
					strtL.add(new Long(s));
					break;
				default:
					//strtL.add(s);
				}
				
			} 
			i++;
		}
		return strtL;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}
}
