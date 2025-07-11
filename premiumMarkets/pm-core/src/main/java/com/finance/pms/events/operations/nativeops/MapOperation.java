package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

@XmlSeeAlso({ArrayMapOperation.class, DoubleMapOperation.class, EventMapOperation.class})
public abstract class MapOperation extends Operation {

	public MapOperation(String reference, String description) {
		super(reference, description);
	}

	public MapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public MapOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack,  int thisStartShift, int thisAndOperandsStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((NumericableMapValue)inputs.get(0));
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	@Override
	public boolean isDataShiftSensitive() {
		return true;
	}
	
	@Override 
	//This should have no effect as the output of MapOperations are not set as their parameters and are isDataShiftSensitive. 
	//FIXME This could be an alternative to the output cache?
	public boolean isForbidThisParameterValue() {
		return true;
	}
	
}
