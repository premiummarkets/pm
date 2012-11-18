
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


package com.finance.pms.events.scoring.functions;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class InvHouseTrendSmoother extends Smoother {
	
	private int period;
	private SortedMap<Date, double[]> pQs;
	
	public InvHouseTrendSmoother(int period, SortedMap<Date, double[]> pQs) {
		super();
		this.period = period;
		this.pQs = pQs;
		
	}

	@Override
	public SortedMap<Date, double[]> smooth(SortedMap<Date, double[]> houseTrend, Boolean fixLag) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();
		List<Date> keys = new ArrayList<Date>();

		Date start;
		if (pQs != null) {
			
			List<Date> qKeys = new ArrayList<Date>(pQs.keySet());
			start = (houseTrend.firstKey().before(qKeys.get(period)))?qKeys.get(period):houseTrend.firstKey();
	
			int indexOfStart = qKeys.indexOf(start);
			if (indexOfStart == -1) throw new RuntimeException(new InvalidAlgorithmParameterException());
			SortedMap<Date, double[]> resInit = pQs.subMap(qKeys.get(indexOfStart - period), qKeys.get(indexOfStart));
			List<Date> keysInit = qKeys.subList(indexOfStart - period, indexOfStart);
			
			keys.addAll(keysInit);
			ret.putAll(resInit);
			
		} else {
			
			start = houseTrend.firstKey();
			keys.add(start);
			ret.put(start, new double[]{0.00001});
			
		}
		
		SortedMap<Date, double[]> houseTrendTail = houseTrend.tailMap(start);
		List<double[]> htValues = new ArrayList<double[]>(houseTrendTail.values());
		List<Date> htKeys = new ArrayList<Date>(houseTrendTail.keySet());
		
		keys.addAll(htKeys);
		
		int keysIdx = 0;
		for (int htValuesIdx = 0; htValuesIdx < htValues.size(); htValuesIdx++) {
			keysIdx = htValuesIdx + period;
			double prevQ = ret.get(keys.get(keysIdx-period))[0];
			double currentHouseTrendValue = htValues.get(htValuesIdx)[0];
			double value = prevQ * Math.pow(10, currentHouseTrendValue);
					
			ret.put(keys.get(keysIdx), new double[]{value});
		}

		return ret;
	}
	

}
