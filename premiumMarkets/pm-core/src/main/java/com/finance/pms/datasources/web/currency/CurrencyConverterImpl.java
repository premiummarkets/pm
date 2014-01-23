/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.datasources.web.currency;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.apache.commons.httpclient.HttpException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyDAO;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.web.HttpSourceExchange;
import com.finance.pms.datasources.web.MyBeanFactoryAware;
import com.finance.pms.datasources.web.formaters.ImfCurrencyHistoryFormater;
import com.finance.pms.events.calculation.DateFactory;

public class CurrencyConverterImpl implements CurrencyConverter, MyBeanFactoryAware {
	
	private final MyLogger LOGGER = MyLogger.getLogger(CurrencyConverterImpl.class);
	
	private HttpSourceExchange httpSource;
	private CurrencyDAO currencyDao;	
	private NumberFormat numberFormater = NumberFormat.getNumberInstance();
	private Map<Currency,Map<Currency,List<CurrencyRate>>> cache;
	
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

	@SuppressWarnings("unchecked")
	private void fetchHistoricalRatesAvailable(Currency fromCurrency, Currency toCurrency) {
		
		try {
			currencyDBAccessSemaphore.acquire();
			
			NavigableSet<CurrencyRate> dbRates = new TreeSet<CurrencyRate>();
			dbRates.addAll(currencyDao.getRates(fromCurrency,toCurrency));
			
//			Calendar todayCal = Calendar.getInstance();
//			todayCal.set(Calendar.HOUR_OF_DAY, 0);
//			todayCal.set(Calendar.MINUTE, 0);
//			todayCal.set(Calendar.SECOND, 0);
//			todayCal.set(Calendar.MILLISECOND,0);
//			Date today = todayCal.getTime();
			Date today = DateFactory.midnithDate(new Date());
			
			Date lastCurrencyRateDate = (!dbRates.isEmpty())?dbRates.last().getDate():new Date(1104537600000L); //date -d"01 January 2005" +%s
//			Date lastAvail = todayCal.getTime();
			if (dbRates.isEmpty() || lastCurrencyRateDate.before(today)) {

				@SuppressWarnings("rawtypes")
				List webRates = new ArrayList<Validatable>();
				try {
					webRates.addAll(this.getRatesForPeriod(fromCurrency, toCurrency, lastCurrencyRateDate, today));
					currencyDao.storeCurrencyRates(webRates);
				} catch (LockAcquisitionException e) {
					LOGGER.warn("",e);
				} catch (HttpException e) {
					LOGGER.error("",e);
				} catch (InterruptedException e) {
					LOGGER.error("",e);
				}
				dbRates.addAll(webRates);

			}
			
			Map<Currency, List<CurrencyRate>> toMap = new ConcurrentHashMap<Currency, List<CurrencyRate>>();
			toMap.put(toCurrency, new ArrayList<CurrencyRate>(dbRates));
			cache.put(fromCurrency,toMap);
			
		} catch (InterruptedException e) {
			LOGGER.error("",e);
		} finally {
			currencyDBAccessSemaphore.release();
		}

	}

	//We can convert only toward Base Unit not toward sub unit like pence for pound
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
		
		return exchangeRate.multiply(amount).setScale(4, BigDecimal.ROUND_HALF_UP);
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
			LOGGER.warn("Cant get rate for "+fromCurrency,e);
			return new ArrayList<CurrencyRate>();
		}
	
	}

	/**
	 * @param fromCurrency
	 * @param toCurrency
	 * @param date
	 * @param yesterday
	 * @return
	 */
	private boolean isCacheOutOfDate(Currency fromCurrency, Currency toCurrency, Date date) {
		
		Calendar yesterday = new GregorianCalendar();
		yesterday.setTime(EventSignalConfig.getNewDate());
		yesterday.add(Calendar.DATE,-1);
		
		List<CurrencyRate> rateList = cache.get(fromCurrency).get(toCurrency);
		Boolean noCurrentData = rateList.get(rateList.size()-1).getDate().before(date);
		Boolean lastUpdateWasNotYeasterday = rateList.get(rateList.size()-1).getDate().before(yesterday.getTime()); //IMF is one day late
		Boolean yesterdayWasNotBank = yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
		
		yesterday.add(Calendar.DATE,-1);
		Boolean lastUpdateWasNotTheDayBeforeYeasterday =  rateList.get(rateList.size()-1).getDate().before(yesterday.getTime());//IMF is two days late ??
		Boolean theDayBeforeYesterdayWasNotBank = yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && yesterday.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
		
		return (noCurrentData && lastUpdateWasNotYeasterday && yesterdayWasNotBank && lastUpdateWasNotTheDayBeforeYeasterday && theDayBeforeYesterdayWasNotBank);
	}

	/**
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	private boolean isCacheEmptyFor(Currency fromCurrency, Currency toCurrency) {
		return (!cache.containsKey(fromCurrency) || !cache.get(fromCurrency).containsKey(toCurrency) || cache.get(fromCurrency).get(toCurrency).isEmpty());
	}

	private BigDecimal extractRatefromCache(List<CurrencyRate> currencyRates, Date date) {
		
		Integer closestByDichoIndex = getClosestByDicho(currencyRates, date, 0, currencyRates.size()-1);
		return currencyRates.get(closestByDichoIndex).getRate();
	}

	/**
	 * Gets the closest by dicho.
	 * 
	 * @param date the date
	 * @param start the start
	 * @param end the end
	 * 
	 * @return the closest by dicho
	 */
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
	
	@SuppressWarnings("unchecked")
	private List<CurrencyRate> getRatesForPeriod(final Currency fromCurrency,final Currency toCurrency, Date start, Date end) throws HttpException, InterruptedException {

		@SuppressWarnings("rawtypes")
		final List rates = new ArrayList<CurrencyRate>();
		Calendar startDateCalendar = Calendar.getInstance();
		startDateCalendar.setTime(start);
		Calendar endDateCalendar = Calendar.getInstance();
		endDateCalendar.setTime(end);

		final String url = httpSource.getImfHistoryUrl(start);
		LOGGER.debug("Url : "+url);
		
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					rates.addAll(httpSource.readURL(new ImfCurrencyHistoryFormater(fromCurrency,toCurrency,url)));
				} catch (HttpException e) {
					LOGGER.error("",e);
				} finally {
					
				}
			}
		});
	
		thread.start();


		int lastDayOfStartDate = startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (startDateCalendar.get(Calendar.DAY_OF_MONTH) == lastDayOfStartDate) {
			startDateCalendar.add(Calendar.MONTH, 1);
			startDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			startDateCalendar.set(Calendar.DAY_OF_MONTH, lastDayOfStartDate);
		}

		if (startDateCalendar.before(endDateCalendar)) {
			rates.addAll(getRatesForPeriod(fromCurrency,toCurrency,startDateCalendar.getTime(),end));
		} 
		
		thread.join();
		return rates;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
		
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

}
