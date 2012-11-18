package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class LnInvSmoother extends Smoother {

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		if (fixLag) throw new NotImplementedException();
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = 1; i < keys.size(); i++) {
			double[] ds = data.get(keys.get(i));
			double[] smoothedDs = new double[ds.length];
			for (int j = 0; j < ds.length; j++) {
				smoothedDs[j] = Math.exp(ds[j]);
			}
			ret.put(keys.get(i), smoothedDs);
		}
		
		return ret;
	}

}
