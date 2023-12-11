package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.admin.config.SystemEnvironment;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.VarOperation;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

public class UnEnvOperation extends VarOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(UnEnvOperation.class);
	
	public UnEnvOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public UnEnvOperation() {
		this("unenv", "Removes a named value from the env", new StringOperation("string", "variable", "variable name", new StringValue("mevar")));

	}

	public UnEnvOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Object envObject = SystemEnvironment.getInstance().remove(targetStock.getStock(), variableName);
		return new BooleanValue(envObject != null);
	}

}
