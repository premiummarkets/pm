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

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.talib.indicators.SMA;

public class StdDevStrategy implements VariationStrategy {
	
	private static final Integer NOTICABLEPERIODBAND = 1;
	private static final Integer MINNBVAR = 10;
	
	//private Quotations quotations;
	SMA sma;

	public StdDevStrategy(SMA sma) {
		//this.quotations = new CalculationQuotations(sma.getStock(), sma.getStripedData());
		this.sma = sma;
	}

	
	public EventType periodType(Integer calculationStartIdx, Integer calculationEndIdx) throws NotEnoughDataException {

		//Avg previous variations
		double[] quotationCloseValues = sma.getSma();

		double lastVariation = periodVariation(quotationCloseValues, calculationEndIdx - NOTICABLEPERIODBAND, calculationEndIdx);
		Boolean positiveVariation = lastVariation > 0;
		
		List<Double> variations = new ArrayList<Double>();
		for (int i = calculationStartIdx + NOTICABLEPERIODBAND; i < calculationEndIdx ; i++) {
			double periodVariation = periodVariation(quotationCloseValues, i - NOTICABLEPERIODBAND, i);
			if (periodVariation <= 0 && !positiveVariation) {
				variations.add(periodVariation);
			}
			if (periodVariation > 0 & positiveVariation) {
				variations.add(periodVariation);
			}
		}
		int nbSignedVariations = variations.size();
		if (variations.size() < MINNBVAR) throw new NotEnoughDataException(null, "", null);
		double[] variationsArray = new double[nbSignedVariations];
		for (int i = 0; i < nbSignedVariations; i++) {
			variationsArray[i] = variations.get(i);
		}
		
		double meanPreviousVariation = mean(0, nbSignedVariations, variationsArray);	
		double stdDev = stdDev(variationsArray, 0, nbSignedVariations, meanPreviousVariation);

		//Last variation
		double lastDiffToMean = lastVariation - meanPreviousVariation;

		if (lastDiffToMean < -stdDev && !positiveVariation) {
			return EventType.BEARISH;
		} 
		if (stdDev < lastDiffToMean &&  positiveVariation){
			return EventType.BULLISH;
		}

		return EventType.NONE;
	}

	/**
	 * @param variations
	 * @param startAvgIdx
	 * @param calculationEndIdx
	 * @param meanPreviousVariation
	 * @return
	 */
	double stdDev(double[] variations, int startAvgIdx, Integer calculationEndIdx, double meanPreviousVariation) {
		double variance = 0;
		for (int i = startAvgIdx; i < calculationEndIdx ; i++) {
			double diffToMean = variations[i] - meanPreviousVariation;
			double periodVariance = diffToMean*diffToMean;
			variance = variance + periodVariance;
		}
		variance = variance/(calculationEndIdx - startAvgIdx);
		double stdDev = Math.sqrt(variance);
		return stdDev;
	}

	/**
	 * @param startAvgIdx
	 * @param calculationEndIdx
	 * @param variations
	 * @param avgPreviousVariation
	 * @return
	 */
	double mean(Integer startAvgIdx, Integer calculationEndIdx, double[] variations) {
		double avgPreviousVariation = 0;
		for (int i = startAvgIdx; i < calculationEndIdx ; i++) {
			avgPreviousVariation = avgPreviousVariation + variations[i];
		}
		avgPreviousVariation = avgPreviousVariation / (calculationEndIdx - startAvgIdx);
		return avgPreviousVariation;
	}

	/**
	 * @param quotationCloseValues
	 * @param iStart
	 * @return
	 */
	private double periodVariation(double[] quotationCloseValues, int iStart, int iEnd) {
		double closeAtI = quotationCloseValues[iEnd];
		double closeAtIPeriodStart = quotationCloseValues[iStart];
		return (closeAtI-closeAtIPeriodStart)/closeAtIPeriodStart;
	}

	
	public EventDefinition getEventDefinition() {
		return EventDefinition.VARIANCE;
	}

	
	public void setQuotations(Quotations quotations) {
		// TODO Auto-generated method stub
		
	}

}
