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
import java.util.LinkedList;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventType;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.RetCode;

public class StochasticOscillator extends TalibIndicator {

	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(StochasticOscillator.class);
	
    @SuppressWarnings("unused")
	private Integer fastKPeriod = 14;
    @SuppressWarnings("unused")
	private Integer slowKPeriod = 3;
    public Integer slowDPeriod = 5;
	
    /** The signal. */
	private double[] slowK;
	
	/** The history. */
	private double[] slowD;
	
	private EventType previousTrend;
	//private Double previousK;
	private LinkedList<Double> mins = new LinkedList<Double>();
	private LinkedList<Double> maxs =  new LinkedList<Double>();
	
	private Double currentMin = 100D;
	private Double currentMax= 0D;
	private Double absMin = 100D;
	private Double absMax= 0D;


	//FIXME
//	private StochasticOscillator(Stock stock, Quotations quotations, Integer fastKPeriod, Integer slowDPeriod, Integer slowKPeriod) throws TalibException {
//		super(stock, quotations,fastKPeriod,slowKPeriod,slowDPeriod);
//		this.fastKPeriod = fastKPeriod;
//		this.slowDPeriod = slowDPeriod;
//		this.slowKPeriod = slowKPeriod;
//	}
	protected StochasticOscillator(Stock stock, Date firstDate, Date lastDate, Currency transactionCurrency, Integer timePeriod,
			Number[] indicatorParams) throws TalibException, NoQuotationsException {
		super(stock, firstDate, timePeriod, lastDate, 0, transactionCurrency, indicatorParams);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected RetCode talibCall(Integer startIdx, Integer endIdx, double[][] inData, Number...indicatorParams) {
		
		RetCode rc = TalibCoreService.getCore().stoch(startIdx, endIdx, inData[2], inData[1], inData[0],
				(Integer) indicatorParams[0],(Integer) indicatorParams[1], MAType.Sma, (Integer)indicatorParams[2], MAType.Sma, outBegIdx, outNBElement, this.slowK, this.slowD);
		
		return rc;
	}

	//TODO Create a calculator for Stochastic
	protected void eventFormulaCalculation(FormulatRes res, Integer indicatorIndex, Integer quotationIndex) {

		if (this.slowK[indicatorIndex] <= 20) {
			 
			//Mins detection
			if ((this.slowK[indicatorIndex - 1] > this.slowK[indicatorIndex])
					&& (this.slowK[indicatorIndex] < this.slowK[indicatorIndex + 1])) {
				
				//mins.add(this.slowK[indicatorIndex]);
				if (currentMin > this.slowK[indicatorIndex]) currentMin = this.slowK[indicatorIndex]; 
				
				LOGGER.trace("Mins for date : " + this.slowK[indicatorIndex] + " on "
						+ this.getIndicatorQuotationData().get(quotationIndex).getDate() + " for "+ this.getStockName());
			}
			
			//bullish signal detection (needs an up trend of at least two mins)
			if (this.slowK[indicatorIndex + 1] > 20) { // 1rst slowK < 20 && last slowK > 20
				
				mins.add(currentMin);
				if (absMin > currentMin) absMin = currentMin; 
				currentMin = 100D;
				
				if (mins.size() >= 2) {
					double last = mins.pollLast();
					if (absMin < last) {
						//absMin = 100D;
						LOGGER.trace("Mins " + mins + " for "+ this.getStockName());
//						if (mins.size() > 2) res.setBullishcrossOver(
//								EventType.BULLISH.equals(previousTrend) && mins.size() != 0 && 
//								mins.get(mins.size() - 1) > mins.get(mins.size() - 2));
						res.setBullishcrossOver(EventType.BULLISH.equals(previousTrend));
						
					}
				}
				
				previousTrend = EventType.BULLISH;
			}
		} 
		else if (this.slowK[indicatorIndex] >= 80) {
			
			//Max detection
			if ((this.slowK[indicatorIndex - 1] < this.slowK[indicatorIndex])
					&& (this.slowK[indicatorIndex] > this.slowK[indicatorIndex + 1])) {
				
				//maxs.add(this.slowK[indicatorIndex]);
				if (currentMax < this.slowK[indicatorIndex]) currentMax = this.slowK[indicatorIndex]; 
				
				LOGGER.trace("Maxs for date : " + this.slowK[indicatorIndex] + " on "
						+ this.getIndicatorQuotationData().get(quotationIndex).getDate() + " for "+ this.getStockName());
			}
			
			//bearish signal detection (needs a down trend of at least two preceding maxs)
			if (this.slowK[indicatorIndex + 1] < 80) { // 1rst slowK > 80 and last slowK < 80
				
				maxs.add(currentMax);
				if (absMax < currentMax) absMax = currentMax; 	
				currentMax = 0D;
				
				if (maxs.size() >= 2) {
					double last = maxs.pollLast();
					
					if (absMax > last) {
						//absMax = 0D;
						LOGGER.trace("Maxs " + maxs  + " for "+ this.getStockName());
//						 res.setBearishcrossBellow(
//								EventType.BEARISH.equals(previousTrend) && maxs.size() != 0 && 
//								maxs.get(maxs.size() - 1) < maxs.get(maxs.size() - 2));
						res.setBearishcrossBellow(EventType.BEARISH.equals(previousTrend));
					}
				}
				
				previousTrend = EventType.BEARISH;
				
			}
		} 
		else if (this.slowK[indicatorIndex] >= 50 && 50 > this.slowK[indicatorIndex + 1]) { //down through 50 ends bearish trend
			//reset the bearish signals
			absMax = 0D;
			currentMax = 0D;
			maxs.clear();
		} else if (this.slowK[indicatorIndex] <= 50 && 50 < this.slowK[indicatorIndex + 1]) { //up through 50 ends bullish trend
			//reset the bullish signals
			absMin = 100D;
			currentMin = 100D;
			mins.clear();
		}
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
	protected String getLine(int indicator, int quotation) {
		String line =
			new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(
					this.getIndicatorQuotationData().get(quotation).getDate()) + "," +
					this.getIndicatorQuotationData().get(quotation).getClose() + "," +
					slowK[indicator] + "," + slowD[indicator] + "\n";
		return line;
	}

	@Override
	protected double[][] getInputData() {
		double[] closeValues = this.getIndicatorQuotationData().getCloseValues();
		double inLow[] = this.getIndicatorQuotationData().getLowValues();
		double inHigh[] = this.getIndicatorQuotationData().getHighValues();
		
		double[][] ret = new double[1][Math.max(Math.max(closeValues.length,inHigh.length),inLow.length)];
		ret[0]= closeValues;
		ret[1]= inLow;
		ret[2]= inHigh;
		return 	ret;
	}
	
	public Boolean isInDataRange(int index) {
		return 0< index && (index < (this.slowK.length -1));
	}
	
	
	
	
}
