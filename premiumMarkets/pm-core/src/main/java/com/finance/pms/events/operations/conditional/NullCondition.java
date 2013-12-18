package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class NullCondition extends Condition<Boolean> {
	
	private NullCondition() {
		super("nullCondition", "Null condition.");
	}
	
	public NullCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		setOperands(operands);
	}

	@Override
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new BooleanMapValue();
	}

}
