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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.mutable.MutableInt;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public abstract class OscillatorDivergenceCalculator extends TalibIndicatorsOperator {

	//	private static MyLogger LOGGER = MyLogger.getLogger(OscillatorDivergenceCalculator.class);

	protected SortedMap<Integer,Double> higherLows;
	protected SortedMap<Integer,Double> lowerHighs;
	private HighLowSolver highLowSolver;
	private Quotations quotationsCopy;

	public OscillatorDivergenceCalculator(EventInfo eventInfo, Observer ...observers) {
		super(eventInfo, observers);

		highLowSolver = new HighLowSolver();
		higherLows = new TreeMap<Integer, Double>();
		lowerHighs = new TreeMap<Integer, Double>();
	}


	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes((EventDefinition) getEventDefinition());
		res.setCurrentDate(qU.getDate());

		Calendar currentDateCal = Calendar.getInstance();
		currentDateCal.setTime(res.getCurrentDate());
		Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDateCal, -getDaysSpan()).getTime();
		int lookBackPeriodStartIdx = quotationsCopy.getClosestIndexBeforeOrAtDateOrIndexZero(0, lookBackPeriodStart);
		int idxSpan = quotationIdx - lookBackPeriodStartIdx;

		if (idxSpan < 4) return res; //We need a least 3 days to draw higher low or lower high

		int mfiIdx = getIndicatorIndexFromQuotationIndex(getOscillator(), quotationIdx);
		int lookBackSpan = mfiIdx - idxSpan;
		if (lookBackSpan < 0) {//No enough data
			throw new InvalidAlgorithmParameterException("Negative look back span for "+quotationsCopy.getStock()+" and "+this.getEventDefinition()+". LookBackPeriodStart : "+lookBackPeriodStart+", days span "+getDaysSpan()+", first date available : "+quotationsCopy.get(0));
		}

		Double[] mfiLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(getOscillatorOutput(), lookBackSpan, mfiIdx));

		{
			Boolean isPriceDown = false;

			Boolean isMfiUp = false;
			Boolean isMfiDownThreshold = false;
			isMfiDownThreshold = isOscBelowLowerThreshold(idxSpan, mfiIdx);

			if (isMfiDownThreshold) {

				ArrayList<Double> regline = new ArrayList<Double>();
				MutableInt firstPeakIdx = new MutableInt(-1);
				MutableInt lastPeakIdx = new MutableInt(-1);
				isMfiUp = highLowSolver.higherLow(mfiLookBackP, new Double[0], getAlphaBalance(), regline, firstPeakIdx, lastPeakIdx);

				if (isMfiUp) {

					//int coveredSpan = regline.size();
					int firstPeakLeftShifted = (int) (Math.max(firstPeakIdx.intValue() - ((double)mfiLookBackP.length)*.1, 0));
					int lastPeakRightShifted = (int) (Math.min(lastPeakIdx.intValue() + ((double)mfiLookBackP.length)*.1, regline.size()-1)); //lastPeakIdx.intValue();
					int coveredSpan = lastPeakRightShifted - firstPeakLeftShifted;
					Double leftSigma = 0d;
					int lastPeakCalculatorIdx = quotationIdx - (regline.size() - lastPeakRightShifted);
					for (int i = lastPeakCalculatorIdx - coveredSpan; i <= lastPeakCalculatorIdx - coveredSpan/2; i++) {
						leftSigma = leftSigma + quotationsCopy.get(i).getClose().doubleValue();
					}
					Double rightSigma=0d;
					for (int i = lastPeakCalculatorIdx - coveredSpan/2; i <= lastPeakCalculatorIdx; i++) {
						rightSigma = rightSigma +  quotationsCopy.get(i).getClose().doubleValue();
					}
					isPriceDown = leftSigma/(coveredSpan - coveredSpan/2) > rightSigma/(coveredSpan/2);

					if (isPriceDown) addReglineOutput(higherLows, lastPeakCalculatorIdx, regline, coveredSpan, firstPeakLeftShifted, lastPeakRightShifted);
				}
			}

			res.setBullishCrossOver(isPriceDown && isMfiDownThreshold && isMfiUp);
			if (res.getBullishCrossOver()) {
				return res;
			}

		}
		{

			Boolean isPriceUp = false;

			Boolean isMfiUpThreshold = false;
			Boolean isMfiDown = false;

			isMfiUpThreshold = isOcsAboveUpperThreshold(idxSpan, mfiIdx);

			if (isMfiUpThreshold) {

				ArrayList<Double> regline = new ArrayList<Double>();
				MutableInt firstTroughIdx = new MutableInt(-1);
				MutableInt lastTroughIdx = new MutableInt(-1);
				isMfiDown = highLowSolver.lowerHigh(mfiLookBackP, new Double[0], getAlphaBalance(), regline, firstTroughIdx, lastTroughIdx);

				if (isMfiDown) {

					//int coveredSpan = regline.size();
					int firstTroughLeftShifted = (int) (Math.max(firstTroughIdx.intValue() - ((double)mfiLookBackP.length)*.1, 0));
					int lastTroughRightShifted = (int) (Math.min(lastTroughIdx.intValue() + ((double)mfiLookBackP.length)*.1, regline.size()-1));
					int coveredSpan = lastTroughRightShifted - firstTroughLeftShifted;
					Double leftSigma = 0d;
					int lastTroughCalculatorIdx = quotationIdx - (regline.size() -lastTroughRightShifted);
					for (int i = lastTroughCalculatorIdx - coveredSpan; i <= lastTroughCalculatorIdx - coveredSpan/2; i++) {
						leftSigma = leftSigma + quotationsCopy.get(i).getClose().doubleValue();
					}	
					Double rightSigma=0d;
					for (int i = lastTroughCalculatorIdx - coveredSpan/2; i <= lastTroughCalculatorIdx; i++) {
						rightSigma = rightSigma + quotationsCopy.get(i).getClose().doubleValue();
					}
					isPriceUp = leftSigma/(coveredSpan - coveredSpan/2) < rightSigma/(coveredSpan/2);

					if (isPriceUp) addReglineOutput(lowerHighs, lastTroughCalculatorIdx, regline, coveredSpan, firstTroughLeftShifted, lastTroughRightShifted);
				}

			}

			res.setBearishCrossBellow(isPriceUp && isMfiDown && isMfiUpThreshold);

			return res;
		}
	}

	protected Boolean isOcsAboveUpperThreshold(int idxSpan, int mfiIdx) {
		for (int i = mfiIdx - idxSpan; i < mfiIdx; i++) {
			if (getOscillatorOutput()[i] >= getOscillatorUpperThreshold()) {
				return true;
			}
		}
		return false;
	}

	protected Boolean isOscBelowLowerThreshold(int idxSpan, int mfiIdx) {
		for (int i = mfiIdx - idxSpan; i < mfiIdx; i++) {
			if (getOscillatorOutput()[i] <= getOscillatorLowerThreshold()) {
				return true;
			}
		}
		return false;
	}

	protected abstract double getOscillatorLowerThreshold();
	protected abstract double getOscillatorUpperThreshold();

	protected abstract Integer getOscillatorQuotationStartDateIdx();

	protected abstract double[] getOscillatorOutput();

	protected abstract TalibIndicator getOscillator();	

	@Override
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		Date calculatorDate = qU.getDate();
		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BEARISH));
		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BULLISH));
		BigDecimal calculatorClose = qU.getClose();
		//		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, getOscillatorQuotationStartDateIdx());
		double mfiV = getOscillatorOutput()[getIndicatorIndexFromQuotationIndex(getOscillator(), calculatorIndex)];
		String thresholdString = printThresholdsCSV();
		String line =
				new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," + calculatorClose + "," + 
						//			getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," + getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," + 
						//			((this.higherLows.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + 
						((this.higherLows.get(calculatorIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(calculatorIndex)!=null)?mfiV:"") + "," + 
						thresholdString + "," + mfiV;

		if (bearishEventValue != null) {
			line = line + "," + calculatorClose +",0,";
		} else if (bullishEventValue != null){
			line = line + ",0," + calculatorClose +",";
		} else {
			line = line + ",0,0,";
		}

		line = addScoringLinesElement(line, calculatorDate, linearExpects)+"\n";

		return line;
	}

	protected String printThresholdsCSV() {
		return getOscillatorLowerThreshold() + "," + getOscillatorUpperThreshold();
	}

	protected void addReglineOutput(SortedMap<Integer, Double> reglines, Integer calculatorIndex, ArrayList<Double> regline, Integer idxSpan, Integer firstExtIdx, Integer lastExtIdx) {

		int gap = 0;
		int overlapFromKey = calculatorIndex-idxSpan;
		if (!reglines.isEmpty()) {
			while ( reglines.lastKey() >= overlapFromKey -1 && gap < (lastExtIdx-firstExtIdx) ) {
				overlapFromKey++;
				gap++;
			}
		}
		if (gap < lastExtIdx) {
			reglines.put(overlapFromKey, regline.get(gap + firstExtIdx));
			reglines.put(calculatorIndex, regline.get(lastExtIdx));
			for (int i = overlapFromKey + 1; i < calculatorIndex; i++) {
				reglines.put(i, Double.NaN);
			}
		}

	}

	protected abstract Double getAlphaBalance();

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {

		this.quotationsCopy = quotations;

		getOscillator().calculateIndicator(quotations);
	}

	@Override
	public Integer getStartShift() {
		return getOscillator().getStartShift() + getDaysSpan();
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return getOscillator().quotationValidity();
	}

	@Override
	public Integer getOutputBeginIdx() {
		return getOscillator().getOutBegIdx().value + getDaysSpan();
	}

}
