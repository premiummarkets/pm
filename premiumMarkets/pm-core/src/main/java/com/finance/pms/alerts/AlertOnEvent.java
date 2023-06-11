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

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.finance.pms.portfolio.MonitorLevel;
import com.finance.pms.portfolio.PortfolioShare;

@Entity
@Table(name="ALERTONEVENT")
public class AlertOnEvent implements Serializable {

	private static final long serialVersionUID = -3484552244277984098L;
	
	
	private PortfolioShare portfolioShare;
	private String eventInfoReference;
	private MonitorLevel monitorLevel;
	private String optionalMessage;
	
	
	@SuppressWarnings("unused")
	private AlertOnEvent() {
		super();
	}


	public AlertOnEvent(PortfolioShare portfolioShare, String eventInfoReference, MonitorLevel monitorLevel, String optionalMessage) {
		super();
		this.portfolioShare = portfolioShare;
		this.eventInfoReference = eventInfoReference;
		this.monitorLevel = monitorLevel;
		this.optionalMessage = optionalMessage;
	}

	public AlertOnEvent(AlertOnEvent alert, PortfolioShare portfolioShare) {
		this(portfolioShare, alert);
	}


	public AlertOnEvent(PortfolioShare portfolioShare, AlertOnEvent alert) {
		this.eventInfoReference = alert.eventInfoReference;
		this.monitorLevel = alert.monitorLevel;
		this.optionalMessage = alert.optionalMessage;
		this.portfolioShare = portfolioShare;
	}

	@Id
	public String getEventInfoReference() {
		return eventInfoReference;
	}


	@SuppressWarnings("unused")
	private void setEventInfoReference(String eventInfoReference) {
		this.eventInfoReference = eventInfoReference;
	}

	@Id
	@Enumerated(EnumType.ORDINAL)
	public MonitorLevel getMonitorLevel() {
		return monitorLevel;
	}


	@SuppressWarnings("unused")
	private void setMonitorLevel(MonitorLevel eventType) {
		this.monitorLevel = eventType;
	}


	public String getOptionalMessage() {
		return optionalMessage;
	}


	@SuppressWarnings("unused")
	private void setOptionalMessage(String optionalMessage) {
		this.optionalMessage = optionalMessage;
	}

	@Id
	@ManyToOne
	@ForeignKey(name="FK_ALERTONEVENT_TO_PORTFOLIO")
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventInfoReference == null) ? 0 : eventInfoReference.hashCode());
		result = prime * result + ((monitorLevel == null) ? 0 : monitorLevel.hashCode());
		result = prime * result + ((portfolioShare == null) ? 0 : portfolioShare.hashCode());
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
		AlertOnEvent other = (AlertOnEvent) obj;
		if (eventInfoReference == null) {
			if (other.eventInfoReference != null)
				return false;
		} else if (!eventInfoReference.equals(other.eventInfoReference))
			return false;
		if (monitorLevel != other.monitorLevel)
			return false;
		if (portfolioShare == null) {
			if (other.portfolioShare != null)
				return false;
		} else if (!portfolioShare.equals(other.portfolioShare))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AlertOnEvent [portfolioShare=" + portfolioShare + ", eventInfoReference=" + eventInfoReference + ", monitorLevel=" + monitorLevel + ", optionalMessage=" + optionalMessage + "]";
	}
	
	
}
