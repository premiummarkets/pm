package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.threads.MyHttpClient;
import com.finance.pms.threads.SimpleHttpClient;

public class HttpSourceStaticList extends HttpSourceMarket {

	public HttpSourceStaticList(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	protected MyHttpClient myHttpConnect() throws HttpException, IOException {
		return new SimpleHttpClient();
	}

	@Override
	public String getStockInfoPageURL(String refName) throws UnsupportedEncodingException {
		return "http://uk.finance.yahoo.com/q?s="+ URLEncoder.encode(refName,"UTF-8");
	}

	@Override
	public String getCategoryStockListURL(StockCategories category, String... params) {
		return "http://premiummarkets.elasticbeanstalk.com/html/stocklists/"+params[0]+".csv.gz";
		//return "http://127.0.0.1:8080/pm-gwt-1.0-SNAPSHOT/html/stocklists/"+params[0]+".csv.gz";
	}

}
