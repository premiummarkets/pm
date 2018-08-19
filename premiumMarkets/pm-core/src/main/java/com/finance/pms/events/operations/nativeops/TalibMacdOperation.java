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
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.talib.indicators.TalibCoreService;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

@XmlRootElement
public class TalibMacdOperation extends TalibOperation {
	
	public TalibMacdOperation() {
		super(
				"macd__", "Macd on any historical data series.", 
				new NumberOperation("number","macdFastPeriod","macd Fast period", new NumberValue(12.0)), 
				new NumberOperation("number","macdSlowPeriod","macd Slow period",  new NumberValue(26.0)), 
				new NumberOperation("number", "macdSignalPeriod", "macd Signal period",  new NumberValue(9.0)),
				new DoubleMapOperation()
			 );
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"macd","signal"})));
	}
	
	public TalibMacdOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {

		//Param check
		Integer fastPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		Integer slowPeriod = ((NumberValue) inputs.get(1)).getValue(targetStock).intValue();
		Integer signalPeriod = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(3)).getValue(targetStock);

		//Calc
		int endIdx = data.size()-1;
		int startIdx = 0;

		double[] inReal = mapToArray(data);
		double[] macd = new double[inReal.length - slowPeriod +1];
		double[] signal = new double[inReal.length - slowPeriod +1];
		
		double[] history = new double[inReal.length - slowPeriod +1];
		RetCode rc = TalibCoreService.getCore().macd(startIdx, endIdx, inReal, fastPeriod, slowPeriod, signalPeriod, outBegIdx, outNBElement, macd, signal, history);
		if (!rc.equals(RetCode.Success)) throw new TalibException("", new Exception());

		Set<Date> dateKeySet = data.keySet();
	
		if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("signal")) {
			return arrayToMap(dateKeySet, signal, outBegIdx.value);
		} else {
			return arrayToMap(dateKeySet, macd, outBegIdx.value);
		}
		
	}

	@Override
	public int operationStartDateShift() {
		return (
				((NumberValue)getOperands().get(1).getParameter()).getValue(null).intValue() +
				((NumberValue)getOperands().get(2).getParameter()).getValue(null).intValue()
		)*7/5;
	}
	
	

}
