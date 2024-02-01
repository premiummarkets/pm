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
package com.finance.pms.events.scoring.functions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;

public class MyApacheStats implements StatsFunction {

	private AbstractUnivariateStatistic statistic;
	private int minPeriod = 1;

	public MyApacheStats(AbstractUnivariateStatistic statistic) {
		super();
		this.statistic = statistic;
	}
	
	public MyApacheStats(AbstractUnivariateStatistic statistic,  int minPeriod ) {
		super();
		this.statistic = statistic;
		this.minPeriod = minPeriod;
	}

	@Override
	public double dEvaluateMd(SortedMap<Date, Double> subMap) {
		return this.dEvaluateCd(subMap.values());
	}
	
	@Override
	public double[] adEvaluateMd(SortedMap<Date, Double> subMap) {
		return new double[] {this.dEvaluateMd(subMap)};
	}

	/**
	 * XXX only evaluate the first dim of the passed arrays.
	 * @param subMap
	 * @return
	 */
	public double dEvaluateCad(Collection<double[]> subMap) {
		statistic.setData(null);
		double[] values = new double[subMap.size()];
		int i = 0;
		for (double[] element : subMap) {
			values[i] = element[0];
			i++;
		}

		return statistic.evaluate(values);
	}
	
	public double[] adEvaluateCad(Collection<double[]> subMap) {
		statistic.setData(null);
		SortedMap<Integer, double[]> valuesMap = new TreeMap<>();
		int i = 0;
		for (double[] element : subMap) {
			for (int j=0; j < element.length; j++) {
				double[] jValues = valuesMap.getOrDefault(j, new double[subMap.size()]);
				jValues[i] = element[j];
				valuesMap.put(j, jValues);
			}
			i++;
		}
		double[] results = valuesMap.values().stream().map(va -> statistic.evaluate(va)).mapToDouble(d -> d).toArray();
		return results;
	}

	public double dEvaluateCd(Collection<Double> subMap) {
		statistic.setData(null);
		double[] values = new double[subMap.size()];
		int i = 0;
		for (Double element : subMap) {
			values[i] = element;
			i++;
		}

		return statistic.evaluate(values);
	}

	@Override
	public SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap) {
		double sEvaluate = this.dEvaluateCd(subMap.values().stream().filter(v -> !Double.isNaN(v)).collect(Collectors.toList()));
		TreeMap<Date, Double> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> sEvaluate, (a, b) -> a, TreeMap<Date,Double>::new));
		return collected;
	}
	
	@Override
	public SortedMap<Date, double[]> madEvaluateMd(SortedMap<Date, Double> subMap) {
		double dEvaluateCd = this.dEvaluateCd(subMap.values().stream().filter(v -> !Double.isNaN(v)).collect(Collectors.toList()));
		TreeMap<Date, double[]> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> new double[] {dEvaluateCd}, (a, b) -> a, TreeMap<Date,double[]>::new));
		return collected;
	}

	@Override
	public List<String> getOutputsRefs() {
		return Arrays.asList(statistic.getClass().getSimpleName().toLowerCase());
	}

	@Override
	public int getMinPeriod() {
		return minPeriod;
	}
	
	

}
