package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class CurvesStdDevComparator extends CurvesComparator {

	public CurvesStdDevComparator(Date start, Date end, int maxShift) {
		super(start, end, maxShift);
	}

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
//		Normalizer normalizer = new Normalizer(start, end, 0, 1);
//		SortedMap<Date, double[]> normalisedData = normalizer.normalised(data);
//		return normalisedData;
		return data;
	}

}
