
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationsFactories;

/**
 * 
 * @author guil
 * see @LeftShifter
 * @param <T>
 */
@Deprecated 
public class LeftShifterGapFree<T> {
	
	private int nbDaysLeftSide;
	private Boolean noDataLoss;
	private Collection<QuotationDataType> quotationDataTypes;
	private Stock stock;
	
	public LeftShifterGapFree(int nbDaysAhead, Boolean noDataLoss) {
		super();
		this.nbDaysLeftSide = nbDaysAhead;
		this.noDataLoss = noDataLoss;
	}
	
	@SuppressWarnings("unchecked")
	public SortedMap<Date, T> shift(SortedMap<Date, T> data) throws NotEnoughDataException {
		TreeMap<Date, double[]> dataTodouble;
		boolean isTaDoubleArray = data.get(data.firstKey()) instanceof double[];
		if (isTaDoubleArray) {
			dataTodouble = data.entrySet().stream().collect(Collectors.toMap(es -> es.getKey(), es -> (double[]) es.getValue(), (a, b) -> a, TreeMap::new));
		} else { //Can only be Double
			dataTodouble = data.entrySet().stream().collect(Collectors.toMap(es -> es.getKey(), es -> new double[] { (Double) es.getValue() }, (a, b) -> a, TreeMap::new));
		}
		SortedMap<Date, double[]> resultToDouble = internalShift(dataTodouble);
		if (isTaDoubleArray) {
			return resultToDouble.entrySet().stream().collect(Collectors.toMap(es -> es.getKey(), es -> (T) es.getValue(), (a, b) -> a, TreeMap::new));
		} else {
			return resultToDouble.entrySet().stream().collect(Collectors.toMap(es -> es.getKey(), es -> (T) Double.valueOf(es.getValue()[0]), (a, b) -> a, TreeMap::new));
		}
	}

	private SortedMap<Date, double[]> internalShift(SortedMap<Date, double[]> data) throws NotEnoughDataException {

		SortedMap<Date, double[]> shiftedOuptput = new TreeMap<Date, double[]>();
		List<Date> dataKeyDates = new ArrayList<Date>(data.keySet());
		
		//Build gap free data
		List<Date> gapFreeKeyDates = new ArrayList<>();
		List<double[]> gapFreeValues = new ArrayList<>();
		Calendar gapFreeCalendar = Calendar.getInstance();
		gapFreeCalendar.setTime(data.firstKey());
		gapFreeKeyDates.add(gapFreeCalendar.getTime());
		int gap = 0;
		for(int nextDataDayIdx = 1; nextDataDayIdx < dataKeyDates.size(); nextDataDayIdx ++) {

			while (gapFreeCalendar.getTime().before(dataKeyDates.get(nextDataDayIdx))) {
				gapFreeCalendar.add(Calendar.DAY_OF_YEAR, 1);
				gapFreeKeyDates.add(gapFreeCalendar.getTime());
				gap++;
			}
			
			double[] y0 = data.get(dataKeyDates.get(nextDataDayIdx-1));
			int x0 = 0;
			double[] y1 = data.get(dataKeyDates.get(nextDataDayIdx));
			int x1 = gap+1;
			List<double[]> lineGap = lineBetween(x0,y0,x1,y1);
			gapFreeValues.addAll(lineGap);
			gap = 0;
			
		}
		gapFreeKeyDates.add(data.lastKey());
		gapFreeValues.add(data.get(data.lastKey()));
		
		SortedMap<Date, double[]> gapFreeData = new TreeMap<Date, double[]>();
		for (int i=0; i < gapFreeKeyDates.size(); i++) {
			gapFreeData.put(gapFreeKeyDates.get(i), gapFreeValues.get(i));
		}
		
		//Shift
		int jFirst = (nbDaysLeftSide >= 0)? nbDaysLeftSide : 0;
		int jLast = (nbDaysLeftSide >= 0)? dataKeyDates.size() : dataKeyDates.size() - nbDaysLeftSide;
		SortedMap<Date, double[]> shiftedGapFreeOuptput = new TreeMap<>();
		for (int j = jFirst; j < jLast; j++) {
			shiftedGapFreeOuptput.put(dataKeyDates.get(j-nbDaysLeftSide), gapFreeData.get(dataKeyDates.get(j)));
		}
		
		//Filter gaps out
		for (Date k : shiftedGapFreeOuptput.keySet()) {
			if (dataKeyDates.contains(k)) shiftedOuptput.put(k, shiftedGapFreeOuptput.get(k));
		}

		//Padding left for left shift (when nbDaysLeftSide > 0) and right for right shift (when nbDaysLeftSide < 0)??
		if (noDataLoss) {
			int nbMissingDays = shiftedOuptput.size() - shiftedOuptput.size();
			Calendar calendar = Calendar.getInstance();

			if (nbDaysLeftSide > 0) {
				calendar.setTime(shiftedOuptput.firstKey());
				for (int i = 1; i <= nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(stock, quotationDataTypes, calendar, -1);
					shiftedOuptput.put(calendar.getTime(), shiftedOuptput.get(dataKeyDates.get(jFirst-i)));
				}
			} else {
				calendar.setTime(shiftedOuptput.lastKey());
				for (int i = 0; i < nbMissingDays; i++) {
					QuotationsFactories.getFactory().incrementDate(stock, quotationDataTypes, calendar, +1);
					shiftedOuptput.put(calendar.getTime(), shiftedOuptput.get(dataKeyDates.get(jLast+i)));
				}
			}
			
		}
		return shiftedOuptput;

	}

	private List<double[]> lineBetween(int x0, double[] y0, int x1, double[] y1) { //x1 exclusive
		List<double[]> line = new ArrayList<double[]>();
		for (int x=x0; x < x1; x++) {
			double point[] = new double[y0.length];
			for(int k=0; k < y0.length; k++) {
				double ak = (y1[k]-y0[k])/(x1-x0);
				double bk = y0[k];
				point[k] = ak * x + bk;
				line.add(point);
			}
		}
		return line;
	}

}
