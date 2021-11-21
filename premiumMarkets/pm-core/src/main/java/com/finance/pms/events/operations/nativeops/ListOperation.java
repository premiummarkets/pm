package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class ListOperation extends Operation {
	
	public ListOperation() {
		super("list of things","List of things.");
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
	public AnyValueListValue<?> calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return new AnyValueListValue<>(inputs);
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		// TODO Auto-generated method stub
	}

}
