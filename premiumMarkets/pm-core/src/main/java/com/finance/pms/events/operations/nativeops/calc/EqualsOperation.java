package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.Value;

public class EqualsOperation extends StringerOperation {

	public EqualsOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public EqualsOperation() {
		this("equals", "Compares values and return TRUE if all are representable as strings and all are equal, FALSE otherwise", new NullOperation("firstValue"), new NullOperation("anyValue"));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		StringableValue firstValue = (StringableValue) inputs.get(0);
		boolean allEquals = inputs.stream().allMatch(s -> ((StringableValue) s).getValueAsString().replaceAll("\"","").equals(firstValue.getValueAsString().replaceAll("\"","")));
		return (allEquals)?new StringValue("TRUE"):new StringValue("FALSE");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

}
