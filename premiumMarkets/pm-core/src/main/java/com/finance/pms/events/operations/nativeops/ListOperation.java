package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

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
	public AnyValueListValue<?> calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new AnyValueListValue<>(inputs);
	}


	@Override
	public String toFormulae() {
		return "[" + this.getOperands().stream().reduce("", (r, e) -> r + ((r.isEmpty())?"":",") + e.toFormulae(), (a, b) -> a + b) + "]";
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock) {
		// TODO Auto-generated method stub
	}

	@Override
	public  AnyValueListValue<?> emptyValue() {
		return new AnyValueListValue<>();
	}

}
