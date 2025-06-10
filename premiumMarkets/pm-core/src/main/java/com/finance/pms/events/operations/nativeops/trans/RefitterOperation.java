package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventInfoOpsCompoOperation;
import com.finance.pms.events.operations.nativeops.CachableOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.scoring.functions.MyApacheStats;

public class RefitterOperation extends DoubleMapOperation implements CachableOperation {
	
	public RefitterOperation() {
		this("refit", "Refits the end of an operation output (prediction) to an other operation output (desired).",
				new DoubleMapOperation("refittee", "refittee", "Output to be refitted (eg. prediction)", null),
				new OperationReferenceOperation("operationReference", "refitter", "Reference (eg. desired)", null),
				new NumberOperation("number", "movingPeriod", "Moving period in data points.", new NumberValue(21.0))
			);
	}

	public RefitterOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> refittee = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		Operation refitter = (Operation) ((OperationReferenceValue<?>) inputs.get(1)).getValue(targetStock);
		Integer span = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		
		MyApacheStats stddev = new MyApacheStats(new StandardDeviation());
		MyApacheStats mean = new MyApacheStats(new Mean());
		
		SortedMap<Date, Double> refitted = refittee.subMap(targetStock.getStartDate(parentRequiredStartShift), targetStock.getEndDate()).entrySet().stream()
		.collect(Collectors.toMap(e -> e.getKey(), e -> {
			
			Date startIter = DateUtils.addDays(e.getKey(), -span);
			Date endIter = e.getKey();
			Date endExIter = DateUtils.addDays(e.getKey(), 1); //End Excluded
			
			SortedMap<Date, Double> refetteeHeadMap = refittee.subMap(startIter, endExIter);
			double refitteeStddev = stddev.dEvaluateMd(refetteeHeadMap);
			double refitteeMean = mean.dEvaluateMd(refetteeHeadMap);
			
			String referenceIter = refitter.getOperationReference() + "_iter";
			EventInfoOpsCompoOperation eventInfo = new EventInfoOpsCompoOperation(referenceIter, referenceIter);
			eventInfo.setKeepEvents(false);
			TargetStockInfo targetStockIter = new TargetStockInfo(targetStock, eventInfo, targetStock.getStock(), startIter, endIter);
			SortedMap<Date, Double> refitterHeadMap = ((NumericableMapValue) refitter.run(targetStockIter, thisCallStack, 0)).getValue(targetStockIter);
			
			int lag = refitter.getLagAmount(targetStock, thisCallStack, refitter.getOperands());
			List<Date> keys = new ArrayList<>(refitterHeadMap.keySet());
			Date lastValid = keys.get(keys.size() -1 -lag);
			Date lastValidEx = DateUtils.addDays(lastValid, 1); 
			refitterHeadMap = refitterHeadMap.subMap(startIter, lastValidEx);

			double refitterStddev = stddev.dEvaluateMd(refitterHeadMap);
			double refitterMean = mean.dEvaluateMd(refitterHeadMap);
			Double refitterLastValue = refitterHeadMap.get(refitterHeadMap.lastKey());
			
			SortedMap<Date, Double> refetteeEndMap = refetteeHeadMap.tailMap(refitterHeadMap.lastKey());
			Double refitteeEndFirstValue = refetteeEndMap.get(refetteeEndMap.firstKey());
			
			SortedMap<Date, Double> endRefitted = refetteeEndMap.entrySet().stream()
			.collect(Collectors.toMap(e1 -> e1.getKey(), e1 -> {
				return ((((e1.getValue() - refitteeEndFirstValue) - refitteeMean) / refitteeStddev) * refitterStddev) + refitterMean + refitterLastValue;
			}, (a, b) -> a, TreeMap::new));
			
			Double double1 = endRefitted.get(endRefitted.lastKey());
			return double1;
			
		}, (a, b) -> a, TreeMap::new));
		
		return new DoubleMapValue(refitted);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return IntStream.range(0, getOperands().size())
				.map(i -> {
					Operation numberOperand = getOperands().get(i);
					return numberOperand.getOrRunParameter(targetStock, thisCallStack)
							.filter(v -> v instanceof NumberValue)
							.map(v -> ((NumberValue) v).getValue(targetStock).intValue())
							.orElseGet(() -> getOperands().get(i).operandsRequiredStartShift(targetStock, thisCallStack, thisParentStartShift));
				})
				.reduce(0, (r, e) -> r + e);
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		String thisShortName = "rf";
		String opsFormulaeShort = super.toFormulaeShort(targetStock, thisCallStack, this.getOperands());
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

}
