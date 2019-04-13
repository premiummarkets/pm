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

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.MapValue;

@XmlSeeAlso({AndBooleanMapCondition.class, OrBooleanMapCondition.class, NotBooleanMapCondition.class, TruthOfCondition.class})
public abstract class BooleanMapCondition extends Condition<Comparable<?>> {

	protected static MyLogger LOGGER = MyLogger.getLogger(BooleanMapCondition.class);

	protected BooleanMapCondition() {
		super();
	}

	public BooleanMapCondition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public BooleanMapCondition(String reference, String description, Operation... operands) {
		super(reference, description, operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Params check
		int i = 0;
		List<Comparable<?>> constants = new ArrayList<>();
		while (!(inputs.get(i) instanceof MapValue<?>)) {
			constants.add((Comparable<?>)inputs.get(i).getValue(targetStock));
			i++;
		}
		@SuppressWarnings("unchecked") List<MapValue<Boolean>> checkedInputs = (List<MapValue<Boolean>>) inputs.subList(i, inputs.size());

		if (shortcutUnary() && checkedInputs.size() == 1) {
			return (BooleanMapValue) checkedInputs.get(i);
		}

		List<SortedMap<Date, Boolean>> maps = new ArrayList<>();
		SortedSet<Date> fullKeySet = new TreeSet<>();
		for (Value<SortedMap<Date, Boolean>> input : checkedInputs) {
			fullKeySet.addAll(input.getValue(targetStock).keySet());
			maps.add(input.getValue(targetStock));
		}

		BooleanMapValue outputs = new BooleanMapValue();

		for (Date date : fullKeySet) {

			Boolean gruyereDetected = false;
			List<Boolean> currentOps = new ArrayList<>();
			for (SortedMap<Date, Boolean> map : maps) {
				Boolean currentOp = map.get(date);
				if (currentOp != null) {
					currentOps.add(currentOp);
				}
				else if (exactDataSet()) {
					gruyereDetected = true;
					//Detection of on element in an operand being not present at date in an other operand
					//Will result in an empty output when the comparison is required exactDataSet true.
					//ex : NOT, !linient AND
					//Otherwise the present elements will be compared ignoring the missing operand element.
					//ex : OR
					break;
				}
			}

			if (!gruyereDetected) {
				//If the exactDataSet requirement is false (no strict comparison),
				// we compare elements of operands even if not all are present at a given date.
				List<Comparable<?>> condCheckParams = new ArrayList<>();
				condCheckParams.addAll(constants);
				condCheckParams.addAll(currentOps);
				@SuppressWarnings("unchecked") Boolean conditionCheck = conditionCheck(condCheckParams.toArray(new Comparable[0]));
				if (conditionCheck != null) {
					outputs.getValue(targetStock).put(date, conditionCheck);
				}
			}

		}

		if (LOGGER.isDebugEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
					outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
		}

		return outputs;
	}

	protected abstract Boolean shortcutUnary();

	protected abstract Boolean exactDataSet();


}
