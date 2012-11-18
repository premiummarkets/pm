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