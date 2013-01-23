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
package com.finance.pms.datasources.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.finance.pms.threads.SourceClient;



// TODO: Auto-generated Javadoc
/**
 * The Class MyDBConnection.
 * 
 * @author Guillaume Thoreton
 */
public class MyDBConnection implements SourceClient {
	
	//private static MyLogger LOGGER = MyLogger.getLogger(MyDBConnection.class);

	//MyConnection conn;
	Connection conn;

	
	public MyDBConnection(Connection conn) {
		//this.conn=new MyConnection(conn);
		this.conn = conn;
	}
	
	public Connection getConn() {
		return conn;
	}


	@Override
	public Boolean isValid() {
		
		try {
			return !this.getConn().isClosed() && this.getConn().isValid(60);
		} catch (SQLException e) {
			return false;
		}
		
	}
	
//	class MyConnection implements Connection {
//		
//		
//		Connection connection;
//	
//		public MyConnection(Connection connection) {
//			super();
//			this.connection = connection;
//		}
//
//		public <T> T unwrap(Class<T> iface) throws SQLException {
//			return connection.unwrap(iface);
//		}
//
//		public boolean isWrapperFor(Class<?> iface) throws SQLException {
//			return connection.isWrapperFor(iface);
//		}
//
//		public Statement createStatement() throws SQLException {
//			return connection.createStatement();
//		}
//
//		public PreparedStatement prepareStatement(String sql) throws SQLException {
//			//LOGGER.info("Query : "+sql);
//			return connection.prepareStatement(sql);
//		}
//
//		public CallableStatement prepareCall(String sql) throws SQLException {
//			return connection.prepareCall(sql);
//		}
//
//		public String nativeSQL(String sql) throws SQLException {
//			return connection.nativeSQL(sql);
//		}
//
//		public void setAutoCommit(boolean autoCommit) throws SQLException {
//			connection.setAutoCommit(autoCommit);
//		}
//
//		public boolean getAutoCommit() throws SQLException {
//			return connection.getAutoCommit();
//		}
//
//		public void commit() throws SQLException {
//			connection.commit();
//		}
//
//		public void rollback() throws SQLException {
//			connection.rollback();
//		}
//
//		public void close() throws SQLException {
//			connection.close();
//		}
//
//		public boolean isClosed() throws SQLException {
//			return connection.isClosed();
//		}
//
//		public DatabaseMetaData getMetaData() throws SQLException {
//			return connection.getMetaData();
//		}
//
//		public void setReadOnly(boolean readOnly) throws SQLException {
//			connection.setReadOnly(readOnly);
//		}
//
//		public boolean isReadOnly() throws SQLException {
//			return connection.isReadOnly();
//		}
//
//		public void setCatalog(String catalog) throws SQLException {
//			connection.setCatalog(catalog);
//		}
//
//		public String getCatalog() throws SQLException {
//			return connection.getCatalog();
//		}
//
//		public void setTransactionIsolation(int level) throws SQLException {
//			connection.setTransactionIsolation(level);
//		}
//
//		public int getTransactionIsolation() throws SQLException {
//			return connection.getTransactionIsolation();
//		}
//
//		public SQLWarning getWarnings() throws SQLException {
//			return connection.getWarnings();
//		}
//
//		public void clearWarnings() throws SQLException {
//			connection.clearWarnings();
//		}
//
//		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
//			return connection.createStatement(resultSetType, resultSetConcurrency);
//		}
//
//		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
//			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
//		}
//
//		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
//			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
//		}
//
//		public Map<String, Class<?>> getTypeMap() throws SQLException {
//			return connection.getTypeMap();
//		}
//
//		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
//			connection.setTypeMap(map);
//		}
//
//		public void setHoldability(int holdability) throws SQLException {
//			connection.setHoldability(holdability);
//		}
//
//		public int getHoldability() throws SQLException {
//			return connection.getHoldability();
//		}
//
//		public Savepoint setSavepoint() throws SQLException {
//			return connection.setSavepoint();
//		}
//
//		public Savepoint setSavepoint(String name) throws SQLException {
//			return connection.setSavepoint(name);
//		}
//
//		public void rollback(Savepoint savepoint) throws SQLException {
//			connection.rollback(savepoint);
//		}
//
//		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
//			connection.releaseSavepoint(savepoint);
//		}
//
//		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
//			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
//		}
//
//		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
//			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
//		}
//
//		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
//			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
//		}
//
//		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
//			return connection.prepareStatement(sql, autoGeneratedKeys);
//		}
//
//		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
//			return connection.prepareStatement(sql, columnIndexes);
//		}
//
//		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
//			return connection.prepareStatement(sql, columnNames);
//		}
//
//		public Clob createClob() throws SQLException {
//			return connection.createClob();
//		}
//
//		public Blob createBlob() throws SQLException {
//			return connection.createBlob();
//		}
//
//		public NClob createNClob() throws SQLException {
//			return connection.createNClob();
//		}
//
//		public SQLXML createSQLXML() throws SQLException {
//			return connection.createSQLXML();
//		}
//
//		public boolean isValid(int timeout) throws SQLException {
//			return connection.isValid(timeout);
//		}
//
//		public void setClientInfo(String name, String value) throws SQLClientInfoException {
//			connection.setClientInfo(name, value);
//		}
//
//		public void setClientInfo(Properties properties) throws SQLClientInfoException {
//			connection.setClientInfo(properties);
//		}
//
//		public String getClientInfo(String name) throws SQLException {
//			return connection.getClientInfo(name);
//		}
//
//		public Properties getClientInfo() throws SQLException {
//			return connection.getClientInfo();
//		}
//
//		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
//			return connection.createArrayOf(typeName, elements);
//		}
//
//		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
//			return connection.createStruct(typeName, attributes);
//		}
//
//		public void setSchema(String schema) throws SQLException {
//			connection.setSchema(schema);
//		}
//
//		public String getSchema() throws SQLException {
//			return connection.getSchema();
//		}
//
//		public void abort(Executor executor) throws SQLException {
//			connection.abort(executor);
//		}
//
//		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
//			connection.setNetworkTimeout(executor, milliseconds);
//		}
//
//		public int getNetworkTimeout() throws SQLException {
//			return connection.getNetworkTimeout();
//		};
		
		
//	}
}
