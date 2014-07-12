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
package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
public class AndDoubleMapCondition extends BooleanDoubleMapCondition {
	
	private Boolean exactDataSet;

	private AndDoubleMapCondition() {
		super("and", "Compare two boolean time series over time and is true when all are true and present.");
	}
	
	public AndDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}
	
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		Boolean lenient = Boolean.valueOf(((StringValue)inputs.get(0)).getValue(targetStock));
		exactDataSet = !lenient;
		return super.calculate(targetStock, inputs.subList(1, inputs.size()));
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Boolean>... ops) {
		for (Comparable<Boolean> op : ops) {
			if (op.compareTo(Boolean.TRUE) != 0) return false;
		}
		return true;
	}
	
	

	@Override
	protected Boolean shortcutUnary() {
		return true;
	}

	@Override
	protected Boolean exactDataSet() {
		return exactDataSet;
	}

}
