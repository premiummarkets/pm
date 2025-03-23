package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.calc.ConcatStringOperation;
import com.finance.pms.events.operations.nativeops.calc.EqualsOperation;
import com.finance.pms.events.operations.nativeops.calc.NegateOperation;
import com.finance.pms.events.operations.nativeops.calc.RandomizeStringOperation;
import com.finance.pms.events.operations.nativeops.calc.StringEqualsOperation;
import com.finance.pms.events.operations.nativeops.data.CsvFileFilterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsDeltaExporterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsExporterOperation;
import com.finance.pms.events.operations.nativeops.data.IOsWebImporterOperation;
import com.finance.pms.events.operations.nativeops.data.IfExistsExporterOperation;
import com.finance.pms.events.operations.nativeops.data.ListFilterOperation;

@XmlSeeAlso({
	CsvFileFilterOperation.class, ListFilterOperation.class, IOsExporterOperation.class, IOsDeltaExporterOperation.class, IOsWebImporterOperation.class, IfExistsExporterOperation.class,
	RandomizeStringOperation.class, ConcatStringOperation.class, StringEqualsOperation.class, EqualsOperation.class, NegateOperation.class
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
	public abstract StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs);
	
	@Override
	public StringValue emptyValue() {
		return new StringValue("");
	}

}
