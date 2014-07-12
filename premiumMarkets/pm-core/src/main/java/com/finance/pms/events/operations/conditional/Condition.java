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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

/**
 * 
 * @author Guillaume Thoreton
 * Some conditions can (when it makes sense) be followed by key words changing the result :
 * 'over n days' : means that the condition happened once over the past n days. It could as well not be fulfilled a the date
 * 'for n days' : means that the condition was true for n days in a row.
 * 'spanning n days' : will be used for condition involving events happening over time like when comparing two status of the data at two point in time t and t-n. 
 * 	For instance 'close crosses up 10 spanning 3 days' means that close was below 10 three days ago and close is now above 10 
 * These two can be combined like for instance :
 *  	close is above 10 over 30 days for 2 days
 *   	close is above 10 over 10 days for 10 days
 *   	goes up for 10 days spanning 2 days
 *   ...
 *   Note 'days' here mean days calendar open days not quotation days.
 */

@XmlRootElement
@XmlSeeAlso({ NullCondition.class, BooleanDoubleMapCondition.class, CmpDoubleMapCondition.class, CrossConstantCondition.class, CrossDoubleMapCondition.class, HighsAndLowsCondition.class})
public class Condition<T> extends Operation {	
	
	protected Condition() {
		super();
	}
	
	public Condition(String reference) {
		super(reference, reference);
	}

	public Condition(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}

	public Condition(String reference, String description, Operation... operands) {
		this(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<T> ... ops) {
		return false;
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new BooleanMapValue();
	}

	@Override //TODO add shift inherent to over, for and spanning
	public int operationStartDateShift() {
		int maxDateShift = 0;
		for (Operation operand : getOperands()) {
			maxDateShift = Math.max(maxDateShift, operand.operationStartDateShift());
		}
		return maxDateShift;
	}
}
