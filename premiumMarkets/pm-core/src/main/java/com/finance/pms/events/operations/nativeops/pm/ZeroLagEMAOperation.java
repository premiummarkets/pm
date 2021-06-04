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
package com.finance.pms.events.operations.nativeops.pm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.PMWithDataOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.scoring.functions.ZeroLagEMASmoother;

public class ZeroLagEMAOperation extends PMWithDataOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(ZeroLagEMAOperation.class);

	private static final int DATAINPUTIDX = 2;

	public ZeroLagEMAOperation() {
		super("zeroLagEMA", "Zero Lag EMA",
				new NumberOperation("number", "period", "EMA period", new NumberValue(14.0)),
				new StringOperation("boolean","doFixlag","artificially fix the SMA lag", new StringValue("FALSE")),
				new DoubleMapOperation("input data"));
	}

	public ZeroLagEMAOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public ZeroLagEMAOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Boolean fixLag = Boolean.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(DATAINPUTIDX)).getValue(targetStock);

		//Calc
		NumericableMapValue ret = new DoubleMapValue();
		try {

			if (data.size() < period) throw new NotEnoughDataException(targetStock.getStock(), data.size() + "<" + period, null);

			ZeroLagEMASmoother smaSmoother = new ZeroLagEMASmoother(period);
			return new DoubleMapValue(smaSmoother.sSmooth(data, fixLag));

		} catch (NotEnoughDataException e) {
			LOGGER.warn(e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " + e, e);
		}

		return ret;
	}

	@Override
	public int operandsRequiredStartShift() {
		return ((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue()*7/5;
	}
}
