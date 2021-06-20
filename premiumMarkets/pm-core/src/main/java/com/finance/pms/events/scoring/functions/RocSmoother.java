package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class RocSmoother extends Smoother {

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		
		TreeMap<Date, double[]> collected = new TreeMap<>();
		List<double[]> values = new ArrayList<>(data.values());
		Iterator<Date> keyIterator = data.keySet().iterator();
		keyIterator.next();
		double rocAcc = 1;
		for(int i = 1; i < values.size(); i++) {
			double roc = (values.get(i)[0] - values.get(i-1)[0])/values.get(i-1)[0];
			rocAcc = rocAcc + roc;
			collected.put(keyIterator.next(), new double[] {rocAcc});
		}
		
		return collected;
	}

}
