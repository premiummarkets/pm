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
