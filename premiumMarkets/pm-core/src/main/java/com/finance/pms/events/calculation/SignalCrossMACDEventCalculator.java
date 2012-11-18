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
package com.finance.pms.events.calculation;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.MACD;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;

public class SignalCrossMACDEventCalculator extends TalibIndicatorsCompositionCalculator {

//	SMA sma;
//	private Integer smaQuotationStartDateIdx;
	MACD macd;
	private Integer macdQuotationStartDateIdx;
	
	public SignalCrossMACDEventCalculator(Stock stock, MACD macd, SMA sma, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate,calculationCurrency);
		
//		this.sma = sma;
//		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
//		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
//		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		this.macd = macd;
		macdQuotationStartDateIdx = macd.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer macdQuotationEndDateIdx = macd.getIndicatorQuotationData().getClosestIndexForDate(macdQuotationStartDateIdx, endDate);
		isValidData(stock, macd, startDate, macdQuotationStartDateIdx, macdQuotationEndDateIdx);
		
	}
	
	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMMACDSIGNALCROSS);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
//		Integer smaIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		Integer macdIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx);
		
		{
			//Quote above SMA,  macd below 0 crossover its signal
//			boolean isPriceAboveSMA = this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() > sma.getSma()[smaIndicatorIndex];
			boolean isMacdNegative = macd.getMacd()[macdIndicatorIndex] < 0;
			boolean isMacdCrossingOverSignal = (macd.getMacd()[macdIndicatorIndex-1] < macd.getSignal()[macdIndicatorIndex-1]) 	// 1rst macd < 1rst Signal
			&& (macd.getMacd()[macdIndicatorIndex] > macd.getSignal()[macdIndicatorIndex]); 					//last macd > last Signal
			res.setBullishCrossOver(isMacdNegative && isMacdCrossingOverSignal); 
			if (res.getBullishCrossOver()) return res;
		}
		{
			//Quote below SMA and macd above 0 cross below its signal
//			boolean isPriceBelowSMA = this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() < sma.getSma()[smaIndicatorIndex];
//			double alpha = macdStdDev(macdIndicatorIndex);
			boolean isMacdPositive = macd.getMacd()[macdIndicatorIndex] > 0 ;
			boolean isMacdCrossingBellowSignal = (macd.getMacd()[macdIndicatorIndex-1] > macd.getSignal()[macdIndicatorIndex-1]) 	// 1rst macd > 1rst Signal
			&& (macd.getMacd()[macdIndicatorIndex] < macd.getSignal()[macdIndicatorIndex]); 					//last macd < last Signal
			res.setBearishCrossBellow(isMacdPositive && isMacdCrossingBellowSignal); 
		}

		
		return res;
		
	}
	
//	private double macdStdDev(Integer index) {
//		//if (index - macdStdDevOutBegIdx.value > 0) return macdStdDevs[index-macdStdDevOutBegIdx.value];
//		return 0;
//		//return  1.0013;
//	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, indicatorIndex);
		if (indicator instanceof MACD) return this.isInDataRange((MACD)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}
	

	private boolean isInDataRange(SMA sma, Integer index) {
		return (0 < index && index < sma.getSma().length);
	}

	private Boolean isInDataRange(MACD macd, Integer index) {
		return (getDaysSpan() < index) && (index < macd.getMacd().length);
	}
	
	@Override
	protected int getDaysSpan() {
		return 10;
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMACDSIGNALCROSS,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMACDSIGNALCROSS,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
//		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		int macdQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,macdQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calculatorDate) + "," +calculatorClose + "," 
//			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," +this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
//			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)] +","
			+ this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getDate()+ "," +this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getClose() + ","
			+ this.macd.getMacd()[getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx)]*100+"," 
			+ this.macd.getSignal()[getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx)]*100;
//			+ this.macdStdDev(getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx));
		
		if (bearishEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null) {
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//return "CALCULATOR DATE, CALCULATOR QUOTE,SMA DATE, SMA QUOTE, SMA "+sma.getPeriod()+", MACD DATE, MACD QUOTE, MACD, SIGNAL, macd dev ,bearish, bullish\n";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MACD DATE, MACD QUOTE, MACD, SIGNAL,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMACDSIGNALCROSS;
	}
	
	
}
