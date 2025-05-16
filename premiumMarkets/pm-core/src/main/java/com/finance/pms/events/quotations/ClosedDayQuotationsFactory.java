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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.Quotations.SplitOption;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;

public class ClosedDayQuotationsFactory implements QuotationsFactory {

	@Override
	public Quotations getSplitFreeQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, ValidityFilter validityFilter) throws NoQuotationsException {
		return new Quotations(stock, firstDate, lastDate, keepCache, targetCurrency, Math.max(0, firstIndexShift), SplitOption.SPLITFREE, validityFilter);
	}

	@Override
	public Quotations getRawQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, SplitOption splitOption, ValidityFilter... otherFilters) throws NoQuotationsException {
		return new Quotations(stock, firstDate, lastDate, keepCache, targetCurrency, Math.max(0, firstIndexShift), splitOption, otherFilters);
	}

	@Override
	public Quotations getBoundSafeEndDateQuotationsInstance(Stock stock, Date endDate, Boolean keepCache, Currency targetCurrency, ValidityFilter validityFilter) throws NoQuotationsException {
		return new Quotations(stock, endDate, endDate, keepCache, targetCurrency, 1, SplitOption.SPLITFREE, validityFilter);
	}
	
	@Override
	public int nbDataPointsBetweenFor(Stock stock, Date firstDate, Date secondDate, ValidityFilter validityFilter) throws NoQuotationsException, NotEnoughDataException {
		Quotations quotations = new Quotations(stock, firstDate, secondDate, true, null, 1, SplitOption.SPLITFREE, validityFilter);
		Integer firstIndex = quotations.getQuotationData().getClosestIndexBeforeOrAtDate(0, firstDate);
		if (firstIndex == -1) firstIndex = 0;
		Integer sndIndex = quotations.getQuotationData().getClosestIndexBeforeOrAtDate(0, secondDate);
		if (sndIndex == -1) 
			throw new NotEnoughDataException(stock, firstDate, secondDate, "Invalid dates, outside the quotation range.", null);
		return sndIndex - firstIndex;
	}
	/**
	 * Based on the quotations in DB for data points to date conversions
	 */
	public Calendar incrementDate(Stock stock, Collection<QuotationDataType> dataTypes, Calendar from, int amount) throws NotEnoughDataException {

		Date firstDate;
		Date lastDate;
		if (amount == 0) {
			return from;
		} else if (amount > 0) {
			firstDate = from.getTime();
			lastDate = DateFactory.getNowEndDate();
		} else { //amount < 0
			firstDate = DateFactory.dateAt1900();
			lastDate = from.getTime();
		}
		try {
			Quotations quotations = QuotationsFactories.getFactory()
										.getSplitFreeQuotationsInstance(stock, firstDate, lastDate, true, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.getFilterFor(dataTypes));
			Date[] quotationsKeySet = quotations.getDates();
			
			Date incrementedDate = null;
			if (amount > 0) {
				int index = Math.min(quotationsKeySet.length-1, amount);
				incrementedDate = quotationsKeySet[index];
			} else { //amount < 0
				int index = Math.max(0, quotationsKeySet.length + amount);
				incrementedDate = quotationsKeySet[index];
			}
			
			from.setTime(incrementedDate);
			return from;
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(stock, e.getMessage(), e);
		}
	}
	
	/**
	 * @deprecated : use incrementDate instead which is based on the quotations in DB for data points to date conversions
	 * amount * 7/5
	 * @param amount
	 * @return
	 */
	@Deprecated
	private int noGapsAmount(int amount) {
//		int nbWeeksBaseOnOpenDays = amount / 5;
//		int addedWeekends = 2 * nbWeeksBaseOnOpenDays;
		return  amount * 7/5;
	}

	
	@Deprecated //FIXME use incrementDate instead
	public Calendar incrementDateLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.MONTH, noGapsAmount(amount));
		fillEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}

	@Deprecated //FIXME use incrementDate instead
	public Calendar incrementDateExtraLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.YEAR, noGapsAmount(amount));
		fillEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}


	public int largeIncrementGap(Calendar firstDate, Calendar secondDate) {
		return ( (secondDate.get(Calendar.MONTH) + secondDate.get(Calendar.YEAR)*12) - (firstDate.get(Calendar.MONTH) + firstDate.get(Calendar.YEAR)*12) );
	}


	public Date shiftToOpeningTime(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}


	public Date shiftToClosingTime(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	private void fillEdgeGap(Calendar calendar, double amountSign) {
		if (amountSign > 0) {
			calendar.setTime(getValidQuotingDateAfterOrAt(calendar.getTime()));
		} else {
			calendar.setTime(getValidQuotingDateBeforeOrAt(calendar.getTime()));
		}
	}


	public  Date getValidQuotingDateAfterOrAt(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		if (Calendar.SATURDAY != day && Calendar.SUNDAY != day) {
			return date;
		} 
		
		if (day == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		} else if (day == Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return calendar.getTime();
	}


	public  Date getValidQuotingDateBeforeOrAt(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		
		if (Calendar.SATURDAY != day && Calendar.SUNDAY != day) {
			return date;
		} 

		if (day == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else if (day == Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, -2);
		}

		return calendar.getTime();
	}

	public Date getFirstQuotationDateFromQuotations(Stock stock) {
		return DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
	}

	/**
	 * OpenIncrement == dataPoints
	 * If firstDate > secondDate, a negative value is returned
	 */
	public int nbOpenIncrementBetween(Double dataPointFactor, Date firstDate, Date secondDate) {
		long intervalMilli = secondDate.getTime() - firstDate.getTime();
		long intervalDays = intervalMilli / DateFactory.DAYINMILLI;
		if (-1 < intervalDays && intervalDays < 1) return 0; //first date is less then 1 day after or before second date
		if (-2 < intervalDays && intervalDays < 2) return (int) (1*Math.signum(intervalDays)); //first date is less then 2 days after or before second date

		double weekendsFactor = 1/dataPointFactor;
		return (int) (intervalDays * weekendsFactor);
	}


	public int minutesIncrement() {
		return 24*60;
	}


	public Boolean isInOpenHours(Date lastDate) {
		throw new NotImplementedException();
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
	public SortedMap<Date, double[]> buildExactMapFromQuotations(Quotations quotations, int from, int to, QuotationDataType... quotationDataTypes) {
		return quotations.getQuotationData().subList(from, to + 1).stream().collect(
				TreeMap::new,
				(result, qU) -> {
					double[] value = Arrays.stream(quotationDataTypes).map(qt -> qU.getData(qt).doubleValue()).mapToDouble(d -> d).toArray();
					result.put(qU.getDate(), value);
				},
				TreeMap::putAll);
	}
	
	@Override
	public SortedMap<Date, double[]> buildExactMapFromQuotationsOHLCV(Quotations quotations) {
		return quotations.getQuotationData().stream().collect(
				TreeMap::new,
				(result, qU) -> result.put(qU.getDate(), new double[] {
						qU.getData(QuotationDataType.OPEN).doubleValue(),
						qU.getData(QuotationDataType.HIGH).doubleValue(),
						qU.getData(QuotationDataType.LOW).doubleValue(),
						qU.getData(QuotationDataType.CLOSE).doubleValue(),
						qU.getData(QuotationDataType.VOLUME).doubleValue()
						}),
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
	public LastUpdateStampChecker checkLastQuotationUpdateFor() {
		return Quotations.checkLastQuotationUpdateFor();
	}

}
