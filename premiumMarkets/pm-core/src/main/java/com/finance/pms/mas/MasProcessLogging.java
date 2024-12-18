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
package com.finance.pms.mas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

import com.finance.pms.admin.install.logging.MyLogger;


// TODO: Auto-generated Javadoc
/**
 * The Class MasProcessLogging.
 * 
 * @author Guillaume Thoreton
 */
public class MasProcessLogging extends Thread {//  implements Runnable {
	
	/** The LOGGER. */
protected static MyLogger LOGGER = MyLogger.getLogger(MasProcessLogging.class);

	/** The alive. */
	private Boolean alive = true;
	
	/** The p1. */
	private Process p1;
	
	/** The outputfile. */
	private File outputfile;
	
	/** The c1. */
	private Integer c1;

	/**
	 * Instantiates a new mas process logging.
	 * 
	 * @param p1 the p1
	 * @param outputfile the outputfile
	 * @param c1 the c1
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasProcessLogging(Process p1, File outputfile, Integer c1) {
		super();
		this.p1 = p1;
		this.outputfile = outputfile;
		this.c1 = c1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		BufferedReader inputB = null;
		BufferedWriter outputB = null;
		try {
			inputB = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			outputB = new BufferedWriter(new FileWriter(outputfile));
			int c;
			CharBuffer buf = CharBuffer.allocate(1024);
			while (alive && ((c = inputB.read (buf)) > -1)) {
				outputB.write(buf.array(),0,c);
			}
			inputB.close();
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("logging interupted for connection " + c1 + ". Probably this server was stopped ...",e);
		} finally {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Mas loggin terminated for connection " + c1);
			try {
				outputB.flush();
				outputB.close();
			} catch (IOException e) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Can't close logging error for connection " + c1+". Can't close output.",e);
			}		
		}
	}

	/**
	 * Stop logging.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void stopLogging() {
		alive = false;
	}

	/**
	 * Gets the c1.
	 * 
	 * @return the c1
	 */
	public Integer getC1() {
		return c1;
	}
}
