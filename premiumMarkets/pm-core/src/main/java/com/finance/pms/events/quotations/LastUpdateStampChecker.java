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
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.events.calculation.DateFactory;
import com.google.common.io.Files;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//TODO
//We should actually reset the attempts field only when the update is successful but that needs a call back after the returned info has been used ..
//then 
//If stampRecords.getLastMarketCloseRecorded().compareTo(lastMrktCloseAtNowDate) >= 0 and lastUpDateStampRecordForAsset.getNbAttempts() >= MAXATTEMPTS: return false
//If stampRecords.getLastMarketCloseRecorded().compareTo(lastMrktCloseAtNowDate) >= 0 and lastUpDateStampRecordForAsset.getNbAttempts() < MAXATTEMPTS: return true
//If stampRecords.getLastMarketCloseRecorded().compareTo(lastMrktCloseAtNowDate) < 0 and lastUpDateStampRecordForAsset.getNbAttempts() < DEADATTEMPTS: return true??
public class LastUpdateStampChecker {
	
	private static final String JSON_DUMP_PATH = System.getProperty("installdir") + File.separator + "update_traker.json";

	private static MyLogger LOGGER = MyLogger.getLogger(LastUpdateStampChecker.class);
	
	static final Integer MAXRETRY = 1;
	static final Integer MAXATTEMPTSFATAL = 10;
	
	private LastUpDateStampRecords stampRecords;
	
	public LastUpdateStampChecker() {
		super();
		this.stampRecords = lastUpdatesRead();
	}

	//FIXME in the same way as the fix in GetQuotation, calculate the end date from the actualDateTime and set it as last update date
	//Compare the attempt time stamp with Mc (see GetQuotations) which should give which end date was attempted already.
	public Boolean isUpdateGranted(String asset, Date lastQuoteDateForAsset, int utcTimeLag, TradingMode tradinMode) {
		
		LastUpDateStampRecord timeStampOfLastUpdate = getLastUpdateStampRecord(asset);
		
		//XXX NOW time zone should depend on the stock provider location (info that could be available in MarketQuotationProviders of stock)
		Date now = DateFactory.getNowEndTime(); //Today actual date time. This can also be random date in the past depending on the DateFactory.ENDDATE settings.
		Date lastMrktCloseBeforeNowDate = DateFactory.endDateFix(now, utcTimeLag, tradinMode); //Previous/This close day after 6PM - can be today 6PM or yesterday 6PM !!US!!
		try {
			
			String timeStampMsg = "Last market close (with UTC lag " + utcTimeLag + "): " + lastMrktCloseBeforeNowDate + " <=? " + "Last quote: " + lastQuoteDateForAsset;
			
			if (lastQuoteDateForAsset != null && lastQuoteDateForAsset.compareTo(lastMrktCloseBeforeNowDate) >= 0) {//Already up to date
				timeStampOfLastUpdate.resetNbAttemts();
				LOGGER.info(asset + " is up to date." + timeStampMsg);
				return false;
			}
			
			if (timeStampOfLastUpdate.getFatalThreshold() >= MAXATTEMPTSFATAL) {//Dead Quote!!??
				LOGGER.warn(asset + " has no quotations new update. Max failed attempt reach: " + MAXATTEMPTSFATAL + ". " + timeStampMsg);
				return false; 
			}
			
			//Should be == as can't be < as timeStampOfLastUpdate is updated with lastMrktCloseBeforeNowDate
			//so we always have timeStampOfLastUpdate <= lastMrktCloseBeforeNowDate
			if (lastMrktCloseBeforeNowDate.compareTo(timeStampOfLastUpdate.getLastAttemptDate()) == 0 ) { 
				LOGGER.info(asset + " has Failed previous update. " + timeStampMsg);
				//Latest actual close day 6PM at Now == Last close day recorded for asset: don't update more then MAXATTEMPTS
				//This means we already have tried update with the actual market data available
				timeStampOfLastUpdate.incNbAttempts();
				
				//accumulation
				if (timeStampOfLastUpdate.getNbAttempts() == MAXRETRY +1 && 
						lastQuoteDateForAsset != null && lastQuoteDateForAsset.compareTo(lastMrktCloseBeforeNowDate) < 0) {//Needs update but failed
						timeStampOfLastUpdate.incFatalThreshold(); //Dead
				}
				
				//New attempt
				if (timeStampOfLastUpdate.getNbAttempts() <= MAXRETRY &&
					lastQuoteDateForAsset != null && lastQuoteDateForAsset.compareTo(lastMrktCloseBeforeNowDate) < 0) {//Needs update but failed
					LOGGER.info(asset + " is NOT up to date and may have new market data. Retrying... " + timeStampMsg);
					return true;
				}

				LOGGER.info(asset + " is NOT up to date. Has Failed all update attempts for the latest market data session. " + timeStampMsg);
				return false;
				
			} else { //Potential new market data available since last check
				timeStampOfLastUpdate.resetNbAttemts();
				LOGGER.info(asset + " is NOT up to date. Needs updating. " + timeStampMsg);
				return true;
			}
			
		} finally {
			timeStampOfLastUpdate.setLastAttemptDate(lastMrktCloseBeforeNowDate);
			lastUpdatesWrite(stampRecords);
		}
		
	}

	LastUpDateStampRecord getLastUpdateStampRecord(String asset) {
		if (!stampRecords.getLastUpDateStampRecords().containsKey(asset)) {
			stampRecords.getLastUpDateStampRecords().put(asset, new LastUpDateStampRecord(DateFactory.dateAtZero(), 0, 0));
			lastUpdatesWrite(stampRecords);
		}
		return stampRecords.getLastUpDateStampRecords().get(asset);
	}
	
	private synchronized LastUpDateStampRecords lastUpdatesRead() {
		
		LastUpDateStampRecords readStampRecords = null;
		File updateTrakerFile = new File(JSON_DUMP_PATH);
		if (!updateTrakerFile.isFile() || updateTrakerFile.length() == 0) {
			readStampRecords = new LastUpDateStampRecords();
			lastUpdatesWrite(readStampRecords);
		} else {
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(updateTrakerFile))) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				readStampRecords = gson.fromJson(bufferedReader, new TypeToken<LastUpDateStampRecords>() {
					private static final long serialVersionUID = 1L;
				}.getType());
			} catch (Exception e) {
				LOGGER.error("Inconsistencies : " + readStampRecords + " in " + JSON_DUMP_PATH, e);
				throw new RuntimeException(e);
			} finally {
				if (readStampRecords == null) {
					LOGGER.warn("File is empty or inconsistent: " + JSON_DUMP_PATH);
					String copyPath = JSON_DUMP_PATH + "_" + new Date().getTime();
					try {
						Files.copy(updateTrakerFile, new File(copyPath));
					} catch (IOException e) {
						LOGGER.warn("Could not backup file " + JSON_DUMP_PATH + " to " + copyPath + ": " + e);
					}
					readStampRecords = new LastUpDateStampRecords();
					lastUpdatesWrite(readStampRecords);
				}
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
	
	public void resetFatalThreshold(String asset) {
		getLastUpdateStampRecord(asset).resetFatalThreshold();
		lastUpdatesWrite(stampRecords);
	}
	
	public void resetAsset(String asset) {
		stampRecords.getLastUpDateStampRecords().remove(asset);
		lastUpdatesWrite(stampRecords);
	}
	
	
	private class LastUpDateStampRecords {
		
		private Map<String, LastUpDateStampRecord> lastUpDateStampRecords;
		
		public LastUpDateStampRecords() {
			super();
			this.lastUpDateStampRecords = new ConcurrentHashMap<>();
		}
		
		public Map<String, LastUpDateStampRecord> getLastUpDateStampRecords() {
			return lastUpDateStampRecords;
		}
		
		@Override
		public String toString() {
			return "LastUpDateStampRecords [lastUpDateStampRecords=" + lastUpDateStampRecords + "]";
		}
		
	}
	
	class LastUpDateStampRecord {

		private Date lastAttemptDate;
		private Integer nbAttempts;
		private Integer fatalThreshold;

		public LastUpDateStampRecord(Date lastAttemptDate, Integer nbAttempts, Integer fatalThreshold) {
			super();
			this.lastAttemptDate = lastAttemptDate;
			this.nbAttempts = nbAttempts;
			this.fatalThreshold = fatalThreshold;
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

		public void incNbAttempts() {
			this.nbAttempts = this.nbAttempts + 1;
		}
		
		public void resetNbAttemts() {
			this.nbAttempts = 0;
		}
		
		public Integer getFatalThreshold() {
			return fatalThreshold;
		}

		public void incFatalThreshold() {
			this.fatalThreshold = this.fatalThreshold + 1;
		}
		
		public void resetFatalThreshold() {
			this.fatalThreshold = 0;
		}
		
		@Override
		public String toString() {
			return "LastUpDateStampRecord [lastAttemptDate=" + lastAttemptDate + ", nbAttempts=" + nbAttempts + ", fatalThreshold =" + fatalThreshold + "]";
		}
		
	}

}
