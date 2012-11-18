package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class HouseTrendSmoother extends Smoother {
	
	private int period;
	
	public HouseTrendSmoother(int period) {
		super();
		this.period = period;
	}

	public HouseTrendSmoother() {
		this.period = 1;
	}


	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		for (int i = period; i < values.size(); i++) {
			double currentValue = values.get(i)[0];
			double previousValue = values.get(i-period)[0];
			
			if (currentValue <= 0 || previousValue <= 0 ) throw new NotImplementedException("currentValue : "+currentValue+", previousValue "+previousValue);
			
			double value = Math.log10(currentValue/previousValue);
			ret.put(keys.get(i), new double[]{value});
		}

		return ret;

	}

}
