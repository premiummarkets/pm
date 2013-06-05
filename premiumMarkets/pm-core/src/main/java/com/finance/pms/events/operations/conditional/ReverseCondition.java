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
import com.finance.pms.events.operations.nativeops.DoubleOperation;
import com.finance.pms.events.operations.nativeops.DoubleValue;
import com.finance.pms.events.scoring.functions.LeftShifter;

public class ReverseCondition extends Condition<Boolean>  implements StandAloneCondition {
	

	private ReverseCondition() {
		super("historical data reverse", "True when a time series reverses up or down", new DoubleOperation("direction"), new DoubleOperation("changeratio"), new DoubleOperation("dateshift"), new DoubleMapOperation());
	}

	public ReverseCondition(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
	}
	
	@Override
	
	public BooleanMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		Double direction = ((DoubleValue) inputs.get(0)).getValue(targetStock).doubleValue();
		Double changeRatio = ((DoubleValue) inputs.get(1)).getValue(targetStock).doubleValue();
		Integer shift = ((DoubleValue) inputs.get(2)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(3)).getValue(targetStock);
		
		LeftShifter<Double> rightShifter = new LeftShifter<Double>(-shift.intValue(), false, false);
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
	public int mainPosition() {
		return 3;
	}

}
