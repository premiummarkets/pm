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
