package com.finance.pms.events.operations.nativeops.data;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.CachableOperation;
import com.finance.pms.events.operations.nativeops.DoubleArrayMapValue;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;

public class IOsSelectOperation extends PMWithDataOperation implements MultiValuesOutput, CachableOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(IOsSelectOperation.class);
	
	private static Integer 	COLUMNINDEX_IDX = 2;

	public IOsSelectOperation() {
		super("iosSelect", "Select columns from multi values input",
			new DoubleMapOperation("Multi values input"),
			new NumberOperation("number","mainColumnIndex", "Main Index of the column to be displayed.", new NumberValue(1.0)),
			new NumberOperation("number","columnIndex", "Index of the columns to include in the output ([1..n] || 0 for all). 1 being the first column.", new NumberValue(0.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	//Can't use MultiSelectorsValue as selectors are not known in advance (this will fail in the editor).
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		DoubleArrayMapValue input = (DoubleArrayMapValue) inputs.get(0);
		
		int mainIndex = ((NumberValue) inputs.get(1)).getNumberValue().intValue();
		List<Integer> includedIndexes = inputs.subList(COLUMNINDEX_IDX, inputs.size()).stream().map(v -> ((NumberValue) v).getNumberValue().intValue()).collect(Collectors.toList());

		if (includedIndexes.contains(0)) {
			includedIndexes.clear();
		} else {
			if (!includedIndexes.contains(mainIndex)) throw new RuntimeException("The column index list must contain the main index.");
		}
		
		List<String> columnRefs = new ArrayList<String>();
		SortedMap<Date, double[]> calculationResults = new TreeMap<Date, double[]>();
		Date startDate = null;
		try {
			
			startDate =  targetStock.getStartDate(thisStartShift);
		
			List<String> headers = input.getColumnsReferences();
			SortedMap<Date, double[]> inputData = input.getDoubleArrayValue();
			
			if (includedIndexes.isEmpty()) {
				includedIndexes.addAll(IntStream.range(0, headers.size()).boxed().collect(Collectors.toList()));
			}

			columnRefs = includedIndexes.stream()
						.map(columnIndex -> {
							String key = (headers.isEmpty())? "Column" + columnIndex : headers.get(columnIndex-1);
							return key;
						})
						.collect(Collectors.toList());
			calculationResults =
					inputData.entrySet().stream()
						.map(e -> {
							double[] nuOutput = new double[includedIndexes.size()];
							IntStream.range(0, nuOutput.length).forEach(i -> nuOutput[i] = e.getValue()[includedIndexes.get(i)-1]);
							return new AbstractMap.SimpleImmutableEntry<>(e.getKey(), nuOutput);
						})
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, TreeMap::new));

			SortedMap<Date, double[]> subMapInclusiveResults = MapUtils.subMapInclusive(calculationResults, startDate, targetStock.getEndDate());			
			return new DoubleArrayMapValue(subMapInclusiveResults, columnRefs, includedIndexes.indexOf(mainIndex));
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			return new DoubleArrayMapValue(calculationResults, columnRefs, includedIndexes.indexOf(mainIndex));
		}

	}

	@Override
	public int mainInputPosition() {
		List<Number> includedIndexes = getOperands().subList(COLUMNINDEX_IDX, getOperands().size()).stream()
				.map(v -> (((NumberValue)v.getOrRunParameter(null).orElseThrow()).getValue(null)))
				.collect(Collectors.toList());
		double mainIndex = ((NumberValue)getOperands().get(1).getOrRunParameter(null).orElseThrow()).getValue(null).doubleValue();
		return (includedIndexes.contains(0.0))?(int)mainIndex:includedIndexes.indexOf(mainIndex);
	}
	
	@Override
	public Integer operationNaturalShift() {
		return 0;
	}

}
