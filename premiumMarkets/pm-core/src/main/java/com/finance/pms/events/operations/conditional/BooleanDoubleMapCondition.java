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
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({AndDoubleMapCondition.class, OrDoubleMapCondition.class, NotDoubleMapCondition.class})
public abstract class BooleanDoubleMapCondition extends Condition<Boolean> {
	
	protected BooleanDoubleMapCondition() {
		super();
	}

	public BooleanDoubleMapCondition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public BooleanDoubleMapCondition(String reference, String description, Operation... operands) {
		super(reference, description, operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		if (shortcutUnary() && inputs.size() == 1) return (BooleanMapValue) inputs.get(0);
		@SuppressWarnings("unchecked") List<Value<SortedMap<Date, Boolean>>> checkedInputs = (List<Value<SortedMap<Date, Boolean>>>)inputs;
		
		List<SortedMap<Date, Boolean>> maps = new ArrayList<SortedMap<Date,Boolean>>();
		SortedSet<Date> fullKeySet = new TreeSet<Date>();
		for (Value<SortedMap<Date, Boolean>> input : checkedInputs) {
			fullKeySet.addAll(input.getValue(targetStock).keySet());
			maps.add(input.getValue(targetStock));
		}
		
		BooleanMapValue outputs = new  BooleanMapValue();

		for (Date date : fullKeySet) {
			
			Boolean gruyere = false;
			List<Boolean> currentOps = new ArrayList<Boolean>();
			for (SortedMap<Date, Boolean> map : maps) {
				Boolean currentOp = map.get(date);
				if (currentOp != null) {
					currentOps.add(currentOp);
				} 
				else if (exactDataSet()) {
						gruyere = true;
						break; 
				}
			}
			
			if (!gruyere) {
				Boolean conditionCheck = conditionCheck(currentOps.toArray(new Boolean[0]));
				if (conditionCheck != null) {
					outputs.getValue(targetStock).put(date, conditionCheck);
				}
			}
			
		}
		
		return outputs;
	}
	
	protected abstract Boolean shortcutUnary();
	
	protected abstract Boolean exactDataSet();

	
}
