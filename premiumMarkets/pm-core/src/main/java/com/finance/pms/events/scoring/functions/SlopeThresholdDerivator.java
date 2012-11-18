package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//The problem is that the trend line also follows a more global trend up and down
//This means, taking in account that we normalise, that the signals will be different for different windows of observation.
//=> replace thresholds by distance from global trend?
public class SlopeThresholdDerivator implements DiscretDerivator {
	
	private int period;
	private double slope;
	private double lowThreshold;
	private double highThreshold;
	private Boolean buyCrossTowardCenter;
	private Boolean sellCrossTowardCenter;

	public SlopeThresholdDerivator(int period, double slope, double lowThreshold, double highThreshold, Boolean buyCrossTowardCenter,  Boolean sellCrossTowardCenter) {
		super();
		this.period = period;
		this.slope = slope;
		this.lowThreshold = lowThreshold;
		this.highThreshold = highThreshold;
		this.buyCrossTowardCenter = buyCrossTowardCenter;
		this.sellCrossTowardCenter = sellCrossTowardCenter;
	}
	
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 0; i < period; i++) {
			ret.put(keys.get(i), new double[]{0.5});
		}
		
		for (int i = period; i < values.size(); i++) {
			
			double drv = 0.5;
			double prev = values.get(i-period)[0];
			double current = values.get(i)[0];
			
			boolean currentBelowLowThreshold = current <= lowThreshold;
			boolean isBuySlope = (buyCrossTowardCenter)? (current - prev) > (slope * new Double(period)) :  (current - prev) < (-slope * new Double(period));
			if ( isBuySlope  && currentBelowLowThreshold ) {
				drv = 0;
			} else {
				boolean isSellSlope = (sellCrossTowardCenter)?  (current - prev) < (-slope * new Double(period))  : (current - prev) > (slope * new Double(period));
				boolean currentAboveHighThreshold = current >= highThreshold;
				if ( isSellSlope  && currentAboveHighThreshold) {
					drv = 1;
				}
			} 
			ret.put(keys.get(i), new double[]{drv});
		}

		return ret;

	}

}
