/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.mutable.MutableInt;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.indicators.FormulatRes;
import com.finance.pms.talib.indicators.TalibIndicator;

public abstract class OscillatorDivergenceCalculator extends TalibIndicatorsCompositionCalculator {
	
	protected SortedMap<Integer,Double> higherLows;
	protected SortedMap<Integer,Double> lowerHighs;
	private HighLowSolver highLowSolver;

	public OscillatorDivergenceCalculator(Stock stock, Date startDate, Date endDate, Currency calculationCurrency) throws NotEnoughDataException {
		super(stock, startDate, endDate, calculationCurrency);
		
		highLowSolver = new HighLowSolver();
		higherLows = new TreeMap<Integer, Double>();
		lowerHighs = new TreeMap<Integer, Double>();
	}

	@Override
	protected FormulatRes eventFormulaCalculation(Integer calculatorIndex) throws InvalidAlgorithmParameterException {
		
		FormulatRes res = new FormulatRes((EventDefinition) getEventDefinition());
		res.setCurrentDate(this.getCalculatorQuotationData().getDate(calculatorIndex));
		
		Calendar currentDateCal = Calendar.getInstance();
		currentDateCal.setTime(res.getCurrentDate());
		Date lookBackPeriodStart = QuotationsFactories.getFactory().incrementDate(currentDateCal, -getDaysSpan()).getTime();
		int lookBackPeriodStartIdx = this.getCalculatorQuotationData().getClosestIndexForDate(0, lookBackPeriodStart);
		int idxSpan = calculatorIndex - lookBackPeriodStartIdx;
		
		if (idxSpan < 4) return res; //We need a least 3 days to draw higher low or lower high
		
		int mfiIdx = getIndicatorIndexFromCalculatorQuotationIndex(getOscillator(), calculatorIndex, getOscillatorQuotationStartDateIdx());
		Double[] mfiLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(getOscillatorOutput(), mfiIdx - idxSpan, mfiIdx));
	
//		Double[] priceLookBackP = ArrayUtils.toObject(Arrays.copyOfRange(this.getCalculatorQuotationData().getCloseValues(), calculatorIndex - idxSpan, calculatorIndex));
		
		{
//			Boolean isPriceDown = this.getCalculatorQuotationData().get(calculatorIndex - priceSpan).getClose().compareTo(this.getCalculatorQuotationData().get(calculatorIndex).getClose()) > 0;
//			ArrayList<Double> priceRegline = new ArrayList<Double>();
//			Boolean isPriceDown = highLowSolver.lowerHigh(priceLookBackP,new Double[0],priceRegline);
//			Boolean isPriceDown = leftSigma/(priceSpan - priceSpan/2) > rightSigma/(priceSpan/2);
			Boolean isPriceDown = false;
			
			Boolean isMfiUp = false;
			Boolean isMfiDownThreshold = false;
			isMfiDownThreshold = isOscBelowLowerThreshold(idxSpan, mfiIdx);

			if (isMfiDownThreshold) {

				ArrayList<Double> regline = new ArrayList<Double>();
				MutableInt firstTroughIdx = new MutableInt(-1);
				MutableInt lastTroughIdx = new MutableInt(-1);
				isMfiUp = highLowSolver.higherLow(mfiLookBackP, new Double[0], getAlphaBalance(), regline, firstTroughIdx, lastTroughIdx);

				if (isMfiUp) {
					int coveredSpan = regline.size();
					Double leftSigma = 0d;
					for (int i = calculatorIndex - coveredSpan; i <= calculatorIndex - coveredSpan/2; i++) {
						leftSigma = leftSigma + this.getCalculatorQuotationData().get(i).getClose().doubleValue();
					}	
					Double rightSigma=0d;
					for (int i = calculatorIndex - coveredSpan/2; i <= calculatorIndex; i++) {
						rightSigma = rightSigma + this.getCalculatorQuotationData().get(i).getClose().doubleValue();
					}
					isPriceDown = leftSigma/(coveredSpan - coveredSpan/2) > rightSigma/(coveredSpan/2);

					if (isPriceDown) addReglineOutput(higherLows, calculatorIndex, regline, coveredSpan);
				}
			}
			
			res.setBullishCrossOver(isPriceDown && isMfiDownThreshold && isMfiUp); 
			if (res.getBullishCrossOver()) {
				return res;
			}
			
		}
		{
//			Boolean isPriceUp = this.getCalculatorQuotationData().get(calculatorIndex - priceSpan).getClose().compareTo(this.getCalculatorQuotationData().get(calculatorIndex).getClose()) < 0;
//			ArrayList<Double> priceRegline = new ArrayList<Double>();
//			Boolean isPriceUp = highLowSolver.higherLow(priceLookBackP,new Double[0],priceRegline);
//			Boolean isPriceUp =  leftSigma/(priceSpan - priceSpan/2) < rightSigma/(priceSpan/2);
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

					int coveredSpan = regline.size();
					Double leftSigma = 0d;
					for (int i = calculatorIndex - coveredSpan; i <= calculatorIndex - coveredSpan/2; i++) {
						leftSigma = leftSigma + this.getCalculatorQuotationData().get(i).getClose().doubleValue();
					}	
					Double rightSigma=0d;
					for (int i = calculatorIndex - coveredSpan/2; i <= calculatorIndex; i++) {
						rightSigma = rightSigma + this.getCalculatorQuotationData().get(i).getClose().doubleValue();
					}
					isPriceUp = leftSigma/(coveredSpan - coveredSpan/2) < rightSigma/(coveredSpan/2);

					if (isPriceUp) addReglineOutput(lowerHighs, calculatorIndex, regline, coveredSpan);
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
	protected String buildLine(int calculatorIndex, Map<EventKey, EventValue> edata, List<SortedMap<Date, double[]>> linearsExpects) {
		Date calculatorDate = this.getCalculatorQuotationData().get(calculatorIndex).getDate();
		EventValue bearishEventValue = edata.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BEARISH));
		EventValue bullishEventValue = edata.get(new StandardEventKey(calculatorDate,getEventDefinition(),EventType.BULLISH));
		BigDecimal calculatorClose = this.getCalculatorQuotationData().get(calculatorIndex).getClose();
		int mfiQuotationIndex = getIndicatorQuotationIndexFromCalculatorQuotationIndex(calculatorIndex,getOscillatorQuotationStartDateIdx());
		double mfiV = getOscillatorOutput()[getIndicatorIndexFromCalculatorQuotationIndex(getOscillator(), calculatorIndex, getOscillatorQuotationStartDateIdx())];
		String thresholdString = printThresholdsCSV();
		String line =
			new SimpleDateFormat("yyyy-MM-dd").format(calculatorDate) + "," + calculatorClose + "," + 
			getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getDate() + "," + getOscillator().getIndicatorQuotationData().get(mfiQuotationIndex).getClose() + "," + 
			((this.higherLows.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + ((this.lowerHighs.get(mfiQuotationIndex)!=null)?mfiV:"") + "," + 
			thresholdString + "," + mfiV;
		
		if (bearishEventValue != null) {
			line = line + "," + calculatorClose +",0,";
		} else if (bullishEventValue != null){
			line = line + ",0," + calculatorClose +",";
		} else {
			line = line + ",0,0,";
		}
		
		line = addScoringLinesElement(line, calculatorDate, linearsExpects)+"\n";
		
		return line;
	}

	protected String printThresholdsCSV() {
		return getOscillatorLowerThreshold() + "," + getOscillatorUpperThreshold();
	}
	
	protected void addReglineOutput(SortedMap<Integer, Double> reglines, Integer calculatorIndex, ArrayList<Double> regline, Integer idxSpan) {
		
		int gap = 0;
		int overlapFromKey = calculatorIndex-idxSpan;
		if (!reglines.isEmpty()) {
			while ( reglines.lastKey() >= overlapFromKey -1 && gap < regline.size()-1) {
				overlapFromKey++;
				gap++;
			}
		}
		if (gap < regline.size() -1) {
			reglines.put(overlapFromKey, regline.get(gap));
			reglines.put(calculatorIndex, regline.get(regline.size()-1));
			for (int i = overlapFromKey + 1; i < calculatorIndex; i++) {
				reglines.put(i, Double.NaN);
			}
		}
			
	}
	
	protected abstract Double getAlphaBalance();

}
