package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.VarOperation;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;


//TODO a monadic letAndRun that lets a variable and runs an other op (potentially using the variable)
public class LetOperation extends VarOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(LetOperation.class);
	
	public LetOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public LetOperation() {
		this("let", "Let a name value pair in the heap",
				new StringOperation("string", "variable", "variable name", new StringValue("mevar")),
				new NullOperation("value"));
	}

	public LetOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Value<?> variableValue = inputs.get(1);
		Value<?> variableReturned = targetStock.letHeapVar(variableName, variableValue);
		LOGGER.info(this.getReference() + ": " + variableName + ", storing: " + variableValue + ", returning: " + variableReturned);
		return variableReturned;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
		//String variableName = ((StringValue) getOperands().get(0).getParameter()).getValue(null);
		//FIXME targetStock.unlet(variableName);
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		Operation operand1 = getOperands().get(1);
		Optional<Value<?>> optParameter1 = operand1.getOrRunParameter(targetStock);
		String valueAsString = ((StringableValue) optParameter1.orElse(new StringValue(operand1.toFormulaeShort(targetStock)))).getAsStringable().replaceAll("\"","");
		return valueAsString;
	}

}