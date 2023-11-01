package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

public class GetOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(GetOperation.class);
	
	public GetOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public GetOperation() {
		this("get", "Get a named value from the heap",
				new StringOperation("string", "variable", "variable name", new StringValue("mevar")),
				new NullOperation("defaultValue"));
	}

	public GetOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, String thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Value<?> defaultValue = inputs.get(1);
		
		Value<?> value = targetStock.getHeapVar(variableName);
		if (value == null) return defaultValue;
		return value;
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

}
