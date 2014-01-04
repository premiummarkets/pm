/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
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
package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.SharesListId;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.web.formaters.DayQuoteBoursoramaFormater;
import com.finance.pms.datasources.web.formaters.StockComplementBoursoFormater;
import com.finance.pms.datasources.web.formaters.StockListBoursormaFormater;
import com.finance.pms.portfolio.SharesList;

/**
 * The Class ProvidersBoursorama.
 * 
 * @author Guillaume Thoreton
 */
public class ProvidersBoursorama extends Providers implements MarketListProvider {
		
	/** The LOGGER. */
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersBoursorama.class);

	/**
	 * Instantiates a new providers boursorama.
	 * 
	 * @param pathToProps the path to props
	 * 
	 * @author Guillaume Thoreton
	 */
	public ProvidersBoursorama(String pathToProps) {
		super();
		this.httpSource = new HttpSourceBoursorama(pathToProps, this);
	}
	
	
	@Override
	public Set<Indice> getIndices() {
		return new TreeSet<Indice>();
	}
	
	@Override
	public void addIndice(Indice indice) {
		// Nothing
	}

	// TODO g�rer les jours et heures ouvr�es.
	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.Providers#getQuotes(com.finance.pms.datasources.shares.Stock, java.util.Date, java.util.Date)
	 */
	@Override
	public void getQuotes(Stock ticker, Date start, Date end) throws SQLException, HttpException {
		
		DayQuoteBoursoramaFormater dayQuoteBoursoramaFormater = new DayQuoteBoursoramaFormater(
				this.httpSource.getStockQuotationURL(
				ticker.getIsin(), 
				new SimpleDateFormat("yyyy").format(start),new SimpleDateFormat("MM").format(start),new SimpleDateFormat("dd").format(start),
				new SimpleDateFormat("yyyy").format(end), new SimpleDateFormat("MM").format(end), new SimpleDateFormat("dd").format(end)),
				ticker,ticker.getMarketValuation().getCurrency().name());
		this.getHttpSource().getScrapperMetrics().addRecord(dayQuoteBoursoramaFormater, "No quotations from boursorma any more");
		throw new HttpException("Invalid login on bourso!");
	
	}


//	/**
//	 * @param ticker
//	 * @param date
//	 * @param end
//	 * @throws HttpException
//	 * @throws IOException
//	 */
//	private List<Validatable> tickerSanityCheck(Stock ticker, Date twentyDaysAgo, Date end) throws HttpException, IOException {
//		List<Validatable> sanityChecklist = moisParMois(ticker, twentyDaysAgo, end);
//		if (sanityChecklist.size() == 0) {
//			throw new HttpException("Bourso doesn't provide quotations for this : "+ticker+" on the "+end);
//		} else {
//			return sanityChecklist;
//		}
//	}
//
//
//	/**
//	 * @param end
//	 * @return
//	 */
//	private Date startOrTwentyDaysAgo(Date start, Date end) {
//		Calendar twentyDaysAgo = Calendar.getInstance();
//		twentyDaysAgo.setTime(end);
//		twentyDaysAgo.add(Calendar.DAY_OF_YEAR, -20);
//		if (twentyDaysAgo.getTime().after(start)) {
//			return twentyDaysAgo.getTime();
//		} else {
//			return start;
//		}
//		
//	}

//	/**
//	 * Mois par mois.
//	 * 
//	 * @param ticker the ticker
//	 * @param start the start
//	 * @param end the end
//	 * @param httpcx the httpcx
//	 * 
//	 * @return the list< validatable>
//	 * 
//	 * @throws HttpException the http exception
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private List<Validatable> moisParMois(Stock ticker, Date start, Date end) throws HttpException, IOException {
//		
//		List<Validatable> queryList = new ArrayList<Validatable>();
//		Date dateMax;
//		GregorianCalendar oneMonthAfterStart = new GregorianCalendar();
//		oneMonthAfterStart.setTime(start);
//		oneMonthAfterStart.add(Calendar.MONTH, 1);
//		oneMonthAfterStart.add(Calendar.DAY_OF_MONTH, -1);
//		
//		// pb avec les chevauchement de mois entre ann�es
//		int anneeStart = (new Integer(new SimpleDateFormat("yyyy").format(start))).intValue();
//		if (oneMonthAfterStart.get(Calendar.YEAR) > anneeStart) {
//			try {
//				dateMax = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/" + anneeStart);
//			} catch (Exception e) {
//				LOGGER.error("Date format ERROR while formating url :" + e,e);
//				dateMax = oneMonthAfterStart.getTime();
//			}
//		} else {
//			dateMax = oneMonthAfterStart.getTime();
//		}
//		if (dateMax.compareTo(end) < 0) { // on continue
//			queryList.addAll(this.httpSource.readURL(
//					new DayQuoteBoursoramaFormater(
//						this.httpSource.getStockQuotationURL(
//						ticker.getIsin(), 
//						new SimpleDateFormat("yyyy").format(start),new SimpleDateFormat("MM").format(start),new SimpleDateFormat("dd").format(start),
//						new SimpleDateFormat("yyyy").format(dateMax), new SimpleDateFormat("MM").format(dateMax), new SimpleDateFormat("dd").format(dateMax)),
//						ticker,ticker.getMarket().getCurrency().name())));
//			oneMonthAfterStart.setTime(dateMax);
//			oneMonthAfterStart.add(Calendar.DAY_OF_MONTH, 1);
//			// Pb avec les ann�es bisextiles pour le mois de f�vrier
//			if (oneMonthAfterStart.get(Calendar.DAY_OF_MONTH) > 28 && oneMonthAfterStart.get(Calendar.MONTH) == 1) {
//				oneMonthAfterStart.add(Calendar.DAY_OF_MONTH, 1);
//			}
//			dateMax = oneMonthAfterStart.getTime();
//			queryList.addAll(moisParMois(ticker, dateMax, end));
//		} else { // on arrete
//			queryList.addAll(this.httpSource.readURL(
//					new DayQuoteBoursoramaFormater(
//							this.httpSource.getStockQuotationURL(
//							ticker.getIsin(), new SimpleDateFormat("yyyy").format(start),new SimpleDateFormat("MM").format(start),
//							new SimpleDateFormat("dd").format(start), new SimpleDateFormat("yyyy").format(end), new SimpleDateFormat("MM").format(end), new SimpleDateFormat("dd").format(end)),
//							ticker,ticker.getMarket().getCurrency().name())));
//		}
//		return queryList;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.finance.pms.datasources.web.Providers#retreiveStockListFromWeb(com.finance.pms.datasources.web.ProvidersTypes)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public StockList retrieveStockListFromWeb(MarketQuotationProviders marketQuotationsProviders, StockList stockList) {

		LOGGER.info("From Web : ");
		
		//Share list
		SharesList shareList = loadSharesListForThisListProvider();
		Set<Stock> sharesListStocks = new ConcurrentSkipListSet<Stock>(); 

		DateFormat dfy = new SimpleDateFormat("yyyy");
		DateFormat dfm = new SimpleDateFormat("MM");
		DateFormat dfd = new SimpleDateFormat("dd");
		Date today = new Date();
		List<Validatable> listReqDel = new ArrayList<Validatable>();
		List<Validatable> listReqDelS = new ArrayList<Validatable>();
		StockList listNew = new StockList();
		// R�cup�ration de la liste des stocks sur le web (isin)
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(today);
		today = getLastOpenDate(gcal.getTime());
		String url = ((HttpSourceBoursorama) this.httpSource)
				.getCategoryStockListURL(StockCategories.DEFAULT_CATEGORY, dfy.format(today), dfm.format(today),dfd.format(today));
		
		StockListBoursormaFormater lsf = new StockListBoursormaFormater(url, StockCategories.DEFAULT_CATEGORY, marketQuotationsProviders);
		List<? extends Validatable> ltmp = new ArrayList<Validatable>();
		try {
			ltmp = ((HttpSourceBoursorama) this.httpSource).readURL(lsf);
		} catch (HttpException e) {
			LOGGER.error("",e);
		}
		listNew.addAll((List<Stock>) ltmp);
		String urlIndices = ((HttpSourceBoursorama) this.httpSource).getCategoryStockListURL(StockCategories.INDICES_EURONEXT, dfy.format(today), dfm.format(today), dfd.format(today));
		StockListBoursormaFormater lsfIndices = new StockListBoursormaFormater(urlIndices, StockCategories.INDICES_EURONEXT, marketQuotationsProviders);
		List<? extends Validatable> ltmpIndices = new ArrayList<Validatable>();
		try {
			ltmpIndices = ((HttpSourceBoursorama) this.httpSource).readURL(lsfIndices);
		} catch (HttpException e) {
			LOGGER.error("",e);
		}
		listNew.addAll((List<Stock>) ltmpIndices);
		String urlTrackers = ((HttpSourceBoursorama) this.httpSource).getCategoryStockListURL(StockCategories.TRACKERS, dfy.format(today), dfm.format(today),dfd.format(today));
		StockListBoursormaFormater lsfTrackers = new StockListBoursormaFormater(urlTrackers, StockCategories.TRACKERS, marketQuotationsProviders);
		List<? extends Validatable> ltmpTrackers =  new ArrayList<Validatable>();
		try {
			ltmpTrackers = ((HttpSourceBoursorama) this.httpSource).readURL(lsfTrackers);
		} catch (HttpException e) {
			LOGGER.error("",e);
		}
		listNew.addAll((List<Stock>) ltmpTrackers);
		gcal.add(Calendar.DATE, -1);
		LOGGER.guiInfo("Number of stocks retreived from web on the " + new SimpleDateFormat("dd/MM/yyyy").format(today) + " : " + listNew.size());
		
		//share list addition
		sharesListStocks.addAll(listNew);

		// Suppression des tickers non mis � jour depuis 2 mois
		Iterator<Stock> listStockIt = stockList.iterator();
		StockList stockAGarder = new StockList();
		while (listStockIt.hasNext()) {
			Stock stockBase = listStockIt.next();
			if (!listNew.contains(stockBase) && stockBase.toBeRemoved(SharesListId.BOURSORAMA)) {
				LOGGER.info("Ticker " + stockBase.toString() + " is obsolete and will be removed");
				buildLookupDeleteReq(listReqDelS, listReqDel, stockBase);
			} else {
				stockAGarder.add(stockBase);
			}
		}

		// Completion and insertion of new stocks - the return of retreiveStock contains inconsistent stocks
		// stockAGarder.removeAll(retreiveStock(listReqDelS, listReqDel, listNew));
		ArrayList<Stock> tmp = supplementAndValidateStocks(listReqDelS, listReqDel, listNew, stockAGarder, stockList);
		stockList = new StockList();
		stockList.addAll(tmp);
		// Deletion of old stocks
		LOGGER.guiInfo("Number of tickers to be removed : " + listReqDel.size());
		LOGGER.info("Tickers to be removed : " + listReqDel);
		// TODO : Hibernate transaction.
//		try {
			//FIXME : consistency pb when deleting shares cause no data available from the web ....
			//DataSource.getInstance().executeBlock(listReqDel, DataSource.SHARES.getDELETE());
			//FIXME : Performance pb when deleting quotations ....
			//DataSource.getInstance().executeBlock(listReqDelS, DataSource.QUOTATIONS.getDELETE());
//		} catch (SQLException e) {
//			LOGGER.error("ERROR : Deleting ticker for ticker list : lookup " + listReqDel + ", shares " + listReqDelS, e);
//		}
		
		//Share list
		updatingShareListInDB(shareList, sharesListStocks);
		
		return stockList;
	}

	/**
	 * Gets the last open date.
	 * 
	 * @param refDate the ref date
	 * 
	 * @return the last open date
	 */
	private Date getLastOpenDate(Date refDate) {

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(refDate);
		// gcal.add(GregorianCalendar.DATE, -1);
		switch (gcal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			gcal.add(Calendar.DATE, -2);
			break;
		case Calendar.SATURDAY:
			gcal.add(Calendar.DATE, -1);
			break;
		default:
			if (gcal.get(Calendar.HOUR_OF_DAY) <= 20) {
				gcal.add(Calendar.DATE, -1);
			}
		}
		refDate = gcal.getTime();
		return refDate;
	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.Providers#retreiveStockListFromCmdLine(java.util.List)
	 */
	@Override
	public StockList retrieveStockListFromCmdLine(List<String> listStocks, StockList stockList, String quotationsProvider) {
		LOGGER.info("From Command Line : ");
		// init des stocks command
		StockList cmdStockList = new StockList(
				new SymbolMarketQuotationProvider(MarketQuotationProviders.valueOfCmd(quotationsProvider),SymbolNameResolver.UNKNOWNEXTENSIONCLUE), listStocks);
		LOGGER.guiInfo("Number of stocks retreived from commande line : "+cmdStockList.size());
		
		//Merge
		for (Stock stock : cmdStockList) {
			stock.retrieveStock(stockList,this.getSharesListIdEnum().getSharesListCmdParam());
		}
		
		return stockList;
	}


	@Override
	public void retrieveAndCompleteStockInfo(Stock st,StockList stockList) {
		this.supplementAndValidateStock(st,stockList);
	}
	
	/**
	 * Supplement and validate stock.
	 * 
	 * @param s the s
	 * @param stockList the stock list
	 * 
	 * @author Guillaume Thoreton
	 */
	private void supplementAndValidateStock(Stock s,StockList stockList) {
		List<Validatable> listReq = new ArrayList<Validatable>();
		List<Validatable> ltmp  = new ArrayList<Validatable>();
		
		if (!stockList.contains(s)) { // not already in base
			// Fetch info for new stock
			String url;
			try {
				url = this.httpSource.getStockInfoPageURL(s.getIsin());
				StockComplementBoursoFormater dsf = new StockComplementBoursoFormater(url, s);
				ltmp = this.httpSource.readURL(dsf);
				LOGGER.guiInfo("Updating stock list : Just fetched New stock "+ltmp.toString());
			
			} catch (UnsupportedEncodingException e) {
				LOGGER.info("Can't fetch ticker info.",e);
				ltmp.add(s);
			} catch (HttpException e) {
				LOGGER.error("",e);
				ltmp.add(s);
			}
			
			//check for last former quotation
			Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(s);
			s.setLastQuote(formerQuotationDate);
			
			LOGGER.info("New ticker : "+s.toString()+" and will be added with last quote : "+ formerQuotationDate);
			
			listReq.addAll(ltmp);
			stockList.add(s);
			
		} else { // already in base : update name
			stockList.get(stockList.indexOf(s)).setName(s.getName());
		}
		try {
			//DataSource.getInstance().executeBlock(listReq, DataSource.SHARES.getINSERT());
			DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReq);
//		} catch (SQLException e) {
//			LOGGER.warn("Warning, this ticker may already be in database. If true, only quotations will be updated :" + e.getMessage() + " cause : " + e.getCause());
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	// TODO rewrite ... => list from base, from cmd, from file, from web => update
	// and merge all ....
	/**
	 * Supplement and validate stock.
	 * 
	 * @param delS the del s
	 * @param delL the del l
	 * @param newStocks the new stocks
	 * @param stockAGarder the stock a garder
	 * @param actualStockList the actual stock list
	 * 
	 * @return the array list< stock>
	 * 
	 * @author Guillaume Thoreton
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Stock> supplementAndValidateStocks(List<Validatable> delS, List<Validatable> delL, 
			StockList newStocks, StockList stockAGarder,StockList actualStockList) {
		
		List<Validatable> listReq = new ArrayList<Validatable>();
		List<Stock> toAdd = new ArrayList<Stock>();
		List<Stock> toRemove = new ArrayList<Stock>();
		Iterator<Stock> newStocksIt = newStocks.iterator();
		
		while (newStocksIt.hasNext()) {
			Stock initialStock = newStocksIt.next();
			if (!actualStockList.contains(initialStock)) { // not already in base
				
				//check for last former quotation
				Date formerQuotationDate = DataSource.getInstance().getLastQuotationDateFromQuotations(initialStock);
				initialStock.setLastQuote(formerQuotationDate);
				LOGGER.info("New ticker in check : "+initialStock.toString()+" and will be added with last quote : "+ formerQuotationDate);
				
				// Fetch info for new stock
				List<Validatable> ltmp = new ArrayList<Validatable>();
				try {
					String url = this.httpSource.getStockInfoPageURL(initialStock.getIsin());
					StockComplementBoursoFormater dsf = new StockComplementBoursoFormater(url, initialStock);
					ltmp = this.httpSource.readURL(dsf);
					LOGGER.info("@d. Just fetched New ticker info : "+ initialStock +"; changes  : "+ ltmp);
				} catch (UnsupportedEncodingException e) {
					LOGGER.info("@d. No valide url. ",e);
					ltmp.add(initialStock);
				} catch (HttpException e) {
					LOGGER.info("@d. No valide url. ",e);
					ltmp.add(initialStock);
				}
				listReq.addAll(ltmp);
				
				// Add new stock
				if (!toAdd.containsAll(ltmp)) toAdd.addAll((List)ltmp);
				// Check inconsistencies and remove old stocks
				if (null != delS && null != delL && null != stockAGarder) {// only if
					// from web
					List<? extends Validatable> listNewStocks = listReq;
					for (Stock sold : actualStockList) {
						for (Stock snew : (List<Stock>) listNewStocks) {
							if ((snew.getSymbol().equals(sold.getSymbol()) && !snew.getIsin().equals(sold.getIsin()))
									|| (!snew.getSymbol().equals(sold.getSymbol()) && snew.getIsin().equals(sold.getIsin()))) {
								if (!toRemove.contains(sold)) {
									LOGGER.debug("Share inconsistency.\n	New : " + snew.toString());
									LOGGER.debug("	Old : " + sold.toString());
									buildLookupDeleteReq(delS, delL, sold);
									toRemove.add(sold);
								}
							}
						}
					}
				}
			} else { // update name
				actualStockList.get(actualStockList.indexOf(initialStock)).setName(initialStock.getName());
			}
		}
		// Ajout des nouvelles stock en base
		LOGGER.guiInfo("Updating stock list : Number of new stock : " + listReq.size());
		if (LOGGER.isInfoEnabled()) {
			int i = 0; StringBuffer sb = new StringBuffer();
			LOGGER.info("New tickers :");
			for (Validatable v : listReq) {
				sb.append(v.toString());
				if ((i % 20) == 0) {
					LOGGER.info(sb.toString());
					sb = new StringBuffer();
				}
				i++;
			}
			LOGGER.info(sb.toString());
		}
		try {
			//DataSource.getInstance().executeBlock(listReq, DataSource.SHARES.getINSERT());
			DataSource.getInstance().getShareDAO().saveOrUpdateStocks(listReq);
//		} catch (SQLException e) {
//			LOGGER.warn("@d. Warning, this ticker is already in database. Only quotations will be updated :"
//					+ e.getMessage() + " cause : " + e.getCause());
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		ArrayList<Stock> retour = new ArrayList<Stock>();
		if (stockAGarder != null) {
			retour.addAll(stockAGarder);
			retour.addAll(toAdd);
			retour.removeAll(toRemove);
		}
		return retour;
	}

//	/**
//	 * Builds the lookup update req.
//	 * 
//	 * @param updateS the update s
//	 * @param updateL the update l
//	 * @param s the s
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private void buildLookupUpdateReq(List<Validatable> updateS, List<Validatable> updateL, Stock s) {
//		final Query updL = new Query();
//		updL.addValue(s.getSymbol());
//		updL.addValue(s.getName());
//		updL.addValue(s.getIsin());
//		updateL.add(new Stock(s) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -274978589027365548L;
//
//			@Override
//			public Query toDataBase() {
//				return updL;
//			}
//		});
//		final Query updS = new Query();
//		updS.addValue(s.getSymbol());
//		updS.addValue(s.getName());
//		updS.addValue(s.getIsin());
//		updateS.add(new Stock(s) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -2713541263407488365L;
//
//			@Override
//			public Query toDataBase() {
//				return updS;
//			}
//		});
//	}

//	/**
//	 * Builds the lookup update name req.
//	 * 
//	 * @param updateSName the update s name
//	 * @param updateL the update l
//	 * @param s the s
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private void buildLookupUpdateNameReq(List<Validatable> updateSName, List<Validatable> updateL, Stock s) {
//		final Query updL = new Query();
//		updL.addValue(s.getSymbol());
//		updL.addValue(s.getName());
//		updL.addValue(s.getIsin());
//		updateL.add(new Stock(s) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 3683109150647863125L;
//
//			@Override
//			public Query toDataBase() {
//				return updL;
//			}
//		});
//		final Query updS = new Query();
//		updS.addValue(s.getName());
//		updS.addValue(s.getSymbol());
//		updS.addValue(s.getIsin());
//		updateSName.add(new Stock(s) {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 5115144737848860513L;
//
//			@Override
//			public Query toDataBase() {
//				return updS;
//			}
//		});
//	}

	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.web.Providers#getStockRefName(com.finance.pms.datasources.shares.Stock)
	 */
	@Override
	public String getStockRefName(Stock ticker) {
		return (ticker.getIsin() != null && !ticker.getIsin().equals(Stock.MISSINGCODE)) ? ticker.getIsin() : ticker.getSymbol();
	}
	
	@Override
	public void retrieveScreeningInfo(Collection<Stock> shareList) {
		throw new UnsupportedOperationException();	
	}


	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		// TODO Auto-generated method stub
		
	}
}
