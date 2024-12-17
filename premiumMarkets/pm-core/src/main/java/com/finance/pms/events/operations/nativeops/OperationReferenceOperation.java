package com.finance.pms.events.operations.nativeops;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public String toFormulae(TargetStockInfo targetStock, List<StackElement> parentCallStack) {
//		return ((StringableValue) getParameter()).getAsStringable();
		return ((OperationReferenceValue<? extends Operation>) this.getParameter()).getAsStringable();
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo stockInfo, List<StackElement> thisCallStack) {
		return reccurentProceeds((ov) -> ov.getValue(null).toFormulaeShort(stockInfo, thisCallStack), ov -> ov.getAsStringable());
	}
	
	@Override
	public String toFormulaeDevelopped() {
//		return reccurentProceeds((ov) -> ov.getValue(null).toFormulaeDevelopped(), ov -> ov.getAsStringable());
		return reccurentProceeds(ov -> ov.getAsStringable(), ov -> ov.getAsStringable());
	}
	
	@Override
	public String toFormulaeFormated(int length, Function<Operation, String> formulaeGenFunc) {
//		return reccurentProceeds(ov -> ov.getValue(null).toFormulaeFormated(length, formulaeGenFunc), ov -> ov.getAsStringable());
		return reccurentProceeds(ov -> ov.getAsStringable(), ov -> ov.getAsStringable());
	}

	private <T> T reccurentProceeds(Function<OperationReferenceValue<? extends Operation>, T> isUsedAsIsFunc, Function<OperationReferenceValue<? extends Operation>, T> isUsedAsCloneFunc) {
		@SuppressWarnings("unchecked")
		OperationReferenceValue<? extends Operation> operationReferenceValue = (OperationReferenceValue<? extends Operation>) this.getParameter();
		Boolean isUsedAsClone = operationReferenceValue.getIsUsedAsClone();
		if (isUsedAsClone) {
			return isUsedAsCloneFunc.apply(operationReferenceValue);
		} else {
			return isUsedAsIsFunc.apply(operationReferenceValue);
		}
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return reccurentProceeds((ov) -> ov.getValue(targetStock).operandsRequiredStartShift(targetStock, thisCallStack, thisParentStartShift), ov -> 0);
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStockOpt, Optional<String> userOperationName) {
		if (targetStockOpt.isPresent()) {
			TargetStockInfo targetStock = targetStockOpt.get();
			this.<Void>reccurentProceeds(ov -> {
				try {
					Operation value = ov.getValue(targetStock);
					value.invalidateOperation(analysisName, targetStockOpt, Optional.of(value.getReference()));
				} catch (Exception e) {
					throw new RuntimeException();
				} 
				return null;
			}, 
			ov -> null);
		}
	}
	
	@Override
	public Optional<String> calculationStatus(TargetStockInfo targetStock, List<StackElement> callStack) {
		return reccurentProceeds((ov) -> ov.getValue(targetStock).calculationStatus(targetStock, callStack), ov -> Optional.empty());
	}

	@Override
	public void invalidateAllNonIdempotentOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
		this.<Void>reccurentProceeds(ov -> {
			try {
				Operation value = ov.getValue(targetStock);
				value.invalidateAllNonIdempotentOperands(analysisName, targetStock, Optional.of(value.getReference()));
			} catch (Exception e) {
				throw new RuntimeException();
			} 
			return null;
		}, 
		ov -> null);
	}
	
	@Override
	public void invalidateAllForciblyOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
		this.<Void>reccurentProceeds(ov -> {
			try {
				Operation value = ov.getValue(targetStock);
				value.invalidateAllForciblyOperands(analysisName, targetStock, Optional.of(value.getReference()));
			} catch (Exception e) {
				throw new RuntimeException();
			} 
			return null;
		}, 
		ov -> null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Value<?> emptyValue() {
		return ((OperationReferenceValue<? extends Operation>)this.getParameter()).getValue(null).emptyValue();
	}
	
	@Override
	public Set<QuotationDataType> getRequiredStockData() {
		return reccurentProceeds(ov -> ov.getValue(null).getRequiredStockData(), ov -> ov.getValue(null).getRequiredStockData());
	}
	
	@Override
	public void interrupt() throws Exception {
		Function<OperationReferenceValue<? extends Operation>, Void> func = ov -> {
				try {
					ov.getValue(null).interrupt();
				} catch (Exception e) {
					throw new RuntimeException();
				} 
				return null;
			};
		this.<Void>reccurentProceeds(func, func);
	}
	
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		return reccurentProceeds(ov -> ov.getValue(targetStock).isIdemPotent(targetStock), ov -> ov.getValue(targetStock).isIdemPotent(targetStock));
	}
	
	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		return reccurentProceeds(ov -> ov.getValue(targetStock).isNoOverrideDeltaOnly(targetStock), ov -> ov.getValue(targetStock).isNoOverrideDeltaOnly(targetStock));
	}
	
//	//@SuppressWarnings("unchecked")
//	@Override
//	public boolean isForbidThisParameterValue() {
//		return false; //((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).isForbidThisParameterValue();
//	}
//	
//	//@SuppressWarnings("unchecked")
//	@Override
//	public boolean isDataShiftSensitive() {
//		return false; //((OperationReferenceValue<? extends Operation>) this.getOrRunParameter(null).orElseThrow()).getValue(null).isDataShiftSensitive();
//	}

	@Override
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOperationStartShift) {
		Function<OperationReferenceValue<? extends Operation>, Integer> func = (ov) -> ov.getValue(null)
				.operandsRequiredStartShiftRecursive(targetStock, thisCallStack, thisOperationStartShift);
		return reccurentProceeds(func, func);
	}

	@Override
	public String resultHint(TargetStockInfo targetStock, List<StackElement> callStack) {
		return reccurentProceeds(ov -> ov.getValue(targetStock).resultHint(targetStock, addThisToStack(callStack, 0, targetStock)), ov -> ov.getAsStringable());
	}
	
}
