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
package com.finance.pms.events.scoring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.finance.pms.events.Validity;
import com.finance.pms.events.calculation.DateFactory;

public class PeriodRatingDTO implements Serializable, Comparable<PeriodRatingDTO> {

	private static final long serialVersionUID = 6439016703303157679L;

	Date from;
	Date to;
	Double priceAtFrom;
	Double priceAtTo;

	String trend;

	Validity rating;
	List<String> configs;

	boolean realised;

	public PeriodRatingDTO() {
		super();
		this.configs = new ArrayList<String>();
		this.realised = false;
	}

	public PeriodRatingDTO(Date from, Double priceAtFrom, String trend) {

		this.configs = new ArrayList<String>();
		this.realised = false;

		this.from = from;
		this.priceAtFrom = priceAtFrom;
		this.trend = trend;

	}

	public PeriodRatingDTO(Date from, Double priceAtFrom, Validity validity, String trend) {

		this.configs = new ArrayList<String>();
		this.realised = false;

		this.from = from;
		this.priceAtFrom = priceAtFrom;
		this.rating = validity;
		this.trend = trend;

	}

	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		if (from.after(to)) throw new IllegalArgumentException("From : "+from+" must be before or equal To : "+to);
		this.to = to;
	}
	public String getTrend() {
		return trend;
	}
	public void setTrend(String trend) {
		this.trend = trend;
	}

	public List<String> getConfigs() {
		return configs;
	}
	public String getConfigsToString() {
		return this.getConfigs().toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
	}

	public Long getPeriodLenght() {
		long OneDayMilliSeconds = DateFactory.DAYINMILLI;
		return  (to.getTime() - from.getTime())/OneDayMilliSeconds;
	}

	public void addConfig(String cfg) {
		this.configs.add(cfg);
	}

	@Override
	public int compareTo(PeriodRatingDTO o) {
		return from.compareTo(o.from);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		PeriodRatingDTO other = (PeriodRatingDTO) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (from.compareTo(other.from) != 0)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (to.compareTo(other.to) != 0)
			return false;
		return true;
	}

	public Validity getRating() {
		return rating;
	}

	public boolean isRealised() {
		return realised;
	}

	public void setRealised(boolean realised) {
		this.realised = realised;
	}

	public Double getPriceAtFrom() {
		return priceAtFrom;
	}

	public Double getPriceAtTo() {
		return priceAtTo;
	}

	public void setPriceAtTo(Double priceAtTo) {
		this.priceAtTo = priceAtTo;
	}

	private Double getPriceChange() {
		return priceAtTo - priceAtFrom;
	}

	public Double getPriceRateOfChange() {
		try {
			return getPriceChange()/priceAtFrom;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	public String toCSV() {
		return from + "," + priceAtFrom + "," + to + "," + priceAtTo + "," + getPriceRateOfChange();
	}

	@Override
	public String toString() {
		return "PeriodRatingDTO [from=" + from + ", to=" + to + ", priceAtFrom=" + priceAtFrom+ ", priceAtTo=" + priceAtTo + ", trend=" + trend + ", rating=" + rating + ", configs="+ configs + ", realised=" + realised + "]";
	}

}
