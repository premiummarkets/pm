package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class CurvesHouseDevDiscretComparator extends CurvesComparator {

	public CurvesHouseDevDiscretComparator(Date start, Date end, int maxShift) {
		super(start, end, maxShift);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected SortedMap<Date, double[]> normalize(Date start, Date end, SortedMap<Date, double[]> data) {
		//TODO
		Normalizer normalizer = new Normalizer(start, end, 1, 2); //Norm necessary as HouseTrend doesn't support negative or zero values
		SortedMap<Date, double[]> normalisedData = normalizer.normalised(data);
		HouseTrendSmoother houseTrendSmoother = new HouseTrendSmoother(9);
		SortedMap<Date, double[]> houseTrend = houseTrendSmoother.smooth(normalisedData, null);
		return houseTrend;
	}

	@Override
	protected double compareNormalised(Date start, Date end, SortedMap<Date, double[]> normalisedData, SortedMap<Date, double[]> normalisedRefData) {
		//TODO
		return 0;
	}

}
