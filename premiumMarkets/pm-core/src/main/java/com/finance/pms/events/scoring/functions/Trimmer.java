package com.finance.pms.events.scoring.functions;

import java.util.Calendar;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationsFactories;

public class Trimmer {
	
	private static MyLogger LOGGER = MyLogger.getLogger(Trimmer.class);
	
	private ApacheStats stdev;
	
	private int trimFactor;
	private Date start;
	private Date end;
	
	
	public Trimmer(int trimFactor, Date start, Date end) {
		super();
		stdev = new ApacheStats(new StandardDeviation());
		
		this.trimFactor = trimFactor;
		
		this.start = start;
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		QuotationsFactories.getFactory().incrementDate(endCal, 1);
		this.end = endCal.getTime();
	}


	public SortedMap<Date, double[]> trimmed(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> subMap = data.subMap(start, end);
		
		double allStdev = stdev.evaluate(subMap.values());
		LOGGER.info("Overall stdev : "+allStdev);
		
		SortedMap<Date, double[]> trimedHouseTrend = new TreeMap<Date, double[]>();
		
		double positiveMax = trimFactor*allStdev;
		double negativeMax = trimFactor*allStdev;

		for (Date date : subMap.keySet()) { 

			double[] ds = subMap.get(date);
			if (ds[0] > positiveMax) {
				trimedHouseTrend.put(date, new double[]{positiveMax});
			}
			else if (ds[0] < -negativeMax) {
				trimedHouseTrend.put(date, new double[]{-negativeMax});
			} else {
				trimedHouseTrend.put(date, ds);
			}
			
		}
		
		return trimedHouseTrend;
	}

}
