
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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.finance.pms.talib.indicators.TalibCoreService;
import com.tictactec.ta.lib.MInteger;

public class ZeroLagEMASmoother extends Smoother {

	int period;
	private MInteger doubleEmaOutBegIdx;
	private MInteger doubleEmaOutNBElement;


	public ZeroLagEMASmoother(int period) {
		super();
		this.period = period;
	}

	public SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, boolean fixLag) {

		double[][] inReal = data.values().stream().map(v -> new double[] {v}).toArray(s -> new double[s][]);
		double[][] zeroLagEmas = smooth(inReal);

		SortedMap<Date, Double> ret = new TreeMap<>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= 2*period  && (j- (2*period)) < zeroLagEmas.length) {
				ret.put(date, zeroLagEmas[j - (2*period)][0]);
			}
			j++;
		}

		return ret;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {

		if (this.period <= 1) return data;

		double[][] inReal = new double[data.size()][];
		int i=0;
		for (double[] dv : data.values()) {

			double[] inRealV = new double[dv.length];
			for (int k = 0; k < dv.length; k++) {
				inRealV[k] = dv[k];
			}
			inReal[i] = inRealV;

			i++;
		}

		double[][] zeroLagEmas = smooth(inReal);

		SortedMap<Date, double[]> ret = new TreeMap<>();
		int j = 0;
		for (Date date : data.keySet()){
			if (j >= 2*period  && (j- (2*period)) < zeroLagEmas.length) {

				double[] retV = new double[zeroLagEmas[j - (2*period)].length];
				for (int k = 0; k < zeroLagEmas[j - (2*period)].length; k++) {
					retV[k] = zeroLagEmas[j - (2*period)][k];
				}
				ret.put(date, retV);

			}
			j++;
		}
		return ret;

	}


	public double[][] smooth(double[][] inReal) {

		if (this.period <= 1) return inReal;

		List<double[]> inRealTransposed = new ArrayList<>();
		double[] ds0 = inReal[0];
		for (@SuppressWarnings("unused") double d : ds0) {
			inRealTransposed.add(new double[inReal.length]);
		}
		for (int i = 0; i < inReal.length; i++) {
			double[] ds = inReal[i];
			for (int k = 0; k < ds.length; k++) {
				inRealTransposed.get(k)[i] = ds[k];
			}
		}

		double[][] zeroLagEmas = null;
		for (int k = 0; k < inRealTransposed.size(); k++) {
			double[] oneInReal = inRealTransposed.get(k);

			MInteger emaOutBegIdx = new MInteger();
			MInteger emaOutNBElement = new MInteger();
			int endIdx = oneInReal.length-1;
			int startIdx = 0;
			double[] ema = new double[oneInReal.length - period +1];
			TalibCoreService.getCore().ema(startIdx, endIdx, oneInReal, period, emaOutBegIdx, emaOutNBElement, ema);

			doubleEmaOutBegIdx = new MInteger();
			doubleEmaOutNBElement = new MInteger();
			double[] doubleEma = new double[ema.length - period +1];
			TalibCoreService.getCore().ema(0, ema.length-1, ema, period, doubleEmaOutBegIdx, doubleEmaOutNBElement, doubleEma);

			if (zeroLagEmas == null) zeroLagEmas= new double[doubleEma.length][];
			for (int j = 0; j < doubleEmaOutNBElement.value; j++) {
				double zeroLagEmaData = 2*ema[j+period-1] - doubleEma[j];

				if (zeroLagEmas[j] == null) zeroLagEmas[j] = new double[inRealTransposed.size()];
				zeroLagEmas[j][k] = zeroLagEmaData;

			}
		}

		return zeroLagEmas;

	}


	public MInteger getDoubleEmaOutBegIdx() {
		return doubleEmaOutBegIdx;
	}


	public MInteger getDoubleEmaOutNBElement() {
		return doubleEmaOutNBElement;
	}

}
