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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;

public class BandRatioNormalizerOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(BandRatioNormalizerOperation.class);
	private static final int DATAINPUTIDX = 4;

	public BandRatioNormalizerOperation() {
		super("bandRNrmlzr", "Normalise to new center and amplitude keeping the original distance ratios to center",
				new NumberOperation("newcenter","newCenter","new center", new NumberValue(0.0)),
				new NumberOperation("actualCenter","actualCenter","actual center", new NumberValue(0.0)), 
				new NumberOperation("distanceToNewCenter","distanceToNewCenter","distance to new center", new NumberValue(1.0)),
				new NumberOperation("distanceToActualCenter","distanceToActualCenter","distance to actual center", new NumberValue(1.0)),
				new DoubleMapOperation("Data to normalise"));
	}

	public BandRatioNormalizerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		double newCenter = ((NumberValue)inputs.get(0)).getValue(targetStock).doubleValue();
		double actualCenter = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();
		double distanceToNewCenter = ((NumberValue)inputs.get(2)).getValue(targetStock).doubleValue();
		double distanceToActualCenter = ((NumberValue)inputs.get(3)).getValue(targetStock).doubleValue();
		
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(DATAINPUTIDX, DATAINPUTIDX+1);
		
		ValueManipulator.InnerCalcFunc innerCalcFunc = data -> innerCalc(targetStock, newCenter, actualCenter, distanceToNewCenter, distanceToActualCenter, data);
		
		return ValueManipulator.doubleArrayExpender(this, DATAINPUTIDX, targetStock, parentRequiredStartShift, innerCalcFunc, numericableMapValue);
		
	}

	private NumericableMapValue innerCalc(TargetStockInfo targetStock, double newCenter, double actualCenter, double distanceToNewCenter, double distanceToActualCenter, List<NumericableMapValue> data) {
		NumericableMapValue doubleMapValue = new DoubleMapValue();
		try {
			SortedMap<Date, Double> value = data.get(0).getValue(targetStock);
			TreeMap<Date, Double> normalized = 
					value.entrySet().stream()
					.collect(Collectors.toMap(
							e -> e.getKey(), 
							e ->  ((e.getValue() - actualCenter)/distanceToActualCenter) * distanceToNewCenter + newCenter, 
							(a,b) -> a, TreeMap::new));
			doubleMapValue.getValue(targetStock).putAll(normalized);
			
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + ": " + e, e);
		}
		return doubleMapValue;
	}

}