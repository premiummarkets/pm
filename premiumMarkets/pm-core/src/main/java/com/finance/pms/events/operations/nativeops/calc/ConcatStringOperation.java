package com.finance.pms.events.operations.nativeops.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.StringerOperation;
import com.finance.pms.events.operations.nativeops.Value;

public class ConcatStringOperation extends StringerOperation {
	
	public ConcatStringOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public ConcatStringOperation() {
		this("conc", "Concatenate the passed strings", new StringOperation("string", "anyString", "Any string to be concatenated", new StringValue("")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String concatenation = inputs.stream()
			.map(sv -> {
				if (sv == null) {
					throw new RuntimeException("Invalid inputs for concatenation: " + inputs);
				}
				else if (sv instanceof StringableValue) {
					return ((StringableValue) sv).getAsStringable();
				} else {
					return sv.toString().replaceAll(" ", "_");
				}
			})
			.reduce("", (a, e) -> a + e);	
		return new StringValue(concatenation);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

}
