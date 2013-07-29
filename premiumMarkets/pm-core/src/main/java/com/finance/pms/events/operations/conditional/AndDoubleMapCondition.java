package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class AndDoubleMapCondition extends BooleanDoubleMapCondition {
	
	private AndDoubleMapCondition() {
		super("and", "Compare two boolean time series over time and is true when all are true.");
	}
	
	public AndDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Boolean>... ops) {
		for (Comparable<Boolean> op : ops) {
			if (op.compareTo(Boolean.TRUE) != 0) return false;
		}
		return true;
	}

	@Override
	protected Boolean shortcutUnary() {
		return true;
	}

	@Override
	protected Boolean exactDataSet() {
		return true;
	}

}
