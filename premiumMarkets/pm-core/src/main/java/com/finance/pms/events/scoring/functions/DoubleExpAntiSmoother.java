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
