package com.finance.pms.events.operations.nativeops;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
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
	public OperationReferenceValue<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return (OperationReferenceValue<?>) inputs.get(0).getValue(targetStock);
	}
	
	@Override
	public String toFormulae() {
		return "$" + ((StringableValue) getOrRunParameter(null).orElseThrow()).getValueAsString() + "$";
	}
	
//	@Override
//	public String toFormulaeShort() {
//		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).toFormulaeShort();
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toFormulaeDevelopped() {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).toFormulaeDevelopped();
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStockOpt, Optional<String> userOperationName) {
		if (targetStockOpt.isPresent()) {
			TargetStockInfo targetStock = targetStockOpt.get();
			Operation value = ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock);
			value.invalidateOperation(analysisName, targetStockOpt, Optional.of(value.getReference()));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void invalidateAllNonIdempotentOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
		Operation value = ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock);
		value.invalidateAllNonIdempotentOperands(analysisName, targetStock,  Optional.of(value.getReference()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void invalidateAllForciblyOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
		Operation value = ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock);
		value.invalidateAllForciblyOperands(analysisName, targetStock, Optional.of(value.getReference()));
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<QuotationDataType> getRequiredStockData() {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).getRequiredStockData();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void interrupt() throws Exception {
		((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).interrupt();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock).isIdemPotent(targetStock);	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock).isNoOverrideDeltaOnly(targetStock);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isParameterDataSensitive() {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).isParameterDataSensitive();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
		return ((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(targetStock).orElseThrow()).getValue(targetStock).operandsRequiredStartShiftRecursive(targetStock, thisOperationStartShift);
	}

}
