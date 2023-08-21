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
package com.finance.pms.queue;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.springframework.jms.JmsException;
import org.springframework.jms.MessageNotReadableException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.finance.pms.events.EventMessageObject;
import com.finance.pms.events.SymbolEvents;

public class TuningAlertsJmsTemplate extends JmsTemplate {
	
	List<SymbolEvents> events = new ArrayList<SymbolEvents>();
	JmsTemplate jmsTemplate;
	String portfolioName;

	public TuningAlertsJmsTemplate(String portfolioName, JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
		this.portfolioName = portfolioName;
	}

	public List<SymbolEvents> getEvents() {
		return events;
	}

	@Override
	public void send(Destination destination, MessageCreator messageCreator) throws JmsException {
		
		try {
			Message message = messageCreator.createMessage(null);
			if (message instanceof SingleEventMessage) {
				EventMessageObject eventMessageObject = (EventMessageObject)((ObjectMessage)message).getObject();
				
				String portfolioName = eventMessageObject.getEventListName();
				if (!this.portfolioName.equals(portfolioName)) {
					throw new MessageNotReadableException(new javax.jms.MessageNotReadableException("portfolio name miss match : " + this.portfolioName + " vs " + portfolioName));
				}
					
				SymbolEvents symbolEvents = new SymbolEvents(eventMessageObject);
				events.add(symbolEvents);
			} else {
				jmsTemplate.send(destination, messageCreator);
			}
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getPortfolioName() {
		return portfolioName;
	}
}
