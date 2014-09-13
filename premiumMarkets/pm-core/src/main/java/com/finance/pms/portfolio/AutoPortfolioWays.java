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
import java.util.Map;

import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.threads.ObserverMsg;

public interface AutoPortfolioWays {
	
	String getName();

	void notifyObservers(ObserverMsg msg);

	Map<Stock, PortfolioShare> getListShares();

	BigDecimal withdrawCash(Date currentDate, Currency transactionCurrency) throws NoCashAvailableException;

	BigDecimal getAvailableCash(Date currentDate);

	void setChanged();

	PortfolioShare addOrUpdateShare(Stock stock, BigDecimal quantity, Date date, BigDecimal avgBuyPrice, MonitorLevel mLevel, Currency trCurrency, TransactionType trType) throws InvalidQuantityException, InvalidAlgorithmParameterException;
	
	TransactionHistory calculate(Date endDate, String... eventListName);

	void exportAutoportfolioContent(Date date);

	void updateShare(PortfolioShare portfolioShare, BigDecimal quantity, Date currentDate, BigDecimal trPrice, TransactionType trType) throws InvalidQuantityException;
	
	public TransactionHistory getTransactionHistory();
	
	public Currency getPortfolioCurrency();
	
	public void log(TransactionRecord transactionRecord);
	
	public String extractPortfolioTransactionLog(Date startDate, Date endDate) throws Throwable;

}
