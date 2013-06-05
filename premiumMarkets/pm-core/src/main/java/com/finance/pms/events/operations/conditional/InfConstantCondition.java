package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class InfConstantCondition extends CmpConstantCondition {
	

	private InfConstantCondition() {
		super("below threshold", "True when a time series value is below the constant.");
	}

	public InfConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double> ... ops) {
		return ops[0].compareTo((Double) ops[1]) < 0;
	}

}
