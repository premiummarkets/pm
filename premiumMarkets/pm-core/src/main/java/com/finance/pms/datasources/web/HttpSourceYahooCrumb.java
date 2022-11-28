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

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.SourceConnector;

/**
 * The Class HttpSourceYahoo.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceYahooCrumb extends HttpSourceQuotation implements SourceConnector {

    private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceYahooCrumb.class);


    public HttpSourceYahooCrumb(String pathToprops, MyBeanFactoryAware beanFactory) {
        super(pathToprops, beanFactory);		
    }

    @Override
    public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay,
            String endYear, String endMonth, String endDay) {
        throw new UnsupportedOperationException();
    }

    public MyUrl getYahooQuoteURL(String symbol, long startDate, long endDate, String cookie, String crumb) throws UnsupportedEncodingException {

    	if (crumb != null) {
	        try {
	            symbol = URLEncoder.encode(symbol,"UTF-8");
	            crumb = URLEncoder.encode(crumb.replace("\\u002F", "/"),"UTF-8"); //The replace may not be need with Content input stream read using StandardCharsets.UTF_8 
	            System.out.println(crumb);
	        } catch (UnsupportedEncodingException e) {
	            if (LOGGER.isDebugEnabled()) LOGGER.debug("",e);
	        }
    	}

        //https://query1.finance.yahoo.com/v7/finance/download/CNDX.L?period1=1284508800&period2=1642723200&interval=1d&events=history&includeAdjustedClose=true
        //Sat, 20 Jan 2018 21:07:49 GMT
        //Tue, 20 Feb 2018 21:07:49 GMT
        //String url = "https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1516482469&period2=1519160869&interval=1d&events=history"+"&crumb="+crumb;
        //              https://query1.finance.yahoo.com/v7/finance/download/%5EAAPL?period1=1516424400&period2=1519102800&interval=1d&events=history&crumb=GEcazs7lpjM
        //              https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1516490318&period2=1519168718&interval=1d&events=history&crumb=9Gm6dOkKT.K
    	//	https://query1.finance.yahoo.com/v7/finance/download/URA?period1=1620330560&period2=1651866560&interval=1d&events=history&includeAdjustedClose=true
        String url = "https://query1.finance.yahoo.com/v7/finance/download/" + URLEncoder.encode(symbol, "UTF-8") + "?"+
                "period1=" + startDate + "&period2=" + endDate +
                "&interval=1d&events=history&includeAdjustedClose=true" +
                ((crumb != null)?"&crumb=" + crumb:"");

        if (LOGGER.isDebugEnabled()) LOGGER.debug(url);

        MyUrl myUrl = new MyUrl(url);
        if (cookie != null) myUrl.addCookie(cookie);

        return myUrl;
    }

    /**
     * Just to get the cookie crumb
     * @return
     */
    public String getYahooQuoteCrumbURL() {
        return "https://finance.yahoo.com/quote/GOOG/history?p=GOOG";
    }

    @Override
    protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
        HttpGet httpGet = new HttpGet(url.getUrl());
        String cookieString = url.getCookieString();
		if (!cookieString.isEmpty()) httpGet.addHeader("Cookie", cookieString);
        return httpGet;
    }

}
