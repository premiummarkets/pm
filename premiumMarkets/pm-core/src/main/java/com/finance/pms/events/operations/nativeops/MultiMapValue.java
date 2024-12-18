package com.finance.pms.events.operations.nativeops;

import java.util.Map;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

public interface MultiMapValue extends MultiValue {

	Map<String, Type> getAdditionalOutputsTypes();
	
	Map<String, NumericableMapValue> getAdditionalOutputs();

	/**
	 * @Deprecated  not used??
	 * @return
	 */
	@Deprecated
	default Boolean isLooseCoupled() {
		return false;
	}

}
