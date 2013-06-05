package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;

@XmlRootElement
public class NotDoubleMapCondition extends BooleanDoubleMapCondition {
	
	private NotDoubleMapCondition() {
		super("not", "Return the complementary of a boolean series.", new Condition<Boolean>("boolean historical data"));
	}
	
	public NotDoubleMapCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public Boolean conditionCheck(Comparable<Boolean>... ops) {
	   return !(Boolean)ops[0];
	}

	@Override
	protected Boolean shortcutUnary() {
		return false;
	}

}
