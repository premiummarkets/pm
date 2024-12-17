package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.admin.config.SystemEnvironment;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.VarOperation;
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.finance.pms.events.operations.nativeops.NamedListValue;
import com.finance.pms.events.operations.nativeops.NullOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EnvOperation extends VarOperation {
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String variableName = ((StringValue) inputs.get(0)).getValue(targetStock);
		Value<?> defaultValue = inputs.get(1);
		
		Object envObject = SystemEnvironment.getInstance().read(targetStock.getStock(), variableName).orElse(null);
		
		if (envObject == null) {
			if (defaultValue instanceof NumberValue &&  //if default is NaN than this means orThrow and the value is needed -> Throw an Exception.
					((NumberValue)defaultValue).getValue(targetStock).equals(Double.NaN)) {
				//LOGGER.warn(this.getReference() + ": No value for " + variableName + ", " + envObject);
				//throw new RuntimeException("Value needed and not provided for " + variableName); //Can't do this at it will mark this operation as failed
				return this.emptyValue();
			} else {
				//LOGGER.warn(this.getReference() + ": Value is null for " + variableName + ", " + envObject + ". Using default: " + defaultValue);
				envObject = defaultValue;
			}
		}
		
		if (envObject instanceof Value) {
			return (Value<?>) envObject;
		} 
		else if (envObject instanceof String) {
			return new StringValue((String) envObject);
		}
		else if (envObject instanceof Number) {
			return new NumberValue(((Number) envObject).doubleValue());
		}
		else if (envObject instanceof Boolean) {
			return new BooleanValue(Boolean.valueOf((boolean) envObject));
		}
		else if (envObject instanceof Map) {
			return new NamedListValue((Map<String, ?>) envObject);
		}
		else if (envObject instanceof JsonObject) {
			Map<String, Object> myMap = new Gson().fromJson(envObject.toString(), HashMap.class);
			return new NamedListValue(myMap);
		}
		else {
			throw new NotImplementedException("FIXME: translation to Value, " + envObject + "@" + envObject.getClass().getName() + " found for " + variableName);	
		}
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		Optional<Value<?>> thisParameter = this.getOrRunParameter(targetStock, thisCallStack);
		String valueAsString = ((StringableValue) thisParameter.orElse(new StringValue(""))).getAsStringable().replaceAll("\"","");
		if (valueAsString.isEmpty()) return "";
		String[] split = valueAsString.split("\\.");
		String variableName = split[split.length-1];
		return variableName;
	}

}
