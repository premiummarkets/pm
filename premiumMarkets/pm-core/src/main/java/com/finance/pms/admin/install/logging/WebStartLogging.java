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
package com.finance.pms.admin.install.logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.CharBuffer;

import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class WebStartLogging.
 * 
 * @author Guillaume Thoreton
 */
public class WebStartLogging extends Thread {
	
	/** The jta. */
	private JTextArea jta;
	
	/** The pss out put. */
	private FileWriter pssOutPut;
	
	/** The p1. */
	private Process p1;

	/**
	 * Instantiates a new web start logging.
	 * 
	 * @param jta the jta
	 * @param p1 the p1
	 * 
	 * @author Guillaume Thoreton
	 */
	public WebStartLogging(JTextArea jta, Process p1) {
		super();
		this.setDaemon(true);
		this.jta = jta;
		this.p1 = p1;
		
		try {
			pssOutPut = new FileWriter(new File(System.getProperty("java.io.tmpdir")+File.separator+"pmsinstall.log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		BufferedReader inputB = null;
		try {
			inputB = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			int c;
			CharBuffer buf = CharBuffer.allocate(2048);
			while ((c = inputB.read(buf)) > -1) {
				StringWriter outputB = new StringWriter();
				outputB.write(buf.array(),0,c);
				jta.append(outputB.toString());
				
				pssOutPut.write(buf.array(),0,c);
				pssOutPut.flush();
				System.out.println("I'm alive");
				sleep(1000);
			}
			pssOutPut.flush();
			inputB.close();
			System.out.println("I'm dead");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
