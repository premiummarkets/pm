package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class CurvesSplitsSolver implements CurvesConstantOperation {

	@Override
	public SortedMap<Date, double[]> operate(SortedMap<Date, double[]> data1, double constant) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>(data1);
		CurvesMultiplication curvesSubstraction = new CurvesMultiplication();
		Date firstKey = data1.firstKey();
		
		List<Date> keysList = new ArrayList<Date>(data1.keySet());
		for (int j = 1; j < keysList.size(); j++) {
			double dj = data1.get(keysList.get(j))[0];
			double djm1 = data1.get(keysList.get(j-1))[0];
			double change = (dj - djm1)/djm1;
			if (Math.abs(change) > constant) {
				SortedMap<Date, double[]> solvedSub = curvesSubstraction.operate(ret.subMap(firstKey, keysList.get(j)), dj/djm1);
				ret.putAll(solvedSub);
			}
		}
		
		return ret;

	}

}
