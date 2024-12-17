package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

@XmlRootElement
public class ListOperation extends Operation {
	
	public ListOperation() {
		super("listOfThings","List of things.");
	}
	
	public ListOperation(String reference, String description) {
		super(reference, description);
	}

	public ListOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}
	
	public ListOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public ListOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	public ListOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public AnyValueListValue<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//return new AnyValueListValue<>(inputs.stream().map(i -> (StringableValue) i).collect(Collectors.toList()));
		return new AnyValueListValue<>(inputs);
	}


	@Override
	public String toFormulae(TargetStockInfo targetStock, List<StackElement> parentCallStack) {
		return "[" + this.getOperands().stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulae(targetStock, parentCallStack), (a, b) -> a + b) + "]";
	}
	

	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		return "lO" + this.getOperands().size();
	}

	@Override
	public String toFormulaeDevelopped() {
		return "[" + this.getOperands().stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulaeDevelopped(), (a, b) -> a + b) + "]";
	}

	@Override
	public String toFormulaeFormated(int length, Function<Operation, String> formulaeGenFunc) {
		return "[" + this.getOperands().stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulaeFormated(length, formulaeGenFunc), (a, b) -> a + b) + "]";
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}

	@Override
	public  AnyValueListValue<?> emptyValue() {
		return new AnyValueListValue<>();
	}

}
