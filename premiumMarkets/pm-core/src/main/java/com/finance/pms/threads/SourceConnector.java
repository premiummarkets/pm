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

import com.finance.pms.mas.RestartServerException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SourceConnector.
 * 
 * @author Guillaume Thoreton
 */
public interface SourceConnector {
	
	/**
	 * Connect.
	 * 
	 * @param connectionId the connection id
	 * 
	 * @return the source client
	 * 
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public SourceClient connect(int connectionId) throws RestartServerException;
	
	/**
	 * Gets the thread pool.
	 * 
	 * @return the thread pool
	 */
	public PoolSemaphore getThreadPool();
	
	/**
	 * Restart source.
	 * 
	 * @param connectionId the connection id
	 * 
	 * @return the int
	 * 
	 * @author Guillaume Thoreton
	 */
	public int crashResart(int connectionId); 
	
	/**
	 * Shutdown source.
	 * 
	 * @param sourceClient the source client
	 * @param connectionId the connection id
	 * 
	 * @author Guillaume Thoreton
	 */
	public void shutdownSource(SourceClient sourceClient, int connectionId); 

}
