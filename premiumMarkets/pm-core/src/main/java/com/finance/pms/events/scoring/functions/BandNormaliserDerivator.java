package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class BandNormaliserDerivator implements DiscretDerivator {
	
	private double lowThreshold;
	private double highThreshold;
	
	public BandNormaliserDerivator(double lowThreshold, double highThreshold) {
		super();
		this.lowThreshold = lowThreshold;
		this.highThreshold = highThreshold;
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 0; i < values.size(); i++) {
			double retValue = 0.5;
			double currentValue = values.get(i)[0];
			if (currentValue < lowThreshold) {
				retValue = 0;
			}
			if (currentValue > highThreshold) {
				retValue = 1;
			}
			
			ret.put(keys.get(i), new double[]{retValue});
		}

		return ret;
	}

}
