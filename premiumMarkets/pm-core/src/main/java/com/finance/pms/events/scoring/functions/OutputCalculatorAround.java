
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


package com.finance.pms.events.scoring.functions;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.SectorCurveTransformator.Extremity;

@Deprecated
public class OutputCalculatorAround implements OutputCalculator {

	private Integer noticablePeriodBand;

	public OutputCalculatorAround(Integer noticablePeriodBand) {
		super();
		this.noticablePeriodBand = noticablePeriodBand;
	}


	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {
		
		Calendar nextDateShift = Calendar.getInstance();
		nextDateShift.setTime(nextExtremDate);
		//FIXME QuotationsFactories.getFactory().incrementDate(nextDateShift, -2*noticablePeriodBand);
		
		if (prevExtremDate.after(nextDateShift.getTime())) {//fix prev, next over lap
			int nbOpenIncrementBetween = QuotationsFactories.getFactory().nbOpenIncrementBetween(prevExtremDate, nextExtremDate);
			nextDateShift.setTime(nextExtremDate);
			//FIXME QuotationsFactories.getFactory().incrementDate(nextDateShift, nbOpenIncrementBetween/2);
		}
		
		if (currentTime.compareTo(prevExtremDate) == 0) {
			return prevValue;
		} else 
			if (currentTime.after(nextDateShift.getTime()) && ( currentTime.before(nextExtremDate) || currentTime.compareTo(nextExtremDate) == 0)) {
				return nextValue;
			} else {
				return Extremity.UNKNOWN.getExValue();
			}
	}

}
