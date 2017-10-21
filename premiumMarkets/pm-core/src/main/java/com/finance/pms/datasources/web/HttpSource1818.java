package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

public class HttpSource1818 extends HttpSourceQuotation {

	public HttpSource1818(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay) {
		MyUrl myUrl = new MyUrl(String.format("https://protect.banqueprivee1818.com/phoenix/infosMarcheValeur/detache?page=histo&valeur=%s,25",ticker));
		myUrl.getHttpParams().add(new BasicNameValuePair("dateDebut", startDay+"/"+startMonth+"/"+startYear));
		myUrl.getHttpParams().add(new BasicNameValuePair("dateFin", endDay+"/"+endMonth+"/"+endYear));
		return myUrl;
	}

	@Override
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
				 
		HttpPost postMethod = new HttpPost(url.getUrl());
		
	    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		//List<NameValuePair> httpParams = url.getHttpParams();
	    //List<BasicNameValuePair> postParams = postParameters.stream().map(param -> new BasicNameValuePair(param.getName(), param.getValue())).collect(Collectors.toList());
		//postParameters.addAll(postParams);
	    postParameters.addAll(url.getHttpParams());
	    
	    postParameters.add(new BasicNameValuePair("typeInstu", "EQU"));
	    postParameters.add(new BasicNameValuePair("submit", " OK "));
	    
	    postMethod.setEntity(new UrlEncodedFormEntity(postParameters));
	    
		return postMethod;
	}

}
