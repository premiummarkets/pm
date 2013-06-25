package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlRootElement
public class StringOperation extends Operation  implements LeafOperation {
	
	public StringOperation() {
		super("string of letters","String of letters as a parameter");
	}

	public StringOperation(String reference) {
		super(reference, reference);
	}
	
	public StringOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}
	
	public StringOperation(String reference, String refAsOperand, String description, StringValue defaultValue) {
		super(reference, refAsOperand, description, defaultValue);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((StringValue)inputs.get(0));
	}

}
