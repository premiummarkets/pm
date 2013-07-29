package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;

import com.finance.pms.events.operations.Operation;

public abstract class PMWithDataOperation extends PMIndicatorOperation {

	protected PMWithDataOperation() {
		super();
	}

	public PMWithDataOperation(String reference, String description, ArrayList<Operation> operands) {
		super(reference, description, operands);
	}
	
	public PMWithDataOperation(String reference, String description, Operation ... operands) {
		this(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
}
