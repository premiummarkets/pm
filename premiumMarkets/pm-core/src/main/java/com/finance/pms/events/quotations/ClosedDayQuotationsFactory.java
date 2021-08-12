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

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;

public class ClosedDayQuotationsFactory implements QuotationsFactory {

	@Override
	public Quotations getQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, ValidityFilter validityFilter) throws NoQuotationsException {
		return new Quotations(stock, firstDate, lastDate, keepCache, targetCurrency, Math.max(1, firstIndexShift), ValidityFilter.SPLITFREE, validityFilter);
	}

	@Override
	public Quotations getRawQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, ValidityFilter validityFilter) throws NoQuotationsException {
		return new Quotations(stock, firstDate, lastDate, keepCache, targetCurrency, Math.max(1, firstIndexShift), validityFilter);
	}

	@Override
	public Quotations getQuotationsInstance(Stock stock, Date endDate, Boolean keepCache, Currency targetCurrency, ValidityFilter validityFilter) throws NoQuotationsException {
		return new Quotations(stock, endDate, endDate, keepCache, targetCurrency, 1, ValidityFilter.SPLITFREE, validityFilter);
	}

	public  Calendar incrementDate(Calendar calendar, int amount) {
		calendar.add(Calendar.DAY_OF_YEAR, noGapsAmount(amount));
		fillEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}

	private int noGapsAmount(int amount) {
		int nbWeeksBaseOnOpenDays = amount / 5;
		int addedWeekends = 2 * nbWeeksBaseOnOpenDays;
		return amount + addedWeekends;
	}


	public  Calendar incrementDateLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.MONTH, noGapsAmount(amount));
		fillEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}


	public  Calendar incrementDateExtraLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.YEAR, noGapsAmount(amount));
		fillEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}


	public  int largeIncrementGap(Calendar firstDate, Calendar secondDate) {
		return ( (secondDate.get(Calendar.MONTH) + secondDate.get(Calendar.YEAR)*12) - (firstDate.get(Calendar.MONTH) + firstDate.get(Calendar.YEAR)*12) );
	}


	public  Date shiftToOpeningTime(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}


	public  Date shiftToClosingTime(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	private  void fillEdgeGap(Calendar calendar, double amountSign) {

		if (amountSign > 0) {
			calendar.setTime(getValidQuotationDateAfter(calendar.getTime()));
		} else {
			calendar.setTime(getValidQuotationDateBefore(calendar.getTime()));
		}
	}


	public  Date getValidQuotationDateAfter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (Calendar.SATURDAY != day && Calendar.SUNDAY != day) {
			return date;
		} 

		if (day == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		} else 
			if (day == Calendar.SUNDAY) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			}

		return calendar.getTime();
	}


	public  Date getValidQuotationDateBefore(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (Calendar.SATURDAY != day && Calendar.SUNDAY != day) {
			return date;
		} 

		if (day == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else 
			if (day == Calendar.SUNDAY) {
				calendar.add(Calendar.DAY_OF_YEAR, -2);
			}

		return calendar.getTime();
	}

	public Date getFirstQuotationDateFromQuotations(Stock stock) {
		return DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
	}

	//If firstDate > secondDate, a negative value is returned
	public int nbOpenIncrementBetween(Date firstDate, Date secondDate) {
		long im = secondDate.getTime() - firstDate.getTime();
		long id = im / DateFactory.DAYINMILLI;
		if (-1 < id && id < 1) return 0;
		if (-2 < id && id < 2) return (int) (1*Math.signum(id));

		double wf = 5d/7d;
		return (int) (id * wf);
	}


	public int minutesIncrement() {
		return 24*60;
	}


	public Boolean isInOpenHours(Date lastDate) {
		throw new NotImplementedException();
	}

	/**
	 * This using the binary search to retrieve quotations will fill in the missing gaps of quotations
	 * This hence is not the quotations as stored in DB. For the actual stored quotations, use {@link #buildExactSMapFromQuotations(Quotations, QuotationDataType, int, int)}
	 */
	@Override
	public SortedMap<Date, double[]> buildMapFromQuotationsClose(Quotations quotations) throws NotEnoughDataException {

		try {
			SortedMap<Date, double[]> fullRefSQuotationsMap = new TreeMap<Date, double[]>();
			Date firstRefStockQuote = quotations.getDate(0);
			Calendar current = Calendar.getInstance();
			current.setTime(firstRefStockQuote);
			Date lastRefStockQuote = quotations.getDate(quotations.size()-1);
			while (current.getTime().before(lastRefStockQuote) || current.getTime().compareTo(lastRefStockQuote) == 0) {
				fullRefSQuotationsMap.put(current.getTime(), new double[] {quotations.getClosestCloseSpForDate(current.getTime()).doubleValue()} );
				QuotationsFactories.getFactory().incrementDate(current, 1);
			}

			return fullRefSQuotationsMap;

		} catch (InvalidAlgorithmParameterException e) {
			throw new NotEnoughDataException(quotations.getStock(), e.getMessage(), e);
		}
	}

	/**
	 * Will return quotations as stored in the DB although potentially altered by a split fix.
	 * @param quotations
	 * @return
	 * @throws NotEnoughDataException
	 */
	@Override
	public SortedMap<Date, double[]> buildExactMapFromQuotationsClose(Quotations quotations) throws NotEnoughDataException {
		return quotations.getQuotationData().stream().collect(
				TreeMap::new,
				(result, qU) -> result.put(qU.getDate(), new double[] {qU.getData(QuotationDataType.CLOSE).doubleValue()}),
				TreeMap::putAll);
	}
	
	@Override
	public SortedMap<Date, double[]> buildExactMapFromQuotations(Quotations quotations, int from, int to, QuotationDataType... quotationDataTypes) throws NotEnoughDataException {
		return quotations.getQuotationData().subList(from, to+1).stream().collect(
				TreeMap::new,
				(result, qU) -> {
					double[] value = Arrays.stream(quotationDataTypes).map(qt -> qU.getData(qt).doubleValue()).mapToDouble(d -> d).toArray();
					result.put(qU.getDate(), value);
				},
				TreeMap::putAll);
	}

	@Override
	public SortedMap<Date, Double> buildExactSMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException {
		SortedMap<Date, Double> fullRefSQuotationsMap = new TreeMap<Date, Double>();
		for (int i = from; i <= to; i++) {
			QuotationUnit quotationUnit = quotations.get(i);
			fullRefSQuotationsMap.put(quotationUnit.getDate(), quotationUnit.getData(field).doubleValue());
		}
		return fullRefSQuotationsMap;
	}

	@Override
	public SortedMap<Date, Number> buildExactBMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException {
		SortedMap<Date, Number> fullRefSQuotationsMap = new TreeMap<Date, Number>();
		for (int i = from; i <= to; i++) {
			QuotationUnit quotationUnit = quotations.get(i);
			fullRefSQuotationsMap.put(quotationUnit.getDate(), quotationUnit.getData(field));
		}
		return fullRefSQuotationsMap;
	}

	@Override
	public SortedMap<Date, Double> buildExactSMapFromQuotationsClose(Quotations quotations, int from, int to) throws NotEnoughDataException {
		SortedMap<Date, Double> fullRefSQuotationsMap = new TreeMap<Date, Double>();
		for (int i = from; i <= to; i++) {
			QuotationUnit quotationUnit = quotations.get(i);
			fullRefSQuotationsMap.put(quotationUnit.getDate(), quotationUnit.getData(QuotationDataType.CLOSE).doubleValue());
		}
		return fullRefSQuotationsMap;
	}

	@Override
	public SortedMap<Date, Double> buildSMapFromQuotationsClose(Quotations quotations, int from, int to) throws NotEnoughDataException {

		try {
			SortedMap<Date, Double> fullRefSQuotationsMap = new TreeMap<Date, Double>();

			Date firstRefStockQuote = quotations.getDate(from);
			Calendar current = Calendar.getInstance();
			current.setTime(firstRefStockQuote);

			Date lastRefStockQuote = quotations.getDate(to);

			while (current.getTime().before(lastRefStockQuote) || current.getTime().compareTo(lastRefStockQuote) == 0) {
				fullRefSQuotationsMap.put(current.getTime(), quotations.getClosestCloseSpForDate(current.getTime()).doubleValue());
				QuotationsFactories.getFactory().incrementDate(current, 1);
			}

			return fullRefSQuotationsMap;

		} catch (InvalidAlgorithmParameterException e) {
			throw new NotEnoughDataException(quotations.getStock(), e.getMessage(), e);
		}
	}

	@Override
	public SortedMap<Date, Double> buildSMapFromQuotations(Quotations quotations, QuotationDataType field, int from, int to) throws NotEnoughDataException {

		try {
			SortedMap<Date, Double> fullRefSQuotationsMap = new TreeMap<Date, Double>();

			Date firstRefStockQuote = quotations.getDate(from);
			Calendar current = Calendar.getInstance();
			current.setTime(firstRefStockQuote);

			Date lastRefStockQuote = quotations.getDate(to);

			while (current.getTime().before(lastRefStockQuote) || current.getTime().compareTo(lastRefStockQuote) == 0) {
				fullRefSQuotationsMap.put(current.getTime(), quotations.getClosestFieldForDate(current.getTime(), field).doubleValue());
				QuotationsFactories.getFactory().incrementDate(current, 1);
			}

			return fullRefSQuotationsMap;

		} catch (InvalidAlgorithmParameterException e) {
			throw new NotEnoughDataException(quotations.getStock(), e.getMessage(), e);
		}
	}

	@Override
	public LastUpdateStampChecker checkLastQuotationUpdateFor() {
		return Quotations.checkLastQuotationUpdateFor();
	}

}
