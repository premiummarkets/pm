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
package com.finance.pms.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import com.finance.pms.admin.install.logging.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class InnerQueue.
 * 
 * @author Guillaume Thoreton
 */
public class InnerQueue implements Queue {
	
	private static MyLogger LOGGER = MyLogger.getLogger(InnerQueue.class);
	
	/** The my queue. */
	private ConcurrentLinkedQueue<Message> myQueue;
	private int myQueueSize;
	private Map<Integer,IdentifiedObjecMessage> inProcessQueue;

	
	public InnerQueue() {
		super();
		myQueue = new ConcurrentLinkedQueue<Message>();
		myQueueSize = 0;
		this.inProcessQueue = new ConcurrentHashMap<Integer,IdentifiedObjecMessage>();
	}
	
	/**
	 * Adds the message.
	 * 
	 * @param myMessage the my message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void addMessage(Message myMessage) {
		synchronized (myQueue) {
			 myQueueSize++;
			 myQueue.add(myMessage);
		}
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return the boolean
	 * 
	 * @author Guillaume Thoreton
	 */
	public Boolean isEmpty() {
		synchronized (myQueue) {
			return myQueueSize == 0;
		}
	}
	
	public Boolean isEmptyAndProcessed() {
		synchronized (myQueue) {
			return myQueueSize == 0 && this.inProcessQueue.isEmpty();
		}
		
	}
	
	/**
	 * Next message.
	 * 
	 * @return the inner message
	 * 
	 * @author Guillaume Thoreton
	 */
	public Message nextMessage() {
		
		Message nextMess;
		synchronized (myQueue) {
			
			nextMess = myQueue.poll();
			myQueueSize--;
			
			inProcessQueue.put(((IdentifiedObjecMessage) nextMess).getMessageKey(),(IdentifiedObjecMessage) nextMess);
		}
		
		return nextMess;
	}
	
	/**
	 * Removes the message.
	 * 
	 * @return the inner message
	 * 
	 * @author Guillaume Thoreton
	 */
	public void removeMessage(Message identifiedObjecMessage) {
		synchronized (myQueue) {
			inProcessQueue.remove(((IdentifiedObjecMessage)identifiedObjecMessage).getMessageKey());
		}
		
	}

	public String getQueueName() throws JMSException {
		throw new UnsupportedOperationException();
	}
	
	public Integer size() {
		//return myQueue.size();
		synchronized (myQueue) {
			return myQueueSize;
		}
		
	}
	
	public Integer messagesInProcess() {
		synchronized (myQueue) {
			return inProcessQueue.size();
		}
		
	}
	
	public String toString() {
		StringBuffer retVal = new StringBuffer("[ ");
		String sep = "";
		for (IdentifiedObjecMessage inProcess :inProcessQueue.values()) {
			retVal.append(sep + "Identifier : "+inProcess.messageKey+" ");
			try {
				retVal.append("Message : "+inProcess.getObject().toString());
			} catch (JMSException e) {
				LOGGER.error("",e);
			}
			sep = ",";
			
		}
		retVal.append(" ]");
		return retVal.toString();
	}

	public boolean contains(Message message) {
		synchronized (myQueue) {
			return myQueue.contains(message);
		}
		
	}
	
	
}
