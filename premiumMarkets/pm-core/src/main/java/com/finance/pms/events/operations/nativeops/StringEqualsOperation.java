package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class StringEqualsOperation extends StringerOperation {

	public StringEqualsOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public StringEqualsOperation() {
		this("equalsString", "Compares strings and return TRUE if all equals, FALSE otherwise", new StringOperation("string", "anyString", "Any string to be compared", new StringValue("")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String firstString = ((StringValue)inputs.get(0)).getValueAsString();
		boolean allEquals = inputs.stream().allMatch(s -> ((StringValue)s).getValueAsString().equals(firstString));
		return (allEquals)?new StringValue("TRUE"):new StringValue("FALSE");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	
	}

}
