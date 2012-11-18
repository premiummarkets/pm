package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class TrendConfirmDerivator implements DiscretDerivator {
	
	SortedMap<Date, double[]> smoothedOfReal;

	public TrendConfirmDerivator(SortedMap<Date, double[]> smoothedOfReal) {
		super();
		this.smoothedOfReal = smoothedOfReal;
		
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 1; i < values.size(); i++) {
			
			double drv = 0.5;
			
			double[] prevSmth = smoothedOfReal.get(keys.get(i-1));
			double[] smth = smoothedOfReal.get(keys.get(i));
			
			if (smth != null && prevSmth != null) {
				boolean isSmthUp = prevSmth[0] < smth[0];
				boolean isDataUp = values.get(i-1)[0] < values.get(i)[0];
				if ( isSmthUp && isDataUp ) {
					drv = 0.0;
				} 
				else if (!isSmthUp && !isDataUp) {
					drv = 1.0;
				}
			}
			
			ret.put(keys.get(i), new double[]{drv});
		}
		
		return ret;
		
	}

}
