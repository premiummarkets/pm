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
import java.util.Optional;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
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
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

//TODO Dynamic criterias  (by inheritance or parameter?)
@SuppressWarnings("rawtypes")
/**
 * Matching discrete inputs by comparing end dates and lengths for every discrete occurrence against each other of the passed inputs.
 * So far only makes sense for binary comparison of Series (i.e. two Series as parameters).
 * Additional constraints :
 * 'spanning'. Does not make sense : As this condition is a status check in time not an event (change of status) check in time.
 * 'over'
 * 'for'
 * @author Gheeyom Thor
 *
 */
public class MatchingBooleanMapCondition extends DiscreteLinearOutputsCondition {

	protected static MyLogger LOGGER = MyLogger.getLogger(MatchingBooleanMapCondition.class);

	public MatchingBooleanMapCondition() {
		super("matching", "Compare boolean time series having multiple numeric outputs. Is true when the operands available numeric outputs match the criteria.");
		//				new NumberOperation("Criteria parameters"),
		//				new DoubleMapOperation("Historical data inputs"));
		//		this.getOperands().stream().forEach(o -> o.setIsVarArgs(true)); //FIXME multiple VarArgs is not supported?
	}

	public MatchingBooleanMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, int thisStartShift, List<? extends Value> inputs) {

		//Sof (sof is first because of the following constants VarArgs.
		//And
		//Constants
		List<Double> constantInputs = new ArrayList<>();
		int sofSize = 0;
		Integer overPeriod = 0;
		Integer forPeriod = 0;
		int i = 0;
		for(Value input : inputs) {
			if (input instanceof BooleanMapValue) break;
			switch(i) {
			case 0 :
				overPeriod = ((NumberValue) inputs.get(sofSize++)).getValue(targetStock).intValue();
				break;
			case 1 :
				forPeriod = ((NumberValue) inputs.get(sofSize++)).getValue(targetStock).intValue();
				break;
			default :
				constantInputs.add(((NumberValue) input).getValue(targetStock).doubleValue());
			}
			i++;
		}
		int constantInputSize = i - sofSize;
		if (overPeriod > 0 && forPeriod > 0) throw new UnsupportedOperationException("Setting both Over Period "+overPeriod+" and For Period "+forPeriod+" is not supported.");

		//Series
		int inputMapsStartIdx = sofSize + constantInputSize; //sof size + constants size
		
		//Unary check
		if (inputs.size() - inputMapsStartIdx == 1) { //Only one map was passed => return as is
			return (BooleanMapValue) inputs.get(inputMapsStartIdx);
		}
		
		List<BooleanMultiMapValue> checkedInputMaps = (List<BooleanMultiMapValue>) inputs.subList(inputMapsStartIdx, inputs.size());

		//Build cmp : for each inputs -> for each additional output : build a list of maps one per input. Each map being <Date, Additional output> with date being the end of each additional output.
		// input0, <<date01, List of addOutput01>, <date02, List of addOutput02>, ...>
		// input1, <<date11, List of  addOutput11>, <date12, List of addOutput12>, ...>
		List<TreeMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>>> inputMapsCmp = checkedInputMaps.stream().map(cinput ->
		cinput.getAdditionalOutputs().values().stream()
		.collect(
				Collectors.toMap(
						addInput -> addInput.getValue(targetStock).lastKey(),
						addInput -> {
							ComparableArray<ComparableSortedMap<Date, Double>> inputArray = new ComparableArray<>();
							inputArray.add(new ComparableSortedMap<Date, Double>(addInput.getValue(targetStock)));
							return inputArray;
						},
						(addInputA, addInputB) -> {
							addInputB.stream().forEach(e -> {if (!addInputA.contains(e)) addInputA.add(e);});
							return addInputA;
						},
						TreeMap::new
						)
				))
				.collect(Collectors.toList());

		BooleanMultiMapValue outputs = new BooleanMultiMapValue();
		BooleanMapValue realRowOutputs = new BooleanMapValue();
		SortedMap<Date, List<ComparableSortedMap<Date, Double>>> matchingsStore = new TreeMap<>();

		SortedSet<Date> fullKeySet;
		try {
			Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(
			        targetStock.getStock(), targetStock.getStartDate(thisStartShift), targetStock.getEndDate(),
			        true, targetStock.getStock().getMarketValuation().getCurrency(),
			        0, ValidityFilter.CLOSE);
			fullKeySet = new TreeSet(QuotationsFactories.getFactory().buildExactMapFromQuotationsClose(quotations).keySet());
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

		//condition check
		for(int refIdx = 0; refIdx < inputMapsCmp.size(); refIdx++) {//Going through each input taken as reference in turn
			for(Date date : inputMapsCmp.get(refIdx).keySet()) {//Going through each 'additional output maps' end date of the selected reference
				Comparable[] constNHeadMapArrayCmp = new Comparable[constantInputSize + inputMapsCmp.size() + 1]; //constants + input maps + return
				for(int ci = 0; ci < constantInputs.size(); ci++) constNHeadMapArrayCmp[ci] = constantInputs.get(ci); //Matching criteria constants
				constNHeadMapArrayCmp[constantInputSize] = new ComparableSortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>>(inputMapsCmp.get(refIdx).headMap(date, true)); //Reference input 'additional outputs maps' up to date
				int cmpIdx = constantInputSize + 1; //we start at constants size + one for the reference input.
				for(int chalIdx = 0; chalIdx < inputMapsCmp.size(); chalIdx++) {
					if (chalIdx != refIdx) {
						constNHeadMapArrayCmp[cmpIdx] = new ComparableSortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>>(inputMapsCmp.get(chalIdx).headMap(date, true)); //Challenging inputs 'additional outputs maps' up to date
						cmpIdx++;
					}
				}
				Comparable _Matching = new ComparableArray<ComparableSortedMap<Date, Double>>();
				constNHeadMapArrayCmp[constNHeadMapArrayCmp.length-1] = _Matching; //Adding the return parameter, array of <Date>.
				Boolean conditionCheck = conditionCheck(constNHeadMapArrayCmp);
				if (conditionCheck != null && conditionCheck) { //We output only if true

					outputs.getValue(targetStock).put(date, conditionCheck);
					//For
					if (overPeriod == 0 || outputs.getValue(targetStock).get(date) == null) {
						conditionCheck = forPeriodReduction(targetStock, fullKeySet, realRowOutputs, forPeriod, date, conditionCheck, outputs);
					}

					//Over
					overPeriodFilling(targetStock, fullKeySet, overPeriod, date, conditionCheck, outputs);

					if (conditionCheck) {
						//Sorting the matching back in order (with the reference map in its original place instead of 0) and storing.
						List<ComparableSortedMap<Date, Double>> matching = new ArrayList<>();
						int matchIdx = 1;
						for(int mapIdx = 0; mapIdx < inputMapsCmp.size(); mapIdx++) {
							if (mapIdx == refIdx) {
								matching.add(((ArrayList<ComparableSortedMap<Date, Double>>) _Matching).get(0));
							} else {
								matching.add(((ArrayList<ComparableSortedMap<Date, Double>>) _Matching).get(matchIdx));
								matchIdx++;
							}
						}
						matchingsStore.put(date, matching);
					}

				}
			}
		}

		if (true && !matchingsStore.isEmpty()) {
			final int fInputMapsStartIdx = inputMapsStartIdx;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			matchingsStore.entrySet().stream().forEachOrdered(e -> {
				String label = "matching" +
						" at " + dateFormat.format(e.getKey());
				IntStream.range(0, e.getValue().size()).forEachOrdered(index -> {
					outputs.getAdditionalOutputs().put(label + " of " + this.getOperands().get(fInputMapsStartIdx + index).getReference(), new DoubleMapValue(e.getValue().get(index)));
					outputs.getAdditionalOutputsTypes().put(label, Type.MULTI);
				});
			});
		}

		if (LOGGER.isInfoEnabled()) {
			SortedMap<Date, Boolean> outputValues = outputs.getValue(targetStock);
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
							outputValues.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).reduce((a, b) -> a + "\n" + b).orElse("No Data"));
			SortedMap<String, NumericableMapValue> addOutputs = new TreeMap(outputs.getAdditionalOutputs());
			LOGGER.info(
					"Condition '" + this.getReference() + "' returns this map \n" +
							addOutputs.entrySet().stream()
							.map(
									e -> "label : " + e.getKey()
									+ " : " + e.getValue().toString()
									).reduce((a, b) -> a + "\n" + b).orElse("No Data"));
		}

		return outputs;
	}

	/**
	 * @param constNHeadMapsCmp : constants..., reference, challengers..., _Matching
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean conditionCheck(Comparable... constNHeadMapsCmp) {
		List<ComparableSortedMap<Date, Double>> _Matching = (ArrayList<ComparableSortedMap<Date, Double>>) constNHeadMapsCmp[constNHeadMapsCmp.length-1]; //Return parameter
		return applyCriteria(_Matching, constNHeadMapsCmp);
	}

	/**
	 * This is the actual criteria algorithm
	 * Here matches :
	 * 	- end date of reference V challengers 'additional output map'
	 * 	- length  of reference V challengers 'additional output map'
	 * @param _Matching Return parameter, list of matching dates found.
	 */
	@SuppressWarnings("unchecked")
	protected Boolean applyCriteria(List<ComparableSortedMap<Date, Double>> _Matching, Comparable... constNHeadMapsCmp) {

		int firstTailMapIdx = 2;
		Double matchEndAlpha = (Double) constNHeadMapsCmp[0];
		Double matchLengthAlpha = (Double) constNHeadMapsCmp[1];

		SortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>> refMap = (SortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>>) constNHeadMapsCmp[firstTailMapIdx];
		Date refRightMost = refMap.lastKey();
		ComparableArray<ComparableSortedMap<Date, Double>> refComparableArray = refMap.get(refRightMost);
		Optional<ArrayList<ComparableSortedMap<Date, Double>>> foundMatching = refComparableArray.stream()
				.map(refRightMostAddOutput -> {
					ArrayList<ComparableSortedMap<Date, Double>> refMatching = new ArrayList<>();
					refMatching.add(refRightMostAddOutput);
					Date refLeftMost = refRightMostAddOutput.firstKey();
					Double refLength = Double.valueOf(refRightMost.getTime() - refLeftMost.getTime());
					for(int i = firstTailMapIdx +1; i < constNHeadMapsCmp.length-1; i++) { //Challengers Iteration: From after the reference Map Up to the return parameter.
						SortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>> chalMap = (SortedMap<Date, ComparableArray<ComparableSortedMap<Date, Double>>>) constNHeadMapsCmp[i];
						ListIterator<Date> chalIter = new ArrayList<>(chalMap.keySet()).listIterator(chalMap.size());
						ArrayList<ComparableSortedMap<Date, Double>> chalMatching = new ArrayList<>();
						while(chalIter.hasPrevious()) {
							Date chalRightMost = chalIter.previous();
							ComparableArray<ComparableSortedMap<Date, Double>> comparableArray = chalMap.get(chalRightMost);
							Boolean goneTooFarLeft = comparableArray.stream()
									.map(rightMostAddOutput -> {
										Date chalLeftMost = rightMostAddOutput.firstKey();
										Double chalLength = Double.valueOf(chalRightMost.getTime() - chalLeftMost.getTime());
										double maxLength = Math.max(Math.abs(chalLength), Math.abs(refLength));
										Boolean okRight = Double.valueOf(refRightMost.getTime() - chalRightMost.getTime())/maxLength < matchEndAlpha;
										if (okRight) {
											Boolean okLength = Math.abs(refLength - chalLength)/maxLength < matchLengthAlpha;
											if (okLength) {
												chalMatching.add(rightMostAddOutput); //Found a match
											}
											return false; //Not Gone too far left.
										} else {
											return true; //Gone too far left for the end criteria.
										}
									})
									.reduce((r, e) -> r || e) //Should be an && but does it matter as all the maps should end at the same date in this iteration?
									.orElse(false);
							if (goneTooFarLeft || !chalMatching.isEmpty()) break; //Too far left or a match was found, we give up the loop
						}

						if (chalMatching.isEmpty()) {
							break; //This challengers doesn't match invalidating the loop on challengers
						} else {
							refMatching.add(chalMatching.get(0)); //We add the first match for this challenger.
						}

					}
					return refMatching;
				})
				.filter(e -> e.size() == (constNHeadMapsCmp.length-1) - (firstTailMapIdx))
				.findFirst();

		_Matching.addAll(foundMatching.orElse(new ArrayList<>()));
		return _Matching.size() == (constNHeadMapsCmp.length-1) - (firstTailMapIdx);
	}

}
