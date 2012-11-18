
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

public class UnEvenSlopeDerivator implements DiscretDerivator {
	
	private int period;
	private double slope;
	private int sellShift;
	private int buyShift;

	public UnEvenSlopeDerivator(int period, double slope, int sellShift, int buyShift) {
		this.period = period;
		this.slope = slope;
		this.sellShift = sellShift;
		this.buyShift = buyShift;
		
		if (sellShift > buyShift) throw new NotImplementedException();
	}

	@Override
	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data) {
		
		SortedMap<Date, double[]> ret = new TreeMap<Date, double[]>();

		List<double[]> values = new ArrayList<double[]>(data.values());
		List<Date> keys = new ArrayList<Date>(data.keySet());
		
		int startIdx = period + Math.max(sellShift, buyShift);
		
		for (int i = 0; i < startIdx; i++) {
			ret.put(keys.get(i), new double[]{0.5});
		}
		
		for (int i = startIdx; i < values.size(); i++) {
			
			double drv = 0.5;
			boolean isSellSlope = isSellSlopeAt(i-sellShift, values);
			if ( isSellSlope ) {
				drv = 1;
			} else {
				Boolean isSellOverLap = false;
				for (int k = i-buyShift+sellShift; k <= i; k++) {
					isSellOverLap = isSellOverLap || isSellSlopeAt(k-sellShift, values);
					if (isSellOverLap) break;
				}
				boolean isBuySlope = isBuySlopAt(i-buyShift, values);
				if ( isBuySlope && !isSellOverLap) {
					drv = 0;
				} 
			}
			ret.put(keys.get(i), new double[]{drv});
		}

		return ret;
	}

	private boolean isBuySlopAt(int j, List<double[]> values) {
		double prevBuy = values.get(j-period)[0];
		double currentBuy = values.get(j)[0];
		double percentDiffBuy = (currentBuy - prevBuy)/prevBuy;
		boolean isBuySlope = percentDiffBuy > (slope * new Double(period));
		return isBuySlope;
	}

	private boolean isSellSlopeAt(int j, List<double[]> values) {
		double prevSell = values.get(j-period)[0];
		double currentSell = values.get(j)[0];
		double percentDiffSell = (currentSell - prevSell)/prevSell;
		boolean isSellSlope = percentDiffSell < (-slope * new Double(period));
		return isSellSlope;
	}

}
