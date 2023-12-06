package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.google.gson.JsonElement;
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
			else
				if (envObject instanceof Map) {
					return new NamedListValue((Map<String, ?>) envObject);
				}
			else
				if (envObject instanceof JsonObject) {
					Set<Entry<String, JsonElement>> entrySet = ((JsonObject) envObject).entrySet();
					return new NamedListValue((Map<String, ?>) entrySet.stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getAsString())));
				}
			else {
				throw new NotImplementedException("FIXME: translation to Value, " + envObject + "@" + envObject.getClass().getName() + " found for " + variableName);
			}	
		}
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		Optional<Value<?>> thisParameter = this.getOrRunParameter(targetStock);
		String valueAsString = ((StringableValue) thisParameter.orElse(new StringValue(""))).getValueAsString().replaceAll("\"","");
		if (valueAsString.isEmpty()) return "";
		String[] split = valueAsString.split("\\.");
		String variableName = split[split.length-1];
		return variableName;
	}

}
