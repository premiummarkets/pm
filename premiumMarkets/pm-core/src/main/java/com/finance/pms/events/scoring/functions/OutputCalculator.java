package com.finance.pms.events.scoring.functions;

import java.util.Date;

public interface OutputCalculator {
	
	Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime);

}
