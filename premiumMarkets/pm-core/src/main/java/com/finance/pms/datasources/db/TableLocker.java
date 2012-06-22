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
package com.finance.pms.datasources.db;

// TODO: Auto-generated Javadoc
/**
 * The Class TableLocker.
 * 
 * @author Guillaume Thoreton
 */
public class TableLocker {
	
	/** The table name. */
	private String tableName;
	
	/** The lock mode value. */
	private LockMode lockModeValue;

	/**
	 * Instantiates a new table locker.
	 * 
	 * @author Guillaume Thoreton
	 */
	public TableLocker() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * Instantiates a new table locker.
	 * 
	 * @param tableName the table name
	 * @param lockModeValue the lock mode value
	 * 
	 * @author Guillaume Thoreton
	 */
	public TableLocker(String tableName,LockMode lockModeValue) {
		super();
		this.tableName = tableName;
		this.lockModeValue = lockModeValue;
	}
	
	/**
	 * Gets the table name.
	 * 
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * Sets the table name.
	 * 
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * Gets the lock mode value.
	 * 
	 * @return the lock mode value
	 */
	public LockMode getLockModeValue() {
		return lockModeValue;
	}
	
	/**
	 * Sets the lock mode.
	 * 
	 * @param lockModeValue the new lock mode
	 */
	public void setLockMode(LockMode lockModeValue) {
		this.lockModeValue = lockModeValue;
	}
	
	
	/**
	 * The Enum LockMode.
	 * 
	 * @author Guillaume Thoreton
	 */
	public enum LockMode { 
		
		/** The WRITINGLOCK. */
		WRITINGLOCK (" IN ROW EXCLUSIVE MODE"), 
		
		/** The READINGLOCK. */
		READINGLOCK (" IN SHARE MODE"), 
		
		/** The NOLOCK. */
		NOLOCK ("");
		
		/** The lock mode. */
		private final String lockMode; 
		
		/**
		 * Instantiates a new lock mode.
		 * 
		 * @param lockMode the lock mode
		 * 
		 * @author Guillaume Thoreton
		 */
		private LockMode(String lockMode) {
			this.lockMode = lockMode;
		}
		
		/**
		 * Gets the lock mode.
		 * 
		 * @return the lock mode
		 */
		public String getLockMode() {
			return lockMode;
		}
		
	};
}
