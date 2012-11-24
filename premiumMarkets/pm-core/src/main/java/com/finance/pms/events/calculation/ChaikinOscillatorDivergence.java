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
import java.util.Arrays;
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
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class ChaikinOscillatorDivergence extends TalibIndicatorsCompositionCalculator {

	private ChaikinOscillator chaikinOscillator;
	private Integer chaikinQuotationStartDateIdx;
	
	private SMA sma;
	private Integer smaQuotationStartDateIdx;

	public ChaikinOscillatorDivergence(Stock stock, ChaikinOscillator chaikinOscillator, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.chaikinOscillator = chaikinOscillator;
		chaikinQuotationStartDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinQuotationEndDateIdx = chaikinOscillator.getIndicatorQuotationData().getClosestIndexForDate(chaikinQuotationStartDateIdx, endDate);
		isValidData(stock, chaikinOscillator, startDate, chaikinQuotationStartDateIdx, chaikinQuotationEndDateIdx);
		
		try {
			this.sma = new SMA(stock, 2, startDate, endDate, calculationCurrency, Math.max(20, getDaysSpan()), 0);
		} catch (TalibException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes(EventDefinition.PMCHAIKINOSCDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int chaikinIdx = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinOscillator, calculatorIndex, chaikinQuotationStartDateIdx);
		double[] chaikinLookBackP = Arrays.copyOfRange(this.chaikinOscillator.getChaikinOsc(), chaikinIdx - getDaysSpan(), chaikinIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(this.getCalculatorQuotationData().getCloseValues(), calculatorIndex - getDaysSpan(), calculatorIndex);
		
		int smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		double[] chaikinThreshCurve = new double[chaikinLookBackP.length];
		
		{
//			Boolean isPriceDown = dataSlope(quotationLookBackP) < 0;
//			Boolean isChaikinUp = dataSlope(chaikinLookBackP) > 0 && this.chaikinOscillator.getChaikinOsc()[chaikinIdx - getDaysSpan()] < 0;
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isChaikinUp = higherLow(chaikinLookBackP, chaikinThreshCurve);
			res.setBullishCrossOver(isPriceDown && isChaikinUp); 
			
			if (res.getBullishCrossOver()) return res;

		}
		{
//			Boolean isPriceUp = dataSlope(quotationLookBackP) > 0;
//			Boolean isChaikinDown = dataSlope(chaikinLookBackP) < 0 && this.chaikinOscillator.getChaikinOsc()[chaikinIdx - getDaysSpan()] > 0;
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isChaikinDown = lowerHigh(chaikinLookBackP,  chaikinThreshCurve);
			res.setBearishCrossBellow(isPriceUp && isChaikinDown);
		
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCDIVERGENCE, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMCHAIKINOSCDIVERGENCE, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int chaikinIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinOscillator, calculatorIndex, chaikinQuotationStartDateIdx);
		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
			+ this.chaikinOscillator.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + ","
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
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMCHAIKINOSCDIVERGENCE;
	}

}
