/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
