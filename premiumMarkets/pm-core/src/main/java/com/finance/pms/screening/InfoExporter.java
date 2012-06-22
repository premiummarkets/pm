/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.screening;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.NavigableSet;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventSource;
import com.finance.pms.events.EventType;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.portfolio.IgnoredEventDateException;

public class InfoExporter extends TrendSupplementStockExporter {
	
	private static MyLogger LOGGER = MyLogger.getLogger(InfoExporter.class);
	
	private Collection<Stock> userPortfolioShareList;
	private Date eventDate;	
	
	public InfoExporter(Collection<Stock> userPortfolioShareList, Queue eventQueue, JmsTemplate jmsTemplate, String filextension, Date eventDate) {
		super(filextension,eventQueue,jmsTemplate);
		this.userPortfolioShareList = userPortfolioShareList;
		this.eventDate = eventDate;
	}

	@Override
	public void buildAndSendScreeningEvents(NavigableSet<TrendSupplementedStock> screened, String eventListName) {
		Collection<SymbolEvents> symbolEvents = new ArrayList<SymbolEvents>();
		Integer rank = 0;
		for (TrendSupplementedStock trendSupplementedStock : screened) {
				Stock stock = trendSupplementedStock.getStock();
				if (userPortfolioShareList.contains(stock)) {
					try {
						Integer previousRank = extractPreviousRank(stock, eventListName, eventDate, EventType.INFO);
						if (previousRank != null) {
							if (previousRank > rank) {
								symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, previousRank, EventType.INFO, "Screener rank is Up", eventDate));
							} else if (previousRank < rank) {
								symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, previousRank, EventType.INFO, "Screener rank is Down", eventDate));
							} 
						}	else {
							symbolEvents.add(constructEvent(eventListName, trendSupplementedStock, EventDefinition.SCREENER, rank, null, EventType.INFO, "Screener rank Init", eventDate));
						}
					} catch (IgnoredEventDateException e) {
						LOGGER.info("Ignoring rank update for "+stock+" as already been calculated for the same date "+eventDate);
					}
				}
				rank++;
		}
		this.sendScreeningEvents(symbolEvents,EventSource.PMUserScreening, eventListName);

	}

}
