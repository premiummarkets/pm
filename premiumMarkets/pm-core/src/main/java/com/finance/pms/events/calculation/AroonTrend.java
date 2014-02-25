
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
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

//TODO this is not a TalibComposition if we use HouseAroon.
//Add an extra layer under EventCompositionCalulator to deal with house indicators
public class AroonTrend extends TalibIndicatorsCompositionCalculator {
	
	int upperThreshold = 90;
	int lowerThreshold = 30;
	int middleThreshold = 50;

	private HouseAroon aroon;

	public AroonTrend(Observer...observers) {
		this.aroon = new HouseAroon(getDaysSpan());
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMAROONTREND);
		res.setCurrentDate(qU.getDate());
		
		int aroonIndex = getIndicatorIndexFromQuotationIndex(this.aroon, quotationIdx);
		
		{
			Boolean wasAroonUpCrossingAboveAroonDown = 
					(aroon.getOutAroonUp()[aroonIndex - getDaysSpan()] < aroon.getOutAroonDown()[aroonIndex - getDaysSpan()]) &&
					(aroon.getOutAroonUp()[aroonIndex] > aroon.getOutAroonDown()[aroonIndex]);
	
			Boolean wasAroonUpCrossingAbove50 = aroon.getOutAroonUp()[aroonIndex - getDaysSpan()] < middleThreshold && middleThreshold < aroon.getOutAroonUp()[aroonIndex] ;
			Boolean isArronUpTo100 		= upperThreshold < aroon.getOutAroonUp()[aroonIndex];
			Boolean isAroonDownBelow30 	= lowerThreshold > aroon.getOutAroonDown()[aroonIndex];
			
			res.setBullishCrossOver(wasAroonUpCrossingAboveAroonDown && wasAroonUpCrossingAbove50 && isArronUpTo100 && isAroonDownBelow30); 
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean wasAroonDownCrossingAboveAroonUp = 
					(aroon.getOutAroonDown()[aroonIndex - getDaysSpan()] < aroon.getOutAroonUp()[aroonIndex - getDaysSpan()]) &&
					(aroon.getOutAroonDown()[aroonIndex] > aroon.getOutAroonUp()[aroonIndex]);
	
			Boolean wasAroonDownCrossingAbove50 = aroon.getOutAroonDown()[aroonIndex - getDaysSpan()] < middleThreshold && middleThreshold < aroon.getOutAroonDown()[aroonIndex] ;
			Boolean isArronDownTo100 = upperThreshold < aroon.getOutAroonDown()[aroonIndex];
			Boolean isAroonUpBelow30 = lowerThreshold > aroon.getOutAroonUp()[aroonIndex];
			
			res.setBearishCrossBellow(wasAroonDownCrossingAboveAroonUp && wasAroonDownCrossingAbove50 && isArronDownTo100 && isAroonUpBelow30);
			return res;
			
		}
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
		if (indicator instanceof SMA) return this.isInDataRange((SMA) indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
		
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return getDaysSpan() <= index && index < sma.getSma().length;
	}
	
	private Boolean isInDataRange(HouseAroon aroon, Integer index) {
		return (getDaysSpan() < index) && (index < aroon.getOutAroonUp().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Aroon DATE, Aroon Up, Aroon Down, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}
	
	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
		
		int aroonIndex = getIndicatorIndexFromQuotationIndex(this.aroon, calculatorIndex);
//		int aroonQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, aroonQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
//			+ this.aroon.getIndicatorQuotationData().get(aroonQuotationIndex).getDate() + "," 
			+ this.aroon.getOutAroonUp()[aroonIndex] + "," + this.aroon.getOutAroonDown()[aroonIndex];
		
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
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		
		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromQuotationIndex(this.aroon, idx);
		return new double[]
				{
					this.aroon.getOutAroonDown()[indicatorIndexFromCalculatorQuotationIndex],
					this.aroon.getOutAroonUp()[indicatorIndexFromCalculatorQuotationIndex],
					middleThreshold, lowerThreshold, upperThreshold
				};
	}
	

	@Override
	protected int getDaysSpan() {
		return 25;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMAROONTREND;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.aroon.calculateIndicator(quotations);
		
	}

	@Override
	public Integer getStartShift() {
		return aroon.getStartShift() + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return aroon.quotationValidity();
	}

	@Override
	public Integer getOutputBeginIdx() {
		return aroon.getOutBegIdx().value + getDaysSpan();
	}

}
