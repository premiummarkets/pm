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
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.AnalysisClient;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.SymbolEvents;
import com.finance.pms.queue.SingleEventMessage;
import com.finance.pms.queue.SymbolEventsMessage;
import com.finance.pms.threads.ConfigThreadLocal;

//FIXME queue and message template should not be passed as parameter but pick from the Spring Container using container awareness.
public abstract class EventsCalculationThread extends Observable implements Callable<SymbolEvents> {

    protected static MyLogger LOGGER = MyLogger.getLogger(EventsCalculationThread.class);

    protected Date startDate;
    protected Date endDate;
    protected String eventListName;

    protected Queue eventQueue;
    protected JmsTemplate jmsTemplate;
    protected Currency calculationCurrency;
    protected Map<String,Config> configs;
    protected Set<Observer> observers;

    protected EventsCalculationThread(
            Date startDate, Date endDate, String eventListName, Currency calculationCurrency, 
            Set<Observer> observers, Queue eventQueue, JmsTemplate jmsTemplate) {

        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventListName = eventListName;
        this.calculationCurrency = calculationCurrency;

        this.eventQueue = eventQueue;
        this.jmsTemplate = jmsTemplate;

        this.configs = ConfigThreadLocal.getAll();

        this.observers = observers;
        for (Observer observer : observers) {
            this.addObserver(observer);
        }

    }
    
    protected EventsCalculationThread(
            Date startDate, Date endDate, String eventListName, Currency calculationCurrency, 
            Set<Observer> observers) {

        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventListName = eventListName;
        this.calculationCurrency = calculationCurrency;

        this.configs = ConfigThreadLocal.getAll();

        this.observers = observers;
        for (Observer observer : observers) {
            this.addObserver(observer);
        }

    }

    protected void sendEvent(String eventListName, String portfolio, Stock stock, EventKey eventKey, EventValue event, EmailFilterEventSource eventSource, EventInfo eventInfo) {

        if (eventQueue == null) {
            LOGGER.info("No queue available. No message required to be sent");
            return;
        }

        if (AnalysisClient.getEmailMsgQeueingFilter().contains(eventSource)) {
            jmsTemplate.send(eventQueue, new MessageCreator() {

                public Message createMessage(Session session) throws JMSException {

                    SingleEventMessage message = new SingleEventMessage(eventListName, portfolio, stock, endDate, eventKey, event, ConfigThreadLocal.getAll());
                    message.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), eventSource); //Source
                    message.setObjectProperty(MessageProperties.TREND.getKey(), event.getEventType().name()); //Bearish or Bullish or Other?
                    message.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
                    if (eventInfo != null) message.setObjectProperty(MessageProperties.EVENT_INFO.getKey(), eventInfo.getEventReadableDef());

                    return message;
                }
            });
        }
    }

    protected void sendEvent(final String eventListName, final SymbolEvents symbolEvents, final EmailFilterEventSource eventSource, final EventType lastEventType, final EventInfo eventInfo) {

        if (eventQueue == null) {
            LOGGER.info("No queue available. No message required to be sent");
            return;
        }

        if (AnalysisClient.getEmailMsgQeueingFilter().contains(eventSource)) {
            jmsTemplate.send(eventQueue, new MessageCreator() {

                public Message createMessage(Session session) throws JMSException {

                    SymbolEventsMessage message = new SymbolEventsMessage(eventListName, symbolEvents, ConfigThreadLocal.getAll());
                    message.setObjectProperty(MessageProperties.ANALYSE_SOURCE.getKey(), eventSource); //Source
                    message.setObjectProperty(MessageProperties.TREND.getKey(), lastEventType.name()); //Bearish or Bullish or Other?
                    message.setObjectProperty(MessageProperties.SEND_EMAIL.getKey(), Boolean.TRUE);
                    if (eventInfo != null) message.setObjectProperty(MessageProperties.EVENT_INFO.getKey(), eventInfo.getEventReadableDef());

                    return message;
                }
            });
        }
    }

    public abstract void cleanEventsFor(Stock stock, EventInfo eventInfo, String eventListName);

}
