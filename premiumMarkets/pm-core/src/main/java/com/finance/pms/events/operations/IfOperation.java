package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

public class IfOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(IfOperation.class);
	
	public IfOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public IfOperation() {
		this("If", "If 1rst operand is true then run second operand else run the third operand.",
		     new StringOperation("condition", "if", "if condition", new StringValue("TRUE")),
			 new OperationReferenceOperation("operationReference", "then", "then", null),
			 new OperationReferenceOperation("operationReference", "else", "else", null));
	}

	public IfOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, String thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String ifCondition = ((StringValue) inputs.get(0)).getValue(targetStock);
		Operation thenOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(1)).getValue(targetStock).clone();
		Operation elseOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(2)).getValue(targetStock).clone();
		
		LOGGER.info(this.getReference() + " condition is " + ifCondition + " for " + targetStock.getStock().getSymbol());
		if (ifCondition.equals("TRUE")) {
			LOGGER.info(this.getReference() + " will run " + thenOperationClone.getReference() + " for " + targetStock.getStock().getSymbol());
			return thenOperationClone
					.run(targetStock, addThisToStack(thisCallStack, thisInputOperandsRequiredShiftFromThis, 0, targetStock), thisInputOperandsRequiredShiftFromThis);
		} else {
			LOGGER.info(this.getReference() + " will run " + elseOperationClone.getReference() + " for " + targetStock.getStock().getSymbol());
			return elseOperationClone
					.run(targetStock, addThisToStack(thisCallStack, thisInputOperandsRequiredShiftFromThis, 0, targetStock), thisInputOperandsRequiredShiftFromThis);
		}
	}

	@Override
	public Value<?> emptyValue() {
		return null;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock) {
	}
	
	
	private <T> T reccurentProceeds(Value<?> value0, Function<Operation,T> f) {
		String ifCondition = (String) value0.getValue(null);
		if (ifCondition.equals("TRUE")) {
			return f.apply(getOperands().get(1));
		} else {
			return f.apply(getOperands().get(2));
		}
	}
	
//	@Override
//	public Set<QuotationDataType> getRequiredStockData(TargetStockInfo targetStock) {
//		Value<?> value0 = this.getOperands().get(0).run(targetStock, "", 0);
//		if (value0 != null) {
//			return this.<Set<QuotationDataType>>reccurentProceeds(value0, o -> o.getRequiredStockData(targetStock));
//		} else {
//			throw new RuntimeException();
//		}
//	}
	
//	@Override
//	public void interrupt() throws Exception {
//		Value<?> value0 = this.getOperands().get(0).run(targetStock, "", 0);
//		if (value0 != null) {
//			this.<Void>reccurentProceeds(value0, o -> {o.interrupt(); return null;});
//		} else {
//			throw new RuntimeException();
//		}
//	}

	@Override
	public void invalidateAllNonIdempotentOperands(TargetStockInfo targetStock, String analysisName) {
//		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") "  + this.shortOutputReference(), 0));
//		if (value0 != null) {
//			this.<Void>reccurentProceeds(value0, o -> {o.invalidateAllNonIdempotentOperands(targetStock, analysisName, stock); return null;});
//		} else {
//			throw new RuntimeException();
//		}
		getOperands().get(1).invalidateAllNonIdempotentOperands(targetStock, analysisName);
		getOperands().get(2).invalidateAllNonIdempotentOperands(targetStock, analysisName);
	}
	
	@Override
	public void invalidateAllForciblyOperands(TargetStockInfo targetInfo, String analysisName) {
		getOperands().get(1).invalidateAllForciblyOperands(targetInfo, analysisName);
		getOperands().get(2).invalidateAllForciblyOperands(targetInfo, analysisName);
	}
	
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") "  + this.shortOutputReference(), 0));
		if (value0 != null) {
			return this.<Boolean>reccurentProceeds(value0, o -> o.isIdemPotent(targetStock));
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") "  + this.shortOutputReference(), 0));
		if (value0 != null) {
			return this.<Boolean>reccurentProceeds(value0, o -> o.isNoOverrideDeltaOnly(targetStock));
		} else {
			throw new RuntimeException();
		}
	}
	
	
	@Override
	public String toFormulaeShort() {
		String thisShortName = "if";
		String opsFormulaeShort = super.toFormulaeShort();
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
//	@Override
//	public boolean isParameterDataSensitive() {
//		Value<?> value0 = this.getOperands().get(0).run(targetStock, "", 0);
//		if (value0 != null) {
//			return this.<Boolean>reccurentProceeds(value0, o -> o.isQuotationsDataSensitive(targetStock));
//		} else {
//			throw new RuntimeException();
//		}
//	}
	@Override
	public boolean isParameterDataSensitive() {
		return true;
	}
	
	@Override
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") "  + this.shortOutputReference(), 0));
		if (value0 != null) {
			return this.<Integer>reccurentProceeds(value0, o -> o.operandsRequiredStartShiftRecursive(targetStock, thisOperationStartShift));
		} else {
			throw new RuntimeException();
		}
	}

}
