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
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.MySimpleRegression;
import com.finance.pms.events.scoring.functions.StatsFunction;

@XmlRootElement
public class StatsOperation extends PMWithDataOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(StatsOperation.class);

	public StatsOperation() {
		super("stat", "Moving statistics",
				new NumberOperation("number","movingPeriod","Moving period. This will be reflected in number of days (*7/5), independent of effective available data. 'NaN' means window == data set size", new NumberValue(21.0)),
				new DoubleMapOperation());
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sma", "mstdev", "msimplereg", "msum"})));
	}

	public StatsOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Double period = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);

		try {
			//Default is identity
			StatsFunction statFunction = new MyApacheStats(new AbstractUnivariateStatistic() {
				@Override
				public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
					return values[begin+length-1];
				}
				@Override
				public UnivariateStatistic copy() {
					return this;
				}
			});

			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			if (outputSelector != null && outputSelector.equalsIgnoreCase("sma")) {
				statFunction = new MyApacheStats(new Mean());
			} 
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mstdev")) {
				statFunction = new MyApacheStats(new StandardDeviation());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("msimplereg")) {
				statFunction = new MySimpleRegression();
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("msum")) {
				statFunction = lookBack -> lookBack.values().stream().filter(e -> !Double.isNaN(e)).reduce((r, e) -> r + e).orElse(0d);
			}

			if (period.isNaN()) {
				double sEvaluate = statFunction.mEvaluate(data);
				TreeMap<Date, Double> collected = data.keySet().stream().collect(Collectors.toMap(k -> k, k -> sEvaluate, (a, b) -> a, TreeMap<Date,Double>::new));
				return new DoubleMapValue(collected);
			} else {
				return new DoubleMapValue(MapUtils.movingStat(data, targetStock.getStartDate(thisStartShift), period.intValue(), statFunction));
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new DoubleMapValue();

	}

	@Override
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (int i = 0; i < 1; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}


}