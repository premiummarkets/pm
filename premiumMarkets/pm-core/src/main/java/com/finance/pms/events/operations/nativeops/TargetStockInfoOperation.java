package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class TargetStockInfoOperation extends Operation {
	
	public TargetStockInfoOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public TargetStockInfoOperation() {
		//FIXME we don't use output selector in order to select the returned value but an operand instead
		//Operations with no operands are actually considered as operations with an indeterministic (undeterministic) N number of operands with N > 0 ..
		this("targetStockInfo", "Return informations about the targeted stock and dates bondaries",
		new StringOperation("string", "TargetStockInfo retreived", "TargetStockInfo retreived", new StringValue("symbol")));
		//setAvailableOutputSelectors(new ArrayList<String>(Arrays.asList(new String[]{"symbol"})));
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String selector = ((StringValue) inputs.get(0)).getValue(targetStock);
		
		//if (getOutputSelector() != null && getOutputSelector().equalsIgnoreCase("symbol")) {
		if (selector.equals("symbol")) {
            return new StringValue(targetStock.getStock().getSymbol());
        }
		
		return new StringValue(targetStock.getStock().getSymbol());
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
	}

}