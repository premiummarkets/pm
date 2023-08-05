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
import java.util.Arrays;
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
import com.finance.pms.talib.indicators.ChaikinOscillator;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

//@Deprecated
public class ChaikinOscillatorDivergence_old extends TalibIndicatorsOperator {

	private ChaikinOscillator chaikinOscillator;
	private SMA sma;

	private Double[] priceLowerLow;
	private Double[] oscillatorHigherLow;

	private double[] quotationsCopy;

	public ChaikinOscillatorDivergence_old(Integer chkInfastPeriod, Integer chkInslowPeriod, Observer... observers) {
		super(EventDefinition.PMMIGHTYCHAIKIN, observers);
		init(chkInfastPeriod, chkInslowPeriod, 2);
	}

	public ChaikinOscillatorDivergence_old(EventInfo reference) {
		//Reflective ops generator
		super(reference);
	}

	protected void init(Integer chkInFastPeriod, Integer chkInSlowPeriod, Integer signalSmaPeriod) {
		this.chaikinOscillator = new ChaikinOscillator(chkInFastPeriod, chkInSlowPeriod);
		this.sma = new SMA(signalSmaPeriod);
	}

	@Override
	public void genericInit(Integer... constants) {
		init(constants[0], constants[1], constants[2]);
	}

	@Override
	protected  FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes(getEventDefinition());
		res.setCurrentDate(qU.getDate());

		int chaikinIdx = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, quotationIdx);
		double[] chaikinLookBackP = Arrays.copyOfRange(this.chaikinOscillator.getChaikinOsc(), chaikinIdx - getDaysSpan(), chaikinIdx);
		double[] quotationLookBackP = Arrays.copyOfRange(quotationsCopy, quotationIdx - getDaysSpan(), quotationIdx);

		int smaIndex = getIndicatorIndexFromQuotationIndex(this.sma, quotationIdx);
		double[] quotationLookBackPThresh = Arrays.copyOfRange(this.sma.getSma(), smaIndex - getDaysSpan(), smaIndex);

		double[] chaikinThreshCurve = new double[chaikinLookBackP.length];

		{
			Boolean isPriceDown = lowerLow(quotationLookBackP, quotationLookBackPThresh);
			double chaikinSlope = higherLow(chaikinLookBackP, chaikinThreshCurve);
			res.setBullishCrossOver(isPriceDown && (chaikinSlope > 0));

			if (res.getBullishCrossOver()) {

				int closeSlopePointsStartIdx = quotationIdx - getDaysSpan();
				for (; closeSlopePointsStartIdx < quotationIdx; closeSlopePointsStartIdx++) if (oscillatorHigherLow[closeSlopePointsStartIdx] == null) break;
				priceLowerLow[closeSlopePointsStartIdx] = quotationsCopy[closeSlopePointsStartIdx];
				//priceHLs[quotationIdx] = quotationsCopy[quotationIdx - getDaysSpan()] + dataSlope*getDaysSpan();
				priceLowerLow[quotationIdx] = quotationsCopy[quotationIdx];

				int oscSlopePointsStartIdx = chaikinIdx - getDaysSpan();
				for (; oscSlopePointsStartIdx < chaikinIdx; oscSlopePointsStartIdx++) if (oscillatorHigherLow[oscSlopePointsStartIdx] == null) break;
				oscillatorHigherLow[oscSlopePointsStartIdx] = this.chaikinOscillator.getChaikinOsc()[oscSlopePointsStartIdx];
				oscillatorHigherLow[chaikinIdx] = oscillatorHigherLow[oscSlopePointsStartIdx]  + chaikinSlope * (getDaysSpan() - (oscSlopePointsStartIdx - chaikinIdx));

				return res;
			}

		}
		{
			Boolean isPriceUp = higherHigh(quotationLookBackP, quotationLookBackPThresh);
			Boolean isChaikinDown = lowerHigh(chaikinLookBackP,  chaikinThreshCurve);
			res.setBearishCrossBelow(isPriceUp && isChaikinDown);
			return res;
		}

	}

	@Override
	protected Boolean isInDataRange(TalibIndicator indicator, Integer indicatorIndex) {
		return (getDaysSpan() < indicatorIndex) && (indicatorIndex < chaikinOscillator.getChaikinOsc().length);
	}

	@Override
	protected String getHeader(List<Integer> scoringSmas) {
		String head = "CALCULATOR DATE, CALCULATOR QUOTE, Chainkin Osc, bearish, bullish";
		head = addScoringHeader(head, scoringSmas);
		return head+"\n";
	}

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {

		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(), EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(), EventType.BULLISH));
		BigDecimal calculatorClose = qU.getCloseSplit();

		int chaikinIndex = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, calculatorIndex);

		String line =
				new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," +calculatorClose + "," + this.chaikinOscillator.getChaikinOsc()[chaikinIndex];

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

		Integer indicatorIndexFromCalculatorQuotationIndex = getIndicatorIndexFromQuotationIndex(this.chaikinOscillator, idx);
		return new double[]
				{
						this.chaikinOscillator.getChaikinOsc()[indicatorIndexFromCalculatorQuotationIndex],
						this.quotationsCopy[idx],
						translateOutputForCharting(this.priceLowerLow[idx]),
						translateOutputForCharting(this.oscillatorHigherLow[indicatorIndexFromCalculatorQuotationIndex])
				};
	}

	@Override
	protected int getDaysSpan() {
		return 60;
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.PMMIGHTYCHAIKIN;
	}

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {

		this.quotationsCopy = quotations.getCloseValues();
		this.priceLowerLow = new Double[quotationsCopy.length];
		this.sma.calculateIndicator(quotations);

		this.chaikinOscillator.calculateIndicator(quotations);
		this.oscillatorHigherLow = new Double[chaikinOscillator.getChaikinOsc().length];

	}


	@Override
	public Integer getStartShift() {
		//return Math.max(20, getDaysSpan());
		return Math.max(chaikinOscillator.getStartShift(), sma.getStartShift()) + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.OHLCV;
	}

	@Override
	public Integer getOutputBeginIdx() {
		return Math.max(chaikinOscillator.getOutBegIdx().value, sma.getOutBegIdx().value) + getDaysSpan();
	}

}
