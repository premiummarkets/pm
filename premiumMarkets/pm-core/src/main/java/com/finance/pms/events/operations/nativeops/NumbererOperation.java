package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({
	NumberMathOperation.class
	})
public abstract class NumbererOperation extends Operation {

	public NumbererOperation(String reference, String description) {
		super(reference, description);
	}

	public NumbererOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public NumbererOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public abstract NumberValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs);
	
	@Override
	public NumberValue emptyValue() {
		return null;
	}
	
//	@Override
//	public void setParameter(Value<?> parameter) {
//		//Always re check the upstream operands (eg. file system, web end point, random values ..)
//	}

}
