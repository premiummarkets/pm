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

import java.util.List;
import java.util.Observer;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.talib.indicators.RSI;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibIndicator;
/**
 * @author Gheeyom Thor
 * is bullish when close makes a lower low over 35 days spanning 5 days smoothed 0 days and 
 * 	RSI makes a higher low over 35 days spanning 5 days smoothed 3 days and 
 *  RSI is below threshold 30 over 35 days for 0 days;
 * is bearish when close makes a higher high over 35 days spanning 5 days smoothed 0 days and 
 *  RSI makes a lower high over 35 days spanning 5 days smoothed 3 days and 
 *  RSI is above threshold 70 over 35 days for 0 days;
 *  
 *  with RSI : rsi(14,close)
 *
 * Bearish divergence occurs when price makes a new high but the RSI makes a lower high, thus failing to confirm. Bullish divergence occurs when price makes a new low but RSI makes a higher low.
 * This is implemented in the inherited @see OscillatorDivergenceCalculator
 * TODO, https://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:relative_strength_index_rsi :
 * Failure Swing : With first peak above higher threshold and second below
 * RSI Uptrends and downtrends :
 * 	uptrends generally traded between RSI 40 and 80, while downtrends usually traded between RSI 60 and 20. //TODO needs a second set of thresholds
 * 	bearish divergence is a sign confirming an uptrend. Similarly, bullish divergence is a sign confirming a downtrend. //TODO Divergence
 * RSI Reversal :
 * 	For example, a positive reversal occurs when an uptrend price correction results in a higher low compared to the last price correction, while RSI results in a lower low compared to the prior correction
 */
public class RSIDivergence extends DivergentOperator {

	RSI rsi;

	private int upperBullishBand; //= 80;
	private int lowerBullishBand; //= 40;

	private int upperBearishBand; //= 60;
	private int lowerBearishBand; //= 20;

	private int lowerThreshold; //= 30;
	private int upperThreshold; //= 70

	public RSIDivergence(
			Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold,
			Integer rsiLowerBullishBand, Integer rsiUpperBullishBand, Integer rsiLowerBearishBand, Integer rsiUpperBearishBand,
			Observer... observers) {
		super(EventDefinition.PMRSIDIVERGENCE, observers);
		init(rsiTimePeriod, rsiLowerThreshold, rsiUpperThreshold, rsiLowerBullishBand, rsiUpperBullishBand, rsiLowerBearishBand, rsiUpperBearishBand);
	}

	public RSIDivergence(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(
			Integer rsiTimePeriod, Integer rsiLowerThreshold, Integer rsiUpperThreshold,
			Integer rsiLowerBullishBand, Integer rsiUpperBullishBand, Integer rsiLowerBearishBand, Integer rsiUpperBearishBand) {
		this.rsi = new RSI(rsiTimePeriod);
		this.lowerThreshold = rsiLowerThreshold;
		this.upperThreshold = rsiUpperThreshold;
		this.lowerBullishBand = rsiLowerBullishBand;
		this.upperBullishBand = rsiUpperBullishBand;
		this.lowerBearishBand = rsiLowerBearishBand;
		this.upperBearishBand = rsiUpperBearishBand;
	}

	@Override
	public void genericInit(Integer... constants) {
		init(constants[0], constants[1], constants[2], constants[3], constants[4], constants[5], constants[6]);
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
	protected int getDaysSpan() {
		return 35;
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, LOW TH, UP TH, RSI, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";
	}

	@Override
	protected double[] buildOneOutput(QuotationUnit quotationUnit, Integer idx) {

		return new double[]
				{
						this.rsi.getRsi()[getIndicatorIndexFromQuotationIndex(this.rsi, idx)],
						lowerThreshold,
						upperThreshold,
						lowerBullishBand,
						upperBullishBand,
						lowerBearishBand,
						upperBearishBand
				};
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMRSIDIVERGENCE;
	}

	@Override
	protected double[] getOscillatorOutput() {
		return this.rsi.getRsi();
	}

	@Override
	protected TalibIndicator getOscillator() {
		return this.rsi;
	}

	@Override
	protected Double getAlphaBalance() {
		return (double) (getDaysSpan()/7);
	}

	@Override
	protected Boolean isOcsWithinBearThresholds(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
			//if (upperBearishBand >= getOscillatorOutput()[i] && getOscillatorOutput()[i] >= lowerBearishBand) {
			if (getOscillatorOutput()[i] >= upperThreshold) {
				return true;
			}
		}
		//if (getOscillatorOutput()[oscIdx - idxSpan] >= upperThreshold) return true;
		return false;
	}

	@Override
	protected Boolean isOscWithinBullThresholds(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
			//if (upperBullishBand >= getOscillatorOutput()[i] && getOscillatorOutput()[i] >= lowerBullishBand) {
			if (lowerThreshold >= getOscillatorOutput()[i]) {
				return true;
			}
		}
		//if (lowerThreshold >= getOscillatorOutput()[oscIdx - idxSpan]) return true;
		return false;
	}

	@Override
	protected int oscLookBackSmoothingPeriod() {
		return 3;
	}

}
