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
package com.finance.pms.datasources.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuoteInflationFormater;
import com.finance.pms.datasources.web.formaters.LineFormater;
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

	public ProvidersInflation(String pathToProps) {
		super();
		this.httpSource = new HttpSourceInflation(pathToProps, this);
		this.addObserver(new InflationUpdateObserver());
	}

	@Override
	public void addIndice(Indice indice) {
		// Nothing
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		// Nothing
	}

	@Override
	public Set<Indice> getIndices() {
		return new TreeSet<Indice>();
	}

	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {
		
		try {
			
//			if (!stock.getSymbol().equals(SYMBOL) || !stock.getIsin().equals(SYMBOL)) {
			if (!stock.equals(ProvidersInflation.inflationStock())) {
				String message = "Error : This should be used to retrieve inflation historical only, not : "+stock.toString();
				LOGGER.error(message);
				throw new RuntimeException(message);
			}
			
			long twoMonthAndHalf = (long) 1000*60*60*24*31*2 + 1000*60*60*24*15;
			SimpleDateFormat sdf = new SimpleDateFormat("MMM yy");
			boolean isLastLessThan2AndHalfMonthOld = stock.getLastQuote().getTime() + twoMonthAndHalf >= end.getTime();
			if (isLastLessThan2AndHalfMonthOld) {//Inflation can be updated monthly only
				throw new HttpException("Inflation data can be updated once in a month only.\nYou requested an update for the month preceding "+sdf.format(end)+"\n and the last update was in the month following "+sdf.format(stock.getLastQuote()));
			}

			MyUrl url = this.httpSource.getStockQuotationURL(null,null,null,null,null,null,null);

			TreeSet<Validatable> queries = initValidatableSet();

			LineFormater dsf = new DayQuoteInflationFormater(url, stock, stock.getMarketValuation().getCurrency().name(), start);
			List<Validatable> urlResults = this.httpSource.readURL(dsf);
			for (Validatable validatable : urlResults) {
				if (((DailyQuotation) validatable).getQuoteDate().after(start)) {
					queries.add(validatable);
				}
			}

			LOGGER.guiInfo("Getting last quotes : Number of new quotations for "+stock.getSymbol()+" : "+queries.size());
			ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>() ;
			tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
			
		} finally  {
			this.setChanged();
			this.notifyObservers(end);
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
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		throw new UnsupportedOperationException("Please use a share list holder for that.");
	}

	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		throw new UnsupportedOperationException("Please use a share list holder for that.");
	}

	@Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {
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
