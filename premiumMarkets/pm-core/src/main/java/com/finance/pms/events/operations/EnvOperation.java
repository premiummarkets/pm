package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.config.SystemEnvironment;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

public class EnvOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(EnvOperation.class);
	
	public EnvOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public EnvOperation() {
		this("env", "Get a named value from the env",
				new StringOperation("string", "variable", "variable name", new StringValue("mevar")),
				new NullOperation("defaultValue"));
	}

	public EnvOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Value<?> defaultValue = inputs.get(1);
		
		Object envObject = SystemEnvironment.getInstance().read(targetStock.getStock(), variableName).orElse(defaultValue);
		if (envObject instanceof Value) {
			return (Value<?>) envObject;
		} else {
			if (envObject instanceof String) {
				return new StringValue((String) envObject);
			}
			else 
			if (envObject instanceof Number) {
				return new NumberValue(((Number)envObject).doubleValue());
			}
			else
			if (envObject instanceof Boolean) {
				return new BooleanValue(Boolean.valueOf((boolean) envObject));
			}
			else {
				throw new NotImplementedException("FIXME: translation to Value, " + envObject + "@" + envObject.getClass().getName() + " found for " + variableName);
			}	
		}
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
	public String toFormulaeShort() {
		Operation operand0 = getOperands().get(0);
		Optional<Value<?>> optParameter0 = operand0.getOrRunParameter(null);
		String valueAsString = ((StringableValue) optParameter0.orElse(new StringValue(operand0.toFormulaeShort()))).getValueAsString().replaceAll("\"","");
		String[] split = valueAsString.split("\\.");
		String variableName = split[split.length-2] + "." + split[split.length-1];
		return variableName;
	}

	@Override
	public boolean isParameterDataSensitive() {
		return true;
	}

}
