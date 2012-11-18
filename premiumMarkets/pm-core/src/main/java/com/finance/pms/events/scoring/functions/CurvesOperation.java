package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public interface CurvesOperation {
	
	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> data1, SortedMap<Date, double[]> data2);

}
