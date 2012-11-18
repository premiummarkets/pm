package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public class NullSmoother extends Smoother {

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> data, Boolean fixLag) {
		return data;
	}

}
