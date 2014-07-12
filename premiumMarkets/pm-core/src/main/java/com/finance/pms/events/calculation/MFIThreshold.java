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
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIThreshold extends TalibIndicatorsCompositionCalculator {
	
	private MFI mfi;

	public MFIThreshold(Integer mfTimePeriod, Integer mfiLowerThreshold, Integer mfiUpperThreshold, Observer[] observers) {
		super(observers);
		this.mfi = new MFI(mfTimePeriod, mfiLowerThreshold, mfiUpperThreshold);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {
		FormulatRes res = new FormulatRes(EventDefinition.PMMFITHRESHOLD);
		res.setCurrentDate(qU.getDate());
		
		Integer mfiIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.mfi, quotationIdx);
		
		{
			//BULL : MFI cross below low threshold (over sold)
			boolean isMFICrossingBelow = this.mfi.getMfi()[mfiIndicatorIndex-1] > mfi.getLowerThreshold() && mfi.getLowerThreshold() > this.mfi.getMfi()[mfiIndicatorIndex]; // 1rst mfi > 20 > last mfi
			res.setBullishCrossOver(isMFICrossingBelow);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : MFI cross above upper threshold (over bought)
			boolean isMFICrossingAbove = this.mfi.getMfi()[mfiIndicatorIndex-1]  < mfi.getUpperThreshold()  && mfi.getUpperThreshold() < this.mfi.getMfi()[mfiIndicatorIndex];// 1rst mfi < 80 < last mfi
			res.setBearishCrossBellow(isMFICrossingAbove);
		}
		
		return res;
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		return (getDaysSpan() <= index && index < mfi.getMfi().length);
	}

	@Override
	protected  String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects)  {
		Date calculatorDate = qU.getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFITHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFITHRESHOLD, EventType.BULLISH));
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
		} else if (bullishEventValue != null) {
			line = line + ",0,"+calculatorClose+",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}
	
	@Override
	protected  double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
		
		return new double[]
				{
					this.mfi.getMfi()[getIndicatorIndexFromQuotationIndex(this.mfi, idx)],
					this.mfi.getLowerThreshold(),
					this.mfi.getUpperThreshold()
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MFI DATE, MFI QUOTE, LOW TH, UP TH, MFI, bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, LOW TH, UP TH, MFI, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected int getDaysSpan() {
		return 1;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMFITHRESHOLD;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.mfi.calculateIndicator(quotations);
		
	}

	@Override
	public Integer getStartShift() {
		return this.mfi.getStartShift() + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return mfi.quotationValidity();
	}

	@Override
	public Integer getOutputBeginIdx() {
		return this.mfi.getOutBegIdx().value + getDaysSpan();
	}
}
