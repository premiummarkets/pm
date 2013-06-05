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
package com.finance.pms.datasources.quotation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.quotation.GetQuotation.GetQuotationResult;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.quotations.LastUpdateStampChecker;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ObserverMsg;
import com.finance.pms.threads.ObserverMsg.ObsKey;


/**
 * The Class GetQuotation.
 * 
 * @author Guillaume Thoreton
 */
public class GetQuotation  extends Observable implements Callable<GetQuotationResult> {

	protected static MyLogger LOGGER = MyLogger.getLogger(GetQuotation.class);

	private Date dateFin;
	private Stock stock;

	/**
	 * Instantiates a new gets the quotation.
	 * 
	 * @param dateFin the date fin
	 * @param stock the stock
	 * @param threadSemaphore the thread semaphore
	 * 
	 * @author Guillaume Thoreton
	 */
	public GetQuotation(Date dateFin, Stock stock) {
		super();
		this.stock = stock;
		
		//Fix date time fields
		Calendar endDayMidNight = Calendar.getInstance();
		endDayMidNight.setTime(QuotationsFactories.getFactory().getValidQuotationDateBefore(dateFin));
		endDayMidNight.set(Calendar.HOUR_OF_DAY, 0);
		endDayMidNight.set(Calendar.MINUTE, 0);
		endDayMidNight.set(Calendar.SECOND, 0);
		endDayMidNight.set(Calendar.MILLISECOND, 0);
		this.dateFin = endDayMidNight.getTime();
		
		
	
	}

	@Override
	public GetQuotationResult call() {
		
		Date lastQuoteDate = null;
		GetQuotationResult ret = new GetQuotationResult(stock);
		
		try {
			
			
			lastQuoteDate = DataSource.getInstance().getLastQuotationDateFromShares(stock);

			Calendar startDayMidNight = Calendar.getInstance();
			startDayMidNight.setTime(lastQuoteDate);
			startDayMidNight.set(Calendar.HOUR_OF_DAY, 0);
			startDayMidNight.set(Calendar.MINUTE, 0);
			startDayMidNight.set(Calendar.SECOND, 0);
			startDayMidNight.set(Calendar.MILLISECOND, 0);

			startDayMidNight.add(Calendar.DAY_OF_YEAR, 1);
			lastQuoteDate = startDayMidNight.getTime();

			if (dateFin.before(lastQuoteDate)) {
				LOGGER.guiInfo("Quotation for "+stock.getSymbol()+ " are up to date to the "+new SimpleDateFormat("yyyy/MM/dd").format(dateFin));
				ret.hasQuotations = true;
				return ret;
			}

			
			//Check last quote update
			LastUpdateStampChecker lastUpdateChecker  = QuotationsFactories.getFactory().checkLastQuotationUpdateFor(stock);
			if (lastUpdateChecker.isUpdateGranted()) {

				//Update
				LOGGER.guiInfo(	"Updating quotation for "+stock.getFriendlyName()+
								" from the " +new SimpleDateFormat("yyyy/MM/dd").format(lastQuoteDate)+ 
								" to the "+new SimpleDateFormat("yyyy/MM/dd").format(dateFin));
	
				LOGGER.guiInfo("Downloading for "+stock.getFriendlyName()+" from the " + lastQuoteDate + " to " + dateFin);
				Providers.getInstance(stock.getSymbolMarketQuotationProvider().getCmdParam()).getQuotes(stock, lastQuoteDate, dateFin);
				
			} else {
				
				//No Update
				LOGGER.guiInfo("Request for updating "+stock.getFriendlyName()+" from the " + lastQuoteDate + " to " + dateFin + " : nothing to do as last update was on the "+lastUpdateChecker);
				
			}
			
		} catch (Exception e) {
			
			String scrapErrorMess = "Failed to update quotes for :" + stock.toString() +".\n" +
					" 		Because "+e+".\n" +
					" 		In update request from "+lastQuoteDate+ " to "+ dateFin+ ".";
			LOGGER.warn(scrapErrorMess);
			ret.isSuccessfulUpdate = false;
			ret.failureCause = e;
			
		} finally {
			
			this.setChanged();
			this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));
			
		}
		
		Date lastQuote = DataSource.getInstance().getLastQuotationDateFromQuotations(stock);
		stock.setLastQuote(lastQuote);
		updateLastQuoteDateForShareInDB(lastQuote);

		LOGGER.guiInfo("Downloaded for "+stock.getSymbol()+" / "+stock.getIsin()+" from the " + lastQuoteDate + " to " + dateFin + ", last quotation : "+stock.getLastQuote());

		Quotations.updateCachedStockKey(stock);
		ret.hasQuotations = true;
		
		return ret;
	}

	/**
	 * @param lastquote
	 * @throws SQLException
	 */
	private void updateLastQuoteDateForShareInDB(Date lastQuote) {
		
		if (null == lastQuote) {
			LOGGER.debug("No last date returned from quotation fetch for : "+stock+". Assuming that it is up to date.");
			return;
		}
		
		//Mise a jour de la date de derniere quote and name
		List<Validatable> updateLastQuotesQueries = new ArrayList<Validatable>();
		final Query uQ = new Query();
		final Stock s = stock;

		uQ.addValue(lastQuote);
		uQ.addValue(stock.getName());
		uQ.addValue(stock.getSymbol());
		uQ.addValue(stock.getIsin());
		updateLastQuotesQueries.add(new Stock(s) {

			private static final long serialVersionUID = 2652971454727241110L;

			@Override
			public Query toDataBase() {
				return uQ;
			}
		});
		
		try {
			DataSource.getInstance().executeBlock(updateLastQuotesQueries, DataSource.SHARES.getUPDATELASTQUOTEANDNAME());
		} catch (SQLException e) {
			LOGGER.error(e,e);
		}
		
	}
	
	public class GetQuotationResult {

		Stock stock;
		Boolean hasQuotations;
		Boolean isSuccessfulUpdate;
		Exception failureCause;
		
		public GetQuotationResult(Stock stock) {
			this.stock = stock;
			this.hasQuotations = false;
			this.isSuccessfulUpdate = true;
		}
	}

}
