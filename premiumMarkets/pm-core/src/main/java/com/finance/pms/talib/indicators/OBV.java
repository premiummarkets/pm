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

public class OBV extends TalibIndicator {
	
	
	private double[] obv;

	public OBV(Stock stock, Date startDate, Date endDate, Currency transactionCurrency) throws TalibException, NoQuotationsException {
		super(stock, startDate, 150, endDate, 0, transactionCurrency);
	}

	@Override
	protected String getHeader() {
		String header = "DATE,QUOTE,VOLUME,OBV\n";
		return header;
	}

	@Override
	protected String getLine(int indicator, int quotation) {
		String line =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
						this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
						this.getIndicatorQuotationData().get(quotation).getClose() + "," +
						this.getIndicatorQuotationData().get(quotation).getVolume()+ "," +
						obv[indicator] + "\n";
		return line;
	}

	@Override
	protected void initResArray(int length) {
		obv = new double[length];
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] indata, Number... indicatorParams) {
		RetCode rc = TalibCoreService.getCore().obv(startIdx, endIdx, indata[0], indata[1], outBegIdx, outNBElement, this.obv);
		return rc;
	}

	@Override
	protected double[][] getInputData()  {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double[] volumes = this.getIndicatorQuotationData().getVolumes();
		double[][] ret = new double[2][Math.max(closeValues.length,volumes.length)];
		ret[0]= closeValues;
		ret[1]= volumes;
		return 	ret;

	}
	
	public double[] getObv() {
		return obv;
	}

	@Override
	public double[] getOutputData() {
		return obv;
	}
}
