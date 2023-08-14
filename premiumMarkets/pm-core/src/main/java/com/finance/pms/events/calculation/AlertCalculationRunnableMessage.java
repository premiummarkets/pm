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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.UserPortfolio;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.threads.ConfigThreadLocal;

public class AlertCalculationRunnableMessage extends AbstractAnalysisClientRunnableMessage {

	private static final long serialVersionUID = 5557078167196323845L;

	private static MyLogger LOGGER = MyLogger.getLogger(AlertCalculationRunnableMessage.class);
	private Date endDate;
	private Date startDate;
	private Collection<Portfolio> portfolios;
	
	/**
	 * Attention the events are generated against the porfolio name, not the analysis name
	 * @param springContext
	 * @param analysisName
	 * @param endDate
	 * @param portfolios
	 */
	public AlertCalculationRunnableMessage(SpringContext springContext, String analysisName, Date endDate, Portfolio... portfolios ) {
		super(1000, springContext, analysisName);
		this.endDate = endDate;
		Calendar instance = Calendar.getInstance();
		instance.setTime(endDate);
		instance.add(Calendar.DAY_OF_MONTH, -1);
		this.startDate = instance.getTime();
		this.portfolios = Arrays.asList(portfolios);

	}

	protected void analysePortfolioCollection(Date startDate, Date endDate, Collection<Portfolio> portfolios) {

		for (final Portfolio portfolio : portfolios) {

			ExecutorService executor = CalculateThreadExecutor.getExecutorInstance();
			List<Future<SymbolEvents>> futures = new ArrayList<Future<SymbolEvents>>();

			boolean isUserPortfolio = portfolio instanceof UserPortfolio; 

			for (PortfolioShare portfolioShare : portfolio.getListShares().values() ) {

				try {

					LOGGER.guiInfo("Processing alerts events for portfolio: " + portfolio.getName() + ", and share: " + portfolioShare);

					final Queue eventQueue = this.eventQueue;
					final JmsTemplate jmsTemplate = this.jmsTemplate;

					Future<SymbolEvents> submitedRunnable = 
							(Future<SymbolEvents>) executor.submit(new AlertsCalculationThread(portfolioShare, startDate, endDate, portfolio.getName(), isUserPortfolio, eventQueue, jmsTemplate));
					futures.add(submitedRunnable);

				} catch (Throwable e) {
					if (LOGGER.isDebugEnabled()) LOGGER.debug(e,e);
				}
			}

			executor.shutdown();
			try {
				boolean awaitTermination = executor.awaitTermination(1, TimeUnit.HOURS);
				if (!awaitTermination) {
					List<Runnable> shutdownNow = executor.shutdownNow();
					LOGGER.error(shutdownNow,new Exception());
				}
			} catch (InterruptedException e) {
				List<Runnable> shutdownNow = executor.shutdownNow();
				LOGGER.error(shutdownNow,e);
			}

			try {
				List<SymbolEvents> allEvents = new ArrayList<SymbolEvents>();
				for (Future<SymbolEvents> future : futures) {
					allEvents.add(future.get());
				}
				EventsResources.getInstance().crudCreateEvents(allEvents, portfolio.getName());

			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			} catch (ExecutionException e) {
				LOGGER.error(e,e);
			}

		}
	}

	public void run() {
		try {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, getConfigs().get(Config.EVENT_SIGNAL_NAME));
			this.analysePortfolioCollection(startDate, endDate, portfolios);

		} catch (Exception e) {
			LOGGER.error("Error in " + this.toString(), e);
		} finally {
			synchronized (this.syncObject) {
				this.syncObject.notify();
			}
		}
	}

	public void runAlertsOnThresholdCalculation() throws InterruptedException {
		synchronized (this.syncObject) {
			this.sendRunnableStartProcessingEvent(this.getAnalysisName(), this);
			this.syncObject.wait();
		}
	}

	@Override
	public String toString() {
		return "AlertCalculationRunnableMessage [endDate=" + endDate + ", portfolios=" + portfolios + ", startDate=" + startDate + ", toString()=" + super.toString() + "]";
	}

}
