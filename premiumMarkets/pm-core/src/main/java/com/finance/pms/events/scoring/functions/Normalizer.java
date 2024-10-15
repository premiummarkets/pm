
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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.scoring.functions.Trimmer.Filter;
import com.finance.pms.events.scoring.functions.Trimmer.Trimmerage;

public class Normalizer<T> {

	private static MyLogger LOGGER = MyLogger.getLogger(Normalizer.class);
	
	Filter filter;
	Trimmerage trimmerage;

	private final Class<T> genType;

	protected final Date start;
	protected final Date end;
	private final double maxNorm;
	private final double minNorm;
	private final double actualCenter;

	private Set<Date> removedKeys = new HashSet<Date>();


	public Normalizer(Class<T> genType, Date start, Date end, double minNorm, double maxNorm, double actualCenter) {

		this.genType = genType;
		this.filter = new Filter() {
			@Override
			public Boolean above(Double value) {
				return false;
			}
			@Override
			public Boolean below(Double value) {
				return false;
			}
		};
		this.trimmerage = new Trimmerage() {

			@Override
			public Double trimmed(Double value, Double lowerThreshold, Double upperThreshold) {
				return value;
			}
			
		};

		this.start = start;
		this.end = end;

		this.minNorm = minNorm;
		this.maxNorm = maxNorm;

		this.actualCenter = actualCenter;
	
	}
	
	public Normalizer(Class<T> genType, Filter filter, Trimmerage trimmerage, Date start, Date end, double minNorm, double maxNorm, double actualCenter) {

		this.genType = genType;
		this.filter = filter;
		this.trimmerage = trimmerage;

		this.start = start;
		this.end = end;

		this.minNorm = minNorm;
		this.maxNorm = maxNorm;

		this.actualCenter = actualCenter;

	}

	public Normalizer(Class<T> genType, Date start, Date end, double minNorm, double maxNorm) {
		this(genType, start, end, minNorm, maxNorm, Double.NaN);
		LOGGER.warn("Normalizer: No actual center set: Changing the mean will result in the alteration of the Probability Density of the data.");
	}


	public SortedMap<Date,T> normalised(SortedMap<Date,T> data) {

		SortedMap<Date, T> ret = new TreeMap<Date, T>();

		SortedMap<Date, T> subD = MapUtils.subMapInclusive(data, start, end);
		double[] calculatedMinMax = calculateMinMax(subD);
		double min = calculatedMinMax[0];
		double max = calculatedMinMax[1];
		
		//max == min: this is  a straight line
		//max == min == 0: this is a straight line of 0s
		double delta = (max == min)?(max == 0)?0:(maxNorm - minNorm)/Math.abs(max):(maxNorm - minNorm)/(max - min);
		
		//fit min actual value to minNorm if no actual centre is provided //XXX This is arbitrary, should it be the max(|max|,|min|) instead?
		double normedPivot = minNorm;
		double actualPivot = min;
		
		if (!Double.isNaN(actualCenter)) {
			normedPivot = (maxNorm - minNorm)/2 + minNorm;
			actualPivot = actualCenter;
		}

		for (Date date : subD.keySet()) {
			double value = valueOf(subD.get(date));
			
			Double trimmed = trimmerage.trimmed(value, min, max);
			
			if (Double.isNaN(trimmed)) {
				ret.put(date, tOf(trimmed));
				if (!Double.isNaN(value)) {
					getRemovedKeys().add(date);
				}
			} else {
				//From BandRatioNormalizerOperation: (value - actualPivot) * distanceToNewCenter/distanceToActualCenter  + normedPivot
				double destValueAti = (trimmed - actualPivot) * delta + normedPivot;
				ret.put(date, tOf(destValueAti));
			}
			
		}

		return ret;

	}

	protected double[] calculateMinMax(SortedMap<Date, T> subD) {

		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;

		for (Date date : subD.keySet()) {
			double value = valueOf(subD.get(date));
			if (value >= max && !filter.above(value)) max = value;
			if (value <= min && !filter.below(value)) min = value;
		}

		//Keep centre of the data
		//This is to keep the variation from the centre with an identical balance
		if (!Double.isNaN(actualCenter)) {
			double biggestAbs = Math.max(Math.abs(max - actualCenter), Math.abs(actualCenter - min));
			max = biggestAbs + actualCenter;
			min = actualCenter - biggestAbs;
		}

		return new double[] {min, max};
	}

	@SuppressWarnings("unchecked")
	private T tOf(Double destValueAti) {

		if (genType.isAssignableFrom(Double.class)) {
			return (T) destValueAti;
		} else if (genType.isArray() && genType.getComponentType().equals(Double.TYPE)) {
			return (T) new double[]{destValueAti};
		} else throw new NotImplementedException();

	}

	private Double valueOf(T t) {

		if (t instanceof Double) {
			return (Double) t;
		} else if (t.getClass().isArray() &&t.getClass().getComponentType().equals(Double.TYPE)){
			if (((double[]) t).length > 1) {
				LOGGER.warn("Normalised data contains element value size > 1 is not supported. Only the first series will be normalised.");
			}
			return ((double[]) t)[0];
		} else throw new NotImplementedException();

	}

	public Set<Date> getRemovedKeys() {
		return removedKeys;
	}

}
