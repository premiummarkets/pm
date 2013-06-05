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

public class PMHouseTrendOperation extends PMIndicatorOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(PMHouseTrendOperation.class);
	
	public PMHouseTrendOperation() {
		super("logroc", "Roc logarithmic over a period", new DoubleOperation("number", "logRocPeriod", "Roc period", new DoubleValue(21.0)), new DoubleMapOperation());
	}
	
	public PMHouseTrendOperation(ArrayList<Operation> operands, String outputSelector) {
		//super("logroc", "Log roc over a period", operands);
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}

	@Override
	public DoubleMapValue calculate(TargetStockInfo targetStock, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		//Param check
		Integer period = ((DoubleValue)inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((DoubleMapValue) inputs.get(1)).getValue(targetStock);

		//Cacl
		DoubleMapValue ret = new DoubleMapValue();
		try {
			HouseTrendSmoother houseTrend = new HouseTrendSmoother(period);
			ret.getValue(targetStock).putAll(houseTrend.sSmooth(data, false));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
