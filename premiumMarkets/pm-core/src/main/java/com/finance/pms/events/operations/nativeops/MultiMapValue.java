package com.finance.pms.events.operations.nativeops;

import java.util.Map;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;

public interface MultiMapValue {

	Map<String, Type> getAdditionalOutputsTypes();

	Map<String, UnarableMapValue> getAdditionalOutputs();

}
