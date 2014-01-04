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
package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.TalibSmaSmoother;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;

@XmlRootElement
public class TalibSmaOperation extends TalibOperation {
	
	public TalibSmaOperation() {
		super("sma__", "SMA on any on any historical data series.",  
				new NumberOperation("number","smaPeriod","SMA period", new NumberValue(200.0)), 
				new StringOperation("boolean","doFixlag","artificially fix the SMA lag", new StringValue("FALSE")), 
			    new DoubleMapOperation());
	}
	
	public TalibSmaOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	protected SortedMap<Date, Double> innerCalculation(TargetStockInfo targetStock, MInteger outBegIdx, MInteger outNBElement, @SuppressWarnings("rawtypes") List<? extends Value> inputs) throws TalibException {

		//Param check
		Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Boolean fixLag = Boolean.valueOf(((StringValue)inputs.get(1)).getValue(targetStock));
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		SortedMap<Date, Double> smoothed = null;
		if (data.size() > period) {
			TalibSmaSmoother smaSmoother = new TalibSmaSmoother(period);
			smoothed = smaSmoother.sSmooth(data, fixLag);
		} else {
			smoothed = new TreeMap<Date, Double>();
		}

		return smoothed;
	}
	
	@Override
	public int operationStartDateShift() {
		return (((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue() + getOperands().get(2).operationStartDateShift())*7/5;
	}

}
