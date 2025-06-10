package com.finance.pms.events.operations.nativeops.data;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;

public abstract class FileExporter extends StringerOperation {

	public FileExporter(String reference, String description) {
		super(reference, description);
	}
	
	public FileExporter(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public FileExporter(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}
	
	
	public abstract Operation getFilePathOperand();
	
	public abstract Operation getHeaderPrefixOperand();

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isForbidThisParameterValue() {
		return true;
	}
	
	

}
