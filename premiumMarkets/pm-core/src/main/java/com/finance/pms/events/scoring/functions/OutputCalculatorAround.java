package com.finance.pms.events.scoring.functions;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.CurveTransformator.Extremity;

public class OutputCalculatorAround implements OutputCalculator {

	private Integer noticablePeriodBand;

	public OutputCalculatorAround(Integer noticablePeriodBand) {
		super();
		this.noticablePeriodBand = noticablePeriodBand;
	}


	@Override
	public Double compute(Date prevExtremDate, Double prevValue, Date nextExtremDate, Double nextValue, Date currentTime) {
		
		Calendar nextDateShift = Calendar.getInstance();
		nextDateShift.setTime(nextExtremDate);
		QuotationsFactories.getFactory().incrementDate(nextDateShift, -2*noticablePeriodBand);
		
		if (prevExtremDate.after(nextDateShift.getTime())) {//fix prev, next over lap
			int nbOpenIncrementBetween = QuotationsFactories.getFactory().nbOpenIncrementBetween(prevExtremDate, nextExtremDate);
			nextDateShift.setTime(nextExtremDate);
			QuotationsFactories.getFactory().incrementDate(nextDateShift, nbOpenIncrementBetween/2);
		}
		
		if (currentTime.equals(prevExtremDate)) {
			return prevValue;
		} else 
			if (currentTime.after(nextDateShift.getTime()) && ( currentTime.before(nextExtremDate) ||  currentTime.equals(nextExtremDate) )) {
				return nextValue;
			} else {
				return Extremity.UNKNOWN.getExValue();
			}
	}

}
