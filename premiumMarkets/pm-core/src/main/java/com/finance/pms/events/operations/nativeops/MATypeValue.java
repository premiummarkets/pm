/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.events.operations.nativeops;

import javax.xml.bind.annotation.XmlElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.tictactec.ta.lib.MAType;

public class MATypeValue extends Value<MAType> implements Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(MATypeValue.class);
	
	@XmlElement
	MAType maTypeValue;

	@SuppressWarnings("unused")
	private MATypeValue() {
		super();
	}
	
	public MATypeValue(String value) {
		super();
		this.maTypeValue = MAType.valueOf((""+value.charAt(0)).toUpperCase() + value.toLowerCase().substring(1));
	}
	

	@Override
	public MAType getValue(TargetStockInfo targetStock) {
		return maTypeValue;
	}
	
	

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : value "+maTypeValue;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		try {
			MATypeValue clone = (MATypeValue) super.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public String getValueAsString() {
		return maTypeValue.name();
	}

}
