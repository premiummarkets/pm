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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.MACD;

@XmlRootElement
public class PMMACDOperation extends PMDataFreeOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(PMMACDOperation.class);
	
	public PMMACDOperation() {
		super(
				"macd_", "House made MACD on close historical data",
				new NumberOperation("number","macdFastPeriod","Macd Fast period", new NumberValue(12.0)), 
				new NumberOperation("number","macdSlowPeriod","Macd Slow period",  new NumberValue(26.0)), 
				new NumberOperation("number", "macdSignalPeriod", "Macd Signal period",  new NumberValue(9.0))
			);
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"macd","history","signal"})));
	}
	
	public PMMACDOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	
	@Override
	public UnarableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer fastPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer signalPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		
		UnarableMapValue ret = new DoubleMapValue();
		try {
			MACD macd = new MACD(fastPeriod, slowPeriod, signalPeriod);
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(
					targetStock.getStock(),  getStartDate(targetStock.getStartDate(), thisStartShift), targetStock.getEndDate(), 
					true, targetStock.getStock().getMarketValuation().getCurrency(), 
					macd.getStartShift(), macd.quotationValidity());
			macd.calculateIndicator(quotations);
			
			if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("history")) {
				return doubleArrayMapToDoubleMap(quotations, targetStock, macd, macd.getHistory());
			}
			else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("signal")) {
				return doubleArrayMapToDoubleMap(quotations, targetStock, macd, macd.getSignal());
			} else {
				//macd
				return doubleArrayMapToDoubleMap(quotations, targetStock, macd, macd.getOutputData());
			}
			
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
