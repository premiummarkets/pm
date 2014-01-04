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
import java.util.SortedMap;

import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;
import com.finance.pms.threads.ConfigThreadLocal;

public class SmaReversal extends TalibIndicatorsCompositionCalculator {
	
	private static final int DAYS_SPAN = 15;

	private SMA sma;
	
	private EventType previousTrend;
	private Integer smaQuotationStartDateIdx;

	public SmaReversal(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		try {
			Integer smaPeriod = ((IndicatorsConfig) ConfigThreadLocal.get("indicatorParams")).getSmaReversalSmaPeriod();
			this.sma = new SMA(stock, smaPeriod, startDate, endDate, calculationCurrency, getDaysSpan(), 0);
		} catch (TalibException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(stock, e.getMessage(),e);
		}
		
		smaQuotationStartDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer smaQuotationEndDateIdx = sma.getIndicatorQuotationData().getClosestIndexForDate(smaQuotationStartDateIdx, endDate);
		isValidData(stock, sma, startDate, smaQuotationStartDateIdx, smaQuotationEndDateIdx);
	
	}


	/**
	 * @return
	 */
	@Override
	protected int getDaysSpan() {
		return DAYS_SPAN;
	}

	
	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes(EventDefinition.PMSMAREVERSAL);
		
		Integer smaIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx);
		
		//BULL : Quote above SMA and SMA up over n days after a BEAR trend  
		{	
			boolean isAboveSMA = this.sma.getSma()[smaIndex] < this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue(); //  sma <  close
			boolean isSMAUp = this.sma.getSma()[smaIndex - getDaysSpan()] <  this.sma.getSma()[smaIndex];
			boolean isPreviouslyBearish = EventType.BEARISH.equals(previousTrend);
			res.setBullishCrossOver(isAboveSMA && isSMAUp && isPreviouslyBearish);	
			if (isSMAUp && isAboveSMA) {
				previousTrend = EventType.BULLISH;
			} 
		}
		
		//BEAR : Quote below SMA and down over n days after a BULL trend
		{
			boolean isBelowSMA = this.sma.getSma()[smaIndex] > this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue(); //  sma >  close
			boolean isSMADown = this.sma.getSma()[smaIndex - getDaysSpan()] >  this.sma.getSma()[smaIndex];
			boolean isPreviouslyBullish = EventType.BULLISH.equals(previousTrend);
			res.setBearishCrossBellow(isBelowSMA && isSMADown && isPreviouslyBullish);
			if (isSMADown && isBelowSMA) {
				previousTrend = EventType.BEARISH;
			} 
		}
			
		return res;
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer index) {
		return getDaysSpan() <= index && index < (this.sma.getSma().length);
	}
	
	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearsihEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSMAREVERSAL,EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSMAREVERSAL,EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calculatorDate) + "," +calculatorClose + "," 
			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," + this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
			+ this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)]; 		
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
				this.getCalculatorQuotationData().get(calculatorIndex).getClose().doubleValue(),
				this.sma.getSma()[getIndicatorIndexFromCalculatorQuotationIndex(this.sma, calculatorIndex, smaQuotationStartDateIdx)]
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head =  "CALCULATOR DATE, CALCULATOR QUOTE,SMA DATE, SMA QUOTE, SMA"+sma.getPeriod()+", bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSMAREVERSAL;
	}
	
	
	

}
