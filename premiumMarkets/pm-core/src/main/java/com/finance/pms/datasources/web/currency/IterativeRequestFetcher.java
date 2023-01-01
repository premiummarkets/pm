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
package com.finance.pms.datasources.web.currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.formaters.CurrencyXRatesDailyFormater;
import com.finance.pms.events.calculation.DateFactory;

public class IterativeRequestFetcher extends Observable implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(IterativeRequestFetcher.class);
	
	private HttpSourceExchange httpSource;
	
	public IterativeRequestFetcher(HttpSourceExchange httpSource) {
		this.httpSource = httpSource;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CurrencyRate> getRatesForPeriod(final Currency fromCurrency, final Currency toCurrency, final Date start, final Date end) throws HttpException, InterruptedException {

		@SuppressWarnings("rawtypes")
		final List rates = new ArrayList<CurrencyRate>();
		Calendar startDateCalendar = Calendar.getInstance();
		
		Date firstAvailable = null;
		try {
			firstAvailable = new SimpleDateFormat("yyyyMMdd").parse("20040101");
		} catch (ParseException e1) {
			LOGGER.error(e1,e1);
		}
		if (start.after(firstAvailable)) startDateCalendar.setTime(start); else startDateCalendar.setTime(firstAvailable);
		
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(end);
		
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					
					Calendar currentCal = Calendar.getInstance();
					currentCal.setTime(DateFactory.midnithDate(start));
					Date midnightEnd = DateFactory.midnithDate(end);
					
					while (currentCal.getTime().before(midnightEnd) || currentCal.getTime().equals(midnightEnd)) {
						LOGGER.guiInfo("Fetching exchange rates: " + httpSource.getXRatesHistoryUrl(currentCal.getTime()));
						List<Validatable> readURL = httpSource.readURL(new CurrencyXRatesDailyFormater(fromCurrency, toCurrency, currentCal.getTime(), httpSource.getXRatesHistoryUrl(currentCal.getTime())));
						LOGGER.guiInfo("Found: " + readURL);
						rates.addAll(readURL);
						//QuotationsFactories.getFactory().incrementDate(currentCal, 1);
						currentCal.setTime(DateUtils.addDays(currentCal.getTime(), 1));
					}
					
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {
					
				}
			}
		});
	
		thread.start();
		
//		int lastDayOfStartDate = startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//		if (startDateCalendar.get(Calendar.DAY_OF_MONTH) == lastDayOfStartDate) {
//			startDateCalendar.add(Calendar.MONTH, 1);
//			startDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
//		} else {
//			startDateCalendar.set(Calendar.DAY_OF_MONTH, lastDayOfStartDate);
//		}
//
//		if (startDateCalendar.before(endDateCalendar)) {
//			rates.addAll(getRatesForPeriod(fromCurrency, toCurrency, startDateCalendar.getTime(), end));
//		} 
		
		thread.join();
		return rates;
	}

}
