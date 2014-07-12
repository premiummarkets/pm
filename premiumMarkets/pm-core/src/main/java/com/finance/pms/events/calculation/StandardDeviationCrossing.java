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
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StandardDeviation;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class StandardDeviationCrossing extends TalibIndicatorsCompositionCalculator {
	
	private StandardDeviation standardDeviation;
	private SMA sma;

	public StandardDeviationCrossing(Observer[] observers) {
		super(observers);
	}

	@Override
	protected  FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException{
		FormulatRes res = new FormulatRes(EventDefinition.STDDEV);
		Date date = qU.getDate();
		res.setCurrentDate(date);
		
		Integer smaIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		Integer stddevIndQuoteIndex = getIndicatorIndexFromQuotationIndex(this.standardDeviation, quotationIdx);
		
		double periodMean = 0;
		for (int i = quotationIdx - standardDeviation.getPeriod(); i < quotationIdx ; i++) {
			periodMean = periodMean + qU.getClose().doubleValue();
		}
		periodMean = periodMean / standardDeviation.getPeriod();
		
		double currentQuote = qU.getClose().doubleValue();
		double currentDev = currentQuote - periodMean;
		
		//EventType resType = EventType.NONE;
		if (Math.abs(currentDev) > this.standardDeviation.getStdDev()[stddevIndQuoteIndex]) {
			boolean isPriceAboveSMA = qU.getClose().doubleValue() > sma.getSma()[smaIndicatorIndex];
			if (currentDev < 0 && !isPriceAboveSMA) {
				res.setBearishCrossBellow(true);
				//resType = EventType.BEARISH;
			}
			if (currentDev > 0 && isPriceAboveSMA) {
				res.setBullishCrossOver(true);
				//resType = EventType.BULLISH;
			}
		}

		return res;
	}

	@Override
	public Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, indicatorIndex);
		if (indicator instanceof StandardDeviation) return this.isInDataRange((StandardDeviation)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return (0 < index && index < sma.getSma().length);
	}

	private Boolean isInDataRange(StandardDeviation stddev, Integer index) {
		return (getDaysSpan() < index && index < stddev.getStdDev().length);
	}

	@Override
	protected int getDaysSpan() {
		//return this.standardDeviation.getPeriod() -1;
		return 10;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		return "CALCULATOR DATE; CALCULATOR QUOTE; STDDEV DATE; STDDEV QUOTE; STDEV; SMA DATE; SMA QUOTE; SMA; bearish; bullish\n";
		return "CALCULATOR DATE, CALCULATOR QUOTE, STDEV, SMA, bearish, bullish\n";
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.STDDEV,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.STDDEV,EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
//		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
//		int stddevQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,stddevQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + ","+calculatorClose + ","
//			+ this.standardDeviation.getIndicatorQuotationData().get(stddevQuotationIndex).getDate() + ","+this.standardDeviation.getIndicatorQuotationData().get(stddevQuotationIndex).getClose() + ","
			+ this.standardDeviation.getStdDev()[getIndicatorIndexFromQuotationIndex(this.standardDeviation, calculatorIndex)]  + ","
//			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + ","+this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + ","
			+ this.sma.getSma()[getIndicatorIndexFromQuotationIndex(this.sma, calculatorIndex)];
		
		if (bearsihEventValue != null) {
			line = line + ";"+calculatorClose+";0;\n";
		} else if (bullishEventValue != null) {
			line = line + ";0;"+calculatorClose+";\n";
		} else {
			line = line + ";0;0;\n";
		}
		return line;
	}
	
	@Override
	protected  double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
			
		return new double[]
				{
					this.sma.getSma()[getIndicatorIndexFromQuotationIndex(this.sma, idx)],
					this.standardDeviation.getStdDev()[getIndicatorIndexFromQuotationIndex(this.standardDeviation, idx)],
					
				};
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.STDDEV;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.sma.calculateIndicator(quotations);
		this.standardDeviation.calculateIndicator(quotations);
		
	}

	@Override
	public Integer getStartShift() {
		return Math.max(this.sma.getStartShift(), this.standardDeviation.getStartShift()) + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return Math.max(this.sma.getOutBegIdx().value, this.standardDeviation.getOutBegIdx().value) + getDaysSpan();
	}
}
