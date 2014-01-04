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
package com.finance.pms.screening;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.NavigableSet;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.portfolio.IgnoredEventDateException;

public class ScreeningSupplementExporter extends ScreeningSupplementStockExporter {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ScreeningSupplementExporter.class);
	
	public static final int TOP_RANK = 20;
	private Date endDate;

	public ScreeningSupplementExporter(String fileExtention, Queue eventQueue, JmsTemplate jmsTemplate, Date endDate) {
		super(fileExtention,eventQueue,jmsTemplate);
		this.endDate = endDate;
	}

	@Override
	public void buildAndSendScreeningEvents(NavigableSet<ScreeningSupplementedStock> screenedStocks, String eventListName) {
		this.storeAndSendScreeningEvents(constructBuySellEvents(screenedStocks, eventListName, endDate), EmailFilterEventSource.PMAutoScreening, eventListName);
	}

	/**
	 * @param screened
	 * @param eventListName
	 * @param endDate 
	 * @return 
	 */
	private Collection<SymbolEvents> constructBuySellEvents(NavigableSet<ScreeningSupplementedStock> screened, String eventListName, Date endDate) {

		Collection<SymbolEvents> screeningEvents = new ArrayList<SymbolEvents>();
		
		Iterator<ScreeningSupplementedStock> screenedIterator = screened.iterator();
		for (int rank=0; screenedIterator.hasNext(); ) {
			ScreeningSupplementedStock screenedStock = screenedIterator.next();
			try {
				Integer previousRank = extractPreviousRank(screenedStock.getStock(), eventListName, endDate, EventType.BULLISH, EventType.NONE, EventType.BEARISH);
				//In top : NONE , Out top : BEARISH, change from out to in : BULLISH, change from in to out : BEARISH
				if (previousRank == null) {
					if (rank <= TOP_RANK) {
						screeningEvents.add(constructEvent(eventListName, screenedStock, EventDefinition.SCREENER, rank, null, EventType.NONE, "Screener rank is in top "+TOP_RANK, endDate));
					}
					if (rank > TOP_RANK) {
						screeningEvents.add(constructEvent(eventListName, screenedStock, EventDefinition.SCREENER, rank, null, EventType.BEARISH, "Screener rank is not in top "+TOP_RANK, endDate));
					}
				} else {
					if (rank <= TOP_RANK && previousRank > TOP_RANK) {
						screeningEvents.add(constructEvent(eventListName, screenedStock, EventDefinition.SCREENER, rank, previousRank, EventType.BULLISH, "Screener rank just got in top "+TOP_RANK, endDate));
					} 
					if (rank <= TOP_RANK && previousRank <= TOP_RANK) {
						screeningEvents.add(constructEvent(eventListName, screenedStock, EventDefinition.SCREENER, rank, previousRank, EventType.NONE, "Screener rank still in top "+TOP_RANK, endDate));
					}
					if (rank > TOP_RANK && previousRank <= TOP_RANK) {
						screeningEvents.add(constructEvent(eventListName, screenedStock, EventDefinition.SCREENER, rank, previousRank, EventType.BEARISH, "Screener rank is got out of top "+TOP_RANK, endDate));
					}
				}
				
			} catch (IgnoredEventDateException e) {
				LOGGER.info("Ignoring rank update for "+screenedStock.getStock()+" as already been calculated for the same date "+endDate);
			}
			rank++;
		}

		return screeningEvents;

	}


}
