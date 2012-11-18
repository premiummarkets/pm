package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class HouseTrendDiscretSmoother extends Smoother {

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 1; i < values.size(); i++) {
			double currentValue = values.get(i)[0];
			double previousValue = values.get(i-1)[0];
			double value = 0.5;
			if (currentValue < previousValue) {
				value = 1;
			} else if (currentValue > previousValue) {
				value = 0;
			}
			ret.put(keys.get(i), new double[]{value});
		}

		return ret;

	}

}
