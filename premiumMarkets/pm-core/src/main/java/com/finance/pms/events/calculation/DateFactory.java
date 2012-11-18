package com.finance.pms.events.calculation;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {
	
	
	public static Date dateAtZero() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1970, 0, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
		
	}

}
