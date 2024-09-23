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
package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.scoring.functions.Trimmer;
import com.finance.pms.events.scoring.functions.Trimmer.TrimType;

public class TrimOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(TrimOperation.class);
	private static final int DATAINPUTIDX = 2;

	public TrimOperation() {
		super("trim", "Trim data according to the score function and the factor threshold over the full data set. May only work for oscillators.",
				new StringOperation("type", "trimType","The type of trim among " + Arrays.toString(TrimType.values()), new StringValue(TrimType.ModZscore.name())),
				new NumberOperation("integer", "trimFactor", "Stdev trim factor.", new NumberValue(Double.NaN)),
				new DoubleMapOperation("Data to normalise"));
	}

	public TrimOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		TrimType trimType = TrimType.valueOf(((StringValue)inputs.get(0)).getValue(targetStock));
		Double trimFactor = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();

		//Calc
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(DATAINPUTIDX, DATAINPUTIDX+1);
		
		ValueManipulator.InnerCalcFunc innerCalcFunc = data -> innerCalc(targetStock, trimType, trimFactor, data);
		
		return ValueManipulator.doubleArrayExpender(this, DATAINPUTIDX, targetStock, parentRequiredStartShift, innerCalcFunc, numericableMapValue);
	}

	private NumericableMapValue innerCalc(TargetStockInfo targetStock, TrimType trimType, Double trimFactor, List<NumericableMapValue> data) {
		NumericableMapValue ret = new DoubleMapValue();
		try {
			
			SortedMap<Date, Double> values = data.get(0).getValue(targetStock);
			Trimmer<Double> trimmer =  Trimmer.build(Double.class, trimType, trimFactor, values);
			values = trimmer.trim(values);
			
			ret.getValue(targetStock).putAll(values);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + ": " + e, e);
		}
		return ret;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}
	
	@Override
	public void interrupt() throws Exception {
		CalculateThreadExecutor.getRandomInfiniteExecutorInstance().shutdownNow();
	}

}
