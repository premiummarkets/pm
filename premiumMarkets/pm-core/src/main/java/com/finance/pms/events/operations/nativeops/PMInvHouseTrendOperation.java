package com.finance.pms.events.operations.nativeops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.scoring.functions.InvHouseTrendSmoother;

public class PMInvHouseTrendOperation extends PMIndicatorOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(PMInvHouseTrendOperation.class);
	
	public PMInvHouseTrendOperation() {
		super("InverseLogroc", "Inverse Roc logarithmic over a period", new NumberOperation("number", "logRocPeriod", "Roc period", new NumberValue(21.0)), new DoubleMapOperation());
	}
	
	public PMInvHouseTrendOperation(ArrayList<Operation> operands, String outputSelector) {
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
			InvHouseTrendSmoother invHouseTrend = new InvHouseTrendSmoother(period, null);
			ret.getValue(targetStock).putAll(invHouseTrend.sSmooth(data, false));
		} catch (Exception e) {
			LOGGER.error(e,e);
		}
		return ret;
	}

}
