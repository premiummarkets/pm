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

import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;
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
import com.finance.pms.events.scoring.functions.RegLineBalancedHighLowSolver;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

public abstract class OscillatorDivergenceCalculator extends TalibIndicatorsOperator {

	//	private static MyLogger LOGGER = MyLogger.getLogger(OscillatorDivergenceCalculator.class);
	private HighLowSolver highLowSolver;

	private Quotations quotationsCopy;
	private double[] closeValues;

	public OscillatorDivergenceCalculator(EventInfo eventInfo, Observer ...observers) {
		super(eventInfo, observers);
		highLowSolver = new SmoothHighLowSolver();
	}


	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes((EventDefinition) getEventDefinition());
		res.setCurrentDate(qU.getDate());

		Calendar currentDateCal = Calendar.getInstance();
		currentDateCal.setTime(res.getCurrentDate());
		Date lookBackStartDate = QuotationsFactories.getFactory().incrementDate(currentDateCal, -getDaysSpan()).getTime();
		int lookBackStartIdx = quotationsCopy.getClosestIndexBeforeOrAtDateOrIndexZero(0, lookBackStartDate);
		int idxSpan = quotationIdx - lookBackStartIdx;

		int oscIdx = getIndicatorIndexFromQuotationIndex(getOscillator(), quotationIdx);
		int oscLookBackStartIdx = oscIdx - idxSpan;
		if (oscLookBackStartIdx < 0) {//No enough data
			throw new InvalidAlgorithmParameterException(
					"Negative look back span for " + quotationsCopy.getStock() + " and " + this.getEventDefinition() +
							". LookBackPeriodStart : " + lookBackStartDate + ", days span " + getDaysSpan() +
							", first date available : " + quotationsCopy.get(0));
		}

		Double[] oscLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(getOscillatorOutput(), oscLookBackStartIdx, oscIdx));
		Double[] stockLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(closeValues, lookBackStartIdx, quotationIdx));

		{
			Boolean isPriceDown = false;
			Boolean isOscillatorUp = false;
			Boolean isOscillatorBelowThreshold = isOscBelowLowerThreshold(idxSpan, oscIdx);

			if (isOscillatorBelowThreshold) {
				isOscillatorUp = highLowSolver.higherLow(oscLookBackP, 0, getAlphaBalance().intValue(), new TreeMap<>(), new ArrayList<>());
				if (isOscillatorUp) {
					isPriceDown = highLowSolver.higherLow(stockLookBackP, 0, getAlphaBalance().intValue(), new TreeMap<>(), new ArrayList<>());
				}
			}

			res.setBullishCrossOver(isPriceDown && isOscillatorBelowThreshold && isOscillatorUp);
			if (res.getBullishCrossOver()) {
				return res;
			}

		}

		{
			Boolean isPriceUp = false;
			Boolean isOscillatorDown = false;
			Boolean isOscillatorAboveThreshold = isOcsAboveUpperThreshold(idxSpan, oscIdx);

			if (isOscillatorAboveThreshold) {
				isOscillatorDown = highLowSolver.lowerHigh(oscLookBackP, 0, getAlphaBalance().intValue(), new TreeMap<>(), new ArrayList<>());
				if (isOscillatorDown) {
					isPriceUp = highLowSolver.lowerHigh(stockLookBackP, 0, getAlphaBalance().intValue(), new TreeMap<>(), new ArrayList<>());
				}
			}

			res.setBearishCrossBellow(isPriceUp && isOscillatorDown && isOscillatorAboveThreshold);
			return res;
		}
	}

	protected Boolean isOcsAboveUpperThreshold(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
			if (getOscillatorOutput()[i] >= getOscillatorUpperThreshold()) {
				return true;
			}
		}
		return false;
	}

	protected Boolean isOscBelowLowerThreshold(int idxSpan, int oscIdx) {
		for (int i = oscIdx - idxSpan; i < oscIdx; i++) {
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
						//((this.higherLows.get(calculatorIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(calculatorIndex)!=null)?mfiV:"") + "," +
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

	protected abstract Double getAlphaBalance();

	@Override
	protected void initIndicators(Quotations quotations) throws TalibException {
		this.quotationsCopy = quotations;
		this.closeValues = quotationsCopy.getCloseValues();
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
