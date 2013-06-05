package com.finance.pms.events.operations.nativeops;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class DoubleOperation extends Operation  implements LeafOperation {
	
	public DoubleOperation() {
		super("number","A number constant like period or threshold.");
	}

	public DoubleOperation(String reference) {
		super(reference, reference);
	}
	
	public DoubleOperation(String reference, String refAsOperand, String description, DoubleValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public DoubleValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((DoubleValue)inputs.get(0));
	}
	
}
