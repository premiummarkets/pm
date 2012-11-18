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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.RetCode;

public class StandardDeviation extends TalibIndicator {
	
	private double[] stdDev;
	private int period;
	
	
	public StandardDeviation(Stock stock, Integer timePeriod, Double ratio, Date startDate, Date endDate, Currency transactionCurrency) throws TalibException, NoQuotationsException {
		super(stock, startDate, timePeriod, endDate, 0, transactionCurrency, timePeriod, ratio);
		this.period = timePeriod;
		
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double[][] ret = new double[1][closeValues.length];
		ret[0]= closeValues;
		return 	ret;
	}

	@Override
	protected void initResArray(int length) {
		this.stdDev = new double[length];
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		RetCode rc = TalibCoreService.getCore().stdDev(startIdx, endIdx, inData[0], (Integer) indicatorParams[0], (Double) indicatorParams[1], outBegIdx, outNBElement, stdDev);
		return rc;
	}
	
	@Override
	protected String getHeader() {
		String header = "DATE,QUOTE,STDDEV\n";
		return header;
	}
	
	@Override
	protected String getLine(int indicator, int quotation) {
		String line =
				new SimpleDateFormat("yyyy-MM-dd").format(
						this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
						this.getIndicatorQuotationData().get(quotation).getClose() + "," +
						stdDev[indicator]  + "\n";
			return line;
	}

	public double[] getStdDev() {
		return stdDev;
	}

	public int getPeriod() {
		return period;
	}

	@Override
	public double[] getOutputData() {
		// TODO Auto-generated method stub
		return null;
	}

}
