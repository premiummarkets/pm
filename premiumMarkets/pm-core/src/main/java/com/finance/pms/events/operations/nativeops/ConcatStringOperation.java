package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class ConcatStringOperation extends StringerOperation {
	
	public ConcatStringOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public ConcatStringOperation() {
		this("conc", "Concatenate the passed strings", new StringOperation("string", "anyString", "Any string to be concatenated", new StringValue("")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String concatenation = inputs.stream()
			.map(sv -> ((StringableValue)sv).getValueAsString())
			.reduce("", (a, e) -> a + e);	
		return new StringValue(concatenation);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	
	}

}
