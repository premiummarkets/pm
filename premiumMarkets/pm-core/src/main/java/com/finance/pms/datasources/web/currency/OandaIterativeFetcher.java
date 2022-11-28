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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.formaters.CurrencyOandaHistoryFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO refactor Iterative currency fetch s
public class OandaIterativeFetcher implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(OandaIterativeFetcher.class);
	
	private static final int DELTA_INC = 500*5/7 - 100; //FIXME data points conversion
	private HttpSourceExchange httpSource;
	
	public OandaIterativeFetcher(HttpSourceExchange httpSource) {
		super();
		this.httpSource = httpSource;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CurrencyRate> getRatesForPeriod(final Currency fromCurrency, final Currency toCurrency, final Date start, final Date end) throws InterruptedException {

		@SuppressWarnings("rawtypes")
		final List rates = new ArrayList<CurrencyRate>();

		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					
					Calendar currentCal = Calendar.getInstance();
					currentCal.setTime(DateFactory.midnithDate(start));
					Date midnightEnd = DateFactory.midnithDate(end);
					
					while (currentCal.getTime().before(midnightEnd) || currentCal.getTime().equals(midnightEnd)) {
						
						Date incStart = currentCal.getTime();
						Date incEnd = incrementDate(currentCal, midnightEnd, DELTA_INC).getTime();
						String oandaHistoryUrl = httpSource.getOandaHistoryUrl(fromCurrency, toCurrency, incStart, incEnd);
						LOGGER.info("Fetching exchange rates : "+oandaHistoryUrl);
						
						List<Validatable> readURL = httpSource.readURL(new CurrencyOandaHistoryFormater(fromCurrency, toCurrency, oandaHistoryUrl));
						LOGGER.info("Found : "+readURL.size());
						if (LOGGER.isDebugEnabled()) LOGGER.debug("Found : "+readURL);
						rates.addAll(readURL);
						
						QuotationsFactories.getFactory().incrementDate(currentCal, 1);
					}
					
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {
					
				}
			}

		});
		
		thread.run();
		return rates;
	}
	
	private Calendar incrementDate(Calendar currentCal, Date midnightEnd, int deltaInc) {
		QuotationsFactories.getFactory().incrementDate(currentCal, deltaInc);
		if (currentCal.getTime().after(midnightEnd)) currentCal.setTime(midnightEnd);
		return currentCal;
	}

}
