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
import java.util.StringTokenizer;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;



/**
 *TODO  include adjusted price
 * 
 * @author Guillaume Thoreton
 */
public class DayQuoteYahooCrumbFormater extends DayQuoteFormater {


    public DayQuoteYahooCrumbFormater(MyUrl url,Stock  stock, String currency) {
        super(url,stock,currency);
    }


    protected LinkedList<Comparable<?>> mainQuery(String line) throws StopParseErrorException {
        StringTokenizer strt = new StringTokenizer(line, ",");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        assert strt.countTokens() == 7;

        LinkedList<Comparable<?>> tokenisedLine = new LinkedList<Comparable<?>>();
        loop :
            for (int columnNum = 0; strt.hasMoreTokens() && columnNum < 7; columnNum++) {
                String field = strt.nextToken();
                if (field != null) {

                    switch (columnNum) {
                    case 0:
                        if (field.equals("Date")) {	break loop; }
                        try {
                            tokenisedLine.add(df1.parse(field));
                        } catch (Exception e) {
                            throw new StopParseErrorException("Date Format error while parsing yahoo quotations : "+params.get(0), e.getMessage());
                        }
                        break;
                    case 1: //Open
                    case 2: //High
                    case 3: //Low
                    case 4: //Close
                    //case 5: //TODO Adj close price
                        tokenisedLine.add(new BigDecimal(field));
                        break;
                    case 6: //Volume
                        tokenisedLine.add(new Long(field));	
                        break;
                    default:
                        //tokenisedLine.add(field);
                    }

                } else {
                    tokenisedLine.add(field);
                }
            }
        return tokenisedLine;
    }


    @Override
    public Boolean canHaveNoResultsFound() {
        return true;
    }
}
