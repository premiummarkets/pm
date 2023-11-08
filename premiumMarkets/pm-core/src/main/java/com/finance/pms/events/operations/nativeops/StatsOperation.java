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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.MySimpleRegression;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.StatsFunction;

@XmlRootElement
public class StatsOperation extends PMWithDataOperation implements MultiValuesOutput {

	private final class SpecificStatsFunction implements StatsFunction {
		
		private final Operation specificOperation;
		private final String thisCallStack;
		private final int thisInputOperandsRequiredShiftFromThis;
		private final TargetStockInfo targetStock;
		private final String outputSelector;
		
		private List<String> doEvalColRefs;

		private SpecificStatsFunction(Operation specificOperation, String thisCallStack, int thisInputOperandsRequiredShiftFromThis, TargetStockInfo targetStock, String outputSelector) {
			this.specificOperation = specificOperation;
			this.thisCallStack = thisCallStack;
			this.thisInputOperandsRequiredShiftFromThis = thisInputOperandsRequiredShiftFromThis;
			this.targetStock = targetStock;
			this.outputSelector = outputSelector;
		}

		@Override
		public double dEvaluateMd(SortedMap<Date, Double> subMap) {
			try {
				NumberValue run = doEval(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis, specificOperation, subMap);
				return run.getNumberValue();
			} catch (NotEnoughDataException e) {
				LOGGER.error(e, e);
				throw new RuntimeException(e);
			}
		}

		@Override
		public double[] adEvaluateMd(SortedMap<Date, Double> subMap) {
			try {
				NumberValue run = doEval(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis, specificOperation, subMap);
				return (run instanceof NumberArrayValue)?((NumberArrayValue)run).getDoubleArrayValue():new double[] {run.getNumberValue()};
			} catch (NotEnoughDataException e) {
				LOGGER.error(e, e);
				throw new RuntimeException(e);
			}
		}

		@Override
		public SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap) {
			try {
				NumberValue run = doEval(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis, specificOperation, subMap);
				TreeMap<Date, Double> collected = subMap.keySet().stream().collect(Collectors.toMap(k -> k, k -> run.getNumberValue(), (a, b) -> a, TreeMap<Date,Double>::new));
				return collected;
			} catch (NotEnoughDataException e) {
				LOGGER.error(e, e);
				throw new RuntimeException(e);
			}
		}

		@Override
		public SortedMap<Date, double[]> madEvaluateMd(SortedMap<Date, Double> subMap) {
			try {
				NumberValue run = doEval(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis, specificOperation, subMap);
				TreeMap<Date, double[]> collected = subMap.keySet().stream()
						.collect(Collectors.toMap(
								k -> k, 
								k -> (run instanceof NumberArrayValue)?((NumberArrayValue)run).getDoubleArrayValue():new double[] {run.getNumberValue()}, 
								(a, b) -> a, TreeMap<Date,double[]>::new)
								);
				return collected;
			} catch (NotEnoughDataException e) {
				LOGGER.error(e, e);
				throw new RuntimeException(e);
			}
		}

		private NumberValue doEval(TargetStockInfo targetStock, String parentCallStack, int parentRequiredStartShift, Operation specificOperation, SortedMap<Date, Double> subMap) throws NotEnoughDataException {
			TargetStockInfo evaluateTargetStock = new TargetStockInfo(targetStock.getAnalysisName(), targetStock.getEventInfoOpsCompoOperation(), targetStock.getStock(), subMap.firstKey(), subMap.lastKey());
			int operandsRequiredStartShift = specificOperation.operandsRequiredStartShift(evaluateTargetStock, parentRequiredStartShift);
			NumberValue run = (NumberValue) specificOperation
					.run(evaluateTargetStock, 
						 addThisToStack(parentCallStack, parentRequiredStartShift, operandsRequiredStartShift, evaluateTargetStock), 
						 parentRequiredStartShift);
			doEvalColRefs = (run instanceof NumberArrayValue)?((NumberArrayValue)run).getColumnsReferences():Arrays.asList(outputSelector);
			return run;
		}

		@Override
		public List<String> getOutputsRefs() {
			return doEvalColRefs;
		}
	}

	private static final int DATA_INPUT_IDX = 2;
	private static MyLogger LOGGER = MyLogger.getLogger(StatsOperation.class);
	
	protected StatsOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public StatsOperation() {
		this("stat", "Moving statistics",
				new NumberOperation("number", "movingPeriod", "Moving period in data points. 'NaN' means window == data set size", new NumberValue(21.0)),
				new OperationReferenceOperation("operationReference", "specificStat", "Specific stat operation to be used. This is optional and only used for specificStat selector", null), //Optional
				new DoubleMapOperation());
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sma", "mstdev", "msimplereg", "msum", "mmin", "mmax", "mtanhnorm", "specificStat"})));
	}

	public StatsOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(
			TargetStockInfo targetStock, String thisCallStack, 
			int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Double period = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		@SuppressWarnings("unchecked")
		List<NumericableMapValue> numericableMapValue = (List<NumericableMapValue>) inputs.subList(DATA_INPUT_IDX, DATA_INPUT_IDX+1);

		try {
			
			String outputSelector = getOutputSelector(); //We don't do all outputs calculations at once as each calculation is independent
			
			final StatsFunction statFunction;
			
			if (outputSelector != null && outputSelector.equalsIgnoreCase("sma")) {
				statFunction = new MyApacheStats(new Mean());
			} 
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mstdev")) {
				statFunction = new MyApacheStats(new StandardDeviation());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("msimplereg")) {
				statFunction = new MySimpleRegression();
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("msum")) {
				statFunction = new MyApacheStats(new Sum());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mmin")) {
				statFunction = new MyApacheStats(new Min());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mmax")) {
				statFunction = new MyApacheStats(new Max());
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mtanhnorm")) { //Sliding bandNormalizer[-1,1,0]
				statFunction = new StatsFunction() {
					
					@Override
					public double dEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return normalized.get(normalized.lastKey());
					}
					
					@Override
					public double[] adEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return new double[] {normalized.get(normalized.lastKey())};
					}
					
					@Override
					public SortedMap<Date, Double> mdEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return normalized;
					}
					
					@Override
					public SortedMap<Date, double[]> madEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return  normalized.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> new double[] {e.getValue()}, (a, b) -> a, TreeMap<Date,double[]>::new));
					}
					
					private SortedMap<Date, Double> doEval(SortedMap<Date, Double> subMap) {
						Normalizer<Double> normalizer = new Normalizer<Double>(Double.class, subMap.firstKey(), subMap.lastKey(), -1, 1, 0);
						SortedMap<Date, Double> normalized = normalizer.normalised(subMap);
						return normalized;
					}

					@Override
					public List<String> getOutputsRefs() {
						return  Arrays.asList(outputSelector);
					}
				};
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("specificStat")) {
				Operation specificOperation = (Operation) ((OperationReferenceValue<?>) inputs.get(1)).getValue(targetStock);
				statFunction = new SpecificStatsFunction(specificOperation, thisCallStack, thisInputOperandsRequiredShiftFromThis, targetStock, outputSelector);
			}
			else { //Can I be here anymore as the operation would not comply the grammar??
				//Default is identity
				statFunction = new MyApacheStats(new AbstractUnivariateStatistic() {
					@Override
					public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
						return values[begin + length-1];
					}
					@Override
					public UnivariateStatistic copy() {
						return this;
					}
				});
			}

			if (period.isNaN()) { //XXX this may not be accurate as even with NaN (all data set), the windows end date should be sliding??
				ValueManipulator.InnerCalcFunc innerCalcFunc = data -> {
					return new DoubleArrayMapValue(statFunction.madEvaluateMd(data.get(0).getValue(targetStock)), statFunction.getOutputsRefs(), mainInputPosition());
				};
				return ValueManipulator.doubleArrayExpender(this, DATA_INPUT_IDX, targetStock, thisOutputRequiredStartShiftByParent, innerCalcFunc, numericableMapValue);
			} else {
				Date startDate = targetStock.getStartDate(thisInputOperandsRequiredShiftFromThis);
				ValueManipulator.InnerCalcFunc innerCalcFunc = data -> {
					SortedMap<Date, double[]> movingStat = MapUtils.madMovingStat(data.get(0).getValue(targetStock), startDate, period.intValue(), statFunction);
					return new DoubleArrayMapValue(movingStat, statFunction.getOutputsRefs(), mainInputPosition());
				};
				return ValueManipulator.doubleArrayExpender(this, DATA_INPUT_IDX, targetStock, thisOutputRequiredStartShiftByParent, innerCalcFunc, numericableMapValue);
			}

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new DoubleArrayMapValue();

	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return IntStream.range(0, 1)
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					if (numberOperand instanceof NumberOperation) {
						return ((NumberValue) numberOperand.getOrRunParameter(targetStock).orElse(new NumberValue(0.0))).getValue(targetStock).intValue();
					} else {
						return getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift);
					}
				})
				.reduce(0, (r, e) -> r + e);
	}
	
	@Override
	public String toFormulaeShort() {
		Operation operand0 = getOperands().get(0);
		String thisShort = getOutputSelector().substring(1,Math.min(getOutputSelector().length(), 4)) + "_" + ((StringableValue) operand0.getOrRunParameter(null).orElse(new StringValue(operand0.toFormulaeShort()))).getValueAsString();
		String opsFormulaeShort = super.toFormulaeShort();
		return thisShort + ((opsFormulaeShort.isEmpty())? "" : "_" + opsFormulaeShort);
	}

	@Override
	public int mainInputPosition() {
		return 0;
	}


}
