package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.FlowOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.quotations.QuotationDataType;


//TODO FIXME: double check that the recursive call overrides from Operation are still working ..?
public class IfOperation extends FlowOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(IfOperation.class);
	
	public IfOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public IfOperation() {
		this("If", "If 1rst operand is true then run second operand else run the third operand. "
					+ "To insure the flow, the first operand can be of any kind but the other operands shoudl be operation references. ",
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
	public void setOperands(ArrayList<Operation> overridingOperands) throws IllegalArgumentException {
		overridingOperands.stream().forEach(op -> op.setRunInSequence(true));
		super.setOperands(overridingOperands);
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String ifCondition = ((StringValue) inputs.get(0)).getValue(targetStock);
		Operation thenOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(1)).getValue(targetStock);
		Operation elseOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(2)).getValue(targetStock);
		
		LOGGER.info(this.getReference() + " condition is " + ifCondition + " for " + targetStock.getStock().getSymbol());
		if (ifCondition.equals("TRUE")) {
			LOGGER.info(this.getReference() + " will run " + thenOperationClone.getReference() + " for " + targetStock.getStock().getSymbol());
			return thenOperationClone.run(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis);
		} else {
			LOGGER.info(this.getReference() + " will run " + elseOperationClone.getReference() + " for " + targetStock.getStock().getSymbol());
			return elseOperationClone.run(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis);
		}
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
//		getOperands().get(1).invalidateOperation(analysisName, targetStock, userOperationName);
//		getOperands().get(2).invalidateOperation(analysisName, targetStock, userOperationName);
	}
	
	
	private <T> T reccurentProceeds(Value<?> value0, Function<Operation,T> f) {
		String ifCondition = (String) value0.getValue(null);
		if (ifCondition.equals("TRUE")) {
			return f.apply(getOperands().get(1));
		} else {
			return f.apply(getOperands().get(2));
		}
	}
	
	//FIXME (as parent but with the addition of the switch true false)
	@Override
	public Value<?> emptyValue() {
		return null;
	}

	
//FIXME XXX	
//	@Override
//	public Set<QuotationDataType> getRequiredStockData(TargetStockInfo targetStock) {
//		Value<?> value0 = this.getOperands().get(0).run(targetStock, "", 0);
//		if (value0 != null) {
//			return this.<Set<QuotationDataType>>reccurentProceeds(value0, o -> o.getRequiredStockData(targetStock));
//		} else {
//			throw new RuntimeException();
//		}
//	}
	@Override
	public Set<QuotationDataType> getRequiredStockData() {
		return new HashSet<>(Arrays.asList(QuotationDataType.values()));
	}
	
	
//	@Override
//	public void interrupt() throws Exception {
//		Value<?> value0 = this.getOperands().get(0).run(targetStock, null, 0);
//		if (value0 != null) {
//			this.<Void>reccurentProceeds(value0, o -> {o.interrupt(); return null;});
//		} else {
//			throw new RuntimeException();
//		}
//	}

	@Override
	public void invalidateAllNonIdempotentOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
//		XXX we can't evaluate the condition at this point within reasonable calculation time
//		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, "(" + targetStock.getStock().getSymbol() + ") "  + this.shortOutputReference(), 0));
//		if (value0 != null) {
//			this.<Void>reccurentProceeds(value0, o -> {o.invalidateAllNonIdempotentOperands(targetStock, analysisName, stock); return null;});
//		} else {
//			throw new RuntimeException();
//		}
		getOperands().get(1).invalidateAllNonIdempotentOperands(analysisName, targetStock, userOperationName);
		getOperands().get(2).invalidateAllNonIdempotentOperands(analysisName, targetStock, userOperationName);
	}
	
	@Override
	public void invalidateAllForciblyOperands(String analysisName, TargetStockInfo targetInfo, Optional<String> userOperationName) {
		getOperands().get(1).invalidateAllForciblyOperands(analysisName, targetInfo, userOperationName);
		getOperands().get(2).invalidateAllForciblyOperands(analysisName, targetInfo, userOperationName);
	}
	
	@Override
	public Boolean isIdemPotent(TargetStockInfo targetStock) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, newCallerStack(targetStock), 0));
		if (value0 != null) {
			return this.<Boolean>reccurentProceeds(value0, o -> o.isIdemPotent(targetStock));
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, newCallerStack(targetStock), 0));
		if (value0 != null) {
			return this.<Boolean>reccurentProceeds(value0, o -> o.isNoOverrideDeltaOnly(targetStock));
		} else {
			throw new RuntimeException();
		}
	}
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		String thisShortName = "if";
		String opsFormulaeShort = super.toFormulaeShort(targetStock, this.getOperands());
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
	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, newCallerStack(targetStock), 0));
		if (value0 != null) {
			return this.<Integer>reccurentProceeds(value0, o -> o.operandsRequiredStartShiftRecursive(targetStock, thisOperationStartShift));
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public String resultHint(TargetStockInfo targetStock, List<StackElement> callStack) {
		Value<?> value0 = this.getOperands().get(0).getOrRunParameter(targetStock).orElse(this.getOperands().get(0).run(targetStock, newCallerStack(targetStock), 0));
		if (value0 != null) {
			List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStock);
			return this.<String>reccurentProceeds(value0, o -> o.resultHint(targetStock, thisCallStack));
		} else {
			throw new RuntimeException();
		}
	}

}
