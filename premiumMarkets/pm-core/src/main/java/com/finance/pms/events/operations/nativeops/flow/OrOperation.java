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
import com.finance.pms.events.operations.conditional.BooleanValue;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;
import com.finance.pms.events.operations.nativeops.MapValue;
import com.finance.pms.events.operations.nativeops.OperationReferenceOperation;
import com.finance.pms.events.operations.nativeops.OperationReferenceValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public class OrOperation extends FlowOperation {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(OrOperation.class);
	
	public OrOperation(String reference, String description, Operation ... operands) {
		super(reference, description,  new ArrayList<Operation>(Arrays.asList(operands)));
		this.getOperands().stream().forEach(op -> op.setRunInSequence(true));
	}
	
	//XXX The recursive operands calls are not exact. The first valid element should prevail (as per runInSequence)
	//XXX For boolean recursion, one element false will make this false: although that may not be the effectively calculated element.
	public OrOperation() {
		this("fOr", "Or Fallback operation. "
				+ "Runs the first operand, if the first operand has an error or no result or returns false, runs the second operand, and so on until a result is returned."
				+ "Will return the first successful input results in case of success. "
				+ "Will return false if all of the inputs fail or have no result or returns false. "
				+ "To insure the flow, the first operand can be of any kind but the other operands shoudl be operation references. ",
			 new OperationReferenceOperation("operationReference", "operation", "operation", null));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	public OrOperation(ArrayList<Operation> operands, String outputSelector) {
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
		LOGGER.info(this.getReference() + " will continue on operand '" + operand.getReference() + "' calculation with exception: " + exception); 
		return false;
	}
	
	@Override
	protected boolean stopOperandsCalculationsOnCondition(TargetStockInfo stockInfo, Operation operand, Value<?> callRes) {
		boolean isTrue = isTrue(stockInfo, callRes);
		LOGGER.info(this.getReference() + " will stop on operand '" + operand.getReference() + "' calculation if true==" + isTrue + " from result: " + callRes);
		return isTrue; //stop if true => true
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
						LOGGER.warn("Operand " + opi.getReference() + " of " + this.getReference() + " failed with AndThenException: " + e);
					} else {
						LOGGER.error("Operand " + opi.getReference() + " of " + this.getReference() + " failed with " + e, e);
					}
				}
			} else {
				opiRes = i;
			}
			if (isTrue(targetStock, opiRes)) {
				LOGGER.info("Operand " + this.getOperands().get(iCpt).getReference() + " is true and will stop this: " + this.getReference());
				res = Optional.of(opiRes);
				break;
			}
			LOGGER.info(this.getOperands().get(iCpt).getReference() + " is " + res + " in this: " + this.getReference());
			if (iCpt < inputs.size() -1) iCpt++;
		};
		//return res.orElse(new DoubleMapValue()); //orElse empty DoubleMapValue for convenience as this the most likely expected output
		final Throwable fRootCause = rootCause;
		final int fICpt = iCpt;
		return res.orElseThrow(() -> new FlowException(this.getReference() + " 'OR' all operands are false (stopped by operand " + this.getOperands().get(fICpt).getReference() + ")" + ((fRootCause != null)?": " + fRootCause:"."), fRootCause));  //Throw to handle roll backs in the service;

	}

	private boolean isTrue(TargetStockInfo targetStock, Value<?> opiRes) {
		return opiRes != null && 
				(
					!((opiRes instanceof BooleanValue) || (opiRes instanceof StringValue) || (opiRes instanceof MapValue)) ||
					(opiRes instanceof BooleanValue && ((BooleanValue) opiRes).getValue(targetStock)) ||
					//StringValue which has to be a boolean (TRUE or FALSE)
					(opiRes instanceof StringValue &&((StringValue) opiRes).getValue(targetStock).equalsIgnoreCase("TRUE")) ||
					//Stringable returns a usable value or a NONE (this is not a boolean)
					!(opiRes instanceof StringableValue && ((StringableValue) opiRes).getAsStringable().replaceAll("\"","").equalsIgnoreCase("NONE")) ||
					(opiRes instanceof MapValue && !((MapValue<?>) opiRes).getValue(targetStock).isEmpty())
				);
	}
	
	
	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		String thisShortName = "fOr";
		String opsFormulaeShort = super.toFormulaeShort(targetStock, thisCallStack, this.getOperands());
		return thisShortName + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}

	@Override
	public String resultHint(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		List<StackElement> thisCallStack = addThisToStack(callStack, 0, targetStockInfo);
		String reduce = getOperands().stream()
				.map(e -> e.resultHint(targetStockInfo, thisCallStack))
				.filter(e -> !e.isEmpty())
				.reduce((r, e) -> r + " fallback " + e).orElse("");
		return reduce;
	}

	@Override
	public Value<?> emptyValue() {
		return  getOperands().get(0).emptyValue();
	}
	
	@Override
	public int mainInputPosition() {
		Operation firstOp = getOperands().get(0);
		if (firstOp instanceof MultiValuesOutput) {
			((MultiValuesOutput) firstOp).mainInputPosition();
		}
		return 0;
	}

}
