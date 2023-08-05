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
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

@Deprecated //Rewrite to match corresponding edited EventInfoOpsCompo 
public class RSIThreshold extends TalibIndicatorsOperator {

	RSI rsi;
	private double lowerThreshold;
	private double upperThreshold;

	public RSIThreshold(Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold, Observer[] observers) {
		super(EventDefinition.PMRSITHRESHOLD, observers);
		init(rsiTimePeriod, rsiLowerThreshold, rsiUpperThreshold);
	}

	public RSIThreshold(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold) {
		this.rsi = new RSI(rsiTimePeriod);
		this.lowerThreshold = rsiLowerThreshold;
		this.upperThreshold = rsiUpperThreshold;
	}

	@Override
	public void genericInit(Integer... constants) {
		init(constants[0], constants[1], constants[2]);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());

		Integer rsiIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.rsi, quotationIdx);

		{
			//BULL : RSI is below low threshold (over sold)
			boolean isRSICrossingBelow = getLowerThreshold() > this.rsi.getRsi()[rsiIndicatorIndex]; //30 > last rsi 
			res.setBullishCrossOver(isRSICrossingBelow);
			if (res.getBullishCrossOver()) return res;
		}
		{
			//BEAR : RSI is above upper threshold (over bought)
			boolean isRSICrossingAbove = getUpperThreshold() < this.rsi.getRsi()[rsiIndicatorIndex]; //70 < last rsi
			res.setBearishCrossBelow(isRSICrossingAbove);
		}

		return res;
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
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, RSI DATE, RSI QUOTE, LOW TH, UP TH, RSI,bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMRSITHRESHOLD,EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,EventDefinition.PMRSITHRESHOLD,EventType.BULLISH));
		BigDecimal calculatorClose = qU.getCloseSplit();
		String line =
				new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","
						+ getLowerThreshold() + ","
						+ getUpperThreshold() + ","
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
						getLowerThreshold(),
						getUpperThreshold()
				};
	}


	@Override
	protected int getDaysSpan() {
		return 1;
	}


	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMRSITHRESHOLD;
	}

	@Override
	public Integer getStartShift() {
		return rsi.getStartShift() + getDaysSpan();
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.rsi.calculateIndicator(quotations);

	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.CLOSE;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return rsi.getOutBegIdx().value + getDaysSpan();
	}


	private double getUpperThreshold() {
		return upperThreshold;
	}

	private double getLowerThreshold() {
		return lowerThreshold;
	}

}
