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
import java.util.List;
import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.TransactionType;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.portfolio.AutoPortfolioWays;
import com.finance.pms.portfolio.CalcSignalRecord;
import com.finance.pms.portfolio.SignalHistory;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.threads.ConfigThreadLocal;

public class AutoPortfolioBuySellMessageRunnable extends AbstractAnalysisClientRunnableMessage {

	private static final long serialVersionUID = 7866255237284058407L;
	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolioBuySellMessageRunnable.class);

	private AutoPortfolioWays portfolio;

	private Date spanEnd;
	private EventInfo eventInfo;
	private List<SymbolEvents> reducedEvents;

	public AutoPortfolioBuySellMessageRunnable(
			AutoPortfolioWays portfolio, Date spanEnd, EventInfo eventInfo,
			List<SymbolEvents> reducedEvents) {
		super(5000, SpringContext.getSingleton(), portfolio.getName());
		this.portfolio = portfolio;
		this.spanEnd = spanEnd;
		this.eventInfo = eventInfo;
		this.reducedEvents = reducedEvents;
	}

	public void runBuyNSellCalculation() throws InterruptedException {
		if (this.portfolio.isAutoCalculationIdempotent()) {
			this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		} else {
			synchronized (syncObject) {
				this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
				if (LOGGER.isDebugEnabled()) LOGGER.debug(Thread.currentThread() + ": waiting on " + syncObject.hashCode());
				syncObject.wait();
			}
			if (LOGGER.isDebugEnabled()) LOGGER.debug(Thread.currentThread() + ": released " + syncObject.hashCode());
		}
	}

	public void run() {

		try {
			
			LOGGER.info("Processing events " + getAnalysisName() + " with " + reducedEvents.size() + " event set(s) up to " + spanEnd);
			if (reducedEvents.isEmpty()) return;

			for (String configName : getPassedThroughConfigs().keySet()) {
				ConfigThreadLocal.set(configName, getPassedThroughConfigs().get(configName));
			}

			SignalHistory calculationSignals = portfolio.calculate(reducedEvents, spanEnd);
			sendSignalsHistory(calculationSignals);

			LOGGER.info("Processing message completed: " + getAnalysisName() + " with " + reducedEvents.size() + " events set(s).");

		} catch (Exception e) {
			LOGGER.error("Error in " + this.toString(), e);
		} finally {
			if (LOGGER.isDebugEnabled()) LOGGER.debug(Thread.currentThread() + ": notifying " + syncObject.hashCode());
			synchronized (syncObject) {
				syncObject.notify();
			}
			if (LOGGER.isDebugEnabled()) LOGGER.debug(Thread.currentThread() + ": notified " + syncObject.hashCode());
		}

	}

	private void sendSignalsHistory(SignalHistory transactionHistory) {

		for (final CalcSignalRecord record : transactionHistory) {

			if (AnalysisClient.getEmailMsgQeueingFilter().contains(record.getSource())) {
				jmsTemplate.send(eventQueue, new MessageCreator() {

					public Message createMessage(Session session) throws JMSException {

						EventType eventType = EventType.INFO;
						EventInfo eventDef = EventDefinition.UNKNOWN99;
						if (record.getMovement().equals(TransactionType.AIN)) {
							eventType = EventType.BULLISH;
							Optional<EventInfo> buyEventDef = record.getEventList().getBuyTriggeringEvents().stream().findFirst();
							eventDef = buyEventDef.orElse(EventDefinition.UNKNOWN99);
						} 
						else if (record.getMovement().equals(TransactionType.AOUT)) {
							eventType = EventType.BEARISH;
							Optional<EventInfo> sellEventDef = record.getEventList().getSellTriggeringEvents().stream().findFirst();
							eventDef = sellEventDef.orElse(EventDefinition.UNKNOWN99);
						}

						String message = record.toString()+"\n\n";

						EventKey eventKey = new ParameterizedEventKey(record.getEventList().getLastDate(), eventDef, eventType);
						EventValue eventValue = new EventValue(record.getEventList().getLastDate(), eventDef, eventType, message, record.getPortfolioName());
						SingleEventMessage infoMessage = 
								new SingleEventMessage(record.getPortfolioName(), record.getPortfolioName(), record.getEventList().getStock(), record.getSignalDate(), eventKey, eventValue, ConfigThreadLocal.getAll());
						infoMessage.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), record.getSource()); //Source (event calculator)
						infoMessage.setObjectProperty(MessageProperties.TREND.getKey(), eventValue.getEventType().name()); //Bearish Bullish Other Info
						infoMessage.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
						infoMessage.setObjectProperty(MessageProperties.EVENT_INFO.getKey(), eventInfo.getEventReadableDef());

						return infoMessage;

					}
				});
			}
		}

	}


	@Override
	public String toString() {
		return this.getClass().getName() + " processing " + this.getAnalysisName();
	}

}
