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
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIDivergence_old extends TalibIndicatorsOperator {
	
	MFI mfi;
	SMA sma;

	private Quotations quotationCopy;
	
	public MFIDivergence_old(Integer mfiTimePeriod, Integer mfiLowerThres, Integer mfiUpperThres, Observer... observers) {
		super(EventDefinition.PMMFIDIVERGENCEOLD, observers);
		init(mfiTimePeriod, mfiLowerThres, mfiUpperThres, 12);
	}
	
	public MFIDivergence_old(EventInfo reference) {
	    //Reflective ops generator
		super(reference);
	}

	protected void init(Integer mfiTimePeriod, Integer mfiLowerThres, Integer mfiUpperThres, Integer signalSmaPeriod) {
	    this.mfi = new MFI(mfiTimePeriod, mfiLowerThres, mfiUpperThres);
	    this.sma = new SMA(signalSmaPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
	    init(constants[0], constants[1], constants[2], constants[3]);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());
		
		int mfiIdx = getIndicatorIndexFromQuotationIndex(this.mfi, quotationIdx);
		double[] mfiLookBackP = Arrays.copyOfRange(this.mfi.getMfi(), mfiIdx - getDaysSpan(), mfiIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(quotationCopy.getCloseValues(), quotationIdx - getDaysSpan(), quotationIdx);
		
		int smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);
		
		double[] lowThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < lowThreshLookBackP.length; i++) {
			lowThreshLookBackP[i] = 50; //this.mfi.getLowerThreshold();
			
		}
		
		double[] upperThreshLookBackP = new double[getDaysSpan()];
		for (int i = 0; i < upperThreshLookBackP.length; i++) {
			upperThreshLookBackP[i] = 50; //this.mfi.getUpperThreshold();
			
		}
		
		{
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			Boolean isMfiUp = higherLow(mfiLookBackP, lowThreshLookBackP) && mfi.getMfi()[mfiIdx - getDaysSpan()] < mfi.getLowerThreshold();
			res.setBullishCrossOver(isPriceDown && isMfiUp); 
			
			if (res.getBullishCrossOver()) return res;
		}
		{
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isMfiDown = lowerHigh(mfiLookBackP, upperThreshLookBackP) && mfi.getMfi()[mfiIdx - getDaysSpan()] > mfi.getUpperThreshold();
			res.setBearishCrossBellow(isPriceUp && isMfiDown);
		
			return res;
		}
	}
	
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, index);
		if (indicator instanceof MFI) return this.isInDataRange((MFI)indicator, index);
		throw new RuntimeException("Booo",new Throwable());
	}
	
	private boolean isInDataRange(SMA sma, Integer index) {
		return 0 <= index && index < sma.getSma().length;
	}
	
	public Boolean isInDataRange(MFI rsi, Integer index) {
		return getDaysSpan() <= index && index < rsi.getMfi().length;
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects){
		Date calculatorDate = qU.getDate();
		EventValue bearsihEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
//		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,mfiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
//			+ this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," +this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," 
			+ this.mfi.getLowerThreshold() + ","
			+ this.mfi.getUpperThreshold() + ","
			+ this.mfi.getMfi()[getIndicatorIndexFromQuotationIndex(this.mfi, calculatorIndex)];
		
		if (bearsihEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null){
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearExpects)+"\n";
		
		return line;
	}
	
	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx)  {
		
		return new double[]
				{
					this.mfi.getMfi()[getIndicatorIndexFromQuotationIndex(this.mfi, idx)],
					//this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)],
					this.mfi.getLowerThreshold(),
					this.mfi.getUpperThreshold()
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MFI DATE, MFI QUOTE, LOW TH, UP TH, MFI ,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
		
	}

	@Override
	protected int getDaysSpan() {
		return 40;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMFIDIVERGENCEOLD;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.quotationCopy = quotations;
		
	}

	@Override
	public Integer getStartShift() {
		return Math.max(mfi.getStartShift(), sma.getStartShift()) + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.OHLCV;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return Math.max(mfi.getOutBegIdx().value, sma.getOutBegIdx().value) + getDaysSpan();
	}
}
