package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

public interface StatsFunction {

	double dEvaluateMd(SortedMap<Date, Double> subMap);
	
	SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap);

	SortedMap<Date, double[]> madEvaluateMd(SortedMap<Date, Double> subMap);
	
	double[] adEvaluateMd(SortedMap<Date, Double> subMap);
	
	List<String> getOutputsRefs();
	
	int getMinPeriod();

}
