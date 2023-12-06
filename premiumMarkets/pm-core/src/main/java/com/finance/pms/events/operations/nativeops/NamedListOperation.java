package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

@XmlRootElement
public class NamedListOperation extends Operation {
	
	public NamedListOperation() {
		super("namedListOfThings","Named list of things (only implemented for numbers).");
	}
	
	public NamedListOperation(String reference, String description) {
		super(reference, description);
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
		double[] valuesOps = inputs.stream().skip(size/2).mapToDouble(v -> ((Number) v.getValue(targetStock)).doubleValue()).toArray();
		return new NumberArrayValue(valuesOps, namesOps, 0);
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
	public  Value<?> emptyValue() {
		return null;
	}

}
