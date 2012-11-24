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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.NotImplementedException;

public class MyUrl {
	
	private String url;
	private List<NameValuePair> httpParams;
	private Integer nbPages;
	
	
	public MyUrl(String url) {
		super();
		this.url = url;
		this.httpParams = new ArrayList<NameValuePair>();
	}
	
	
	public MyUrl() {
		super();
		this.httpParams = new ArrayList<NameValuePair>();
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public List<NameValuePair> getHttpParams() {
		return httpParams;
	}
	public void setHttpParams(List<NameValuePair> httpParams) {
		this.httpParams = httpParams;
	}


	public Integer getNbPages() {
		return nbPages;
	}


	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}
	
	public MyUrl getUrlForPage(Integer i) {
		throw new NotImplementedException();
	}
	
	
}
