
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
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

//XXX sma 1 == shift 1 for convenience and backward compatibility in this implementation sma 1 == sma 0.
public class TalibSmaSmoother extends Smoother implements SSmoother {

	private static MyLogger LOGGER = MyLogger.getLogger(TalibSmaSmoother.class);

	private int period;


	public TalibSmaSmoother(int period) {
		super();
		this.period = period;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data , Boolean fixLag) {

		double[][] dataArray = data.values().toArray(new double[data.size()][]);

		double[] sma = smooth(dataArray);

		//Lag fix and return
		int lag = lagCalc(fixLag, period); 
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= period - lag && (j- (period - lag)) < sma.length) {
				ret.put(date, new double[] {sma[j - (period - lag)]});
			}
			j++;
		}

		return ret;
	}


	public double[] smooth(double[][] dataArray) {

		double[] sma = new double[dataArray.length - period +1];


		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = dataArray.length-1;
		int startIdx = 0;

		double[] inReal = new double[dataArray.length];
		int i=0;
		for (double[] dv : dataArray) {
			inReal[i] = dv[0];
			i++;
		}

		RetCode rc;
		if (period <= 1) {

			period = 0; //XXX
			sma = Arrays.copyOfRange(inReal, startIdx, endIdx);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;
			rc = RetCode.Success;

		} else {
			rc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, sma);
		}
		LOGGER.debug("Smoothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);
		return sma;
	}


	@Override
	public SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, Boolean fixLag) {

		int lag = lagCalc(fixLag, period);

		MInteger outBegIdx = new MInteger();
		MInteger outNBElement = new MInteger();
		int endIdx = data.size()-1;
		int startIdx = 0;

		double[] sma = new double[data.size() - period +1];

		double[] inReal = new double[data.size()];
		int i=0;
		for (Double dv : data.values()) {
			inReal[i] = dv;
			i++;
		}

		RetCode rc;
		if (period <= 1) {

			period = 0; //XXX
			sma = Arrays.copyOfRange(inReal, startIdx, endIdx);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;
			rc = RetCode.Success;


		} else {
			rc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal, period, outBegIdx, outNBElement, sma);
		}
		LOGGER.debug("smothing res : retcode "+rc.name()+" out begin idx "+outBegIdx.value+", out nb ele "+outNBElement.value);

		SortedMap<Date, Double> ret = new TreeMap<Date, Double>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= period - lag && (j- (period - lag)) < sma.length) {
				ret.put(date, sma[j - (period - lag)]);
			}
			j++;
		}


		return ret;
	}

}
