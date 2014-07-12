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
package com.finance.pms.screening;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NavigableSet;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.portfolio.IgnoredEventDateException;

public class ScreeningInfoExporter extends ScreeningSupplementStockExporter {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ScreeningInfoExporter.class);
	
	private Collection<Stock> shareListFilter;
	private Date eventDate;	
	
	public ScreeningInfoExporter(Collection<Stock> shareListFilter, Queue eventQueue, JmsTemplate jmsTemplate, String filextension, Date eventDate) {
		super(filextension,eventQueue,jmsTemplate);
		this.shareListFilter = shareListFilter;
		this.eventDate = eventDate;
	}

	@Override
	public void buildAndSendScreeningEvents(NavigableSet<ScreeningSupplementedStock> referenceShareList, String eventListName) {
		
		Collection<SymbolEvents> symbolEvents = new ArrayList<SymbolEvents>();
		Integer rank = 0;
		
		for (ScreeningSupplementedStock trendSupplementedStock : referenceShareList) {
				Stock stock = trendSupplementedStock.getStock();
				if (shareListFilter.contains(stock)) {
					try {
						Integer previousRank = extractPreviousRank(stock, eventListName, eventDate, EventType.INFO);
						if (previousRank != null) {
							if (previousRank > rank) {
								symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, previousRank, EventType.INFO, "Screener rank is Up", eventDate));
							} else if (previousRank < rank) {
								symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, previousRank, EventType.INFO, "Screener rank is Down", eventDate));
							} 
						} else {
							symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, null, EventType.INFO, "Screener rank Init", eventDate));
						}
					} catch (IgnoredEventDateException e) {
						LOGGER.info("Ignoring rank update for "+stock+" as already been calculated for the same date "+eventDate);
					}
				}
				rank++;
		}
		
		this.storeAndSendScreeningEvents(symbolEvents, EmailFilterEventSource.PMUserScreening, eventListName);

	}

}
