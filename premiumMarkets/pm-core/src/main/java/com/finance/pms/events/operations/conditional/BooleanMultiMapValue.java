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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.MultiMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;

public class BooleanMultiMapValue extends BooleanMapValue implements MultiMapValue {

	private static MyLogger LOGGER = MyLogger.getLogger(BooleanMapValue.class);

	private Map<String, NumericableMapValue> additionalOutputs;
	private Map<String, Type> additionalOutputsTypes;

	public BooleanMultiMapValue() {
		super();
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	public BooleanMultiMapValue(Set<Date> keySet, boolean initValue) {
		super(keySet, initValue);
		additionalOutputs = new TreeMap<String, NumericableMapValue>();
		additionalOutputsTypes = new HashMap<String, Type>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": size is " + map.size() + ((map.size() > 0)?", first key " + map.firstKey()+ ", last key " + map.lastKey():"");
	}

	@Override
	public Object clone() {
		try {
			BooleanMultiMapValue clone = (BooleanMultiMapValue) super.clone();
			clone.additionalOutputs = new HashMap<String, NumericableMapValue>();
			for (String outputKey : additionalOutputs.keySet()) {
				NumericableMapValue  addOutputClone = (NumericableMapValue) (additionalOutputs.get(outputKey)).clone();
				clone.additionalOutputs.put(outputKey, addOutputClone);
				clone.additionalOutputsTypes.put(outputKey, additionalOutputsTypes.get(outputKey));
			}
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}

	@Override
	public Map<String, NumericableMapValue> getAdditionalOutputs() {
		return additionalOutputs;
	}

	@Override
	public Map<String, Type> getAdditionalOutputsTypes() {
		return additionalOutputsTypes;
	}
	
	@Override
	public BooleanMultiMapValue filtered(Date endDate) {
		return new BooleanMultiMapValue() {

			@Override
			public String toString() {
				return "[" + endDate + "] view of: " + BooleanMultiMapValue.this.toString();
			}

			@Override
			public Object clone() {
				return BooleanMultiMapValue.this.clone();
			}

			@Override
			public Map<String, NumericableMapValue> getAdditionalOutputs() {
				return super.getAdditionalOutputs().entrySet().stream()
						.collect(HashMap::new, (m,e) -> m.put(e.getKey(), e.getValue().filtered(endDate)), HashMap::putAll);
			}

			@Override
			public Map<String, Type> getAdditionalOutputsTypes() {
				return BooleanMultiMapValue.this.getAdditionalOutputsTypes();
			}

			@Override
			public BooleanMultiMapValue filtered(Date endDate) {
				return BooleanMultiMapValue.this.filtered(endDate);
			}

			@Override
			public SortedMap<Date, Boolean> getValue(TargetStockInfo targetStock) {
				return BooleanMultiMapValue.this.getValue(targetStock).headMap(DateUtils.addDays(endDate, 1));
			}
			
		};
	}

}
