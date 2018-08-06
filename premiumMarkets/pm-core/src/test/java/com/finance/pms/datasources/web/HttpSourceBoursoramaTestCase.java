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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.formaters.DayQuoteBoursoramaFormater;

import junit.framework.TestCase;

/**
 * The Class HttpSourceBoursoramaTestCase.
 * 
 * @author Guillaume Thoreton
 */
public class HttpSourceBoursoramaTestCase extends TestCase {

	/** The http source. */
	HttpSourceBoursorama httpSource;


	@Override
	protected void setUp() {
		SpringContext springContext = new SpringContext("/home/guil/Developpement/Quotes/pms/db.properties");
		//springContext.setDataSource("/home/guil/Developpement/Quotes/pms/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		springContext.refresh();

		//ProvidersBoursorama boursoramaProvider = (ProvidersBoursorama) ProvidersBoursorama.getInstance("boursorama");
		//httpSource = (HttpSourceBoursorama) boursoramaProvider.getHttpSource();

	} 
	
	public void test() {
		assertTrue(true);
	}

	public void rawConnection() throws HttpException, IOException {

//		HttpClient httpClient = new HttpClient();
//		httpClient.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
//
////		String connexionGetRedirectLocation;
//		Map<String, String> boursoGetResponseCookies;
//		Map<String, String> connexionGetResponseCookies = new HashMap<String, String>();
//		Map<String, String> connexionRedirectResponseCookies;
//		String connexionPostRedirectLocation;
//		String phpSessionId;
//		
//		//Boursorama.com
//		{
//			GetMethod boursoGet = new GetMethod("http://www.boursorama.com/index.phtml");
//			setHeader(boursoGet);
//
//			int boursoGetRes = httpClient.executeMethod(boursoGet);
//			
//			assertEquals(200, boursoGetRes);
//
//			//Check response
//			Header[] boursoGetResponseHeader = boursoGet.getResponseHeaders("Set-Cookie");
//			boursoGetResponseCookies = extractCookies(boursoGetResponseHeader);
//
//			assertEquals(2, boursoGetResponseCookies.size());
//			assertTrue(boursoGetResponseCookies.containsKey("PHPSESSIONID"));
//			assertTrue(boursoGetResponseCookies.containsKey("OBJECT_BOURSORAMA"));
//			
//			phpSessionId = boursoGetResponseCookies.get("PHPSESSIONID");
//			
//		}
//		
//				
//		//Connection Get
//		{
//			GetMethod connexionGet = new GetMethod("http://www.boursorama.com/bienvenue.phtml");
//			setHeader(connexionGet);
//
//			connexionGet.addRequestHeader("Cookie","OBJECT_BOURSORAMA=0");
//			connexionGet.addRequestHeader("Cookie","forced=1");
//			connexionGet.addRequestHeader("Cookie","version=19");
//			connexionGet.addRequestHeader("Cookie","PHPSESSIONID="+phpSessionId);
//
//			int connectionGetRes = httpClient.executeMethod(connexionGet);
//
//			assertEquals(200, connectionGetRes);
//			
//			//Check response
//			Header[] connexionGetResponseHeader = connexionGet.getResponseHeaders("Set-Cookie");
//			connexionGetResponseCookies = extractCookies(connexionGetResponseHeader);
//
//			assertEquals(1, connexionGetResponseCookies.size());
//			assertTrue(connexionGetResponseCookies.containsKey("STORAGE_COOKIE"));
//			
////			InputStream in;
////			if (connexionGet.getResponseHeader("Content-Encoding") != null && "gzip".equals(connexionGet.getResponseHeader("Content-Encoding").getConstant())) {
////				in = new GZIPInputStream(connexionGet.getResponseBodyAsStream());
////			}
////			else {
////				in = connexionGet.getResponseBodyAsStream();
////			}
////
////			BufferedReader dis = new BufferedReader(new InputStreamReader(in));
////			String line;
////			while ((line = dis.readLine()) != null)
////			{
////				System.out.println(line);
////			}
//
////			connexionGetRedirectLocation = connexionGet.getResponseHeader("Location").getConstant();
////			assertEquals("https://www.boursorama.com/connexion.phtml?noorg=1", connexionGetRedirectLocation);
//
//		}
//		
//
//		//Redirect
//		{
//			GetMethod connexionRedirect = new GetMethod("https://www.boursorama.com/connexion.phtml");
//			//GetMethod connexionRedirect = new GetMethod("");
//			setHeader(connexionRedirect);
//			//additionalCookies(connexionRedirect,"0");
//			
//			connexionRedirect.addRequestHeader("Cookie","OBJECT_BOURSORAMA=0");
//			connexionRedirect.addRequestHeader("Cookie","TestIfCookie=ok");
//			connexionRedirect.addRequestHeader("Cookie","useruid=e80d50d9f9a82dee7d15815329f74452a2c4ace7faf8b9bdb8a51ac5a785cfe8");
//			connexionRedirect.addRequestHeader("Cookie","PHPSESSIONID="+boursoGetResponseCookies.get("PHPSESSIONID"));
//			connexionRedirect.addRequestHeader("Cookie","STORAGE_COOKIE="+boursoGetResponseCookies.get("STORAGE_COOKIE"));
//			connexionRedirect.addRequestHeader("Cookie","forced=1");
//			connexionRedirect.addRequestHeader("Cookie","version=19");
//
//			int connectionRedirectRes = httpClient.executeMethod(connexionRedirect);
//
//			//Request check
//			Header[] connexionRedirectRequestHeader = connexionRedirect.getRequestHeaders("Cookie");
//			Map<String, String> connexionRedirectRequestCookies = extractCookies(connexionRedirectRequestHeader);
//			assertTrue(connexionRedirectRequestCookies.containsKey("PHPSESSIONID"));
//			assertTrue(connexionRedirectRequestCookies.containsKey("STORAGE_COOKIE"));
//
//			assertEquals(connexionGetResponseCookies.get("PHPSESSIONID"), connexionRedirectRequestCookies.get("PHPSESSIONID"));
//			assertEquals(connexionGetResponseCookies.get("STORAGE_COOKIE"), connexionRedirectRequestCookies.get("STORAGE_COOKIE"));
//
//			assertEquals(200, connectionRedirectRes);
//
//			//Response check
//			Header[] connexionRedirectResponseHeader = connexionRedirect.getResponseHeaders("Set-Cookie");
//			connexionRedirectResponseCookies = extractCookies(connexionRedirectResponseHeader);
//
//			assertEquals(2, connexionRedirectResponseCookies.size());
//			assertTrue(connexionRedirectResponseCookies.containsKey("PHPSESSIONID"));
//			assertEquals(boursoGetResponseCookies.get("PHPSESSIONID"), connexionRedirectResponseCookies.get("PHPSESSIONID"));
//			//assertTrue(connexionRedirectResponseCookies.containsKey("STORAGE_COOKIE"));
//
//		}
//
//		//Login
//		{
//			PostMethod connectionPost = new PostMethod("https://www.boursorama.com/logunique.phtml");
//			setHeader(connectionPost);
//			connectionPost.setRequestHeader("Content-Type","text/html; charset=ISO-8859-1");
//			connectionPost.addParameter("org", "");
//			connectionPost.addParameter("redirect", "");
//			connectionPost.addParameter("login", "guitoun");
//			connectionPost.addParameter("password",  "Luig2000");
//			connectionPost.addParameter("memo", "oui");
//			connectionPost.addParameter("submit2", "Se connecter");
//
//			additionalCookies(connectionPost,"0");
//			connectionPost.setRequestHeader("Referer","https://www.boursorama.com/connexion.phtml?");
//
//			int connectionPostRes = httpClient.executeMethod(connectionPost);
//
//			assertEquals(302, connectionPostRes);
//
//			//Request check
//			Header[] connexionPostRequestHeader = connectionPost.getRequestHeaders("Cookie");
//			Map<String, String> connexionPostRequestCookies = extractCookies(connexionPostRequestHeader);
//			assertTrue(connexionPostRequestCookies.containsKey("PHPSESSIONID"));
//			assertTrue(connexionPostRequestCookies.containsKey("STORAGE_COOKIE"));
//
//			assertEquals(connexionRedirectResponseCookies.get("PHPSESSIONID"), connexionPostRequestCookies.get("PHPSESSIONID"));
//			assertEquals(connexionRedirectResponseCookies.get("STORAGE_COOKIE"), connexionPostRequestCookies.get("STORAGE_COOKIE"));
//
//			//Response Check
//			Header[] connexionPostResponseHeader = connectionPost.getResponseHeaders("Set-Cookie");
//			Map<String, String> connexionPostResponseCookies = extractCookies(connexionPostResponseHeader);
//
//			assertEquals(4, connexionPostResponseCookies.size());
//			assertTrue(connexionPostResponseCookies.containsKey("OBJECT_BOURSORAMA"));
//			assertTrue(connexionPostResponseCookies.containsKey("LOGKEY"));
//			assertTrue(connexionPostResponseCookies.containsKey("STORAGE_COOKIE"));
//			assertTrue(connexionPostRequestCookies.containsKey("SESSION_SERVER_NUM"));
//
//			assertEquals(connexionGetResponseCookies.get("OBJECT_BOURSORAMA"), "1");
//			assertEquals(connexionGetResponseCookies.get("STORAGE_COOKIE"), "deleted");
//			assertEquals(connexionGetResponseCookies.get("LOGKEY"), "Z3VpdG91bg%3D%3Dad4666329c52ae5d22cd9865c965831c");
//
//			connexionPostRedirectLocation = connectionPost.getResponseHeader("Location").getConstant();
//			assertEquals("http://www.boursorama.com/index.phtml", connexionPostRedirectLocation);
//
//		}
//
//		//Index redirection
//		{
//			GetMethod indexRedirect = new GetMethod(connexionPostRedirectLocation);
//			setHeader(indexRedirect);
//			additionalCookies(indexRedirect,"1");
//
//			int indexRedirectRes = httpClient.executeMethod(indexRedirect);
//
//			assertEquals(200, indexRedirectRes);
//
//			//Request check
//			Header[] indexRedirectRequestHeader = indexRedirect.getRequestHeaders("Cookie");
//			Map<String, String> indexRedirectRequestCookies = extractCookies(indexRedirectRequestHeader);
//			assertTrue(indexRedirectRequestCookies.containsKey("PHPSESSIONID"));
//			assertTrue(indexRedirectRequestCookies.containsKey("SESSION_SERVER_NUM"));
//			assertTrue(indexRedirectRequestCookies.containsKey("LOGKEY"));
//
//			assertEquals(connexionRedirectResponseCookies.get("PHPSESSIONID"), indexRedirectRequestCookies.get("PHPSESSIONID"));
//			assertEquals(connexionGetResponseCookies.get("LOGKEY"), "Z3VpdG91bg%3D%3Dad4666329c52ae5d22cd9865c965831c");
//
//			//Response check
//			Header[] indexRedirectResponseHeader = indexRedirect.getResponseHeaders("Set-Cookie");
//			Map<String, String> indexRedirectResponseCookies = extractCookies(indexRedirectResponseHeader);
//
//			assertEquals(0, indexRedirectResponseCookies.size());
//
//			InputStream in;
//			if (indexRedirect.getResponseHeader("Content-Encoding") != null && "gzip".equals(indexRedirect.getResponseHeader("Content-Encoding").getConstant())) {
//				in = new GZIPInputStream(indexRedirect.getResponseBodyAsStream());
//			}
//			else {
//				in = indexRedirect.getResponseBodyAsStream();
//			}
//
//			BufferedReader dis = new BufferedReader(new InputStreamReader(in));
//			String line;
//			Boolean found = false;
//			while ((line = dis.readLine()) != null)
//			{
//				found = line.contains("<a class=\"logout\" href=\"/deconnexion.phtml?deconnecter=oui\">Déconnexion</a>");
//			}
//
//			assertTrue(found);
//
//		}

	}

//	private void setHeader(HttpMethod httppost) {
//		
//		httppost.setRequestHeader("Host","www.boursorama.com");
//		httppost.setRequestHeader("User-Agent","Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.9.2.22) Gecko/20110905 Ubuntu/10.04 (lucid) Firefox/3.6.22");
//		httppost.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		httppost.setRequestHeader("Accept-Language","en-us,en;q=0.5");
//		httppost.setRequestHeader("Accept-Encoding","gzip,deflate");
//		httppost.setRequestHeader("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//		httppost.setRequestHeader("Keep-Alive","115");
//		httppost.setRequestHeader("Connection","keep-alive");
//	}


	private Map<String, String> extractCookies(Header[] connexionGetResponseHeader) {

		Map<String,String> connexionGetResponseCookies = new HashMap<String, String>();
		for (Header header:  connexionGetResponseHeader) {

			String cookieString = header.getValue();
			String[] headerNVPairs = cookieString.split(";");

			String nvPair[] = headerNVPairs[0].split("=");
			String name = nvPair[0].trim();
			String value = nvPair[1].trim();
			connexionGetResponseCookies.put(name,value);

		}
		return connexionGetResponseCookies;
	}

//	private void additionalCookies(HttpMethod connexionGet, String obgBours) {
//		connexionGet.addRequestHeader("Cookie","OBJECT_BOURSORAMA="+obgBours);
////		connexionGet.addRequestHeader("Cookie","TestIfCookieP=ok");
////		connexionGet.addRequestHeader("Cookie","TestIfCookie=ok");
////		connexionGet.addRequestHeader("Cookie","useruid=66c85e494b5179772a3f665bb00690e9236b4d9ffcf297a368fa65a8105aa3b5");
//		connexionGet.addRequestHeader("Cookie","forced=1");
//		connexionGet.addRequestHeader("Cookie","version=19");
//	}

	public void myhttpConnect() {
//
//		BoursoramaHttpClient httpclient;
//		try {
//			httpclient = (BoursoramaHttpClient) httpSource.myHttpConnect();
//
//			GetMethod httpget = new GetMethod("http://www.boursorama.com/monbourso/outils/telechargement/telechargement.phtml");
//
//			int result = httpclient.executeMethod(httpget);
//
//			assertEquals(200, result);
//
//
//		} catch (HttpException e) {
//			assertTrue(false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}  
//
//	}

//	public void telechargement() {
//
//		BoursoramaHttpClient httpclient;
//		try {
//			httpclient = (BoursoramaHttpClient) httpSource.myHttpConnect();
//
//			GetMethod httpget = new GetMethod("http://www.boursorama.com/monbourso/outils/telechargement/telechargement.phtml");
//
//			int getRes = httpclient.executeMethod(httpget);
//
//			InputStream in;
//			if (httpget.getResponseHeader("Content-Encoding") != null && "gzip".equals(httpget.getResponseHeader("Content-Encoding").getConstant())) {
//				in = new GZIPInputStream(httpget.getResponseBodyAsStream());
//			}
//			else {
//				in = httpget.getResponseBodyAsStream();
//			}
//
//			BufferedReader dis = new BufferedReader(new InputStreamReader(in));
//			String line;
//			while ((line = dis.readLine()) != null)
//			{
//				System.out.println("Response :" + line);
//			}
//
//
//			assertEquals(200, getRes);
//
//			PostMethod httpPost = new PostMethod("http://www.boursorama.com/monbourso/outils/telechargement/telechargement.phtml");

			List<NameValuePair> listHttpParams = 	new ArrayList<NameValuePair>();	
//			listHttpParams.add(new NameValuePair("hid_date", "ok"));
//			listHttpParams.add(new NameValuePair("SELECT_PORT", "Tous"));
//			listHttpParams.add(new NameValuePair("MARCHE", "SICOVAM"));	
//			listHttpParams.add(new NameValuePair("CODE", "GB00B0CTWC01"));
//			listHttpParams.add(new NameValuePair("A_LIBELLE", "1"));
//			listHttpParams.add(new NameValuePair("A_SICO", "1"));
//			listHttpParams.add(new NameValuePair("A_DATE", "1"));
//			listHttpParams.add(new NameValuePair("A_OUV", "1"));
//			listHttpParams.add(new NameValuePair("A_HAUT", "1"));
//			listHttpParams.add(new NameValuePair("A_BAS", "1"));
//			listHttpParams.add(new NameValuePair("A_CLOT", "1"));
//			listHttpParams.add(new NameValuePair("A_VOL", "1"));
//			listHttpParams.add(new NameValuePair("jour1", "16"));
//			listHttpParams.add(new NameValuePair("mois1", "09"));
//			listHttpParams.add(new NameValuePair("annee1", "2011"));
//			listHttpParams.add(new NameValuePair("jour2", "21"));
//			listHttpParams.add(new NameValuePair("mois2", "09"));
//			listHttpParams.add(new NameValuePair("annee2", "2011"));
//			listHttpParams.add(new NameValuePair("ISINY", "Y"));
//			listHttpParams.add(new NameValuePair("FILE_FORMAT", DOWNLOADTYPE.LIBRE));
//			listHttpParams.add(new NameValuePair("download", "Télécharger"));
			//			try {
			//				listHttpParams.add(new NameValuePair("download", URLEncoder.encode("Télécharger","ISO-8859-15")));
			//			} catch (UnsupportedEncodingException e) {
			//				e.printStackTrace();
			//			}
			//			
//			httpPost.addParameters(listHttpParams.toArray(new NameValuePair[20]));
//
//			httpPost.addRequestHeader("Cookie","pbw=%24b%3D12036%3B%24o%3D99999%3B%24sh%3D1080%3B%24sw%3D1920");
//			httpPost.addRequestHeader("Cookie","pdomid=1");
//			httpPost.addRequestHeader("Cookie","pid=9048030599386453242");
//			httpPost.addRequestHeader("Cookie","utmctr=boursorama");
//			httpPost.addRequestHeader("Cookie","pbwmaj=6");
//			httpPost.addRequestHeader("Cookie","Trk874=Creation=21%2F09%2F2011+16%3A44%3A39&Value=607");
//			httpPost.addRequestHeader("Cookie","statid=388305");
//
//			httpPost.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//			httpPost.setRequestHeader("Content-Lenght","244");
//
//			int result = httpclient.executeMethod(httpPost);
//
//			assertEquals(200, result);
//
//
//		} catch (HttpException e) {
//			assertTrue(false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}  

	}

	public void httpSource() throws ParseException {

		//BoursoramaHttpClient httpclient;
		try {
			//httpclient = (BoursoramaHttpClient) httpSource.myHttpConnect();

			Stock ticker = new Stock("GB00B0CTWC01","OILB.L");

			Date start = new SimpleDateFormat("dd/MM/yyyy").parse("16/09/2011");
			Date end = new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2011");

			MyUrl stockQuotationURL = this.httpSource.getStockQuotationURL(ticker.getIsin(), 
					new SimpleDateFormat("yyyy").format(start),new SimpleDateFormat("MM").format(start),new SimpleDateFormat("dd").format(start),
					new SimpleDateFormat("yyyy").format(end), new SimpleDateFormat("MM").format(end), new SimpleDateFormat("dd").format(end));

			DayQuoteBoursoramaFormater dayQuoteFormater = new DayQuoteBoursoramaFormater(stockQuotationURL, ticker, Currency.EUR.name());		

			List<Validatable> res = this.httpSource.readURL(dayQuoteFormater);

			System.out.println(res);


		} catch (HttpException e) {
			assertTrue(false);
		} 
	}

}
