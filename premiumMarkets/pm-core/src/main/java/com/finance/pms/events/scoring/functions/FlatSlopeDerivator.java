package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//Not working with the thresholds as conditions are never all met.
public class FlatSlopeDerivator implements DiscretDerivator {
	
	private int period;
	private double slope;

	public FlatSlopeDerivator(int period, double slope) {
		super();
		this.period = period;
		this.slope = slope;
	}
	
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 2*period; i < values.size(); i++) {
			
			double drv = 0.5;
			double prevPrev = values.get(i-2*period)[0];
			double prev = values.get(i-period)[0];
			double current = values.get(i)[0];
			if (Math.abs(current - prev) < slope) {
				if ( (prev - prevPrev) > (slope * new Double(period)) ) { //&& current >= hightThreshold ) {
					drv = 1;
				} else if ( (prev - prevPrev) < (-slope * new Double(period)) ) { //&& current <= lowThreshold ) {
					drv = 0;
				} 
			}
			ret.put(keys.get(i), new double[]{drv});
		}

		return ret;

	}

}
