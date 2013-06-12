
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

import java.util.Date;
import java.util.List;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.StochasticOscillator;
import com.finance.pms.talib.indicators.TalibIndicator;

public class StochasticDivergence extends OscillatorDivergenceCalculator {
	
	private StochasticOscillator stochOsc;
	private Integer stochQuotationStartDateIdx;

	public StochasticDivergence(Stock stock, StochasticOscillator stochasticOscillator, HouseAroon aroon, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		this.stochOsc = stochasticOscillator;
		stochQuotationStartDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(0, startDate);
		Integer stochQuotationEndDateIdx = stochasticOscillator.getIndicatorQuotationData().getClosestIndexForDate(stochQuotationStartDateIdx, endDate);
		isValidData(stock, stochasticOscillator, startDate, stochQuotationStartDateIdx, stochQuotationEndDateIdx);
	
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
		//String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, LOW TH, UP TH, STOCH SLOW K, STOCH SLOW D , bearish, bullish";
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, STOCH DATE, LOW TH, UP TH, STOCH SLOW K, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";	
	}
	
	@Override
	protected double[] buildOneOutput(int calculatorIndex) {
			
		int stochIndex = getIndicatorIndexFromCalculatorQuotationIndex(this.stochOsc, calculatorIndex, stochQuotationStartDateIdx);
		return new double[]
				{
				this.stochOsc.getSlowK()[stochIndex],
				translateOutputForCharting(this.higherLows.get(calculatorIndex)),
				translateOutputForCharting(this.lowerHighs.get(calculatorIndex)),
				this.stochOsc.getSlowD()[stochIndex],
				this.stochOsc.getLowerThreshold(),
				this.stochOsc.getUpperThreshold(),
				};
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMSSTOCHDIVERGENCE;
	}

	@Override
	protected double getOscillatorLowerThreshold() {
		return this.stochOsc.getLowerThreshold();
	}

	@Override
	protected double getOscillatorUpperThreshold() {
		return this.stochOsc.getUpperThreshold();
	}

	@Override
	protected Integer getOscillatorQuotationStartDateIdx() {
		return stochQuotationStartDateIdx;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return this.stochOsc.getSlowD();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return this.stochOsc;
	}

}
