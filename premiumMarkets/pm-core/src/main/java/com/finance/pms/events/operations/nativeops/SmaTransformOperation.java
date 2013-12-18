package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.SmaTransformator;

public class SmaTransformOperation extends PMWithDataOperation {
	
	
private static MyLogger LOGGER = MyLogger.getLogger(SmaTransformOperation.class);
	
	public SmaTransformOperation() {
		super("smaTransformator", "Calculate SMA from one period to another", 
				new NumberOperation("Origin period"), new NumberOperation("Destination period"),
				new DoubleMapOperation("SMA data to transform"));
	}
	
	public SmaTransformOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer orgPeriog = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		Integer destPeriod = ((NumberValue)inputs.get(1)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(2)).getValue(targetStock);
		
		//Calc
		DoubleMapValue ret = new DoubleMapValue();
		try {
			
			SmaTransformator<Double> transformator = new SmaTransformator<Double>(Double.class, orgPeriog, destPeriod);
			SortedMap<Date, Double> transfomed = transformator.transform(data);
			
			ret.getValue(targetStock).putAll(transfomed);
			
		} catch (Exception e) {
			LOGGER.error(targetStock.getStock().getFriendlyName() + " : " +e, e);
		}
		return ret;
	}

	@Override
	public int operationStartDateShift() {
		int shift = 0;
		for (Operation operand : getOperands()) {
			shift = shift +  operand.operationStartDateShift();
		}
		return shift;
	}


}
