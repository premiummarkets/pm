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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;

public class QuotationsIntradayNyseEuroNext extends QuotationsIntraDay {

	QuotationsIntradayNyseEuroNext(Stock stock, Date firstDate, Date lastDate, Currency targetCurrency, Integer firstIndexShift, Integer lastIndexShift) throws NoQuotationsException {
		super(stock, firstDate, lastDate, targetCurrency, firstIndexShift, lastIndexShift);
	}

	public QuotationsIntradayNyseEuroNext(Stock stock, QuotationData quotationData, Currency targetCurrency) {
		super(stock, quotationData, targetCurrency);
	}
	
	@Override
	protected QuotationData retreiveQuotationsData(Date firstDate, Integer indexShiftBefore) {
	
		File dailyDataFile = new File(System.getProperty("installdir") + File.separator + "IntradayNyseEuroNext"+this.stock.getSymbolRoot()+".csv");
		
		SortedSet<QuotationUnit> quotationUnitsStack = new TreeSet<QuotationUnit>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(dailyDataFile));
			String line = null;
			
			Date day = null;
			Pattern pattern = Pattern.compile(".* \\(([0-9]{2}/[0-9]{2}/[0-9]{2})\\)");
			Calendar dayCal = Calendar.getInstance();
			
			while((line=bufferedReader.readLine()) != null) {
				
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					day = new SimpleDateFormat("dd/MM/yy").parse(matcher.group(1));
					dayCal.setTime(day);
				} else {
				
					String[] split = line.split(";");
					
					Date minutes;
					try {
						minutes = new SimpleDateFormat("HH:mm:ss").parse(split[0]);
						Calendar minutesCal = Calendar.getInstance();
						minutesCal.setTime(minutes);
						dayCal.set(Calendar.HOUR_OF_DAY, minutesCal.get(Calendar.HOUR_OF_DAY));
						dayCal.set(Calendar.MINUTE, minutesCal.get(Calendar.MINUTE));
						dayCal.set(Calendar.SECOND, minutesCal.get(Calendar.SECOND));
						
						quotationUnitsStack.add(new QuotationUnit(dayCal.getTime(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal(split[2]), new Long(split[3])));
						
					} catch (ParseException e) {
						LOGGER.info("Ignored line :"+line);
					}
					
				}
			}
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			LOGGER.error(e,e);
			return null;
		} catch (IOException e) {
			LOGGER.error(e,e);
			return null;
		} catch (ParseException e) {
			LOGGER.error(e,e);
			return null;
		}
		
		SortedSet<QuotationUnit> normalizedQU = new TreeSet<QuotationUnit>();
		Iterator<QuotationUnit> iterator = quotationUnitsStack.iterator();
		QuotationUnit currentQU = iterator.next();
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(currentQU.getDate());
		Calendar nextTime = Calendar.getInstance();
		QuotationUnit nextQU = currentQU;
		nextTime.setTime(nextQU.getDate());
		Date lastDate = quotationUnitsStack.last().getDate();

		while (currentTime.getTime().compareTo(lastDate) < 0) {
			while (currentTime.compareTo(nextTime) < 0) {
				normalizedQU.add(new QuotationUnit(currentTime.getTime(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, currentQU.getClose(), currentQU.getVolume()));
				QuotationsFactories.getFactory().incrementDate(currentTime, 1);
			} 
			currentQU = nextQU;
			if (iterator.hasNext()) {
				nextQU = iterator.next();
				nextTime.setTime(nextQU.getDate());
			} else {
				nextTime.setTime(lastDate);
			}
		}
		normalizedQU.add(new QuotationUnit(lastDate, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, nextQU.getClose(), nextQU.getVolume()));
		return new QuotationData(normalizedQU);
	}


}
