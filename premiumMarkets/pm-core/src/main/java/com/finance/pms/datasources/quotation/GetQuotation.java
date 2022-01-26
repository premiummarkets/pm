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
import java.util.Arrays;
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
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.Providers;
import com.finance.pms.datasources.web.currency.QuotationFixer;
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
public class GetQuotation extends Observable implements Callable<GetQuotationResult> {

	protected static MyLogger LOGGER = MyLogger.getLogger(GetQuotation.class);

	private Date dateFin;
	private Stock stock;
	private Boolean forceReset;
	private Boolean forceUpdate;

	private EventSignalConfig config;

	public GetQuotation(Date dateFin, Stock stock, Boolean forceReset, Boolean forceUpdate) {
		config = (EventSignalConfig) ConfigThreadLocal.get(Config.EVENT_SIGNAL_NAME);

		this.stock = stock;
		this.dateFin = DateFactory.midnithDate(QuotationsFactories.getFactory().getValidQuotationDateBefore(dateFin));
		this.forceReset = forceReset;
		this.forceUpdate = forceUpdate;

	}

	@Override
	public GetQuotationResult call() {

		synchronized (stock) {

			ConfigThreadLocal.set(Config.EVENT_SIGNAL_NAME, config);
			ConfigThreadLocal.set(Config.INDICATOR_PARAMS_NAME, new IndicatorsConfig());
			Date dateAtZero = DateFactory.dateAtZero();

			GetQuotationResult ret = new GetQuotationResult(stock);
			Date lastQuoteBeforeUpdate;
			if (forceReset) {
				lastQuoteBeforeUpdate = dateAtZero;
				stock.setLastQuote(dateAtZero);
			} else {
				lastQuoteBeforeUpdate = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, true);
			}
			Boolean updateGranted = null;
			Date updateStart = null;

			LastUpdateStampChecker lastUpdateChecker = QuotationsFactories.getFactory().checkLastQuotationUpdateFor();
			
			synchronized (lastUpdateChecker) {
				updateGranted = lastUpdateChecker.isUpdateGranted(stock.getSymbol(), stock.getLastQuote());
			}
			
			try {

				Calendar startDayMidNight = Calendar.getInstance();
				startDayMidNight.setTime(DateFactory.midnithDate(lastQuoteBeforeUpdate));
				startDayMidNight.add(Calendar.DAY_OF_YEAR, 1);
				updateStart = startDayMidNight.getTime();

				if (forceReset || forceUpdate || updateGranted) { //Update granted for today

					LOGGER.guiInfo(	"Updating quotation for " + stock.getFriendlyName() +
							" from " + new SimpleDateFormat("yyyy/MM/dd").format(updateStart) + 
							" to " + new SimpleDateFormat("yyyy/MM/dd").format(dateFin));

					Providers.getInstance(stock.getSymbolMarketQuotationProvider().getCmdParam()).getQuotes(stock, updateStart, dateFin);
					ret.isSuccessfulUpdate = true;
					
					LOGGER.guiInfo(
							"Done quotations for " + stock.getFriendlyName() +
							" from " + new SimpleDateFormat("yyyy/MM/dd").format(updateStart) + 
							" to " + new SimpleDateFormat("yyyy/MM/dd").format(dateFin) +
							" STATUS : Success " + ret.isSuccessfulUpdate);

				} else {//No Update : already done today

					ret.isSuccessfulUpdate = null;
					LOGGER.info("Ungranted quotation update for " + stock.getFriendlyName()+" from the " + updateStart + " to " + dateFin );

				}

			} catch (Exception e) {

				String scrapErrorMess = "Failed to update quotes for :" + stock.toString() + ".\n" +
						"\tBecause " + e + ".\n" +
						"\tIn update request from " + updateStart + " to " + dateFin + ".";
				LOGGER.warn(scrapErrorMess);
				ret.isSuccessfulUpdate = false;
				ret.failureCause = e;

			} finally {

				this.setChanged();
				this.notifyObservers(new ObserverMsg(stock, ObsKey.NONE));

			}

			Date lastQuoteExUsers = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, true);

			if (lastQuoteExUsers.after(dateAtZero)) {//Existing data (not including user's)
				ret.hasPreviousQuotations = true;
			} else {//and update was successful
				ret.hasPreviousQuotations = false;
			}

			if (ret.isSuccessfulUpdate != null && ret.isSuccessfulUpdate && (lastQuoteExUsers.after(lastQuoteBeforeUpdate))) {
				ret.hasNewQuotations = true;
			} else {
				ret.hasNewQuotations = false;
			}

			Date lastQuote = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, false);
			stock.setLastQuote(lastQuote);
			updateLastQuoteDateForShareInDB(lastQuote);

			LOGGER.info(
					"Done quotations for " + stock.getFriendlyName() + " from " + updateStart + " to " + dateFin + ", last quotation : " + stock.getLastQuote() + ", "
							+ "STATUS : Success " + ret.isSuccessfulUpdate + " (granted: " + updateGranted + "), "
							+ "New data " + ret.hasNewQuotations + ", Has previous data " + ret.hasPreviousQuotations + 
							" (ex. User Entries with overshadowing " + stock.isOverrideUserQuotes() + ")");

			if (ret.hasNewQuotations) {
				lastUpdateChecker.resetFatalThreshold(stock.getSymbol());
			}
			if (forceReset) {
				Quotations.removeCachedStockKey(stock);
			} else {
				Quotations.updateCachedStockKey(stock);
			}

			if (//TODO apply to any with currency factor <> 1
					Currency.GBP.equals(stock.getMarketValuation().getCurrency()) && 
					stock.getMarketValuation().getCurrencyFactor().doubleValue() > 1 &&
					(ret.isSuccessfulUpdate != null && ret.isSuccessfulUpdate) && ret.hasNewQuotations
			) {
				QuotationFixer quotationFixer = new QuotationFixer();
				quotationFixer.fixPennyPound(Arrays.asList(stock));
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
