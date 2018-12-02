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
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

@Deprecated
public class SmaReversal extends TalibIndicatorsOperator {

	private static final int DAYS_SPAN = 15;

	private SMA sma;

	private EventType previousTrend;

	public SmaReversal(Integer smaPeriod, Observer[] observers) {
		super(EventDefinition.PMSMAREVERSAL, observers);
		init(smaPeriod);
	}

	public SmaReversal(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(Integer smaPeriod) {
		this.sma = new SMA(smaPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
		init(constants[0]);
	}


	@Override
	protected int getDaysSpan() {
		return DAYS_SPAN;
	}


	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException{

		FormulatRes res = new FormulatRes(getEventDefinition());

		Integer smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);

		//BULL : Quote above SMA and SMA up over n days after a BEAR trend
		{	
			boolean isAboveSMA = this.sma.getSma()[smaIndex] < qU.getCloseSp().doubleValue(); //  sma <  close
			boolean isSMAUp = this.sma.getSma()[smaIndex - getDaysSpan()] < this.sma.getSma()[smaIndex];
			boolean isPreviouslyBearish = EventType.BEARISH.equals(previousTrend);
			res.setBullishCrossOver(isAboveSMA && isSMAUp && isPreviouslyBearish);
			if (isSMAUp && isAboveSMA) {
				previousTrend = EventType.BULLISH;
			} 
		}

		//BEAR : Quote below SMA and down over n days after a BULL trend
		{
			boolean isBelowSMA = this.sma.getSma()[smaIndex] > qU.getCloseSp().doubleValue(); //  sma >  close
			boolean isSMADown = this.sma.getSma()[smaIndex - getDaysSpan()] > this.sma.getSma()[smaIndex];
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearsihEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMSMAREVERSAL,EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMSMAREVERSAL,EventType.BULLISH));
		BigDecimal calculatorClose = qU.getCloseSp();
		//		int smaQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,smaQuotationStartDateIdx);
		String line =
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calculatorDate) + "," +calculatorClose + "," 
				//			+ this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getDate() + "," + this.sma.getIndicatorQuotationData().get(smaQuotationIndex).getClose() + "," 
				+ this.sma.getSma()[getIndicatorIndexFromQuotationIndex(this.sma, calculatorIndex)]; 		
		if (bearsihEventValue != null) {
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
						quotationUnit.getCloseSp().doubleValue(),
						this.sma.getSma()[getIndicatorIndexFromQuotationIndex(this.sma, idx)]
				};
	}


	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		//		String head =  "CALCULATOR DATE, CALCULATOR QUOTE,SMA DATE, SMA QUOTE, SMA"+sma.getPeriod()+", bearish, bullish";
		String head =  "CALCULATOR DATE, CALCULATOR QUOTE, SMA"+sma.getPeriod()+", bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSMAREVERSAL;
	}


	@Override
	public Integer getStartShift() {
		return sma.getStartShift() + getDaysSpan();
	}


	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.sma.calculateIndicator(quotations);

	}


	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}


	@Override
	public Integer getOutputBeginIdx() {
		return sma.getOutBegIdx().value + getDaysSpan();
	}

}
