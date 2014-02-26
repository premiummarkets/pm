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

import java.text.SimpleDateFormat;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.RetCode;

public class StochasticOscillator extends TalibIndicator {

	protected static MyLogger LOGGER = MyLogger.getLogger(StochasticOscillator.class);
	
	private double[] slowK;
	private double[] slowD;
	
	private int fastKLookBackPeriod;
	private Integer slowKSmaPeriod;
	private Integer slowDSmaPeriod;

	public StochasticOscillator(Integer fastKLookBackPeriod, Integer slowKSmaPeriod, Integer slowDSmaPeriod) {
		super(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod);
		this.fastKLookBackPeriod = fastKLookBackPeriod;
		this.slowKSmaPeriod = slowKSmaPeriod;
		this.slowDSmaPeriod = slowDSmaPeriod;
	}


	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number...indicatorParams) {
		RetCode rc = TalibCoreService.getCore()
				.stoch(
						startIdx, endIdx, inData[2], inData[1], inData[0],
						(Integer) indicatorParams[0], (Integer) indicatorParams[1], MAType.Sma, (Integer)indicatorParams[2], MAType.Sma, 
						outBegIdx, outNBElement, slowK, slowD);
		
		return rc;
	}


	@Override
	protected void initResArray(int length) {
		slowD = new double[length];
		slowK = new double[length];
	}

	@Override
	protected String getHeader() {
		return "DATE,QUOTE,SLOWK,SLOWD\n";
	}

	@Override
	protected String getLine(Integer indicator, QuotationUnit qU) {
		String line =
			new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(
					qU.getDate()) + "," +
					qU.getClose() + "," +
					slowK[indicator] + "," + slowD[indicator] + "\n";
		return line;
	}

	@Override
	protected double[][] getInputData(Quotations quotations) {
		double[] closeValues = quotations.getCloseValues();
		double inLow[] = quotations.getLowValues();
		double inHigh[] = quotations.getHighValues();
		
		double[][] ret = new double[3][Math.max(Math.max(closeValues.length, inHigh.length), inLow.length)];
		ret[0]= closeValues;
		ret[1]= inLow;
		ret[2]= inHigh;
		return 	ret;
	}

	@Override
	public double[] getOutputData() {
		throw new NotImplementedException();
	}

	public double[] getSlowK() {
		return slowK;
	}

	public double[] getSlowD() {
		return slowD;
	}

	public double getLowerThreshold() {
		return 20;
	}

	public int getUpperThreshold() {
		return 80;
	}


	@Override
	public Integer getStartShift() {
		//return  fastKLookBackPeriod + 200;
		return fastKLookBackPeriod + slowKSmaPeriod + slowDSmaPeriod + 100;
	}


	@Override
	public ValidityFilter quotationValidity() {
		return ValidityFilter.OHLC;
	}	
	
}
