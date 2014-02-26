
/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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


package com.finance.pms.events.calculation.houseIndicators;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.tictactec.ta.lib.RetCode;

//TODO inherit form Indicator instead of Talib
//XXX
public class HouseAroon extends TalibIndicator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HouseAroon.class);
	
	private double[] outAroonDown;
	private double[] outAroonUp;
	private double[] outAroonOsc;
	private Integer period;

	public HouseAroon(Integer period) {
		super(period);
		this.period = period;
	}

	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		
		try {
			
			Integer period = (Integer) indicatorParams[0];
			
			outBegIdx.value = startIdx + getStartShift();
			outNBElement.value = endIdx - outBegIdx.value;
			
			//From Talib
			//			 outAroonUp[outIdx] = factor*(optInTimePeriod-(today-highestIdx));
			//	         outAroonDown[outIdx] = factor*(optInTimePeriod-(today-lowestIdx));
			//	         aroon = factor*(highestIdx-lowestIdx);
			
			for (int i = outBegIdx.value ; i <= endIdx; i++) {
				int[] periodHighLowIdxs = periodHighLowIdxs(i, inData[1], inData[2], inData[0], period);
				outAroonUp[ i - outBegIdx.value ] = (new Double(period - (i - periodHighLowIdxs[0]))/period.doubleValue()) * 100;
				outAroonDown[ i - outBegIdx.value ] = (new Double(period - (i - periodHighLowIdxs[1]))/period.doubleValue()) * 100;
				outAroonOsc[ i - outBegIdx.value ] = (new Double(periodHighLowIdxs[0] - periodHighLowIdxs[1])/period.doubleValue()) * 100;
			}
			
		} catch (IndexOutOfBoundsException e) {
			LOGGER.warn(e);
			return RetCode.OutOfRangeStartIndex;
		} catch (Exception e) {
			LOGGER.warn(e,e);
			return RetCode.OutOfRangeStartIndex;
		}
		
		return RetCode.Success;
		
	}

	private int[] periodHighLowIdxs(Integer currentIdx, double[] lows, double[] highs, double[] closes, Integer period) {
		
		int periodStartIdx = currentIdx - period;
		
		Integer periodHighIdx = currentIdx;
		Integer periodLowIdx = currentIdx;
		
		
		double[] meanHighs = new double[currentIdx - periodStartIdx + 1];
		int smoothingPerd = 2*period;
		for (int i = periodStartIdx; i <= currentIdx; i++) {
			int relIdx = i - periodStartIdx;
			double high = highs[i-smoothingPerd];
			for (int j = smoothingPerd+1; j >= 0; j--) {
				high = smooth(0.04, highs[i-j], high);
			}
			meanHighs[relIdx] = high;
		}
		double[] meanLows = new double[currentIdx - periodStartIdx + 1];
		for (int i = periodStartIdx; i <= currentIdx; i++) {
			int relIdx = i - periodStartIdx;
			double low = lows[i-smoothingPerd];
			for (int j = smoothingPerd+1; j >= 0; j--) {
				low = smooth(0.04, lows[i-j], low);
			}
			meanLows[relIdx] = low;
		}
		
		double periodHigh = meanHighs[meanHighs.length-1];
		double periodLow = meanLows[meanLows.length -1];
		
		for (int i = periodStartIdx; i <= currentIdx; i++) {
			
			int relIdx = i - periodStartIdx;
			
			if(periodHigh  < meanHighs[relIdx]) {
				periodHigh = meanHighs[relIdx];
				periodHighIdx = i;
			}
			if (meanLows[relIdx] < periodLow) {
				periodLow = meanLows[relIdx];
				periodLowIdx = i;
			}
		}
		
		return new int[] {periodHighIdx, periodLowIdx};
	}
	
	private double smooth(double alpha, double x, double prevSmooth) {
		return alpha * x + (1 - alpha) * (prevSmooth);
	}

	public double[] getOutAroonDown() {
		return outAroonDown;
	}

	public double[] getOutAroonUp() {
		return outAroonUp;
	}

	@Override
	protected double[][] getInputData(Quotations quotations) {
		double[] closeValues = quotations.getCloseValues();
		double inLow[] = quotations.getLowValues();
		double inHigh[] = quotations.getHighValues();
		
		double[][] ret = new double[3][Math.max(closeValues.length,Math.max(inLow.length, inHigh.length))];
		ret[0]= closeValues;
		ret[1]= inLow;
		ret[2]= inHigh;
		return 	ret;
	}

	@Override
	protected void initResArray(int length) {
		outAroonDown = new double[length];
		outAroonUp = new double[length];
		outAroonOsc = new double[length];
		
	}

	@Override
	protected String getHeader() {
		return null;
	}

	@Override
	protected String getLine(Integer indicator, QuotationUnit qU) {
		return null;
	}

	@Override
	public double[] getOutputData() {
		throw new NotImplementedException();
	}

	@Override
	public Integer getStartShift() {
		return 3*period +1;
	}

	@Override
	public ValidityFilter quotationValidity() {
		return ValidityFilter.OHLC;
	}

	public double[] getOutAroonOsc() {
		return outAroonOsc;
	}

}
