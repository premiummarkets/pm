/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
package com.finance.pms.portfolio;

import java.math.BigDecimal;
import java.util.Date;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventSource;
import com.finance.pms.events.SymbolEvents;

public class TransactionRecord {
	
	//this.log("available cash","date", "symbol", "isin", "sharename", "movement", "quantity", "price", "eventList");
	private String portfolioName;
	private BigDecimal availableCash;
	private Date date;
	private Stock stock;
	private String movement;
	private BigDecimal transactionQuantity;
	private BigDecimal transactionPrice;
	private SymbolEvents eventList;
	private EventSource source;
	
	public TransactionRecord(String tunningPortfolioName,BigDecimal availbleCash, Date date, Stock stock, String movement, BigDecimal quantity, BigDecimal price, SymbolEvents eventList, EventSource source) {
		super();
		this.portfolioName = tunningPortfolioName;
		this.availableCash = availbleCash;
		this.date = date;
		this.stock = stock;
		this.movement = movement;
		this.transactionQuantity = quantity;
		this.transactionPrice = price;
		this.eventList = eventList;
		this.source = source;
	}
	
	public String getPortfolioName() {
		return portfolioName;
	}
	
	public BigDecimal getAvailableCash() {
		return availableCash;
	}
	public void setAvailableCash(BigDecimal availbleCash) {
		this.availableCash = availbleCash;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public BigDecimal getTransactionQuantity() {
		return transactionQuantity;
	}
	public void setTransactionQuantity(BigDecimal quantity) {
		this.transactionQuantity = quantity;
	}
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(BigDecimal price) {
		this.transactionPrice = price;
	}
	public SymbolEvents getEventList() {
		return eventList;
	}

	@Override
	public String toString() {
		return " portfolioName : " + portfolioName 
				+ ".\n stock : " + stock 
				+ ".\n movement : " + movement
				+ ".\n transactionPrice : " + transactionPrice 
				+ ".\n date (mvt detection date) : " + date 
				+ ".\n quantity left/bought : " + transactionQuantity
				+ ".\n availableCash : " + availableCash 
				+ ".\n eventList : " + eventList
				+ ".\n buytriggeringEvents : " + ((eventList != null)? eventList.getBuyTriggeringEvents(): "none")
				+ ".\n selltriggeringEvents : " + ((eventList != null)? eventList.getSellTriggeringEvents(): "none")
				+ ".\n triggeringWeight : " + ((eventList != null)? eventList.getTriggeringFinalWeight(): "none");
	}

	public EventSource getSource() {
		return source;
	}
	
	
	
}
