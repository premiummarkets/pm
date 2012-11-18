package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

public abstract class Smoother {

	public abstract SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag);
	
	protected int trimStartingNaN(SortedMap<Date, double[]> data, List<Date> keys) {
		//Trim starting NaN
		int startIdx = 0;
		Boolean stop = false;
		for (; startIdx < keys.size(); startIdx++) {
			double[] ds = data.get(keys.get(startIdx));
			for (int j = 0; j < ds.length; j++) {
				if (!Double.isNaN(ds[j])) stop = true;
			}
			if (stop) break;
		}
		return startIdx;
	}

}
