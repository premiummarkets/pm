package com.finance.pms.events.operations;

import java.util.concurrent.ConcurrentHashMap;

import com.finance.pms.events.operations.nativeops.MultiValue;
import com.finance.pms.events.operations.nativeops.Value;

public class TargetStockHeap extends ConcurrentHashMap<String, Value<?>> {

	private static final long serialVersionUID = 195728989008457606L;
	
	public Value<?> getHeapVar(String variableName) {
		
		synchronized (this) {
			String[] nameSplit = variableName.split("\\.");
			if (nameSplit.length > 1) { //Composite name
				Value<?> variableValue = this.get(nameSplit[0]);
				if (variableValue instanceof MultiValue) {
					Value<?> value = ((MultiValue) variableValue).getAdditionalOutputs().get(nameSplit[1]);
					if (value == null) {
						throw new RuntimeException("getHeapVar Name " + nameSplit[1] + " not found in variable " + variableName + ": " + variableValue);
					}
					return value;
				}
			}
			
			return this.get(variableName);
		}
		
	}
	
	//Supports Stringable Value and Map like Multivalue objects
	public Value<?> letHeapVar(String variableName, Value<?> variableValue) {
		
		synchronized (this) {
			String[] nameSplit = variableName.split("\\.");
			if (nameSplit.length > 1) { //Composite name
				if (variableValue instanceof MultiValue) {
					this.put(nameSplit[0], variableValue);
					Value<?> value = ((MultiValue) variableValue).getAdditionalOutputs().get(nameSplit[1]);
					if (value == null) {
						throw new RuntimeException("letHeapVar Name " + nameSplit[1] + " not found in variable " + variableName + ": " + variableValue);
					}
					return value;
				}
			}
			
			this.put(variableName, variableValue);
			return variableValue;
		}
		
	}

	public String toCsv() {
		return "\"" + this.entrySet().stream()
				.map(e -> e.getKey() + ": " + e.getValue().getValue(null).toString())
				.reduce((a, e) -> a + ",\n " + e).orElse("") + "\"" ;
 	}

}
