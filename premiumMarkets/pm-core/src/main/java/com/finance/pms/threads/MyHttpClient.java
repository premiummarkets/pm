/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.threads;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;

import com.finance.pms.admin.install.logging.MyLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class MyHttpClient.
 * 
 * @author Guillaume Thoreton
 */
public abstract class MyHttpClient extends HttpClient implements SourceClient {
	
	private static MyLogger LOGGER = MyLogger.getLogger(MyHttpClient.class);
	
	private Map<String, Cookie> cookies;
	

	/**
	 * Instantiates a new my http client.
	 * 
	 * @author Guillaume Thoreton
	 */
	public MyHttpClient() {
		super();
		this.cookies = new HashMap<String, Cookie>();
	}


	public Map<String, Cookie> getCookies() {
		return cookies;
	}
	
	public void createSessionCookie(String domain, String name, String value, String path, String expirse) {
		//Sun, 19-Sep-2021 14:02:35 GMT
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z");
		Cookie cookie;
		try {
			cookie = new Cookie(domain, name, value, path,(expirse != null)?dateFormat.parse(expirse):null, false);
			this.cookies.put(name,cookie);
		} catch (ParseException e) {
			LOGGER.warn("invalid cookie",e);
		}
		
	}

	@Override
	public int executeMethod(HttpMethod method) throws IOException, HttpException {
		
		preExecuteMethod(method);
		int ret =  super.executeMethod(method);
		postExecuteMethod(method);
		
		return ret;
		
	}
	
	public abstract void preExecuteMethod(HttpMethod method);
	
	public abstract void postExecuteMethod(HttpMethod method);
	
	/**
	 * Gets the http client.
	 * 
	 * @return the http client
	 */
	public void setUpClient() {
	
		this.getParams().setSoTimeout(300000);
		this.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
		
		// RFC 2101 cookie management spec is used per default
		// to parse, validate, format & match cookies
		//httpclient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		//httpclient.getParams().setCookiePolicy(CookiePolicy.NETSCAPE);
		this.getParams().setCookiePolicy(CookiePolicy.DEFAULT);
		
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
	
}
