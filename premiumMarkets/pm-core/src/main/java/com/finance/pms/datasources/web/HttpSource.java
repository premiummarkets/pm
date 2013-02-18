/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StopParseErrorException;
import com.finance.pms.datasources.web.formaters.StopParseFoundException;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;


// TODO: Auto-generated Javadoc
/**
 * The Class HttpSource.
 * 
 * @author Guillaume Thoreton
 */
public abstract class HttpSource {

	/** The initial state. */
	private HttpState initialState;
	
	/** The nb http threads. */
	protected int nbHttpThreads; // = 20;
	
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSource.class);

	private MyBeanFactoryAware beanFactoryAware;
	
	/**
	 * Stop all threads.
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void stopALLThreads() {
		//TODO stop all instances
	}
	
	
	/**
	 * Instantiates a new http source.
	 * 
	 * @param pathToprops the path toprops
	 * 
	 * @author Guillaume Thoreton
	 */
	public HttpSource(String pathToprops, MyBeanFactoryAware beanFActoryAware) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream((new File(pathToprops))));
			//Connection
			if (props.containsKey("http.login"))
				MainPMScmd.getPrefs().put("http.login", props.getProperty("http.login"));
			if (props.containsKey("http.password"))
				MainPMScmd.getPrefs().put("http.password", props.getProperty("http.password"));
			//Nb Threads
			if (props.containsKey("http.poolsize"))
				MainPMScmd.getPrefs().put("http.poolsize", props.getProperty("http.poolsize"));
			//Email
			MainPMScmd.getPrefs().remove("mail.host");
			MainPMScmd.getPrefs().remove("mail.username");
			MainPMScmd.getPrefs().remove("mail.password");
			MainPMScmd.getPrefs().remove("mail.from");
			if (props.containsKey("mail.to"))
				MainPMScmd.getPrefs().put("mail.to", props.getProperty("mail.to"));
			if (props.containsKey("mail.from") && !props.getProperty("mail.from").equals(""))
				MainPMScmd.getPrefs().put("mail.from", props.getProperty("mail.from"));
			if (props.containsKey("mail.host") && !props.getProperty("mail.host").equals(""))
				MainPMScmd.getPrefs().put("mail.host", props.getProperty("mail.host"));
			if (props.containsKey("mail.username") && !props.getProperty("mail.username").equals(""))
				MainPMScmd.getPrefs().put("mail.username", props.getProperty("mail.username"));
			if (props.containsKey("mail.password") && !props.getProperty("mail.password").equals(""))
				MainPMScmd.getPrefs().put("mail.password", props.getProperty("mail.password"));
			
			MainPMScmd.getPrefs().flush();
		} catch (Exception e) {
			LOGGER.error("Couldn't find propertie file. Check the propeties path",e);
		}
		//Init connection
		//HttpState initialState = new HttpState();
		initialState = new HttpState();
		// Initial set of cookies can be retrieved from persistent storage and
		// re-created, using a persistence mechanism of choice,
		Cookie mycookie = new Cookie(".foobar.com", "mycookie", "stuff", "/", null, false);
		// and then added to your HTTP state instance
		initialState.addCookie(mycookie);
		//init thread
		this.nbHttpThreads = (new Integer(MainPMScmd.getPrefs().get("http.poolsize", "10"))).intValue();
		
		this.beanFactoryAware = beanFActoryAware;
		
	}

	/**
	 * Gets the connection from pool.
	 * 
	 * @return the connection from pool
	 * @throws HttpException 
	 */
	public MyHttpClient getConnectionFromPool() throws HttpException {
		MyHttpClient ret;
		try {
			ret = (MyHttpClient) this.getThreadPool().getResource();
			return ret;
		} catch (InterruptedException e) {
			LOGGER.error("Unable to get Connection. Is network available?", e);
			throw new HttpException("Unable to get Connection. Is network available?", e);
			//System.exit(1);
		} catch (TimeoutException e) {
			LOGGER.error("Unable to get Connection. Is network available? Thread lock ?", e);
			throw new HttpException("Unable to get Connection. Is network available?",e);
		}
		
	}

	/**
	 * Realese pool connection.
	 * 
	 * @param conn the conn
	 * 
	 * @author Guillaume Thoreton
	 */
	public void realesePoolConnection(SourceClient conn) {
		this.getThreadPool().releaseResource(conn);
	}

	

	/**
	 * Http connect.
	 * 
	 * @return the my http client
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract MyHttpClient httpConnect(); //throws IOException, HttpException;

	/**
	 * Read url.
	 * 
	 * @param formater the formater
	 * 
	 * @return the list< validatable>
	 * 
	 * @author Guillaume Thoreton
	 * @throws HttpException 
	 */
	public List<Validatable> readURL(LineFormater formater) throws HttpException {
		
		MyHttpClient httpcx = this.getConnectionFromPool();
		
		List<Validatable> ret = new ArrayList<Validatable>();
		try {
			ret.addAll(this.readURL(formater, httpcx));
			
		} catch (HttpException e) {
			
			getScrapperMetrics().addRecord(formater, e.getMessage());
			throw e;
			
		} catch (IOException e) {
			
			getScrapperMetrics().addRecord(formater, e.getMessage());
			
		} catch (Exception e) {
			
			LOGGER.error("ERROR processing url :" + e,e);
			LOGGER.error("Is " + httpcx.getHostConfiguration().getHostURL() +" with params "+ httpcx.getParams() + " a valid url ?",e);
			LOGGER.debug("",e);
			getScrapperMetrics().addRecord(formater, e.getMessage());
			
		} finally {
			this.realesePoolConnection(httpcx);
		}
		
		MetricsResType metricType = extractMetricRes(formater,ret);
		getScrapperMetrics().addRecord(formater, metricType);
		
		return ret;
	}


	/**
	 * @param formater
	 * @param ret
	 * @return
	 */
	private MetricsResType extractMetricRes(LineFormater formater, List<Validatable> ret) {
		MetricsResType metricType;
		if (ret.size() != 0) {
			if (!formater.isEmptyValue()) {
				metricType = MetricsResType.SUCCESS;
			} else {
				metricType = MetricsResType.EMPTY;
			}
		} else if (formater.canHaveEmptyResults()) {
			metricType = MetricsResType.EMPTY;
		} else {
			metricType = MetricsResType.FAILURE;
		}
		return metricType;
	}

	/**
	 * Read url.
	 * 
	 * @param formater the formater
	 * @param httpcx the httpcx
	 * 
	 * @return the list< validatable>
	 * 
	 * @throws HttpException the http exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	protected Set<Validatable> readURL(LineFormater formater, MyHttpClient httpcx) throws HttpException, IOException {
		
		Set<Validatable> resultSet= new HashSet<Validatable>();

		HttpMethodBase  httpget = this.getRequestMethod(formater.getMyUrl());
		try {

			int result = httpcx.executeMethod(httpget);

			// Display status code
			while (result == 302) {//redirected

				String locationRedir = httpget.getResponseHeader("location").getValue();
				LOGGER.debug("Redirection URL :"+locationRedir);
				httpget.releaseConnection();

				//MyUrl myNewUrl = new MyUrl("http://"+httpget.getURI().getHost()+locationRedir);
				MyUrl myNewUrl = new MyUrl(locationRedir);

				myNewUrl.setHttpParams(formater.getMyUrl().getHttpParams());
				httpget = this.getRequestMethod(myNewUrl);
				result = httpcx.executeMethod(httpget);
			}

			if (result != 200) {
				throw new HttpException("Service Error "+result+" for "+formater.getUrl());

			} else {

				InputStream in;
				if (
						httpget.getResponseHeader("Content-Encoding") != null && "gzip".contains(httpget.getResponseHeader("Content-Encoding").getValue()) ||
						httpget.getResponseHeader("Content-Type") != null && "application/x-gzip".equals(httpget.getResponseHeader("Content-Type").getValue())
						) {
					in = new GZIPInputStream(httpget.getResponseBodyAsStream());
				}

				else {
					if (
							httpget.getResponseHeader("Content-Type") != null && "application/x-zip-compressed".equals(httpget.getResponseHeader("Content-Type").getValue())
							) {
						in = new ZipInputStream(httpget.getResponseBodyAsStream());
						((ZipInputStream) in).getNextEntry();
					}
					else {
						in = httpget.getResponseBodyAsStream();
					}
				}

				BufferedReader dis = new BufferedReader(new InputStreamReader(in,"ISO-8859-15"));
				String line = "";
				try {
					while ((line = dis.readLine()) != null) {
						if (line.length() > 0) {
							List<Validatable> validatables;
							
							try {
								
								validatables = formater.formatLine(line);
								if (validatables != null) resultSet.addAll(validatables);
								
							} catch (StopParseFoundException e) {
								
								LOGGER.debug("Symbol : " + ((StopParseFoundException) e).getLastOne() + " Found");
								resultSet.add((e.getLastOne()));
								break;
								
							} catch (StopParseErrorException e) {
								
								LOGGER.warn("Stop Parsing Url " + formater.getUrl() + " : "+ ((StopParseErrorException) e).getMessage());
								LOGGER.warn("Reason : " + ((StopParseErrorException) e).getReason());
								if (LOGGER.isTraceEnabled()) {
									for (; ((line = dis.readLine()) != null);) {
										LOGGER.trace(line);
									}
								}
								throw new IOException(
										"Stop Parsing Url " + formater.getUrl() + " : "+ ((StopParseErrorException) e).getMessage()+"\n"+
												"Reason : " + ((StopParseErrorException) e).getReason());
								
							} catch (Exception e) {
								LOGGER.debug("Ignoring line :" + line);
								LOGGER.trace(e);
							}
						}
					}
				} finally {
					dis.close();
				}

			}

		} finally {
			httpget.releaseConnection();
		}

		return resultSet;
	}

	/**
	 * @param httpget
	 * @throws UnsupportedEncodingException
	 */
	protected abstract HttpMethodBase getRequestMethod(MyUrl url) throws UnsupportedEncodingException;

	/**
	 * Gets the stock quotation url.
	 * 
	 * @param ticker the ticker
	 * @param startYear the start year
	 * @param startMonth the start month
	 * @param startDay the start day
	 * @param endYear the end year
	 * @param endMonth the end month
	 * @param endDay the end day
	 * 
	 * @return the stock quotation url
	 */
	public abstract MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay);
	

	/**
	 * Gets the stock info page url.
	 * 
	 * @param refName the isin
	 * 
	 * @return the stock info page url
	 * @throws UnsupportedEncodingException 
	 */
	public abstract String getStockInfoPageURL(String refName) throws UnsupportedEncodingException; 
	
	/**
	 * Gets the market stock list url.
	 * @param marche the marche
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * 
	 * @return the market stock list url
	 */
	public abstract String getCategoryStockListURL(StockCategories category, String...params);

	/**
	 * Gets the thread pool.
	 * 
	 * @return the thread pool
	 */
	public abstract PoolSemaphore getThreadPool();

	/**
	 * Stop threads.
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract void stopThreads();


	public ScraperMetrics getScrapperMetrics() {
		return beanFactoryAware.getBeanFactory().getBean(ScraperMetrics.class);
	}

}
