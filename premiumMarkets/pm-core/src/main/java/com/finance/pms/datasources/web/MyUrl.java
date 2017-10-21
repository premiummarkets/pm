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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.http.NameValuePair;

public class MyUrl {

    private String url;
    private List<NameValuePair> httpParams;
    private Integer nbPages;
    private ArrayList<String> cookies;


    public MyUrl(String url) {
        super();
        this.url = url;
        this.httpParams = new ArrayList<NameValuePair>();
        this.cookies = new ArrayList<String>();
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


    public void addCookie(String cookie) {
        this.cookies.add(cookie);
    }


    public String getCookieString() {
        return cookies.stream()
                .reduce(
                  (r, c) -> {
                      try {
                          c = URLEncoder.encode(c,"UTF-8");
                      } catch (UnsupportedEncodingException e) {
                          throw new RuntimeException(e);
                      }
                      r = r + ";" + c;
                      return r;
                  }
               ).get();
    }

}
