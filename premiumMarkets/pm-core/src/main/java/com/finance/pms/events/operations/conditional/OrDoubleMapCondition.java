package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class OrDoubleMapCondition extends BooleanDoubleMapCondition {

	private OrDoubleMapCondition() {
		super("or", "Compare two boolean time series over time and is true when at least one is true.");
	}

	public OrDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Boolean>... ops) {
		for (Comparable<Boolean> op : ops) {
			if (op.compareTo(Boolean.TRUE) == 0) return true;
		}
		return false;
	}

	@Override
	protected Boolean shortcutUnary() {
		return true;
	}

	@Override
	protected Boolean exactDataSet() {
		return false;
	}

}
