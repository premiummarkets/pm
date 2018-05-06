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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.ParameterizedEventKey;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.pounderationrules.PonderationRule;
import com.finance.pms.portfolio.AutoPortfolioWays;
import com.finance.pms.portfolio.TransactionHistory;
import com.finance.pms.portfolio.TransactionRecord;
import com.finance.pms.queue.AbstractAnalysisClientRunnableMessage;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.threads.ConfigThreadLocal;

public class AutoPortfolioBuySellMessageRunnable extends AbstractAnalysisClientRunnableMessage {

	private static final long serialVersionUID = 7866255237284058407L;
	private static MyLogger LOGGER = MyLogger.getLogger(AutoPortfolioBuySellMessageRunnable.class);

	private String messageTxt;
	private AutoPortfolioWays portfolio;

	private Date startDate;
	private Date endDate;
	private String[] additionalEventListNames;
	private EventInfo eventInfo;

	private PonderationRule buyPonderationRule;
	private PonderationRule sellPonderationRule;

	public AutoPortfolioBuySellMessageRunnable(AutoPortfolioWays portfolio, Date startDate, Date endDate, EventInfo eventInfo, PonderationRule buyPonderationRule, PonderationRule sellPonderationRule, String... eventListName) {
		super(5000, SpringContext.getSingleton(), portfolio.getName());
		this.messageTxt = "Run Auto portfolios Calculation from " + startDate + " to "+ endDate;
		this.portfolio = portfolio;
		this.startDate = startDate;
		this.endDate = endDate;
		this.eventInfo = eventInfo;
		this.buyPonderationRule = buyPonderationRule;
		this.sellPonderationRule = sellPonderationRule;
		this.additionalEventListNames = eventListName;
	}

	public void runAsyncBuyNSellCalculation() throws InterruptedException {
		this.sendRunnableStartProcessingEvent(getAnalysisName(), this);
//				synchronized (syncObject) {
//					syncObject.wait();
//				}
	}

	public void run() {

		try {
			LOGGER.debug("Processing " + getAnalysisName());

			for (String configName : getPassedThroughConfigs().keySet()) {
				ConfigThreadLocal.set(configName, getPassedThroughConfigs().get(configName));
			}

			String[] fullEventListNames = Arrays.copyOf(getAdditionalEventListNames(), getAdditionalEventListNames().length+1);
			fullEventListNames[getAdditionalEventListNames().length] = getAnalysisName(); //Running analysis (Neural mainly) + portfolio specific events (mainly Alerts)

			LOGGER.info("Processing signals " + getMessageTxt() + ", " + getAnalysisName() + " on the " + getStartDate() + " with " +  Arrays.toString(fullEventListNames));
			Set<EventInfo> cfgIndepsAndParameterized = new HashSet<EventInfo>(Arrays.asList(EventDefinition.alertsOnThresholds())); //We retrieve events for this eventInfo complemented with potential alerts for this portfolio
			cfgIndepsAndParameterized.add(eventInfo);
			List<SymbolEvents> events = 
					portfolio.getListShares().keySet().stream()
					.map(s -> EventsResources.getInstance().crudReadEventsForStock(s, getStartDate(), getEndDate(), cfgIndepsAndParameterized, fullEventListNames))
					.collect(Collectors.toList());

			TransactionHistory calculationTransactions = portfolio.calculate(events, getEndDate(), buyPonderationRule, sellPonderationRule, getAdditionalEventListNames());
			sendTransactionHistory(calculationTransactions);

			//FIXME this is useful only when the transactions effectively occurred
			//			AutoPortfolioAnalyser logAnalyser = new AutoPortfolioAnalyser(portfolio);
			//			String logAnalysisMsg = logAnalyser.message();
			//			if (!logAnalysisMsg.isEmpty()) sendTransactionSummary(portfolio, logAnalysisMsg);

			LOGGER.info("Processor message completed : " +getMessageTxt()+", " +getAnalysisName()+" on the " + getStartDate()+ " with "+ Arrays.toString(fullEventListNames));

		} catch (Exception e) {
			LOGGER.error("Error in "+this.toString(),e);
		} finally {
			synchronized (syncObject) {
				syncObject.notify();
			}
		}

	}

	private void sendTransactionSummary(final AutoPortfolioWays portfolio, final String logAnalysisMsg) {

		if (AnalysisClient.getEmailMsgQeueingFilter().contains(EmailFilterEventSource.Metrics)) {
			jmsTemplate.send(eventQueue, new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {

					EventKey eventKey = new StandardEventKey(getEndDate(), EventDefinition.UNKNOWN99, EventType.INFO);
					EventValue eventValue = new EventValue(getEndDate(), EventDefinition.UNKNOWN99, EventType.INFO, logAnalysisMsg, portfolio.getName());
					SingleEventMessage infoMessage = new SingleEventMessage(portfolio.getName(), getEndDate(), AnalysisClient.ANY_STOCK, eventKey, eventValue, ConfigThreadLocal.getAll());
					infoMessage.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), EmailFilterEventSource.Summary); //Source (event calculator)
					infoMessage.setObjectProperty(MessageProperties.TREND.getKey(), eventValue.getEventType().name()); //Bearish Bullish Other Info
					infoMessage.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);

					return infoMessage;
				}
			});
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
		return this.getClass().getName()+" processing "+this.getAnalysisName() + " at "+ getStartDate();
	}

	//	public static String messageLinks(String analysisName, Stock stock) {
	//		return 
	//				"Generated files :\n"+
	//				"file:// "+System.getProperty("installdir") + File.separator + "autoPortfolioLogs"+ File.separator +analysisName+stock.getSymbol()+ "_*_BuyAndSellRecords*.csv\n" +
	//				"file:// "+System.getProperty("installdir") + File.separator + "tmp" + File.separator + "nr_"+stock.getSymbol()+"_cf"+analysisName+"*.csv\n";
	//	}

	private String getMessageTxt() {
		return messageTxt;
	}

	private Date getStartDate() {
		return startDate;
	}

	private Date getEndDate() {
		return endDate;
	}

	private String[] getAdditionalEventListNames() {
		return additionalEventListNames;
	}

}
