
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
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;


public class StochasticThreshold extends TalibIndicatorsOperator {
	
	private StochasticOscillator stochasticOscillator;

	public StochasticThreshold(Integer fastKLookBackPeriod, Integer slowKSmaPeriod, Integer slowDSmaPeriod, Observer... observers) {
		super(EventDefinition.PMSSTOCHTHRESHOLD, observers);
		init(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod);
	}

	public StochasticThreshold(EventInfo reference) {
	    //Reflective ops generator
		super(reference);
	}

	protected void init(Integer fastKLookBackPeriod, Integer slowKSmaPeriod, Integer slowDSmaPeriod) {
	    this.stochasticOscillator = new StochasticOscillator(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
	    init(constants[0], constants[1], constants[2]);
	}

	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException  {
		
		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());
		
		Integer stochIndicatorIndex = getIndicatorIndexFromQuotationIndex(this.stochasticOscillator, quotationIdx);

		
		{
			//BULL : Stoch cross below low threshold (over sold) with an up trend (above sma)
			boolean isCrossingBelow = 
					this.stochasticOscillator.getSlowD()[stochIndicatorIndex-1] > stochasticOscillator.getLowerThreshold() && 
					stochasticOscillator.getLowerThreshold() > this.stochasticOscillator.getSlowD()[stochIndicatorIndex]; // crosses below  lower threshold
			res.setBullishCrossOver(isCrossingBelow);
			if (res.getBullishCrossOver()) return res;
		} 
		{
			//BEAR : RSI cross above upper threshold (over bought) with a down trend (under sma)
			boolean isCrossingAbove = 
					this.stochasticOscillator.getSlowD()[stochIndicatorIndex-1]  < stochasticOscillator.getUpperThreshold()  &&
					stochasticOscillator.getUpperThreshold() < this.stochasticOscillator.getSlowD()[stochIndicatorIndex];// crosses above upper threshold
			res.setBearishCrossBellow(isCrossingAbove);
			
			return res;
		}
		
	}
	
	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof StochasticOscillator) return this.isInDataRange((StochasticOscillator)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}

	private Boolean isInDataRange(StochasticOscillator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < this.stochasticOscillator.getSlowK().length);
		
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
//		String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, Upper Th, lower Th, STOCH SLOW K, STOCH SLOW D , bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Upper Th, lower Th, STOCH SLOW K, STOCH SLOW D , bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, QuotationUnit qU, List<SortedMap<Date, double[]>> linearsExpects){
		
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHTHRESHOLD, EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,EventDefinition.PMSSTOCHTHRESHOLD, EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
		
		int stochIndex = getIndicatorIndexFromQuotationIndex(this.stochasticOscillator, calculatorIndex);
//		int stochQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, stochQuotationStartDateIdx);
		
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + ","  
//			+ this.stochasticOscillator.getIndicatorQuotationData().get(stochQuotationIndex).getDate() + ","
			+ this.stochasticOscillator.getLowerThreshold() + ","
			+ this.stochasticOscillator.getUpperThreshold() + ","
			+ this.stochasticOscillator.getSlowK()[stochIndex] + ","
			+ this.stochasticOscillator.getSlowD()[stochIndex];
		
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
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {
			
		int stochIndex = getIndicatorIndexFromQuotationIndex(this.stochasticOscillator, idx);
		return new double[]
				{
					this.stochasticOscillator.getSlowK()[stochIndex],
					this.stochasticOscillator.getSlowD()[stochIndex],
					this.stochasticOscillator.getLowerThreshold(),
					this.stochasticOscillator.getUpperThreshold()
				};
	}

	@Override
	protected int getDaysSpan() {
		return 1;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSSTOCHTHRESHOLD;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		stochasticOscillator.calculateIndicator(quotations);
		
	}

	@Override
	public Integer getStartShift() {
		return stochasticOscillator.getStartShift() + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return stochasticOscillator.quotationValidity();
	}

	@Override
	public Integer getOutputBeginIdx() {
		return stochasticOscillator.getOutBegIdx().value + getDaysSpan();
	}

}
