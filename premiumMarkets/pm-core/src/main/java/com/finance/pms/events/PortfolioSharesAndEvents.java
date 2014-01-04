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
package com.finance.pms.events;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.finance.pms.portfolio.PortfolioShare;

public class PortfolioSharesAndEvents {
	
	SortedSet<PortfolioShare> portfolioShares;
	private SortedMap<EventInfo,List<EventValue>> events;
	
	public PortfolioSharesAndEvents() {
		super();
		this.events = new TreeMap<EventInfo, List<EventValue>>();
		this.portfolioShares = new TreeSet<PortfolioShare>();
	}

	public SortedMap<EventInfo, List<EventValue>> getEvents() {
		return events;
	}

	public void add(PortfolioShare portfolioShare) {
		this.portfolioShares.add(portfolioShare);
	}

	public SortedSet<PortfolioShare> getPortfolioShares() {
		return portfolioShares;
	}
}