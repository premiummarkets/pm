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
package com.finance.pms.threads;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.Semaphore;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class ThreadSemaphore.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class ThreadSemaphore {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(ThreadSemaphore.class);
	
	/** The total number of threads. */
	private static Integer totalNumberOfThreadsToRun;
	
	/** The progress per cent. */
	public static Float progressPerCent = 0F;
	
	/** The semaphore. */
	private Semaphore semaphore;
	
	/** The nb treads to run. */
	private Integer nbTreadsToRun;
	

	/** The awaiting object. */
	private Object awaitingObject;
	
	/** The property change support. */
	private static PropertyChangeSupport propertyChangeSupport;

	private Integer semaphoreCapacity;
	
	static {
		ThreadSemaphore.propertyChangeSupport = new PropertyChangeSupport(ThreadSemaphore.class);
	}
	
	
	/**
	 * Gets the new semaphore.
	 * 
	 * @param nbTreadsToRun the nb treads to run
	 * @param nbthreads the nbthreads
	 * 
	 * @return the new semaphore
	 */
	public static ThreadSemaphore getNewSemaphore(Integer nbTreadsToRun, Integer nbthreads) {
		return new ThreadSemaphore(nbTreadsToRun, nbthreads);
	}

	/**
	 * Instantiates a new thread semaphore.
	 * 
	 * @param nbTreadsToRun the nb treads to run
	 * @param nbthreads the nbthreads
	 * 
	 * @author Guillaume Thoreton
	 */
	@Deprecated
	private ThreadSemaphore(Integer nbTreadsToRun, Integer nbthreads) {
		super();
		ThreadSemaphore.totalNumberOfThreadsToRun = nbTreadsToRun;
		
		this.nbTreadsToRun = nbTreadsToRun;
		this.semaphoreCapacity = nbthreads;
		this.semaphore = new Semaphore(nbthreads);
		
		LOGGER.debug("Created semaphore  :"+this.toString());
	}

	/**
	 * Acquire.
	 * 
	 * @throws InterruptedException the interrupted exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public void acquire() throws InterruptedException {
		LOGGER.debug("Acquiring semaphore. Nb of thread still to finish is :"+nbTreadsToRun);
		LOGGER.debug("Acquiring semaphore. Nb of free permits :"+semaphore.availablePermits());
		LOGGER.debug("Acquiring semaphore. Nb of thread waiting on aquire before acquiring :"+semaphore.getQueueLength());
		semaphore.acquire();
		LOGGER.debug("Acquired semaphore. Nb of thread waiting on aquire after acquiring :"+semaphore.getQueueLength());
	}

	/**
	 * Release.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void release() {
		
		synchronized (nbTreadsToRun) {
			
			if (nbTreadsToRun == 0) {
				LOGGER.error("All thread have been already been released! Check if the semaphore is initalized properly.",new Throwable());
			}
			
			nbTreadsToRun--;

			LOGGER.debug("Releasing semaphore. Number of thread left to finish after release :"+nbTreadsToRun);
			LOGGER.debug("Releasing semaphore. Nb of free permits :"+semaphore.availablePermits());
			LOGGER.debug("Releasing semaphore. Nb of thread waiting on aquire :"+semaphore.getQueueLength());
			
			ThreadSemaphore.updateProgressCounter(nbTreadsToRun);

			if (this.awaitingObject != null && nbTreadsToRun == 0) {
				synchronized (this.awaitingObject) {
					LOGGER.debug("Notifying for release");
					this.awaitingObject.notify();
				}
			}
		}
		
		if (semaphore.availablePermits() == semaphoreCapacity) {
			LOGGER.error("All thread have been already been released! Check if the acquire is set properly.",new Throwable());
		}
		
		semaphore.release();

	}

	/**
	 * Update progress counter.
	 * 
	 * @param nbTreadsLeft the nb treads left
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void updateProgressCounter(Integer nbTreadsLeft) {
		Float oldPpc = ThreadSemaphore.progressPerCent;
		ThreadSemaphore.progressPerCent =  (new Float(ThreadSemaphore.totalNumberOfThreadsToRun - nbTreadsLeft)) * 100 / (new Float(ThreadSemaphore.totalNumberOfThreadsToRun));
		ThreadSemaphore.propertyChangeSupport.firePropertyChange("progressPerCent", oldPpc, ThreadSemaphore.progressPerCent);
	}
	
	public void await(Object awaitingObject) {
		this.await(awaitingObject,60000);
		
	}
	
	/**
	 * Await.
	 * 
	 * @param awaitingObject the awaiting object
	 * 
	 * @author Guillaume Thoreton
	 * @param timeout 
	 */
	public void await(Object awaitingObject, long timeout) {
		
		this.awaitingObject = awaitingObject;
		try {
			LOGGER.debug("Await. Try to synchronize on : "+this.awaitingObject);
			synchronized (this.awaitingObject) {
				LOGGER.debug("Await. Number of thread left to run : "+nbTreadsToRun);
				while (nbTreadsToRun > 0) {
					LOGGER.debug("Waiting for all threads to be done.");
					awaitingObject.wait(timeout);
				}
				LOGGER.debug("All threads have issued a release nb Threads to run is : "+nbTreadsToRun+". Wait is over.");
			}
		} catch (InterruptedException e) {
			LOGGER.debug("Semaphore wait interrupted",e);
		}
	}
	
	
	/**
	 * Adds the property change listener.
	 * 
	 * @param listener the listener
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public Integer getNbTreadsToRun() {
		return nbTreadsToRun;
	}

	@Override
	public String toString() {
		return "ThreadSemaphore [nbTreadsToRun=" + nbTreadsToRun + ", semaphoreCapacity=" + semaphoreCapacity + "]";
	}
	
	
}
