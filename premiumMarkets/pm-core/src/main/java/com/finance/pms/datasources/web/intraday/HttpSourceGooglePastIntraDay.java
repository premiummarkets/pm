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
package com.finance.pms.datasources.web.intraday;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyBeanFactoryAware;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.RealTimeGoogleLineFormater.ValidatableQuotationUnit;
import com.finance.pms.threads.MyHttpClient;

public class HttpSourceGooglePastIntraDay extends HttpSourceGoogleIntraDay {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceGooglePastIntraDay.class);

	public HttpSourceGooglePastIntraDay(String pathToprops, MyBeanFactoryAware beanFActoryAware) {
		super(pathToprops, beanFActoryAware);
	}

	@Override
	protected Set<Validatable> readURL(LineFormater formater, MyHttpClient httpcx) throws HttpException, IOException {
		
		Set<Validatable> resultSet= new HashSet<Validatable>();
		
		Date endDate;
		try {
			endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(MainPMScmd.getMyPrefs().get("test.endDate",null));
		} catch (ParseException e) {
			throw new IOException(e);
		}
		
		File dailyDataFile = new File(System.getProperty("installdir")+ File.separator + "IntradayGoogle"+((Stock)formater.getParams().get(0)).getSymbolRoot()+".csv");
		BufferedReader dis = new BufferedReader(new FileReader(dailyDataFile));
		String line = "";
		try {
			while ((line = dis.readLine()) != null) {
				if (line.length() > 0) {
					List<Validatable> validatables;
					
					try {
						
						validatables = formater.formatLine(line);
						//if (validatables != null) resultSet.addAll(validatables);
						if (validatables != null) {
							for (Validatable validatable : validatables) {
								Date date = ((ValidatableQuotationUnit) validatable).getQuotationUnit().getDate();
								if (date.before(endDate) || date.compareTo(endDate) == 0) {
									resultSet.add(validatable);
								}
							}
						}
//					} catch (StopParseFoundException e) {
//						
//						LOGGER.error("Symbol : " + ((StopParseFoundException) e).getLastOne() + " Found.");
//						resultSet.add((e.getLastOne()));
//						
//					} catch (StopParseErrorException e) {
//						
//						LOGGER.warn("Stop Parsing Url " + formater.getUrl() + " : "+ ((StopParseErrorException) e).getMessage());
//						LOGGER.warn("Reason : " + ((StopParseErrorException) e).getReason());
//						if (LOGGER.isTraceEnabled()) {
//							for (; ((line = dis.readLine()) != null);) {
//								LOGGER.trace(line);
//							}
//						}
//						throw new IOException(
//								"Stop Parsing Url " + formater.getUrl() + " : "+ ((StopParseErrorException) e).getMessage()+"\n"+
//								"Reason : " + ((StopParseErrorException) e).getReason());
			
					} catch (Exception e) {
						LOGGER.debug("Ignoring line :" + line);
						LOGGER.trace(e);
					}
				}
			}
		} finally {
			dis.close();
		}
		
		return resultSet;
	}
	
	
	
	

}
