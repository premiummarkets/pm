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

public class LogOperation extends PassThroughOperation {

	protected static MyLogger LOGGER = MyLogger.getLogger(LogOperation.class);
	
	private static final int UPSTREAM_OPERATION_IDX = 1;
	
	public LogOperation(String reference, String description, Operation ... operands) {
		super(UPSTREAM_OPERATION_IDX, reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public LogOperation() {
		this("log", "Pass through which logs an additional message. The _ character will be replaced with white spaces.",
				new StringOperation("string", "message", "Message to log", new StringValue("hello_world")),
				new NullOperation("passThroughValue"));
	}

	public LogOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	@Override
	public void setOperands(ArrayList<Operation> overridingOperands) throws IllegalArgumentException {
		overridingOperands.stream().forEach(op -> op.setRunInSequence(true));
		super.setOperands(overridingOperands);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String message = ((StringValue) inputs.get(0)).getValue(targetStock);
		LOGGER.info(getReference() + ": " + message.replaceAll("_", " ") + " in " + this.getOperands().get(UPSTREAM_OPERATION_IDX).toFormulaeShort(targetStock));
		return inputs.get(UPSTREAM_OPERATION_IDX);
	}
	
}
