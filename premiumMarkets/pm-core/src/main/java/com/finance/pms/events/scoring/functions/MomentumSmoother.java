package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MomentumSmoother extends Smoother {

	private int period;

	public MomentumSmoother(int period) {
		this.period = period;
	}

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = period; i < values.size(); i++) {

			double currentValue = values.get(i)[0];
			double previousValue = values.get(i-period)[0];
			double value = (currentValue - previousValue)/previousValue;
			
			//DecimalFormat twoDForm = new DecimalFormat("#.##");
			//ret.put(keys.get(i), new double[]{Double.valueOf(twoDForm.format(value))});
			ret.put(keys.get(i), new double[]{value});

		}

		return ret;
	}

}
