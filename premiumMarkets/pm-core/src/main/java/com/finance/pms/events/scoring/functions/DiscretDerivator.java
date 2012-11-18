package com.finance.pms.events.scoring.functions;

import java.util.Date;
import java.util.SortedMap;

public interface DiscretDerivator {

	public SortedMap<Date, double[]> derivateDiscret(SortedMap<Date, double[]> data);

}
