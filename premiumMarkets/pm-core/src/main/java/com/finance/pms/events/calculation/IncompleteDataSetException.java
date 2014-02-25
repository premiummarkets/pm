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
package com.finance.pms.events.calculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.EventInfo;
import com.finance.pms.events.SymbolEvents;

public class IncompleteDataSetException extends Exception {

	private static final long serialVersionUID = 8415012484395864898L;

	private List<Stock> failingStocks;
	private Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> calculatedOutput;
	private Set<EventCompostionCalculator> validEventCalculators;
	private List<SymbolEvents> symbolEvents;
	
	public IncompleteDataSetException(Stock failingStock, SymbolEvents symbolEventsForStock, String arg0) {
		super(arg0);
		this.failingStocks = new ArrayList<Stock>();
		this.failingStocks.add(failingStock);
		this.calculatedOutput = new HashMap<Stock, Map<EventInfo, SortedMap<Date,double[]>>>();
		this.calculatedOutput.put(failingStock, symbolEventsForStock.getCalculationOutput());
		this.symbolEvents = new ArrayList<SymbolEvents>();
		this.symbolEvents.add(symbolEventsForStock);
	}

	public IncompleteDataSetException(List<Stock> failingStocks, List<SymbolEvents> symbolEvents, Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> calculatedOutput, String arg0) {
		super(arg0);
		this.failingStocks = failingStocks;
		this.calculatedOutput = calculatedOutput;
		this.symbolEvents = symbolEvents;
	}

	public IncompleteDataSetException(Stock failingStock, Set<EventCompostionCalculator> validEventCaclulators, String error) {
		super(error);
		this.failingStocks = new ArrayList<Stock>();
		this.failingStocks.add(failingStock);
		this.validEventCalculators = validEventCaclulators;
	}

	public List<Stock> getFailingStocks() {
		return failingStocks;
	}

	public Map<Stock, Map<EventInfo, SortedMap<Date, double[]>>> getCalculatedOutput() {
		return calculatedOutput;
	}

	public Set<EventCompostionCalculator> getValidEventCalculators() {
		return validEventCalculators;
	}

	public List<SymbolEvents> getSymbolEvents() {
		return symbolEvents;
	}

}
