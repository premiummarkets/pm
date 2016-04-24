package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.http.HttpException;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.threads.HtmlUnitClient;
import com.finance.pms.threads.MyHttpClient;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;

public abstract class HttpSourceAspScrapper extends HttpSource {

	public HttpSourceAspScrapper(String pathToprops, MyBeanFactoryAware beanFActoryAware) {
		super(pathToprops, beanFActoryAware);
	}

	@Override
	protected Set<Validatable> readURL(LineFormater formater, MyHttpClient<?, ?> httpcx) throws HttpException, IOException {
		
		@SuppressWarnings("unchecked")
		MyHttpClient<String, Page> httpcxCast = (MyHttpClient<String, Page> ) httpcx;
		Page page = httpcxCast.doCall(this.getRequestMethod(formater.getMyUrl()));
		WebResponse webResponse = page.getWebResponse();
		
		return super.parseInputStream(webResponse.getContentAsStream(), formater, new ArrayList<>()); 
		
	}

	@Override
	public MyHttpClient<?, ?> getConnectionFromPool() throws HttpException {
		WebClient webClient = new WebClient();
		return new  HtmlUnitClient(webClient);
	}
	

	@Override
	protected String getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
		return url.getUrl();
	}
	

}
