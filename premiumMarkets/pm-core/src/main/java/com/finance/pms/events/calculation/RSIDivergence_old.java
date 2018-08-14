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
import java.util.Arrays;
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
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class RSIDivergence_old extends TalibIndicatorsOperator {
	
	SMA sma;
	RSI rsi;
	
	private Quotations quotationsCopy;
	
//	public RSIDivergence_old(EventInfo eventInfo, Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String analyseName, Boolean persistTrainingEvents, Observer... observers) throws NotEnoughDataException, TalibException, NoQuotationsException {
////		private Integer rsiTimePeriod = 14;
////		private Integer rsiUpperThreshold = 70;
////		private Integer rsiLowerThreshold = 30;
//		this(stock,new RSI(stock, 14, 70, 30, startDate, endDate, calculationCurrency), startDate,endDate,calculationCurrency);
//	}
//	
//	public RSIDivergence_old(Stock stock, RSI rsi, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
//		super(stock, startDate, endDate, calculationCurrency);
//
//		this.rsi = rsi;
//		rsiQuotationStartDateIdx = rsi.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
//		Integer macdQuotationEndDateIdx = rsi.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(rsiQuotationStartDateIdx, endDate);
//		isValidData(stock, rsi, startDate, rsiQuotationStartDateIdx, macdQuotationEndDateIdx);
//		
//		try {
//			this.sma = new SMA(stock, 12, startDate, endDate, calculationCurrency, Math.max(20, getDaysSpan()));
//		} catch (TalibException e) {
//			throw new NotEnoughDataException(stock, e.getMessage(),e);
//		} catch (NoQuotationsException e) {
//			throw new NotEnoughDataException(stock, e.getMessage(),e);
//		}
//		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(0, startDate);
//		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexBeforeOrAtDateOrIndexZero(smaQuotationStartDateIdx, endDate);
//		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
//	}
	
	public RSIDivergence_old(Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold, Observer... observers) {
		super(EventDefinition.PMRSIDIVERGENCEOLD, observers);
		init(rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold, 12);
	}
	
	public RSIDivergence_old(EventInfo reference) {
	    //Reflective ops generator
		super(reference);
	}

	protected void init(Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold, Integer signalSMAPeriod) {
	    this.rsi = new RSI(rsiTimePeriod, rsiUpperThreshold, rsiLowerThreshold);
        this.sma = new SMA(signalSMAPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
	    init(constants[0], constants[1], constants[2], constants[3]);
	}


	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException{
		
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());
		
		int rsiIdx = getIndicatorIndexFromQuotationIndex(this.rsi, quotationIdx);
		double[] rsiLookBackP = Arrays.copyOfRange(this.rsi.getRsi(), rsiIdx - getDaysSpan(), rsiIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(quotationsCopy.getCloseValues(), quotationIdx - getDaysSpan(), quotationIdx);
		
		int smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		double[] lowThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < lowThreshLookBackP.length; i++) {
			lowThreshLookBackP[i] = this.rsi.getLowerThreshold();
			
		}
		
		double[] upperThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < upperThreshLookBackP.length; i++) {
			upperThreshLookBackP[i] = this.rsi.getUpperThreshold();
			
		}
		
		{
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isRsiUp = higherLow(rsiLookBackP, lowThreshLookBackP);
			res.setBullishCrossOver(isPriceDown && isRsiUp); 
			
			if (res.getBullishCrossOver()) return res;

		}
		{
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isRsiDown = lowerHigh(rsiLookBackP, upperThreshLookBackP);
			res.setBearishCrossBellow(isPriceUp && isRsiDown);
		
			return res;
		}
		
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
//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, RSI DATE, RSI QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate, getEventDefinition(),EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate, getEventDefinition(),EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
//		int macdQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,rsiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
//			+ this.rsi.getIndicatorQuotationData().get(macdQuotationIndex).getDate()+ "," +this.rsi.getIndicatorQuotationData().get(macdQuotationIndex).getClose() + ","
			+ this.rsi.getLowerThreshold() + ","
			+ this.rsi.getUpperThreshold() + ","
			+ this.rsi.getRsi()[getIndicatorIndexFromQuotationIndex(this.rsi, calculatorIndex)];
		
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
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
			
		return new double[]
				{
				this.rsi.getRsi()[getIndicatorIndexFromQuotationIndex(this.rsi, idx)],
				this.rsi.getLowerThreshold(),
				this.rsi.getUpperThreshold(),
				};
	}



	@Override
	protected int getDaysSpan() {
		return 40;
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMRSIDIVERGENCEOLD;
	}


	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		
		quotationsCopy = quotations;
		
		this.rsi.calculateIndicator(quotations);
		this.sma.calculateIndicator(quotations);
		
	}



	@Override
	public Integer getStartShift() {
		return Math.max(rsi.getStartShift(), sma.getStartShift()) + getDaysSpan();
	}


	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}


	@Override
	public Integer getOutputBeginIdx() {
		return Math.max(rsi.getOutBegIdx().value, sma.getOutBegIdx().value) + getDaysSpan();
	}
}
