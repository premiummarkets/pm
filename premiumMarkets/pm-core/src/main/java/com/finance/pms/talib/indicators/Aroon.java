package com.finance.pms.talib.indicators;

import java.util.Date;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.RetCode;

public class Aroon extends TalibIndicator {

	private double[] outAroonDown;
	private double[] outAroonUp;

	public Aroon(Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency) throws TalibException, NoQuotationsException {
		super(stock, firstDate, 50, lastDate, 0, calculationCurrency, 50);
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
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		//TalibCoreService.getCore().aroon(startIdx, endIdx, inHigh, inLow, optInTimePeriod, outBegIdx, outNBElement, outAroonDown, outAroonUp)
		RetCode rc = TalibCoreService.getCore().aroon(startIdx, endIdx, inData[0], inData[0], (Integer) indicatorParams[0], outBegIdx, outNBElement, outAroonDown, outAroonUp);
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
		throw new NotImplementedException();
	}

	public double[] getOutAroonDown() {
		return outAroonDown;
	}

	public double[] getOutAroonUp() {
		return outAroonUp;
	}

}
