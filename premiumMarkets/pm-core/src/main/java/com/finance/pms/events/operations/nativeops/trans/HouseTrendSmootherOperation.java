package com.finance.pms.events.operations.nativeops.trans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;
import com.finance.pms.events.operations.nativeops.NumberOperation;
import com.finance.pms.events.operations.nativeops.NumberValue;
import com.finance.pms.events.operations.nativeops.NumericableMapValue;
import com.finance.pms.events.operations.nativeops.Value;
import com.finance.pms.events.operations.nativeops.ta.PMWithDataOperation;
import com.finance.pms.events.scoring.functions.HouseTrendSmoother;

public class HouseTrendSmootherOperation extends PMWithDataOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HouseTrendSmootherOperation.class);

	/**
	 * 
	 * @see com.finance.pms.events.operations.nativeops.calc.LnPeriodicOperation
	 * 
	 */
	public HouseTrendSmootherOperation() {
		super("rawHouseTrend", "Raw House Trend", 
				new NumberOperation("number","houseTrendPeriod", "House trend period. Same as smothing the result of Ht(1) by the same period value.", new NumberValue(1.0)),
				new DoubleMapOperation("Normalised data"));
	}

	public HouseTrendSmootherOperation(ArrayList<Operation> operands, String outputSelector) {
		this();
		this.setOperands(operands);
		this.setOutputSelector(outputSelector);
	}
	

	@Override
	public NumericableMapValue calculate(TargetStockInfo targetStock,List<StackElement> thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		Integer houseTrendPeriod = ((NumberValue) inputs.get(0)).getValue(targetStock).intValue();
		SortedMap<Date, Double> data = ((NumericableMapValue) inputs.get(1)).getValue(targetStock);

		try {

			HouseTrendSmoother htSmoother = new HouseTrendSmoother(houseTrendPeriod);
			SortedMap<Date, Double> sSmoothed = htSmoother.sSmooth(data, null);
			return new DoubleMapValue(sSmoothed);
			

		} catch (Exception e) {
			LOGGER.error(e,e);
		}

		return new DoubleMapValue();

	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return ((NumberValue)getOperands().get(0).getOrRunParameter(targetStock, thisCallStack).orElse(new NumberValue(0.0))).getValue(targetStock).intValue();
	}


}
