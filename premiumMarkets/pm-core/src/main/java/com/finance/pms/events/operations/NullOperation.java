package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.finance.pms.events.operations.nativeops.AnyValueListValue;
import com.finance.pms.events.operations.nativeops.StringValue;

public class NullOperation extends Operation {
	
	public NullOperation() {
		super("NONE","Null operation. Should signify no operation.");
	}

	public NullOperation(String reference) {
		super(reference, reference);
	}
	
	public NullOperation(String reference, String definition) {
		super(reference, definition);
	}

	public NullOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}
	
	public NullOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}
	
	public NullOperation(String reference, String refAsOperand, String description, StringValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new AnyValueListValue<>(inputs);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock) {
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}

}
