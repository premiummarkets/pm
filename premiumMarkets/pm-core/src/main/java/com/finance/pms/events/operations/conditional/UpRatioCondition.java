package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;

public class UpRatioCondition extends CrossConstantCondition implements StandAloneCondition {
	
	private UpRatioCondition() {
		super(
				"up", "True when a time series is up more than the ratio in %",
				new NumberOperation("threshold"), 
				new NumberOperation("dates comparison span"), new NumberOperation("time period over which it happens"),  new NumberOperation("length of time over which it is true"), 
				new DoubleMapOperation("historical data input"));
	}

	public UpRatioCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Double> ... ops) {
		Double prev = (Double) ops[0];
		Double current = (Double) ops[1];
		Double constant = (Double) ops[2];
		return (current - prev)/Math.abs(prev) > constant/100;
	}

}
