package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;

public class BuyAndHoldOperation extends PMWithDataOperation {

	public BuyAndHoldOperation() {
		super("buyAndHold", "Returns -1 at start date and +1 after start date.", new DoubleMapOperation("Dates keys set references"));
	}

	public BuyAndHoldOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		SortedMap<Date, Double> keySetRefference = ((NumericableMapValue) inputs.get(0)).getValue(targetStock);
		
		Date startDate = targetStock.getStartDate(0);
		
		SortedMap<Date, Double> collect = keySetRefference.keySet().stream().collect(Collectors.toMap(d -> d, d -> (d.compareTo(startDate) <= 0)?-1.0:+1.0, (a, b) -> a, TreeMap::new));
		DoubleMapValue result = new DoubleMapValue(collect);
		return result;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 1;
	}

}
