package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.NullOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class EqualsOperation extends StringerOperation {

	public EqualsOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public EqualsOperation() {
		this("equals", "Compares values and return TRUE if all are representable as strings and all are equal, FALSE otherwise", new NullOperation("firstValue"), new NullOperation("anyValue"));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		StringableValue firstValue = (StringableValue) inputs.get(0);
		boolean allEquals = inputs.stream().allMatch(s -> ((StringableValue) s).getValueAsString().equals(firstValue.getValueAsString()));
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
