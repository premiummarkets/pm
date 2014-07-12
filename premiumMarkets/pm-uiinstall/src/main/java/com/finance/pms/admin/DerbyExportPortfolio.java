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
package com.finance.pms.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


// TODO: Auto-generated Javadoc
/**
 * The Class DerbyExport.
 * 
 * @author Guillaume Thoreton
 */
public class DerbyExportPortfolio {

	
	/** The dat dir name. */
	//final String datDirName = "distrib/export_nasdaq-yahoo";
	//final String datDirName = "distrib/export";
	//final String datDirName = "my_export";
	//final String datDirName = "distrib/export_nasdaq-yahoo";
	final String datDirName = "/home/guil/tmp";
	//final String datDirName = "/home/guil/Documents/Comptes/Gestion/PMS";
	
	/** The db name. */
	//final String dbName = "piggymarketsqueak_euronext_yahoo";
	//final String dbName = "piggymarketsqueak_initialdb";
	final String dbName = "piggymarketsqueak";
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {
		DerbyExportPortfolio de=new DerbyExportPortfolio();
		de.exportDB();
	}
	
	
	/**
	 * Export db.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void exportDB() {
		
		try {
//			PreparedStatement psEvents=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
//			psEvents.setString(1,null);
//			psEvents.setString(2,"EVENTS");
//			psEvents.setString(3,datDirName+"/EVENTS.dat");
//			psEvents.setString(4,";");
//			psEvents.setString(5,null);
//			psEvents.setString(6,null);
//			psEvents.execute();
			
			PreparedStatement psPort=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
			psPort.setString(1,null);
			psPort.setString(2,"PORTFOLIO");
			psPort.setString(3,datDirName+"/PORTFOLIO.dat");
			psPort.setString(4,";");
			psPort.setString(5,null);
			psPort.setString(6,null);
			psPort.execute();
			
			PreparedStatement psPortName=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
			psPortName.setString(1,null);
			psPortName.setString(2,"PORTFOLIO_NAME");
			psPortName.setString(3,datDirName+"/PORTFOLIO_NAME.dat");
			psPortName.setString(4,";");
			psPortName.setString(5,null);
			psPortName.setString(6,null);
			psPortName.execute();
			
			PreparedStatement psAlerts=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
			psAlerts.setString(1,null);
			psAlerts.setString(2,"ALERTS_ALERTS");
			psAlerts.setString(3,datDirName+"/ALERTS_ALERTS.dat");
			psAlerts.setString(4,";");
			psAlerts.setString(5,null);
			psAlerts.setString(6,null);
			psAlerts.execute();
			
			PreparedStatement psAlertsAlerts=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
			psAlertsAlerts.setString(1,null);
			psAlertsAlerts.setString(2,"ALERTS");
			psAlertsAlerts.setString(3,datDirName+"/ALERTS.dat");
			psAlertsAlerts.setString(4,";");
			psAlertsAlerts.setString(5,null);
			psAlertsAlerts.setString(6,null);
			psAlertsAlerts.execute();
//			
//			PreparedStatement psShares=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
//			psShares.setString(1,null);
//			psShares.setString(2,"SHARES");
//			psShares.setString(3,datDirName+"/SHARES.dat");
//			psShares.setString(4,";");
//			psShares.setString(5,null);
//			psShares.setString(6,null);
//			psShares.execute();
			

//			
//			PreparedStatement psQuotation=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
//			psQuotation.setString(1,null);
//			psQuotation.setString(2,"QUOTATIONS");
//			psQuotation.setString(3,datDirName+"/QUOTATIONS.dat");
//			psQuotation.setString(4,";");
//			psQuotation.setString(5,null);
//			psQuotation.setString(6,null);
//			psQuotation.execute();
			
	
//			PreparedStatement psQuotation=this.connect(true).prepareStatement("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE (?,?,?,?,?,?)");
//			psQuotation.setString(1,null);
//			psQuotation.setString(2,"NASDAQ_QUOTATIONS");
//			psQuotation.setString(3,"distrib/"+datDirName+"/NASDAQ_QUOTATIONS.dat");
//			psQuotation.setString(4,";");
//			psQuotation.setString(5,null);
//			psQuotation.setString(6,null);
//			psQuotation.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Connect.
	 * 
	 * @param autocommit the autocommit
	 * 
	 * @return the connection
	 * 
	 * @author Guillaume Thoreton
	 */
	private Connection connect(boolean autocommit) {
		String connectionURL;
		Connection conn = null;
			try {
				// Resolve the className
				try {
					Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				} catch (java.lang.ExceptionInInitializerError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Set up the connection
				connectionURL = "jdbc:" +  "derby";
				//connectionURL = connectionURL + ":" + "/opt/USERDATA/derby/";
				connectionURL = connectionURL + ":" + "/home/guil/Developpement/newEclipse/premiumMarkets/pm-uiinstall/distrib/derby/";
				connectionURL = connectionURL + dbName;
				conn = DriverManager.getConnection(connectionURL);
				conn.setAutoCommit(autocommit);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return conn;
	}
}
