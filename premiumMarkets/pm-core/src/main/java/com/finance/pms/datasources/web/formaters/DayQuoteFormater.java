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
package com.finance.pms.datasources.web.formaters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public abstract class DayQuoteFormater extends LineFormater {
	
	protected Currency currency; 

	public DayQuoteFormater(MyUrl url, Stock  stock, String currency) {
		super(url);
		params.add(stock);
		params.add(currency);
		
		this.currency = Currency.valueOf(currency);
	}
	
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		LinkedList<Comparable<?>> mainQuery = mainQuery(line);
		List<Validatable> ret = new ArrayList<Validatable>();
		ret.add(new DailyQuotation(mainQuery,(Stock) params.get(0), (String) params.get(1)));
		
		return ret;
	}
	
	protected abstract  LinkedList<Comparable<?>> mainQuery(String line)  throws StopParseException ;

}
