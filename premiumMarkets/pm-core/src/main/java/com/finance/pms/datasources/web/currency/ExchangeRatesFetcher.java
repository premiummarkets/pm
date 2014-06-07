package com.finance.pms.datasources.web.currency;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.shares.Currency;

public interface ExchangeRatesFetcher {

	public abstract List<CurrencyRate> getRatesForPeriod(final Currency fromCurrency, final Currency toCurrency, Date start, Date end) throws HttpException, InterruptedException;

}
