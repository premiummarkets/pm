/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.talib.indicators;

import java.text.SimpleDateFormat;

import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.tictactec.ta.lib.RetCode;

//FIXME period is not initialized in the context of TaLib generic reflection.
public class LinearRegression extends TalibIndicator {

	private double[] linearRegression;
	private Integer timePeriod;

	public LinearRegression() throws TalibException {
		super(0);
		this.timePeriod = 0;
	}


	public LinearRegression(Integer period) throws TalibException {
		super(period);
		this.timePeriod = period;
	}

	@Override
	protected double[][] getInputData(Quotations quotations) {
		double[] closeValues = quotations.getCloseValues();
		double[][] ret = new double[1][closeValues.length];
		ret[0]= closeValues;
		return 	ret;
	}

	@Override
	protected void initResArray(int length) {
		this.linearRegression = new double[length];
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inReal, Number... indicatorParams) {

		RetCode rc = RetCode.Success;
		Integer period = (Integer) indicatorParams[0];
		if (period == 0) {
			period = (endIdx-startIdx+1)*2/3;
		}
		rc = TalibCoreService.getCore().linearReg(startIdx, endIdx, inReal[0], period, outBegIdx, outNBElement, this.linearRegression);

		return rc;
	}

	@Override
	protected String getHeader() {
		return "DATE,QUOTE,LinearReg\n";
	}

	@Override
	protected String getLine(Integer indicator, QuotationUnit qU) {
		String line = new SimpleDateFormat("yyyy-MM-dd").format(qU.getDate()) + "," + qU.getCloseSp() + "," + linearRegression[indicator] + "\n";
		return line;
	}

	@Override
	public double[] getOutputData() {
		return null;
	}


	@Override
	public Integer getStartShift() {
		return timePeriod;
	}


	@Override
	public ValidityFilter quotationValidity() {
		return ValidityFilter.CLOSE;
	}
}
