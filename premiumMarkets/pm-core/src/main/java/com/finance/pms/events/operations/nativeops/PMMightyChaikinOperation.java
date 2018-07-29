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
package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.EventKey;
import com.finance.pms.events.EventType;
import com.finance.pms.events.EventValue;
import com.finance.pms.events.calculation.ChaikinOscillatorDivergence_old;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.TalibException;

@XmlRootElement
public class PMMightyChaikinOperation extends PMDataFreeOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(PMMightyChaikinOperation.class);
	
	public PMMightyChaikinOperation() {
		super("chaikin_", "Chaikin indicator house made", 
				new NumberOperation("number","Fast Period","Not implemented - Chaikin fast period", new NumberValue(3.0)),
				new NumberOperation("number","Slow Period","Not implemented - Chaikin slow period", new NumberValue(10.0)));
	}
	
	public PMMightyChaikinOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer fastPeriod = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();

		DoubleMapValue ret = new DoubleMapValue();
		try {
			ChaikinOscillatorDivergence_old mChaikin = new ChaikinOscillatorDivergence_old(fastPeriod, slowPeriod);
			
			Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(
					targetStock.getStock(), getStartDate(targetStock.getStartDate(), thisStartShift), targetStock.getEndDate(), 
					true, targetStock.getStock().getMarketValuation().getCurrency(),
					mChaikin.getStartShift(), mChaikin.quotationsValidity());
			
			SortedMap<EventKey, EventValue> eventsFor = mChaikin.calculateEventsFor(quotationsInstance, "inMem"+this.getClass().getSimpleName()+"Operation ");
			
			DoubleMapValue buySellEvents = new DoubleMapValue();
			for (EventKey eventKey : eventsFor.keySet()) {
				if (eventKey.getEventType().equals(EventType.BULLISH)) {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),-100.0);
				} 
				else if (eventKey.getEventType().equals(EventType.BEARISH)) {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),+100.0);
				} 
				else {
					buySellEvents.getValue(targetStock).put(eventKey.getDate(),0.0);
				}
			}
			return buySellEvents;
			
		} catch (NoQuotationsException e) {
			LOGGER.warn(e);
		} catch (TalibException e) {
			LOGGER.warn(e);
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
