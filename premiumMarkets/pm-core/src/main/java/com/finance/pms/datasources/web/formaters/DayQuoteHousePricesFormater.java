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
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;

public class DayQuoteHousePricesFormater extends LineFormater {
	
	private List<Validatable> validatables;
	
	private Date startDate;

	public DayQuoteHousePricesFormater(MyUrl url, Stock stock, Date startDate) {
		super(url);
		params.add(stock);
		params.add(Currency.NAN.name());
	
		this.startDate = startDate;
		
		this.validatables = new ArrayList<Validatable>();
		
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		//Name,URI,"Region GSS code",Period,"Sales volume","Reporting period","Average price All property types","Percentage change (yearly) All property types","Percentage change (monthly) All property types",
		//"House price index All property types","Sales volume All property types","Average price Detached houses","Percentage change (yearly) Detached houses","Percentage change (monthly) Detached houses","House price index Detached houses","Average price Semi-detached houses","Percentage change (yearly) Semi-detached houses","Percentage change (monthly) Semi-detached houses","House price index Semi-detached houses","Average price Terraced houses","Percentage change (yearly) Terraced houses","Percentage change (monthly) Terraced houses","House price index Terraced houses","Average price Flats and maisonettes","Percentage change (yearly) Flats and maisonettes","Percentage change (monthly) Flats and maisonettes","House price index Flats and maisonettes",
		//"Average price First-time buyers","Percentage change (yearly) First-time buyers","Percentage change (monthly) First-time buyers","House price index First-time buyers","Average price Former owner-occupiers","Percentage change (yearly) Former owner-occupiers","Percentage change (monthly) Former owner-occupiers","House price index Former owner-occupiers",
		//"Average price Cash purchases","Percentage change (yearly) Cash purchases","Percentage change (monthly) Cash purchases","House price index Cash purchases","Sales volume Cash purchases","Average price Mortgage purchases","Percentage change (yearly) Mortgage purchases","Percentage change (monthly) Mortgage purchases","House price index Mortgage purchases","Sales volume Mortgage purchases",
		//"Average price New build","Percentage change (yearly) New build","Percentage change (monthly) New build","House price index New build","Sales volume New build","Average price Existing properties","Percentage change (yearly) Existing properties","Percentage change (monthly) Existing properties","House price index Existing properties","Sales volume Existing properties",
		//"Pivotable date"
		LOGGER.trace(line);
		int periodColumn = 3;
		int salesVolumesIdx = 4;
		int avgPrice = 6;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String[] lineSplit = line.split(",");
			Date lineDate = sdf.parse(lineSplit[periodColumn]);
			if (lineDate.compareTo(startDate) >= 0) {
				LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
				mainQuery.add(DateFactory.midnithDate(lineDate));
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(BigDecimal.ZERO);
				mainQuery.add(new BigDecimal(lineSplit[avgPrice]));
				Long volume;
				try {
					volume = Long.valueOf(lineSplit[salesVolumesIdx]);
				} catch (NumberFormatException e) {
					LOGGER.warn("No volume found in " + line);
					volume = 0l;
				}
				mainQuery.add(volume);
				mainQuery.add(BigDecimal.ONE); //Split
				mainQuery.add(ORIGIN.WEB.ordinal());
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
