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
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.MySimpleRegression;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.StatsFunction;

@XmlRootElement
public class StatsOperation extends PMWithDataOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(StatsOperation.class);

	public StatsOperation() {
		super("stat", "Moving statistics",
				new NumberOperation("number", "movingPeriod", "Moving period in data points. 'NaN' means window == data set size", new NumberValue(21.0)),
				new DoubleMapOperation());
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sma", "mstdev", "msimplereg", "msum", "mmin", "mmax", "mtanhnorm"})));
	}

	public StatsOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(
			TargetStockInfo targetStock, String thisCallStack, 
			int thisOutputRequiredStartShiftFromParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Double period = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(1, 2);

		try {
			
			final StatsFunction statFunction;
			
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
				statFunction = new MyApacheStats(new Sum());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mmin")) {
				statFunction = new MyApacheStats(new Min());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mmax")) {
				statFunction = new MyApacheStats(new Max());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mtanhnorm")) { //Sliding bandNormalizer[-1,1,0]
				statFunction = new StatsFunction() {
					
					@Override
					public double mEvaluate(SortedMap<Date, Double> subMap) {
						Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, subMap.firstKey(), subMap.lastKey(), -1, 1, 0);
						SortedMap<Date, Double> normalized = normalizer.normalised(subMap);
						return normalized.get(normalized.lastKey());
					}
					
					@Override
					public SortedMap<Date, Double> evaluate(SortedMap<Date, Double> subMap) {
						Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, subMap.firstKey(), subMap.lastKey(), -1, 1, 0);
						SortedMap<Date, Double> normalized = normalizer.normalised(subMap);
						return normalized;
					}
				};
			} else {
				//Default is identity
				statFunction = new MyApacheStats(new AbstractUnivariateStatistic() {
					@Override
					public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
						return values[begin + length-1];
					}
					@Override
					public UnivariateStatistic copy() {
						return this;
					}
				});
			}

			if (period.isNaN()) {
				ValueManipulator.InnerCalcFunc innerCalcFunc = data -> {
					return new DoubleMapValue(statFunction.evaluate(data.get(0).getValue(targetStock)));
				};
				return ValueManipulator.doubleArrayExpender(this, 1, targetStock, thisOutputRequiredStartShiftFromParent, innerCalcFunc, numericableMapValue);
			} else {
				Date startDate = targetStock.getStartDate(thisInputOperandsRequiredShiftFromThis);
				ValueManipulator.InnerCalcFunc innerCalcFunc = data -> {
					SortedMap<Date, Double> movingStat = MapUtils.movingStat(data.get(0).getValue(targetStock), startDate, period.intValue(), statFunction);
					return new DoubleMapValue(movingStat);
				};
				return ValueManipulator.doubleArrayExpender(this, 1, targetStock, thisOutputRequiredStartShiftFromParent, innerCalcFunc, numericableMapValue);
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new DoubleMapValue();

	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return IntStream.range(0, 1)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					if (numberOperand instanceof NumberOperation) {
						return ((NumberValue) numberOperand.getParameter()).getValue(null).intValue();
					} else {
						return getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift);
					}
				})
				.reduce(0, (r, e) -> r + e);
	}
	
	@Override
	public String toFormulaeShort() {
		String thisShort = getOutputSelector().substring(1,Math.min(getOutputSelector().length(), 4)) + "_" + ((StringableValue) getOperands().get(0).getParameter()).getValueAsString();
		String opsFormulaeShort = super.toFormulaeShort();
		return thisShort + ((opsFormulaeShort.isEmpty())? "" : "_" + opsFormulaeShort);
	}


}
