package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public abstract class VarOperation extends Operation {

	public VarOperation() {
	}

	public VarOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public VarOperation(String reference, String description) {
		super(reference, description);
	}

	public VarOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}


	@Override
	public Value<?> emptyValue() {
		return new StringValue("noVarFound");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}
	
	@Override
	public boolean isForbidThisParameterValue() {
		return true;
	}

}
