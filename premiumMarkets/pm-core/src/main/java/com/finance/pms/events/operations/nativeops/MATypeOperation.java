package com.finance.pms.events.operations.nativeops;

import java.util.EnumSet;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.tictactec.ta.lib.MAType;

@XmlRootElement
public class MATypeOperation extends Operation  implements LeafOperation {
	
	public MATypeOperation() {
		super("moving average type", "One of "+EnumSet.allOf(MAType.class));
	}

	public MATypeOperation(String reference) {
		super(reference, reference);
	}
	
	public MATypeOperation(String reference, String refAsOperand, String description, NumberValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public MATypeValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((MATypeValue)inputs.get(0));
	}

	@Override
	public int operationStartDateShift() {
		return 0;
	}

}
