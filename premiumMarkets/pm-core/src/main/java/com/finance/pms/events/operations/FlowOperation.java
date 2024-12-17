package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.nativeops.StringableValue;

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
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

}
