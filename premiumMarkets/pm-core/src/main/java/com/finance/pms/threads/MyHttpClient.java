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
package com.finance.pms.threads;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * The Class MyHttpClient.
 * 
 * @author Guillaume Thoreton
 */
public class MyHttpClient implements SourceClient {
	
//	private static MyLogger LOGGER = MyLogger.getLogger(MyHttpClient.class);
	
	private CloseableHttpClient closeableHttpClient;
	private Map<String, Cookie> cookies;
	
	public MyHttpClient(CloseableHttpClient closeableHttpClient) {
		super();
		this.cookies = new HashMap<String, Cookie>();
		this.closeableHttpClient = closeableHttpClient;
	}


	public Map<String, Cookie> getCookies() {
		return cookies;
	}
	
	public void createSessionCookie(String domain, String name, String value, String path, String expirse) {
		
//		//Sun, 19-Sep-2021 14:02:35 GMT
//		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z");
//		Cookie cookie;
//		try {
//			cookie = new Cookie(domain, name, value, path,(expirse != null)?dateFormat.parse(expirse):null, false);
//			this.cookies.put(name,cookie);
//		} catch (ParseException e) {
//			LOGGER.warn("invalid cookie",e);
//		}
		
	}

	public CloseableHttpResponse execute(HttpUriRequest method) throws IOException, ClientProtocolException {
		CloseableHttpResponse ret =  closeableHttpClient.execute(method);
		return ret;
	}
	
	public void setUpClient() {
	
//		this.getParams().setSoTimeout(300000);
//		this.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
		
		// RFC 2101 cookie management spec is used per default
		// to parse, validate, format & match cookies
		//httpclient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		//httpclient.getParams().setCookiePolicy(CookiePolicy.NETSCAPE);
//		this.getParams().setCookiePolicy(CookiePolicy.DEFAULT);
		
		// A different cookie management spec can be selected when desired
		//httpclient.getParams().setCookiePolicy(CookiePolicy.NETSCAPE);
		// Netscape Cookie Draft spec is provided for completeness
		// You would hardly want to use this spec in real life situations
		//httppclient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		// Compatibility policy is provided in order to mimic cookie
		// management of popular web browsers that is in some areas
		// not 100% standards compliant
		
//		httpclient.getParams().setParameter("http.useragent", 
//				"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.5) Gecko/2008121621 Ubuntu/8.04 (hardy) Firefox/3.0.5"); 

//		HttpParamsFactory paramsFactory = new DefaultHttpParamsFactory();
//		HttpParams httpParams = paramsFactory.getDefaultParams();
//		httpParams.setParameter("Content-Type","application/x-www-form-urlencoded");
//		httpParams.setParameter("Accept-Language","en-gb,en;q=0.5");
//		httpParams.setParameter("Host","www.boursorama.com");
//		httpParams.setParameter("Accept","text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//		httpParams.setParameter("Keep-Alive","300");
//		httpParams.setParameter("User-Agent","Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.8.1.14) Gecko/20080404 Firefox/2.0.0.14");
//		httpParams.setParameter("Referer","http://www.boursorama.com/");
//		httpParams.setParameter("Accept-Encoding","gzip,deflate");
//		httpParams.setParameter("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//		
//		httpclient.getParams().setDefaults(httpParams);
	
	}


	@Override
	public Boolean isValid() {
		return true;
	}
	
}
