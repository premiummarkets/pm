package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.CurveFlip;

public class FlipOperation extends PMWithDataOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(FlipOperation.class);
	
	public FlipOperation() {
		super("flipAround", "Flip the input upside down", new NumberOperation("flip axe"), new DoubleMapOperation("Data to flip around"));
	}
	
	public FlipOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Double flipAxe = ((NumberValue)inputs.get(0)).getValue(targetStock).doubleValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);
		
		//Calc
		DoubleMapValue ret = new DoubleMapValue();
		try {
			
			CurveFlip curveFlip = new CurveFlip();
			SortedMap<Date, Double> fliped = curveFlip.sOperate(data, flipAxe);
			
			ret.getValue(targetStock).putAll(fliped);
			
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " +e, e);
		}
		return ret;
	}

	@Override
	public int operationStartDateShift() {
		return getOperands().get(1).operationStartDateShift();
	}
}
