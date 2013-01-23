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
package com.finance.pms.events.calculation;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.ThresholdAlertParser;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventSource;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class AlertsCalculationThread extends EventsCalculationThread {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(AlertsCalculationThread.class);
	
	private PortfolioShare portfolioShare;
	private Boolean isUserPortfolio;
	
	protected AlertsCalculationThread(
			PortfolioShare portfolioShare, Date startDate, Date endDate, String eventListName, 
			Boolean keepCache, Boolean isUserPortfolio, Queue eventQueue, JmsTemplate jmsTemplate) {
		
		super(startDate, endDate, eventListName, portfolioShare.getTransactionCurrency(), new HashSet<Observer>(), keepCache, eventQueue, jmsTemplate);
		
		this.portfolioShare = portfolioShare;
		this.isUserPortfolio = isUserPortfolio;
		
	}


	public SymbolEvents call() {
		
		SymbolEvents ret = new SymbolEvents(portfolioShare.getStock());
		
		ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, this.configs.get(Config.EVENT_SIGNAL_NAME));
		
		try {
				LOGGER.debug("Analysing events for portfolio share "+portfolioShare+", starting at "+startDate);
				
				ThresholdAlertParser thresholdAlertIndicator =  new ThresholdAlertParser(portfolioShare, startDate, endDate, calculationCurrency);
				Set<EventCompostionCalculator> evtCalculators = new HashSet<EventCompostionCalculator>();
				evtCalculators.add(thresholdAlertIndicator);
				Map<EventKey, EventValue> eventDataAggregation = calculateEventsForEachDateAndIndicatorComp(evtCalculators, startDate, endDate, true);
				
				for (EventValue eventValue : eventDataAggregation.values()) {
					//Here eventListName must be = to portfolio name for alerts
					EventSource msalert = (isUserPortfolio)?EventSource.PMUserAlert:EventSource.PMAlert;
					this.sendEvent(portfolioShare.getStock(), eventListName, eventValue, msalert);
				}
				
				ret.addEventResultElement(eventDataAggregation, EventDefinition.getEventDefList());
				LOGGER.debug("end analyse " + portfolioShare + " from " + startDate + " to " + endDate);
			
		} catch (Exception e) {
			// Oops
			LOGGER.error("ERROR : While calcuting Events for " + portfolioShare,e);
		}
		
		return ret;
	}
	
	@Override
	public void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist) {
		EventsResources.getInstance().crudDeleteEventsForStock(portfolioShare.getStock(), eventListName, datedeb, datefin, true, EventDefinition.alertsOnThresholds());
	}
}
