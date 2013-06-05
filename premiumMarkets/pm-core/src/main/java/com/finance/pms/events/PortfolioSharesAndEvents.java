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