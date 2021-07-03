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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.Trimmer;

public class BandNormalizerOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(BandNormalizerOperation.class);
	private static final int DATAINPUTIDX = 4;
	
	private int slidingPeriod = 5*251;

	public BandNormalizerOperation() {
		super("bandNormalizer", "Normalise the data between the lower and the upper threshold",
				new NumberOperation("lower threshold"), new NumberOperation("upper threshold"),
				new StringOperation("boolean","keepZero","Keep distance ratio of min and max to zero as the original", new StringValue("FALSE")),
				new NumberOperation("integer", "trimFactor", "Stdev trim factor. Will only work for oscillators", new NumberValue(Double.NaN)),
				new DoubleMapOperation("Data to normalise"));
	}

	public BandNormalizerOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int lowerThreshold = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		int upperThreshold = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		Boolean keepZero = Boolean.valueOf(((StringValue)inputs.get(2)).getValue(targetStock));
		Double trimFactor = ((NumberValue)inputs.get(3)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATAINPUTIDX)).getValue(targetStock);

		//Calc
		NumericableMapValue ret = new DoubleMapValue();
		try {
			
			SortedMap<Date, Double> trimmed = data;
			if (!Double.isNaN(trimFactor)) {
				Trimmer trimmer = new Trimmer(slidingPeriod, trimFactor.intValue(), data.firstKey(), data.lastKey());
				trimmed = trimmer.sTrimmed(data);
			}
			
			Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, trimmed.firstKey(), trimmed.lastKey(), lowerThreshold, upperThreshold, keepZero);
			SortedMap<Date, Double> normalized = normalizer.normalised(trimmed);
			
			ret.getValue(targetStock).putAll(normalized);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " +e, e);
		}
		return ret;
	}

	@Override
	public int operandsRequiredStartShift() {
		double trimFactor = ((NumberValue)getOperands().get(DATAINPUTIDX-1).getParameter()).getValue(null).doubleValue();
		if (Double.isNaN(trimFactor)) return 0;
		return slidingPeriod;
	}

}
