package com.finance.pms.datasources.web;

import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.http.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.TableLocker;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.web.formaters.DailyQuotation;
import com.finance.pms.datasources.web.formaters.DayQuote1818Formater;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.events.calculation.DateFactory;

public class Provider1818 extends Providers implements QuotationProvider {

	private static MyLogger LOGGER = MyLogger.getLogger(Provider1818.class);

	protected Provider1818(String pathToProps) {
		super();
		this.httpSource = new HttpSource1818(pathToProps, this);
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getIsin();
	}

	@Override
	public void retrieveAndCompleteStockInfo(Stock stock, StockList stockList) {
		throw new UnsupportedOperationException("Please use another share list holder provider for that.");
	}

	@Override
	public void getQuotes(Stock stock, Date start, Date end) throws Exception {

		if (stock.getSymbol() == null) throw new RuntimeException("Error : no Symbol for "+stock.toString());

		MyUrl url;
		try {
			url = resolveUrlFor(stock, start, end);
		} catch (InvalidAlgorithmParameterException e) {
			return;
		}
		
		Date lastMarketCloseDate = DateFactory.midnithDate(DateFactory.lastMarketCloseTime(end).getTime());
		TreeSet<Validatable> queries = initValidatableSet();
		List<Validatable> ohlcList = readPage(stock, url, start);
		List<Validatable> ohlcvValids = ohlcList.stream().filter(ohlcv -> !((DailyQuotation) ohlcv).getQuoteDate().after(lastMarketCloseDate)).collect(Collectors.toList());
		queries.addAll(ohlcvValids);

		//https://protect.wealthmanagement.natixis.com/phoenix/infosMarcheValeur/detache?page=cours&valeur=LU1829221024,25,814
		LOGGER.guiInfo("Last quotes  for " + stock.getSymbol() +". Number of new quotations:" + queries.size() + ", request: " + url);

		try {
			ArrayList<TableLocker> tablet2lock = new ArrayList<TableLocker>();
			tablet2lock.add(new TableLocker(DataSource.QUOTATIONS.TABLE_NAME,TableLocker.LockMode.NOLOCK));
			DataSource.getInstance().executeInsertOrUpdateQuotations(new ArrayList<Validatable>(queries), tablet2lock);
		} catch (SQLException e) {
			LOGGER.error("Yahoo quotations sql error trying : "+url.getUrl(), e);
			throw e;
		}

	}

	@Override
	public MyUrl resolveUrlFor(Stock stock, Date start, Date end) throws Exception {
		//		Calendar startCal = Calendar.getInstance();
		//		startCal.setTime(start);
		//		Calendar endCal = Calendar.getInstance();
		//		startCal.setTime(end);
		//		return httpSource.getStockQuotationURL(
		//				this.getStockRefName(stock), 
		//				""+startCal.get(Calendar.YEAR), ""+startCal.get(Calendar.MONTH), ""+startCal.get(Calendar.DAY_OF_MONTH), 
		//				""+endCal.get(Calendar.YEAR), ""+endCal.get(Calendar.MONTH), ""+endCal.get(Calendar.DAY_OF_MONTH));
		return this.httpSource.getStockQuotationURL(
				this.getStockRefName(stock), 
				new SimpleDateFormat("yyyy").format(start),new SimpleDateFormat("MM").format(start),new SimpleDateFormat("dd").format(start),
				new SimpleDateFormat("yyyy").format(end), new SimpleDateFormat("MM").format(end), new SimpleDateFormat("dd").format(end)
				);
	}

	@Override
	public List<Validatable> readPage(Stock stock, MyUrl url, Date startDate) throws HttpException {
		LineFormater dsf = new DayQuote1818Formater(url, stock);
		return this.httpSource.readURL(dsf);
	}

}
