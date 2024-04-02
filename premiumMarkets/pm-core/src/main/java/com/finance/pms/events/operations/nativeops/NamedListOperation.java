package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

@XmlRootElement
public class NamedListOperation extends Operation {

	
	public NamedListOperation() {
		super("namedListOfThings","Named list of things.");
	}
	
	public NamedListOperation(String reference) {
		super(reference, reference);
	}

	public NamedListOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}
	
	public NamedListOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public NamedListOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public NamedListOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		int size = inputs.size();
		List<String> namesOps = inputs.stream().limit(size/2).map(v -> (String) v.getValue(targetStock)).collect(Collectors.toList());
		@SuppressWarnings("unchecked")
		List<Object> valuesOps = inputs.stream().skip(size/2).map(v -> {
			Object value = v.getValue(targetStock);
			if (value instanceof Collection) {
				return listOParams((Collection<Value<?>>) value, targetStock);
			} else {
				return otherScalar(v, targetStock);
			}
		}).collect(Collectors.toList());
		Map<String, Object> map = IntStream.range(0, namesOps.size())
				.mapToObj(i -> i)
				.collect(HashMap::new, (a, i) -> a.put(namesOps.get(i), valuesOps.get(i)), HashMap::putAll);
				//.collect(Collectors.toMap(i -> namesOps.get(i), i -> valuesOps.get(i), (a, b) -> a, HashMap::new)); //NullPointer
		return new NamedListValue(map);
	}

	@SuppressWarnings("unchecked")
	private Object listOParams(Collection<Value<?>> collection, TargetStockInfo targetStock) {
		return collection.stream()
		.map(v -> {
			Object value = v.getValue(targetStock);
			if (value instanceof Collection) {
				return listOParams((Collection<Value<?>>) value, targetStock);
			} else {
				return otherScalar(v, targetStock);
			}
		})
		.collect(Collectors.toList());
	}
	
	private Object otherScalar(Value<?> v, TargetStockInfo targetStock) {
		Object value = v.getValue(targetStock);
		if (v instanceof StringValue && ((StringValue) v).isBoolean()) {
			return Boolean.valueOf(value.toString());
		}
		if (v instanceof StringValue && ((StringValue) v).isNone()) {
			return null;
		}
		if (v instanceof NumberValue && ((Number) value).doubleValue() == ((Number) value).intValue()) {
			return Integer.valueOf(((Number) value).intValue());
		}
		return v.getValue(targetStock);
	}

	@Override
	public String toFormulae(TargetStockInfo targetStock) {
		int size = this.getOperands().size();
		List<Operation> namesOps = this.getOperands().stream().limit(size/2).collect(Collectors.toList());
		List<Operation> valuesOps = this.getOperands().stream().skip(size/2).collect(Collectors.toList());
		return "{" + IntStream.range(0, namesOps.size()).mapToObj(i ->  namesOps.get(i).toFormulae(targetStock) + ":" + valuesOps.get(i).toFormulae(targetStock)).reduce((a,e) -> a + "," + e).orElse("") + "}";
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public Value<?> emptyValue() {
		return new NamedListValue();
	}

}
