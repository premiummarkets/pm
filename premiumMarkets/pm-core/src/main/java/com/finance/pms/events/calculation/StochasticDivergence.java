
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
/**
 * is bullish when close makes a lower low over 35 days spanning 5 days smoothed 0 days and 
 *  StochOsc makes a higher low over 35 days spanning 5 days smoothed 0 days and 
 *  StochOsc is below threshold 20 over 35 days for 0 days;
 * is bearish when close makes a higher high over 35 days spanning 5 days smoothed 0 days and 
 *  StochOsc makes a lower high over 35 days spanning 5 days smoothed 0 days and 
 *  StochOsc is above threshold 80 over 35 days for 0 days;
 *  
 *  with StochOsc : stoch:SlowK(14,1,Sma,3,Sma,high,low,close) ~ stochF:FastK(14,3,Sma,high,low,close)
 *  
 * TODO : https://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:stochastic_oscillator_fast_slow_and_full
 * Middle line cross confirmation
 * Bull Bear Set-ups
 */
import java.util.List;
import java.util.Observer;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibIndicator;

public class StochasticDivergence extends DivergentOperator {

	private StochasticOscillator stochOsc;

	private double upperThreshold;
	private double lowerThreshold;

	public StochasticDivergence(Integer fastKLookBackPeriod, Integer slowKSmaPeriod, Integer slowDSmaPeriod, Integer lowerThreshold, Integer upperThreshold, Observer... observers) {
		super(EventDefinition.PMSSTOCHDIVERGENCE, observers);
		init(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod, lowerThreshold, upperThreshold);
	}

	public StochasticDivergence(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(Integer fastKLookBackPeriod, Integer slowKSmaPeriod, Integer slowDSmaPeriod, Integer lowerThreshold, Integer upperThreshold) {
		this.stochOsc = new StochasticOscillator(fastKLookBackPeriod, slowKSmaPeriod, slowDSmaPeriod);
		this.lowerThreshold = lowerThreshold;
		this.upperThreshold = upperThreshold;
	}

	@Override
	public void genericInit(Integer... constants) {
		init(constants[0], constants[1], constants[2], constants[3], constants[4]);
	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		if (indicator instanceof HouseAroon) return this.isInDataRange((HouseAroon)indicator, indicatorIndex);
		if (indicator instanceof StochasticOscillator) return this.isInDataRange((StochasticOscillator)indicator, indicatorIndex);
		if (indicator instanceof SMA) return this.isInDataRange((SMA)indicator, indicatorIndex);
		throw new RuntimeException("Booo",new Throwable());
	}

	private Boolean isInDataRange(StochasticOscillator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < this.stochOsc.getSlowK().length);

	}

	private boolean isInDataRange(SMA sma, Integer index) {
		return (getDaysSpan() < index && index < sma.getSma().length);
	}

	private boolean isInDataRange(HouseAroon aroon, Integer index) {
		return (0 < index && index < aroon.getOutAroonUp().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, LOW TH, UP TH, STOCH SLOW K, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}

	@Override
	protected  double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx)  {

		int stochIndex = getIndicatorIndexFromQuotationIndex(this.stochOsc, idx);
		return new double[]
				{
						this.stochOsc.getSlowK()[stochIndex],
						this.stochOsc.getSlowD()[stochIndex],
						getLowerThreshold(),
						getUpperThreshold(),
				};
	}

	private double getUpperThreshold() {
		return upperThreshold;
	}

	private double getLowerThreshold() {
		return lowerThreshold;
	}

	@Override
	protected int getDaysSpan() {
		return 35;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSSTOCHDIVERGENCE;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return this.stochOsc.getSlowK();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return this.stochOsc;
	}

	@Override
	protected Double getAlphaBalance() {
		return (double) (getDaysSpan()/7);
	}

	protected Boolean isOcsWithinBearThresholds(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
			if (getOscillatorOutput()[i] >= upperThreshold) {
				return true;
			}
		}
		//if (getOscillatorOutput()[oscIdx - idxSpan] >= stochOsc.getUpperThreshold()) return true;
		return false;
	}


	protected Boolean isOscWithinBullThresholds(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
			if (getOscillatorOutput()[i] <= lowerThreshold) {
				return true;
			}
		}
		//if (getOscillatorOutput()[oscIdx - idxSpan] <= stochOsc.getLowerThreshold()) return true;
		return false;
	}

	@Override
	protected int oscLookBackSmoothingPeriod() {
		return 0;
	}

}
