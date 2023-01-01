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
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.threads.ConfigThreadLocal;

public class AlertsCalculationThread extends EventsCalculationThread {

	protected static MyLogger LOGGER = MyLogger.getLogger(AlertsCalculationThread.class);

	private PortfolioShare portfolioShare;
	private Boolean isUserPortfolio;

	protected AlertsCalculationThread(
			PortfolioShare portfolioShare, Date startDate, Date endDate, String eventListName, 
			Boolean isUserPortfolio, Queue eventQueue, JmsTemplate jmsTemplate) {

		super(startDate, endDate, eventListName, portfolioShare.getTransactionCurrency(), new HashSet<Observer>(), eventQueue, jmsTemplate);

		this.portfolioShare = portfolioShare;
		this.isUserPortfolio = isUserPortfolio;

	}


	public SymbolEvents call() {

		SymbolEvents ret = new SymbolEvents(portfolioShare.getStock());

		EventSignalConfig config = (EventSignalConfig) this.configs.get(Config.EVENT_SIGNAL_NAME);
		ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);

		try {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Analysing events for portfolio share " + portfolioShare + ", starting at " + startDate);

			AlertOnThresholdParser thresholdAlertIndicator =  new AlertOnThresholdParser(portfolioShare, (Observer[]) observers.toArray(new Observer[0]));

			try {
				cleanEventsFor(this.portfolioShare.getStock(), EventDefinition.ALERTTHRESHOLD, this.eventListName);
			} catch (Exception e) {
				LOGGER.error(e,e);
			}

			Quotations quotations = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(portfolioShare.getStock(), startDate, endDate, true, calculationCurrency, 1, ValidityFilter.CLOSE);
			LOGGER.info(
					"Alerts on threshold calculation span for "+portfolioShare.getStock()+": start "+startDate+", end "+endDate+"\n"+
					"Quotations idx : from "+quotations.getFirstDateShiftedIdx()+" to "+quotations.getLastDateIdx()+ " with size "+quotations.size() + "\n"+
					"quotations: from "+((quotations.size() > 0)?quotations.get(0):"NaN")+" to "+((quotations.size() > 0)?(quotations.get(quotations.size()-1)):"NaN"));
			
			SortedMap<EventKey, EventValue> calculatedEventsForCalculator = thresholdAlertIndicator.calculateEventsFor(quotations, this.eventListName);

			for (EventKey eventKey : calculatedEventsForCalculator.keySet()) {

				//Here eventListName must be = to portfolio name for alerts
				EmailFilterEventSource msalert = (isUserPortfolio)? EmailFilterEventSource.PMUserAlert : EmailFilterEventSource.PMAutoAlert;
				this.sendEvent(portfolioShare.getStock(), eventListName, eventKey, calculatedEventsForCalculator.get(eventKey), msalert, EventDefinition.ALERTTHRESHOLD);

			}

			ret.addEventResultElement(calculatedEventsForCalculator, EventDefinition.ALERTTHRESHOLD);
			if (LOGGER.isDebugEnabled()) LOGGER.debug("end analyse " + portfolioShare + " from " + startDate + " to " + endDate);

		} catch (Exception e) {
			// Oops
			LOGGER.error("ERROR : While calculating Alerts for " + portfolioShare, e);
		}

		return ret;
	}

	@Override
	public void cleanEventsFor(Stock stock, EventInfo eventDef, String eventListName) {
		//FIXME?
		//EventsResources.getInstance().crudDeleteEventsForStockNoTunedConfHandling(portfolioShare.getStock(), eventListName, datedeb, datefin, EventDefinition.alertsOnThresholds());
	}
}
