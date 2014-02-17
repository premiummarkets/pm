/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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

	public enum ORIGIN {WEB,USER};
	
	private Stock stock;
	private Currency currency;
    private Date date;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private Long volume;
    private ORIGIN origin;
    
    
    //Hib
    @SuppressWarnings("unused")
	private QuotationUnit() {
		super();
	}


	public QuotationUnit(Stock stock, Currency currency, Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Long volume, ORIGIN origin)
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
    }
    
    
    public Number getData(QuotationDataType field) {
    	switch (field) {
		case OPEN:
				return getOpen();
		case HIGH:
			return getHigh();
		case LOW:
			return getLow();
		case CLOSE :
			return getClose();
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
    public BigDecimal getClose()
    {
        return close;
    }

    public BigDecimal getHigh()
    {
        return high;
    }

    public BigDecimal getLow()
    {
        return low;
    }

	@Column(name="OPENVALUE")
    public BigDecimal getOpen()
    {
        return open;
    }

    public long getVolume()
    {
        return volume;
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
		return new QuotationUnit(this.stock, this.currency, newDate, this.open, this.high, this.low, this.close, this.volume, this.origin);
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
	private void setOpen(BigDecimal open) {
		this.open = open;
	}


	@SuppressWarnings("unused")
	private void setHigh(BigDecimal high) {
		this.high = high;
	}


	@SuppressWarnings("unused")
	private void setLow(BigDecimal low) {
		this.low = low;
	}


	@SuppressWarnings("unused")
	private void setClose(BigDecimal close) {
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
		return "QuotationUnit [stock=" + stock.getFriendlyName() + ", currency=" + currency + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", origin=" + origin + "]";
	}
	
    	
}
