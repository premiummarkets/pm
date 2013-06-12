package com.finance.pms.events.operations.conditional;

import java.util.Map;

import com.finance.pms.events.calculation.parametrizedindicators.ChartedOutputGroup.Type;
import com.finance.pms.events.operations.nativeops.DoubleMapValue;

public interface MultiMapValue {

	public abstract Map<String, Type> getAdditionalOutputsTypes();

	public abstract Map<String, DoubleMapValue> getAdditionalOutputs();

}
