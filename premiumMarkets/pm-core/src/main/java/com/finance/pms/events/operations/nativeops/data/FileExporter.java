package com.finance.pms.events.operations.nativeops.data;

import java.util.ArrayList;

import com.finance.pms.events.operations.Operation;
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

}
