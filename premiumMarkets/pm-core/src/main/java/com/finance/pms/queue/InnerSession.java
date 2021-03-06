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

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import com.finance.pms.threads.ConfigThreadLocal;

// TODO: Auto-generated Javadoc
/**
 * The Class InnerSession.
 * 
 * @author Guillaume Thoreton
 */
public class InnerSession implements Session {
	
	
	/* (non-Javadoc)
	 * @see javax.jms.Session#close()
	 */
	public void close() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#commit()
	 */
	public void commit() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createBrowser(javax.jms.Queue)
	 */
	public QueueBrowser createBrowser(Queue arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createBrowser(javax.jms.Queue, java.lang.String)
	 */
	public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createBytesMessage()
	 */
	public BytesMessage createBytesMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createConsumer(javax.jms.Destination)
	 */
	public MessageConsumer createConsumer(Destination arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createConsumer(javax.jms.Destination, java.lang.String)
	 */
	public MessageConsumer createConsumer(Destination arg0, String arg1) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createConsumer(javax.jms.Destination, java.lang.String, boolean)
	 */
	public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createDurableSubscriber(javax.jms.Topic, java.lang.String)
	 */
	public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createDurableSubscriber(javax.jms.Topic, java.lang.String, java.lang.String, boolean)
	 */
	public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createMapMessage()
	 */
	public MapMessage createMapMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createMessage()
	 */
	public Message createMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createObjectMessage()
	 */
	public ObjectMessage createObjectMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	public ObjectMessage createObjectMessage(Serializable arg0) throws JMSException {
		SingleEventMessage myMess  = new SingleEventMessage(arg0, ConfigThreadLocal.getAll());
		return myMess;
		//throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createProducer(javax.jms.Destination)
	 */
	public MessageProducer createProducer(Destination arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createQueue(java.lang.String)
	 */
	public Queue createQueue(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createStreamMessage()
	 */
	public StreamMessage createStreamMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createTemporaryQueue()
	 */
	public TemporaryQueue createTemporaryQueue() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createTemporaryTopic()
	 */
	public TemporaryTopic createTemporaryTopic() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createTextMessage()
	 */
	public TextMessage createTextMessage() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createTextMessage(java.lang.String)
	 */
	public TextMessage createTextMessage(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#createTopic(java.lang.String)
	 */
	public Topic createTopic(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#getAcknowledgeMode()
	 */
	public int getAcknowledgeMode() throws JMSException {
		throw new UnsupportedOperationException();
		//return 0;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#getMessageListener()
	 */
	public MessageListener getMessageListener() throws JMSException {
		throw new UnsupportedOperationException();
		//return null;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#getTransacted()
	 */
	public boolean getTransacted() throws JMSException {
		throw new UnsupportedOperationException();
		//return false;
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#recover()
	 */
	public void recover() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#rollback()
	 */
	public void rollback() throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#run()
	 */
	public void run() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#setMessageListener(javax.jms.MessageListener)
	 */
	public void setMessageListener(MessageListener arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.jms.Session#unsubscribe(java.lang.String)
	 */
	public void unsubscribe(String arg0) throws JMSException {
		throw new UnsupportedOperationException();
	}
}
