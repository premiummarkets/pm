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

	MACD macd;
	private Integer macdQuotationStartDateIdx;
	
	public SignalCrossMACDEventCalculator(Stock stock, MACD macd, SMA sma, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate,calculationCurrency);
		
		this.macd = macd;
		macdQuotationStartDateIdx = macd.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
		Integer macdQuotationEndDateIdx = macd.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(macdQuotationStartDateIdx, endDate);
		isValidData(stock, macd, startDate, macdQuotationStartDateIdx, macdQuotationEndDateIdx);
		
	}
	
	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMMACDSIGNALCROSS);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Integer macdIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx);
		
		{;
			boolean isMacdNegative = macd.getMacd()[macdIndicatorIndex] < 0;
			boolean isMacdCrossingOverSignal = 
								(macd.getMacd()[macdIndicatorIndex-1] < macd.getSignal()[macdIndicatorIndex-1]) 	//1rst macd < 1rst Signal
								&& (macd.getMacd()[macdIndicatorIndex] > macd.getSignal()[macdIndicatorIndex]); 	//last macd > last Signal
			res.setBullishCrossOver(isMacdNegative && isMacdCrossingOverSignal); 
			if (res.getBullishCrossOver()) return res;
		}
		{
			boolean isMacdPositive = macd.getMacd()[macdIndicatorIndex] > 0 ;
			boolean isMacdCrossingBellowSignal = 
								(macd.getMacd()[macdIndicatorIndex-1] > macd.getSignal()[macdIndicatorIndex-1]) 	//1rst macd > 1rst Signal
								&& (macd.getMacd()[macdIndicatorIndex] < macd.getSignal()[macdIndicatorIndex]); 	//last macd < last Signal
			res.setBearishCrossBellow(isMacdPositive && isMacdCrossingBellowSignal); 
		}

		
		return res;
		
	}
	
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
		int macdQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,macdQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calculatorDate) + "," +calculatorClose + "," 
			+ this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getDate()+ "," +this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getClose() + ","
			+ this.macd.getMacd()[getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx)]*100+"," 
			+ this.macd.getSignal()[getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx)]*100;
		
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
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MACD DATE, MACD QUOTE, MACD, SIGNAL,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMACDSIGNALCROSS;
	}
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
		
		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.macd, calculatorIndex, macdQuotationStartDateIdx);
		return new double[]
				{
				this.macd.getMacd()[indicatorIndexFromCalculatorQuotationIndex],
				this.macd.getSignal()[indicatorIndexFromCalculatorQuotationIndex]
				};
	}
	
	
}
