package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
@XmlRootElement
public class CrossUpConstantCondition extends CrossConstantCondition implements OnThresholdCondition {
	
	public CrossUpConstantCondition() {
		super("cross up threshold", "True when the first time series value is crossing above a constant.");
	}
	
	public CrossUpConstantCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Double>... ops) {
		return ops[0].compareTo((Double) ops[2]) <= 0 &&  ops[2].compareTo((Double) ops[1]) < 0;
	}
	
	@Override
	public int thresholdPosition() {
		return 0;
	}

	@Override
	public int mainPosition() {
		return 2;
	}

}
