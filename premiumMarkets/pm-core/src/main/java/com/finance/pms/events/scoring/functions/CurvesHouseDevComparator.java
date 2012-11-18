package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class CurvesHouseDevComparator extends CurvesComparator {

	public CurvesHouseDevComparator(Date start, Date end, int maxShift) {
		super(start, end, maxShift);
	}

	@Override
	protected double compareNormalised(Date start, Date end, SortedMap<Date, double[]> normalisedData, SortedMap<Date, double[]> normalisedRefData) {
		
		int nbValues = 0;
		double shiftErr = 0;
		for (Date date : normalisedData.keySet()) {
			if (date.after(start) && date.before(end)) {
				double hdData[] = normalisedData.get(date);
				double hdRefData[] = normalisedRefData.get(date);
				if (hdRefData != null && !Double.isNaN(hdData[0]) && !Double.isNaN(hdRefData[0])) {
					double delta = hdData[0] - hdRefData[0];
					shiftErr = shiftErr + delta * delta;
					nbValues++;
				}
			}
		}
		
		shiftErr = shiftErr / nbValues;
		return shiftErr;
		
	}

	
	protected SortedMap<Date, double[]> normalize(Date start, Date end, SortedMap<Date, double[]> data) {
		ZeroLagEMASmoother emaSmoother = new ZeroLagEMASmoother(9);
		SortedMap<Date, double[]> smoothed = emaSmoother.smooth(data, null);
		Normalizer normalizer = new Normalizer(start, end, 1, 11); //Norm necessary as HouseTrend doesn't support negative or zero values
		SortedMap<Date, double[]> normalisedData = normalizer.normalised(smoothed);
		HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother(1);
		SortedMap<Date, double[]> houseTrend = houseTrendSmoother.smooth(normalisedData, null);
		return houseTrend;
	}

}
