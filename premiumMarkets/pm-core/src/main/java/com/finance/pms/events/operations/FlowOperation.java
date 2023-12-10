package com.finance.pms.events.operations;

import java.util.ArrayList;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public abstract class FlowOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(FlowOperation.class);

	public FlowOperation() {
	}

	public FlowOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public FlowOperation(String reference, String description) {
		super(reference, description);
	}

	public FlowOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public Value<?> emptyValue() {
		return  getOperands().get(getOperands().size() -1).emptyValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

}
