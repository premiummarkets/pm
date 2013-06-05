package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.StringValue;

@XmlRootElement
public class StringOperation extends Operation  implements LeafOperation {
	
	public StringOperation() {
		super("string","String constant");
	}

	public StringOperation(String reference) {
		super(reference, reference);
	}

	public StringOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((StringValue)inputs.get(0));
	}

}
