package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class HouseTrendDiscretDerivator implements DiscretDerivator {
	
	
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		
		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 1; i < values.size(); i++) {
			double value = 0.5;
			if (values.get(i)[0] > values.get(i-1)[0]) {
				value = 0;
			} else if (values.get(i)[0] < values.get(i-1)[0]) {
				value = 1;
			}
			ret.put(keys.get(i), new double[]{value});
		}
		
		return ret;
		
	}

}
