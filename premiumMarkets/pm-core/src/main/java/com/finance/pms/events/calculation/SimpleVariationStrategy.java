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

import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventType;
import com.finance.pms.events.quotations.Quotations;

public class SimpleVariationStrategy implements VariationStrategy {
	
	private static final int NOTICABLEPERIODBAND = 10;
	private Quotations quotations;

	
	public EventType periodType(Integer calculationStartIdx, Integer calculationEndIdx) {
		
		//Avg previous variations
		double[] quotationCloseValues = quotations.getCloseValues();
		double avgPreviousVariation = mean(calculationStartIdx, calculationEndIdx, quotationCloseValues);

		//Last variation
		int higherIndex = calculationEndIdx;
		int lowerIndex = calculationEndIdx-2*NOTICABLEPERIODBAND;
		double closeAtEndOfNextPeriod = quotationCloseValues[higherIndex];
		double closeAtStartOfPeriod = quotationCloseValues[lowerIndex];
		double lastVariation = (closeAtEndOfNextPeriod  - closeAtStartOfPeriod) / closeAtStartOfPeriod;

		//Rate of last variation
		double variationRate = lastVariation/avgPreviousVariation;

		if (variationRate < -1) {
			return EventType.BEARISH;
		} 
		if (variationRate > 1){
			return EventType.BULLISH;
		}

		return EventType.NONE;
	}

	/**
	 * @param calculationStartIdx
	 * @param calculationEndIdx
	 * @param quotationCloseValues
	 * @param avgPreviousVariation
	 * @return
	 */
	private double mean(Integer calculationStartIdx, Integer calculationEndIdx, double[] quotationCloseValues) {
		int startAvgindex = calculationStartIdx + NOTICABLEPERIODBAND;
		double avgPreviousVariation = 0;
		for (int i = startAvgindex; i < calculationEndIdx ; i++) {
			double closeAtI = quotationCloseValues[i];
			double closeAtIPeriodStart = quotationCloseValues[i - NOTICABLEPERIODBAND];
			double periodVariation = (closeAtI  - closeAtIPeriodStart) / closeAtIPeriodStart;
			avgPreviousVariation = avgPreviousVariation + Math.abs(periodVariation);
		}
		avgPreviousVariation = avgPreviousVariation / (calculationEndIdx -1 - startAvgindex);
		return avgPreviousVariation;
	}

	
	public EventDefinition getEventDefinition() {
		return EventDefinition.VARIATION;
	}

	
	public void setQuotations(Quotations calculatorQuotationData) {
		this.quotations = calculatorQuotationData;
		
	}
}
