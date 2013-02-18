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
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIThreshold extends TalibIndicatorsCompositionCalculator {
	
	private MFI mfi;
	private Integer mfiQuotationStartDateIdx;
	
	public MFIThreshold(Stock stock, MFI mfi, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		this.mfi = mfi;
		
		mfiQuotationStartDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer macdQuotationEndDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(mfiQuotationStartDateIdx, endDate);
		isValidData(stock, mfi, startDate, mfiQuotationStartDateIdx, macdQuotationEndDateIdx);
		
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		FormulatRes res = new FormulatRes(EventDefinition.PMMFITHRESHOLD);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Integer mfiIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx);
		
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFITHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFITHRESHOLD, EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,mfiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
			+ this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," +this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," 
			+ this.mfi.getLowerThreshold() + ","
			+ this.mfi.getUpperThreshold() + ","
			+ this.mfi.getMfi()[getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx)];
		
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
	protected double[] buildOneOutput(int calculatorIndex) {
		
		return new double[]
				{
					this.mfi.getMfi()[getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx)],
					this.mfi.getLowerThreshold(),
					this.mfi.getUpperThreshold()
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, MFI DATE, MFI QUOTE, LOW TH, UP TH, MFI, bearish, bullish";
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
}
