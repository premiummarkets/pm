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
package com.finance.pms.events.quotations;

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;

public interface QuotationsFactory {

	public abstract Date getValidQuotationDateBefore(Date date);

	public abstract Date getValidQuotationDateAfter(Date date);

	public abstract Date shiftToOpeningTime(Calendar calendar);
	
	public abstract Date shiftToClosingTime(Calendar calendar);
	
	public abstract int largeIncrementGap(Calendar firstDate, Calendar secondDate);

	public abstract Calendar incrementDateExtraLarge(Calendar calendar, int amount);

	public abstract Calendar incrementDateLarge(Calendar calendar, int amount);

	public abstract Calendar incrementDate(Calendar calendar, int amount);

//	public abstract Quotations getQuotationsInstance(Stock stock, QuotationData quotationData, Currency targetCurrency, ValidityFilter cacheFilter) throws NoQuotationsException;

	public abstract Quotations getQuotationsInstance(Stock stock, Date endDate, Boolean keepCache, Currency targetCurrency,  ValidityFilter cacheFilter) throws NoQuotationsException;

	public abstract Quotations getQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, ValidityFilter cacheFilter) throws NoQuotationsException;

	int nbOpenIncrementBetween(Date firstDate, Date secondDate);

	int minutesIncrement();

	public abstract Boolean isInOpenHours(Date lastDate);

	SortedMap<Date, double[]> buildMapFromQuotationsClose(Quotations quotations) throws NotEnoughDataException;
	
	SortedMap<Date, Double> buildSMapFromQuotationsClose(Quotations quotations, int from, int to) throws NotEnoughDataException;
	SortedMap<Date, Number> buildExactBMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException;

	public abstract LastUpdateStampChecker checkLastQuotationUpdateFor(Validatable stock);

	SortedMap<Date, Double> buildSMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException;

	SortedMap<Date, Double> buildExactSMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException;

		
}
