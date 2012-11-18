package com.finance.pms.events.scoring.functions;

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.quotations.QuotationsFactories;

public class InvNormalizer {
	
	private Date start;
	private Date end;
	private double maxNorm;
	private double minNorm;
	
	

	public InvNormalizer(Date start, Date end, double minNorm, double maxNorm) {
		super();
		this.start = start;
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(endCal, 1);
		this.end = endCal.getTime();

		this.minNorm = minNorm;
		this.maxNorm = maxNorm;
	}



	public SortedMap<Date, double[]> normalised(SortedMap<Date, double[]> data) {
		
		//b = [(a - minA) / (maxA - minA)] * (maxNorm - minNorm) + minNorm 
		//with maxNorm = 1 and minNorm = 0
		//b = [(a - minA) / (maxA - minA)]
		
		if (data.get(data.firstKey()).length > 1) throw new NotImplementedException();
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;
		SortedMap<Date, double[]> subD = data.subMap(start, end);
		for (Date date : subD.keySet()) {
			double value = data.get(date)[0];
			if (value >= max) max = value;
			if (value <= min) min = value;
		}
		for (Date date : subD.keySet()) {
			double value = data.get(date)[0];
			ret.put(date, new double[]{ ((max-value)/(max-min)) * (maxNorm - minNorm) + minNorm});
		}
		
		return ret;
		
	}
	

}
