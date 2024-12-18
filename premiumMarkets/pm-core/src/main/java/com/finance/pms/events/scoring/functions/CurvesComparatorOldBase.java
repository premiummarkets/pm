
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;

public abstract class CurvesComparatorOldBase implements CurvesComparator {

	protected int maxShift;
	private Date start;
	private Date end;
	protected Date cmpStart;
	protected Date cmpEnd;

	/**
	 * Compare two input and gives the stdev
	 * @param stock TODO
	 * @param start 
	 * @param end 
	 * @param maxShift 
	 * @throws NotEnoughDataException 
	 */
	public CurvesComparatorOldBase(Stock stock, Date start, Date end, int maxShift) throws NotEnoughDataException {
		super();
		this.maxShift = maxShift;

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), startCal, maxShift);
		this.cmpStart = startCal.getTime();
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), endCal, -maxShift);
		this.cmpEnd = endCal.getTime();
		
		this.start = start;
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(stock, Arrays.asList(QuotationDataType.CLOSE), endCal, +1);
		this.end = endCal.getTime();
	}
	
	
	@Override
	public String name() {
		return "undefined";
	}

	//CurveErr: best shift and error (double) + the error when no shift is applied + all the test shifts and their errors list.
	public CurveErr compareShifted(SortedMap<Date, double[]> data, SortedMap<Date, double[]> refData) {
		
		int foldSize = 21;
		double realErr = Double.MAX_VALUE;
		SortedSet<ErrShift> sortedBestShifts = new TreeSet<ErrShift>();

		List<Date> datakeySet = new ArrayList<Date>(data.subMap(start, end).keySet());
		
		for (int shift = -maxShift; shift <= maxShift; shift++) {
			
			double shiftErr = 0;
			for (int i = maxShift; i < datakeySet.size() - maxShift - foldSize; i = i + foldSize) {
				
				SortedMap<Date, double[]> shiftedData = new TreeMap<Date, double[]>();
				for (int j = i; j < i+ foldSize; j++) {
					shiftedData.put(datakeySet.get(j), data.get(datakeySet.get(j+shift)));
				}
				
				Date refSlotStart = datakeySet.get(i);
				Date refSlotEnd = datakeySet.get(i + foldSize);
				
				SortedMap<Date, double[]> subData = shiftedData.subMap(refSlotStart, refSlotEnd);
				SortedMap<Date, double[]> subRefData = refData.subMap(refSlotStart, refSlotEnd);
				
				double dataStdev = stdev(subData.values());
				double refDataStdev = stdev(subRefData.values());
				CurvesMultiplication curvesMultiplication = new CurvesMultiplication();
				SortedMap<Date, double[]> amplNormData = curvesMultiplication.operate(subData, refDataStdev/dataStdev);

				double dataMedian = median(amplNormData.values());
				double refDataMedian = median(subRefData.values());
				CurvesSubtraction curvesSubtraction = new CurvesSubtraction();
				SortedMap<Date, double[]> synchedData = curvesSubtraction.operate(amplNormData, dataMedian-refDataMedian);

				SortedMap<Date, double[]> normalisedRefData = normalize(refSlotStart, refSlotEnd, subRefData);
				SortedMap<Date, double[]> normalisedData = normalize(refSlotStart, refSlotEnd, synchedData);
			
				shiftErr = shiftErr + compareNormalised(refSlotStart, refSlotEnd, normalisedData, normalisedRefData);
				
			}

			if (shift == 0) {
				realErr = shiftErr;
			}
			sortedBestShifts.add(new ErrShift(shiftErr, shift));

		}
		
		int bestShift = sortedBestShifts.first().shift;
		double bestdErr = sortedBestShifts.first().err;
		return new CurveErr(bestShift, bestdErr, realErr, sortedBestShifts);
	}
	
	private double stdev(Collection<double[]> subMap) {
		double[] values = new double[subMap.size()];
		int i = 0;
		for (double[] element : subMap) {
			values[i] = element[0];
			i++;
		}
		
		StandardDeviation stdev = new StandardDeviation();
		return stdev.evaluate(values);
	}
	
	private double median(Collection<double[]> subMap) {
		double[] values = new double[subMap.size()];
		int i = 0;
		for (double[] element : subMap) {
			values[i] = element[0];
			i++;
		}
		
		Median median = new Median();
		return median.evaluate(values);
	}

	protected abstract double compareNormalised(Date slotStart, Date slotEnd, SortedMap<Date, double[]> normalisedData, SortedMap<Date, double[]> normalisedRefData);
	
	protected abstract SortedMap<Date, double[]> normalize(Date slotStart, Date slotEnd, SortedMap<Date, double[]> data);
	
	@Override
	public Map<String, Double> compare(SortedMap<Date, double[]> refline, SortedMap<Date, double[]> challenger) {
		refline = trim(challenger, refline);
		challenger = trim(refline, challenger);
		return Map.ofEntries(java.util.Map.entry("default", compareShifted(refline, challenger).realErr));
	}

	public class CurveErr {
		
		private SortedSet<ErrShift> sortedBestShifts;
		private int bestShift;
		private double bestErr;
		private double realErr;
		
		public CurveErr(int bestShift, double bestErr, double realErr, SortedSet<ErrShift> sortedBestShifts) {
			super();
			this.bestShift = bestShift;
			this.bestErr = bestErr;
			this.realErr = realErr;
			
			this.sortedBestShifts = sortedBestShifts;
		}

		@Override
		public String toString() {
			return  bestShift + ", " + bestErr + ", " + realErr;
		}

		public int getBestShift() {
			return bestShift;
		}

		public double getBestErr() {
			return bestErr;
		}

		public double getRealErr() {
			return realErr;
		}

		public SortedSet<ErrShift> getSortedBestShifts() {
			return sortedBestShifts;
		}
		
		public String getSortedBestShiftsToString() {
			return sortedBestShifts.toString().replaceAll("[\\[\\]]", "");
		}
		
	}
	
	//The best shift is the one with the smallest error
	//Than the one that is most in advance to the ref curve ie the closest to negative infinity 
	//The shift being the days to put the data back into sync with the reference a negative value means that the data is leading the reference.
	class ErrShift implements Comparable<ErrShift>{
		Double err;
		Integer shift;
		
		public ErrShift(double err, int shift) {
			super();
			this.err = err;
			this.shift = shift;
		}

		@Override
		public int compareTo(ErrShift o) {
			int compareTo = err.compareTo(o.err);
			if (compareTo == 0) return shift.compareTo(o.shift);
			return compareTo;
		}

		@Override
		public String toString() {
			return "ErrShift (err=" + err*1000 + ", shift=" + shift + ")";
		}

	}
}
