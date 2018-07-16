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
package com.finance.pms.events.operations.conditional;

import java.util.Date;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.MapValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.TimedMapValue;
import com.finance.pms.events.operations.Value;

public class BooleanMapValue extends Value<SortedMap<Date, Boolean>> implements TimedMapValue<Boolean>, Cloneable {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(BooleanMapValue.class);
	
	SortedMap<Date, Boolean> map;
	

	public BooleanMapValue() {
		map = new  TreeMap<Date, Boolean>();
	}

	public BooleanMapValue(Set<Date> keySet, boolean initValue) {
		map = new  TreeMap<Date, Boolean>();
		for (Date date : keySet) {
			map.put(date, initValue);
		}
	}

	@Override
	public SortedMap<Date, Boolean> getValue(TargetStockInfo targetStock) {
		return map;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : size is "+map.size() + ((map.size() > 0)?", first key "+map.firstKey()+ ", last key "+map.lastKey():"");
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		try {
			BooleanMapValue clone = (BooleanMapValue) super.clone();
			clone.map = (SortedMap<Date, Boolean>) ((TreeMap<Date, Boolean>)this.map).clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

}
