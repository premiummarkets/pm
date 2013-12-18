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
package com.finance.pms.screening;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.EventsResources;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.events.calculation.MessageProperties;
import com.finance.pms.queue.SymbolEventsMessage;
import com.finance.pms.threads.ConfigThreadLocal;

public abstract class Exporter<T> {
	
	private static MyLogger LOGGER = MyLogger.getLogger(Exporter.class);

	public static String SEPARATOR = ";";
	public static String BLANK = "/";
	
	protected BufferedWriter bufferedWriter;
	
	protected Queue eventQueue;
	protected JmsTemplate jmsTemplate;

	protected String fileName;
	protected String header;
	
	public abstract void exportToFile(T element) throws IOException;
	
	/**
	 * @param fileName
	 */
	protected Exporter(String fileName,String header,Queue queue,JmsTemplate jmsTemplate) {
		this.fileName = fileName;
		this.header = header;
		this.eventQueue = queue;
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * @param fileName
	 * @param header
	 */
	protected void writeFileHeader(String fileName, String header) {
		File file = new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator +fileName+".csv");
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(header);
			bufferedWriter.newLine();
		} catch (IOException e) {
			LOGGER.error("",e);
		}
	}
	
	protected void storeAndSendScreeningEvents(Collection<SymbolEvents> screeningEvents, EmailFilterEventSource eventSource, String eventListName) {
		
		for (SymbolEvents event : screeningEvents) {
			sendScreeningEvent(event, eventSource, eventListName);
			EventsResources.getInstance().crudCreateEvents(event, true, eventListName);
		} 
	}

	private void sendScreeningEvent(final SymbolEvents symbolEvent,final EmailFilterEventSource eventSource, final String eventListName) {	
		
		if (AnalysisClient.getEmailMsgQeueingFilter().contains(eventSource)) {
			jmsTemplate.send(eventQueue, new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {

					SymbolEventsMessage message = new SymbolEventsMessage(eventListName, symbolEvent, ConfigThreadLocal.getAll());
					message.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), eventSource);
					message.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
					message.setObjectProperty(MessageProperties.EVENT_INFO.getKey(), EventDefinition.SCREENER.getEventReadableDef());

					List<EventValue> eventValues = symbolEvent.getSortedDataResultList();
					EventValue eventValue = eventValues.get(eventValues.size()-1);
					message.setObjectProperty(MessageProperties.TREND.getKey(), eventValue.getEventType().name());
					
					return message;
				}
			});
		}
	}
	
	public abstract void  buildAndSendScreeningEvents(T screened,String eventListName);
	

}
