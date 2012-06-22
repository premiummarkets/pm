/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
