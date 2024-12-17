package com.finance.pms.events.operations.nativeops;

import java.util.List;

import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

public interface LaggingOperation {
	
	/**
	 * Lag amount. Will generate NaNs to the right of the data set.
	 * The value is of the same nature as the operandsRequiredStartShift (the left shift).
	 * @param targetStock 
	 * @param thisCallStack TODO
	 * @return
	 */
	int rightLagAmount(TargetStockInfo targetStock, List<StackElement> thisCallStack) throws Exception;

}
