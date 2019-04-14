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

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StopParseErrorException;
import com.finance.pms.datasources.web.formaters.StopParseFoundException;
import com.finance.pms.threads.ApacheHttpClient;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.PoolSemaphore;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

/**
 * The Class HttpSource.
 * 
 * @author Guillaume Thoreton
 */
public abstract class HttpSource implements SourceConnector {

	private static MyLogger LOGGER = MyLogger.getLogger(HttpSource.class);

	private int nbHttpThreads; // = 20;
	private PoolSemaphore threadPool;

	private MyBeanFactoryAware beanFactoryAware;


	public static void stopALLThreads() {
		//TODO stop all instances
	}

	public HttpSource(String pathToprops, MyBeanFactoryAware beanFActoryAware) {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream((new File(pathToprops))));
			//Connection
			if (props.containsKey("http.login"))
				MainPMScmd.getMyPrefs().put("http.login", props.getProperty("http.login"));
			if (props.containsKey("http.password"))
				MainPMScmd.getMyPrefs().put("http.password", props.getProperty("http.password"));
			//Nb Threads
			if (props.containsKey("http.poolsize"))
				MainPMScmd.getMyPrefs().put("http.poolsize", props.getProperty("http.poolsize"));
			//Email
			MainPMScmd.getMyPrefs().remove("mail.host");
			MainPMScmd.getMyPrefs().remove("mail.username");
			MainPMScmd.getMyPrefs().remove("mail.password");
			MainPMScmd.getMyPrefs().remove("mail.from");
			if (props.containsKey("mail.to"))
				MainPMScmd.getMyPrefs().put("mail.to", props.getProperty("mail.to"));
			if (props.containsKey("mail.from") && !props.getProperty("mail.from").equals(""))
				MainPMScmd.getMyPrefs().put("mail.from", props.getProperty("mail.from"));
			if (props.containsKey("mail.host") && !props.getProperty("mail.host").equals(""))
				MainPMScmd.getMyPrefs().put("mail.host", props.getProperty("mail.host"));
			if (props.containsKey("mail.username") && !props.getProperty("mail.username").equals(""))
				MainPMScmd.getMyPrefs().put("mail.username", props.getProperty("mail.username"));
			if (props.containsKey("mail.password") && !props.getProperty("mail.password").equals(""))
				MainPMScmd.getMyPrefs().put("mail.password", props.getProperty("mail.password"));

			MainPMScmd.getMyPrefs().flushy();


		} catch (Exception e) {
			LOGGER.error("Couldn't find propertie file. Check the propeties path",e);
		}

		//		//Init connection
		//		//HttpState initialState = new HttpState();
		//		initialState = new HttpState();
		//		// Initial set of cookies can be retrieved from persistent storage and
		//		// re-created, using a persistence mechanism of choice,
		//		Cookie mycookie = new Cookie(".foobar.com", "mycookie", "stuff", "/", null, false);
		//		// and then added to your HTTP state instance
		//		initialState.addCookie(mycookie);

		//init thread
		this.nbHttpThreads = (new Integer(MainPMScmd.getMyPrefs().get("http.poolsize", "10"))).intValue();
		this.threadPool = new PoolSemaphore(this.nbHttpThreads, this, false);

		this.beanFactoryAware = beanFActoryAware;

	}

	public MyHttpClient<?,?> getConnectionFromPool() throws HttpException {
		ApacheHttpClient ret;
		try {
			ret = (ApacheHttpClient) this.getThreadPool().getResource();
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

	public void realesePoolConnection(SourceClient conn) {
		this.getThreadPool().releaseResource(conn);
	}

	public ApacheHttpClient httpConnect() {

		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(30000);
		requestBuilder = requestBuilder.setSocketTimeout(30000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(30000);
		requestBuilder = requestBuilder.setCookieSpec(CookieSpecs.DEFAULT);

		HttpClientBuilder builder = HttpClientBuilder.create();     
		builder.setDefaultRequestConfig(requestBuilder.build());
		CloseableHttpClient closeableHttpClient = builder.build();

		return new ApacheHttpClient(closeableHttpClient);

	}

	public List<Validatable> readURL(LineFormater formater) throws HttpException {

		MyHttpClient<?,?> httpcx = this.getConnectionFromPool();

		List<Validatable> ret = new ArrayList<Validatable>();
		try {

			ret.addAll(this.readURL(formater, httpcx));

			MetricsResType metricType = completeMetricRes(formater, ret);
			getScrapperMetrics().addRecord(formater, metricType);

		} catch (HttpException e) {

			LOGGER.warn(e);
			getScrapperMetrics().addRecord(formater, e.getMessage(), MetricsResType.HTTPERROR);
			throw e;

		} catch (IOException e) {

			LOGGER.warn(e);
			getScrapperMetrics().addRecord(formater, e.getMessage(), MetricsResType.FAILURE);
			throw new HttpException(e.getMessage());

		} catch (Exception e) {

			LOGGER.error("ERROR processing url :" + e, e);
			getScrapperMetrics().addRecord(formater, e.getMessage(), MetricsResType.FAILURE);

		} finally {
			this.realesePoolConnection((SourceClient) httpcx);
		}

		return ret;
	}

	private MetricsResType completeMetricRes(LineFormater formater, List<Validatable> ret) {
		MetricsResType metricType;

		if (!ret.isEmpty()) {//Results were found

			if (formater.isResultValueEqNA()) {//But NA
				metricType = MetricsResType.EMPTYRESULTS;
			} else {
				metricType = MetricsResType.SUCCESS;
			}

		} else {//No results found

			if (formater.canHaveNoResultsFound()) {//But that is permitted
				metricType = MetricsResType.NORESULTS;	
			} else {
				metricType = MetricsResType.FAILURE;
			}

		} 
		return metricType;
	}

	protected Set<Validatable> readURL(LineFormater formater, MyHttpClient<?,?> httpcx) throws HttpException, IOException {

		@SuppressWarnings("unchecked")
		MyHttpClient<HttpUriRequest, CloseableHttpResponse> httpcxCast = (MyHttpClient<HttpUriRequest, CloseableHttpResponse>) httpcx;

		Set<Validatable> resultSet= new HashSet<Validatable>();

		HttpUriRequest httpget = this.<HttpUriRequest>getRequestMethod(formater.getMyUrl());
		CloseableHttpResponse response = httpcxCast.doCall(httpget);

		List<Exception> otherExeptions = new ArrayList<Exception>();
		try {

			int result = response.getStatusLine().getStatusCode();

			//Display status code
			while (result == 302) {//redirected

				String locationRedir = response.getFirstHeader("location").getValue();
				LOGGER.debug("Redirection URL :" + locationRedir);
				//httpget.releaseConnection();
				response.close();

				MyUrl myNewUrl = new MyUrl(locationRedir);
				myNewUrl.setHttpParams(formater.getMyUrl().getHttpParams());
				httpget = this.getRequestMethod(myNewUrl);
				response = httpcxCast.doCall(httpget);
			}


			if (result != 200) {
				throw new HttpException("Service Error "+result+" for "+formater.getUrl());

			} else {

				InputStream in;
				Header contentEncodingHeader = response.getFirstHeader("Content-Encoding");
				Header contentEncodingType = response.getFirstHeader("Content-Type");
				if ((contentEncodingHeader != null && "gzip".contains(contentEncodingHeader.getValue())) ||
						(contentEncodingType != null && "application/x-gzip".equals(contentEncodingType.getValue()))
						) {//gzip
					in = new GZIPInputStream(response.getEntity().getContent());
				}
				else 
					if (contentEncodingType != null && ("application/x-zip-compressed".equals(contentEncodingType.getValue()) || "application/zip".equals(contentEncodingType.getValue()))
							) {//zip
						in = new ZipInputStream(response.getEntity().getContent());
						((ZipInputStream) in).getNextEntry();
					} 
					else 
						if (contentEncodingType != null && 
						("application/vnd.ms-excel".equals(contentEncodingType.getValue()) || 
								"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentEncodingType.getValue()))) {//xlsx
							in = new XlsxInputStream(response.getEntity().getContent());
						}
						else {//html or txt
							in = response.getEntity().getContent();
						}

				resultSet = parseInputStream(in, formater, otherExeptions);
			}

		} catch (Exception e) {
			otherExeptions.add(e);
		} finally {
			//httpget.releaseConnection();
			response.close();
		}

		if (!otherExeptions.isEmpty() && resultSet.isEmpty()) {
			throw new IOException("Error(s) while parsing Url " + formater.getUrl() + " : "+ otherExeptions );
		}

		return resultSet;
	}

	protected Set<Validatable> parseInputStream(InputStream in, LineFormater formater, List<Exception> otherExeptions) throws UnsupportedEncodingException, IOException {

		Set<Validatable> resultSet= new HashSet<Validatable>();

		BufferedReader dis = new BufferedReader(new InputStreamReader(in,"ISO-8859-15"));
		//BufferedReader dis = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		String line = "";
		try {

			int lineNumber = 0;
			while ((line = dis.readLine()) != null && lineNumber < 2000) {

				if (LOGGER.isDebugEnabled()) System.out.println(line);

				if (line.length() > 0) {
					lineNumber++;

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
								"Stop parsing Url " + formater.getUrl() + " : "+ ((StopParseErrorException) e).getMessage()+"\n"+
										"Reason : " + ((StopParseErrorException) e).getReason());

					} catch (Exception e) {

						LOGGER.debug("Ignoring line :" + line);
						LOGGER.trace(e);
						otherExeptions.add(e);

					}
				}
			}

		} finally {
			dis.close();
		}

		return resultSet;
	}

	protected abstract <T> T getRequestMethod(MyUrl url) throws UnsupportedEncodingException;

	public abstract MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay);

	public abstract String getStockInfoPageURL(String refName) throws UnsupportedEncodingException; 

	public abstract String getCategoryStockListURL(StockCategories category, String...params);

	public PoolSemaphore getThreadPool() {
		return this.threadPool;
	}

	public void stopThreads() {
		this.threadPool.stopThreads();
	}

	public ScraperMetrics getScrapperMetrics() {
		return beanFactoryAware.getBeanFactory().getBean(ScraperMetrics.class);
	}

}
