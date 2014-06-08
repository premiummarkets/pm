package com.finance.pms.datasources.web.currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.formaters.CurrencyXRatesDailyFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationsFactories;

public class IterativeRequestFetcher implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(IterativeRequestFetcher.class);
	
	private HttpSourceExchange httpSource;
	
	public IterativeRequestFetcher(HttpSourceExchange httpSource) {
		super();
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
						LOGGER.info("Fetching exchange rates : "+httpSource.getXRatesHistoryUrl(currentCal.getTime()));
						List<Validatable> readURL = httpSource.readURL(new CurrencyXRatesDailyFormater(fromCurrency, toCurrency, currentCal.getTime(), httpSource.getXRatesHistoryUrl(currentCal.getTime())));
						LOGGER.info("Found : "+readURL);
						rates.addAll(readURL);
						QuotationsFactories.getFactory().incrementDate(currentCal, 1);
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
