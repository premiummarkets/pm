
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

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class ExponentialSmoother extends Smoother {
		
		private Double alpha;
		private Double beta;
		private SortedMap<Date, double[]> trendLine;
		
		public ExponentialSmoother() {
			super();
			this.alpha = 0.06;
			this.beta = Double.NaN;
		}

		public ExponentialSmoother(double alpha) {
			super();
			this.alpha = alpha;
			this.beta = Double.NaN;
		}

		public ExponentialSmoother(double alpha, double beta) {
			this.alpha = alpha;
			this.beta = beta;
		}

		private double smooth(double x, double prevSmooth) {
			return alpha*x + (1-alpha) * (prevSmooth);
		}
		
		private double smoothDouble(double x, double prevSmooth, double prevLag) {
			return alpha*x + (1-alpha) * (prevSmooth + prevLag);
		}
		
		private double lagn(double sn, double prevSn, double prevLag) {
			return beta*(sn - prevSn) + (1-beta)*prevLag;
		}
		
		@Override
		public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
			
			if (beta.equals(Double.NaN) && fixLag) throw new InvalidParameterException("Beta must be specified to fix the lag.");
			
			trendLine = new TreeMap<Date, double[]>();
			SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
			List<Date> keys = new ArrayList<Date>(data.keySet());
			
			int startIdx = trimStartingNaN(data, keys);
			
			//init
			int seed = 3;
			double[] sn = Arrays.copyOf(data.get(keys.get(startIdx)), data.get(keys.get(startIdx)).length);
			double[] bn = Arrays.copyOf(data.get(keys.get(startIdx + seed)), data.get(keys.get(startIdx + seed)).length);
			for (int j = 0; j < bn.length; j++) {
				bn[j] = (bn[j] - sn[j])/seed;
			}
			ret.put(keys.get(startIdx), Arrays.copyOf(sn, sn.length));
			trendLine.put(keys.get(startIdx), Arrays.copyOf(bn, bn.length));
			
			//loop
			for (int i = startIdx+1; i < keys.size(); i++) {
				double[] ds = data.get(keys.get(i));
				double[] smoothedDs = new double[ds.length];
				double[] trendDs = new double[ds.length];
				for (int j = 0; j < ds.length; j++) {
					
					if (Double.isNaN(ds[j])) {throw new NotImplementedException();}
					
					if (!fixLag) {
						smoothedDs[j] = smooth(ds[j], sn[j]);
					} else {
						smoothedDs[j] = smoothDouble(ds[j], sn[j], bn[j]);
						trendDs[j] = lagn(smoothedDs[j], sn[j], bn[j]);
					}
				}
				
				ret.put(keys.get(i), smoothedDs);
				sn = smoothedDs;
				trendLine.put(keys.get(i), trendDs);
				bn = trendDs;
			}
			
			return ret;
		}

		public SortedMap<Date, double[]> getTrendLine() {
			return trendLine;
		}
	}