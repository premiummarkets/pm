
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.events.quotations.QuotationsFactories;

public class DateFactory {

	protected static MyLogger LOGGER = MyLogger.getLogger(DateFactory.class);

	public static long DAYINMILLI = 1000*60*60*24;

	public static Date dateAtZero() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(1970, 0, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();

	}

	public static Date midnithDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();

	}

	public static synchronized long milliSecStamp() {
		long time = new Date().getTime();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			LOGGER.error(e,e);
		}
		return time;

	}

	public static Calendar getNowEndDateCalendar() {
		Calendar instance = Calendar.getInstance();
		instance.setTime(getNowEndDate());
		return instance;
	}

	//Retro tests
	private static Date ENDDATE; 
	static {
		initEndDate();
	}

	private static void initEndDate() {
		String endDateStr = MainPMScmd.getMyPrefs().get("test.endDate", null);
		if (endDateStr != null && !endDateStr.isEmpty()) {
			try {
				ENDDATE = midnithDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr));
			} catch (ParseException e) {
				LOGGER.error(e);
			}
		}
	}

	public static Date getNowEndDate() {
		if (ENDDATE != null) {
			return new Date(ENDDATE.getTime());
		} else {
			Date midnithDate = midnithDate(new Date());
			return midnithDate;
		}
	}
	
	public static Date getNowEndTime() {
		if (ENDDATE != null) {
			Calendar nowEndDateCalendar = getNowEndDateCalendar();
			nowEndDateCalendar.set(Calendar.HOUR_OF_DAY, 18 + 5); //End std trading US 18hours (GMT) + 5hours
			nowEndDateCalendar.set(Calendar.MINUTE, 0);
			nowEndDateCalendar.set(Calendar.SECOND, 0);
			nowEndDateCalendar.set(Calendar.MILLISECOND, 0);
			return nowEndDateCalendar.getTime();
		} else {
			return new Date();
		}
	}
	
	public static void forceEndDate(Date date) {
		ENDDATE = date;
	}
	//

	//TODO replace with DateFactory.dateAtZero
	public static Date DEFAULT_DATE;
	static {
		DateFactory.DEFAULT_DATE = new Date(0);
		try {
			DateFactory.DEFAULT_DATE = new SimpleDateFormat("yyyy/MM/dd").parse("1970/01/01");
		} catch (ParseException e1) {
			LOGGER.error("Shouldn't be here", e1);
		}
	}
	
	/**
	 * nb days = data point amount * 7/5
	 */
	public static Date incrementDateWraper(Date startDate, int dataPointsAmount) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		QuotationsFactories.getFactory().incrementDate(startCal, dataPointsAmount);
		return startCal.getTime();
	}
	
	/**
	 * actualDateTime will always be after the last market close time returned
	 * @param actualDateTime
	 * @return
	 */
	public static Date endDateFix(Date actualDateTime, int utcTimeLag, TradingMode tradingMode) {
		
		Date actualDate = DateFactory.midnithDate(actualDateTime);
		Calendar dayMarketClosureCal = Calendar.getInstance();
		if (tradingMode.equals(TradingMode.NON_STOP)) {
			dayMarketClosureCal.setTime(actualDateTime);
			dayMarketClosureCal.set(Calendar.HOUR_OF_DAY, 0 - utcTimeLag); //Midnight UTC lag added is the closure time
			dayMarketClosureCal.set(Calendar.MINUTE, 0);
			dayMarketClosureCal.set(Calendar.SECOND, 0);
			dayMarketClosureCal.set(Calendar.MILLISECOND, 0);
		} else { //skip week ends
			dayMarketClosureCal.setTime(QuotationsFactories.getFactory().getValidQuotationDateBeforeOrAt(actualDate)); //Today or last Friday (if Sat or Sun)
			dayMarketClosureCal.set(Calendar.HOUR_OF_DAY, 18 - utcTimeLag); //At market closure time (inc time lag)
		}
		
		Date endDate;
		if (actualDateTime.compareTo(dayMarketClosureCal.getTime()) < 0) { //Before closure
			dayMarketClosureCal.add(Calendar.HOUR_OF_DAY, + utcTimeLag); //Fixing the potential added/removed day to get the Locale day.
			if (tradingMode.equals(TradingMode.NON_STOP)) {
				dayMarketClosureCal.add(Calendar.DAY_OF_YEAR, -2);
			} else {
				dayMarketClosureCal.add(Calendar.DAY_OF_YEAR, -1);
				dayMarketClosureCal.setTime(QuotationsFactories.getFactory().getValidQuotationDateBeforeOrAt(dayMarketClosureCal.getTime())); 
			}
			Date previousMarketClosure = dayMarketClosureCal.getTime();
			endDate = DateFactory.midnithDate(previousMarketClosure);
		} else { //After Closure
			if (tradingMode.equals(TradingMode.NON_STOP)) {
				dayMarketClosureCal.add(Calendar.DAY_OF_YEAR, -1);
			}
			endDate = DateFactory.midnithDate(dayMarketClosureCal.getTime());
		}
		
		return endDate;
		
	}

	public static int UStoGBUTCTimeLag() {
//		int mill = 1000*60*60;
//		int us = TimeZone.getTimeZone("EST").getOffset(Calendar.getInstance(TimeZone.getTimeZone("EST")).getTimeInMillis())/mill;
//		int os = TimeZone.getDefault().getOffset(Calendar.getInstance(TimeZone.getDefault()).getTimeInMillis())/mill;
//		int t = us - os;
//		LOGGER.info("UStoGBUTCTimeLag: " + t + " with os: " + os + " and us: " + us);
		return -5;
	}

}
