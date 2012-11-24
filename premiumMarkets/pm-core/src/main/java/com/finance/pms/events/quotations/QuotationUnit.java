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

package com.finance.pms.events.quotations;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class QuotationUnit.
 * 
 * @author Guillaume Thoreton
 */
@Entity
@Table(name="QUOTATIONS")
public class QuotationUnit implements Comparable<QuotationUnit>
{
    
    /** The date. */
    private Date date;
    
    /** The open. */
    private BigDecimal open;
    
    /** The high. */
    private BigDecimal high;
    
    /** The low. */
    private BigDecimal low;
    
    /** The close. */
    private BigDecimal close;
    
    /** The volume. */
    private long volume;
    
 
    /**
     * Instantiates a new quotation unit.
     * 
     * @param date the date
     * @param open the open
     * @param high the high
     * @param low the low
     * @param close the close
     * @param volume the volume
     * 
     * @author Guillaume Thoreton
     */
    public QuotationUnit(Date date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long volume)
    {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    /**
     * Gets the close.
     * 
     * @return the close
     */
    public BigDecimal getClose()
    {
        return close;
    }

    /**
     * Gets the date.
     * 
     * @return the date
     */
    @Id
    @Temporal(TemporalType.DATE)
    public Date getDate()
    {
        return date;
    }

    /**
     * Gets the high.
     * 
     * @return the high
     */
    public BigDecimal getHigh()
    {
        return high;
    }

    /**
     * Gets the low.
     * 
     * @return the low
     */
    public BigDecimal getLow()
    {
        return low;
    }

    /**
     * Gets the open.
     * 
     * @return the open
     */
    public BigDecimal getOpen()
    {
        return open;
    }

    /**
     * Gets the volume.
     * 
     * @return the volume
     */
    public long getVolume()
    {
        return volume;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(QuotationUnit o) {
		return this.date.compareTo(o.getDate());
	}

	@Override
	public String toString() {
		return "QuotationUnit [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", volume=" + volume + "]";
	}
	
	public QuotationUnit clone(Date newDate) {
		return new QuotationUnit(newDate,this.open,this.high,this.low,this.close, this.volume);
	}
    
	
    	
}
