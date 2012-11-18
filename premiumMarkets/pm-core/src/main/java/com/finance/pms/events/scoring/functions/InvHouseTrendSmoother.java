package com.finance.pms.events.scoring.functions;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class InvHouseTrendSmoother extends Smoother {
	
	private int period;
	private SortedMap<Date, double[]> pQs;
	
	public InvHouseTrendSmoother(int period, SortedMap<Date, double[]> pQs) {
		super();
		this.period = period;
		this.pQs = pQs;
		
	}

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> houseTrend, Boolean fixLag) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		List<Date> keys = new ArrayList<Date>();
		List<Date> qKeys = new ArrayList<Date>(pQs.keySet());
		
		Date start = (houseTrend.firstKey().before(qKeys.get(period)))?qKeys.get(period):houseTrend.firstKey();
		
		int indexOfStart = qKeys.indexOf(start);
		if (indexOfStart == -1) throw new RuntimeException(new InvalidAlgorithmParameterException());
		SortedMap<Date, double[]> resInit = pQs.subMap(qKeys.get(indexOfStart - period), qKeys.get(indexOfStart));
		List<Date> keysInit = qKeys.subList(indexOfStart - period, indexOfStart);
		
		keys.addAll(keysInit);
		ret.putAll(resInit);
		
		SortedMap<Date, double[]> houseTrendTail = houseTrend.tailMap(start);
		List<double[]> htValues = new ArrayList<double[]>(houseTrendTail.values());
		List<Date> htKeys = new ArrayList<Date>(houseTrendTail.keySet());
		
		keys.addAll(htKeys);
		
		int keysIdx = 0;
		for (int htValuesIdx = 0; htValuesIdx < htValues.size(); htValuesIdx++) {
			keysIdx = htValuesIdx + period;
			double prevQ = ret.get(keys.get(keysIdx-period))[0];
			double currentHouseTrendValue = htValues.get(htValuesIdx)[0];
			double value = prevQ * Math.pow(10, currentHouseTrendValue);
					
			ret.put(keys.get(keysIdx), new double[]{value});
		}

		return ret;
	}
	

}
