package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.LeftShifter;

public class LeftShifterOperation extends PMWithDataOperation {


	private static MyLogger LOGGER = MyLogger.getLogger(LeftShifterOperation.class);

	public LeftShifterOperation() {
		super("leftShifter", "Left shift", new NumberOperation("left shift span"), new DoubleMapOperation("Data to shift"));
	}

	public LeftShifterOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		//Param check
		int leftShiftSpan = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);

		//Calc
		DoubleMapValue ret = new DoubleMapValue();
		try {

			LeftShifter<Double> leftShifter = new LeftShifter<Double>(leftShiftSpan, false, true);
			SortedMap<Date, Double> shifted = leftShifter.shift(data);
			ret.getValue(targetStock).putAll(shifted);

		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " +e, e);
		}
		return ret;
	}

	@Override
	public int operationStartDateShift() {
		return ((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue() + getOperands().get(1).operationStartDateShift();
	}

}
