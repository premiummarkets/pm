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
package com.finance.pms.portfolio.gui.charts;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;

// TODO: Auto-generated Javadoc
/**
 * The Enum JFreeChartTimePeriod.
 * 
 * @author Guillaume Thoreton
 */
public enum JFreeChartTimePeriod {
	
	/** The SECOND. */
	SECOND (Second.class,1000),
	
	/** The MINUTE. */
	MINUTE (Minute.class,60*1000),
	
	/** The HOUR. */
	HOUR (Hour.class,60*60*1000),
	
	/** The DAY. */
	DAY (Day.class,24*60*60*1000);
	
	/** The period. */
	Class<? extends RegularTimePeriod> period;
	
	/** The period length. */
	Integer periodLength;

	/**
	 * Instantiates a new j free chart time period.
	 * 
	 * @param period the period
	 * @param periodLength the period length
	 * 
	 * @author Guillaume Thoreton
	 */
	private JFreeChartTimePeriod(Class<? extends RegularTimePeriod> period, Integer periodLength) {
		this.period = period;
		this.periodLength = periodLength;
	}
	
	/**
	 * Gets the period.
	 * 
	 * @return the period
	 * 
	 * @author Guillaume Thoreton
	 */
	public Class<? extends RegularTimePeriod> getPeriod() {
		return period;
	}

	/**
	 * Gets the period length.
	 * 
	 * @return the period length
	 */
	public Integer getPeriodLength() {
		return periodLength;
	}

	/**
	 * Sets the period length.
	 * 
	 * @param periodLength the new period length
	 */
	public void setPeriodLength(Integer periodLength) {
		this.periodLength = periodLength;
	}
	
	
}
