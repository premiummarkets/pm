package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.VarOperation;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

public class GetOperation extends VarOperation {
	
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
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Value<?> defaultValue = inputs.get(1);
		
		Value<?> variableValue = targetStock.getHeapVar(variableName);
		if (variableValue == null) variableValue = defaultValue; //TODO if default is NaN than this means orThrow and the value is needed -> Throw an Exception.
		
		LOGGER.info(this.getReference() + ": " + variableName + ", " + variableValue);
		return variableValue;
	}

}