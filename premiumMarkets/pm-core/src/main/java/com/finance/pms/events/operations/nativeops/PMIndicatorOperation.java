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
import java.util.Arrays;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.talib.indicators.TalibIndicator;

@XmlSeeAlso({
	PMAroonOperation.class, PMMACDOperation.class, PMSMAOperation.class, PMLogRocOperation.class, PMMightyChaikinOperation.class, 
	FlipOperation.class, LeftShifterOperation.class, BandNormalizerOperation.class
	})
public abstract class PMIndicatorOperation extends DoubleMapOperation {

	protected PMIndicatorOperation() {
		super();
	}

	public PMIndicatorOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public PMIndicatorOperation(String reference, String description, Operation ... operands) {
		this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	protected DoubleMapValue doubleArrayMapToDoubleMap(Quotations quotations, TargetStockInfo targetStock, TalibIndicator talibIndicator, double[] outputs) {
		
		DoubleMapValue dateDoubleMap = new DoubleMapValue();
		for (int i = 0; i < talibIndicator.getOutNBElement().value; i++) {
			Date calculatorDate = quotations.getDate(i + talibIndicator.getOutBegIdx().value);
			Double output = outputs[i];
			dateDoubleMap.getValue(targetStock).put(calculatorDate, output);
		}
		
		return dateDoubleMap;
		
	}
	
	protected SortedMap<Date, double[]> doubleMapToDoubleArrayMap(TargetStockInfo targetStock, DoubleMapValue input) {
		
		SortedMap<Date, double[]> doubleArrayMap = new TreeMap<Date, double[]>();
		SortedMap<Date, Double> inputValue = input.getValue(targetStock);
		
		for (Date date : inputValue.keySet()) {
			doubleArrayMap.put(date, new double[]{inputValue.get(date)});
		}
			
		return doubleArrayMap;
	}

	protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest, int originIdx) {
		for (Date date : origin.keySet()) {
			dest.put(date, origin.get(date)[originIdx]);
		}
	}	
	
	protected void transOutput(SortedMap<Date, double[]> origin, SortedMap<Date, Double> dest) {
		transOutput(origin, dest, 0);
	}
	
	protected void transInput(SortedMap<Date, Double> origin, SortedMap<Date, double[]> dest) {
		for (Date date : origin.keySet()) {
			dest.put(date, new double[]{origin.get(date)});
		}
	}	

}
