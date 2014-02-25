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
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class ChaikinOscillatorThreshold extends TalibIndicatorsCompositionCalculator {

	private ChaikinOscillator chaikinOscillator;
	
	public ChaikinOscillatorThreshold(Integer chkInfastPeriod, Integer chkInslowPeriod, Observer... observers) {
		super(observers);
		this.chaikinOscillator = new ChaikinOscillator(chkInfastPeriod, chkInslowPeriod);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException{

		FormulatRes res = new FormulatRes(EventDefinition.PMCHAIKINOSCTHRESHOLD);
		res.setCurrentDate(qU.getDate());
		
		Integer stochIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, quotationIdx);

		
		{
			//BULL : Chaikin cross above zero
			boolean isCrossingAbove = this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex-1] < 0 && 0 < this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex];
			res.setBullishCrossOver(isCrossingAbove);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : Chaikin cross below zero
			boolean isCrossingBelow = this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex-1]  > 0 && 0 > this.chaikinOscillator.getChaikinOsc()[stochIndicatorIndex];
			res.setBearishCrossBellow(isCrossingBelow);
			
			return res;
		}
		
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < chaikinOscillator.getChaikinOsc().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin Osc DATE, Chainkin Osc, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCTHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCTHRESHOLD, EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
		
		int chaikinIndex = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, calculatorIndex);
//		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
//			+ this.chaikinOscillator.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + ","
			+ this.chaikinOscillator.getChaikinOsc()[chaikinIndex];
		
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
		
		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, idx);
		return new double[]
				{
				this.chaikinOscillator.getChaikinOsc()[indicatorIndexFromCalculatorQuotationIndex],
				0
				};
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMCHAIKINOSCTHRESHOLD;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.chaikinOscillator.calculateIndicator(quotations);
	}

	@Override
	public Integer getStartShift() {
		return this.chaikinOscillator.getStartShift()+ getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return chaikinOscillator.quotationValidity();
	}

	@Override
	public Integer getOutputBeginIdx() {
		return chaikinOscillator.getOutBegIdx().value + getDaysSpan();
	}
}
