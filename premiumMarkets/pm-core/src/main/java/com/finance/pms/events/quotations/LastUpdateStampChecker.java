package com.finance.pms.events.quotations;

import java.util.Calendar;
import java.util.Date;

import com.finance.pms.events.calculation.DateFactory;

public class LastUpdateStampChecker {
	
	static final Integer MAXATTEMPTS = 3;
	
	Date lastUpdateDate;
	Integer nbAttempts;
	
	public LastUpdateStampChecker() {
		super();
		this.lastUpdateDate = DateFactory.dateAtZero();
		this.nbAttempts = 0;
	}

	public Boolean isUpdateGranted() {
		
		//XXX NOW time zone should depend on the stock provider location (info that could be available in MarketQuotationProviders of stock)
		Calendar  lastClose = Calendar.getInstance();
		Date now = lastClose.getTime();
		if (lastClose.get(Calendar.HOUR_OF_DAY) < 18) {
			lastClose.set(Calendar.DAY_OF_YEAR, -1);
		}
		lastClose.set(Calendar.HOUR_OF_DAY, 18);
		lastClose.set(Calendar.MINUTE, 0);
		lastClose.set(Calendar.SECOND, 0);
		if (this.lastUpdateDate.after(lastClose.getTime())) {
			if (nbAttempts > MAXATTEMPTS) {
				return false;
			} else {
				if (now.getTime() > this.lastUpdateDate.getTime() + 1000*60*30) {
					nbAttempts ++;
					this.lastUpdateDate = now;
					return true;
				} else {
					return false;
				}
			}
		} else {
			nbAttempts = 0;
			this.lastUpdateDate = now;
			return true;
		}
	}

	@Override
	public String toString() {
		return "LastUpdateStampChecker [lastUpdateDate=" + lastUpdateDate + ", nbAttempts=" + nbAttempts + "]";
	}

}
