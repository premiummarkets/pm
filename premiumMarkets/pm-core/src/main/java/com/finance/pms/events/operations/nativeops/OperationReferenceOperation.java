package com.finance.pms.events.operations.nativeops;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class OperationReferenceOperation extends Operation implements LeafOperation {
	
	public OperationReferenceOperation() {
		super("operation reference", "Operation reference name");
	}
	
	public OperationReferenceOperation(String reference) {
		super(reference, reference);
	}
	
	public OperationReferenceOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public OperationReferenceValue<?> calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return (OperationReferenceValue<?>) inputs.get(0).getValue(targetStock);
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
	}

}
