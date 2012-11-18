package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class InvMomentumSmoother extends Smoother {

	private int period;
	private SortedMap<Date, double[]> pQs;
	private Date start;

	public InvMomentumSmoother(Date start, int period, SortedMap<Date, double[]> pQs) {
		this.period = period;
		this.pQs = pQs;
		this.start = start;
	}

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> momentum, Boolean fixLag) {

		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(momentum.tailMap(start).values());
		List<Date> keys = new ArrayList<Date>(momentum.tailMap(start).keySet());
		
		ret.putAll(pQs.subMap(keys.get(0), keys.get(period)));
		
		double prevQ = ret.get(keys.get(0))[0];
		for (int i = period; i < values.size(); i++) {

			double currentMomentumValue = values.get(i)[0];
			double value = prevQ * (currentMomentumValue +1);

			ret.put(keys.get(i), new double[]{value});
			
			prevQ = ret.get(keys.get(i-period))[0];

		}

		return ret;
	}

}
