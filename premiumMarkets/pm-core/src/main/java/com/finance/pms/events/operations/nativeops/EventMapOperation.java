package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.conditional.EventMapValue;
import com.finance.pms.events.operations.nativeops.calc.ProfitDrivenOperation;
import com.finance.pms.events.operations.nativeops.calc.ProfitWalkerOperation;
import com.finance.pms.events.operations.nativeops.calc.RandomOperation;

@XmlSeeAlso({RandomOperation.class, ProfitDrivenOperation.class, ProfitWalkerOperation.class})
public class EventMapOperation extends MapOperation {

	public EventMapOperation() {
		super("historical data", "Time series of real historical data or resulting of calculations");
	}

	public EventMapOperation(String reference) {
		super(reference, reference);
	}

	public EventMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public EventMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public EventMapOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public EventMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((EventMapValue)inputs.get(0));
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}


}
