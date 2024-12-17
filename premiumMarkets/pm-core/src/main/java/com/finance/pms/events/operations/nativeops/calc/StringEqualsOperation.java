package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.Value;

public class StringEqualsOperation extends StringerOperation {

	public StringEqualsOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public StringEqualsOperation() {
		this("equalsString", "Compares strings and return TRUE if all equals, FALSE otherwise", new StringOperation("string", "anyString", "Any string to be compared", new StringValue("")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String firstString = ((StringValue) inputs.get(0)).getAsStringable();
		boolean allEquals = inputs.stream().allMatch(s -> ((StringValue)s).getAsStringable().equals(firstString));
		return (allEquals)?new StringValue("TRUE"):new StringValue("FALSE");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

}
