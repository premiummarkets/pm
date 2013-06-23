package com.finance.pms.events.operations.nativeops;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class NumberOperation extends Operation  implements LeafOperation {
	
	public NumberOperation() {
		super("number","A number constant like period or threshold.");
	}

	public NumberOperation(String reference) {
		super(reference, reference);
	}
	
	public NumberOperation(String reference, String refAsOperand, String description, NumberValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public NumberValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((NumberValue)inputs.get(0));
	}
	
}
