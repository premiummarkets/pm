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
