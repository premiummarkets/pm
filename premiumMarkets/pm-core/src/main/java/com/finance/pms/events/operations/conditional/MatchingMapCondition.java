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
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.ComparableSortedMap;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

//TODO The input has to be a BooleanMultiMapValue.
@SuppressWarnings("rawtypes")
public class MatchingMapCondition extends Condition<Comparable> {

	protected static MyLogger LOGGER = MyLogger.getLogger(MatchingMapCondition.class);
	private double matchEndAlpha = 5;
	private double matchLengthAlpha = 5;

	protected MatchingMapCondition() {
		super("matches", "Compare boolean time series also having multiple numeric outputs and is true when the operands numeric outputs match the criteria.");
	}

	public MatchingMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		BooleanMapValue outputs = new BooleanMapValue();

		List<BooleanMultiMapValue> checkedInputs = (List<BooleanMultiMapValue>) inputs;

		List<TreeMap<Date, ComparableSortedMap<Date, Double>>> inputsCmp = checkedInputs.stream().map(cinput ->
			cinput.getAdditionalOutputs().values().stream()
			.collect(
					Collectors.toMap(
							addInput -> addInput.getValue(targetStock).lastKey(),
							addInput -> new ComparableSortedMap<Date, Double>(addInput.getValue(targetStock)),
							(addInputA, addInputB) -> addInputA,
							TreeMap::new
					)
			))
			.collect(Collectors.toList());
		
		for(int i = 0; i < inputsCmp.size(); i++) {
			//SortedMap<Date, ComparableSortedMap<Date, Double>> treeMap = inputsCmp.get(i);
			for(Date date : inputsCmp.get(i).keySet()) {
				ComparableSortedMap[] tailMapArrayCmp = new ComparableSortedMap[inputsCmp.size()];
				for(int j = 0; j < inputsCmp.size(); j++) {
					tailMapArrayCmp[j] = new ComparableSortedMap(inputsCmp.get(j).tailMap(date));
					Boolean conditionCheck = conditionCheck(tailMapArrayCmp);
					if (conditionCheck != null) {
						outputs.getValue(targetStock).put(date, conditionCheck);
					}
				}
			}
		}

		if (LOGGER.isInfoEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
					outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
		}

		return outputs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean conditionCheck(Comparable... tailMapsCmp) {
		SortedMap<Date, ComparableSortedMap<Date, Double>> refMap = (SortedMap<Date, ComparableSortedMap<Date, Double>>) tailMapsCmp[0];
		Date refRightMost = refMap.lastKey();
		Date refLeftMost = refMap.get(refMap.lastKey()).firstKey();
		Double refLength = (refRightMost.getTime() - refLeftMost.getTime())/LinearOutputs.DAY_IN_MILLI;
		for(int i = 1; i < tailMapsCmp.length; i++) {
			SortedMap<Date, ComparableSortedMap<Date, Double>> chalMap = (SortedMap<Date, ComparableSortedMap<Date, Double>>) tailMapsCmp[i];
			ListIterator<Date> chalIter = new ArrayList<>(chalMap.keySet()).listIterator(chalMap.size());
			Boolean okRight = false;
			Boolean okLength = false;
			while(chalIter.hasPrevious()) {
				Date chalRightMost = chalIter.previous();
				okRight = (refRightMost.getTime() - chalRightMost.getTime())/LinearOutputs.DAY_IN_MILLI < matchEndAlpha;
				if (okRight) {
					Date chalLeftMost = chalMap.get(chalRightMost).firstKey();
					Double chalLength = (chalRightMost.getTime() - chalLeftMost.getTime())/LinearOutputs.DAY_IN_MILLI;
					okLength = Math.abs(refLength - chalLength) < matchLengthAlpha;
					if (okLength) break;
				} else break;
			}
			if (!okRight || !okLength) return false;
		}
		return true;
	}

}
