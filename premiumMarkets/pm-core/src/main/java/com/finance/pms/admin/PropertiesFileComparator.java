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
package com.finance.pms.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileComparator {
	
	
	public static void main(String ... args) {
		
		Properties p1 = new Properties();
		Properties p2 = new Properties();
		
		System.out.println("< "+args[0]);
		System.out.println("> "+args[1]);
	
		try {
			File pfile = new File(args[0]);
			FileInputStream propFileIS = new FileInputStream(pfile);
			p1.load(propFileIS);
			
			File pfile2 = new File(args[1]);
			FileInputStream propFileIS2 = new FileInputStream(pfile2);
			p2.load(propFileIS2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Object key1 : p1.keySet()) {
			
			String foundKey2 = null;
			for (Object key2 : p2.keySet()) {
				if (key1.equals(key2)) {
					foundKey2 = (String) key2;
					break;
				}
			}
			
			if (foundKey2 != null) {
				if (p1.getProperty((String) key1).equals(p2.getProperty(foundKey2))) {
					//Ok ==
				} else {
					System.out.println("< "+key1+"="+p1.getProperty((String) key1));
					System.out.println("> "+foundKey2+"="+p2.getProperty((String) foundKey2));
				}
			} else {
				System.out.println("< "+key1+"="+p1.getProperty((String) key1));
			}
		}
		
		//Check for remaining in p2 and in p1
		for (Object key2 : p2.keySet()) {
			
			Boolean foundKey1 = false;
			for (Object key1 : p1.keySet()) {
				if (key1.equals(key2)) {
					foundKey1 = true;
					break;
				}
			}
			
			if (foundKey1) {
				//ok
			} else {
				System.out.println("> "+key2+"="+p2.getProperty((String) key2));
			}
		}
		
		
	}

}
