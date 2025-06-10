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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.apache.http.HttpException;

import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.db.ValidatableDated;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.portfolio.PortfolioMgr;

public class ProvidersCurrency extends Providers implements QuotationProvider {

	
	protected ProvidersCurrency() {
		super();
	}

	public ProvidersCurrency(String pathToProps) {
		super();
	}

	@Override
	public void getQuotes(final Stock currencyStock, Date start, Date end) throws SQLException, HttpException {
		
		
		if (!currencyStock.getCategory().equals(StockCategories.CURRENCY_RATE)) {
			throw new RuntimeException("Error: This should be used to retrieve currency historical only, not: "+currencyStock.toString());
		}
		
		//Fetch rates
		String[] symbolIsinSplit = currencyStock.getSymbol().split("Per");
		Currency referee = Currency.valueOf(symbolIsinSplit[1]);
		Currency target = Currency.valueOf(symbolIsinSplit[0]);
		List<ValidatableDated> rates = filterToEndDateInclusive(end, PortfolioMgr.getInstance().getCurrencyConverter().fetchRateHistoryUpTo(referee, target, end));
		
		//Store in quotations
		TreeSet<ValidatableDated> queries = new TreeSet<ValidatableDated>();
		if (rates != null && !rates.isEmpty()) {
			for (ValidatableDated rate : rates) {
				
					ValidatableDated currencyStockQuotation = new CurrencyRate((CurrencyRate) rate) {
						private static final long serialVersionUID = 1L;
	
						@Override
						public Query toDataBase() {
							
							LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
							
							mainQuery.add(getDate());
							mainQuery.add(getRate());
							mainQuery.add(getRate());
							mainQuery.add(getRate());
							mainQuery.add(getRate());
							mainQuery.add(Long.valueOf(0));
							mainQuery.add(null); //split
							mainQuery.add(ORIGIN.WEB.ordinal());
							
							Query iq = new Query();
							iq.addValuesList(mainQuery);
							iq.addValue(Currency.NAN.name());
							iq.addValue(currencyStock.getSymbol());
							iq.addValue(currencyStock.getIsin());	
							return iq;
						}
					};
					queries.add(currencyStockQuotation);
			}
			
			ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>() ;
			tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<ValidatableDated>(queries), tablet2lock);
			
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
		throw new UnsupportedOperationException("Currencies can't be updated that way.");
	}

}
