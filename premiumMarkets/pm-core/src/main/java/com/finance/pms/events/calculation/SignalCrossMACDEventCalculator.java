/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.MACD;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

@Deprecated //Rewrite to match corresponding edited EventInfoOpsCompo 
public class SignalCrossMACDEventCalculator extends TalibIndicatorsOperator {

	MACD macd;

	public SignalCrossMACDEventCalculator(Integer macdFastPeriod, Integer macdSlowPeriod, Integer macdSignalPeriod, Observer[] observers) {
		super(EventDefinition.PMMACDSIGNALCROSS, observers);
		init(macdFastPeriod, macdSlowPeriod, macdSignalPeriod);
	}
	
	public SignalCrossMACDEventCalculator(EventInfo reference) {
	    //Reflective ops generator
		super(reference);
	}

	protected void init(Integer macdFastPeriod, Integer macdSlowPeriod, Integer macdSignalPeriod) {
	    this.macd = new MACD(macdFastPeriod, macdSlowPeriod, macdSignalPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
	    init(constants[0], constants[1], constants[2]);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException{
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());
		
		Integer macdIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.macd, quotationIdx);
		
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects){
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMMACDSIGNALCROSS,EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMMACDSIGNALCROSS,EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
		//int macdQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,macdQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calculatorDate) + "," +calculatorClose + "," 
			//+ this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getDate()+ "," +this.macd.getIndicatorQuotationData().get(macdQuotationIndex).getClose() + ","
			+ this.macd.getMacd()[getIndicatorIndexFromQuotationIndex(this.macd, calculatorIndex)]*100+"," 
			+ this.macd.getSignal()[getIndicatorIndexFromQuotationIndex(this.macd, calculatorIndex)]*100;
		
		if (bearishEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null) {
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearExpects)+"\n";
		
		return line;
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//String head = "CALCULATOR DATE, CALCULATOR QUOTE, MACD DATE, MACD QUOTE, MACD, SIGNAL,bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MACD, SIGNAL,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMACDSIGNALCROSS;
	}
	
	@Override
	protected double[] buildOneOutput(QuotationUnit qU, Integer calculatorIndex) {
		
		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromQuotationIndex(this.macd, calculatorIndex);
		return new double[]
				{
				this.macd.getMacd()[indicatorIndexFromCalculatorQuotationIndex],
				this.macd.getSignal()[indicatorIndexFromCalculatorQuotationIndex]
				};
	}

	@Override
	public Integer getStartShift() {
		return macd.getStartShift() + getDaysSpan();
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.macd.calculateIndicator(quotations);
		
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return macd.getOutBegIdx().value + getDaysSpan();
	}
	
	
}
