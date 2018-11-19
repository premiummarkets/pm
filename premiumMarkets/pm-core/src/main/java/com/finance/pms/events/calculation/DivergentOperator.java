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

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.HighLowSolver.Greed;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibException;
import com.finance.pms.talib.indicators.TalibIndicator;

/**
 * @author Gheeyom Thor
 * @see https://stockcharts.com/school/
 *
 */
public abstract class DivergentOperator extends TalibIndicatorsOperator {

	//private static MyLogger LOGGER = MyLogger.getLogger(OscillatorDivergenceCalculator.class);
	private HighLowSolver highLowSolver;

	private Quotations quotationsCopy;
	private double[] closeValues;

	public DivergentOperator(EventInfo eventInfo, Observer ...observers) {
		super(eventInfo, observers);
		highLowSolver = new SmoothHighLowSolver();
	}


	@Override
	protected FormulatRes eventFormulaCalculation(QuotationUnit qU, Integer quotationIdx) throws InvalidAlgorithmParameterException {

		FormulatRes res = new FormulatRes((EventDefinition) getEventDefinition());
		res.setCurrentDate(qU.getDate());

		int idxSpan = getDaysSpan();
		int lookBackStartIdx = quotationIdx - idxSpan;

		int oscIdx = getIndicatorIndexFromQuotationIndex(getOscillator(), quotationIdx);
		int oscLookBackStartIdx = oscIdx - idxSpan;
		if (lookBackStartIdx < 0 || oscLookBackStartIdx < 0) {//No enough data
			throw new InvalidAlgorithmParameterException(
					"Negative look back span for " + quotationsCopy.getStock() + " and " + this.getEventDefinition() +
							". Current date : " + res.getCurrentDate() + ", days span " + getDaysSpan() +
							", first date available : " + quotationsCopy.get(0));
		}

		//Double[] oscLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(getOscillatorOutput(), oscLookBackStartIdx, oscIdx));
		//Double[] stockLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(closeValues, lookBackStartIdx, quotationIdx));
		//FIXME
		SortedMap<Integer, Double> stockLookBackP = null;
		SortedMap<Integer, Double> oscLookBackP = null;

		{//Bullish
			Boolean isPriceDown = false;
			Boolean isOscillatorUp = false;
			Boolean isOscWithinBullThresholds = isOscWithinBullThresholds(idxSpan, oscIdx);

			if (isOscWithinBullThresholds) {
				isPriceDown = highLowSolver.lowerLow(stockLookBackP, 0, getAlphaBalance().intValue(),  Greed.GREEDY, new TreeMap<>(), new ArrayList<>(), Double.NaN,  Double.NaN,  Double.NaN, Double.NaN, Double.NaN, Double.NaN); //FIXME
				if (isPriceDown) {
					isOscillatorUp = highLowSolver.higherLow(oscLookBackP, oscLookBackSmoothingPeriod(), getAlphaBalance().intValue(),  Greed.GREEDY, new TreeMap<>(), new ArrayList<>(), Double.NaN,  Double.NaN,  Double.NaN, Double.NaN, Double.NaN, Double.NaN); //FIXME
				}
			}

			res.setBullishCrossOver(isOscWithinBullThresholds && isPriceDown && isOscillatorUp);
			if (res.getBullishCrossOver()) {
				return res;
			}

		}
		{//Bearish
			Boolean isPriceUp = false;
			Boolean isOscillatorDown = false;
			Boolean isOcsWithinBearThresholds = isOcsWithinBearThresholds(idxSpan, oscIdx);

			if (isOcsWithinBearThresholds) {
				isPriceUp = highLowSolver.higherHigh(stockLookBackP, 0, getAlphaBalance().intValue(),  Greed.GREEDY, new TreeMap<>(), new ArrayList<>(), Double.NaN,  Double.NaN,  Double.NaN, Double.NaN, Double.NaN, Double.NaN); //FIXME
				if (isPriceUp) {
					isOscillatorDown = highLowSolver.lowerHigh(oscLookBackP, oscLookBackSmoothingPeriod(), getAlphaBalance().intValue(), Greed.GREEDY, new TreeMap<>(), new ArrayList<>(), Double.NaN,  Double.NaN,  Double.NaN, Double.NaN, Double.NaN, Double.NaN); //FIXME
				}
			}

			res.setBearishCrossBellow(isOcsWithinBearThresholds && isPriceUp && isOscillatorDown);
			return res;
		}
	}


	protected abstract int oscLookBackSmoothingPeriod();

	protected abstract Boolean isOcsWithinBearThresholds(int idxSpan, int oscIdx);

	protected abstract Boolean isOscWithinBullThresholds(int idxSpan, int oscIdx);

	protected abstract double[] getOscillatorOutput();

	protected abstract TalibIndicator getOscillator();

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.finance.pms.events.calculation.TalibIndicatorsOperator#buildLine(int, java.util.Map, com.finance.pms.events.quotations.QuotationUnit, java.util.List)
	 */
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> eData, QuotationUnit qU, List<SortedMap<Date, double[]>> linearExpects) {
		throw new NotImplementedException("FIXME");
//		Date calculatorDate = qU.getDate();
//		EventValue bearishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BEARISH));
//		EventValue bullishEventValue = eData.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BULLISH));
//		BigDecimal calculatorClose = qU.getClose();
//		//		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex, getOscillatorQuotationStartDateIdx());
//		double mfiV = getOscillatorOutput()[getIndicatorIndexFromQuotationIndex(getOscillator(), calculatorIndex)];
//		//String thresholdString = printThresholdsCSV();
//		String line =
//				new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," + calculatorClose + ","; //FIXME
//						//			getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," + getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," + 
//						//			((this.higherLows.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + 
//						//((this.higherLows.get(calculatorIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(calculatorIndex)!=null)?mfiV:"") + "," +
//						//thresholdString + "," + mfiV;
//
//		if (bearishEventValue != null) {
//			line = line + "," + calculatorClose +",0,";
//		} else if (bullishEventValue != null){
//			line = line + ",0," + calculatorClose +",";
//		} else {
//			line = line + ",0,0,";
//		}
//
//		line = addScoringLinesElement(line, calculatorDate, linearExpects)+"\n";
//
//		return line;
	}

//	private String printThresholdsCSV() {
//		return getOscillatorLowerThreshold() + "," + getOscillatorUpperThreshold();
//	}

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
