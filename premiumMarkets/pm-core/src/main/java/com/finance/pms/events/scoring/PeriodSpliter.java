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
package com.finance.pms.events.scoring;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

import com.finance.pms.datasources.events.OnTheFlyRevesreCalcPeriod;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.QuotationsFactories;

public class PeriodSpliter {
	
	public Stack<OnTheFlyRevesreCalcPeriod> splitBackward(Date dateDeb, Date dateFin, Integer periodLength, int calendarTimeUnit) {

		Calendar dateDebCal = Calendar.getInstance();
		dateDebCal.setTime(DateFactory.midnithDate(dateDeb));

		Calendar dateFinCal = Calendar.getInstance();
		dateFinCal.setTime(DateFactory.midnithDate(dateFin));

		Stack<OnTheFlyRevesreCalcPeriod> retuneDates = new Stack<OnTheFlyRevesreCalcPeriod>();

		if (periodLength > 0) {

			Calendar slidingDateFinCal = Calendar.getInstance();
			slidingDateFinCal.setTime(dateFin);
			slidingDateFinCal.add(Calendar.DAY_OF_YEAR, 1); //for exclusive subsets fix

			//Add previous periods
			while (dateDebCal.getTime().before(slidingDateFinCal.getTime())) {

				Date pEndDate = slidingDateFinCal.getTime();
				if (calendarTimeUnit == Calendar.DAY_OF_YEAR) {
					QuotationsFactories.getFactory().incrementDate(slidingDateFinCal, -periodLength);
				} else {
					QuotationsFactories.getFactory().incrementDateLarge(slidingDateFinCal, -periodLength);
				}
				Date pStartDate = slidingDateFinCal.getTime();
				retuneDates.add(new OnTheFlyRevesreCalcPeriod(dateDeb, dateFin, pStartDate, pEndDate));
			}

		} else {

			retuneDates.add(new OnTheFlyRevesreCalcPeriod(dateDeb, dateFin, dateDeb, dateFin));
		}

		return retuneDates;
	}
	
	public Stack<OnTheFlyRevesreCalcPeriod> splitForward(Date dateDeb, Date dateFin, Integer periodLength, int calendarTimeUnit) {

		Calendar dateDebCal = Calendar.getInstance();
		dateDebCal.setTime(DateFactory.midnithDate(dateDeb));

		Calendar dateFinCal = Calendar.getInstance();
		dateFinCal.setTime(DateFactory.midnithDate(dateFin));
		dateFinCal.add(Calendar.DAY_OF_YEAR, 1); //for exclusive subsets fix


		Stack<OnTheFlyRevesreCalcPeriod> retuneDates = new Stack<OnTheFlyRevesreCalcPeriod>();

		if (periodLength > 0) {

			Calendar slidingDateDebCal = Calendar.getInstance();
			slidingDateDebCal.setTime(dateDeb);
			
			//Add previous periods
			while (dateFinCal.getTime().after(slidingDateDebCal.getTime())) {

				Date pStartDate = slidingDateDebCal.getTime();
				if (calendarTimeUnit == Calendar.MONTH) {
					QuotationsFactories.getFactory().incrementDateLarge(slidingDateDebCal, periodLength);
				} else if (calendarTimeUnit == Calendar.DAY_OF_YEAR)  {
					QuotationsFactories.getFactory().incrementDate(slidingDateDebCal, periodLength);
				} else {
					throw new InvalidParameterException("calendarTimeUnit : "+calendarTimeUnit);
				}
				Date pEndDate = slidingDateDebCal.getTime();
				
				if (pEndDate.before(dateFinCal.getTime())) {
					retuneDates.add(0,new OnTheFlyRevesreCalcPeriod(dateDeb, dateFin, pStartDate, pEndDate));
				} else {//End date reached
					retuneDates.add(0,new OnTheFlyRevesreCalcPeriod(dateDeb, dateFin, pStartDate, dateFinCal.getTime()));
					break;
				}
			}

		} else {
			retuneDates.add(new OnTheFlyRevesreCalcPeriod(dateDeb, dateFin, dateDeb, dateFin));
		}

		return retuneDates;
	}

}
