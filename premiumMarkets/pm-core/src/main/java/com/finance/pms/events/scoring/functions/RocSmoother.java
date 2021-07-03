package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.quotations.QuotationsFactories;

public class RocSmoother extends Smoother {
	
	Integer reSeedingPeriod = 221;

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixBias) {
		
		TreeMap<Date, double[]> collected = new TreeMap<>();
		Iterator<Date> keyIterator = data.keySet().iterator();
		Date currentDate = keyIterator.next();
		Date reSeedDate = currentDate;
		
		while (reSeedDate.compareTo(data.lastKey()) <= 0) {
			reSeedDate = getNextReSeedingDate(currentDate);
			
			List<Double> iterationRocs = rocsFor(MapUtils.subMapInclusive(data, currentDate, reSeedDate), fixBias);
			double previousRocEnd = ((collected.size() > 0) ? collected.lastEntry().getValue()[0] : 0);
			double rocAcc = 1;
			for (int i = 0; i < iterationRocs.size(); i++) {
				collected.put(currentDate, new double[] {previousRocEnd + rocAcc});
				currentDate = keyIterator.next();
				rocAcc = rocAcc + iterationRocs.get(i);
			}
			collected.put(currentDate, new double[] {previousRocEnd + rocAcc});
		}

		return collected;
	}

	private Date getNextReSeedingDate(Date currentDate) {
		Date reSeedDate;
		Calendar reSeedDateCal = Calendar.getInstance();
		reSeedDateCal.setTime(currentDate);
		QuotationsFactories.getFactory().incrementDate(reSeedDateCal, reSeedingPeriod);
		reSeedDate = reSeedDateCal.getTime();
		return reSeedDate;
	}

	private List<Double> rocsFor(SortedMap<Date, double[]> data, Boolean fixBias) {
		
		List<double[]> values = new ArrayList<>(data.values());
		List<Double> rocs = new ArrayList<>();
		for(int i = 1; i < values.size(); i++) {
			double roc = (values.get(i)[0] - values.get(i-1)[0])/values.get(i-1)[0];
			rocs.add(roc);
		}
		
		List<Double> fixed = new ArrayList<>();
		if (fixBias) {
			MyApacheStats meanFunc = new MyApacheStats(new Mean());
			double mean = meanFunc.sEvaluate(rocs);
			MyApacheStats stdFunc = new MyApacheStats(new StandardDeviation());
			double stdev = stdFunc.sEvaluate(rocs);
			
			fixed = rocs.stream().map(r -> (r-mean)/stdev).collect(Collectors.toList());
			
		} else {
			fixed = rocs;
		}
		return fixed;
	}

}
