/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
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
import java.util.Arrays;
import java.util.Date;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.CalculationQuotations;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;


public class SMA extends TalibIndicator {
	
	private double[] sma;
	private Integer period;

	public SMA(Stock stock, Integer period, Date startDate, Date endDate, Currency calculationCurrency) throws TalibException, NoQuotationsException {
		super(stock, startDate, 2*period, endDate, 0, calculationCurrency, period);
		this.period = period;
	}
	
	public SMA(Stock stock, Integer period, Date startDate, Date endDate, Currency calculationCurrency, Integer startDateShift, Integer endDateShift) throws TalibException, NoQuotationsException {
		super(stock, startDate, startDateShift, endDate, endDateShift, calculationCurrency, period);
		this.period = period;
	}


	public SMA(TalibIndicator obv, Integer period) throws TalibException {
		super(new CalculationQuotations(obv.getIndicatorQuotationData().getStock(), obv.getStripedData(0), obv.getIndicatorQuotationData().getStock().getMarketValuation().getCurrency()), period);
	}

	/**
	 * @param startIdx
	 * @param endIdx
	 * @param inReal
	 * @param outNBElement
	 * @return
	 */
	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inReal, Number...indicatorsParams) {
		
		RetCode rc = RetCode.Success;
		Integer period = (Integer) indicatorsParams[0];
		
		if (period == 1) {
			this.sma = Arrays.copyOfRange(inReal[0], startIdx, endIdx + 10);
			outBegIdx = new MInteger();
			outBegIdx.value = startIdx;
			outNBElement = new MInteger();
			outNBElement.value = endIdx - outBegIdx.value;
		} else {
			rc = TalibCoreService.getCore().sma(startIdx, endIdx, inReal[0], period, outBegIdx, outNBElement, this.sma);
		}
		
		return rc;
	}
	
	@Override
	protected void initResArray(int length) {
		this.sma = new double[length];
	}

	@Override
	protected String getHeader() {
		return "DATE,QUOTE,SMA"+period+"\n";
	}

	@Override
	protected String getLine(int indicator, int quotation) {
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
					this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
					this.getIndicatorQuotationData().get(quotation).getClose() + "," +
					sma[indicator] + "\n";
		return line;
	}
	
	public double[] getSma() {
		return sma;
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double[][] ret = new double[1][closeValues.length];
		ret[0]= closeValues;
		return 	ret;
	}
	
	
	public Integer getPeriod() {
		return period;
	}

	@Override
	public double[] getOutputData() {
		return sma;
	}

	
} 
