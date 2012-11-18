package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmthCrossDerivator implements DiscretDerivator {
	
	SortedMap<Date, double[]> smoothed;
	SortedMap<Date, double[]> control;
	private double lowThreshold = .15;
	private double highThreshold = .70;

	public SmthCrossDerivator(SortedMap<Date, double[]> smoothed, SortedMap<Date, double[]> control) {
		super();
		this.smoothed = smoothed;
		this.control = control;
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 1; i < values.size(); i++) {
			
			double drv = 0.5;
			double[] prevSmth = smoothed.get(keys.get(i-1));
			double[] smth = smoothed.get(keys.get(i));
			
			double[] prevCtl = control.get(keys.get(i-1));
			double[] ctl = control.get(keys.get(i));
			
			if (smth != null && prevSmth != null && prevCtl != null && ctl != null) {
				boolean isCtlUp = prevCtl[0] < ctl[0];
				boolean isCrossingAboveSmth = values.get(i-1)[0] < prevSmth[0] && smth[0] <= values.get(i)[0];
				boolean isBelowLowThr = lowThreshold >= values.get(i)[0];
				boolean isBelowCenter = 0.5 >= values.get(i)[0];
				if ( isCrossingAboveSmth && isBelowLowThr ) {
					drv = 0.0;
				} else {
					boolean isCtlDown = prevCtl[0] > ctl[0];
					boolean isCrossingBelowSmth = values.get(i-1)[0] >= prevSmth[0] &&  smth[0] >= values.get(i)[0];
					boolean isAboveHighThr = highThreshold <= values.get(i)[0];
					boolean isAboveCenter = 0.5 <= values.get(i)[0];
					if ( isCrossingBelowSmth && ( isAboveHighThr || (isAboveCenter && isCtlDown) ) ) {
						drv = 1.0;
					}
				}
			}
			
			ret.put(keys.get(i), new double[]{drv});
		}
		
		return ret;
		
	}

}
