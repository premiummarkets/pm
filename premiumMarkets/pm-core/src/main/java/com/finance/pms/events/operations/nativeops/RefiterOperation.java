package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.MyApacheStats;

@SuppressWarnings("all") //FIXME
public class RefiterOperation extends DoubleMapOperation implements CachableOperation {
	
	public RefiterOperation() {
		this("refit", "Refits the end of an operation output (prediction) to an other operation output (desired).",
				new DoubleMapOperation("refitee", "refitee", "Output to be refited", null),
				new DoubleMapOperation("refiter", "refiter", "Output to refit to", null));
	}

	public RefiterOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> refitee = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		SortedMap<Date, Double> refiter = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);
		
		MyApacheStats stddev1 = new MyApacheStats(new StandardDeviation());
		double refiteeStddev = stddev1.dEvaluateMd(refitee);
		MyApacheStats mean1 = new MyApacheStats(new Mean());
		double refiteeMean = mean1.dEvaluateMd(refitee);
		
		MyApacheStats stddev2 = new MyApacheStats(new StandardDeviation());
		double refiterStddev = stddev2.dEvaluateMd(refiter);
		MyApacheStats mean2 = new MyApacheStats(new Mean());
		double refiterMean = mean2.dEvaluateMd(refiter);
		
		SortedMap<Date, Double> endToRefit = refitee.tailMap(refiter.lastKey());
		double slope = 1d; //refiterStddev/refiteeStddev; //TODO how can I match the passing of zero??
		double intersect = -slope * endToRefit.get(endToRefit.firstKey()) + refiter.get(refiter.lastKey());
		Map<Date, Double> refitedEnd = endToRefit.entrySet().stream().collect(
				Collectors.toMap(e -> e.getKey(), e -> slope * e.getValue() + intersect));
		
		SortedMap<Date, Double> refited = new TreeMap<Date, Double>();
		refited.putAll(refiter);
		refited.putAll(refitedEnd);
		return new DoubleMapValue(refited);
	}

	@Override
	public Integer operationNaturalShift() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isParameterDataSensitive() {
		return true;
	}

}
