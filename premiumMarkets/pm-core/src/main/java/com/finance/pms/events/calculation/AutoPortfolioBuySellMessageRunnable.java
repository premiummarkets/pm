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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.AutoPortfolioDelegate.BuyStrategy;
import com.finance.pms.portfolio.AutoPortfolioWays;
import com.finance.pms.portfolio.TransactionHistory;
import com.finance.pms.portfolio.TransactionRecord;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.threads.ConfigThreadLocal;

public class AutoPortfolioBuySellMessageRunnable extends AbstractAnalysisClientRunnableMessage {

	private static final long serialVersionUID = 7866255237284058407L;
	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolioBuySellMessageRunnable.class);

	private AutoPortfolioWays portfolio;

	private Date spanEnd;
	private String[] additionalEventListNames;
	private EventInfo eventInfo;
	private List<SymbolEvents> reducedEvents;

	private PonderationRule buyPonderationRule;
	private PonderationRule sellPonderationRule;
	private BuyStrategy buyStrategy;


	public AutoPortfolioBuySellMessageRunnable(
			AutoPortfolioWays portfolio, Date spanEnd, EventInfo eventInfo, 
			BuyStrategy buyStrategy, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, 
			List<SymbolEvents> reducedEvents, String... eventListName) {
		super(5000, SpringContext.getSingleton(), portfolio.getName());
		this.portfolio = portfolio;
		this.spanEnd = spanEnd;
		this.eventInfo = eventInfo;
		this.buyStrategy = buyStrategy;
		this.buyPonderationRule = buyPonderationRule;
		this.sellPonderationRule = sellPonderationRule;
		this.additionalEventListNames = eventListName;
		this.reducedEvents = reducedEvents;
	}

	public void runBuyNSellCalculation() throws InterruptedException {
		if (this.portfolio.isAutoCalculationIdempotent()) {
			this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
		} else {
			synchronized (syncObject) {
				this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
				LOGGER.info(Thread.currentThread() + ": waiting on " + syncObject.hashCode());
				syncObject.wait();
			}
			LOGGER.info(Thread.currentThread() + ": released " + syncObject.hashCode());
		}
	}

	public void run() {

		try {
			
			LOGGER.info("Processing signals " + getAnalysisName() + " with " + Arrays.toString(additionalEventListNames));
			if (reducedEvents.isEmpty()) return;

			for (String configName : getPassedThroughConfigs().keySet()) {
				ConfigThreadLocal.set(configName, getPassedThroughConfigs().get(configName));
			}

			TransactionHistory calculationTransactions = portfolio.calculate(reducedEvents, spanEnd, buyStrategy, buyPonderationRule, sellPonderationRule, additionalEventListNames);
			sendTransactionHistory(calculationTransactions);

			LOGGER.info("Processor message completed : " + getAnalysisName() + " with " + Arrays.toString(additionalEventListNames));

		} catch (Exception e) {
			LOGGER.error("Error in " + this.toString(), e);
		} finally {
			LOGGER.info(Thread.currentThread() + ": notifying " + syncObject.hashCode());
			synchronized (syncObject) {
				syncObject.notify();
			}
			LOGGER.info(Thread.currentThread() + ": notified " + syncObject.hashCode());
		}

	}

	private void sendTransactionHistory(TransactionHistory transactionHistory) {

		for (final TransactionRecord record : transactionHistory) {

			if (AnalysisClient.getEmailMsgQeueingFilter().contains(record.getSource())) {
				jmsTemplate.send(eventQueue, new MessageCreator() {

					public Message createMessage(Session session) throws JMSException {

						EventType eventType = EventType.INFO;
						EventInfo eventDef = EventDefinition.UNKNOWN99;
						if (record.getMovement().equals("buy")) {
							eventType = EventType.BULLISH;
							Optional<EventInfo> buyEventDef = record.getEventList().getBuyTriggeringEvents().stream().findFirst();
							eventDef = buyEventDef.orElse(EventDefinition.UNKNOWN99);
						} 
						else if (record.getMovement().equals("sell")) {
							eventType = EventType.BEARISH;
							Optional<EventInfo> sellEventDef = record.getEventList().getSellTriggeringEvents().stream().findFirst();
							eventDef = sellEventDef.orElse(EventDefinition.UNKNOWN99);
						}

						String message = record.toString()+"\n\n";

						EventKey eventKey = new ParameterizedEventKey(record.getEventList().getLastDate(), eventDef, eventType);
						EventValue eventValue = new EventValue(record.getEventList().getLastDate(), eventDef, eventType, message, record.getPortfolioName());
						SingleEventMessage infoMessage = new SingleEventMessage(record.getPortfolioName(), record.getDate(), record.getStock(), eventKey, eventValue, ConfigThreadLocal.getAll());
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
