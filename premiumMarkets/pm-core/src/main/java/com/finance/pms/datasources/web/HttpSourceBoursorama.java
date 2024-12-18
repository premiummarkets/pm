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

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;

import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.threads.ApacheHttpClient;
import com.finance.pms.threads.SourceClient;
import com.finance.pms.threads.SourceConnector;

/**
 * The Class HttpSourceBoursorama.
 * 
 * @author Guillaume Thoreton
 */
@Deprecated
public class HttpSourceBoursorama extends HttpSource implements SourceConnector {
	

//	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceBoursorama.class);
	public static int numThreadGlob = 0;

	public HttpSourceBoursorama(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);		

	}

	@Override
	public MyUrl getStockQuotationURL(String ticker, String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay) {
		MyUrl ret = new MyUrl();
		
		ret.setUrl(this.getBoursoramaSICOVAMURL());
		
		List<NameValuePair> listHttpParams = 	new ArrayList<NameValuePair>();	
//		listHttpParams.add(new NameValuePair("hid_date", "ok"));
//		listHttpParams.add(new NameValuePair("SELECT_PORT", "Tous"));
//		listHttpParams.add(new NameValuePair("MARCHE", "SICOVAM"));	
//		listHttpParams.add(new NameValuePair("CODE", ticker));
//		listHttpParams.add(new NameValuePair("A_LIBELLE", "1"));
//		listHttpParams.add(new NameValuePair("A_SICO", "1"));
//		listHttpParams.add(new NameValuePair("A_DATE", "1"));
//		listHttpParams.add(new NameValuePair("A_OUV", "1"));
//		listHttpParams.add(new NameValuePair("A_HAUT", "1"));
//		listHttpParams.add(new NameValuePair("A_BAS", "1"));
//		listHttpParams.add(new NameValuePair("A_CLOT", "1"));
//		listHttpParams.add(new NameValuePair("A_VOL", "1"));
//		listHttpParams.add(new NameValuePair("jour1", startDay));
//		listHttpParams.add(new NameValuePair("mois1", startMonth));
//		listHttpParams.add(new NameValuePair("annee1", startYear));
//		listHttpParams.add(new NameValuePair("jour2", endDay));
//		listHttpParams.add(new NameValuePair("mois2", endMonth));
//		listHttpParams.add(new NameValuePair("annee2", endYear));
//		listHttpParams.add(new NameValuePair("FILE_FORMAT", DOWNLOADTYPE.LIBRE));
//		try {
//			listHttpParams.add(new NameValuePair("download", URLEncoder.encode("T�l�charger","ISO-8859-15")));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		ret.setHttpParams(listHttpParams);
		
		return ret;

	}

	public String getBoursoramaISINURL(String ticker, // String bourse,
			String startYear, String startMonth, String startDay, String endYear, String endMonth, String endDay, String type) {

		String ret=null;
		try {
			ret = "http://www.boursorama.com/outils/telechargement/telechargement.phtml?" + "hid_date=ok&SELECT_PORT=Tous&MARCHE=SICOVAM&CODE=" + ticker
					+ "&A_LIBELLE=1&A_SICO=1&A_DATE=1&A_OUV=1&A_HAUT=1&A_BAS=1&A_CLOT=1&A_VOL=1" + "&jour1=" + startDay + "&mois1=" + startMonth + "&annee1=" + startYear + "&jour2=" + endDay + "&mois2="
					+ endMonth + "&annee2=" + endYear + "&FILE_FORMAT=" + type + "&ISINY=Y&download="
					+ URLEncoder.encode("T�l�charger","ISO-8859-15");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

	public String getBoursoramaSICOVAMURL() {
	
		//return "http://www.boursorama.com/outils/telechargement/telechargement.phtml";
		return "http://www.boursorama.com/monbourso/outils/telechargement/telechargement.phtml";
	}

	@Override
	public String getCategoryStockListURL(StockCategories category, String...params) {

		String url=null;
		try {
			url = "http://www.boursorama.com/outils/telechargement/telechargement.phtml?" + "hid_date=ok&MARCHE=" + category.getBoursoramaUrlCatHint() + "&SELECT_PORT=Tous&CODE=&A_LIBELLE=1&A_SICO=1"
			+ "&A_DATE=1&A_OUV=1&A_HAUT=1&A_BAS=1&A_CLOT=1&A_VOL=1" + "&jour1=" + params[2] + "&mois1=" + params[1] + "&annee1=" + params[0] + "&jour2=" + params[2] + "&mois2=" + params[1] + "&annee2=" + params[0]
			+ "&FILE_FORMAT=LIBRE&ISINY=Y&download=" 
			+ URLEncoder.encode("T�l�charger","ISO-8859-15");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}

	@Override
	public String getStockInfoPageURL(String isin) {
		//http://www.boursorama.com/cours.phtml?symbole=1rPFTE
		return "http://www.boursorama.com/cours.phtml?code=" + isin + "&choix_bourse=pays%3D33&categorie=";
	}

	public SourceClient connect(int connectionid) {

		SourceClient retour = null;
//		try {
//			retour = this.myHttpConnect();
//		} catch (HttpException e) {
//			LOGGER.error("",e);
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			LOGGER.error("",e);
//			throw new RuntimeException(e);
//		}
		return retour;

	}

	@Override
	public ApacheHttpClient httpConnect() {
//		MyHttpClient myHttpClient;
//		try {
//			myHttpClient = this.myHttpConnect();
//		} catch (HttpException e) {
//			LOGGER.error("Can't open http connection",e);
//		} catch (IOException e) {
//			LOGGER.error("Can't open http connection",e);
//		}
		return null;
	}

//	public MyHttpClient myHttpConnect() throws IOException, HttpException {
//		
//		int result = 404;
//		BoursoramaHttpClient httpclient = new BoursoramaHttpClient();
//		
//		// Get HTTP POST method
//		PostMethod httppost = new PostMethod("https://www.boursorama.com/logunique.phtml");
//		httppost.addParameter("org", "");
//		httppost.addParameter("redirect", "");
//		httppost.addParameter("login", MainPMScmd.getMyPrefs().get("http.login", "dummy"));
//		httppost.addParameter("password", MainPMScmd.getMyPrefs().get("http.password", "password"));
//		httppost.addParameter("submit2", "Se connecter");
//		
//		httppost.setRequestHeader("Referer","https://www.boursorama.com/connexion.phtml?");
//
//		// Execute HTTP POST
//		try {
//			
//			if (LOGGER.isDebugEnabled()) LOGGER.debug("Connection request : "+httppost.getPath());
//			
//			result = httpclient.executeMethod(httppost);
//			
//			//DEBUG
//			if (LOGGER.isDebugEnabled()) LOGGER.debug("Connection result : "+result);
//			if (LOGGER.isDebugEnabled()) {
//				 for (int i = 0; i < httppost.getResponseHeaders().length; i++) {
//					 if (LOGGER.isDebugEnabled()) LOGGER.debug(" Post Response header " + httppost.getResponseHeaders()[i].toExternalForm());
//				 }  
//			}
//			if (LOGGER.isTraceEnabled()) {
//				InputStream in;
//				if (httppost.getResponseHeader("Content-Encoding") != null && "gzip".equals(httppost.getResponseHeader("Content-Encoding").getConstant())) {
//					in = new GZIPInputStream(httppost.getResponseBodyAsStream());
//				}
//				else {
//					in = httppost.getResponseBodyAsStream();
//				}
//				
//				 BufferedReader dis = new BufferedReader(new InputStreamReader(in));
//				 String line;
//				 while ((line = dis.readLine()) != null)
//				 {
//					 if (LOGGER.isDebugEnabled()) LOGGER.debug("Response :" + line);
//				 }
//			}
//			//END DEBUG
//			
//			
//		} catch (Exception e) {
//			LOGGER.error("Login : http connection ERROR : " + e,e);
//		} 
//		finally {
//			httppost.releaseConnection();
//		}
//		
//		String redirectLocation;
//		Header locationHeader = httppost.getResponseHeader("location");
//		if (locationHeader != null) {
//			redirectLocation = locationHeader.getConstant();
//			
//			//Execute redirect
//			HttpGet httpget = new HttpGet(redirectLocation);
//			try {
//				int redirectResult = httpclient.executeMethod(httpget);
//				if (redirectResult != 200) {
//					throw new HttpException("Unexpected response on redirect"+redirectResult);
//				}
//			} finally {
//				httpget.releaseConnection();
//			}
//
//		} else {
//			// The response is invalid and did not provide the new location for the resource.  Report an error or possibly handle the response like a 404 Not Found error.
//			throw new HttpException("Unexpected response on connection"+result);
//		}
//		
//		return httpclient;
//	}


	public int crashResart(int connectionId) {
		return 0;
	}

	public void shutdownSource(SourceClient c, int connectionId) {
		// TODO Auto-generated method stub
	}

	static class DOWNLOADTYPE {

		static final String LIBRE = "LIBRE";
		static final String WALDATA = "WALDATA";

	}
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	protected HttpUriRequest getRequestMethod(MyUrl url) throws UnsupportedEncodingException {
//		PostMethod  httpget = new PostMethod(url.getUrl());
//		addRequestParams(httpget,url.getHttpParams());
//		
//		return httpget;
		return null;
	}

//	private void addRequestParams(PostMethod httpget, List<NameValuePair> listHttpParams) throws UnsupportedEncodingException {
//		
//		for (NameValuePair nameValuePair: listHttpParams ) {
//			httpget.addParameter(nameValuePair);
//		}
//	}

	@Deprecated
	protected void setRequestHeader(HttpUriRequest httpget) {
		
//				httpget.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//				httpget.setRequestHeader("Accept-Language","en-gb,en;q=0.5");
//				httpget.setRequestHeader("Host","www.boursorama.com");
//				httpget.setRequestHeader("Accept","text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//				httpget.setRequestHeader("Keep-Alive","300");
//				httpget.setRequestHeader("User-Agent","Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.8.1.14) Gecko/20080404 Firefox/2.0.0.14");
//				//httpget.setRequestHeader("Referer","http://www.boursorama.com/");
//				httpget.setRequestHeader("Accept-Encoding","gzip,deflate");
//				httpget.setRequestHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");

	}
	

}
