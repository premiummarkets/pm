package com.finance.pms.talib.indicators;

import java.util.Date;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.RetCode;

public class ChaikinLine extends TalibIndicator {
	
	private double[] chaikinLine;

	public ChaikinLine(Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency) throws TalibException, NoQuotationsException {
		super(stock, firstDate, 100, lastDate, 0, calculationCurrency);

	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double inLow[] = this.getIndicatorQuotationData().getLowValues();
		double inHigh[] = this.getIndicatorQuotationData().getHighValues();
		double inVolume[] = this.getIndicatorQuotationData().getVolumes();
		
		double[][] ret = new double[4][Math.max(Math.max(Math.max(closeValues.length, inHigh.length), inLow.length), inVolume.length)];
		ret[0]= closeValues;
		ret[1]= inLow;
		ret[2]= inHigh;
		ret[3]= inVolume;
		return 	ret;
	}

	@Override
	protected void initResArray(int length) {
		chaikinLine = new double[length];

	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		//RetCode rc = TalibCoreService.getCore().ad(startIdx, endIdx, inHigh, inLow, inClose, inVolume, outBegIdx, outNBElement, outReal);
		RetCode rc = TalibCoreService.getCore().ad(startIdx, endIdx, inData[2], inData[1], inData[0], inData[3], outBegIdx, outNBElement, chaikinLine);
		return rc;
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
		return chaikinLine;
	}

	public double[] getChaikinLine() {
		return chaikinLine;
	}

}
