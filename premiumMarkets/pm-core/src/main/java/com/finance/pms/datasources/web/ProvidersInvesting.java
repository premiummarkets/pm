package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuoteInvestingFormater;
import com.finance.pms.datasources.web.formaters.StopParseErrorException;
import com.finance.pms.events.calculation.DateFactory;
import com.nixxcode.jvmbrotli.common.BrotliLoader;
import com.nixxcode.jvmbrotli.dec.BrotliInputStream;


public class ProvidersInvesting extends Providers implements QuotationProvider {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersInvesting.class);
	

	protected ProvidersInvesting(String pathToProps) {
		super();
		//this.httpSource = new HttpSourceInvesting(pathToProps, this);
	}
	
	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws Exception {
		
		BrotliLoader.isBrotliAvailable();
		
		String symbolRoot = stock.getSymbolRoot();
		TreeSet<Validatable> queries = new TreeSet<>();
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
			
			HttpPost searchRequest = new HttpPost("https://www.investing.com/search/service/search");
			initHeaders(searchRequest);
			searchRequestParams(stock, symbolRoot, searchRequest);
			
			//	
			CloseableHttpResponse searchResponse = httpClient.execute(searchRequest);
			HttpEntity searchResponseEntity = searchResponse.getEntity();
			try (InputStream searchContent =
					(searchResponseEntity.getContentEncoding().getValue().equals("br"))?
							new BrotliInputStream(searchResponseEntity.getContent()):searchResponseEntity.getContent()){
				Integer pair_ID = extractPairId(stock, symbolRoot, searchContent);
				HttpPost request = new HttpPost("https://www.investing.com/instruments/HistoricalDataAjax");
				initHeaders(request);
				
				//curr_id=38411&st_date=06%2F18%2F2017&end_date=07%2F18%2F2017&interval_sec=Daily&sort_col=date&sort_ord=DESC&action=historical_data
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				ArrayList<NameValuePair> postParameters;
				postParameters = new ArrayList<NameValuePair>();
				postParameters.add(new BasicNameValuePair("curr_id", pair_ID.toString()));
			    postParameters.add(new BasicNameValuePair("st_date", dateFormat.format(start)));
			    postParameters.add(new BasicNameValuePair("end_date", dateFormat.format(end)));
			    postParameters.add(new BasicNameValuePair("interval_sec", "Daily"));
			    postParameters.add(new BasicNameValuePair("sort_col", "date"));
			    postParameters.add(new BasicNameValuePair("sort_ord", "DESC"));
			    postParameters.add(new BasicNameValuePair("action", "historical_data"));
			    HttpEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");
				request.setEntity(entity);
				
				CloseableHttpResponse response = httpClient.execute(request);
				HttpEntity responseEntity = response.getEntity();
				try (BufferedReader content = new BufferedReader(new InputStreamReader(
						(responseEntity.getContentEncoding().getValue().equals("br"))?
								new BrotliInputStream(responseEntity.getContent()):responseEntity.getContent()))) {
					DayQuoteInvestingFormater dayQuoteInvestingFormater = new DayQuoteInvestingFormater(null, stock);
					Date lastMarketCloseDate = end;
					String line = "";
					while ((line = content.readLine()) != null) {
						System.out.println(line);
						List<Validatable> ohlcvListForLine = dayQuoteInvestingFormater.formatLine(line);
						List<Validatable> ohlcvValids = ohlcvListForLine.stream().filter(ohlcv -> !((DailyQuotation) ohlcv).getQuoteDate().after(lastMarketCloseDate)).collect(Collectors.toList());
						queries.addAll(ohlcvValids);
					};
				}
				
			} catch (Exception e) {
				throw e;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		try {
			ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
			tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
		} catch (SQLException e) {
			LOGGER.error("Investing quotations sql error trying : " + stock, e);
			throw e;
		}
		
	}

	protected int extractPairId(Stock stock, String symbolRoot, InputStream content) throws StopParseErrorException {
		
		int pair_ID = -1;
		JsonReader rdr = Json.createReader(content);
		JsonObject obj = rdr.readObject();
		JsonArray jsonArray = obj.getJsonArray("All");
		Stream<JsonValue> filter = jsonArray.stream().filter(s -> ((JsonObject)s).getString("symbol").equals(symbolRoot));
		ArrayList<JsonValue> collected = filter.collect(Collectors.toCollection(ArrayList::new));
		
		int count = collected.size();
		switch(count) {
			case 0 : 
				throw new StopParseErrorException(stock, "not found", "not found");
			case 1 :
				pair_ID = ((JsonObject)collected.stream().findFirst().get()).getInt("pair_ID");
			default :
				Stream<JsonValue> mFilter = collected.stream().filter(s -> {
					String extString = ((JsonObject)s).getString("aql_link").split(":")[0];
					LOGGER.info("Extension string test for : " + stock + " : " + extString + " to compare with " + stock.getMarket().getYahooExtension() + " and " + stock.getMarket().getYahooExtension().getSpecificMarketExtension());
					return extString.equals(stock.getMarket().getYahooExtension().name()) || extString.equals(stock.getMarket().getYahooExtension().getSpecificMarketExtension());
				}
				);
				pair_ID = ((JsonObject)mFilter.findFirst().get()).getInt("pair_ID");
		};
		
		return pair_ID;
	}

	protected void searchRequestParams(Stock stock, String symbolRoot, HttpPost request) throws UnsupportedEncodingException {
		//search_text="INXG"&term="INXG"&country_id="0"&tab_id="All"
		ArrayList<NameValuePair> postParameters;
		postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("search_text", symbolRoot));
	    postParameters.add(new BasicNameValuePair("term", stock.getSymbolRoot()));
	    postParameters.add(new BasicNameValuePair("country_id", "0"));
	    postParameters.add(new BasicNameValuePair("tab_id", "All"));
	    HttpEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");
		request.setEntity(entity);
	}

	protected void initHeaders(HttpPost request) {
		request.setHeader("Content-Type","application/x-www-form-urlencoded");
		request.setHeader("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:54.0) Gecko/20100101 Firefox/54.0");
		request.setHeader("Host", "www.investing.com");
		request.setHeader("Accept", "text/plain, */*; q=0.01");
		request.setHeader("Accept-Language", "en-US,en;q=0.5");
		request.setHeader("Accept-Encoding", "gzip, deflate, br");
		request.setHeader("X-Requested-With", "XMLHttpRequest");
		request.setHeader("Referer", "https://www.investing.com/etfs/etfs-gold-historical-data");
		request.setHeader("Connection", "keep-alive");
		request.setHeader("DNT", "1");
	}

	@Override
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Validatable> readPage(Stock stock, MyUrl url, Date startDate) throws HttpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStockRefName(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {
		// TODO Auto-generated method stub
	}

}
