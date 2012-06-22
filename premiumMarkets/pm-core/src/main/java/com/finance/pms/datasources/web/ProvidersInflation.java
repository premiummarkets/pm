/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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

public class ProvidersInflation extends Providers {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersInflation.class);

	public static final String SYMBOL = "Inflation";

	public ProvidersInflation(String pathToProps) {
		super();
		this.httpSource = new HttpSourceInflation(pathToProps, this);
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
	public Date getQuotes(Stock stock, Date start, Date end) throws SQLException, HttpException {

		MyUrl url;
		if (!stock.getSymbol().equals(SYMBOL) || !stock.getIsin().equals(SYMBOL)) {
			throw new RuntimeException("Error : This should be used to retreive inflation historical only, not : "+stock.toString());
		}

		url = this.httpSource.getStockQuotationURL(null,null,null,null,null,null,null);

		TreeSet<Validatable> queries = initValidatableSet();

		LineFormater dsf = new DayQuoteInflationFormater(url, stock, stock.getMarket().getCurrency().name());
		List<Validatable> urlResults = this.httpSource.readURL(dsf);
		for (Validatable validatable : urlResults) {
			if (((DailyQuotation) validatable).getQuoteDate().after(start)) {
				queries.add(validatable);
			}
		}


		LOGGER.guiInfo("Getting last quotes : Number of new quotations for "+stock.getSymbol()+" : "+queries.size());
		ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>() ;
		tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
		DataSource.getInstance().executeLongBatch(queries,DataSource.QUOTATIONS.getINSERT(),tablet2lock);

		return extractLastDateFrom(queries);

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

}
