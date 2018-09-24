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
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.scoring.functions.HighLowSolver;
import com.finance.pms.events.scoring.functions.Line;
import com.finance.pms.events.scoring.functions.SmoothHighLowSolver;

public class LowerHighCondition extends HighsAndLowsCondition {

	HighLowSolver highLowSolver = new SmoothHighLowSolver();

	public LowerHighCondition() {
		super("lower high",  "True when the time series is making a lower high.");
	}

	public LowerHighCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Boolean conditionCheck(Comparable ... ops) {
		return highLowSolver.lowerHigh(
				(SortedMap<Integer, Double>) ops[0], (Integer) ops[1], (Integer) ops[2], (SortedMap<Integer, Double>) ops[3], ((Line<Integer, Double>) ops[4]),
				(Double) ops[5], (Double) ops[6], (Double) ops[7], (Double) ops[8],
				(Double) ops[9], (Double) ops[10]);

	}

}
