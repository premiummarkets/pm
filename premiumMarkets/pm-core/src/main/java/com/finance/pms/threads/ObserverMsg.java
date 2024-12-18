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
package com.finance.pms.threads;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.datasources.shares.Stock;

public class ObserverMsg {
	
	private Stock stock;
	private ObsKey key;
	private List<NameValuePair> nameValuePairs;
	
	public enum ObsKey {
		INITMSG, DONE, SCHEDULE, PRGSMSG, NONE, RESETTOTAL, DATA_POST;
	}

	public ObserverMsg(Stock stock, ObsKey key) {
		super();
		this.stock = stock;
		this.key = key;
		nameValuePairs = new ArrayList<ObserverMsg.NameValuePair>();
	}
	
	public ObserverMsg(Stock stock, ObsKey key, Object ...values) {
		super();
		this.stock = stock;
		this.key = key;
		nameValuePairs = new ArrayList<ObserverMsg.NameValuePair>();
		for (Object object : values) {
			nameValuePairs.add(new NameValuePair("", object));
		}
		
	}

	public void addNameValue(String name, Object value) {
		nameValuePairs.add(new NameValuePair(name, value));
	}

	public Stock getStock() {
		return stock;
	}

	public ObsKey getKey() {
		return key;
	}

	public List<NameValuePair> getNameValuePairs() {
		return nameValuePairs;
	}
	
	public class NameValuePair {
		
		public String name;
		public Object value;
		
		public NameValuePair(String name, Object value) {
			super();
			this.name = name;
			this.value = value;
		}
		
		
	}
	

}
