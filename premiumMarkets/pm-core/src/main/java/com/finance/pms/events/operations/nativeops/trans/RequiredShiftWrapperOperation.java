package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.Value;

public class RequiredShiftWrapperOperation extends Operation {
	
	public RequiredShiftWrapperOperation() {
		this("shiftWrapper", "This is a pass through for the output of its operand. Use this to add (or remove -) to the required shift of its operand. NaN means full data set.", 
				new NumberOperation("addedShift", "addedShift", "Added Shift", new NumberValue(Double.NaN)), 
				new DoubleMapOperation("data")
		);
	}

	public RequiredShiftWrapperOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Integer addedShift = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		return inputs.get(1);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		Double addedShift = ((NumberValue) getOperands().get(0).getOrRunParameter(targetStock).orElse(new NumberValue(0.0))).getValue(targetStock).doubleValue();
		if (Double.isNaN(addedShift)) {
			return (int) TimeUnit.DAYS.convert(DateFactory.midnithDate(new Date()).getTime() - DateFactory.dateAtZero().getTime(), TimeUnit.MILLISECONDS);
		} else {
			return addedShift.intValue();
		}
	}

	@Override
	public Value<?> emptyValue() {
		return new DoubleMapValue();
	}

}
