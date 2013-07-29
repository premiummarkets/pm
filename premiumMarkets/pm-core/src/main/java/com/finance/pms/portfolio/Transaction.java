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


public class Transaction {
	
	public enum TransactionType {AIN,AOUT,NULL};
	
	private BigDecimal transactionSharePrice;
	private BigDecimal quantity;
	private BigDecimal fullAmountIn;
	private BigDecimal fullAmountOut;
	private TransactionType modtype;
	private Date date;


	public Transaction(BigDecimal previousAmountIn, BigDecimal previousAmountOut, BigDecimal quantity, BigDecimal transactionSharePrice, TransactionType modtype, Date date) 
	throws InvalidQuantityException {
		super();
		
		if (quantity.compareTo(BigDecimal.ZERO) < 0)  throw new InvalidQuantityException("The quantity can't be negative!",new Throwable());
		this.fullAmountIn = previousAmountIn;
		this.fullAmountOut = previousAmountOut;
		this.quantity = quantity;
		this.transactionSharePrice = transactionSharePrice;
		this.modtype = modtype;
		this.date = date;
	}
	
	public BigDecimal getTransactionSharePrice() {
		return transactionSharePrice;
	}

	public void setTransactionSharePrice(Float transactionSharePrice) {
		this.transactionSharePrice = new BigDecimal(transactionSharePrice.toString()).setScale(2,BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = new BigDecimal(quantity.toString()).setScale(4,BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal amount() {
		return this.quantity.multiply(this.transactionSharePrice).setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	
	public BigDecimal fullAmountIn() {
		
		if (this.modtype.equals(TransactionType.AIN)) {
			BigDecimal fullin =  this.fullAmountIn.add(this.amount());
			return fullin;
		}
		return this.fullAmountIn;
	
	}
	
	public BigDecimal fullAmountOut() {
		
		if (this.modtype.equals(TransactionType.AOUT)) {
			BigDecimal fullout = this.fullAmountOut.add(this.amount());
			return fullout;
		} 
		return this.fullAmountOut;
			
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
		return "Transaction [transactionPrice=" + transactionSharePrice + ", quantity=" + quantity + ", fullAmountIn=" + fullAmountIn
				+ ", fullAmountOut=" + fullAmountOut + ", modtype=" + modtype + ", date=" + date + "]";
	}
	
	
	
}
