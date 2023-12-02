package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.AndThenException;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.MapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public class AndOperation extends Operation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(AndOperation.class);
	
	public AndOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public AndOperation() {
		this("fAnd", "Runs the first operand, if the first operand has no error and a result and is not false, runs the second operand, and so on until all inputs are run. "
				+ "Will return the last input results in case of success. "
				+ "Will return false if any of the inputs fails or has no result or returns false.",
			 new OperationReferenceOperation("operationReference", "operation", "operation", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public AndOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	
	

	@Override
	public Value<?> run(TargetStockInfo targetStock, List<StackElement> parentCallStack, int thisOutputRequiredStartShiftByParent) {
		try {
			return super.run(targetStock, parentCallStack, thisOutputRequiredStartShiftByParent);
		} catch (Exception e) {
			Throwable rootCause = e;
		    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
		        rootCause = rootCause.getCause();
		    }
			if (rootCause instanceof AndThenException) {
				LOGGER.warn("One Operand of " + this.getReference() + " failed with AndThenException " + e);
			} else {
				LOGGER.error("One Operand of " + this.getReference() + " failed with " + e, e);
			}
			return new StringValue("FALSE");
		}
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Optional<Value<?>> res = Optional.empty();
		for (Value<?> i : inputs) {
			Value<?> opiRes = null;
			if (i instanceof OperationReferenceValue) {
				Operation opi = (Operation) ((OperationReferenceValue<?>) i).getValue(targetStock).clone();
				try {
					opiRes = opi.run(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis);
					LOGGER.info("Operand " + opi.getReference() + " of " + this.getReference() + " result " + opiRes);
				} catch (Exception e) {
					Throwable rootCause = e;
				    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
				        rootCause = rootCause.getCause();
				    }
					if (rootCause instanceof AndThenException) {
						LOGGER.warn("One Operand of " + this.getReference() + " failed with AndThenException " + e);
					} else {
						LOGGER.error("One Operand of " + this.getReference() + " failed with " + e, e);
					}
				}
			} else {
				opiRes = i;
			}
			if (isFalse(targetStock, opiRes)) {
				res = Optional.empty();
				break;
			}
			res = Optional.of(opiRes);
		};
		return res.orElse(new DoubleMapValue()); //orElse empty DoubleMapValue for convenience as this the most likely expected output

	}

	private boolean isFalse(TargetStockInfo targetStock, Value<?> opiRes) {
		return opiRes == null || 
				(opiRes instanceof StringableValue && ((StringableValue) opiRes).getValueAsString().replaceAll("\"","").equalsIgnoreCase("FALSE")) ||
				(opiRes instanceof MapValue && ((MapValue<?>) opiRes).getValue(targetStock).isEmpty());
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
	public void invalidateOperation(String analysisName, Optional<TargetStockInfo> targetStock, Optional<String> userOperationName) {
	}
	
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		String thisShortName = "fAnd";
		String opsFormulaeShort = super.toFormulaeShort(targetStock);
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
	
	@Override
	public String resultHint(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStockInfo);
		String reduce = getOperands().stream()
				.map(e -> e.resultHint(targetStockInfo, thisCallStack))
				.filter(e -> !e.isEmpty())
				.reduce((r, e) -> r + " and " + e).orElse("");
		return reduce;
	}
	
//	private <T> T reccurentProceeds(Function<Operation, T> f, BiFunction<T, T, T> g) {
//		return getOperands().stream()
//			.map(o -> f.apply(o))
//			.reduce((a, o) -> g.apply(a, o))
//			.orElseThrow();
//	}
//	
//	@Override
//	public Set<QuotationDataType> getRequiredStockData() {
//		return this.<Set<QuotationDataType>>reccurentProceeds(o -> o.getRequiredStockData(), (a,b) -> {
//			a.addAll(b);
//			return a;
//		});
//	}
//	
////	@Override
////	public void interrupt() throws Exception {
////		Value<?> value0 = this.getOperands().get(0).run(targetStock, "", 0);
////		if (value0 != null) {
////			this.<Void>reccurentProceeds(value0, o -> {o.interrupt(); return null;});
////		} else {
////			throw new RuntimeException();
////		}
////	}
//
//	@Override
//	public void invalidateAllNonIdempotentOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
//		getOperands().stream().forEach(o -> o.invalidateAllNonIdempotentOperands(analysisName, targetStock, userOperationName));
//	}
//	
//	@Override
//	public void invalidateAllForciblyOperands(String analysisName, TargetStockInfo targetStock, Optional<String> userOperationName) {
//		getOperands().stream().forEach(o -> o.invalidateAllForciblyOperands(analysisName, targetStock, userOperationName));
//
//	}
//	
//	@Override
//	public Boolean isIdemPotent(TargetStockInfo targetStock) {
//		return this.<Boolean>reccurentProceeds(o -> o.isIdemPotent(targetStock), (a,b) -> a && b);
//	}
//
//	@Override
//	public Boolean isNoOverrideDeltaOnly(TargetStockInfo targetStock) {
//		return this.<Boolean>reccurentProceeds(o -> o.isNoOverrideDeltaOnly(targetStock), (a,b) -> a || b);
//	}
//
//	
//	@Override
//	public boolean isParameterDataSensitive() {
//		return this.<Boolean>reccurentProceeds(o -> o.isParameterDataSensitive(), (a,b) -> a || b);
//	}
//	
//	@Override
//	public int operandsRequiredStartShiftRecursive(TargetStockInfo targetStock, int thisOperationStartShift) {
//		return this.<Integer>reccurentProceeds(o -> o.operandsRequiredStartShiftRecursive(targetStock, thisOperationStartShift), (a,b) -> Math.max(a, b));
//	}

}
