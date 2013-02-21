package com.finance.pms.datasources.web.currency;

import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;

import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.StockNotFoundException;
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
	
	public Stock buildStock() throws InvalidAlgorithmParameterException, SQLException, StockNotFoundException {
		
		final String isinSymbol = target.name()+"Per"+referee.name();
		String name = target.name()+" Per " +referee.name();
		Stock currencyStock = new Stock(
				isinSymbol, isinSymbol, name, 
				true, StockCategories.CURRENCY_RATE,
				new SymbolMarketQuotationProvider(MarketQuotationProviders.CURRENCY, SymbolNameResolver.UNKNOWNEXTENSIONCLUE), new MarketValuation(Market.UNKNOWN), "", TradingMode.CONTINUOUS, 0l);
		
		QuotationUpdate quotationUpdate = new QuotationUpdate();
		StockList stockList = new StockList();
		stockList.add(currencyStock);
		quotationUpdate.getQuotes(stockList);
		
		DataSource.getInstance().getShareDAO().saveOrUpdateStock(currencyStock);
		
		return currencyStock;
	}

}
