
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmthCrossDerivator implements DiscretDerivator {
	
	SortedMap<Date, double[]> smoothed;
	SortedMap<Date, double[]> control;
	private double lowThreshold = .15;
	private double highThreshold = .70;

	public SmthCrossDerivator(SortedMap<Date, double[]> smoothed, SortedMap<Date, double[]> control) {
		super();
		this.smoothed = smoothed;
		this.control = control;
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		for (int i = 1; i < values.size(); i++) {
			
			double drv = 0.5;
			double[] prevSmth = smoothed.get(keys.get(i-1));
			double[] smth = smoothed.get(keys.get(i));
			
			double[] prevCtl = control.get(keys.get(i-1));
			double[] ctl = control.get(keys.get(i));
			
			if (smth != null && prevSmth != null && prevCtl != null && ctl != null) {
				boolean isCtlUp = prevCtl[0] < ctl[0];
				boolean isCrossingAboveSmth = values.get(i-1)[0] < prevSmth[0] && smth[0] <= values.get(i)[0];
				boolean isBelowLowThr = lowThreshold >= values.get(i)[0];
				boolean isBelowCenter = 0.5 >= values.get(i)[0];
				if ( isCrossingAboveSmth && isBelowLowThr ) {
					drv = 0.0;
				} else {
					boolean isCtlDown = prevCtl[0] > ctl[0];
					boolean isCrossingBelowSmth = values.get(i-1)[0] >= prevSmth[0] &&  smth[0] >= values.get(i)[0];
					boolean isAboveHighThr = highThreshold <= values.get(i)[0];
					boolean isAboveCenter = 0.5 <= values.get(i)[0];
					if ( isCrossingBelowSmth && ( isAboveHighThr || (isAboveCenter && isCtlDown) ) ) {
						drv = 1.0;
					}
				}
			}
			
			ret.put(keys.get(i), new double[]{drv});
		}
		
		return ret;
		
	}

}
