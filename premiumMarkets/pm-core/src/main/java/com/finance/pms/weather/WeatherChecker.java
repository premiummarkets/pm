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
package com.finance.pms.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EmailFilterEventSource;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.WeatherEventKey;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.IndicatorsOperator;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.talib.dataresults.StandardEventValue;

public class WeatherChecker extends IndicatorsOperator {

	private static MyLogger LOGGER = MyLogger.getLogger(WeatherChecker.class);

	private Date startDate;
	private Date endDate;
	SortedSet<WeatherElement> weatherElements;

	public WeatherChecker(Date startDate, Date endDate, Observer... observers) {
		super(observers);
		this.weatherElements = WeatherDAOImpl.getInstance().getMonthlyWeatherUntil(endDate, new WeatherElementsComparator());
		this.startDate = startDate;
		this.endDate = endDate;
	}

	//com.finance.pms.events.EventInfo, com.finance.pms.datasources.shares.Stock, java.util.Date, java.util.Date, com.finance.pms.datasources.shares.Currency, java.lang.String, java.lang.Boolean, [Ljava.util.Observer;)
	public WeatherChecker(EventInfo eventInfo, Stock stock, Date startDate, Date endDate, Currency calculationCurrency, String eventListName, Observer...observers) 
			throws NotEnoughDataException {
		this(startDate, endDate, observers);
	}

	@Override
	public SortedMap<EventKey, EventValue> calculateEventsFor(Quotations quotations, String eventListName) {

		Calendar endDateCalendar = firstDayOfPrevMonthOf(endDate);
		Calendar currentDateCalendar = firstDayOfPrevMonthOf(startDate);
		Calendar todayCalendar = firstDayOfPrevMonthOf(DateFactory.getNowEndDate());

		EventValue meanEventValue = new EventValue(endDate, EventDefinition.WEATHER, EventType.NONE, eventListName);
		EventValue trendChangeEventValue = new EventValue(endDate, EventDefinition.WEATHER, EventType.NONE, eventListName);

		for (; currentDateCalendar.getTime().compareTo(endDateCalendar.getTime()) <= 0 && currentDateCalendar.getTime().compareTo(todayCalendar.getTime()) <= 0;
				currentDateCalendar.add(Calendar.MONTH, 1)) {

			WeatherElement currentDateWeather = new WeatherElement(currentDateCalendar.getTime());
			SortedSet<WeatherElement> currentDateTailSet = weatherElements.tailSet(currentDateWeather);

			if (currentDateTailSet.size() > 0) {

				currentDateWeather = currentDateTailSet.first();

				Calendar currentDateWeatherCal = Calendar.getInstance();
				currentDateWeatherCal.setTime(currentDateWeather.getDate());
				if (currentDateWeatherCal.get(Calendar.YEAR) != currentDateCalendar.get(Calendar.YEAR)) {
					LOGGER.error("No weather info found for this month"+ currentDateCalendar.getTime()+". Please update.");
					break;
				}

				Date sameMonthZeroYear = getSameMonthOfZeroYear(currentDateWeather.getDate());
				Date sameMonthNextYear = getSameMonthOfNextYear(currentDateWeather.getDate());
				SortedSet<WeatherElement> monthWeatherElementHistory = weatherElements.subSet(new WeatherElement(sameMonthZeroYear), new WeatherElement(sameMonthNextYear));
				Integer monthMeanOfMonthMeans = calculateMeanHistory(monthWeatherElementHistory);

				//Position to mean
				positionToMean(meanEventValue, endDate, currentDateWeather, monthMeanOfMonthMeans, eventListName);

				//Trend change detection
				trendChangeDetection(trendChangeEventValue, endDate, currentDateWeather, monthMeanOfMonthMeans, eventListName);

			} else {

				LOGGER.error("No month weather data for "+currentDateCalendar.getTime()+" in "+weatherElements+".\n Please update the weather data.");
			}
		}

		SortedMap<EventKey, EventValue> eventData = new TreeMap<EventKey, EventValue>();
		if (!meanEventValue.getEventType().equals(EventType.NONE)) {
			LOGGER.info("Weather mean temperature hint : " + meanEventValue);
			addEvent(eventData, meanEventValue.getDate(), meanEventValue.getEventDef(), meanEventValue.getEventType(), meanEventValue.getMessage(), eventListName, "mean");
		}

		if (!trendChangeEventValue.getEventType().equals(EventType.NONE)) {
			LOGGER.info("Weather trend change in temperature hint : " + trendChangeEventValue);
			addEvent(eventData, trendChangeEventValue.getDate(), trendChangeEventValue.getEventDef(), trendChangeEventValue.getEventType(), trendChangeEventValue.getMessage(), eventListName, "trend");
		}

		return eventData;
	}

	private void addEvent(Map<EventKey, EventValue> eventData, Date currentDate, EventInfo eventDefinition, EventType eventType, String message, String eventListName, String hint) {
		EventKey iek = new WeatherEventKey(currentDate, eventDefinition, eventType, hint);
		EventValue iev = new StandardEventValue(currentDate, eventDefinition, eventType, message, eventListName);
		eventData.put(iek, iev);
	}

	private Calendar firstDayOfPrevMonthOf(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar;
	}

	private Date getSameMonthOfNextYear(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.YEAR,1);
		return calendar.getTime();
	}

	private Date getSameMonthOfZeroYear(Date currentDate) {
		Calendar currentDateCal = Calendar.getInstance();
		currentDateCal.setTime(currentDate);

		Calendar zeroYearCal = Calendar.getInstance();
		zeroYearCal.setTime(DateFactory.dateAtZero());
		zeroYearCal.set(Calendar.MONTH, currentDateCal.get(Calendar.MONTH));
		Date zeroDate = zeroYearCal.getTime();
		return zeroDate;
	}

	private void trendChangeDetection(EventValue eventValue, Date endDate, WeatherElement currentDateWeather, Integer meanHistory, String eventListName) {

		Calendar previousTwoYearsCalendar = Calendar.getInstance();
		previousTwoYearsCalendar.setTime(currentDateWeather.getDate());
		previousTwoYearsCalendar.add(Calendar.YEAR, -2);

		try {
			SortedSet<WeatherElement> previousTwoYearsWeathers = weatherElements.subSet(new WeatherElement(previousTwoYearsCalendar.getTime()), currentDateWeather);
			Iterator<WeatherElement> iterator = previousTwoYearsWeathers.iterator();
			WeatherElement firstPrevious = iterator.next();
			WeatherElement secondPrevious = iterator.next();

			checkWeatherDataValidity(currentDateWeather, firstPrevious, secondPrevious);

			Integer firstPreviousTemp = firstPrevious.getAvgTemp();
			Integer secondPreviousTemp = secondPrevious.getAvgTemp();
			Integer currentTemp = currentDateWeather.getAvgTemp();

			if (firstPreviousTemp > secondPreviousTemp && secondPreviousTemp < currentTemp && secondPreviousTemp <= meanHistory) {//up reversal
				addTrendChangeEvent(eventValue, endDate, EventType.BULLISH, eventListName, currentDateWeather.getDate(), meanHistory, firstPreviousTemp, secondPreviousTemp, currentTemp, "Temperature reversal detected ");
			} else if (firstPreviousTemp < secondPreviousTemp && secondPreviousTemp > currentTemp && secondPreviousTemp >= meanHistory) {//down reversal
				addTrendChangeEvent(eventValue, endDate, EventType.BEARISH, eventListName, currentDateWeather.getDate(), meanHistory, firstPreviousTemp, secondPreviousTemp, currentTemp, "Temperature reversal detected ");
			} else {
				addTrendChangeEvent(eventValue, endDate, EventType.INFO, eventListName, currentDateWeather.getDate(), meanHistory, firstPreviousTemp, secondPreviousTemp, currentTemp, "Temperature RAS (we need : t0>t1<t2 and t1<Mean or t0<t1>t2 and t1>Mean) ");
			}
		} catch (Exception e) {
			LOGGER.error("No monthly weather history for " + endDate, e);
		}
	}

	private void checkWeatherDataValidity(WeatherElement currentDateWeather, WeatherElement firstPrevious, WeatherElement secondPrevious) throws Exception {

		Calendar  firstPreviousCal = Calendar.getInstance();
		firstPreviousCal.setTime(firstPrevious.getDate());
		Calendar  secondPreviousCal = Calendar.getInstance();
		secondPreviousCal.setTime(secondPrevious.getDate());
		Calendar  currentCal = Calendar.getInstance();
		currentCal.setTime(currentDateWeather.getDate());

		if (
				firstPreviousCal.get(Calendar.YEAR) +1 != secondPreviousCal.get(Calendar.YEAR) || secondPreviousCal.get(Calendar.YEAR) +1 != currentCal.get(Calendar.YEAR) ||
				firstPreviousCal.get(Calendar.MONTH) != secondPreviousCal.get(Calendar.MONTH) || secondPreviousCal.get(Calendar.MONTH) != currentCal.get(Calendar.MONTH)
			) {
			throw new Exception("Weather information is not up to date. Please update.");
		}
	}

	private void addTrendChangeEvent(EventValue eventValue, Date endDate, EventType eventType, String eventListName,
			Date firstDayOfPrevMonth, Integer meanHistory, Integer firstPreviousTemp, Integer middlePreviousTemp, Integer currentTemp, String msgPreamb) {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		DateFormat monthSF = new SimpleDateFormat("MMMMMMMMMMMMMM yyyy");

		String message = 
				eventType + " " + msgPreamb +
				"for last " + monthSF.format(firstDayOfPrevMonth) + ". " +
				"Previous are "+firstPreviousTemp+" , "+ middlePreviousTemp+". " +
				"Current is "+currentTemp+". " +
				"Mean is "+meanHistory+". " +
				"On the "+simpleDateFormat.format(endDate);

		addEventConcat(eventValue, eventType, message);
	}

	private void positionToMean(EventValue eventValue, Date endDate, WeatherElement currentDateWeather, Integer meanHistory, String eventListName) {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
		DateFormat monthSF = new SimpleDateFormat("MMMMMMMMMMMMMM yyyy");
		String trendHintMsg;
		if (currentDateWeather.getAvgTemp() < meanHistory) {
			trendHintMsg = "is Below historical mean";
		} else if (currentDateWeather.getAvgTemp() > meanHistory) {
			trendHintMsg = "is Above historical mean";
		} else {
			trendHintMsg = "is Equal to historical mean";
		}

		String message = "Last "+monthSF.format(currentDateWeather.getDate())+" avg temperature ("+currentDateWeather.getAvgTemp()+") "+trendHintMsg+" ("+meanHistory+") on the "+simpleDateFormat.format(endDate);
		addEventConcat(eventValue, EventType.INFO, message);

	}

	private Integer calculateMeanHistory(SortedSet<WeatherElement> monthWeatherElementHistory) {
		Integer meanMonthAvgHistory = 0, i = 0;
		for (WeatherElement weatherElement : monthWeatherElementHistory) {
			i++;
			meanMonthAvgHistory = meanMonthAvgHistory + weatherElement.getAvgTemp();
		}
		meanMonthAvgHistory = meanMonthAvgHistory/i;

		return meanMonthAvgHistory;
	}

	protected int getDaysSpan() {
		return 0;
	}

	private EventValue addEventConcat(EventValue concatenedEvtVal, EventType newEventType, String message) {

		concatenedEvtVal.setMessage(message+"\n"+concatenedEvtVal.getMessage());

		EventType prevEventType= concatenedEvtVal.getEventType();

		switch (prevEventType) {//event type super seeding : NONE, INFO, BULLISH/BEARISH
		case NONE : //NONE is super seeded by all others
			concatenedEvtVal.setEventType(newEventType);
			break;
		case INFO :
		case BULLISH :
		case BEARISH :
			if (!newEventType.equals(EventType.NONE)) {
				concatenedEvtVal.setEventType(newEventType);
			}
			break;
		default:
			throw new NotImplementedException(new Throwable());
		}

		return concatenedEvtVal;
	}

	@Override
	public  SortedMap<Date, double[]> calculationOutput() {
		return new TreeMap<Date, double[]>();
	}

	@Override
	public EventDefinition getEventDefinition() {
		return EventDefinition.WEATHER;
	}	

	@Override
	public EmailFilterEventSource getSource() {
		return EmailFilterEventSource.PMGlobalBuySell;
	}


	@Override
	public Integer getStartShift() {
		return 0;
	}

	@Override
	public ValidityFilter quotationsValidity() {
		return ValidityFilter.NONEVALID;
	}

}

