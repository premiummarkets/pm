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
package com.finance.pms.events.scoring;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;


@Entity
@Table(name="TUNEDCONF")
/**
 * Monitor the storage status of the event
 * - dirty(staled): the decrepency of the stored events V the operation
 * - lastCalculationStart(fisrtStoredEventCalculationStart): first stored event calculation start
 * - lastCalculationEnd(lastStoredEventCalculationEnd): last stored event calculation end
 * @author guil
 *
 */
public class TunedConf {

	static MyLogger LOGGER = MyLogger.getLogger(TunedConf.class);

	private TunedConfId tunedConfId;

	private Boolean isRemovable;
	private Date fisrtStoredEventCalculationStart; //fisrtStoredEventCalculationStart
	private Date lastStoredEventCalculationEnd; //lastStoredEventCalculationEnd

	@SuppressWarnings("unused")
	private TunedConf() {
		super();
		// Hib
	}

	public TunedConf(Stock stock, String configFile, String eventDefinition, Boolean isRemovable) {
		super();
		this.tunedConfId = new TunedConfId(stock, configFile, eventDefinition);
		this.isRemovable = isRemovable;
		this.fisrtStoredEventCalculationStart = DateFactory.dateAtZero();
		this.lastStoredEventCalculationEnd = DateFactory.dateAtZero();
	}

	@Temporal(TemporalType.DATE)
	@Column(name="LASTCALCULATIONSTART")
	public Date getFisrtStoredEventCalculationStart() {
		return fisrtStoredEventCalculationStart;
	}

	public void setFisrtStoredEventCalculationStart(Date lastCalculationStart) {
		this.fisrtStoredEventCalculationStart = lastCalculationStart;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="LASTCALCULATIONEND")
	public Date getLastStoredEventCalculationEnd() {
		return lastStoredEventCalculationEnd;
	}

	public void setLastStoredEventCalculationEnd(Date lastCalculationEnd) {
		this.lastStoredEventCalculationEnd = lastCalculationEnd;
	}

	@Override
	public String toString() {
		return "TunedConf [tunedConfId=" + tunedConfId + ", lastCalculationStart=" + fisrtStoredEventCalculationStart + ", lastCalculationEnd=" + lastStoredEventCalculationEnd + "]";
	}

	@EmbeddedId
	public TunedConfId getTunedConfId() {
		return tunedConfId;
	}

	public void setTunedConfId(TunedConfId tunedConfId) {
		this.tunedConfId = tunedConfId;
	}

	@Column(name="DIRTY")
	public Boolean getIsRemovable() {
		return isRemovable;
	}

	//Hib
	public void setIsRemovable(Boolean dirty) {
		this.isRemovable = dirty;
	}
	
	@Transient
	public Boolean isEmpty() {
		return DateFactory.dateAtZero().equals(lastStoredEventCalculationEnd);
	}

}
