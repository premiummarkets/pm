package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.Value;

public abstract class PassThroughOperation extends Operation {
	
	private int upStreamOperationIdx;

	public PassThroughOperation(int upStreamOperationIdx, String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
		this.upStreamOperationIdx = upStreamOperationIdx;
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis,
			@SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return inputs.get(upStreamOperationIdx);
	}

	@Override
	public Value<?> emptyValue() {
		 return getOperands().get(upStreamOperationIdx).emptyValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

}
