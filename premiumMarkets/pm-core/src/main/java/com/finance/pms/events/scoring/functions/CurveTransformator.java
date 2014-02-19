
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.finance.pms.events.scoring.functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.QuotationsFactories;

public class CurveTransformator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(CurveTransformator.class);
	
	public enum Extremity {
		UNKNOWN(0.5), BOTTOM(0.0), TOP(1.0);
		
		double exValue;
		
		private Extremity(double exVAlue) {
			this.exValue = exVAlue;
		}

		public double getExValue() {
			return exValue;
		}
		
	}


	OutputCalculator calculator;
	Smoother smoother;
	int periodBand;
	private  SortedMap<Date, double[]> original;
	private SortedMap<Date, Extremity> extremsList;
	private SortedMap<Date, double[]> transformedCurve;
	private SortedMap<Date, double[]> smoothedData;

	public CurveTransformator(OutputCalculator calculator, Smoother smoother, int periodBand) {
		super();
		this.calculator = calculator;
		this.smoother = smoother;
		this.periodBand = periodBand;
	}

	public SortedMap<Date, double[]> transform(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		original = data;
		smoothedData = smoother.smooth(data, fixLag);
		extremsList = buildExtremsList(smoothedData, periodBand);
		transformedCurve = calculatorTransformedData(extremsList);
		
		return transformedCurve;
	}


	private SortedMap<Date, double[]> calculatorTransformedData(SortedMap<Date, Extremity>  extremsList) {
		
		SortedMap<Date, double[]> stockCurve = new TreeMap<Date, double[]>();
		
		Date prevExtremDate = extremsList.firstKey();
		for (Date extremDate : extremsList.keySet()) {
			
			Calendar current = Calendar.getInstance();
			current.setTime(prevExtremDate);
			while (current.getTime().before(extremDate) || current.getTime().compareTo(extremDate) == 0) {
				Double value = calculator.compute(prevExtremDate, extremsList.get(prevExtremDate).exValue, extremDate, extremsList.get(extremDate).exValue, current.getTime());
				stockCurve.put(current.getTime(), new double[]{value});
				QuotationsFactories.getFactory().incrementDate(current, 1);
			}
			prevExtremDate = extremDate;
		}
		
		return stockCurve;
	}


	public SortedMap<Date, Extremity> buildExtremsList(SortedMap<Date, double[]> smoothedData, int band) {

		SortedMap<Date, Extremity> ret = new TreeMap<Date, Extremity>();

		Calendar current = Calendar.getInstance();
		current.setTime(smoothedData.firstKey());
		QuotationsFactories.getFactory().incrementDate(current, +2*band);

		Date endCalculation = smoothedData.lastKey();
		while (current.getTime().before(endCalculation) || current.getTime().compareTo(endCalculation) == 0) {

			Extremity extremity;
			try {
				extremity = trendChangeDetector(smoothedData, current.getTime(), band);
				if (extremity.equals(Extremity.TOP) || extremity.equals(Extremity.BOTTOM)) {
					ret.put(current.getTime(), extremity);
				}
				
			} catch (NotEnoughDataException e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				LOGGER.debug("Best output NA for " + sdf.format(current.getTime()) + " cause " + e.getMessage());
			}

			QuotationsFactories.getFactory().incrementDate(current, 1);
		}

		//edges fix
		if (ret.isEmpty() || ret.firstKey().after(smoothedData.firstKey())) {
			ret.put(smoothedData.firstKey(), Extremity.UNKNOWN);
		}
		if (ret.isEmpty() || ret.lastKey().before(smoothedData.lastKey())) {
			ret.put(smoothedData.lastKey(), Extremity.UNKNOWN);
		}

		return ret;
	}
	
	
	private Extremity trendChangeDetector(SortedMap<Date, double[]> data, Date date, int band) throws NotEnoughDataException {
		
		Calendar lowBandDate = Calendar.getInstance();
		lowBandDate.setTime(date);
		QuotationsFactories.getFactory().incrementDate(lowBandDate, -band);
		Calendar highBandDate = Calendar.getInstance();
		highBandDate.setTime(date);
		QuotationsFactories.getFactory().incrementDate(highBandDate, +band);
		
		Calendar prevHighBandDate =  Calendar.getInstance();
		prevHighBandDate.setTime(date);
		Calendar prevLowBandDate = Calendar.getInstance();
		prevLowBandDate.setTime(lowBandDate.getTime());
		QuotationsFactories.getFactory().incrementDate(prevLowBandDate, -band);
		
		Date firstDate = data.firstKey();
		Date lastDate= data.lastKey();
		
		//check size
		if (data.get(firstDate).length != 1) throw new InvalidParameterException();
		
		//first date <= lowest req for calc and highest req for calc <= last date 
		if (firstDate.getTime() <= prevLowBandDate.getTimeInMillis()  && highBandDate.getTimeInMillis() <= lastDate.getTime()) {
			
			double closeAtEndOfNextPeriod = data.get(highBandDate.getTime())[0];
			double closeAtStartOfPeriod = data.get(lowBandDate.getTime())[0];
			double closeAtEndOfPrevPeriod = data.get(prevHighBandDate.getTime())[0];
			double closeAtStartOfPrevPeriod = data.get(prevLowBandDate.getTime())[0];
			
			double variation = closeAtEndOfNextPeriod  - closeAtStartOfPeriod;
			double prevVariation = closeAtEndOfPrevPeriod - closeAtStartOfPrevPeriod;
			
			if (variation < 0 && prevVariation > 0) {
				return Extremity.TOP;
			} 
			if (variation > 0 && prevVariation < 0){
				return Extremity.BOTTOM;
			}
			
			return Extremity.UNKNOWN;
		}
		
		//error mgt
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		throw new NotEnoughDataException(
				null,
				firstDate, lastDate,
				"Date "+ sdf.format(date) + " is out of range with lower date  :" + 
				sdf.format(prevLowBandDate.getTimeInMillis()) + " < " + sdf.format(firstDate) + " or " + sdf.format(highBandDate.getTimeInMillis()) + " > " + sdf.format(lastDate)
				, new Throwable());
		
	}

	public SortedMap<Date, Extremity> getExtremsList() {
		return extremsList;
	}

	public SortedMap<Date, double[]> getTransformedCurve() {
		return transformedCurve;
	}

	public SortedMap<Date, double[]> getSmoothedData() {
		return smoothedData;
	}
	
	public void export(String extention) {
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + "curveTransfomed"+extention+".csv")));
			bufferedWriter.write("date, orignial, smoothed, extreme, transfomed \n");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			for (Date date : transformedCurve.keySet()) {
				double[] ov = original.get(date);
				double[] sv = smoothedData.get(date);
				double[] tv = transformedCurve.get(date);
				Extremity ev = extremsList.get(date);
				
				bufferedWriter.write(dateFormat.format(date));
				
				if (ov != null) {
					bufferedWriter.write(", "+ ov[0]);
				} else {
					bufferedWriter.write(", NA ");
				}
				if (sv != null) {
					bufferedWriter.write(", "+ sv[0]);
				} else {
					bufferedWriter.write(", NA ");
				}
				if (ev != null) {
					bufferedWriter.write(", "+ ev.getExValue());
				} else {
					bufferedWriter.write(", NA ");
				}
				if (tv != null) {
					bufferedWriter.write(", "+ tv[0]);
				} else {
					bufferedWriter.write(", NA ");
				}
				
				bufferedWriter.write("\n");
			}
			
			bufferedWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
