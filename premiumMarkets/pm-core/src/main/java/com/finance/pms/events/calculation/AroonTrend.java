
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


package com.finance.pms.events.calculation;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationsFactories;
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
	private Integer aroonQuotationStartDateIdx;
	
	public AroonTrend(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		try {
			
			int aroonPeriod = getDaysSpan();
			this.aroon =  new HouseAroon(stock, startDate, endDate, calculationCurrency, aroonPeriod);
			
		} catch (TalibException e) {
			Calendar startDateCal = Calendar.getInstance();
			startDateCal.setTime(startDate);
			QuotationsFactories.getFactory().incrementDate(startDateCal, getDaysSpan()*2);
			throw new NotEnoughDataException(startDateCal.getTime(), endDate, e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		aroonQuotationStartDateIdx = aroon.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer aroonQuotationEndDateIdx = aroon.getIndicatorQuotationData().getClosestIndexForDate(aroonQuotationStartDateIdx, endDate);
		isValidData(stock, aroon, startDate, aroonQuotationStartDateIdx, aroonQuotationEndDateIdx);
		
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		FormulatRes res = new FormulatRes(EventDefinition.PMAROONTREND);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int aroonIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.aroon, calculatorIndex, aroonQuotationStartDateIdx);
		
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMAROONTREND, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int aroonIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.aroon, calculatorIndex, aroonQuotationStartDateIdx);
		int aroonQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, aroonQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
			+ this.aroon.getIndicatorQuotationData().get(aroonQuotationIndex).getDate() + "," 
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
	protected double[] buildOneOutput(int calculatorIndex) {
		
		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.aroon, calculatorIndex, aroonQuotationStartDateIdx);
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

}
