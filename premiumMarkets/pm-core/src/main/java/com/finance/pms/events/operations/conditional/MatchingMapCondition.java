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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.ComparableArray;
import com.finance.pms.datasources.ComparableSortedMap;
import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;

//TODO The input has to be a BooleanMultiMapValue.
@SuppressWarnings("rawtypes")
/**
 * So far only makes sense for binary comparison of Series (i.e. two Series as parameters).
 * 
 * @author Gheeyom Thor
 *
 */
public class MatchingMapCondition extends Condition<Comparable> implements LinearOutputs {

	protected static MyLogger LOGGER = MyLogger.getLogger(MatchingMapCondition.class);

	protected MatchingMapCondition() {
		super("matching", "Compare boolean time series also having multiple numeric outputs and is true when the operands numeric outputs match the criteria.");
	}

	public MatchingMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		//Constants
		int constantInputSize = 0;
		List<Double> constantInputs = new ArrayList<>();
		for(Value input : inputs) {
			if (input instanceof BooleanMapValue) break;
			constantInputSize++;
			constantInputs.add(((NumberValue) input).getValue(targetStock).doubleValue());
		}

		//Series
		if (inputs.size() == constantInputSize + 1) {
			return (BooleanMapValue) inputs.get(constantInputSize);
			//			//FIXME TargetStockInfo.populateChartedOutputGroups
			//			//We need to recreate the input into an BooleanMapValue object as an inherited BooleanMultiMapValue would break the graph adding unrequested chartings (or ..)
			//			BooleanMapValue booleanMapValue = new BooleanMapValue();
			//			booleanMapValue.getValue(targetStock).putAll(((BooleanMapValue) inputs.get(firstSeriesIdx)).getValue(targetStock));
			//			return booleanMapValue;
		}
		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		SortedMap<Date, List<SortedMap<Date, Double>>> matchingsStore = new TreeMap<>();

		List<BooleanMultiMapValue> checkedInputMaps = (List<BooleanMultiMapValue>) inputs.subList(constantInputSize, inputs.size());

		//Build cmp
		List<TreeMap<Date, ComparableSortedMap<Date, Double>>> inputMapsCmp = checkedInputMaps.stream().map(cinput ->
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

		//condition check
		for(int refIdx = 0; refIdx < inputMapsCmp.size(); refIdx++) {
			for(Date date : inputMapsCmp.get(refIdx).keySet()) {
				Comparable[] constNHeadMapArrayCmp = new Comparable[constantInputSize + inputMapsCmp.size() + 1];
				for(int ci = 0; ci < constantInputs.size(); ci++) constNHeadMapArrayCmp[ci] = constantInputs.get(ci); //constants
				constNHeadMapArrayCmp[constantInputSize] = new ComparableSortedMap(inputMapsCmp.get(refIdx).headMap(date, true)); //reference
				int cmpIdx = constantInputSize + 1; //we start at constants size + one reference
				for(int chalIdx = 0; chalIdx < inputMapsCmp.size(); chalIdx++) {
					if (chalIdx != refIdx) {
						constNHeadMapArrayCmp[cmpIdx] = new ComparableSortedMap(inputMapsCmp.get(chalIdx).headMap(date, true));
						cmpIdx++;
					}
				}
				Comparable _Matching = new ComparableArray<>();
				constNHeadMapArrayCmp[constNHeadMapArrayCmp.length-1] = _Matching;
				Boolean conditionCheck = conditionCheck(constNHeadMapArrayCmp);
				if (conditionCheck != null) {

					outputs.getValue(targetStock).put(date, conditionCheck);

					if (conditionCheck) {
						//Sorting the matching back in order and storing
						List<SortedMap<Date, Double>> matching = new ArrayList<>();
						int matchIdx = 1;
						for(int mapIdx = 0; mapIdx < inputMapsCmp.size(); mapIdx++) {
							if (mapIdx == refIdx) {
								matching.add(inputMapsCmp.get(mapIdx).get(((ArrayList<Date>) _Matching).get(0)));
							} else {
								matching.add(inputMapsCmp.get(mapIdx).get(((ArrayList<Date>) _Matching).get(matchIdx)));
								matchIdx++;
							}
						}
						matchingsStore.put(date, matching);
					}

				}
			}
		}

		if (true && !matchingsStore.isEmpty()) {
			final int fConstantInputSize = constantInputSize;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			matchingsStore.entrySet().stream().forEachOrdered(e -> {
				String label = "matching" +
						" at " + dateFormat.format(e.getKey());
						//+ " of " + this.getOperands().subList(fConstantInputSize, this.getOperands().size()).stream().map(o -> o.getReference()).reduce((r, oe) -> r + " and " + oe);
				IntStream.range(0, e.getValue().size()).forEachOrdered(index -> {
					outputs.getAdditionalOutputs().put(label + " of " + this.getOperands().get(fConstantInputSize + index).getReference(), new DoubleMapValue(e.getValue().get(index)));
					outputs.getAdditionalOutputsTypes().put(label, Type.MULTI);
				});
			});
		}

		if (LOGGER.isInfoEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
							outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).get());
			SortedMap<String, NumericableMapValue> addOutputs = new TreeMap(outputs.getAdditionalOutputs());
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
							addOutputs.entrySet().stream()
							.map(
								e -> "label : " + e.getKey()
								+ " : " + e.getValue().toString()
								//+ " :\n" + e.getValue().getValue(null).entrySet().stream().map(t -> t.getKey() + "=" + t.getValue()).reduce((a, b) -> a + "\n" + b).get()
							).reduce((a, b) -> a + "\n" + b).get());
		}

		return outputs;
	}

	/**
	 * @param constNHeadMapsCmp : constants, .., reference, challengers .., _Matchings
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean conditionCheck(Comparable... constNHeadMapsCmp) {

		List<Date> _Matching = (ArrayList<Date>) constNHeadMapsCmp[constNHeadMapsCmp.length-1];

		int firstTailMapIdx = 2;
		Double matchEndAlpha = (Double) constNHeadMapsCmp[0];
		Double matchLengthAlpha = (Double) constNHeadMapsCmp[1];

		SortedMap<Date, ComparableSortedMap<Date, Double>> refMap = (SortedMap<Date, ComparableSortedMap<Date, Double>>) constNHeadMapsCmp[firstTailMapIdx];
		Date refRightMost = refMap.lastKey();
		_Matching.add(refRightMost);
		Date refLeftMost = refMap.get(refMap.lastKey()).firstKey();
		Double refLength = Double.valueOf(refRightMost.getTime() - refLeftMost.getTime());
		for(int i = firstTailMapIdx +1; i < constNHeadMapsCmp.length-1; i++) {
			SortedMap<Date, ComparableSortedMap<Date, Double>> chalMap = (SortedMap<Date, ComparableSortedMap<Date, Double>>) constNHeadMapsCmp[i];
			ListIterator<Date> chalIter = new ArrayList<>(chalMap.keySet()).listIterator(chalMap.size());
			Boolean okRight = false;
			Boolean okLength = false;
			Date chalRightMost = null;
			while(chalIter.hasPrevious()) {
				chalRightMost = chalIter.previous();
				Date chalLeftMost = chalMap.get(chalRightMost).firstKey();
				Double chalLength = Double.valueOf(chalRightMost.getTime() - chalLeftMost.getTime());
				double maxLength = Math.max(Math.abs(chalLength), Math.abs(refLength));

				okRight = Double.valueOf(refRightMost.getTime() - chalRightMost.getTime())/maxLength < matchEndAlpha;
				if (okRight) {
					okLength = Math.abs(refLength - chalLength)/maxLength < matchLengthAlpha;
					if (okLength) break; //Found
				} else break; //Gone too far left : Not found
			}
			if (!okRight || !okLength) return false;
			_Matching.add(chalRightMost);
		}
		return true;

	}

}
