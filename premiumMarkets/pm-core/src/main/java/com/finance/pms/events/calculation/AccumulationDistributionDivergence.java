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
import com.finance.pms.talib.indicators.ChaikinLine;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class AccumulationDistributionDivergence extends TalibIndicatorsCompositionCalculator {

	ChaikinLine chaikin;
	
	SMA priceSma65;
	SMA priceSma20;
	SMA chaikinSma65;
	SMA chaikinSma20;
	
	private Integer chaikinQuotationStartDateIdx;
	private Integer chaikinSma20QuotationStartDateIdx;
	private Integer chaikinSma65QuotationStartDateIdx;
	private Integer sma20QuotationStartDateIdx;
	private Integer sma65QuotationStartDateIdx;
	
	public AccumulationDistributionDivergence(Stock stock, ChaikinLine chaikinLine, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.chaikin = chaikinLine;
		chaikinQuotationStartDateIdx = chaikinLine.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinQuotationEndDateIdx = chaikinLine.getIndicatorQuotationData().getClosestIndexForDate(chaikinQuotationStartDateIdx, endDate);
		isValidData(stock, chaikinLine, startDate, chaikinQuotationStartDateIdx, chaikinQuotationEndDateIdx);
		
		try {
			this.chaikinSma20 = new SMA(chaikin, 20);
			this.chaikinSma65 = new SMA(chaikin, 65);
			this.priceSma20 = new SMA(stock, 20, startDate, endDate, calculationCurrency, 20, 0);
			this.priceSma65 = new SMA(stock, 65, startDate, endDate, calculationCurrency, 65, 0);
		} catch (TalibException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		
		chaikinSma20QuotationStartDateIdx = chaikinSma20.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinSma20QuotationEndDateIdx = chaikinSma20.getIndicatorQuotationData().getClosestIndexForDate(chaikinSma20QuotationStartDateIdx, endDate);
		isValidData(stock, chaikinSma20, startDate, chaikinSma20QuotationStartDateIdx, chaikinSma20QuotationEndDateIdx);
		
		chaikinSma65QuotationStartDateIdx = chaikinSma65.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer chaikinSma65QuotationEndDateIdx = chaikinSma65.getIndicatorQuotationData().getClosestIndexForDate(chaikinSma65QuotationStartDateIdx, endDate);
		isValidData(stock, chaikinSma65, startDate, chaikinSma65QuotationStartDateIdx, chaikinSma65QuotationEndDateIdx);
		
		sma20QuotationStartDateIdx = priceSma20.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer sma20QuotationEndDateIdx = priceSma20.getIndicatorQuotationData().getClosestIndexForDate(sma20QuotationStartDateIdx, endDate);
		isValidData(stock, priceSma20, startDate, sma20QuotationStartDateIdx, sma20QuotationEndDateIdx);
		
		sma65QuotationStartDateIdx = priceSma65.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer sma65QuotationEndDateIdx = priceSma65.getIndicatorQuotationData().getClosestIndexForDate(sma65QuotationStartDateIdx, endDate);
		isValidData(stock, priceSma65, startDate, sma65QuotationStartDateIdx, sma65QuotationEndDateIdx);
	}


	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMACCDISTDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		double close = this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue();
		
		int chainkinIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikin, calculatorIndex, chaikinQuotationStartDateIdx);
		
		int chainkinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		int chainkinSma20Index = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinSma20, chainkinQuotationIndex, chaikinSma20QuotationStartDateIdx);
		int chainkinSma65Index = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikinSma65, chainkinQuotationIndex, chaikinSma65QuotationStartDateIdx);
		
		int sma20Index = getIndicatorIndexFromCalculatorQuotationIndex(this.priceSma20, calculatorIndex, sma20QuotationStartDateIdx);
		int sma65Index = getIndicatorIndexFromCalculatorQuotationIndex(this.priceSma20, calculatorIndex, sma65QuotationStartDateIdx);
		
		Boolean isPriceBelowSma65 = close < priceSma65.getSma()[sma65Index];
		Boolean isPriceBelowSma20 = close < priceSma20.getSma()[sma20Index];
		Boolean isChaikinBelowSma65 = chaikin.getChaikinLine()[chainkinIndex] < chaikinSma65.getSma()[chainkinSma65Index];
		Boolean isChaikinBelowSma20 = chaikin.getChaikinLine()[chainkinIndex] < chaikinSma20.getSma()[chainkinSma20Index];
		
		{
			res.setBullishCrossOver(isPriceBelowSma65 && isPriceBelowSma20 && !isChaikinBelowSma65 && !isChaikinBelowSma20); 
			if (res.getBullishCrossOver()) return res;

		}
		{
			res.setBearishCrossBellow(!isPriceBelowSma65 && !isPriceBelowSma20 && isChaikinBelowSma65 && isChaikinBelowSma20);
			return res;
			
		}
		
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof ChaikinLine) return this.isInDataRange((ChaikinLine)indicator, indicatorIndex);
		if (indicator instanceof SMA) return this.isInDataRange((SMA) indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
		
	}
	
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return getDaysSpan() <= index && index < sma.getSma().length;
	}
	
	private Boolean isInDataRange(ChaikinLine chaikin, Integer index) {
		return (getDaysSpan() < index) && (index < chaikin.getChaikinLine().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//return "CALCULATOR DATE; CALCULATOR QUOTE; Chainkin DATE; Chainkin; bearish; bullish\n";	
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin DATE, Chainkin, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMACCDISTDIVERGENCE, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMACCDISTDIVERGENCE, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		
		int chaikinIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.chaikin, calculatorIndex, chaikinQuotationStartDateIdx);
		int chaikinQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, chaikinQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
			+ this.chaikin.getIndicatorQuotationData().get(chaikinQuotationIndex).getDate() + ","
			+ this.chaikin.getChaikinLine()[chaikinIndex];
		
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
		
		return new double[]
				{
				this.chaikin.getChaikinLine()[getIndicatorIndexFromCalculatorQuotationIndex(this.chaikin, calculatorIndex, chaikinQuotationStartDateIdx)]
				};
	}

	@Override
	protected int getDaysSpan() {
		return 1;
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMACCDISTDIVERGENCE;
	}

}
