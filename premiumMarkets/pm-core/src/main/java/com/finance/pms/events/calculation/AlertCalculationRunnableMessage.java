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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.finance.pms.MainPMScmd;
import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
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

	public AlertCalculationRunnableMessage(SpringContext springContext, String analysisName, Date endDate, Portfolio... portfolios ) {
		super(1000, springContext, analysisName);
		this.endDate = endDate;
		this.startDate = endDate;
		this.portfolios = Arrays.asList(portfolios);

	}

	protected void analysePortfolioCollection(Date startDate, Date endDate, Collection<Portfolio> portfolios, Boolean keepCache) {

		for (final Portfolio portfolio : portfolios) {
			
			ExecutorService executor = Executors.newFixedThreadPool(new Integer(MainPMScmd.getPrefs().get("alertcalculator.semaphore.nbthread","5")));
			List<Future<SymbolEvents>> futures = new ArrayList<Future<SymbolEvents>>();
			
			boolean isUserPortfolio = portfolio instanceof UserPortfolio; 
			
			for (PortfolioShare portfolioShare : portfolio.getListShares().values() ) {

				try {
					
					LOGGER.guiInfo("Processing alerts events for portfolio "+portfolio.getName());

					final Queue eventQueue = this.eventQueue;
					final JmsTemplate jmsTemplate = this.jmsTemplate;
					
					//EventsResources.getInstance().cleanEventsForAnalysisName(portfolio.getName(), startDate, endDate, true, EventDefinition.alertsOnThresholds());
					
					Future<SymbolEvents> submitedRunnable = 
							(Future<SymbolEvents>) executor.submit(new AlertsCalculationThread(portfolioShare, startDate, endDate, portfolio.getName(), isUserPortfolio, keepCache, eventQueue, jmsTemplate));
					futures.add(submitedRunnable);

				} catch (Throwable e) {
					LOGGER.debug(e,e);
				}
			}
		
			executor.shutdown();
			try {
				boolean awaitTermination = executor.awaitTermination(2, TimeUnit.DAYS);
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
				EventsResources.getInstance().storeEvents(allEvents, true, portfolio.getName());
				
			} catch (InterruptedException e) {
				LOGGER.error(e,e);
			} catch (ExecutionException e) {
				LOGGER.error(e,e);
			}
			
		}
	}

	public void run() {
	
		try {
			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME,this.configs.get(Config.EVENT_SIGNAL_NAME));
			this.analysePortfolioCollection(startDate, endDate, portfolios, true);
			
		} catch (Exception e) {
			LOGGER.error("Error in "+this.toString(),e);
		} finally {
			synchronized (this.syncObject) {
				this.syncObject.notify();
			}
		}
	}
	
	public void runAlertsOnThresholdCalculation() throws InterruptedException {
		
		this.sendRunnableStartProcessingEvent(this.getAnalysisName(), this);
		synchronized (this.syncObject) {
			this.syncObject.wait();
		}
	}

	@Override
	public String toString() {
		return "AlertCalculationRunnableMessage [endDate=" + endDate + ", portfolios=" + portfolios + ", startDate=" + startDate + ", toString()=" + super.toString() + "]";
	}
	
	

}
