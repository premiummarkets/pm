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
package com.finance.pms.datasources.quotation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import com.finance.pms.admin.config.Config;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.admin.config.IndicatorsConfig;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.quotation.GetQuotation.GetQuotationResult;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.LastUpdateStampChecker;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.threads.ConfigThreadLocal;
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
	private Boolean reset;

	private EventSignalConfig config;

	public GetQuotation(Date dateFin, Stock stock, Boolean reset) {
		super();
		config = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);
		
		this.stock = stock;
		this.dateFin = DateFactory.midnithDate(QuotationsFactories.getFactory().getValidQuotationDateBefore(dateFin));
		this.reset = reset;
	
	}

	@Override
	public GetQuotationResult call() {
		
		synchronized (stock) {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());
			Date dateAtZero = DateFactory.dateAtZero();

			GetQuotationResult ret = new GetQuotationResult(stock);
			Date lasteQuoteBeforeUpdate;
			if (reset) {
				lasteQuoteBeforeUpdate = dateAtZero;
				stock.setLastQuote(dateAtZero);
			} else {
				lasteQuoteBeforeUpdate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, true);
			}
			Boolean updateGranted = null;
			Date updateStart = null;

			try {	

				Calendar startDayMidNight = Calendar.getInstance();
				startDayMidNight.setTime(DateFactory.midnithDate(lasteQuoteBeforeUpdate));
				startDayMidNight.add(Calendar.DAY_OF_YEAR, 1);
				updateStart = startDayMidNight.getTime();

				if (dateFin.before(updateStart)) {//Update not needed
					LOGGER.guiInfo("Quotation for "+stock.getFriendlyName()+ " are up to date to the "+new SimpleDateFormat("yyyy/MM/dd").format(dateFin));
				} else {//Check last quote update
					LastUpdateStampChecker lastUpdateChecker  = QuotationsFactories.getFactory().checkLastQuotationUpdateFor(stock);
					updateGranted = lastUpdateChecker.isUpdateGranted();
					if (reset || updateGranted) { //Update granted for today

						LOGGER.guiInfo(	"Updating quotation for "+stock.getFriendlyName()+
								" from the " +new SimpleDateFormat("yyyy/MM/dd").format(updateStart)+ 
								" to the "+new SimpleDateFormat("yyyy/MM/dd").format(dateFin));
						LOGGER.guiInfo("Downloading for "+stock.getFriendlyName()+" from the " + updateStart + " to " + dateFin);

						Providers.getInstance(stock.getSymbolMarketQuotationProvider().getCmdParam()).getQuotes(stock, updateStart, dateFin);
						ret.isSuccessfulUpdate = true;

					} else {//No Update : already done today

						ret.isSuccessfulUpdate = null;
						LOGGER.guiInfo("Request for updating "+stock.getFriendlyName()+" from the " + updateStart + " to " + dateFin + " : nothing to do as last update was on the "+lastUpdateChecker);

					}
				}

			} catch (Exception e) {

				String scrapErrorMess = "Failed to update quotes for :" + stock.toString() +".\n" +
						"\tBecause "+e+".\n" +
						"\tIn update request from "+updateStart+ " to "+ dateFin+ ".";
				LOGGER.warn(scrapErrorMess);
				ret.isSuccessfulUpdate = false;
				ret.failureCause = e;

			} finally {

				this.setChanged();
				this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));

			}

			Date lastQuote = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false);

			if (lastQuote.after(dateAtZero)) {//existing data (could be user)
				if (lasteQuoteBeforeUpdate.equals(dateAtZero) && (ret.isSuccessfulUpdate == null || !ret.isSuccessfulUpdate)) {//but from web was 10700101 and no update was made
					ret.hasPreviousQuotations = false;
				} else {//and update was successful
					ret.hasPreviousQuotations = true;
				}
			} else {
				ret.hasPreviousQuotations = false;
			}

			if (ret.isSuccessfulUpdate != null && ret.isSuccessfulUpdate && (lastQuote.after(lasteQuoteBeforeUpdate) || lastQuote.equals(lasteQuoteBeforeUpdate))) {
				ret.hasNewQuotations = true;
			} else {
				ret.hasNewQuotations = false;
			}

			stock.setLastQuote(lastQuote);
			updateLastQuoteDateForShareInDB(lastQuote);

			LOGGER.guiInfo(
					"Done quotations for "+stock.getFriendlyName()+" from " + updateStart + " to " + dateFin + ", last quotation : " + stock.getLastQuote() + ", " +
							"STATUS : Success " + ret.isSuccessfulUpdate +" (as granted was " + updateGranted +"), New data " + ret.hasNewQuotations+", Has previous data " + ret.hasPreviousQuotations + 
							" (also there may well be user entries, fyi user quotations overshadowing is set to "+stock.isOverrideUserQuotes()+")");

			if (reset) {
				Quotations.removeCachedStockKey(stock);
			} else {
				Quotations.updateCachedStockKey(stock);
			}

			return ret;

		}
	}

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
		Boolean hasNewQuotations;
		Boolean isSuccessfulUpdate;
		Boolean hasPreviousQuotations;
		Exception failureCause;
		
		public GetQuotationResult(Stock stock) {
			this.stock = stock;
			this.isSuccessfulUpdate = null;
			this.hasNewQuotations = null;
			this.hasPreviousQuotations = null;
		}
	}

}
