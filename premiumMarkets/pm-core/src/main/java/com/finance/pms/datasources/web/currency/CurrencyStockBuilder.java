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
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;

public class CurrencyStockBuilder {
	
	Currency target;
	Currency referee;
	
	
	public CurrencyStockBuilder(Currency referee, Currency target) {
		super();
		this.target = target;
		this.referee = referee;
		
	}
	
	public Stock buildAndFetchRates() throws InvalidAlgorithmParameterException, SQLException, QuotationUpdateException {
		
		Stock currencyStock = buildStock();
		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		StockList stockList = new StockList();
		stockList.add(currencyStock);
		quotationUpdate.getQuotes(stockList);
		
		DataSource.getInstance().getShareDAO().saveOrUpdateStock(currencyStock);
		
		return currencyStock;
	}

	public Stock buildStock() throws InvalidAlgorithmParameterException {
		
		final String isinSymbol = target.name()+"Per"+referee.name();
		String name = target.name()+" Per " +referee.name();
		
		Stock currencyStock = DataSource.getInstance().getShareDAO().loadStockBy(isinSymbol, isinSymbol);
		if (currencyStock == null) {
			currencyStock = new Stock(
					isinSymbol, isinSymbol, name, 
					true, StockCategories.CURRENCY_RATE,
					new SymbolMarketQuotationProvider(MarketQuotationProviders.CURRENCY, SymbolNameResolver.UNKNOWNEXTENSIONCLUE), 
					new MarketValuation(Market.UNKNOWN, BigDecimal.ONE, target), "", TradingMode.CONTINUOUS, 0l
				);
		}
		return currencyStock;
		
	}

}
