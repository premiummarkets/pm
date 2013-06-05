package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class SupConstantCondition extends CmpConstantCondition {
	

	private SupConstantCondition() {
		super("above threshold", "True when a time series value is above the constant.");
	}

	public SupConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double> ... ops) {
		return ops[0].compareTo((Double) ops[1])  > 0;
	}

}
