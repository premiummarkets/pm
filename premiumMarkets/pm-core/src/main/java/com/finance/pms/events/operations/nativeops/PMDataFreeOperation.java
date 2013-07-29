package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;

import com.finance.pms.events.operations.Operation;

public abstract class PMDataFreeOperation extends PMIndicatorOperation {
	
	protected PMDataFreeOperation() {
		super();
	}

	public PMDataFreeOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public PMDataFreeOperation(String reference, String description, Operation ... operands) {
		this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	

	@Override
	public int operationStartDateShift() {//The shift must be done in internally in the operation implementer
		return 0;
	}


}
