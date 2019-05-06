package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

public class TruthOfCondition extends BooleanMapCondition {

	private TruthOfCondition() {
		super("truthOf", "Is true if between at least and at most input Series are true",
				new StringOperation("boolean", "isLenient", "If false, the output keySet is an intersection of the input keySets, if true a union", new StringValue("TRUE")),
				new NumberOperation("number","atLeast", "Minimum to be true", new NumberValue(0.0)),
				new NumberOperation("number","atMost", "Maximum to be true", new NumberValue(Double.NaN)),
				new Condition<Boolean>("boolean data Series"));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public TruthOfCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(@SuppressWarnings("rawtypes") Comparable... ops) {
		Double min = (Double) ops[0];
		if (min.isNaN()) min = -Double.MAX_VALUE;
		Double max = (Double) ops[1];
		if (max.isNaN()) max = Double.MAX_VALUE;
		List<Boolean> bools = Arrays.stream(Arrays.copyOfRange(ops, 2, ops.length)).map(b -> (Boolean) b).collect(Collectors.toList());
		int boolSum = 0;
		for (Boolean op : bools) {
			boolSum = boolSum + ((Boolean.TRUE.compareTo(op) == 0)?1:0);
		}
		return (min <= boolSum) && (boolSum <= max);
	}

	@Override
	protected Boolean shortcutUnary() {
		return true;
	}

}
