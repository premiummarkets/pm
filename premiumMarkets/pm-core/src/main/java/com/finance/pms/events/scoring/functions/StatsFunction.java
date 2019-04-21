package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public interface StatsFunction {

	double mEvaluate(SortedMap<Date, Double > subMap);

}
