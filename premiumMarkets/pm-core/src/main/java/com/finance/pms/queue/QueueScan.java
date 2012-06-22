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
	
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(QueueScan.class);
	
	/** The destination. */
	private InnerQueue destination;
	
	/** The my message listener. */
	private MessageListener myMessageListener;
	
	/** The to be stoped. */
	private Boolean toBeStoped = false;

	/**
	 * Sets the to be stoped.
	 * 
	 * @param toBeStoped the new to be stoped
	 */
	public void setToBeStoped(Boolean toBeStoped) {
		this.toBeStoped = toBeStoped;
	}


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


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
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
				destination.removeMessage(nextMess);
			} catch (Exception e) {
				destination.removeMessage(nextMess);
				LOGGER.error("Can't deal with the following :"+nextMess.toString()+ " Message is now lost",e);
				LOGGER.debug(e,e);
			}
		}

		LOGGER.info("End of Queue Scan.");

	}
	
}
