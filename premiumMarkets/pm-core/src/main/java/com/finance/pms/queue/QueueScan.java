/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.BeanFactory;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class QueueScan.
 * 
 * @author Guillaume Thoreton
 */
public class QueueScan extends Thread {
	

	protected static MyLogger LOGGER = MyLogger.getLogger(QueueScan.class);
	

	private InnerQueue destination;
	private MessageListener myMessageListener;
	private Boolean toBeStoped = false;


	/**
	 * Instantiates a new queue scan.
	 * 
	 * @param destination the destination
	 * @param myMessageListener the my message listener
	 * 
	 * @author Guillaume Thoreton
	 */
	public QueueScan(InnerQueue destination, MessageListener myMessageListener) {
		super();
		this.destination = destination;
		this.myMessageListener = myMessageListener;
	
	}

	
	public void setToBeStoped(Boolean toBeStoped) {
		this.toBeStoped = toBeStoped;
	}

	/**
	 * Start.
	 * 
	 * @param beanFactory the bean factory
	 * 
	 * @author Guillaume Thoreton
	 */
	public synchronized void start(BeanFactory beanFactory) {
		super.start();
		LOGGER.debug("dataSource in QueueScan : "+beanFactory.getBean("dataSource"));
	}


	@Override
	public void run() {
		
		while (!toBeStoped || !destination.isEmpty()) {
			
			while (destination.isEmpty()) {
				
				if (toBeStoped) break;
				
				synchronized (destination) {
					destination.notifyAll();
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignored) {
					LOGGER.debug("Interrupted Thread while Queue sleep : " + ignored);
					toBeStoped = true;
				}
			}
			
			if (toBeStoped && destination.isEmpty()) break;
			
			Message nextMess = destination.nextMessage();
			try {
				LOGGER.debug("Processing message :"+nextMess);
				this.myMessageListener.onMessage(nextMess);
				
			} catch (Exception e) {
				
				LOGGER.error("Can't deal with the following :"+nextMess.toString()+ " Message is now lost",e);
				LOGGER.debug(e,e);
			} finally {
				
				try {
					destination.removeMessage(nextMess);
				} catch (Throwable e) {
					LOGGER.error(e,e);
				}
			}
		}

		LOGGER.info("End of Queue Scan.");

	}
	
}
