package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;


public class SupDoubleMapCondition extends CmpDoubleMapCondition {
	

	private SupDoubleMapCondition() {
		super("historical superior", "True when the first time series value is strictly above the second one.");
	}
	
	public SupDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[1]) > 0;
	}

}
