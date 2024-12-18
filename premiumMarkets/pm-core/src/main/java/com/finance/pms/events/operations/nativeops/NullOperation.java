package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

public class NullOperation extends Operation {
	
	public NullOperation() {
		super("NONE","Null operation. Should signify no operation when assigned or any operation when operand.");
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
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//return new AnyValueListValue<>(inputs.stream().map(i -> (StringableValue) i).collect(Collectors.toList()));
		return new NullValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}

}
