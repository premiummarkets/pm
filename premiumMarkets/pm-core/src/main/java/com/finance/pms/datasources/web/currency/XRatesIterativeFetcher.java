package com.finance.pms.datasources.web.currency;

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

public class XRatesIterativeFetcher implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(XRatesIterativeFetcher.class);
	
	private HttpSourceExchange httpSource;
	
	public XRatesIterativeFetcher(HttpSourceExchange httpSource) {
		super();
		this.httpSource = httpSource;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CurrencyRate> getRatesForPeriod(final Currency fromCurrency, final Currency toCurrency, final Date start, final Date end) throws HttpException, InterruptedException {

		@SuppressWarnings("rawtypes")
		final List rates = new ArrayList<CurrencyRate>();

		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					
					Calendar currentCal = Calendar.getInstance();
					currentCal.setTime(DateFactory.midnithDate(start));
					Date midnightEnd = DateFactory.midnithDate(end);
					
					while (currentCal.getTime().before(midnightEnd) || currentCal.getTime().equals(midnightEnd)) {
						String xRatesHistoryUrl = httpSource.getXRatesHistoryUrl(currentCal.getTime());
						LOGGER.info("Fetching exchange rates : "+xRatesHistoryUrl);
						List<Validatable> readURL = httpSource.readURL(new CurrencyXRatesDailyFormater(fromCurrency, toCurrency, currentCal.getTime(), xRatesHistoryUrl));
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

		thread.join();
		return rates;
	}

}
