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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.DateFactory;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LastUpdateStampChecker {
	
	private static final String JSON_DUMP_PATH = System.getProperty("installdir") + File.separator + "update_traker.json";

	private static MyLogger LOGGER = MyLogger.getLogger(LastUpdateStampChecker.class);
	
	static final Integer MAXATTEMPTS = 3;
	static final Integer MAXATTEMPTSINROW = 2;
	
	private LastUpDateStampRecords stampRecords;
	
	public LastUpdateStampChecker() {
		super();
		this.stampRecords = lastUpdatesRead();
	}

	public Boolean isUpdateGranted(String asset) {
		
		LastUpDateStampRecord lastUpDateStampRecordForAsset = null;
		if (stampRecords.getLastUpDateStampRecords().containsKey(asset)) {
			lastUpDateStampRecordForAsset = stampRecords.getLastUpDateStampRecords().get(asset);
		} else {
			lastUpDateStampRecordForAsset = new LastUpDateStampRecord(DateFactory.dateAtZero(), 0);
			stampRecords.getLastUpDateStampRecords().put(asset, lastUpDateStampRecordForAsset);
		}
		
		try {
			//XXX NOW time zone should depend on the stock provider location (info that could be available in MarketQuotationProviders of stock)
			Date now = DateFactory.getNowEndDateCalendar().getTime(); //Today midnight date. This can also be random date in the past.
			Calendar lastMarketCloseAtNow = lastMarketCloseTime(now); //Previous/This close day after 6PM - can be today 6PM or yesterday 6PM
			Date lastMrktCloseAtNowDate = lastMarketCloseAtNow.getTime();
			LOGGER.info("Last close day recorded: " + stampRecords.getLastMarketCloseRecorded() + " and Latest actual close day 6PM (US) at Now: " + lastMrktCloseAtNowDate);
			if ( stampRecords.getLastMarketCloseRecorded().compareTo(lastMrktCloseAtNowDate) >= 0 ) { //Last close day recorded >= Latest actual close day 6PM at Now
				//Basically: There is NO new close date to worry about.
				if (lastUpDateStampRecordForAsset.getNbAttempts() < MAXATTEMPTS) { //How many times did we check
					//We checked less then MAXATTEMPTS so we can check again.
					if (now.compareTo(lastUpDateStampRecordForAsset.getLastAttemptDate()) > 0 || lastUpDateStampRecordForAsset.getNbAttempts() < MAXATTEMPTSINROW) {//Either the last attempt was yesterday or ?something went wrong? less then MAXATTEMPTSINROW times
						lastUpDateStampRecordForAsset.setNbAttempts(lastUpDateStampRecordForAsset.getNbAttempts()+1);
						lastUpDateStampRecordForAsset.setLastAttemptDate(now);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {//Last close day recorded < Previous close day 6PM. There is a new close date after our last attempt => update without further ado.
				stampRecords.setLastMarketCloseRecorded(lastMrktCloseAtNowDate);
				lastUpDateStampRecordForAsset.setNbAttempts(0);
				lastUpDateStampRecordForAsset.setLastAttemptDate(now);
				return true;
			}
		} finally {
			lastUpdatesWrite(stampRecords);
		}
		
	}

	//Now will always be after the last market close time
	Calendar lastMarketCloseTime(Date now) {
		
		Calendar calendar = Calendar.getInstance(Locale.US); //XXX This is for Yahoo
		calendar.setTime(now);
		
		if (calendar.get(Calendar.HOUR_OF_DAY) < 18) {//Now is before 6PM, we take the previous day
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	
	private synchronized LastUpDateStampRecords lastUpdatesRead() {
		
		File updateTrakerFile = new File(JSON_DUMP_PATH);
		if (!updateTrakerFile.isFile()) {
			lastUpdatesWrite(new LastUpDateStampRecords(DateFactory.dateAtZero()));
		}
		
		LastUpDateStampRecords readStampRecords = null;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(updateTrakerFile))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			readStampRecords = gson.fromJson(bufferedReader, new TypeToken<LastUpDateStampRecords>() {
				private static final long serialVersionUID = 1L;
			}.getType());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (readStampRecords == null || readStampRecords.getLastMarketCloseRecorded() == null) {
				LOGGER.warn("File present but with inconsistent values : " + readStampRecords + " in " + JSON_DUMP_PATH);
				readStampRecords = new LastUpDateStampRecords(DateFactory.dateAtZero());
				lastUpdatesWrite(readStampRecords);
			}
		}
		
		return readStampRecords;
		
	}

	private synchronized void lastUpdatesWrite(LastUpDateStampRecords lastUpDateStampRecords) {
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(JSON_DUMP_PATH))) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(lastUpDateStampRecords, bufferedWriter);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "LastUpdateStampChecker " + stampRecords;
	}

	public Integer getNbAttempts(String asset) {
		return stampRecords.getLastUpDateStampRecords().get(asset).getNbAttempts();
	}
	
	public LastUpDateStampRecord getLastUpDateStampRecord(String asset) {
		return stampRecords.getLastUpDateStampRecords().get(asset);
	}

	public Date getLastMarketCloseRecorded() {
		return stampRecords.getLastMarketCloseRecorded();
	}
	
	private class LastUpDateStampRecords {
		
		private Date lastMarketCloseRecorded;
		private Map<String, LastUpDateStampRecord> lastUpDateStampRecords;
		
		public LastUpDateStampRecords(Date lastMarketCloseRecorded) {
			super();
			this.lastMarketCloseRecorded = lastMarketCloseRecorded;
			this.lastUpDateStampRecords = new ConcurrentHashMap<>();
		}
		
		public Date getLastMarketCloseRecorded() {
			return lastMarketCloseRecorded;
		}
		public void setLastMarketCloseRecorded(Date lastMarketCloseRecorded) {
			this.lastMarketCloseRecorded = lastMarketCloseRecorded;
		}
		public Map<String, LastUpDateStampRecord> getLastUpDateStampRecords() {
			return lastUpDateStampRecords;
		}
		
		@Override
		public String toString() {
			return "LastUpDateStampRecords [lastMarketCloseRecorded=" + lastMarketCloseRecorded + ", lastUpDateStampRecords=" + lastUpDateStampRecords + "]";
		}
		
	}
	
	private class LastUpDateStampRecord {

		private Date lastAttemptDate;
		private Integer nbAttempts;
		
		public LastUpDateStampRecord(Date lastAttemptDate, Integer nbAttempts) {
			super();
			this.lastAttemptDate = lastAttemptDate;
			this.nbAttempts = nbAttempts;
		}

		public Date getLastAttemptDate() {
			return lastAttemptDate;
		}
		public Integer getNbAttempts() {
			return nbAttempts;
		}
		
		public void setLastAttemptDate(Date lastAttemptDate) {
			this.lastAttemptDate = lastAttemptDate;
		}

		public void setNbAttempts(Integer nbAttempts) {
			this.nbAttempts = nbAttempts;
		}
		
		@Override
		public String toString() {
			return "LastUpDateStampRecord [lastAttemptDate=" + lastAttemptDate + ", nbAttempts=" + nbAttempts + "]";
		}
		
	}

}
