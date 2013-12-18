package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class CrossDownDoubleMapCondition extends CrossDoubleMapCondition {

	private CrossDownDoubleMapCondition() {
		super("historical cross down", "True when the first time series value is crossing above the second one.");
	}
	
	public CrossDownDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Double>... ops) {
		Double previousFirstOp = (Double) ops[0];
		Double currentFirstOp = (Double) ops[1];
		Double previousSecondOp = (Double) ops[2];
		Double currentSecondOp = (Double) ops[3];
		return previousFirstOp.compareTo(previousSecondOp) >= 0 &&  currentFirstOp.compareTo(currentSecondOp) < 0;
	}


}
