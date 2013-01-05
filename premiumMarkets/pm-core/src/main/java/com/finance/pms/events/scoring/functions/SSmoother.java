package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public interface SSmoother {
	
	public abstract SortedMap<Date, Double> sSmooth(SortedMap<Date, Double> data, Boolean fixLag);

}
