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
package com.finance.pms.datasources.web.formaters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;



// TODO: Auto-generated Javadoc
/**
 * The Class DayQuoteBoursoramaFormater.
 * 
 * @author Guillaume Thoreton
 */
public class DayQuoteBoursoramaFormater extends DayQuoteFormater {

	
	/**
	 * Instantiates a new day quote boursorama formater.
	 * 
	 * @param url the url
	 * @param stock the stock
	 * @param transactionCurrency the transactionCurrency
	 * 
	 * @author Guillaume Thoreton
	 */
	public DayQuoteBoursoramaFormater(MyUrl url, Stock  stock, String currency) {
		super(url,stock,currency);
	}

	
    /**
     * Main query.
     * 
     * @param line the line
     * 
     * @return the list< comparable>
     * 
     * @throws StopParseException the stop parse exception
     * 
     * @author Guillaume Thoreton
     */
	protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseException {
		StringTokenizer tokenisedLine = new StringTokenizer(line, "\t");
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		LinkedList<Comparable<?>> fieldList = new LinkedList<Comparable<?>>();
		
		assert (tokenisedLine.countTokens() == 8 || tokenisedLine.countTokens() == 7) ;
		if (tokenisedLine.countTokens() != 8 && tokenisedLine.countTokens() != 7) {
		    LOGGER.warn("Invalid Page format! This is not the Page I expected! ");
    		throw new StopParseErrorException("Error Parsing Line : Line "+line,"Invalid Page format! The is not the Page I expected!");
		} else {
		    int i;
 		    if (tokenisedLine.countTokens() == 7) {
 		        i=2;
 		    } else {
 		        i=1;
 		    }
			for (; tokenisedLine.hasMoreTokens() && i <= 8; i++) {
				String field = tokenisedLine.nextToken();
				if (field != null) {
					switch (i) {
					case 1:
					case 2:
						break;
					case 3:
						try {
							fieldList.add(df1.parse(field));
						} catch (Exception e) {
							throw new RuntimeException(
									"Date Format ERROR while parsing boursorama values :" + e);
						}
						break;
					case 4:
					case 5:
					case 6:
					case 7:
						fieldList.add(new BigDecimal(field));
						break;
					case 8:
						fieldList.add(new Long(field));
						break;
					default:
						fieldList.add(field);
					}
				} else {
					fieldList.add(field);
				}
			}
		}
		return fieldList;
	}
	
    
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.formaters.LineFormater#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.formaters.LineFormater#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DayQuoteBoursoramaFormater other = (DayQuoteBoursoramaFormater) obj;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}


	@Override
	public Boolean canHaveEmptyResults() {
		return true;
	}
	
}
