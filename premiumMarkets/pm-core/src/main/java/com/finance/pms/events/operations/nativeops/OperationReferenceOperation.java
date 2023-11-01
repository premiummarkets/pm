package com.finance.pms.events.operations.nativeops;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.quotations.QuotationDataType;

@XmlRootElement
public class OperationReferenceOperation extends Operation implements LeafOperation {
	
	public OperationReferenceOperation() {
		super("operationReference", "Operation reference name");
	}
	
	public OperationReferenceOperation(String reference) {
		super(reference, reference);
	}
	
	public OperationReferenceOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public OperationReferenceValue<?> calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return (OperationReferenceValue<?>) inputs.get(0).getValue(targetStock);
	}
	
	@Override
	public String toFormulae() {
		return "$" + ((StringableValue) getParameter()).getValueAsString() + "$";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toFormulaeDevelopped() {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(null).toFormulaeDevelopped();
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<QuotationDataType> getRequiredStockData() {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(null).getRequiredStockData();
		} else {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void interrupt() throws Exception {
		if (this.getParameter() != null) {
			((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(null).interrupt();
		} else {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void invalidateAllNonIdempotentOperands(TargetStockInfo targetStock, String analysisName, Optional<Stock> stock) {
		if (this.getParameter() != null) {
			((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(targetStock).invalidateAllNonIdempotentOperands(targetStock, analysisName, stock);
		} else {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(targetStock).isIdemPotent(targetStock);
		} else {
			throw new RuntimeException();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(targetStock).isNoOverrideDeltaOnly(targetStock);
		} else {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isParameterDataSensitive() {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(null).isParameterDataSensitive();
		} else {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
		if (this.getParameter() != null) {
			return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getValue(targetStock).operandsRequiredStartShiftRecursive(targetStock, thisOperationStartShift);
		} else {
			throw new RuntimeException();
		}
	}

}
