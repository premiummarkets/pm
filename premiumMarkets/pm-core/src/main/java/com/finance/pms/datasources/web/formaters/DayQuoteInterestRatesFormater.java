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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.calculation.DateFactory;

public class DayQuoteInterestRatesFormater extends LineFormater {
	
	private List<Validatable> validatables;
	
	private Date startDate;

	public DayQuoteInterestRatesFormater(MyUrl url, Stock stock, Date startDate) {
		super(url);
		params.add(stock);
		params.add(Currency.NAN.name());
	
		this.startDate = startDate;
		
		this.validatables = new ArrayList<Validatable>();
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {

		LOGGER.trace(line);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] lineSplit = line.split(",");
			Date lineDate = sdf.parse(lineSplit[0]);
			if (lineDate.compareTo(startDate) >= 0) {
				LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
				mainQuery.add(DateFactory.midnithDate(lineDate));
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(new BigDecimal(lineSplit[1]));
				mainQuery.add(Long.valueOf(0));
				mainQuery.add(BigDecimal.ONE); //Split
				validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
			}
		} catch (ParseException e) {
			LOGGER.warn("Can't read line: " + line + ": " + e);
		}

		return validatables;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}
	
	
}
