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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.apache.http.HttpException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyDAO;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.MyBeanFactoryAware;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.LastUpdateStampChecker;
import com.finance.pms.events.quotations.QuotationsFactories;

public class CurrencyConverterImpl implements CurrencyConverter, MyBeanFactoryAware {

	private final MyLogger LOGGER = MyLogger.getLogger(CurrencyConverterImpl.class);

	HttpSourceExchange httpSource;
	private CurrencyDAO currencyDao;	
	private NumberFormat numberFormater = NumberFormat.getNumberInstance();
	private Map<Currency, Map<Currency, List<CurrencyRate>>> cache;

	private Semaphore currencyDBAccessSemaphore;
	private BeanFactory beanFactory;

	public CurrencyConverterImpl(String pathToProps, CurrencyDAO currencyDao) {
		super();
		this.httpSource =  new HttpSourceExchange(pathToProps, this);
		this.numberFormater.setMaximumFractionDigits(2);
		this.cache = new ConcurrentHashMap<Currency, Map<Currency,List<CurrencyRate>>>();
		this.currencyDao=currencyDao;
		this.currencyDBAccessSemaphore = new Semaphore(1);
	}

	private void fetchHistoricalRatesAvailable(Currency fromCurrency, Currency toCurrency) {

		try {
			currencyDBAccessSemaphore.acquire();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

			NavigableSet<CurrencyRate> dbRates = new TreeSet<CurrencyRate>();
			dbRates.addAll(currencyDao.getRates(fromCurrency, toCurrency));

			Date today = DateFactory.midnithDate(new Date());

			Date lastCurrencyRateDate = fetchLastRateDate(dbRates);
			
			LastUpdateStampChecker lastUpdateChecker = QuotationsFactories.getFactory().checkLastQuotationUpdateFor();
			Boolean isUpdateGranted = false;
			synchronized (lastUpdateChecker) {
				isUpdateGranted = lastUpdateChecker.isUpdateGranted(fromCurrency.toString() + " to " + toCurrency.toString(), lastCurrencyRateDate, DateFactory.UStoGBUTCTimeLag(), TradingMode.NON_STOP); //US market close, non stop trading
			}
			
			if (dbRates.isEmpty() || lastCurrencyRateDate.before(today)) {

				Stock currencyStock = new CurrencyStockBuilder(fromCurrency, toCurrency).buildStock();

				if (isUpdateGranted) {//Additional Grant check necessary as the update of exchange rate can come also from calls to convert as well as update quotations
					LOGGER.info(currencyStock + ": update granted from " + df.format(lastCurrencyRateDate) + " to " + df.format(today));

					//bulk download
					dbRates.addAll(bulkCompletion(fromCurrency, toCurrency, today, lastCurrencyRateDate));

					//Check 5 days missing
					lastCurrencyRateDate = fetchLastRateDate(dbRates);
					Calendar fiveDaysAgo = Calendar.getInstance();
					fiveDaysAgo.setTime(today);
					boolean onlyFiveDaysMissing = lastCurrencyRateDate.after(QuotationsFactories.getFactory().incrementDate(fiveDaysAgo,-5).getTime());

					//Complete 5 days missing
					if (onlyFiveDaysMissing) {
						LOGGER.info(currencyStock + ": 5 last days update granted from " + df.format(lastCurrencyRateDate) + " to " + df.format(today));
						dbRates.addAll(fiveDaysCompletion(fromCurrency, toCurrency, today, lastCurrencyRateDate));
					}

				} else {
					if (LOGGER.isDebugEnabled()) LOGGER.debug(currencyStock + ": update NOT granted at " + df.format(today));
				}

			}

			Map<Currency, List<CurrencyRate>> toMap = new ConcurrentHashMap<Currency, List<CurrencyRate>>();
			toMap.put(toCurrency, new ArrayList<CurrencyRate>(dbRates));
			cache.put(fromCurrency, toMap);

		} catch (InterruptedException e) {
			LOGGER.error("", e);
		} catch (InvalidAlgorithmParameterException e1) {
			LOGGER.error("", e1);
		} finally {
			currencyDBAccessSemaphore.release();
		}

	}

	private Collection<? extends CurrencyRate> bulkCompletion(Currency fromCurrency, Currency toCurrency, Date today, Date lastCurrencyRateDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		LOGGER.info(fromCurrency + " to " + toCurrency + ": bulk completion from " + df.format(lastCurrencyRateDate) + " to " + df.format(today));
		//ExchangeRatesFetcher fetcher = new OandaIterativeFetcher(httpSource);
		ExchangeRatesFetcher fetcher = new EuropeanCentralBankFetcher(httpSource);

		List<CurrencyRate> webRates = fetch(fetcher, fromCurrency, toCurrency, lastCurrencyRateDate, today);
		return webRates;

	}

	private Date fetchLastRateDate(NavigableSet<CurrencyRate> dbRates) {
		try {
			//Date lastCurrencyRateDate = (!dbRates.isEmpty())?dbRates.last().getDate():new Date(1104537600000L); //date -d"01 January 2005" +%s
			return (!dbRates.isEmpty())?dbRates.last().getDate():new SimpleDateFormat("yyyyMMdd").parse("19990101");
		} catch (ParseException e) {
			LOGGER.error(e, e);
		}
		return new Date();
	}

	private List<CurrencyRate> fiveDaysCompletion(Currency fromCurrency, Currency toCurrency, Date today, Date lastCurrencyRateDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		LOGGER.info(fromCurrency + " to " + toCurrency + ": daily completion from " + df.format(lastCurrencyRateDate) + " to " + df.format(today));
		ExchangeRatesFetcher fetcher = new XRatesIterativeFetcher(httpSource);

		List<CurrencyRate> webRates = fetch(fetcher, fromCurrency, toCurrency, lastCurrencyRateDate, today);
		return webRates;

	}

	private List<CurrencyRate> fetch(ExchangeRatesFetcher fetcher, Currency fromCurrency, Currency toCurrency, Date lastCurrencyRateDate, Date today) {
		List<CurrencyRate> webRates = new ArrayList<CurrencyRate>();
		try {
			webRates.addAll(fetcher.getRatesForPeriod(fromCurrency, toCurrency, lastCurrencyRateDate, today));
			currencyDao.storeCurrencyRates(webRates);
		} catch (LockAcquisitionException e) {
			LOGGER.warn("",e);
		} catch (HttpException e) {
			LOGGER.error("",e);
		} catch (InterruptedException e) {
			LOGGER.error("",e);
		}
		return webRates;
	}

	//We can convert only toward Base Unit not toward sub unit like pence for pound
	@Override
	public BigDecimal convert(MarketValuation fromCurrency, Currency toCurrency, BigDecimal amount, Date date) {

		BigDecimal fromBaseCurrencyAmount = fromCurrency.translateToBaseCurrencyUnit(amount);
		BigDecimal converted = this.convert(fromCurrency.getCurrency(), toCurrency, fromBaseCurrencyAmount, date);

		return converted;

	}


	@Override
	public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount, Date date) {

		Boolean convertable = !fromCurrency.equals(toCurrency) && !Currency.NAN.equals(toCurrency) && !Currency.NAN.equals(fromCurrency);

		BigDecimal exchangeRate = BigDecimal.ONE;
		if (convertable){
			exchangeRate = fetchRateForDate(fromCurrency, toCurrency, date);
		}

		return exchangeRate.multiply(amount).setScale(10, RoundingMode.HALF_EVEN);
	}

	private BigDecimal fetchRateForDate(Currency fromCurrency, Currency toCurrency, Date date) {

		if (isCacheEmptyFor(fromCurrency, toCurrency) || isCacheOutOfDate(fromCurrency, toCurrency, date) ) {
			fetchHistoricalRatesAvailable(fromCurrency, toCurrency);
		}

		BigDecimal exchangeRate;
		try {
			exchangeRate = extractRatefromCache(cache.get(fromCurrency).get(toCurrency), date);
		} catch (Exception e) {
			LOGGER.warn("Cant get rate for "+fromCurrency,e);
			exchangeRate = BigDecimal.ONE;
		}

		return exchangeRate;
	}

	public List<CurrencyRate> fetchRateHistoryUpTo(Currency fromCurrency, Currency toCurrency, Date date) {

		if (isCacheEmptyFor(fromCurrency, toCurrency) || isCacheOutOfDate(fromCurrency, toCurrency, date) ) {
			fetchHistoricalRatesAvailable(fromCurrency, toCurrency);
		}

		try {
			return cache.get(fromCurrency).get(toCurrency);
		} catch (Exception e) {
			LOGGER.warn("Cant get rate for " + fromCurrency, e);
			return new ArrayList<CurrencyRate>();
		}

	}

	private boolean isCacheOutOfDate(Currency fromCurrency, Currency toCurrency, Date today) {

		//		Calendar yesterday = new GregorianCalendar();
		//		yesterday.setTime(EventSignalConfig.getNewDate());
		//		yesterday.add(Calendar.DATE,-1);

		List<CurrencyRate> rateList = cache.get(fromCurrency).get(toCurrency);
		Date lastCachedDate = rateList.get(rateList.size()-1).getDate();
		//		Boolean noCurrentData = lastCachedDate.before(today);
		//		Boolean lastUpdateWasNotYeasterday = lastCachedDate.before(yesterday.getTime()); //IMF is one day late
		//		Boolean yesterdayWasNotBank = yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
		//		
		//		yesterday.add(Calendar.DATE,-1);
		//		Boolean lastUpdateWasNotTheDayBeforeYeasterday =  lastCachedDate.before(yesterday.getTime());//IMF is two days late ??
		//		Boolean theDayBeforeYesterdayWasNotBank = yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
		//		
		//		return (noCurrentData && lastUpdateWasNotYeasterday && yesterdayWasNotBank && lastUpdateWasNotTheDayBeforeYeasterday && theDayBeforeYesterdayWasNotBank);
		Date lastOpenDayBeforeToday = QuotationsFactories.getFactory().getValidQuotingDateBeforeOrAt(today);
		Boolean isMissingOpenDays = lastCachedDate.before(lastOpenDayBeforeToday);

		return isMissingOpenDays;
	}

	private boolean isCacheEmptyFor(Currency fromCurrency, Currency toCurrency) {
		return (!cache.containsKey(fromCurrency) || !cache.get(fromCurrency).containsKey(toCurrency) || cache.get(fromCurrency).get(toCurrency).isEmpty());
	}

	private BigDecimal extractRatefromCache(List<CurrencyRate> currencyRates, Date date) {

		Integer closestByDichoIndex = getClosestByDicho(currencyRates, date, 0, currencyRates.size()-1);
		return currencyRates.get(closestByDichoIndex).getRate();
	}

	private Integer getClosestByDicho(List<CurrencyRate> rates, Date date, Integer start, Integer end) {

		Integer midle = (end - start) / 2 + start;
		Date dMidle = rates.get(midle).getDate();

		//Stop conditions
		if (date.compareTo(dMidle) == 0)
			return midle;
		if (midle.equals(start)) {
			if (date.before(rates.get(start).getDate()))
				return start;
			else
				return end;
		}

		//Continue
		if (date.before(dMidle)) {
			return this.getClosestByDicho(rates, date, start, midle);
		}
		if (date.after(dMidle)) {
			return this.getClosestByDicho(rates, date, midle, end);
		}
		throw new InvalidParameterException("No data for transactionCurrency");
	}


	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;

	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

}
