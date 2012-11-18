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
package com.finance.pms.mas;

import com.finance.pms.admin.install.logging.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class MasProcess.
 * 
 * @author Guillaume Thoreton
 */
public class MasProcess {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MasProcess.class);
	
	/** The process. */
	Process process;
	
	/** The log thread. */
	MasProcessLogging logThread;
	
	
	/**
	 * Instantiates a new mas process.
	 * 
	 * @param process the process
	 * @param logThread the log thread
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasProcess(Process process, MasProcessLogging logThread) {
		super();
		this.process = process;
		this.logThread = logThread;
	}
	
	/**
	 * Stop process.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void stopProcess() {
		//Stop log
		LOGGER.info("Shutting down logging for Thread "+this.getLogThread().getC1());
		this.getLogThread().stopLogging();
		//Kill
		this.getProcess().destroy();
	}
	
	/**
	 * Gets the process.
	 * 
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}
	
	/**
	 * Sets the process.
	 * 
	 * @param process the new process
	 */
	public void setProcess(Process process) {
		this.process = process;
	}
	
	/**
	 * Gets the log thread.
	 * 
	 * @return the log thread
	 */
	public MasProcessLogging getLogThread() {
		return logThread;
	}
	
	/**
	 * Sets the log thread.
	 * 
	 * @param logThread the new log thread
	 */
	public void setLogThread(MasProcessLogging logThread) {
		this.logThread = logThread;
	}

}
