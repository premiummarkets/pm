package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.UnaryCondition;

public abstract class ArrayMapOperation extends MapOperation implements UnaryCondition {
	
	public ArrayMapOperation() {
		super("multi historical data", "Multiple Time series of real historical data or resulting of calculations");
	}

	public ArrayMapOperation(String reference) {
		super(reference, reference);
	}

	public ArrayMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public ArrayMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public ArrayMapOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public DoubleArrayMapValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((DoubleArrayMapValue)inputs.get(0));
	}

}