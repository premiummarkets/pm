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
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.Trimmer;

public class BandNormalizerOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(BandNormalizerOperation.class);
	private static final int DATAINPUTIDX = 4;
	
	private int trimmerSlidingPeriod = 251;

	public BandNormalizerOperation() {
		super("bandNormalizer", "Normalise the data between and to the lower and the upper threshold",
				new NumberOperation("lower threshold"), new NumberOperation("upper threshold"),
				new NumberOperation("actual center","actualCenter","Keep distance ratio of min and max to the data relative specified center (NaN accepted).", new NumberValue(Double.NaN)),
				new NumberOperation("integer", "trimFactor", "Stdev trim factor. Will only work for oscillators (NaN accepted).", new NumberValue(Double.NaN)),
				new DoubleMapOperation("Data to normalise"));
	}

	public BandNormalizerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		double lowerThreshold = ((NumberValue)inputs.get(0)).getValue(targetStock).doubleValue();
		double upperThreshold = ((NumberValue)inputs.get(1)).getValue(targetStock).doubleValue();
		double actualCenter = ((NumberValue)inputs.get(2)).getValue(targetStock).doubleValue();
		Double trimFactor = ((NumberValue)inputs.get(3)).getValue(targetStock).doubleValue();

		//Calc
		//SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATAINPUTIDX)).getValue(targetStock);
		//return innerCalc(targetStock, lowerThreshold, upperThreshold, actualCenter, trimFactor, data);
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(DATAINPUTIDX, DATAINPUTIDX+1);
		
		ValueManipulator.InnerCalcFunc innerCalcFunc = data -> innerCalc(targetStock, lowerThreshold, upperThreshold, actualCenter, trimFactor, data);
		
		return ValueManipulator.doubleArrayExpender(this, DATAINPUTIDX, targetStock, parentRequiredStartShift, innerCalcFunc, numericableMapValue);
	}

	private NumericableMapValue innerCalc(TargetStockInfo targetStock, double lowerThreshold, double upperThreshold, double actualCenter, Double trimFactor, List<NumericableMapValue> data) {
		NumericableMapValue ret = new DoubleMapValue();
		try {
			trimmerSlidingPeriod=251;
			SortedMap<Date, Double> trimmed = data.get(0).getValue(targetStock);
			if (!Double.isNaN(trimFactor)) {
				Trimmer trimmer = new Trimmer(targetStock.getStock(), getRequiredStockData(), trimmerSlidingPeriod, trimFactor.intValue(), trimmed.firstKey(), trimmed.lastKey());
				trimmed = trimmer.sTrimmed(trimmed);
			}
			
			Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, trimmed.firstKey(), trimmed.lastKey(), lowerThreshold, upperThreshold, actualCenter);
			SortedMap<Date, Double> normalized = normalizer.normalised(trimmed);
			
			ret.getValue(targetStock).putAll(normalized);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}
		return ret;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		double trimFactor = ((NumberValue) getOperands().get(DATAINPUTIDX-1).getOrRunParameter(targetStock).orElse(new NumberValue(0.0))).getValue(targetStock).doubleValue();
		if (Double.isNaN(trimFactor)) return 0;
		return trimmerSlidingPeriod;
	}
	
	@Override
	public void interrupt() throws Exception {
		CalculateThreadExecutor.getRandomInfiniteExecutorInstance().shutdownNow();
	}

}
