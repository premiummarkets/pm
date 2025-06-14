package com.finance.pms.events.operations.nativeops.trans;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.StringableValue;
import com.finance.pms.events.operations.nativeops.Value;

public class StartDateWrapperOperation extends Operation {
	
	public StartDateWrapperOperation() {
		this("shiftWrapper", "This is a pass through for the output of its operand. Use this to add (or remove -) to the required shift of its operand. NaN means full data set.", 
				new NumberOperation("addedShift", "addedShift", "Added Shift", new NumberValue(Double.NaN)), 
				new DoubleMapOperation("data")
		);
	}

	public StartDateWrapperOperation(String reference, String description, Operation ...operands) {
		super(reference, description, new ArrayList<>(Arrays.asList(operands)));
	}

	@Override
	public Value<?> calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		//Integer addedShift = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		return inputs.get(1);
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		Double addedShift = ((NumberValue) getOperands().get(0).getOrRunParameter(targetStock, thisCallStack).orElse(new NumberValue(0.0))).getValue(targetStock).doubleValue();
		if (Double.isNaN(addedShift)) {
			
			//Update sliding start date
			ZoneId osZoneId = ZoneId.systemDefault();
			// Convert java.util.Date to ZonedDateTime
			ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(DateFactory.dateAtZero().toInstant(), osZoneId);
			ZonedDateTime endZonedDateTime = ZonedDateTime.ofInstant(DateFactory.midnithDate(new Date()).toInstant(), osZoneId);
			int diffInDays = (int) ChronoUnit.DAYS.between(startZonedDateTime, endZonedDateTime);
			return diffInDays;
		} else {
			return addedShift.intValue();
		}
	}

	@Override
	public Value<?> emptyValue() {
		return new DoubleMapValue();
	}

	@Override
	public String toFormulaeShort(TargetStockInfo targetStock, List<StackElement> thisCallStack) {
		Operation operand0 = getOperands().get(0);
		String requiredStartShift = ((StringableValue) operand0.getOrRunParameter(targetStock, thisCallStack)
										.orElse(new StringValue(operand0.toFormulaeShort(targetStock, thisCallStack)))).getAsStringable();
		List<Operation> ops = getOperands().subList(1, getOperands().size());
		String opsFormulaeShort = toFormulaeShort(targetStock, thisCallStack, ops);
		return "sW" + "_" + requiredStartShift + ((opsFormulaeShort.isEmpty())?"":"_" + opsFormulaeShort);
	}
	
	

}
