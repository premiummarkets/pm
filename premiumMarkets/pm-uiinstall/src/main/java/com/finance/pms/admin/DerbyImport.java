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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DerbyImport {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DerbyImport derbyImport = new DerbyImport();
		derbyImport.importDB();
	}
	
	public void importDB() {
//		DbInstaller dbInstaller = new DbInstaller();
//		try {
//			dbInstaller.importDB(
//					new File("/home/guil/"),
//					//"Developpement/Quotes/com.finance.pms/distrib/export_nasdaq-yahoo",
//					"tmp",
//					//"Developpement/Quotes/pms/distrib/export",
//					connect());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Connection connection = connect();
			PreparedStatement psQuotation = connection.prepareStatement("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (?,?,?,?,?,?,?)");
			psQuotation.setString(1, null);
			psQuotation.setString(2, "TREND_SUPPLEMENT");
			psQuotation.setString(3, "/home/guil/tmp/TREND_SUPPLEMENT.dat");
			psQuotation.setString(4, ";");
			psQuotation.setString(5, null);
			psQuotation.setString(6, null);
			psQuotation.setString(7, "1");
			
			psQuotation.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection connect() {
		Connection connection = null;
		String connectionURL;
			try {
				// Resolve the className
				try {
					//Class.forName(MainPMScmd.prefs.get("driver", "org.apache.derby.jdbc.EmbeddedDriver"));
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				} catch (java.lang.ExceptionInInitializerError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Set up the connection
				//connectionURL = "jdbc:derby:"+piggyMarketSqueakFolder.getAbsolutePath() +File.separator+"derby"+File.separator+"piggymarketsqueak";
				connectionURL = "jdbc:" +  "derby";
				connectionURL = connectionURL + ":" + "/home/guil/Documents/Comptes/Gestion/PMS/";
				connectionURL = connectionURL + "piggymarketsqueak";
				connection = DriverManager.getConnection(connectionURL);
				connection.setAutoCommit(true);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return connection;
	}
}
