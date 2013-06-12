package com.finance.pms.events.operations.nativeops;

import java.util.EnumSet;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.tictactec.ta.lib.MAType;

public class MATypeOperation extends Operation  implements LeafOperation {
	
	public MATypeOperation() {
		super("moving average type", "One of "+EnumSet.allOf(MAType.class));
	}

	public MATypeOperation(String reference) {
		super(reference, reference);
	}
	
	public MATypeOperation(String reference, String refAsOperand, String description, DoubleValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public MATypeValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((MATypeValue)inputs.get(0));
	}

}
