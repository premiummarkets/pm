package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

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
	public Value<?> calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Integer addedShift = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		return inputs.get(1);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		Double addedShift = ((NumberValue) getOperands().get(0).getParameter()).getValue(null).doubleValue();
		if (Double.isNaN(addedShift)) {
			return (int) TimeUnit.DAYS.convert(DateFactory.midnithDate(new Date()).getTime() - DateFactory.dateAtZero().getTime(), TimeUnit.MILLISECONDS);
		} else {
			return addedShift.intValue();
		}
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {

	}

	@Override
	public Value<?> emptyValue() {
		return new DoubleMapValue();
	}

}
