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
package com.finance.pms.talib.indicators;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.tictactec.ta.lib.RetCode;

public class Aroon extends TalibIndicator {

	private double[] outAroonDown;
	private double[] outAroonUp;
	

	public Aroon(Number... indicatorParams) throws TalibException {
		super(indicatorParams);
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
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		RetCode rc = TalibCoreService.getCore().aroon(startIdx, endIdx, inData[1], inData[2], (Integer) indicatorParams[0], outBegIdx, outNBElement, outAroonDown, outAroonUp);
		return rc;
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

	public double[] getOutAroonDown() {
		return outAroonDown;
	}

	public double[] getOutAroonUp() {
		return outAroonUp;
	}


	@Override
	public Integer getStartShift() {
		return 50;
	}


	@Override
	public ValidityFilter quotationValidity() {
		return ValidityFilter.OHLC;
	}

}
