package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;

public class CrossDownConstantCondition extends CrossConstantCondition implements OnThresholdCondition {
	

	private CrossDownConstantCondition() {
		super(
				"cross down threshold",  "True when the first time series value is crossing below a constant.", 
				new NumberOperation("threshold"), 
				new NumberOperation("dates comparison span"), new NumberOperation("time period over which it happens"),  new NumberOperation("length of time over which it is true"), 
				new DoubleMapOperation("historical data input"));
	}
	
	public CrossDownConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[2]) >= 0 &&  ops[2].compareTo((Double) ops[1]) > 0;
	}
	
	@Override
	public int inputThresholdPosition() {
		return 0;
	}

}
