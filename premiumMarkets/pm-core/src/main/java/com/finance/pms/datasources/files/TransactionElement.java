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
package com.finance.pms.datasources.files;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

@Entity()
@Table(name="TRANSACTIONS")
public class TransactionElement implements Comparable<TransactionElement>, Serializable {
	
	private static final long serialVersionUID = -257553176773712060L;
	
	@Id  @GeneratedValue
	Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumns({ @JoinColumn(name = "isin", referencedColumnName = "isin"), @JoinColumn(name = "symbol", referencedColumnName = "symbol") })
	Stock stock;
	String account;
	@Temporal(TemporalType.DATE)
	Date date;
	BigDecimal quantity;
	BigDecimal price;
	@Enumerated(EnumType.STRING)
	Currency currency;
	
	@SuppressWarnings("unused")
	private TransactionElement() {
		//Hib
	}
	
	public TransactionElement(Stock stock, String accountName, Date date, BigDecimal price, BigDecimal amount, Currency currency) {
		super();
		this.stock = stock;
		this.account = accountName;
		this.date = date;
		this.price = price;
		this.quantity = amount;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "TransactionElement [symbol=" + stock + ", accountName=" + account + ", date=" + date + ", quantity="+ quantity + ", price=" + price + "]";
	}

	public int compareTo(TransactionElement o) {
		int equalDate = this.date.compareTo(o.date);
		if (equalDate == 0) {
			return 1;
		}
		return equalDate;
	}

	public Stock getStock() {
		return stock;
	}

	public Date getDate() {
		return date;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param simpleDateFormat
	 * @param reportPrint
	 */
	public StringBuffer printTestElement() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
		StringBuffer reportPrint = new StringBuffer();
		reportPrint.append("elements.add(new TransactionElement(stock").append(", \""+account+"\"");
		reportPrint.append(", simpleDateFormat.parse(\"").append(simpleDateFormat.format(getDate())).append("\")");
		reportPrint.append(", new BigDecimal(").append(getPrice()).append(")");
		reportPrint.append(", new BigDecimal(").append(getQuantity()).append(")");
		reportPrint.append(", Currency.").append(getCurrency());
		reportPrint.append("));\n");
		
		return reportPrint;
	}

	public String getAccount() {
		return account;
	}

	@SuppressWarnings("unused")
	private void setAccount(String accountName) {
		this.account = accountName;
	}

	@SuppressWarnings("unused")
	private void setSymbol(Stock stock) {
		this.stock = stock;
	}

	@SuppressWarnings("unused")
	private void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("unused")
	private void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@SuppressWarnings("unused")
	private void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Integer id) {
		this.id = id;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionElement other = (TransactionElement) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (currency != other.currency)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	public Currency getCurrency() {
		return currency;
	}

	//@SuppressWarnings("unused")
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	
	
}
