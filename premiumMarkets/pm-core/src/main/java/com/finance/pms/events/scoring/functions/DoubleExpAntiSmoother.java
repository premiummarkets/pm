
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class DoubleExpAntiSmoother extends Smoother {
	
	private Double alpha;
	private Double beta;
	private double[] quotationAtIdx0;
	private SortedMap<Date, double[]> smoothedLine;

	public DoubleExpAntiSmoother(Double alpha, Double beta, double[] firstQuotation) {
		super();
		this.alpha = alpha;
		this.beta = beta;
		this.quotationAtIdx0 = firstQuotation;
	}



	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> trendData, Boolean fixLag) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		smoothedLine = new TreeMap<Date, double[]>();
		List<Date> keys = new ArrayList<Date>(trendData.keySet());
		
		int startIdx = trimStartingNaN(trendData, keys);

		//init
		double[] prevSmoothedDs = Arrays.copyOf(quotationAtIdx0, quotationAtIdx0.length);
		ret.put(keys.get(startIdx), Arrays.copyOf(prevSmoothedDs, prevSmoothedDs.length));

		for (int i = startIdx+1; i < keys.size(); i++) {

			double[] trendDs = trendData.get(keys.get(i));
			double[] prevTrendDs = trendData.get(keys.get(i-1));

			double[] smoothedDs = new double[trendDs.length];
			double[] quotationsDs = new double[trendDs.length];

			for (int j = 0; j < trendDs.length; j++) {

				if (Double.isNaN(trendDs[j])) {throw new NotImplementedException();}
				smoothedDs[j] = smoothDouble(prevSmoothedDs[j], trendDs[j], prevTrendDs[j]);
				quotationsDs[j] = quotation(smoothedDs[j], prevSmoothedDs[j], prevTrendDs[j]); //+ quotationAtIdx0[j];
			}

			smoothedLine.put(keys.get(i), smoothedDs);
			prevSmoothedDs = smoothedDs;
			
			ret.put(keys.get(i), quotationsDs);

		}
		
		return ret;

	}


	private double quotation(double smoothed, double prevSmoothed, double prevTrend) {
		double ret = (smoothed - (1-alpha)*(prevSmoothed + prevTrend))/alpha;
		return ret;
	}



	private double smoothDouble(double prevSmoothed, double trend, double prevTrend) {
		double ret = prevSmoothed + (trend - (1-beta)*prevTrend)/beta;
		return ret;
	}



	public SortedMap<Date, double[]> getSmoothedLine() {
		return smoothedLine;
	}
	

}
