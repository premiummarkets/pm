package com.finance.pms.datasources.web.currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.datasources.web.formaters.CurrencyEuropeanCentralBankFormater;

public class EuropeanCentralBankFetcher implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(EuropeanCentralBankFetcher.class);
	
	private HttpSourceExchange httpSource;

	protected EuropeanCentralBankFetcher(HttpSourceExchange httpSource) {
		super();
		this.httpSource = httpSource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CurrencyRate> getRatesForPeriod(Currency fromCurrency, Currency toCurrency, Date start, Date end) throws HttpException, InterruptedException {
		
		@SuppressWarnings("rawtypes")
		final List rates = new ArrayList<CurrencyRate>();
		Calendar startDateCalendar = Calendar.getInstance();
		startDateCalendar.setTime(start);
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(end);

		final String url = httpSource.getEuropeanCentralBankUrl();
		LOGGER.debug("Url : "+url);
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					rates.addAll(httpSource.readURL(new CurrencyEuropeanCentralBankFormater(fromCurrency, toCurrency, new MyUrl(url))));
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {
					
				}
			}
		});
	
		thread.start();
		
//		//??
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
//		//??
		
		thread.join();
		return rates;
	}

}
