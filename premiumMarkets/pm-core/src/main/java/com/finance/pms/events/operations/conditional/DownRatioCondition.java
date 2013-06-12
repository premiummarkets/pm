package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;

public class DownRatioCondition extends CrossConstantCondition implements StandAloneCondition {

	private DownRatioCondition() {
		super("down", "True when a time series is down more than the ratio in %");
	}

	public DownRatioCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double> ... ops) {
		Double prev = (Double) ops[0];
		Double current = (Double) ops[1];
		Double constant = (Double) ops[2];
		return (current - prev)/Math.abs(prev) < -constant;
	}

}
