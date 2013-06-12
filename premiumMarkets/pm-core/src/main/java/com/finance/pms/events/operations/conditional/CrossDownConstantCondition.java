package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class CrossDownConstantCondition extends CrossConstantCondition implements OnThresholdCondition {
	
	public CrossDownConstantCondition() {
		super("cross down threshold",  "True when the first time series value is crossing below a constant.");
	}

	public CrossDownConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[2]) >= 0 &&  ops[2].compareTo((Double) ops[1]) > 0;
	}
	
	@Override
	public int inputThresholdPosition() {
		return 0;
	}

}
