package com.finance.pms.events.operations.nativeops;

import com.finance.pms.events.operations.TargetStockInfo;

public interface LaggingOperation {
	
	/**
	 * Lag amount. Will generate NaNs to the right of the data set.
	 * The value is of the same nature as the operandsRequiredStartShift (the left shift).
	 * @param targetStock 
	 * @return
	 */
	int rightLagAmount(TargetStockInfo targetStock) throws Exception;

}
