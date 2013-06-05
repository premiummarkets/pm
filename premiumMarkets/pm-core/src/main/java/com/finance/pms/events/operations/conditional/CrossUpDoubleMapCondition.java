package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class CrossUpDoubleMapCondition extends CrossDoubleMapCondition {
	
	
	private CrossUpDoubleMapCondition() {
		super("historical cross up", "True when the first time series value is crossing below the second one.");
	}
	
	public CrossUpDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double>... ops) {
		Double previousFirstOp = (Double) ops[0];
		Double currentFirstOp = (Double) ops[1];
		Double previousSecondOp = (Double) ops[2];
		Double currentSecondOp = (Double) ops[3];
		return previousFirstOp.compareTo(previousSecondOp) >= 0 &&  currentFirstOp.compareTo(currentSecondOp) < 0;
	}


}
