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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.EventDataValue;

public class OperationValue<T> extends Value<T> implements Cloneable  {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EventDataValue.class);
	
	Operation operation;

	public OperationValue(Operation operation) {
		super();
		this.operation = operation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue(TargetStockInfo targetStock) {
		ArrayList<Value<T>> emptyInputs = new ArrayList<Value<T>>(); //parameterised operation has no input params
		return (T) operation.calculate(targetStock, 0, emptyInputs); //XXX what is this?
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() +" : operation "+operation.toString();
	}
	
	@Override
	public Object clone() {
		try {
			@SuppressWarnings("unchecked")
			OperationValue<T> clone = (OperationValue<T>) super.clone();
			clone.operation = (Operation) this.operation.clone();
			return clone;
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return null;
	}


}
