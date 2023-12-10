package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

public class LogOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(LogOperation.class);
	
	public LogOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public LogOperation() {
		this("log", "Pass through which logs an additional message.",
				new StringOperation("string", "message", "message to log", new StringValue("hello world!")),
				new NullOperation("passThroughValue"));
	}

	public LogOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String message = ((StringValue) inputs.get(0)).getValue(targetStock);
		LOGGER.info(getReference() + ": " + message.replaceAll("_", " ") + " in " + this.getOperands().get(1).toFormulaeShort(targetStock));
		return inputs.get(1);
	}

	@Override
	public Value<?> emptyValue() {
		 return getOperands().get(1).emptyValue();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}
	
}
