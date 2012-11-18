/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

import javax.jms.MessageListener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.Lifecycle;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving innerMessage events. The class that is
 * interested in processing a innerMessage event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addInnerMessageListener<code> method. When
 * the innerMessage event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see InnerMessageEvent
 */
public class InnerMessageListener implements BeanFactoryAware, Lifecycle {
	
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(InnerMessageListener.class);
	
	/** The bean factory. */
	BeanFactory beanFactory;
	
	/** The queueScan. */
	QueueScan queueScan;
	

	/**
	 * Instantiates a new inner message listener.
	 * 
	 * @param destination the destination
	 * @param messageListener the message listener
	 * 
	 * @author Guillaume Thoreton
	 */
	public InnerMessageListener(InnerQueue destination, MessageListener messageListener) {	
		queueScan = new QueueScan(destination,messageListener);
		this.start();
	}


	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.beanFactory = arg0;
	}


	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#isRunning()
	 */
	public boolean isRunning() {
		return queueScan.isAlive();
	}


	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#start()
	 */
	public void start() {
		queueScan.start();
	}


	/* (non-Javadoc)
	 * @see org.springframework.context.Lifecycle#stop()
	 */
	public void stop() {
		try {
			LOGGER.info("Trying to stop the inner message listener");
			synchronized (queueScan) {
				try {
					LOGGER.info("Ascking event listener to stop.");
					queueScan.setToBeStoped(true);
					queueScan.join();
					LOGGER.info("Event listener is stoped.");
				} catch (InterruptedException e) {
					LOGGER.error("",e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
