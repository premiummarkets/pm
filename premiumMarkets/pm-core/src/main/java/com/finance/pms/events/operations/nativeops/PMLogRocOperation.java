package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;

public class PMLogRocOperation extends PMWithDataOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(PMLogRocOperation.class);
	
	public PMLogRocOperation() {
		super("logroc", "Roc logarithmic over a period", new NumberOperation("number", "logRocPeriod", "Roc period", new NumberValue(1.0)), new DoubleMapOperation());
	}
	
	public PMLogRocOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer period = ((NumberValue)inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);

		//Cacl
		DoubleMapValue ret = new DoubleMapValue();
		try {
			HouseTrendSmoother houseTrend = new HouseTrendSmoother(period);
			ret.getValue(targetStock).putAll(houseTrend.sSmooth(data, null));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

	@Override
	public int operationStartDateShift() {
		return ((NumberValue)getOperands().get(0).getParameter()).getValue(null).intValue() + getOperands().get(1).operationStartDateShift();
	}

}
