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
import com.finance.pms.datasources.web.formaters.CurrencyOandaHistoryFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO refactor Iterative currency fetch s
public class OandaIterativeFetcher implements ExchangeRatesFetcher {
	
	private final MyLogger LOGGER = MyLogger.getLogger(OandaIterativeFetcher.class);
	
	private static final int DELTA_INC = 500*5/7 - 100;
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
						LOGGER.debug("Found : "+readURL);
						rates.addAll(readURL);
						
						QuotationsFactories.getFactory().incrementDate(currentCal, 1);
					}
					
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {
					
				}
			}

		});
	
//		thread.start();
//		thread.join();
		
		thread.run();
		return rates;
	}
	
	private Calendar incrementDate(Calendar currentCal, Date midnightEnd, int deltaInc) {
		QuotationsFactories.getFactory().incrementDate(currentCal, deltaInc);
		if (currentCal.getTime().after(midnightEnd)) currentCal.setTime(midnightEnd);
		return currentCal;
	}

}
