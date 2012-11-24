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
package com.finance.pms.alerts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import com.finance.pms.portfolio.PortfolioShare;


//@Entity
//@Table(name="ALERTS")
public class AlertsSet_old implements Iterable<Alert> {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -398427323458559295L;
	
	private Long id;
	
	private Set<Alert> alerts;
	private ThresholdType thresholdType;
	private PortfolioShare portfolioShare;

	@SuppressWarnings("unused")
	private AlertsSet_old() {
		super();
		this.alerts = new HashSet<Alert>();
		//hib requirement?
	}
	
	
	
	public AlertsSet_old(ThresholdType thresholdType, PortfolioShare portfolioShare) {
		super();
		this.thresholdType = thresholdType;
		this.portfolioShare = portfolioShare;
		this.alerts = new HashSet<Alert>();
	}
	
	public AlertsSet_old(AlertsSet_old alertsSet) {
		this.id = alertsSet.id;
		this.alerts = new HashSet<Alert>(alertsSet.getAlerts());
		this.portfolioShare = alertsSet.portfolioShare;
		this.thresholdType = alertsSet.thresholdType;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	

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

	@Enumerated(EnumType.STRING)
	public ThresholdType getThresholdType() {
		return thresholdType;
	}
	@SuppressWarnings("unused")
	private void setThresholdType(ThresholdType thresholdType) {
		this.thresholdType = thresholdType;
	}

	@OneToMany(mappedBy="alertsSet",fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true)
	@Fetch(FetchMode.SELECT)
	public Set<Alert> getAlerts(){
		return this.alerts;
	}
	
	@SuppressWarnings("unused")
	private void setAlerts(Set<Alert> alerts) {
		this.alerts = alerts;
	}

	public boolean add(Alert e) {
		if (null == alerts) return false;
		//e.setAlertsSet(this);
		return alerts.add(e);
	}

	public boolean addAll(HashSet<Alert> hashSet) {
		return alerts.addAll(hashSet);
	}
	
	public boolean removeAll(HashSet<Alert> alerts) {
		return alerts.removeAll(alerts);
	}

	public boolean remove(Object o) {
		return alerts.remove(o);
	}

	public int size() {
		return alerts.size();
	}
	
	public void clear() {
		alerts.clear();
	}

	public Iterator<Alert> iterator() {
		return alerts.iterator();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final AlertsSet_old other = (AlertsSet_old) obj;
		if (portfolioShare == null) {
			if (other.portfolioShare != null)
				return false;
		} else if (!portfolioShare.equals(other.portfolioShare))
			return false;
		if (thresholdType == null) {
			if (other.thresholdType != null)
				return false;
		} else if (!thresholdType.equals(other.thresholdType))
			return false;
		return true;
	}



	@Override
	public String toString() {
		
		
		StringBuilder builder = new StringBuilder("AlerstSet of id "+id+", type "+thresholdType+", portfolio "+portfolioShare+" [ ");
		if (alerts != null) {
			for (Alert alert:alerts) {
				builder.append(alert+" ");
			}
		}
		builder.append("]");
		return builder.toString();
	}



	public HashSet<Alert> getAlertsForType(AlertType alertType) {
		HashSet<Alert> ret = new HashSet<Alert>();
		for (Alert alert : this.alerts) {
			if (alertType.equals(alert.getAlertType())) {
				ret.add(alert);
			}
		}
		return ret;		
		
	}


	public Boolean removeType(AlertType alertType) {
		HashSet<Alert> toBeRemoved = this.getAlertsForType(alertType);
		return this.alerts.removeAll(toBeRemoved);
	}	
	
}
