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
package com.finance.pms.datasources.files;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.finance.pms.portfolio.InvalidQuantityException;

public class Transaction {
	

	private BigDecimal transactionSharePrice;
	private BigDecimal quantity;
	private TransactionType modtype;
	private Date date;


	public Transaction(BigDecimal quantity, BigDecimal transactionSharePrice, TransactionType modtype, Date date) 
	throws InvalidQuantityException {
		super();
		
		if (quantity.compareTo(BigDecimal.ZERO) < 0)  {
			throw new InvalidQuantityException("The quantity can't be negative!",new Throwable());
		}
		this.quantity = quantity;
		this.transactionSharePrice = transactionSharePrice;
		this.modtype = modtype;
		this.date = date;
	}
	
	public BigDecimal getTransactionSharePrice() {
		return transactionSharePrice;
	}
	
	public void setTransactionSharePrice(BigDecimal transactionSharePrice) {
		this.transactionSharePrice = transactionSharePrice;
	}

	public void setTransactionSharePrice(Float transactionSharePrice) {
		this.transactionSharePrice = new BigDecimal(transactionSharePrice.toString()).setScale(10, RoundingMode.HALF_EVEN);
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = new BigDecimal(quantity.toString()).setScale(10, RoundingMode.HALF_EVEN);
	}

	public TransactionType getModtype() {
		return modtype;
	}


	public void setModtype(TransactionType modtype) {
		this.modtype = modtype;
	}


	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Transaction [transactionPrice=" + transactionSharePrice + ", quantity=" + quantity + ", fullAmountIn=" + ", modtype=" + modtype + ", date=" + date + "]";
	}

	public void setDate(Date date) {
		this.date = date;
	}
}