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
package com.finance.pms.alerts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.finance.pms.portfolio.PortfolioShare;


@Entity
@Table(name="ALERTS")
public class AlertOnThreshold implements Serializable {
	
	private static final long serialVersionUID = -4914585030980424597L;
	
	private PortfolioShare portfolioShare;
	private AlertOnThresholdType alertType;
	private ThresholdType thresholdType;
	private BigDecimal value;
	private String optionalMessage;
	

	@SuppressWarnings("unused")
	private AlertOnThreshold() {
		super();
	}

	public AlertOnThreshold(PortfolioShare portfolioShare, ThresholdType thresholdType, AlertOnThresholdType alertType, BigDecimal value, String optionalMessage) {
		
		if (value != null ) {
			this.value = value;
		} else {
			value = BigDecimal.ZERO.setScale(4);
		}
		this.optionalMessage = (optionalMessage == null)?alertType.getText():optionalMessage;
		this.alertType = alertType;
		this.thresholdType = thresholdType;
		this.portfolioShare = portfolioShare;
		
	}

	public AlertOnThreshold(AlertOnThreshold alert, PortfolioShare portfolioShare) {
		this.value = alert.value;
		this.optionalMessage = alert.optionalMessage;
		this.alertType = alert.alertType;
		this.thresholdType = alert.thresholdType;
		this.portfolioShare = portfolioShare;
	}

	public BigDecimal getValue() {
		return value;
	}

	@SuppressWarnings("unused")
	private void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getOptionalMessage() {
		return optionalMessage;
	}

	public void setOptionalMessage(String optionalMessage) {
		this.optionalMessage = optionalMessage;
	}
	
	@Id
	@ManyToOne
	@ForeignKey(name="FK_ALERTS_TO_PORTFOLIO")
	@JoinColumns({
		@JoinColumn(name="symbol", referencedColumnName="symbol" , nullable = false),
		@JoinColumn(name="isin", referencedColumnName="isin", nullable = false),
		@JoinColumn(name="name", referencedColumnName="name", nullable = false)
	})
	public PortfolioShare getPortfolioShare() {
		return portfolioShare;
	}
	
	@SuppressWarnings("unused")
	private void setPortfolioShare(PortfolioShare portfolioShare) {
		this.portfolioShare = portfolioShare;
	}

	@Override
	public String toString() {
		NumberFormat format = new DecimalFormat("#0.00");
		return format.format(this.value) + ", " + this.alertType.getText() + " " + this.optionalMessage;
	}
	
	@Id
	@Enumerated(EnumType.STRING)
	public AlertOnThresholdType getAlertType() {
		return alertType;
	}
	
	public void setAlertType(AlertOnThresholdType alertType) {
		this.alertType = alertType;
	}

	@Id
	@Enumerated(EnumType.STRING)
	public ThresholdType getThresholdType() {
		return thresholdType;
	}
	
	@SuppressWarnings("unused")
	private void setThresholdType(ThresholdType thresholdType) {
		this.thresholdType = thresholdType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertType == null) ? 0 : alertType.hashCode());
		result = prime * result + ((portfolioShare == null) ? 0 : portfolioShare.hashCode());
		result = prime * result + ((thresholdType == null) ? 0 : thresholdType.hashCode());
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
		AlertOnThreshold other = (AlertOnThreshold) obj;
		if (alertType != other.alertType)
			return false;
		if (portfolioShare == null) {
			if (other.portfolioShare != null)
				return false;
		} else if (!portfolioShare.equals(other.portfolioShare))
			return false;
		if (thresholdType != other.thresholdType)
			return false;
		return true;
	}
}
