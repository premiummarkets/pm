/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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

public class MFI extends TalibIndicator {
	
	private double[] mfi;
    private Integer upperThreshold;
    private Integer lowerThreshold;
	private Integer period;
	

	public MFI(Stock stock, Integer timePeriod, Integer lowerThres, Integer upperThres, Date startDate, Date endDate, Currency transactionCurrency) throws TalibException, NoQuotationsException {
		super(stock, startDate, 100 + timePeriod,  endDate, 0, transactionCurrency , timePeriod);
		this.lowerThreshold = lowerThres;
		this.upperThreshold = upperThres;
		this.period = timePeriod;
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double inLow[] = this.getIndicatorQuotationData().getLowValues();
		double inHigh[] = this.getIndicatorQuotationData().getHighValues();
		double[] volumes = this.getIndicatorQuotationData().getVolumes();
		
		double[][] ret = new double[4][Math.max(Math.max(Math.max(closeValues.length,inHigh.length),inLow.length),volumes.length)];
		ret[0]= closeValues;
		ret[1]= inLow;
		ret[2]= inHigh;
		ret[3]= volumes;
		return 	ret;
	}
	

	@Override
	protected String getHeader() {
		return "DATE,QUOTE,MFI\n";
	}

	@Override
	protected String getLine(int indicator, int quotation) {
		String line =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
						this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
						this.getIndicatorQuotationData().get(quotation).getClose() + "," +
						mfi[indicator] + "\n";
			return line;
	}

	@Override
	protected void initResArray(int length) {
		mfi = new double[length];
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number... indicatorParams) {
		RetCode rc = TalibCoreService.getCore().mfi(startIdx, endIdx, inData[2], inData[1], inData[0], inData[3], (Integer)indicatorParams[0], outBegIdx, outNBElement, this.mfi);
		return rc;
	}
	
	public double[] getMfi() {
		return mfi;
	}

	public double getLowerThreshold() {
		return lowerThreshold;
	}

	public double getUpperThreshold() {
		return upperThreshold;
	}

	public Integer getPeriod() {
		return period;
	}

	@Override
	public double[] getOutputData() {
		// TODO Auto-generated method stub
		return null;
	}
}
