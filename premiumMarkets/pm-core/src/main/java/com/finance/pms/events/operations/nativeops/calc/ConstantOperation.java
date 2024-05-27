package com.finance.pms.events.operations.nativeops.calc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateUtils;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;

public class ConstantOperation extends DoubleMapOperation {

	public ConstantOperation() {
		this("constant_", "Draw a map from a constant.", new NumberOperation("number", "constant", "Constant to draw", new NumberValue(Double.NaN)));
	}
	
	public ConstantOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}
	
	public ConstantOperation(String reference, String description, ArrayList<DoubleMapOperation> operands) {
		super(reference, description, operands);
	}

	public ConstantOperation(String reference, String description, DoubleMapOperation ...operands) {
		this(reference, description, new ArrayList<DoubleMapOperation>(Arrays.asList(operands)));
	}


	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Double constant = ((NumberValue) inputs.get(0)).getNumberValue();
		
		SortedMap<Date, Double> data = Stream.iterate(targetStock.getStartDate(thisStartShift), date -> DateUtils.addDays(date, 1))
        .limit(Duration.between(targetStock.getStartDate(thisStartShift).toInstant(), targetStock.getEndDate().toInstant()).toDays() + 1)
        .collect(Collectors.toMap(date -> date, date -> constant, (a, b) -> a, TreeMap::new));
		
		//List<String> headers = Arrays.asList(this.getOperands().get(0).getReferenceAsOperand());
		return new DoubleMapValue(data);
		
	}

}
