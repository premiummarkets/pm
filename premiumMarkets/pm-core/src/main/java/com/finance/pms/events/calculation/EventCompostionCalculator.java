/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
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

import java.util.Date;
import java.util.Map;
import java.util.Observer;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventDefinition;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.dataresults.StandardEventKey;
import com.finance.pms.talib.dataresults.StandardEventValue;

public abstract class EventCompostionCalculator {
	
	protected Stock stock;
	private Quotations quotations;
	protected Integer calculationStartIdx;
	protected Integer calculationEndIdx;
	protected Observer[] observers;
	
	public EventCompostionCalculator(Stock stock, Date startDate, Date endDate, Currency calculationCurrency, Integer calculatorIndexShift) throws NotEnoughDataException {
		super();
		this.stock = stock;
		try {
			//TODO add dayspan to start shift
			this.quotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock, startDate, endDate, true, calculationCurrency, calculatorIndexShift + 15, 0);
			
		} catch (NoQuotationsException e) {
			throw new NotEnoughDataException(e.getMessage(),e);
		}
		this.calculationStartIdx =  this.getCalculatorQuotationData().getClosestIndexForDate(0, startDate);
		this.calculationEndIdx = this.getCalculatorQuotationData().getClosestIndexForDate(0, endDate);
		
	}
	
	public EventCompostionCalculator(Stock stock, Date startDate, Date endDate, Currency calculationCurrency, Observer... observers) throws NotEnoughDataException {
		this(stock, startDate, endDate, calculationCurrency, 0);
		this.observers = observers;
	}
	
	public EventCompostionCalculator(Stock stock) { //No quotation related calculator
		this.stock = stock;
	}
	
	public abstract void cleanEventsFor(String eventListName, Date datedeb, Date datefin, Boolean persist);

	public abstract Map<EventKey, EventValue> calculateEventsFor(String eventListName);
	
	protected Quotations getCalculatorQuotationData() {
		return this.quotations;
	}

	protected void addEvent(Map<EventKey, EventValue> eventData, Date currentDate, EventDefinition eventDefinition, EventType eventType, String message, String eventListName) {
		StandardEventKey iek = new StandardEventKey(currentDate, eventDefinition, eventType);
		EventValue iev = new StandardEventValue(currentDate, eventType, eventDefinition, message, eventListName);
		eventData.put(iek, iev);
	}

	protected abstract int getDaysSpan();
	
	public abstract SortedMap<Date, double[]> calculationOutput();
	
	public abstract EventDefinition getEventDefinition();

}
