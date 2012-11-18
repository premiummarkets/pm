package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SlopeDerivator implements DiscretDerivator {
	
	private int period;
	private double slope;
	private Boolean buyCrossTowardCenter;
	private Boolean sellCrossTowardCenter;

	public SlopeDerivator(int period, double slope, Boolean buyCrossTowardCenter, Boolean sellCrossTowardCenter) {
		super();
		this.period = period;
		this.slope = slope;
		this.buyCrossTowardCenter = buyCrossTowardCenter;
		this.sellCrossTowardCenter = sellCrossTowardCenter;
	}

	@Override
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
			double percentDiff = (current - prev)/prev;
			
			boolean isBuySlope = (buyCrossTowardCenter)? percentDiff > (slope * new Double(period)) :  percentDiff < (-slope * new Double(period));
			if ( isBuySlope ) {
				drv = 0;
			} else {
				boolean isSellSlope = (sellCrossTowardCenter)?  percentDiff < (-slope * new Double(period))  : percentDiff > (slope * new Double(period));
				if ( isSellSlope ) {
					drv = 1;
				}
			} 
			ret.put(keys.get(i), new double[]{drv});
		}

		return ret;
	}

}
