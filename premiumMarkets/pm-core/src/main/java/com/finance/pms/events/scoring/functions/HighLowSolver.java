package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.SortedMap;

public interface HighLowSolver {

	Boolean higherHigh(Double[] data, int smoothingPeriod, int minimumNbDaysBetweenExtremes, SortedMap<Integer, Double> higherHighs, ArrayList<Double> expertTangent);

}
