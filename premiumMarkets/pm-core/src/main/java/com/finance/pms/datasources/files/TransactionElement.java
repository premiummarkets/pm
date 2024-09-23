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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.portfolio.Portfolio;

@Entity
@Table(name="TRANSACTIONS")
public class TransactionElement implements Comparable<TransactionElement>, Serializable {
	
	private static final long serialVersionUID = -257553176773712060L;
	
	
	private String id;
	private Stock stock;
	
	private Date date;
	private BigDecimal quantity;
	private BigDecimal price;
	private Currency currency;
	
	private Portfolio portfolio;
	private String externalAccount;

	
	@SuppressWarnings("unused")
	private TransactionElement() {
		//Hib
	}
	
	public TransactionElement(
			@NotNull Stock stock, 
			Portfolio portfolio, String externalAccount, 
			@NotNull Date date, @NotNull BigDecimal price, @NotNull BigDecimal quantity, Currency currency) {
		super();
		this.id = UUID.randomUUID().toString();
		this.stock = stock;
		this.portfolio = portfolio;
		this.externalAccount = externalAccount;
		this.date = date;
		this.price = price;
		this.quantity = quantity;
		this.currency = currency;
	}

	public TransactionElement(Portfolio portfolio, TransactionElement transactionElement) {
		this.id = UUID.randomUUID().toString();
		this.stock = transactionElement.getStock();
		this.date = transactionElement.getDate();
		this.quantity = transactionElement.getQuantity();
		this.price = transactionElement.getPrice();
		this.currency = transactionElement.getCurrency();
		this.portfolio = portfolio;
		this.externalAccount = transactionElement.getExternalAccount();
	}

	@Override
	public String toString() {
		String portfolioName = (portfolio != null)? portfolio.getName() : null; //FIXME how can the portfolio name be null or is it for Hib reflection compat??
		return "TransactionElement [id=" + id + ", symbol=" + stock + ", accountName=" + externalAccount + ", portfolio=" + portfolioName + ", date=" + date + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	public String toChart() {
		return  date + " / amt " + quantity.multiply(price).setScale(2, RoundingMode.HALF_EVEN);
	}

	public int compareTo(TransactionElement o) {
		return new TransactionComparator().compare(this, o);
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumns({ @JoinColumn(name = "isin", referencedColumnName = "isin"), @JoinColumn(name = "symbol", referencedColumnName = "symbol") })
	public Stock getStock() {
		return stock;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public StringBuffer printTestElement() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
		StringBuffer reportPrint = new StringBuffer();
		reportPrint.append("elements.add(new TransactionElement(stock").append(", \"" + externalAccount + "\"");
		reportPrint.append(", simpleDateFormat.parse(\"").append(simpleDateFormat.format(getDate())).append("\")");
		reportPrint.append(", new BigDecimal(").append(getPrice()).append(")");
		reportPrint.append(", new BigDecimal(").append(getQuantity()).append(")");
		reportPrint.append(", Currency.").append(getCurrency());
		reportPrint.append("));\n");
		
		return reportPrint;
	}

	public String getExternalAccount() {
		return externalAccount;
	}

	@SuppressWarnings("unused")
	private void setExternalAccount(String accountName) {
		this.externalAccount = accountName;
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

	//@Id  @GeneratedValue
	@Id
	public String getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(String id) {
		this.id = id;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Enumerated(EnumType.STRING)
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@ManyToOne
	@ForeignKey(name="FK_TRANSACTION_TO_PORTFOLIO_NAME")
	@JoinColumn(name = "portfolio")
	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
	public TransactionType transactionType() {
		
		if (this.getQuantity().compareTo(BigDecimal.ZERO) > 0 && this.getPrice().compareTo(BigDecimal.ZERO) != 0 ) {//buy and not split
			return TransactionType.AIN;
		} 
		else if (this.getQuantity().compareTo(BigDecimal.ZERO) < 0 && this.getPrice().compareTo(BigDecimal.ZERO) != 0) {//sell and not split
			return TransactionType.AOUT;
		} 
		else {
			return TransactionType.NULL;
		}
		
	}

	
}
