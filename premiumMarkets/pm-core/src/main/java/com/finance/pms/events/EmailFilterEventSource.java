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
package com.finance.pms.events;

import java.util.EnumSet;

public enum EmailFilterEventSource {
	PMTAEvents ("Events from PM first and second pass indicators", true, false, false), 
	PMAutoScreening ("Screening - screening is not an indicator - events (buy and sell) from AutotPortfolio post processing", false, false, false),
	PMUserScreening ("Screening events (rank) from user portfolios post processing", true, true, false),
	PMAutoAlert ("Alerts on threshold  on UserPortfolio", false, false, false),
	PMUserAlert ("Alerts on threshold on UserPortfolio", true, true , false),
	PMAutoBuySell ("Buy and sell Signals AutotPortfolio post processing", false, false, false),
	PMUserBuySell ("Buy and sell Signals UserPortfolio post processing", true, false, false),
	PMGlobalBuySell ("Global events not related to a particular stock", false, true, false), 
	Metrics ("Metrics", false, true, true),
	Summary ("Summary messages", false, true, true);
	
	String description;
	Boolean uiEvent;
	Boolean cmdLineEvent;
	Boolean webEvent;
	
	private EmailFilterEventSource(String description, Boolean uiEvent, Boolean cmdLineEvent, Boolean webEvent) {
		this.description=description;
		this.uiEvent = uiEvent;
		this.cmdLineEvent = cmdLineEvent;
		this.webEvent = webEvent;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getCmdLineEvent() {
		return cmdLineEvent;
	}
	
	public Boolean getUiEvent() {
		return uiEvent;
	}
	
	public Boolean getWebEvent() {
		return webEvent;
	}
	
	public static EnumSet<EmailFilterEventSource> uiSet() {
		EnumSet<EmailFilterEventSource> ret = EnumSet.noneOf(EmailFilterEventSource.class);
		for (EmailFilterEventSource efes : EmailFilterEventSource.values()) {
			if (efes.getUiEvent()) ret.add(efes);
		}
		return ret;
	}
	
	public static EnumSet<EmailFilterEventSource> cmdSet() {
		EnumSet<EmailFilterEventSource> ret = EnumSet.noneOf(EmailFilterEventSource.class);
		for (EmailFilterEventSource efes : EmailFilterEventSource.values()) {
			if (efes.getCmdLineEvent()) ret.add(efes);
		}
		return ret;
	}
	
	public static EnumSet<EmailFilterEventSource> webSet() {
		return EnumSet.noneOf(EmailFilterEventSource.class);
	}
		
}
