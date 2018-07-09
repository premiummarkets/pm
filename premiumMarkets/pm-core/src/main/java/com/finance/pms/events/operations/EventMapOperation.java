package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.List;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.conditional.EventDataValue;
import com.finance.pms.events.operations.nativeops.MapOperation;

public class EventMapOperation extends Operation implements MapOperation {

	public EventMapOperation() {
		super("historical data", "Time series of real historical data or resulting of calculations");
	}

	public EventMapOperation(String reference) {
		super(reference, reference);
	}

	public EventMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public EventMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	@Override
	public EventDataValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((EventDataValue)inputs.get(0));
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Stock... stock) {
		//Nothing
	}


}
