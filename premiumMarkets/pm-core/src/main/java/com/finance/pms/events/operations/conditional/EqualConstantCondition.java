package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class EqualConstantCondition extends CmpConstantCondition {

	private EqualConstantCondition() {
		super("equal threshold", "True when a time series reaches a value equal to the constant.");
	}

	public EqualConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double> ... ops) {
		return  ops[0].compareTo((Double) ops[1]) == 0;
	}

}
