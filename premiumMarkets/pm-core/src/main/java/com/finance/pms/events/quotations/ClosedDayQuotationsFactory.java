/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.events.quotations;

import java.security.InvalidAlgorithmParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;

public class ClosedDayQuotationsFactory implements QuotationsFactory {
	
	
	public Quotations getQuotationsInstance(Stock stock, Date firstDate, Date lastDate, Boolean keepCache, Currency targetCurrency, Integer firstIndexShift, Integer lastIndexShift) throws NoQuotationsException {
		return new Quotations(stock, firstDate, lastDate, keepCache, targetCurrency, firstIndexShift, lastIndexShift);
	}
	
	public  Quotations getQuotationsInstance(Stock stock, Date endDate, Boolean keepCache, Currency targetCurrency) throws NoQuotationsException {
		return new Quotations(stock, oneLargeIncrementBefore(endDate), endDate, keepCache, targetCurrency, 0, 0);
	}
	
	public  Quotations getQuotationsInstance(Stock stock, QuotationData quotationData, Currency targetCurrency) throws NoQuotationsException {
		return new Quotations(stock, quotationData, targetCurrency);
	}
	
	public  Calendar incrementDate(Calendar calendar, int amount) {
		calendar.add(Calendar.DAY_OF_YEAR, noGapsAmount(amount));
		closeNoQuotationEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}

	/**
	 * @param amount
	 * @return
	 */
	private int noGapsAmount(int amount) {
		Integer nbGaps = amount / 5;
		return amount + 2*nbGaps;
	}
	
	
	public  Calendar incrementDateLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.MONTH, noGapsAmount(amount));
		closeNoQuotationEdgeGap(calendar, Math.signum(amount));
		return calendar;
	}
	
	
	public  Calendar incrementDateExtraLarge(Calendar calendar, int amount) {
		calendar.add(Calendar.YEAR, noGapsAmount(amount));
		closeNoQuotationEdgeGap(calendar, Math.signum(amount));
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
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * @param calendar
	 */
	private  void closeNoQuotationEdgeGap(Calendar calendar, double amountSign) {
		
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
	
	private  Date oneLargeIncrementBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		this.incrementDateLarge(calendar, -1);
		return calendar.getTime();
	}

	
	public  Date getFirstQuotationDateFromQuotations(Stock stock) {
		return DataSource.getInstance().getFirstQuotationDateFromQuotations(stock);
	}

	
	public int nbOpenIncrementBetween(Date firstDate, Date secondDate) {
		return (int) ((secondDate.getTime() - firstDate.getTime()) / (1000*60*60*24)) * 5/7;
	}

	
	public int minutesIncrement() {
		return 24*60;
	}

	
	public Boolean isInOpenHours(Date lastDate) {
		throw new NotImplementedException();
	}
	
	@Override
	public SortedMap<Date, double[]> buildMapFromQuotations(Quotations quotations) throws NotEnoughDataException {
		
		try {
			SortedMap<Date, double[]> fullRefSQuotationsMap = new TreeMap<Date, double[]>();
			Date firstRefStockQuote = quotations.getDate(0);
			Calendar current = Calendar.getInstance();
			current.setTime(firstRefStockQuote);
			Date lastRefStockQuote = quotations.getDate(quotations.size()-1);
			while (current.getTime().before(lastRefStockQuote) || current.getTime().equals(lastRefStockQuote)) {
				fullRefSQuotationsMap.put(current.getTime(), new double[] {quotations.getCloseForDate(current.getTime()).doubleValue()} );
				QuotationsFactories.getFactory().incrementDate(current, 1);
			}
			
			return fullRefSQuotationsMap;
			
		} catch (InvalidAlgorithmParameterException e) {
			throw new NotEnoughDataException(e.getMessage(), e);
		}
	}

}
