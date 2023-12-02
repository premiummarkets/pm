package com.finance.pms.events.operations.nativeops;

import java.util.Date;
import java.util.SortedMap;

public interface StringableMapValue {

	public SortedMap<Date, String> getValueAsStringMap();
}
