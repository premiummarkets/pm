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
package com.finance.pms.admin;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;


// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseExportSample.
 * 
 * @author Guillaume Thoreton
 */
public class DatabaseExportSample
{
    
    /**
     * The main method.
     * 
     * @param args the arguments
     * 
     * @throws Exception the exception
     * 
     * @author Guillaume Thoreton
     */
    public static void main(String[] args) throws Exception
    {
        // database connection
    	
        //Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
		//        Connection jdbcConnection = DriverManager.getConnection(
		//                "jdbc:hsqldb:sample", "sa", "");
    	
    	//jdbc:postgresql:localhost:5432/postgres?user=postgres&password=postgres
    	
    	//Class<?> driverClass = Class.forName("org.postgresql.Driver");
		Connection jdbcConnection = DriverManager.getConnection(
		                "jdbc:postgresql://localhost:5432/mov_shares","postgres","postgres");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
		//partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
		//partialDataSet.addTable("BAR");
        partialDataSet.addTable("LOOKUP");
        partialDataSet.addTable("shares", "SELECT * FROM shares WHERE symbol='FTE.FR' or symbol='PAJ.FR'" );
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
//        IDataSet fullDataSet = connection.createDataSet();
//        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
        
        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
//        String[] depTableNames = 
//          TablesDependencyHelper.getAllDependentTables( connection, "X" );
//        IDataSet depDataset = connection.createDataSet( depTableNames );
//        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));  
        
        // write DTD file
        FlatDtdDataSet.write(connection.createDataSet(),
                new FileOutputStream("com.finance.pms.dtd"));
        
    }
}