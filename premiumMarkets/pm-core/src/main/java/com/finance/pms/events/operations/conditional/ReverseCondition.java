package com.finance.pms.events.operations.conditional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.scoring.functions.LeftShifter;


/**
 * 
 * @author Guillaume Thoreton
 * Additional constraints :
 * not implemented : 'over'
 * does not make sense : 'for'. As the condition is an event in time not a status in time.
 * 'spanning'
 * 
 */
public class ReverseCondition extends Condition<Boolean>  implements StandAloneCondition {
	

	private ReverseCondition() {
		super("historical data reverse", "True when a time series reverses up or down", 
		new NumberOperation("direction"), new NumberOperation("change ratio"), new NumberOperation("dates comparison span"), new DoubleMapOperation("historical data input"));
	}

	public ReverseCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}
	
	@Override
	
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double direction = ((NumberValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double changeRatio = ((NumberValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Integer spanningShift = ((NumberValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);
		
		if (spanningShift == 0) spanningShift = 1;
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-spanningShift.intValue(), false, false);
		SortedMap<Date, Double> rightShiftedData = rightShifter.shift(data);
		
		BooleanMapValue outputs = new  BooleanMapValue();
		Boolean isUp = null;
		Boolean wasDown = null;
		Boolean isDown = null;
		Boolean wasUp = null;
		for (Date date : data.keySet()) {
			Double current = data.get(date);
			Double previous = rightShiftedData.get(date);
			if (previous != null && !previous.isNaN()) {
				isUp = (current - previous)/Math.abs(previous) > changeRatio;
				isDown = (current - previous)/Math.abs(previous) < -changeRatio;
				if (wasDown != null) {
					Boolean reversalUp = wasDown && isUp;
					Boolean reversalDown = wasUp && isDown;
					if ((direction >= 0)) {
						outputs.getValue(targetStock).put(date, reversalUp);
					} else {
						outputs.getValue(targetStock).put(date, reversalDown);
					}
				}
				wasDown = isDown;
				wasUp = isUp;
			}
		}
		
		return outputs;
	}

	@Override
	public int mainInputPosition() {
		return 3;
	}
	
	@Override
	public int operationStartDateShift() {
		int maxDateShift = getOperands().get(mainInputPosition()).operationStartDateShift();
		for (int i = 2; i < mainInputPosition(); i++) {
			maxDateShift = maxDateShift + getOperands().get(i).operationStartDateShift();
		}
		return maxDateShift;
	}

}
