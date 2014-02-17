package com.finance.pms.datasources.files;

import java.math.BigDecimal;
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
		this.transactionSharePrice = new BigDecimal(transactionSharePrice.toString()).setScale(10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = new BigDecimal(quantity.toString()).setScale(10, BigDecimal.ROUND_HALF_EVEN);
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