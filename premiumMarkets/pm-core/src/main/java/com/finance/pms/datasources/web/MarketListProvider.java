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

import java.util.Collection;
import java.util.Observer;
import java.util.Set;
import java.util.SortedSet;

import org.apache.http.HttpException;

import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.portfolio.SharesList;

public interface MarketListProvider {

	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stocksInDB) throws HttpException;

	public void  updateStockListFromWeb(MarketQuotationProviders marketQuotationsProviders) throws HttpException;

	public MarketQuotationProviders defaultMarketQuotationProviders();

	public void addIndices(SortedSet<Indice> indices, Boolean replace);

	public abstract SortedSet<Indice> getIndices();

	public SharesListId getSharesListIdEnum();

	public void retrieveStockListFromBase(StockList dbStockList);

	public Stock supplement(Stock stock);

	public void addObservers(Set<Observer> engineObservers);

	public SharesList loadSharesListForThisListProvider();

	public StockList retreiveStockListFromFile(String pathToFileList, StockList dbStockList);

	public void retrieveAndCompleteStockInfo(Stock newStock, StockList dbStockList);
	
	public void retrieveScreeningInfo(Collection<Stock> shareListInDB);

}
