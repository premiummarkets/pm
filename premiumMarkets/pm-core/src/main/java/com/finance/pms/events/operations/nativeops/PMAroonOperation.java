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
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.houseIndicators.HouseAroon;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.talib.indicators.TalibException;

@XmlRootElement
public class PMAroonOperation extends PMDataFreeOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(PMAroonOperation.class);
	
	public PMAroonOperation() {
		super("aroon_", "Aroon indicator house made", new NumberOperation("number","aroonPeriod","Aroon period", new NumberValue(25.0)));
		setAvailableOutputSelectors(new ArrayList<String>( Arrays.asList(new String[]{"down","up"})));
	}
	
	public PMAroonOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();

		DoubleMapValue ret = new DoubleMapValue();
		try {
			
			//HouseAroon aroon = new HouseAroon(targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), null, period);
			HouseAroon arron = new HouseAroon(period);
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(
					targetStock.getStock(), targetStock.getStartDate(), targetStock.getEndDate(), 
					true, targetStock.getStock().getMarketValuation().getCurrency(), 
					arron.getStartShift(), arron.quotationValidity());
			arron.calculateIndicator(quotations);
			
			if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("down")) {
				return doubleArrayMapToDoubleMap(quotations, targetStock, arron, arron.getOutAroonDown());
			}
			else if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("up")) {
				return doubleArrayMapToDoubleMap(quotations, targetStock, arron, arron.getOutAroonUp());
			} else {
				//error
				return doubleArrayMapToDoubleMap(quotations, targetStock, arron, arron.getOutputData());
			}
			
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
