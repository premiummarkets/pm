/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
import java.util.Map;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.MFI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;

public class MFIDivergence extends IndicatorsCompositionCalculator {
	
	SMA sma;
	MFI mfi;
	private Integer mfiQuotationStartDateIdx;
	private Integer smaQuotationStartDateIdx;
	
	public MFIDivergence(Stock stock, MFI mfi, SMA sma, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		this.mfi = mfi;
		this.sma = sma;
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
		
		mfiQuotationStartDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer macdQuotationEndDateIdx = mfi.getIndicatorQuotationData().getClosestIndexForDate(mfiQuotationStartDateIdx, endDate);
		isValidData(stock, mfi, startDate, mfiQuotationStartDateIdx, macdQuotationEndDateIdx);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMMFIDIVERGENCE);
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Integer smaIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);		
		Integer mfiIndicatorIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx);
		
		{
			Boolean isPriceDown = 	this.getCalculatorQuotationData().get(calculatorIndex - getDaysSpan()).getClose().doubleValue() > 
									this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue();
			Boolean isMfiUp = 	mfi.getMfi()[mfiIndicatorIndex - getDaysSpan()] < 
								mfi.getMfi()[mfiIndicatorIndex];
			boolean isPriceAboveSMA = 	this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() > 
										sma.getSma()[smaIndicatorIndex];
			res.setBullishcrossOver(isPriceDown && isMfiUp && isPriceAboveSMA);
			if (res.getBullishcrossOver()) return res;
		} 
		{
			Boolean isPriceUp = this.getCalculatorQuotationData().get(calculatorIndex - getDaysSpan()).getClose().doubleValue() <
								this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue();
			Boolean isMfiDown = mfi.getMfi()[mfiIndicatorIndex - getDaysSpan()] >
								mfi.getMfi()[mfiIndicatorIndex];
			boolean isPriceBelowSMA = 	this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue() < 
										sma.getSma()[smaIndicatorIndex];
			res.setBearishcrossBellow(isPriceUp && isMfiDown && isPriceBelowSMA);

		}

		return res;
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

	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFIDIVERGENCE,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMMFIDIVERGENCE,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,mfiQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," 
			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," +this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)] +","
			+ this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," +this.mfi.getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," 
			+ this.mfi.getLowerThreshold() + ","
			+ this.mfi.getUpperThreshold() + ","
			+ this.mfi.getMfi()[getIndicatorIndexFromCalculatorQuotationIndex(this.mfi, calculatorIndex, mfiQuotationStartDateIdx)];
		
		if (bearsihEventValue != null) {
			line = line + ","+calculatorClose+",0,\n";
		} else if (bullishEventValue != null){
			line = line + ",0,"+calculatorClose+",\n";
		} else {
			line = line + ",0,0,\n";
		}
		return line;
	}


	@Override
	protected String getHeader() {
		return "CALCULATOR DATE, CALCULATOR QUOTE,SMA DATE, SMA QUOTE, SMA50, MFI DATE, MFI QUOTE, LOW TH, UP TH, MFI ,bearish, bullish\n";
		
	}

	@Override
	protected int getDaysSpan() {
		return mfi.getPeriod();
	}
}
