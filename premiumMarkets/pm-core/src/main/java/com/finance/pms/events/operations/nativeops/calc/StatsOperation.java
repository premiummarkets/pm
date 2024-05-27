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
package com.finance.pms.events.operations.nativeops.calc;

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
import com.finance.pms.events.operations.CalculateThreadExecutor;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberArrayValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.operations.util.ValueManipulator;
import com.finance.pms.events.scoring.functions.MyApacheStats;
import com.finance.pms.events.scoring.functions.MySimpleRegression;
import com.finance.pms.events.scoring.functions.Normalizer;
import com.finance.pms.events.scoring.functions.NormalizerMeanStdev;
import com.finance.pms.events.scoring.functions.StatsFunction;

@XmlRootElement
public class StatsOperation extends PMWithDataOperation implements MultiValuesOutput {

	private final class SpecificStatsFunction implements StatsFunction {
		
		private final Operation specificOperation;
		private final List<StackElement> thisCallStack;
		private final int thisInputOperandsRequiredShiftFromThis;
		private final TargetStockInfo targetStock;
		private final String outputSelector;
		private int minPeriod;
		
		private List<String> doEvalColRefs;

		private SpecificStatsFunction(Operation specificOperation, List<StackElement> thisCallStack, int thisInputOperandsRequiredShiftFromThis, TargetStockInfo targetStock, String outputSelector) {
			this.specificOperation = specificOperation;
			this.thisCallStack = thisCallStack;
			this.thisInputOperandsRequiredShiftFromThis = thisInputOperandsRequiredShiftFromThis;
			this.targetStock = targetStock;
			this.outputSelector = outputSelector;
			this.minPeriod = specificOperation.operandsRequiredStartShift(targetStock, thisInputOperandsRequiredShiftFromThis);
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

		private NumberValue doEval(TargetStockInfo targetStock, List<StackElement> parentCallStack, int parentRequiredStartShift, Operation specificOperation, SortedMap<Date, Double> subMap) throws NotEnoughDataException {
			TargetStockInfo evaluateTargetStock = new TargetStockInfo(targetStock.getAnalysisName(), targetStock.getEventInfoOpsCompoOperation(), targetStock.getStock(), subMap.firstKey(), subMap.lastKey());
			NumberValue run = (NumberValue) specificOperation.run(evaluateTargetStock, parentCallStack, parentRequiredStartShift);
			doEvalColRefs = (run instanceof NumberArrayValue)?((NumberArrayValue)run).getColumnsReferences():Arrays.asList(outputSelector);
			return run;
		}

		@Override
		public List<String> getOutputsRefs() {
			return doEvalColRefs;
		}

		@Override
		public int getMinPeriod() {
			return minPeriod;
		}
	}

	private static MyLogger LOGGER = MyLogger.getLogger(StatsOperation.class);
	
	private static final int DATA_INPUT_IDX = 3;
	private static final int STAT_OP_IDX = 2;
	
	protected StatsOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public StatsOperation() {
		this("stat", "Moving statistics",
				new NumberOperation("number", "movingPeriod", "Moving period in data points. 'NaN' means window == data set size", new NumberValue(21.0)),
				new StringOperation("boolean", "isLenientInit", "If true, in case a lack of heading data, the initial calculation window may be calculated from the smaller period (<= moving period) as permited by the stat operation in use.", new StringValue("FALSE")),
				new OperationReferenceOperation("operationReference", "specificStat", "Specific stat operation to be used. This is optional and only used for specificStat selector", null), //Optional
				new DoubleMapOperation());
		setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"sma", "mstdev", "msimplereg", "msum", "mmin", "mmax", "mtanhnorm", "mmeanostdevnorm", "specificStat"})));
	}

	public StatsOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(
			TargetStockInfo targetStock, List<StackElement> thisCallStack, 
			int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		Double period = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Boolean lenientInit = Boolean.valueOf(((StringValue) inputs.get(1)).getValue(targetStock));
		
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
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("mmeanostdevnorm")) { // (data-mean)/stdev
				statFunction = new StatsFunction() {

					@Override
					public double dEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return normalized.get(normalized.lastKey());
					}

					private SortedMap<Date, Double> doEval(SortedMap<Date, Double> subMap) {
						NormalizerMeanStdev<Double> normalizerMeanStdev = new NormalizerMeanStdev<>();
						return normalizerMeanStdev.normalised(subMap);
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

					@Override
					public double[] adEvaluateMd(SortedMap<Date, Double> subMap) {
						SortedMap<Date, Double> normalized = doEval(subMap);
						return new double[] {normalized.get(normalized.lastKey())};
					}

					@Override
					public List<String> getOutputsRefs() {
						return  Arrays.asList(outputSelector);
					}

					@Override
					public int getMinPeriod() {
						return 1;
					}
					
				};
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

					@Override
					public int getMinPeriod() {
						return 1;
					}
				};
			}
			else if (outputSelector != null && outputSelector.equalsIgnoreCase("specificStat")) {
				Operation specificOperation = (Operation) ((OperationReferenceValue<?>) inputs.get(STAT_OP_IDX)).getValue(targetStock);
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
					SortedMap<Date, double[]> movingStat = MapUtils.madMovingStat(data.get(0).getValue(targetStock), startDate, period.intValue(), statFunction, lenientInit);
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
					return numberOperand.getOrRunParameter(targetStock)
							.filter(v -> v instanceof NumberValue)
							.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
							.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisParentStartShift));
				})
				.reduce(0, (r, e) -> r + e);
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		Operation period = getOperands().get(0);
		Operation leniency = getOperands().get(1);
		String thisShort = 
				getOutputSelector().substring(1,Math.min(getOutputSelector().length(), 4)) + "_" +
				((StringableValue) period.getOrRunParameter(targetStock).orElse(new StringValue(period.toFormulaeShort(targetStock)))).getAsStringable() + "_" +
				((StringableValue) leniency.getOrRunParameter(targetStock).orElse(new StringValue(leniency.toFormulaeShort(targetStock)))).getValue(targetStock).toString().substring(0,1);
		String opsFormulaeShort = super.toFormulaeShort(targetStock, this.getOperands().subList(2, this.getOperands().size()));
		return "st_" + thisShort + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

	@Override
	public int mainInputPosition() {
		return 0;
	}
	
	@Override
	public void interrupt() throws Exception {
		CalculateThreadExecutor.getRandomInfiniteExecutorInstance().shutdownNow();
	}


}
