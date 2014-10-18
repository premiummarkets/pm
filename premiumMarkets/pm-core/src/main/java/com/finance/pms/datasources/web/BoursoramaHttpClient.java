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


@Deprecated
public class BoursoramaHttpClient {
	
	public BoursoramaHttpClient() {
//		this.setUpClient();
//		this.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	}
	
//	@Override
//	public void preExecuteMethod(HttpMethod method) {
//		setHeader(method);	
//		this.addReceivedCookies(this, method);
//		
//		//add other cookies
//		method.addRequestHeader("Cookie","OBJECT_BOURSORAMA=1");
//		method.addRequestHeader("Cookie","TestIfCookieP=ok");
//		method.addRequestHeader("Cookie","useruid=66c85e494b5179772a3f665bb00690e9236b4d9ffcf297a368fa65a8105aa3b5");
//		method.addRequestHeader("Cookie","forced=1");
//		method.addRequestHeader("Cookie","version=19");
//	}
//
//	@Override
//	public void postExecuteMethod(HttpMethod method) {
//		this.extractReceivedCookies(method);
//		this.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
//	}

//	private void extractReceivedCookies(HttpMethod httpget) {
//		
//		Header[] receivedCookies = httpget.getResponseHeaders("Set-Cookie");
//		for (Header header:  receivedCookies) {
//			
//			String headerCookie = header.getValue();
//			if (!headerCookie.contains("OBJECT_BOURSORAMA")) {
//				splitCookie(headerCookie);
//			}
//			
//		}
//	}

	private void splitCookie(String headerValue) {
		String[] headerNVPairs = headerValue.split(";");
		String name = null, value = null, expires = null, path = null, domain = null;
		boolean first = true;
		for (String nvPairString : headerNVPairs) {
			String nvPair[] = nvPairString.split("=");
			String n = nvPair[0].trim();
			String v = nvPair[1].trim();
			if (first) {
				name = n;
				value = v;
				first= false;
			} else {
				if (n.equals("expires")) {
					expires = v;
				}
				if (n.equals("path")) {
					path = v;
				}
				if (n.equals("domain")) {
					domain = v; //.replaceFirst(".","");
				}
			}
		}
		
//		this.createSessionCookie(domain, name, value, path, expires);
	}
	
//	private void addReceivedCookies(MyHttpClient httpclient, HttpMethod httpMethod, String... additionalCookies) {
//
//		Collection<Cookie> cookieValues = httpclient.getCookies().values();
//		for (Cookie cookie : cookieValues) {
//			String cookedCookie = cookie.getName()+"="+cookie.getValue();
//			httpMethod.addRequestHeader("Cookie",cookedCookie);
//		}
//		
//	}
	
//	private void setHeader(HttpMethod httppost) {
//		httppost.setRequestHeader("Host","www.boursorama.com");
//		httppost.setRequestHeader("User-Agent","Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.9.2.22) Gecko/20110905 Ubuntu/10.04 (lucid) Firefox/3.6.22");
//		httppost.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		httppost.setRequestHeader("Accept-Language","en-us,en;q=0.5");
//		httppost.setRequestHeader("Accept-Encoding","gzip,deflate");
//		httppost.setRequestHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//		httppost.setRequestHeader("Keep-Alive","115");
//		httppost.setRequestHeader("Connection","keep-alive");
//	}

//	@Override
//	public Boolean isValid() {
//		return true;
//	}
//
//	@Override
//	public HttpParams getParams() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ClientConnectionManager getConnectionManager() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void close() throws IOException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
