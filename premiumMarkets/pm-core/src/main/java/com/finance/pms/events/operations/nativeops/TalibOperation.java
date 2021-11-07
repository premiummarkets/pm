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
package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;

public abstract class TalibOperation extends DoubleMapOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(TalibOperation.class);

	@SuppressWarnings("unused")
	private TalibOperation() {
		super();
	}

	public TalibOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public TalibOperation(String reference, String description, Operation ... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		NumericableMapValue ret = new DoubleMapValue();
		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();

		try {

			SortedMap<Date, Double> outputMap = innerCalculation(targetStock, outBegIdx, outNBElement, inputs);
			ret.getValue(targetStock).putAll(outputMap);
			return ret;

		} catch (TalibException e) {
			LOGGER.warn("Talib Operation calculation error " + this + " for " + targetStock.getStock().getFriendlyName(), e);
			throw new RuntimeException(e);
		}
	}

	protected abstract SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException;

	protected SortedMap<Date, Double> arrayToMap(List<Date> dateKeySet, double[] array, int shift) {
		int j = 0;
		SortedMap<Date, Double> map = new TreeMap<Date, Double>();
		for (Date date : dateKeySet){
			if ((j >= shift) && (j - shift) < array.length) {
				map.put(date, array[j - shift]);
			}
			j++;
		}
		return map;
	}

	protected SortedMap<Date, Double> arrayToMap(List<Date> dateKeySet, int[] array, int shift) {
		int j = 0;
		SortedMap<Date, Double> map = new TreeMap<Date, Double>();
		for (Date date : dateKeySet){
			if ((j >= shift) && (j - shift) < array.length) {
				map.put(date, Double.valueOf(array[j - shift]));
			}
			j++;
		}
		return map;
	}

	protected double[] mapToArray(List<Date> smallestDateKeySet, SortedMap<Date, Double> map) {
//		double[] array = new double[smallestDateKeySet.size()];
//		int i=0;
//		for (Date date : map.keySet()) {
//			if (smallestDateKeySet.contains(date)) {
//				array[i] = map.get(date);
//				i++;
//			}
//		}
//		return array;
		
		return map.entrySet().stream()
		.filter(e -> smallestDateKeySet.contains(e.getKey()))
		.mapToDouble(e -> e.getValue().doubleValue())
		.toArray();
	}

}
