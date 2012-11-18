package com.finance.pms.events.calculation.houseIndicators;

import java.util.Date;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.tictactec.ta.lib.RetCode;

//TODO inherit form Indicator instead of Talib
//XXX
public class HouseAroon extends TalibIndicator {
	
	private double[] outAroonDown;
	private double[] outAroonUp;

	public HouseAroon(Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency, Integer period) throws NoQuotationsException, TalibException {
		super(stock, firstDate, 2*period, lastDate, 0, calculationCurrency, period);
	}

	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		
		Integer perd = (Integer) indicatorParams[0];
		
		outBegIdx.value = startIdx + perd;
		outNBElement.value = endIdx - outBegIdx.value;
		outBegDate = this.getIndicatorQuotationData().getDate(outBegIdx.value);
		
		for (int i = outBegIdx.value ; i <= endIdx; i++) {
			int[] periodHighLowIdxs = periodHighLowIdxs(i, inData[1], inData[2], inData[0], perd);
			outAroonUp[ i - outBegIdx.value ] = (new Double(perd - (i - periodHighLowIdxs[0]))/perd.doubleValue()) * 100;
			outAroonDown[ i - outBegIdx.value ] = (new Double(perd - (i - periodHighLowIdxs[1]))/perd.doubleValue()) * 100;
		}
		
		return RetCode.Success;
		
	}

	private int[] periodHighLowIdxs(Integer currentIdx, double[] lows, double[] highs, double[] closes, Integer perd) {
		
		int periodStartIdx = currentIdx - perd;
		
		Integer periodHighIdx = currentIdx;
		Integer periodLowIdx = currentIdx;
		
		
		double[] meanHighs = new double[currentIdx - periodStartIdx + 1];
		int smoothingPerd = 2*perd;
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

	@Override
	public void exportToCSV() {
		// TODO Auto-generated method stub

	}

	public double[] getOutAroonDown() {
		return outAroonDown;
	}

	public double[] getOutAroonUp() {
		return outAroonUp;
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double inLow[] = this.getIndicatorQuotationData().getLowValues();
		double inHigh[] = this.getIndicatorQuotationData().getHighValues();
		
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
		
	}

	@Override
	protected String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getLine(int indicator, int quotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getOutputData() {
		throw new NotImplementedException();
	}

}
