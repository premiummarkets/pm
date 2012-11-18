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
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;

public class RSIThreshold extends TalibIndicatorsCompositionCalculator {
	
//	SMA sma;
//	private Integer smaQuotationStartDateIdx;
	RSI rsi;
	private Integer rsiQuotationStartDateIdx;
	
	public RSIThreshold(Stock stock, SMA sma, RSI rsi, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
//		this.sma = sma;
		this.rsi = rsi;
		
//		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
//		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
//		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		rsiQuotationStartDateIdx = rsi.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer macdQuotationEndDateIdx = rsi.getIndicatorQuotationData().getClosestIndexForDate(rsiQuotationStartDateIdx, endDate);
		isValidData(stock, rsi, startDate, rsiQuotationStartDateIdx, macdQuotationEndDateIdx);
	}


	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMRSITHRESHOLD);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
//		Integer smaIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		Integer rsiIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.rsi, calculatorIndex, rsiQuotationStartDateIdx);

		
		{
			//BULL : RSI cross below low threshold (over sold) with an up trend (above sma)
			boolean isRSICrossingBelow = this.rsi.getRsi()[rsiIndicatorIndex-1] > rsi.getLowerThreshold() && rsi.getLowerThreshold() > this.rsi.getRsi()[rsiIndicatorIndex]; // 1rst rsi > 30 > last rsi 
//			boolean isPriceAboveSMA = this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() > sma.getSma()[smaIndicatorIndex];
			res.setBullishCrossOver(isRSICrossingBelow);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : RSI cross above upper threshold (over bought) with a down trend (under sma)
			boolean isRSICrossingAbove = this.rsi.getRsi()[rsiIndicatorIndex-1]  < rsi.getUpperThreshold()  && rsi.getUpperThreshold() < this.rsi.getRsi()[rsiIndicatorIndex];// 1rst rsi < 70 <  last rsi 
//			boolean isPriceBelowSMA = this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() < sma.getSma()[smaIndicatorIndex];
			res.setBearishCrossBellow(isRSICrossingAbove);
		}
		
		return res;
	}
	
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, index);
		if (indicator instanceof RSI) return this.isInDataRange((RSI)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return (getDaysSpan() <= index && index < sma.getSma().length);
	}
	
	public Boolean isInDataRange(RSI rsi, Integer index) {
		return (getDaysSpan() <= index && index < rsi.getRsi().length);
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		return "CALCULATOR DATE, CALCULATOR QUOTE, SMA DATE, SMA QUOTE, SMA50, RSI DATE, RSI QUOTE, LOW TH, UP TH, RSI,bearish, bullish\n";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, RSI DATE, RSI QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMRSITHRESHOLD,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMRSITHRESHOLD,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
//		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		int macdQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,rsiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
//			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," +this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
//			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)] +","
			+ this.rsi.getIndicatorQuotationData().get(macdQuotationIndex).getDate()+ "," +this.rsi.getIndicatorQuotationData().get(macdQuotationIndex).getClose() + ","
			+ this.rsi.getLowerThreshold() + ","
			+ this.rsi.getUpperThreshold() + ","
			+ this.rsi.getRsi()[getIndicatorIndexFromCalculatorQuotationIndex(this.rsi, calculatorIndex, rsiQuotationStartDateIdx)];
		
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
	protected int getDaysSpan() {
		return 1;
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMRSITHRESHOLD;
	}
}
