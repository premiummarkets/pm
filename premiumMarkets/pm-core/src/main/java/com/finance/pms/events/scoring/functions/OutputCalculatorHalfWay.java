package com.finance.pms.events.scoring.functions;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.events.quotations.QuotationsFactories;

//XXX
public class OutputCalculatorHalfWay implements OutputCalculator {

	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {
		
		int nbOpenDaysBetweenExtr = QuotationsFactories.getFactory().nbOpenIncrementBetween(prevExtremDate, nextExtremDate);
		Calendar halfWayCal = Calendar.getInstance();
		halfWayCal.setTime(prevExtremDate);
		QuotationsFactories.getFactory().incrementDate(halfWayCal, nbOpenDaysBetweenExtr/2);
		
		if (currentTime.before(halfWayCal.getTime())) {
			return prevValue;
		} else{
			return nextValue;
		}
	}

}
