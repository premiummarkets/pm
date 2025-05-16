package com.finance.pms.events.operations.nativeops;

import java.util.List;
import java.util.Optional;

import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;

public interface CachableOperation {
	
	//The shift left from the start date from which the data will naturally be calculated and provided.
	//FIXME this is a short cut: there should actually be a first pass to find out the longest shift before the calculation occurs.
	/**
	 * @Deprecated not used??
	 * @return
	 */
	@Deprecated
	public default Integer operationNaturalShift() {
		return 0;
	}
	
	public boolean isForbidThisParameterValue();
	
	public default Optional<String> calculationStatus(TargetStockInfo targetStockInfo, List<StackElement> callStack) {
		return Optional.empty();
	}
	
}
