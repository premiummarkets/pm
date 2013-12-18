package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;


public class InfDoubleMapCondition extends CmpDoubleMapCondition {
	
	private InfDoubleMapCondition() {
		super("historical inferiority", "True when the first time series value is strictly below the second one.");
	}
	
	public InfDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[1]) < 0;
	}

}
