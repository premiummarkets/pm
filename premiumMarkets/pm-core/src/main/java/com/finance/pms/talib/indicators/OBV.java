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
