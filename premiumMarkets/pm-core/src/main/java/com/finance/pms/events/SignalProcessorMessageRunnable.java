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
package com.finance.pms.events;

import java.util.Arrays;
import java.util.Calendar;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.MessageProperties;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.AutoPortfolioWays;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.TransactionHistory;
import com.finance.pms.portfolio.TransactionRecord;
import com.finance.pms.queue.SignalProcessorMessage;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.threads.ConfigThreadLocal;

public class SignalProcessorMessageRunnable implements Runnable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(SignalProcessorMessageRunnable.class);
	
	private SignalProcessorMessage message;
	private JmsTemplate jmsTemplate;
	private Queue eventQueue;

	public SignalProcessorMessageRunnable(Message message, JmsTemplate jmsTemplate, Queue eventQueue) {
		super();
		this.message = (SignalProcessorMessage) message;
		this.jmsTemplate = jmsTemplate;
		this.eventQueue = eventQueue;
	}

	public void run() {

		try {
			LOGGER.debug("Processing " + message.getSignalProcessingName());

			for (String configName : message.getPassedThroughConfigs().keySet()) {
				ConfigThreadLocal.set(configName, message.getPassedThroughConfigs().get(configName));
			}

			LOGGER.info("Processing signals " +message.getMessageTxt()+", " + message.getSignalProcessingName() + " on the " + message.getStartDate() + " with " + message.getAdditionalEventListNames());

			AutoPortfolioWays portfolio = (AutoPortfolioWays) PortfolioMgr.getInstance().getPortfolio(message.getSignalProcessingName());
			
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(message.getStartDate());
			while (currentDate.getTime().before(message.getEndDate()) || currentDate.getTime().equals(message.getEndDate())) {
				
				TransactionHistory calculationTransactions = portfolio.calculate(currentDate.getTime(), message.getAdditionalEventListNames());
				sendTransactionHistory(calculationTransactions);
				
				QuotationsFactories.getFactory().incrementDate(currentDate, 1);
				
			}
			
			LOGGER.info("Processor message completed : " +message.getMessageTxt()+", " +message.getSignalProcessingName()+" on the " + message.getStartDate()+ " with "+ Arrays.toString(message.getAdditionalEventListNames()));
		} catch (Exception e) {
			LOGGER.error("Error in "+this.toString(),e);
		}

	}
	
	private void sendTransactionHistory(TransactionHistory transactionHistory) {
		
		for (final TransactionRecord record : transactionHistory) {
			
			jmsTemplate.send(eventQueue, new MessageCreator() {
				
				public Message createMessage(Session session) throws JMSException {
					
					EventValue eventValue = new EventValue(record.getEventList().getLastDate(), EventDefinition.UNKNOWN99, EventType.INFO, record.toString(), record.getPortfolioName());
					SingleEventMessage infoMessage = new SingleEventMessage(record.getPortfolioName(), record.getDate(), record.getStock(), eventValue);
					infoMessage.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), record.getSource()); //Source (event calculator)
					infoMessage.setObjectProperty(MessageProperties.TREND.getKey(), eventValue.getEventType().name()); //Bearish Bullish Other Info
					infoMessage.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
					
					return infoMessage;
				}
			});
		}
		
	}


	@Override
	public String toString() {
		return this.getClass().getName()+" processing "+this.message.getSignalProcessingName() + " at "+ message.getStartDate();
	}

}
