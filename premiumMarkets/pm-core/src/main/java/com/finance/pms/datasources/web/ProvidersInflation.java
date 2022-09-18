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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import com.finance.pms.datasources.quotation.GetInflation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuoteInflationFormater;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.InflationUpdateObserver;

public class ProvidersInflation extends Providers implements QuotationProvider {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersInflation.class);

	private static Stock inflationStock;

	public static final Stock inflationStock() {
		if (inflationStock == null) {
			inflationStock = DataSource.getInstance().loadStockBySymbol("Inflation");
		}
		return inflationStock;
	}

	protected ProvidersInflation() {
		super();
		this.addObserver(new InflationUpdateObserver());
	}

	public ProvidersInflation(String pathToProps) {
		super();
		this.httpSource = new HttpSourceInflation(pathToProps, this);
		this.addObserver(new InflationUpdateObserver());
	}

	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {

		try {

			if (!stock.equals(ProvidersInflation.inflationStock())) {
				String message = "Error : This should be used to retrieve inflation historical only, not : " + stock.toString();
				LOGGER.error(message);
				throw new RuntimeException(message);
			}

			long twoMonthAndHalf = (long) (DateFactory.DAYINMILLI*31.0*2.5); //(long) DateFactory.DAYINMILLI*31*2 + DateFactory.DAYINMILLI*15;
			SimpleDateFormat sdf = new SimpleDateFormat("MMM yy");
			Date lastWebDate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, true);
			
			Quotations lastQuotations = 
					QuotationsFactories.getFactory().getQuotationsInstance(stock, start, end, false, stock.getMarketValuation().getCurrency(), 0, ValidityFilter.CLOSE);
			List<QuotationUnit> usersDbQ = lastQuotations.getQuotationUnits(0, lastQuotations.size()-1).stream()
					.filter(qu -> ORIGIN.USER.equals(qu.getOrigin()))
					.collect(Collectors.toList());
			List<QuotationUnit> webDbQ = lastQuotations.getQuotationUnits(0, lastQuotations.size()-1).stream()
					.filter(qu -> ORIGIN.WEB.equals(qu.getOrigin()))
					.collect(Collectors.toList()); 
			BigDecimal lastClose = webDbQ.get(webDbQ.size()-1).getCloseRaw();
			Date lastDate = webDbQ.get(webDbQ.size()-1).getDate();
			
			boolean isLastLessThan2AndHalfMonthOld = lastWebDate.getTime() + twoMonthAndHalf >= end.getTime();
			if (isLastLessThan2AndHalfMonthOld) {//Inflation can be updated monthly only
				lastQuoteInterpolation(stock, lastClose, lastDate, end, usersDbQ);
				throw new HttpException(
					"Inflation data can only be updated once in " + twoMonthAndHalf/(31.0*DateFactory.DAYINMILLI) + " months.\n" +
					"You already updated up to " + sdf.format(lastWebDate)
				);
			}

			MyUrl url = this.httpSource.getStockQuotationURL(null,null,null,null,null,null,null);

			TreeSet<Validatable> queries = initValidatableSet();
			LineFormater dsf = new DayQuoteInflationFormater(url, stock, stock.getMarketValuation().getCurrency().name(), start);
			@SuppressWarnings("unchecked")
			List<Validatable> urlResults = filterToEndDate(end, (Collection<? extends ValidatableDated>)  this.httpSource.readURL(dsf));
			
			if (!urlResults.isEmpty()) {
			
				for (Validatable validatable : urlResults) {
					if (((DailyQuotation) validatable).getQuoteDate().after(start)) {
						queries.add(validatable);
					}
				}

				//Update with new Web quotations
				LOGGER.guiInfo("Getting last quotes : Number of new quotations for " + stock.getSymbol() + " : " + queries.size());
				ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
				tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
				DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
				
				List<Validatable> urlResultsSorted = urlResults.stream().sorted((i1, i2) -> ((DailyQuotation)i2).compareTo(i1)).collect(Collectors.toList());
				DailyQuotation lastWebQuotation = (DailyQuotation) urlResultsSorted.get(0);
				lastClose = (BigDecimal) lastWebQuotation.getClose();
				lastDate = lastWebQuotation.getQuoteDate();
				
			}

			lastQuoteInterpolation(stock, lastClose, lastDate, end, usersDbQ);

		} catch (NoQuotationsException e) {
			LOGGER.error(e);
		} finally {
			this.setChanged();
			this.notifyObservers(end);
		}

	}

	private void lastQuoteInterpolation(Stock stock, BigDecimal lastClose, Date lastDate, Date end, List<QuotationUnit> usersQ) {
		try {
			GetInflation inflationInterpoler = GetInflation.geInstance();

			BigDecimal inflationRateWithinDateRange = inflationInterpoler.inflationRateWithinDateRange(lastDate, end);
			int a = QuotationsFactories.getFactory().nbOpenIncrementBetween(lastDate, end);
			Double endClose = lastClose.doubleValue() + ((double) a) * inflationRateWithinDateRange.doubleValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(end);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			QuotationUnit quotationUnit = 
				new QuotationUnit(
					stock, stock.getMarketValuation().getCurrency(),
					calendar.getTime(),
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					BigDecimal.ZERO,
					new BigDecimal(endClose),
					Long.valueOf(0),
					ORIGIN.USER, BigDecimal.ONE);
			
			//Clean up interpolations between start and end
			DataSource.getInstance().getShareDAO().deleteQuotationUnits(usersQ); 
			
			//Insert new interpolation
			DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnit(quotationUnit);
			
		} catch (NotEnoughDataException e) {
			LOGGER.warn("Can't interpolate remaining inflation. " + e);
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
		throw new UnsupportedOperationException("Inflation can't be updated that way.");
	}

}
