package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StringableValue;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

@XmlSeeAlso({
	CsvFileFilterOperation.class, IOsExporterOperation.class, IOsDeltaExporterOperation.class, IOsWebImporterOperation.class,
	RandomizeStringOperation.class, ConcatStringOperation.class, StringEqualsOperation.class
	})
public abstract class StringerOperation extends Operation {

	public StringerOperation(String reference, String description) {
		super(reference, description);
	}

	public StringerOperation(String reference, String description, ArrayList<? extends Operation> operands) {
		super(reference, description, operands);
	}

	public StringerOperation(String reference, String referenceAsOperand, String description, StringableValue defaultValue) {
		super(reference, referenceAsOperand, description, defaultValue);
	}

	@Override
	public abstract StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs);
	
	@Override
	public StringValue emptyValue() {
		return null;
	}

}
