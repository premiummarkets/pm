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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.MyApacheStats;

public class RecursiveOperation extends DoubleMapOperation {

	public RecursiveOperation() {
		this("recursive_", "recursive operation Y0 = V and Yn = f(Yn-1)",
				new NumberOperation("seedingPeriod","seedingPeriod", "One period on which the seeding is calculated (using a mean)", new NumberValue(1.0)),
				new DoubleMapOperation("seed","seed", "Use the first period values as a seed for the recursion", null),
				new DoubleMapOperation("paramsData", "calculusParamLists", "Timed calculus parameters", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"rates", "continuousRates", "EMA"})));
	}

	public RecursiveOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Params check
		Integer period = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue(); //Used in operationStartDateShift() calculation for seeding
		SortedMap<Date, Double> seedingData = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		@SuppressWarnings("rawtypes") List<? extends Value> paramsInputs = inputs.subList(1, inputs.size()); //The seed is included as the first data parameter by default

		Date startDateShift = targetStock.getStartDate(thisStartShift);

		//Build one inputs
		SortedMap<Date, List<Double>> oneInputParams = new TreeMap<>();
		for (Date date : ((NumericableMapValue) paramsInputs.get(0)).getDateKeys()) {
			if (date.compareTo(startDateShift) >= 0) {
				List<Double> valuesList = paramsInputs.stream()
						.map(i -> ((NumericableMapValue)i).getValue(targetStock).get(date))
						.collect(Collectors.toList());
				if (valuesList.stream().noneMatch(v -> v == null)) {
					oneInputParams.put(date, valuesList);
				}
			}
		}

		//Recursive
		final BiFunction<Double, List<Double>, Double> function;
		String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
		if (outputSelector != null && outputSelector.equalsIgnoreCase("rates")) {
			function = (y, params) -> y * (1 + params.get(1));
		}
		else if (outputSelector != null && outputSelector.equalsIgnoreCase("continuousRates")) {
			function = (y, params) -> y * Math.exp(params.get(1));
		}
		else if (outputSelector != null && outputSelector.equalsIgnoreCase("EMA")) {
			//TODO EMA = Price(t) * k + EMA(y) * (1 – k) where t = today, y = yesterday, N = number of days in EMA, k = 2/(N+1)
			//TODO EMA seed : Add the closing prices for the first period days together and divide them by period. <= ok as below
			//Param 0 (seed): data, Param 1: constant(period) => seed is param 0
			throw new NotImplementedException();
		} 
		else {
			function = (y, params) -> y; //Defaults to identity
		}

		//Seed
		Date seedFrom = new Date(startDateShift.getTime() - ((long)(period*7d/5d)) * (1000l * 60l * 60l * 24l));
		Collection<Double> seedValues = MapUtils
				.subMapInclusive(seedingData, seedFrom, startDateShift)
				.values()
				.stream().filter(v -> !Double.isNaN(v)).collect(Collectors.toList());
		Double seed = new MyApacheStats(new Mean()).sEvaluate(seedValues);
		if (seed.isNaN()) seed = seedingData.get(seedingData.headMap(startDateShift).lastKey()); //When no seed because seedingData are outside [startDate - period, startDate]

		//Calculus
		List<Date> keys = new ArrayList<>(oneInputParams.keySet());
		AtomicInteger i = new AtomicInteger(0);
		Stream<Double> recursive = Stream.iterate(seed, u -> {
			Date k = keys.get(i.incrementAndGet());
			return function.apply(u, oneInputParams.get(k));
		});

		AtomicInteger ia = new AtomicInteger(0);
		SortedMap<Date, Double> calculus = recursive
				.limit(keys.size()-1)
				.collect(Collectors.toMap(
						v -> keys.get(ia.getAndIncrement()),
						v -> v,
						(a, b) -> a, TreeMap::new));

		return new DoubleMapValue(calculus);
	}

	@Override
	public int operandsRequiredStartShift() {
		int maxDateShift = 0;
		for (int i = 0; i < 1; i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operandsRequiredStartShift();
		}
		return maxDateShift;
	}

}
