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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.finance.pms.datasources.files.TransactionElement;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.AutoPortfolioDelegate.BuyStrategy;
import com.finance.pms.threads.ObserverMsg;

public interface AutoPortfolioWays {
	
	
	//The concurrency thing
	TransactionHistory calculate(List<SymbolEvents> events, Date enDate, BuyStrategy buyStrategy, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule);
	Boolean isAutoCalculationIdempotent();

	//The buy and sell thing
	BigDecimal withdrawCash(Date currentDate, Currency transactionCurrency) throws NoCashAvailableException;
	BigDecimal getAvailableCash(Date currentDate);
	
	Currency getPortfolioCurrency();
	void updateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException;
	PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date date, BigDecimal avgBuyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException, InvalidAlgorithmParameterException;

	//The transactions thing
	TransactionHistory getTransactionHistory();
	void log(TransactionRecord transactionRecord);
	String extractPortfolioTransactionLog(Date startDate, Date endDate) throws Throwable;
	SortedSet<TransactionElement> getTransactions();
	
	//Other things
	String getName();

	void notifyObservers(ObserverMsg msg);
	void setChanged();

	Map<Stock, PortfolioShare> getListShares();
	List<PortfolioShare> getOwnedPorfolioShares();

	void exportAutoportfolioContent(Date date);



}
