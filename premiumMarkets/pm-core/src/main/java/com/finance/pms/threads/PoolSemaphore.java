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
package com.finance.pms.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.mas.RestartServerException;



// TODO: Auto-generated Javadoc
/**
 * The Class PoolSemaphore.
 * 
 * @author Guillaume Thoreton
 */
public class PoolSemaphore {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(PoolSemaphore.class);
	
	/** The Constant NUMBER_OF_CONNEXION_TRY. */
	public static final int NUMBER_OF_CONNEXION_TRY = 10;
	
	/** The available. */
	private final Semaphore available;
	
	/** The n threads. */
	private int nThreads;

	/** The source client. */
	private SourceClient[] sourceClient;
	
	/** The source connector. */
	private SourceConnector sourceConnector;
	
	/** The used. */
	protected Boolean[] used;
	

	/**
	 * Instantiates a new pool semaphore.
	 * 
	 * @param nThreads the n threads
	 * @param sourceConnector the source connector
	 * @param initPool the init pool
	 * 
	 * @author Guillaume Thoreton
	 */
	public PoolSemaphore(int nThreads, SourceConnector sourceConnector, Boolean initPool) {
		
		this.nThreads = nThreads;
		this.sourceConnector = sourceConnector;
		this.sourceClient  = new SourceClient[this.nThreads];
		this.available = new Semaphore(nThreads, false);
		used = new Boolean[nThreads];
		
		for (int i=0 ; i< nThreads; i++) used[i]=false;
		if (initPool) {
			this.sourceClient = initConnections();
		}
	}

	/**
	 * Inits the connections.
	 * 
	 * @return the source client[]
	 * 
	 * @author Guillaume Thoreton
	 */
	private SourceClient[] initConnections() {
		
		SourceClient[] ret = new SourceClient[this.nThreads];
		int threadnum = 0;
		
		for (; threadnum < this.nThreads; threadnum++) {
			ret[threadnum] = tryConnection(threadnum, false);
		}

		return ret;
	}

	/**
	 * Try connection.
	 * 
	 * @param threadnum the threadnum
	 * @param crashed the crashed
	 * 
	 * @return the source client
	 * 
	 * @author Guillaume Thoreton
	 */
	private SourceClient tryConnection(int threadnum, Boolean crashed) {
		
		SourceClient ret = null;
		int connectionTry = 0;
		try {
			if (crashed) crashSpecificRestart(threadnum);
			ret = initOneConnection(threadnum);
		} catch (RestartServerException e) {
			
			while (connectionTry < 3 && connectionTry > -1) {
				crashSpecificRestart(threadnum);
				try {
					LOGGER.debug("\n\nConnection attempts for thread " + threadnum + " : " + (connectionTry + 1));
					ret = initOneConnection(threadnum);
					connectionTry = -1;
				} catch (RestartServerException e1) {
					connectionTry++;
					LOGGER.debug("\n\nConnection echec for thread " + threadnum + " on the "+connectionTry+" attempts");
				}
			}
			if (connectionTry == 3) {
				LOGGER.warn("\n\nFATAL : Trice unabled to connect to Mas server : " + threadnum + " after restarting Mas server ... Aborting.");
				used[threadnum] = true;
			}
		} catch (RuntimeException e) {
			LOGGER.error("\n\nFATAL : Source connection ERROR :" + e,e);
		}

		return ret;
	}

	/**
	 * Inits the one connection.
	 * 
	 * @param threadnum the threadnum
	 * 
	 * @return the source client
	 * 
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	private SourceClient initOneConnection(int threadnum) throws RestartServerException {

		SourceClient ret;
		int k;
		for (k=0; k < PoolSemaphore.NUMBER_OF_CONNEXION_TRY; k++) {
			try {
				ret = this.sourceConnector.connect(threadnum);
				return ret;
			} catch (RestartServerException e) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					LOGGER.error("Wait for data source has been interrupted!",e1);
				}
			} catch (Exception e) {
				LOGGER.error(e,e);
				break;
			}
		}
		
		throw new RuntimeException("FATAL : "+k+" times unabled to connect to server : "+ threadnum +" ... Aborting.");
	}

	
	/**
	 * Gets the resource.
	 * 
	 * @return the resource
	 * 
	 * @throws InterruptedException the interrupted exception
	 */
	public SourceClient getResource() throws InterruptedException, TimeoutException {
		
		//check sanity before locking ...
		checkSanity();
		Boolean acquired = available.tryAcquire(30,TimeUnit.MINUTES);
		if (!acquired) {
			LOGGER.error("Couldn't acquire resource. Please increase the amount available.");
			throw new TimeoutException("Couldn't acquire resource. Please increase the amount available.");
		}
		return getNextAvailableConnexion();
	}

	public void releaseResource(SourceClient x) {
		if (markAsUnused(x)) available.release();
	}

	private SourceClient getNextAvailableConnexion() {
		for (int i = 0; i < this.nThreads; ++i) {
			synchronized (used) {
				if (!used[i]) {
					try {
						if (null == sourceClient[i] || !sourceClient[i].isValid()) {
							if (null != sourceClient[i]) LOGGER.warn("Connector "+sourceConnector.getClass().getSimpleName()+" : Connection "+i+" is staled : restarting");
							sourceClient[i] = this.initOneConnection(i);
						}
						used[i] = true;
						return sourceClient[i];
					} catch (RestartServerException e) {
						LOGGER.error("I shouldn't be here ... Mas doesn't use that :-)",e);
					} catch (RuntimeException e) {
						LOGGER.error("FATAL : Source connection ERROR :" + e,e);
					}
				}
			}
		}
		throw new UnsupportedOperationException("No resource available. Fixe me!");
	}

	protected synchronized boolean markAsUnused(SourceClient item) {
		for (int i = 0; i < nThreads; ++i) {
			if (item == sourceClient[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public SourceClient reconnectSource(SourceClient item) throws InterruptedException, TimeoutException {
	
		DataSource.getInstance().shutdownSource(item);
		
		for (int i = 0; i < nThreads; ++i) {
	
			if (item == sourceClient[i]) {
				sourceClient[i] = tryConnection(i, true);
				
				if (null != sourceClient[i]) { //remove resource from pool
					available.release();
					used[i]=false;
				}
				
				break;
			}
		}
	
	
		return this.getResource();
		
	}

	private void checkSanity() throws InterruptedException {
		int outOfOrder = 0;
		for (int i = 0; i < this.nThreads; ++i) {
			if (used[i] && sourceClient[i] == null) outOfOrder++;
		}
		if (outOfOrder == this.nThreads) {
			LOGGER.error("All resources in the pool seams to be shutdown and can't be restarted");
			this.available.release(this.nThreads);
			throw new InterruptedException("No resource available. Fix me!");
		}
	}
	
	private void crashSpecificRestart(int resourceNum) {
		
		int s = this.sourceConnector.crashResart(resourceNum);
		try {
			synchronized (this) {
				wait(s);
			}
		} catch (InterruptedException e) {
			LOGGER.error("",e);
		}
	}

    public void stopThreads() {
    	
    	LOGGER.info("Stopping Thread pool, nb threads to stop : "+nThreads);
    	
        for (int i = 0; i < nThreads; i++) {
        	if (null != sourceClient[i]) {
        		sourceConnector.shutdownSource(sourceClient[i], i);
        		LOGGER.info("Stopping Thread pool, "+sourceConnector.getClass().getName()+" shutting down : "+i);
        	} else {
        		LOGGER.info("Stopping DB Thread pool, "+sourceConnector.getClass().getName()+" is empy  : "+i);
        	}
        }
    }

}
