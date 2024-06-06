package com.finance.pms.events.operations.nativeops.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.AndThenException;
import com.finance.pms.events.operations.FlowException;
import com.finance.pms.events.operations.FlowOperation;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.MapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public class AndOperation extends FlowOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(AndOperation.class);
	
	public AndOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public AndOperation() {
		this("fAnd", "And Then operation. "
				+ "Runs the first operand, if the first operand has no error and a result and is not false, runs the second operand, and so on until all inputs are run. "
				+ "Will return the last input results in case of success. "
				+ "Will throw a flow exception if any of the inputs fails or has no result or returns false. "
				+ "To insure the flow, the first operand can be of any kind but the other operands should be operation references. ",
			 new OperationReferenceOperation("operationReference", "operation", "operation", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public AndOperation(ArrayList<Operation> operands, String outputSelector) {
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
	protected Boolean stopOperandsCalculationsOnError(Exception exception, Operation operand) {
		LOGGER.info(this.getReference() + " will stop on operand '" + operand.getReference() + "' calculation with exception: " + exception);  
		return true;
	}
	
	@Override
	protected boolean stopOperandsCalculationsOnCondition(TargetStockInfo stockInfo, Operation operand, Value<?> callRes) {
		boolean isFalse = isFalse(stockInfo, callRes);
		LOGGER.info(this.getReference() + " will stop on operand '" + operand.getReference() + "' calculation if true==" + isFalse + ", from result: " + callRes);
		return isFalse; //stop if false => true
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisOutputRequiredStartShiftByParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Optional<Value<?>> res = Optional.empty();
		Throwable rootCause = null;
		int iCpt = 0;
		for (Value<?> i : inputs) {
			Value<?> opiRes = null;
			if (i instanceof OperationReferenceValue) {
				Operation opi = (Operation) ((OperationReferenceValue<?>) i).getValue(targetStock);
				try {
					opiRes = opi.run(targetStock, thisCallStack, thisInputOperandsRequiredShiftFromThis);
					LOGGER.info("Operand " + opi.getReference() + " of " + this.getReference() + " result " + opiRes);
				} catch (Exception e) {
					rootCause = e;
				    while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
				        rootCause = rootCause.getCause();
				    }
					if (rootCause instanceof AndThenException) {
						LOGGER.warn("Operand "  + opi.getReference() +  " of " + this.getReference() + " failed with AndThenException: " + e);
					} else {
						LOGGER.error("Operand " + opi.getReference() +  " of " + this.getReference() + " failed with " + e, e);
					}
				}
			} else {
				opiRes = i;
			}
			if (isFalse(targetStock, opiRes)) {
				LOGGER.info(this.getOperands().get(iCpt).getReference() + " is false and will stop this: " + this.getReference());
				res = Optional.empty();
				break;
			}
			res = Optional.of(opiRes);
			LOGGER.info(this.getOperands().get(iCpt).getReference() + " is " + res + " in this: " + this.getReference());
			if (iCpt < inputs.size() -1) iCpt++;
		};
		//return res.orElse(new DoubleMapValue()); //orElse empty DoubleMapValue for convenience as this the most likely expected output
		final Throwable fRootCause = rootCause;
		final int fICpt = iCpt;
		return res.orElseThrow(() -> new FlowException(this.getReference() + " 'AND' one operand is false (stopped by operand " + this.getOperands().get(fICpt).getReference() + ")" + ((fRootCause != null)?": " + fRootCause:"."), fRootCause));  //Throw to handle roll backs in the service;

	}

	private boolean isFalse(TargetStockInfo targetStock, Value<?> opiRes) {
		return opiRes == null ||
				//StringValue which has to be a boolean (TRUE or FALSE)
				(opiRes instanceof StringValue &&((StringValue) opiRes).getValue(targetStock).equalsIgnoreCase("FALSE")) ||
				//Stringable returns a usable value or a NONE (this is not a boolean)
				(opiRes instanceof StringableValue && ((StringableValue) opiRes).getAsStringable().replaceAll("\"","").equalsIgnoreCase("NONE")) ||
				(opiRes instanceof MapValue && ((MapValue<?>) opiRes).getValue(targetStock).isEmpty());
	}
	
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock) {
		String thisShortName = "fAnd";
		String opsFormulaeShort = super.toFormulaeShort(targetStock, this.getOperands());
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
	
	@Override
	public String resultHint(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStockInfo);
		String reduce = getOperands().stream()
				.map(e -> e.resultHint(targetStockInfo, thisCallStack))
				.filter(e -> !e.isEmpty())
				.reduce((r, e) -> r + " and then " + e).orElse("");
		return reduce;
	}
	

	@Override
	public Value<?> emptyValue() {
		return getOperands().get(getOperands().size()-1).emptyValue();
	}

}
