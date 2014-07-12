
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.finance.pms.events.scoring.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

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

	@Override
	public SortedMap<Date, Double> sOperate(SortedMap<Date, Double> data1, Double constant) {
		throw new NotImplementedException();
	}

}
