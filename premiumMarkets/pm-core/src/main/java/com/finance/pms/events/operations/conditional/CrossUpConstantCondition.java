package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
@XmlRootElement
public class CrossUpConstantCondition extends CrossConstantCondition implements OnThresholdCondition {
	

	private CrossUpConstantCondition() {
		super(
				"cross up threshold", "True when the first time series value is crossing above a constant.",
				new NumberOperation("threshold"), 
				new NumberOperation("dates comparison span"), new NumberOperation("time period over which it happens"),  new NumberOperation("length of time over which it is true"), 
				new DoubleMapOperation("historical data input"));
	}
	
	public CrossUpConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}


	@Override
	public Boolean conditionCheck(@SuppressWarnings("unchecked") Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[2]) <= 0 &&  ops[2].compareTo((Double) ops[1]) < 0;
	}
	
	@Override
	public int inputThresholdPosition() {
		return 0;
	}

}
