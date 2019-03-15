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

package com.finance.pms.events.quotations;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

/**
 * The Class QuotationUnit.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name="QUOTATIONS")
public class QuotationUnit implements Serializable, Comparable<QuotationUnit>
{

	private static final long serialVersionUID = -406044551517984882L;

	public enum ORIGIN {WEB, USER, DEL};

	private Stock stock;
	private Currency currency;
	private Date date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private Long volume;
	private ORIGIN origin;

	private BigDecimal split;

	//Hib
	@SuppressWarnings("unused")
	private QuotationUnit() {
		super();
	}

	public QuotationUnit(Stock stock, Currency currency, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Long volume, ORIGIN origin, BigDecimal split)
	{
		this.stock = stock;
		this.currency=currency;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.origin = origin;
		this.setSplit(split);
	}


	public Number getData(QuotationDataType field) {
		switch (field) {
		case OPEN:
			return getOpenSplit();
		case HIGH:
			return getHighSplit();
		case LOW:
			return getLowSplit();
		case CLOSE :
			return getCloseSplit();
		case VOLUME :
			return getVolume();
		default :
			throw new RuntimeException("Unknown quotation data type");
		}
	}

	@Id
	@Temporal(TemporalType.DATE)
	public Date getDate()
	{
		return date;
	}

	@ManyToOne
	@JoinColumns( { @JoinColumn(name = "isin", referencedColumnName = "isin"), @JoinColumn(name = "symbol", referencedColumnName = "symbol") })
	@Id
	public Stock getStock() {
		return stock;
	}

	@SuppressWarnings("unused")
	private void setStock(Stock stock) {
		this.stock = stock;
	}

	@Column(name="CLOSEVALUE")
	public BigDecimal getCloseRaw() {
		return close;
	}

	@Transient
	public BigDecimal getCloseSplit()
	{
		if (split.compareTo(BigDecimal.ONE) == 0) return getCloseRaw();
		return close.divide(split, 10, BigDecimal.ROUND_HALF_EVEN);
	}

	@Column(name="HIGH")
	public BigDecimal getHighRaw() {
		return high;
	}

	@Transient
	public BigDecimal getHighSplit()
	{
		if (split.compareTo(BigDecimal.ONE) == 0) return getHighRaw();
		return high.divide(split, 10, BigDecimal.ROUND_HALF_EVEN);
	}

	@Column(name="LOW")
	public BigDecimal getLowRaw() {
		return low;
	}

	@Transient
	public BigDecimal getLowSplit()
	{
		if (split.compareTo(BigDecimal.ONE) == 0) return getLowRaw();
		return low.divide(split, 10, BigDecimal.ROUND_HALF_EVEN);
	}

	@Column(name="OPENVALUE")
	public BigDecimal getOpenRaw() {
		return open;
	}

	@Transient
	public BigDecimal getOpenSplit()
	{
		if (split.compareTo(BigDecimal.ONE) == 0) return getOpenRaw();
		return open.divide(split, 10, BigDecimal.ROUND_HALF_EVEN);
	}

	public long getVolume()
	{
		return volume;
	}

	@Transient
	public BigDecimal getSplit() {
		return split;
	}

	public void setSplit(BigDecimal split) {
		this.split = split.setScale(10);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		final QuotationUnit other = (QuotationUnit) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (date.compareTo(other.date) != 0)
			return false;
		return true;
	}

	public int compareTo(QuotationUnit o) {
		return this.date.compareTo(o.getDate());
	}

	public QuotationUnit clone(Date newDate) {
		return new QuotationUnit(this.stock, this.currency, newDate, this.open, this.high, this.low, this.close, this.volume, this.origin, this.split);
	}

	@Id
	@Enumerated(EnumType.ORDINAL)
	public ORIGIN getOrigin() {
		return origin;
	}

	public void setOrigin(ORIGIN origin) {
		this.origin = origin;
	}


	@SuppressWarnings("unused")
	private void setDate(Date date) {
		this.date = date;
	}


	@SuppressWarnings("unused")
	private void setOpenRaw(BigDecimal open) {
		this.open = open;
	}


	@SuppressWarnings("unused")
	private void setHighRaw(BigDecimal high) {
		this.high = high;
	}


	@SuppressWarnings("unused")
	private void setLowRaw(BigDecimal low) {
		this.low = low;
	}


	@SuppressWarnings("unused")
	private void setCloseRaw(BigDecimal close) {
		this.close = close;
	}

	@SuppressWarnings("unused")
	private void setVolume(long volume) {
		this.volume = volume;
	}

	//XXX not used
	@Enumerated(EnumType.STRING)
	public Currency getCurrency() {
		return currency;
	}

	@SuppressWarnings("unused")
	private void setCurrency(Currency currency) {
		this.currency = currency;
	}


	@Override
	public String toString() {
		return "QuotationUnit [stock=" + stock.getFriendlyName() +
				", currency=" + currency + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close +
				", volume=" + volume + ", origin=" + origin + ", split=" + split + "]";
	}


}
