package com.finance.pms.events.scoring.functions;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class ThresholdDerivator implements DiscretDerivator {

	private double lowThreshold;
	private double highThreshold;
	private Boolean buyCrossTowardCenter;
	private Boolean sellCrossTowardCenter;

	public ThresholdDerivator(double lowThreshold, double highThreshold, Boolean buyCrossTowardCenter,  Boolean sellCrossTowardCenter) {
		super();
		if (highThreshold < lowThreshold) throw new InvalidParameterException();
		
		this.lowThreshold = lowThreshold;
		this.highThreshold = highThreshold;
		this.buyCrossTowardCenter = buyCrossTowardCenter;
		this.sellCrossTowardCenter = sellCrossTowardCenter;
	}
	
	public ThresholdDerivator(double lowThreshold, double highThreshold) {
		this(lowThreshold, highThreshold, false, true);
	}

	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 1; i < values.size(); i++) {
			double retValue = 0.5;
			double prevValue = values.get(i-1)[0];
			double currentValue = values.get(i)[0];
			boolean prevToLowTh = (buyCrossTowardCenter)? prevValue < lowThreshold : prevValue > lowThreshold;
			boolean currentToLowTh = (buyCrossTowardCenter)? lowThreshold <= currentValue : lowThreshold >= currentValue;
			if ( prevToLowTh && currentToLowTh) {
				retValue = 0;
			} else {
				boolean prevToHighTh = (sellCrossTowardCenter)? prevValue > highThreshold : prevValue < highThreshold;
				boolean currentToHighTh = (sellCrossTowardCenter)? highThreshold >= currentValue : highThreshold <= currentValue;
				if ( prevToHighTh && currentToHighTh ) {
					retValue = 1;
				} else {
					retValue = 0.5;
				}
			}
			ret.put(keys.get(i), new double[]{retValue});
		}

		return ret;

	}

}
