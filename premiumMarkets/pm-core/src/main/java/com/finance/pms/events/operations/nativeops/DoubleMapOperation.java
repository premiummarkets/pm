package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang.NotImplementedException;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({PMIndicatorOperation.class, TalibOperation.class, StockOperation.class})
public class DoubleMapOperation extends Operation {

	public DoubleMapOperation() {
		super("historical data", "Time series of real historical data or resulting of calculations");
	}
	
	public DoubleMapOperation(String reference) {
		super(reference, reference);
	}

	public DoubleMapOperation(String reference, String definition) {
		super(reference, definition);
	}

	public DoubleMapOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		return ((DoubleMapValue)inputs.get(0));
	}

	@Override
	public int operationStartDateShift() {
		throw new NotImplementedException();
	}

}
