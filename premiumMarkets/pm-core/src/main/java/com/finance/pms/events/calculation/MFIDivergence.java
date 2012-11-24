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
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIDivergence extends TalibIndicatorsCompositionCalculator {
	
	MFI mfi;
	private Integer mfiQuotationStartDateIdx;
	
	SMA sma;
	private Integer smaQuotationStartDateIdx;
	
	public MFIDivergence(Stock stock, MFI mfi, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.mfi = mfi;
		mfiQuotationStartDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer macdQuotationEndDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(mfiQuotationStartDateIdx, endDate);
		isValidData(stock, mfi, startDate, mfiQuotationStartDateIdx, macdQuotationEndDateIdx);
		
		try {
			this.sma = new SMA(stock,12, startDate, endDate, calculationCurrency, Math.max(20, getDaysSpan()), 0);
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
		
		FormulatRes res = new FormulatRes(EventDefinition.PMMFIDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		int mfiIdx = getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx);
		double[] mfiLookBackP = Arrays.copyOfRange(this.mfi.getMfi(), mfiIdx - getDaysSpan(), mfiIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(this.getCalculatorQuotationData().getCloseValues(), calculatorIndex - getDaysSpan(), calculatorIndex);
		
		int smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFIDIVERGENCE,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFIDIVERGENCE,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
//		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,mfiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
//			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," +this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
//			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)] +","
			+ this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," +this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," 
			+ this.mfi.getLowerThreshold() + ","
			+ this.mfi.getUpperThreshold() + ","
			+ this.mfi.getMfi()[getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx)];
		
		if (bearsihEventValue != null) {
			line = line + ","+calculatorClose+",0,";
		} else if (bullishEventValue != null){
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//return "CALCULATOR DATE, CALCULATOR QUOTE,SMA DATE, SMA QUOTE, SMA50, MFI DATE, MFI QUOTE, LOW TH, UP TH, MFI ,bearish, bullish\n";
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
		return EventDefinition.PMMFIDIVERGENCE;
	}
}
