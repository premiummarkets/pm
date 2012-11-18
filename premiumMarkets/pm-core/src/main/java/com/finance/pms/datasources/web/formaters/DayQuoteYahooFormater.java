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
package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;



// TODO: Auto-generated Javadoc
/**
 * The Class DayQuoteYahooFormater.
 * 
 * @author Guillaume Thoreton
 */
public class DayQuoteYahooFormater extends DayQuoteFormater {
	
	
	/**
	 * Instantiates a new day quote yahoo formater.
	 * 
	 * @param url the url
	 * @param stock the stock
	 * @param transactionCurrency the transactionCurrency
	 * 
	 * @author Guillaume Thoreton
	 */
	public DayQuoteYahooFormater(MyUrl url,Stock  stock, String currency) {
		super(url,stock,currency);
	}

	
	/**
	 * Main query.
	 * 
	 * @param line the line
	 * 
	 * @return the list< comparable>
	 * 
	 * @author Guillaume Thoreton
	 * @throws StopParseErrorException 
	 */
	protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseErrorException {
		StringTokenizer strt = new StringTokenizer(line, ",");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		assert strt.countTokens() == 7;

		LinkedList<Comparable<?>> tokenisedLine = new LinkedList<Comparable<?>>();
		loop :
			for (int i =1;strt.hasMoreTokens() && i <=	6; i++) {
				String field = strt.nextToken();
				if (field != null) {

					switch (i) {
					case 1:
						if (field.equals("Date")) {	break loop; }
						try {
							tokenisedLine.add(df1.parse(field));
						} catch (Exception e) {
							throw new StopParseErrorException("Date Format error while parsing yahoo quotations : "+params.get(0), e.getMessage());
						}
						break;
					case 2:
					case 3:
					case 4:
					case 5:
						tokenisedLine.add(new BigDecimal(field));
						break;
					case 6 :
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
