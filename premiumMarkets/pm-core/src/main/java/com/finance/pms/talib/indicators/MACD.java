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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.RetCode;

// TODO: Auto-generated Javadoc
/**
 * The Class MACD.
 * 
 * @author Guillaume Thoreton
 */
public class MACD extends TalibIndicator {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MACD.class);
	
	public Integer fastPeriod;
	public Integer slowPeriod;
	public Integer signalPeriod;
	
	
	private double[] macd;
	private double[] signal;
	private double[] history;

	/**
	 * Instantiates a new mACD.
	 * 
	 * @param stock the stock
	 * @param firstDate the first date
	 * @param lastDate the last date
	 * 
	 * @author Guillaume Thoreton
	 * @param transactionCurrency 
	 * @param endDate2 
	 * @param endDate 
	 * @throws TalibException 
	 * @throws NoQuotationsException 
	 */
	public MACD(Stock stock, Integer fastPeriod, Integer slowPeriod, Integer signalPeriod, Date startDate, Date endDate, Currency transactionCurrency) throws TalibException, NoQuotationsException {
		super(stock,startDate, Math.max(slowPeriod, fastPeriod) + signalPeriod +1, endDate, 0, transactionCurrency, fastPeriod, slowPeriod, signalPeriod);
		
		this.fastPeriod = fastPeriod;
		this.signalPeriod = signalPeriod;
		this.slowPeriod = slowPeriod;
	}

	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number...indicatorParams) {
		RetCode rc = TalibCoreService.getCore().macd(startIdx, endIdx, inData[0], (Integer)indicatorParams[0], (Integer) indicatorParams[1],(Integer) indicatorParams[2], outBegIdx, outNBElement, macd, signal, history);
		return rc;
	}


	@Override
	protected void initResArray(int length) {
		macd = new double[length];
		signal = new double[length];
		history = new double[length];
	}


	/**
	 * @param df
	 * @param i
	 * @return
	 */
	@Override
	protected String getLine(int indicator, int quotation) {
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
					this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
					this.getIndicatorQuotationData().get(quotation).getClose() + "," +
					macd[indicator] + "," + signal[indicator]  + "," + history[indicator]  + "\n";
		return line;
	}

	/**
	 * @return
	 */
	@Override
	protected String getHeader() {
		String header = "DATE,QUOTE,MACD,SIGNAL,HISTORY\n";
		return header;
	}
	
	public double[] getMacd() {
		return macd;
	}

	public double[] getSignal() {
		return signal;
	}

	public double[] getHistory() {
		return history;
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double[][] ret = new double[1][closeValues.length];
		ret[0]= closeValues;
		return 	ret;
	}

	@Override
	public double[] getOutputData() {
		// TODO Auto-generated method stub
		return null;
	}

}
