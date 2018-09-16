
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

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationsFactories;

public class Normalizer<T> {

	private static MyLogger LOGGER = MyLogger.getLogger(Normalizer.class);

	private final Class<T> genType;

	protected final Date start;
	protected final Date end;
	private final double maxNorm;
	private final double minNorm;
	private final boolean keepDistanceToZero;


	public Normalizer(Class<T> genType, Date start, Date end, double minNorm, double maxNorm, boolean keepDistanceToZero) {

		this.genType = genType;

		this.start = start;

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(endCal, 1);
		this.end = endCal.getTime();

		this.minNorm = minNorm;
		this.maxNorm = maxNorm;

		this.keepDistanceToZero = keepDistanceToZero;
	}

	public Normalizer(Class<T> genType, Date start, Date end, double minNorm, double maxNorm) {
		this(genType, start, end, minNorm, maxNorm, false);
	}


	public SortedMap<Date, T> normalised(SortedMap<Date,T> data) {

		SortedMap<Date, T> ret = new TreeMap<Date, T>();

		SortedMap<Date, T> subD = data.subMap(start, end);
		double[] calculatedMinMax = calculateMinMax(subD);
		double min = calculatedMinMax[0];
		double max = calculatedMinMax[1];

		for (Date date : subD.keySet()) {
			double value = valueOf(subD.get(date));
			double destValueAti = ((value-min)/(max-min)) * (maxNorm - minNorm) + minNorm;
			ret.put(date,  tOf(destValueAti));
		}

		return ret;

	}

	protected double[] calculateMinMax(SortedMap<Date, T> subD) {

		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;

		for (Date date : subD.keySet()) {
			double value = valueOf(subD.get(date));
			if (value >= max) max = value;
			if (value <= min) min = value;
		}

		//Keep a gap to the requested norm for the smallest of abs(min) and abs(max)
		//This can avoid having the norm stuck to zero if 0 is requested
		if (keepDistanceToZero) {
			double biggestAbs = Math.max(Math.abs(max), Math.abs(min));
			max = (max > 0)? biggestAbs: 0;
			min = (min < 0)? -biggestAbs : 0;
		}

		return new double[] {min,max};
	}

	//	public double getNormalizedZero() {
	//		if (max == 0 && min == 0) throw new RuntimeException("Uninitialised normaliser", new Exception());
	//		return (-min/(max-min)) * (maxNorm - minNorm) + minNorm;
	//	}
	//	
	//	public double getNormalizedValue(Double value) {
	//		if (max == 0 && min == 0) throw new RuntimeException("Uninitialised normaliser", new Exception());
	//		return (value-min/(max-min)) * (maxNorm - minNorm) + minNorm;
	//	}

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

}
