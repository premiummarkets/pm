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
package com.finance.pms.events.calculation;

import java.util.Date;
import java.util.HashSet;
import java.util.Observer;
import java.util.SortedMap;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.alerts.AlertOnThresholdParser;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
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
		
		EventSignalConfig config = (EventSignalConfig) this.configs.get(Config.EVENT_SIGNAL_NAME);
		ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);
		
		try {
				LOGGER.debug("Analysing events for portfolio share "+portfolioShare+", starting at "+startDate);
				
				AlertOnThresholdParser thresholdAlertIndicator =  new AlertOnThresholdParser(portfolioShare, startDate, endDate, calculationCurrency);

				try {
					cleanEventsFor(this.eventListName, startDate, endDate, true);
				} catch (Exception e) {
					LOGGER.error(e,e);
				}

				SortedMap<EventKey, EventValue> calculatedEventsForCalculator = thresholdAlertIndicator.calculateEventsFor(this.eventListName);

				for (EventValue eventValue : calculatedEventsForCalculator.values()) {
					
					//Here eventListName must be = to portfolio name for alerts
					EmailFilterEventSource msalert = (isUserPortfolio)? EmailFilterEventSource.PMUserAlert : EmailFilterEventSource.PMAutoAlert;
					this.sendEvent(portfolioShare.getStock(), eventListName, eventValue, msalert, EventDefinition.ALERTTHRESHOLD);
					
				}

				ret.addEventResultElement(calculatedEventsForCalculator, EventDefinition.ALERTTHRESHOLD);
				LOGGER.debug("end analyse " + portfolioShare + " from " + startDate + " to " + endDate);
				
			
				
		} catch (Exception e) {
			// Oops
			LOGGER.error("ERROR : While calculating Events for " + portfolioShare,e);
		}
		
		return ret;
	}
	
	@Override
	public void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist) {
		EventsResources.getInstance().crudDeleteEventsForStock(portfolioShare.getStock(), eventListName, datedeb, datefin, true, EventDefinition.alertsOnThresholds());
	}
}
