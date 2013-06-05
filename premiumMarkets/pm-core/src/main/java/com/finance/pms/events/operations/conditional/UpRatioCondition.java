package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class UpRatioCondition extends CrossConstantCondition implements StandAloneCondition {
	
	private UpRatioCondition() {
		super("up", "True when a time series is up more than the ratio in %");
	}

	public UpRatioCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double> ... ops) {
		Double prev = (Double) ops[0];
		Double current = (Double) ops[1];
		Double constant = (Double) ops[2];
		return (current - prev)/Math.abs(prev) > constant;
	}

	@Override
	public int mainPosition() {
		return 2;
	}

}
