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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuoteInvestirFormater;

public class ProvidersInvestir extends Providers implements QuotationProvider {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersInvestir.class);
	
	public ProvidersInvestir(String pathToProps) {
		super();
		this.httpSource = new HttpSourceInvestir(pathToProps, this);
	}
	
	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {
		
		//TODO scrapeLast();
		
		MyUrl url = resolveUrlFor(stock, start, end);
		TreeSet<Validatable> queries = initValidatableSet();
		
		for (int i = 1; i <= url.getNbPages(); i++) {
			List<Validatable> urlResults = readPage(stock, url.getUrlForPage(i), start);
			if (urlResults.size() == 0) { break;}
			for (Validatable validatable : urlResults) {
				Date qDate = ((DailyQuotation) validatable).getQuoteDate();
				if (qDate.after(start) || qDate.equals(start)) {
					queries.add(validatable);
				}
			}
			LOGGER.guiInfo("Getting last quotes on investir web site : Number of new quotations for "+stock.getSymbol()+" :"+queries.size());
		}
	
		ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>() ;
		tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
		DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries),tablet2lock);

	}

	public List<Validatable> readPage(Stock stock, MyUrl url, Date start) throws HttpException {
		DayQuoteInvestirFormater dsf = new DayQuoteInvestirFormater(url, stock, stock.getMarketValuation().getCurrency().name(), start);
		return this.httpSource.readURL(dsf);
	}
	
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) {
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		String urlString = ((HttpSourceInvestir) this.httpSource).getStockInvestirURL();
		String extension = stock.getSymbolMarketQuotationProvider().getMarketQuotationProvider().getSymbolNameResolver().getExtension(stock.getSymbol());
		
		if (extension.equals(stock.getSymbol())) { //No symbol extension. We try with the market
			extension = stock.getMarketValuation().getMarket().getInvestirExtension();
		}
 
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		Integer nbYears = today.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
		Integer nbMonth = nbYears * 12 + today.get(Calendar.MONTH) - startCal.get(Calendar.MONTH) +1;
		nbMonth =  Math.min(nbMonth, 60);
		
		//String formatedUrl = String.format(urlString, stock.getIsin(), extension, -nbMonth);
		String formatedUrl = String.format(urlString, extension, stock.getIsin(), -nbMonth);
		
		Integer nbPages = (nbMonth * 25) / 40 +1;
		MyUrl url = new MyUrl(formatedUrl) {

			@Override
			public MyUrl getUrlForPage(Integer i) {
				//return new MyUrl(this.getUrl()+i);
				return new MyUrl(this.getUrl().replaceAll("\\$\\$PAGENUM\\$\\$", ""+i));
			}
			
		};
		url.setNbPages(nbPages);
		return url;
	}

	@Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {
		 throw new NotImplementedException() ;
	}

	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		throw new NotImplementedException();
	}

	@Override
	public String getStockRefName(Stock stock) {
		 return stock.getSymbol();
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock s, StockList stockList) {
		throw new NotImplementedException();
	}

	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareListInDB) {
		throw new NotImplementedException();
	}

	@Override
	public void addIndice(Indice indice) {
		// Nothing
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<Indice> getIndices() {
		// TODO Auto-generated method stub
		return null;
	}
}
