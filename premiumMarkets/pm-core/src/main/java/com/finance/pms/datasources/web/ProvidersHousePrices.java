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
package com.finance.pms.datasources.web;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuoteHousePricesFormater;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class ProvidersHousePrices extends Providers implements QuotationProvider, ProvidersRates {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersHousePrices.class);

	protected ProvidersHousePrices() {
		super();
	}

	public ProvidersHousePrices(String pathToProps) {
		super();
		this.httpSource = new HttpSourceHousePrices(pathToProps, this);
	}

	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date volumeAvail = sdf.parse("20050101");
			
			Date startQ = (start.before(volumeAvail))? start: volumeAvail;
			Quotations periodQuotations = QuotationsFactories.getFactory().
												getSplitFreeQuotationsInstance(stock, startQ, end, false, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
			
			List<QuotationUnit> validWebDbQ = periodQuotations.getQuotationUnits(0, periodQuotations.size()-1).stream()
												//Valid only if volume was present as latest HPIs come without the volume. This applies only at or after volumeAvail date
												.filter(qu -> ORIGIN.WEB.equals(qu.getOrigin()) && 
														(qu.getDate().before(volumeAvail) || (qu.getDate().compareTo(volumeAvail) >= 0) && qu.getVolumeSplit() != 0)) 
												.collect(Collectors.toList()); 
			List<QuotationUnit> usersDbQ = periodQuotations.getQuotationUnits(0, periodQuotations.size()-1).stream()
												.filter(qu -> (ORIGIN.USER.equals(qu.getOrigin())))
												.collect(Collectors.toList());

			BigDecimal formerWebDbClose = validWebDbQ.get(validWebDbQ.size()-1).getCloseRaw();
			Date formerWebDbDate = validWebDbQ.get(validWebDbQ.size()-1).getDate();
			
			Calendar formerWebDbCalendar = Calendar.getInstance();
			formerWebDbCalendar.setTime(formerWebDbDate);
			int n = 6;
			formerWebDbCalendar.add(Calendar.MONTH, n);
			
			SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
			SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
			Calendar now = DateFactory.getNowEndDateCalendar();
			int currentYear = now.get(Calendar.YEAR);
			int currentMonth = now.get(Calendar.MONTH);
			int formerValidYear =  formerWebDbCalendar.get(Calendar.YEAR);
			int formeValidMonth =  formerWebDbCalendar.get(Calendar.MONTH);
			if (currentYear < formerValidYear || (currentYear == formerValidYear && currentMonth <= formeValidMonth)) {//monthly only
				lastQuoteInterpolation(stock, formerWebDbClose, formerWebDbDate, end, usersDbQ);
				throw new HttpException(
					"House prices data can only be updated once in " + (n +1) + " month.\n" +
					"You already updated up to " + sdfYear.format(formerWebDbDate) + "/" + sdfMonth.format(formerWebDbDate)
				);
			}

			String region = stock.getSymbol().replaceAll("hpi", "");
			MyUrl url = this.httpSource.getStockQuotationURL(region , 
											sdfYear.format(formerWebDbDate), sdfMonth.format(formerWebDbDate), "01", 
											sdfYear.format(now.getTime()), sdfMonth.format(now.getTime()), "01");

			TreeSet<ValidatableDated> queries = initValidatableSet();
			LineFormater dsf = new DayQuoteHousePricesFormater(url, stock, start);
			List<ValidatableDated> readPage = this.httpSource.readURL(dsf).stream().map(v -> (ValidatableDated) v).collect(Collectors.toList());
			readPage = filterToEndDate(end, readPage);
			
			if (!readPage.isEmpty()) {
			
				for (ValidatableDated validatable : readPage) {
					if (validatable.getDate().after(start)) {
						queries.add(validatable);
					}
				}

				//Update with new Web quotations
				LOGGER.guiInfo("Getting last quotes: Number of new quotations for " + stock.getSymbol() + ": " + queries.size());
				ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
				tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
				DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<ValidatableDated>(queries), tablet2lock);
				
				List<Validatable> urlResultsSorted = readPage.stream().sorted((i1, i2) -> ((DailyQuotation)i2).compareTo(i1)).collect(Collectors.toList());
				DailyQuotation lastWebQuotation = (DailyQuotation) urlResultsSorted.get(0);
				formerWebDbClose = (BigDecimal) lastWebQuotation.getClose();
				formerWebDbDate = lastWebQuotation.getQuoteDate();
				
			}

			lastQuoteInterpolation(stock, formerWebDbClose, formerWebDbDate, end, usersDbQ);

		} catch (NoQuotationsException e) {
			LOGGER.error(e);
		} catch (ParseException e) {
			LOGGER.error(e, e);
		}
	}
	
	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) {
		throw new UnsupportedOperationException("Please use a share list holder for that.");
	}

	@Override
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception {
		return null;
	}

	@Override
	public List<Validatable> readPage(Stock stock, MyUrl url, Date date) throws HttpException {
		throw new UnsupportedOperationException("Can't be updated that way.");
	}

}
