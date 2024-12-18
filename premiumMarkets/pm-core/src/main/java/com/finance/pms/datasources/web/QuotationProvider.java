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
package com.finance.pms.datasources.web;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpException;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Stock;

public interface QuotationProvider {
	
	/**
	 * End is the inferred last quote at the time of running.
	 * We expect [start, end] (end inclusive)
	 * @param stock
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public void getQuotes(Stock stock, Date start, Date end) throws Exception;
	
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception;

	public List<Validatable> readPage(Stock stock, MyUrl url, Date startDate) throws HttpException;


	public default List<ValidatableDated> filterToEndDateInclusive(Date endDate, Collection<? extends ValidatableDated> dailyQuotations) {
		List<ValidatableDated> ohlcvValids = dailyQuotations.stream().filter(ohlcv -> !ohlcv.getDate().after(endDate)).collect(Collectors.toList());
		return ohlcvValids;
	}
	

}
