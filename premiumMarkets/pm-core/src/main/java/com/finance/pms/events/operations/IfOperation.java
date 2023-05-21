package com.finance.pms.events.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
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
	public Value<?> calculate(TargetStockInfo targetStock, String thisCallStack, int thisOutputRequiredStartShiftFromParent, int thisInputOperandsRequiredShiftFromThis, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String ifCondition = ((StringValue) inputs.get(0)).getValue(targetStock);
		Operation thenOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(1)).getValue(targetStock).clone();
		Operation elseOperationClone = (Operation) ((OperationReferenceValue<?>) inputs.get(2)).getValue(targetStock).clone();
		
		try {
			LOGGER.info(this.getReference() + " condition is " + ifCondition);
			if (ifCondition.equals("TRUE")) {
				LOGGER.info(this.getReference() + " will run " + thenOperationClone.getReference());
				return thenOperationClone
						.run(targetStock, addThisToStack(thisCallStack, thisInputOperandsRequiredShiftFromThis, 0, targetStock), thisInputOperandsRequiredShiftFromThis);
			} else {
				LOGGER.info(this.getReference() + " will run " + elseOperationClone.getReference());
				return elseOperationClone
						.run(targetStock, addThisToStack(thisCallStack, thisInputOperandsRequiredShiftFromThis, 0, targetStock), thisInputOperandsRequiredShiftFromThis);
			}
		} catch (NotEnoughDataException e) {
			LOGGER.error(e, e);
		}
		return new DoubleMapValue();
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
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
	}

}
