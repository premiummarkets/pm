/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.quotations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.DateFactory;

public class LastUpdateStampChecker {
	
	private static MyLogger LOGGER = MyLogger.getLogger(LastUpdateStampChecker.class);
	
	static final Integer MAXATTEMPTS = 10;
	static final Integer MAXATTEMPTSINROW = 3;
	
	Date lastMarketCloseUpdate;
	Date lastAttemptDate;
	Integer nbAttempts;
	
	public LastUpdateStampChecker() {
		super();
		Date[] lastUpdatesReads = lastUpdatesRead();
		this.lastMarketCloseUpdate = lastUpdatesReads[0];
		this.lastAttemptDate = lastUpdatesReads[1];
		this.nbAttempts = 0;
	}

	public Boolean isUpdateGranted() {
		
		//XXX NOW time zone should depend on the stock provider location (info that could be available in MarketQuotationProviders of stock)
		Date now = DateFactory.getNowEndDateCalendar().getTime();
		Calendar lastMarketCloseTime = lastMarketCloseTime(now);
		if ( this.lastMarketCloseUpdate.after(lastMarketCloseTime.getTime()) || this.lastMarketCloseUpdate.equals(lastMarketCloseTime.getTime()) ) {
			if (nbAttempts >= MAXATTEMPTS) {
				return false;
			} else {
				if (now.getTime() > this.lastAttemptDate.getTime() + 1000*60*30 || nbAttempts < MAXATTEMPTSINROW) {
					nbAttempts ++;
					this.lastAttemptDate = now;
					return true;
				} else {
					return false;
				}
			}
		} else {
			nbAttempts = 0;
			this.lastMarketCloseUpdate = lastMarketCloseTime.getTime();
			this.lastAttemptDate = now;
			lastUpdatesWrite(this.lastMarketCloseUpdate, this.lastAttemptDate);
			return true;
		}
		
	}

	//Now will always be after the last market close time
	private Calendar lastMarketCloseTime(Date now) {
		
		Calendar calendar = Calendar.getInstance(Locale.US);
		calendar.setTime(now);
		
		if (calendar.get(Calendar.HOUR_OF_DAY) < 18) {//Before 6PM, we take the previous day
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	
	private Date[] lastUpdatesRead() {
		
		String updateTraker = System.getProperty("installdir") + File.separator + "update_traker.txt";
		
		File updateTrakerFile = new File(updateTraker);
		if (!updateTrakerFile.isFile()) {
			lastUpdatesWrite(DateFactory.dateAtZero(), DateFactory.dateAtZero());
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date[] results = new Date[2];
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(updateTrakerFile))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				try {
					String[] lineSplit = line.split("=");
					if (lineSplit[0].equals("lastMarketCloseUpdate")) {
						results[0] = dateFormat.parse(lineSplit[1]);
					}
					else if (lineSplit[0].equals("lastAttemptDate")) {
						results[1] = dateFormat.parse(lineSplit[1]);
					}
				} catch (Exception e) {
					LOGGER.warn("Unreadable line in " + updateTraker + " : " + line + ". Cause: " + e);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	private void lastUpdatesWrite(Date lastMarketCloseUpdate, Date lastAttemptDate) {
		String updateTraker = System.getProperty("installdir") + File.separator + "update_traker.txt";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(updateTraker))) {
			bufferedWriter.write("lastMarketCloseUpdate=" + dateFormat.format(lastMarketCloseUpdate));
			bufferedWriter.newLine();
			bufferedWriter.write("lastAttemptDate=" + dateFormat.format(lastMarketCloseUpdate));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "LastUpdateStampChecker [lastUpdateDate=" + lastMarketCloseUpdate + ", nbAttempts=" + nbAttempts + "]";
	}

}
