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
package com.finance.pms.admin;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.DataSource.QUOTATIONS;
import com.finance.pms.datasources.db.DataSource.SHARES;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.StockToDB;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.portfolio.AbstractSharesList;
import com.finance.pms.portfolio.Portfolio;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.portfolio.PortfolioMgr;
import com.finance.pms.portfolio.PortfolioShare;
import com.finance.pms.portfolio.SharesList;


/**
 * The Class AdminDB.
 * 
 * @author Guillaume Thoreton
 */
@SuppressWarnings("all")
public class AdminDB {

	/** The Constant prefdbpath. */
	public static final String prefdbpath = "com.finance.pms";

	/** The Constant prefs. */
	public static final Preferences prefs = Preferences.userRoot().node(prefdbpath);
	
	public static PortfolioDAO portfolioDAO;

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @author Guillaume Thoreton
	 */
	public static void main(String[] args) {

		String dbProps = args[0];
		String command = args[1];
		String justcheck = args[2];
		
		//new DataSource(dbProps);
		SpringContext ctx = new SpringContext(dbProps);
		//ctx.setDataSource(dbProps);
		ctx.loadBeans("/connexions.xml", "/swtclients.xml","/talibanalysisservices.xml");
		ctx.refresh();
		
		portfolioDAO =  (PortfolioDAO)ctx.getBean("portfolioDAO");

		StockList shares = new StockList();
		shares.addAll(DataSource.getInstance().loadAllStocks());
		
		Boolean justcheckB = Boolean.valueOf(justcheck);
		if (command.equals("-fixLastupdatedate")) fixLastUpdateDate(shares,justcheckB);
		if (command.equals("-cleanobsoletequotes")) cleanObsoleteQuotes(shares,justcheckB);
		//if (command.equals("-addsuffixe")) addSuffixe(shares,justcheckB);
		if (command.equals("-addExtension")) addExtension(shares,justcheckB);
		if (command.equals("-fixPortfolioSymbols")) fixPortfolioSymbols(justcheckB);
		//FIXME if (command.equals("-updateNAN")) updateNANSymbol(DataSource.getInstance().loadStocks(StockCategories.SICAV),justcheckB);
		if (command.equals("-insertPortfolio")) {
			if (args.length < 3) {
				System.out.println("Params : <path to share list file>");
				return;
			}
			insertPortfolio(args[2]);
		}
		if (command.equals("-addPortfolioIsinCol")) addPortfolioIsinCol(justcheckB);
		if (command.equals("-addPortfolioNames")) addPortfolioNames(justcheckB);
		//FIXME
		//if (command.equals("-addPortfolioAmountIn")) addPortfolioAmountIn(justcheckB);
		if (command.equals("-addBuyPriceStopLossAlert")) addBuyPriceStopLoss(justcheckB);
		
		if (command.equals("-createSharelist")) createShareList(justcheckB);
		
		if (command.equals("-setBuyPrice")) setBuyPrice(justcheckB);
		
		//TODO check symbol/isin inconsistencies : <!> some stocks have the same symbol but in different countries (ex FR, UK ...)
		//select * from lookup a, lookup b 
		//where ((a.symbol = b.symbol and a.isin <> b.isin) or (a.symbol <> b.symbol and a.isin = b.isin)) 
		//and (a.removable is true or b.removable is true)
		
		ctx.close();
	}

	private static void setBuyPrice(Boolean justcheckB) {
		List<Portfolio> portfolios = PortfolioMgr.getInstance().getVisiblePortfolios();
		for (AbstractSharesList portfolio : portfolios) {
			Map<Stock, PortfolioShare> listShares = portfolio.getListShares();
			for (PortfolioShare portfolioShare : listShares.values()) {
				
				if (portfolioShare.getPriceAvgBuy(null, null).intValue() == 0) {//FIXME
					BigDecimal quantity = portfolioShare.getQuantity(null, null);
					BigDecimal cashin = portfolioShare.getCashin(null, null, null);//FIXME
					if (quantity.compareTo(BigDecimal.ZERO) != 0 && cashin.compareTo(BigDecimal.ZERO) != 0) {
					} else {
						System.out.println("Can't update portfolioShare :"+
								portfolioShare.getName()+"cause : q="+quantity+" and c="+ cashin);
					}
				}
				
			}
		}
		
		PortfolioMgr.getInstance().hibStorePortfolio();
		
	}

	private static void createShareList(Boolean justcheckB) {
		String shareListName = "EURONEXT";

		//Share list
		SharesList shareList = portfolioDAO.loadShareList(shareListName);

		Query query = new Query("select * from shares where MARKETLISTPROVIDER='"+shareListName+"'") {

			@Override
			public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
				while (rs.next()) {
					try {
						retour.add(new Stock(rs.getString(
								SHARES.ISIN_FIELD).trim(),rs.getString(SHARES.SYMBOL_FIELD).trim(), rs.getString(SHARES.NAME_FIELD).trim(), 
								rs.getBoolean(SHARES.REMOVABLE), StockCategories.valueOf(rs.getString(SHARES.CATEGORY).trim()), 
								rs.getDate(SHARES.LASTQUOTE), 
								new SymbolMarketQuotationProvider(rs.getString(SHARES.QUOTATIONPROVIDER).trim(),rs.getString(SHARES.SYMBOL_FIELD).trim()),new MarketValuation(Market.valueOf(rs.getString(SHARES.MARKET).trim()), 
								rs.getBigDecimal(SHARES.CURRENCYFACTOR), Currency.valueOf(rs.getString(SHARES.CURRENCY).trim())),
								rs.getString(SHARES.SECTOR_HINT), 
								TradingMode.valueOf(rs.getString(SHARES.TRADING_MODE).trim()), 
								rs.getLong(SHARES.CAPITALISATION)));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};

		List<Stock> stocks = DataSource.getInstance().exectuteSelect(Stock.class,query);

		shareList.addShares(stocks);
		portfolioDAO.saveOrUpdatePortfolio(shareList);
	} 

	/**
	 * Fix last update date.
	 * 
	 * @param shares the shares
	 * @param justCheck the just check
	 * 
	 * @author Guillaume Thoreton
	 */
	private static void fixLastUpdateDate(StockList shares, final Boolean justCheck) {
		List<Thread> threads = new ArrayList<Thread>();
		for (Stock s : shares) {
			final Stock stock = s;
			Thread t = new Thread(new Runnable() {
				public void run() {
					
						Date lastQuote = DataSource.getInstance().getLastQuotationDateFromQuotations(stock, null);//FIXME

						//Check 
						Date lastRegistered = DataSource.getInstance().getLastQuotationDateFromShares(stock);
						if (lastRegistered.compareTo(lastQuote) != 0) {
							System.out.println("Stock :" + stock.getIsin() + " is inconsistent : shares is  " 
									+ lastRegistered + " and quotation is  " + lastQuote);
						}


						//update
						if (!justCheck) {
							try {
								List<Validatable> lstUpdate = new ArrayList<Validatable>();
								final Query uQ = new Query(DataSource.SHARES.getUPDATELASTQUOTE());
								uQ.addValue(lastQuote);
								uQ.addValue(stock.getSymbol());
								uQ.addValue(stock.getIsin());
								lstUpdate.add(new Stock(stock) {
									@Override
									public Query toDataBase() {
										return uQ;
									}
								});
								DataSource.getInstance().executeBlock(lstUpdate, DataSource.SHARES.getUPDATELASTQUOTE());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			});
			//if (stock.getIsin().equals("IT0001976429")) t.start();
			threads.add(t);
			t.start();
		}
		
		for (Thread thread : threads) {
			while (thread.isAlive()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Clean obsolete quotes.
	 * 
	 * @param shares the shares
	 * @param justCheck the just check
	 * 
	 * @author Guillaume Thoreton
	 */
	private static void cleanObsoleteQuotes(StockList shares, Boolean justCheck) {
		
		StockList orphanShares = DataSource.getInstance().getOrphanShares();
		List<Validatable> Ql = new ArrayList<Validatable>();
		Iterator<Stock> orphanSharesIt = orphanShares.iterator();
		while (orphanSharesIt.hasNext()) {
			Stock st = orphanSharesIt.next();
			if (!shares.contains(st)) {
				final Query q = new Query();
				q.addValue(st.getSymbol()+"%");
				q.addValue(st.getIsin());
				Ql.add(new Stock(st) {
					@Override
					public Query toDataBase() {
						return q;
					}
				});
			}
		}
		System.out.println("Number tickers that while be cleaned : " + Ql.size());
		System.out.println("Tickers that will be cleaned : " + Ql);
		
		if (!justCheck)
			try {
				//TODO Proper delete??
				String delete = "DELETE FROM quotations where symbol like ? AND isin = ?"; 
				DataSource.getInstance().executeBlock(Ql, delete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	/**
	 * Adds the suffixe.
	 * 
	 * @param shares the shares
	 * @param justCheck the just check
	 * 
	 * @author Guillaume Thoreton
	 */
//	private static void addSuffixe(StockList shares, Boolean justCheck) {
//		
//		List<Validatable> lstUpdate = new ArrayList<Validatable>();
//		List<Validatable> lstUpdateS = new ArrayList<Validatable>();
//		
//		for (Stock s: shares) {
//			String symbol = s.getSymbol();
//			try {
//				s.setSymbol(symbol);
//			} catch (InvalidAlgorithmParameterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			{
//				final Query uQ = new Query(DataSource.SHARES.getUPDATEREFERENCE());		
//					uQ.addValue(s.getSymbol());
//					uQ.addValue(s.getName());
//					uQ.addValue(s.getIsin());
//					lstUpdate.add(new Stock(s) {
//						@Override
//						public Query toDataBase() {
//							return uQ;
//						}
//					});
//			}
//			
//			{
//				final Query uQ2 = new Query(DataSource.QUOTATIONS.getUPDATEREFERENCE());
//				uQ2.addValue(s.getSymbol());
//				//uQ2.addValue(s.getName());
//				uQ2.addValue(s.getIsin());
//				lstUpdateS.add(new Stock(s) {
//					@Override
//					public Query toDataBase() {
//						return uQ2;
//					}
//				});
//			}
//			
//		}
//		
//		System.out.println("Number tickers that while be updated : " + lstUpdate.size());
//		System.out.println("Tickers that while be updated : " + lstUpdate);
//		
//		System.out.println("Number shares that while be updated : " + lstUpdateS.size());
//		System.out.println("shares that while be updated : " + lstUpdateS);
//		
//		try {
//			if (!justCheck)
//				DataSource.getInstance().executeBlock(lstUpdate, DataSource.SHARES.getUPDATEREFERENCE());
//
//			if (!justCheck)
//				DataSource.getInstance().executeBlock(lstUpdateS, DataSource.QUOTATIONS.getUPDATEREFERENCE());
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Update nan symbol.
	 * 
	 * @param shares the shares
	 * @param justCheck the just check
	 * 
	 * @author Guillaume Thoreton
	 */
//	private static void updateNANSymbol(StockList shares, Boolean justCheck) {
//		
//		List<Validatable> lstUpdate = new ArrayList<Validatable>();
//		List<Validatable> lstUpdateS = new ArrayList<Validatable>();
//		
//		for (Stock s: shares) {
//			String isin = s.getIsin();
//			String isinNumber = isin.substring(2);
//			
//			try {
//				s.setSymbol(isinNumber);
//			} catch (InvalidAlgorithmParameterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			{
//				final Query uQ = new Query(DataSource.SHARES.getUPDATEREFERENCE());		
//					uQ.addValue(s.getSymbol());
//					uQ.addValue(s.getName());
//					uQ.addValue(s.getIsin());
//					lstUpdate.add(new Stock(s) {
//						@Override
//						public Query toDataBase() {
//							return uQ;
//						}
//					});
//			}
//			
//			{
//				final Query uQ2 = new Query(DataSource.QUOTATIONS.getUPDATEREFERENCE());
//				uQ2.addValue(s.getSymbol());
//				//uQ2.addValue(s.getName());
//				uQ2.addValue(s.getIsin());
//				lstUpdateS.add(new Stock(s) {
//					@Override
//					public Query toDataBase() {
//						return uQ2;
//					}
//				});
//			}
//			
//		}
//		
//		System.out.println("Number tickers that while be updated : " + lstUpdate.size());
//		System.out.println("Tickers that while be updated : " + lstUpdate);
//		
//		System.out.println("Number shares that while be updated : " + lstUpdateS.size());
//		System.out.println("shares that while be updated : " + lstUpdateS);
//		
//		try {
//			if (!justCheck)
//				DataSource.getInstance().executeBlock(lstUpdate, DataSource.SHARES.getUPDATEREFERENCE());
//
//			if (!justCheck)
//				DataSource.getInstance().executeBlock(lstUpdateS, DataSource.QUOTATIONS.getUPDATEREFERENCE());
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Insert portfolio.
	 * 
	 * @param pathToPortfolio the path to portfolio
	 * 
	 * @author Guillaume Thoreton
	 */
	//FIXME
	private static void insertPortfolio(String pathToPortfolio) {
		
		//String sqlReq = "INSERT INTO PORTFOLIO (SYMBOL,QUANTITY,CASHIN,CASHOUT,NAME,MONITOR) values (?,?,?,?,?,?)";
		
	
//		try {
//			List<Portfolio> portfolios = PortfolioMgr.getInstance().getPortfolios();
//			
//			File sharesToaddF = new File(pathToPortfolio);
//			BufferedReader fis = new BufferedReader(new InputStreamReader(new FileInputStream(sharesToaddF)));
//			String line;
//			while ((line = fis.readLine()) != null) {
//				if (!line.equals("")) {
//					StringTokenizer st = new StringTokenizer(line, ",");
//					String portfolioName = st.nextToken();
//					String isin = st.nextToken();
//					String symbol = st.nextToken();
//					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = df.parse(st.nextToken());
//					Float quantity = new Float(st.nextToken());
//					String price = st.nextToken();
//					Float cashout = 0F;
//					//String monitor = st.nextToken();
//					Stock s = new Stock(isin, symbol, StockCategories.DEFAULT_CATEGORY,
//							new SymbolMarketQuotationProvider(
//							MarketQuotationProviders.BOURSORAMA, SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
//							Market.EURONEXT);
//					s = DataSource.getInstance().loadStock(s);
//					String name = s.getName();
//					Float cashin = (new Float(new Float(price) * (new Float(quantity))));
//					try {
//						PortfolioShare ps;
//						//ps = new PortfolioShare(symbol, date,quantity, cashin, cashout, lv, monitor);
//						ps = new PortfolioShare(s, quantity, date, cashin, cashout, MonitorLevel.ANY,new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN));
//						///alerts
//						Float current = PortfolioMgr.getInstance().loadLastDayClosed(s.getSymbol(),s.getIsin());
//						ps.addBuyAlerts();
//						Portfolio p = new UserPortfolio(portfolioName);
//						int pIndex;
//						if ((pIndex = portfolios.indexOf(p)) != -1) {
//							p = portfolios.get(pIndex);
//						} else {
//							portfolios.add(p);
//						}
//						p.addNewShare(ps);
//					} catch (RuntimeException e) {
//						System.out.println("No share in db " + s.getIsin());
//						e.printStackTrace();
//					}
//				}
//			}
//			PortfolioMgr.getInstance().setPortfolios(portfolios);
//			
//			//PortfolioMgr.getInstance().storePortfolios();
//			PortfolioMgr.getInstance().hibStorePortfolio();
//			
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			System.out.println("Path : "+pathToPortfolio);
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchStockException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	private static void addExtension(StockList shares, Boolean justCheck) {
		
		List<Validatable> lstUpdate = new ArrayList<Validatable>();
		List<Validatable> lstUpdateS = new ArrayList<Validatable>();
		List<Stock> newshares =  new ArrayList<Stock>();
		
		String updateS = "UPDATE " + SHARES.TABLE_NAME + 
				" set " + SHARES.SYMBOL_FIELD + " = ? " +
				" where " + SHARES.ISIN_FIELD + " = ? " +
				" and ( "+ SHARES.SYMBOL_FIELD + " = ?  " +
				" or "+ SHARES.SYMBOL_FIELD + " like ?  ) ";
		
		String updateQ = "UPDATE " + QUOTATIONS.TABLE_NAME + 
				" set " + QUOTATIONS.SYMBOL_FIELD + " = ? " +
				" where " + QUOTATIONS.ISIN_FIELD + " = ? " +
				" and ( "+ SHARES.SYMBOL_FIELD + " = ?  " +
				" or "+ SHARES.SYMBOL_FIELD + " like ?  ) ";
		
		for (Stock s: shares) {
			String symbol = s.getSymbol();
			SymbolMarketQuotationProvider symbolMarketQuotationProvider = s.getSymbolMarketQuotationProvider();
			
			Stock ns = new Stock(s);
			try {
				MarketQuotationProviders marketQuotationProvidersList = symbolMarketQuotationProvider.getMarketQuotationProvider();
				String extensionClue = selectMarket(symbol,ns.getIsin());
				SymbolMarketQuotationProvider newSymbolMarketQuotationProvider = new SymbolMarketQuotationProvider(marketQuotationProvidersList, extensionClue);
				ns.setSymbolMarketQuotationProvider(newSymbolMarketQuotationProvider);
				ns.setSymbol(symbol);
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			}
			
			String symbollike = (symbol.contains("."))?symbol.substring(0, symbol.length() - 3)+".%":symbol+".%";
			
			{
				
				final Query uQ = new Query(updateS);		
					uQ.addValue(ns.getSymbol());
					uQ.addValue(s.getIsin());
					uQ.addValue(s.getSymbol());
					uQ.addValue(symbollike);
					lstUpdate.add(new Stock(ns) {
						@Override
						public Query toDataBase() {
							return uQ;
						}
					});
			}

			{
				final Query uQ2 = new Query(updateQ);
				uQ2.addValue(ns.getSymbol());
				uQ2.addValue(s.getIsin());
				uQ2.addValue(s.getSymbol());
				uQ2.addValue(symbollike);
				lstUpdateS.add(new Stock(ns) {
					@Override
					public Query toDataBase() {
						return uQ2;
					}
				});
			}
			
			newshares.add(ns);
			
		}
		
		for (Stock s: newshares) {
			System.out.println(s);
			List<Stock> ls = new ArrayList<Stock>();
			for (Stock s1: shares) {
				if (s1.getSymbol().equals(s.getSymbol()) && s1.getIsin().equals(s.getIsin())) {
					ls.add(s1);
				}
			}
			if (ls.size() >= 1 ) System.out.println("Stock :"+s + " Is unchanged :"+ls);
		}
		
		try {
			if (!justCheck)
				DataSource.getInstance().executeBlock(lstUpdate, updateS);

			if (!justCheck)
				DataSource.getInstance().executeBlock(lstUpdateS,updateQ);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Select market.
	 * 
	 * @param symbol the symbol
	 * @param isin the isin
	 * 
	 * @return the string
	 * 
	 * @author Guillaume Thoreton
	 */
	private static String selectMarket(String symbol,String isin) {
			final Query uQ2 = new Query(
					"SELECT MARKET FROM " + SHARES.TABLE_NAME + " WHERE "+
					" ( " +SHARES.TABLE_NAME + "." + SHARES.SYMBOL_FIELD+ " like ? " +
					" OR "+SHARES.TABLE_NAME + "." + SHARES.SYMBOL_FIELD + " = ? ) " +
					" AND "+ SHARES.TABLE_NAME + "." + SHARES.ISIN_FIELD+ " = ? "
					){
				public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
					if (null != rs.getString(1))
						retour.add(rs.getString(1));
					else
						throw new SQLException("Error !!");
				};
			};
			String symbollike = (symbol.contains("."))?symbol:symbol+".%";
			uQ2.addValue(symbollike);
			uQ2.addValue(symbol);
			uQ2.addValue(isin);
			
			List<Object> o = DataSource.getInstance().exectuteSelect(Object.class,uQ2);
			
			if (o.size() != 1) {
				System.out.println("boo : "+ symbol+" size :"+o.size());
			}
			
		return ((String)o.get(0)).trim();
	}
	
	/**
	 * Fix portfolio symbols.
	 * 
	 * @param justCheck the just check
	 * 
	 * @author Guillaume Thoreton
	 */
	private static void fixPortfolioSymbols(Boolean justCheck) {
		List<Validatable> portfoliosymbols = new ArrayList<Validatable>();
		List<Validatable> updatePortfolio = new ArrayList<Validatable>();
		String ssfp = "select symbol from portfolio";
		{
			final Query uQ = new Query(ssfp) {

				@Override
				public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
					if (null != rs.getString(1))
						retour.add(rs.getString(1));
					else
						throw new SQLException("Error !!");
				}
				
			};
			portfoliosymbols.add(new StockToDB() {
				@Override
				public Query toDataBase() {
					return uQ;
				}
				@Override
				public int hashCode() {
					return System.identityHashCode(this);
				}

				@Override
				public boolean equals(Object obj) {
					return (this == obj);
				}
			});
			List<Object> ss = DataSource.getInstance().exectuteSelect(Object.class,uQ);
			
			String udatep = "update portfolio set symbol = ? where symbol = ?";
			for (Object o : ss) {
				String ssfs = "select symbol from shares where symbol like ?";
						
				final Query uQ2 = new Query(ssfs) {
					@Override
					public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
						if (null != rs.getString(1))
							retour.add(rs.getString(1));
						else
							throw new SQLException("Error !!");
					}
				};
				
				uQ2.addValue("%"+((String) o).substring(0, ((String) o).length() - 3)+"%");
				portfoliosymbols.add(new StockToDB() {
					@Override
					public Query toDataBase() {
						return uQ2;
					}
					@Override
					public int hashCode() {
						return System.identityHashCode(this);
					}

					@Override
					public boolean equals(Object obj) {
						return (this == obj);
					}
				});
				List<Object> ssn = DataSource.getInstance().exectuteSelect(Object.class,uQ2);
				if (ssn.size() > 0) {
					String ns = (String) ssn.get(0);
					final Query uQ3 = new Query(ssfs);
					uQ3.addValue(ns);
					uQ3.addValue((String) o);
					updatePortfolio.add(new StockToDB() {
						@Override
						public Query toDataBase() {
							return uQ3;
						}
						@Override
						public int hashCode() {
							return System.identityHashCode(this);
						}

						@Override
						public boolean equals(Object obj) {
							return (this == obj);
						}
					});

				}
			}
			
			try {
				if (!justCheck)
					DataSource.getInstance().executeBlock(updatePortfolio, udatep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void addPortfolioIsinCol(Boolean justCheck) {
		List<Validatable> portfoliosymbols = new ArrayList<Validatable>();
		List<Validatable> updatePortfolio = new ArrayList<Validatable>();
		String ssfp = "select symbol from portfolio";
		{
			final Query uQ = new Query(ssfp) {

				@Override
				public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
					if (null != rs.getString(1))
						retour.add(rs.getString(1));
					else
						throw new SQLException("Error !!");
				}
				
			};
			
			portfoliosymbols.add(new StockToDB() {
				@Override
				public Query toDataBase() {
					return uQ;
				}
				@Override
				public int hashCode() {
					return System.identityHashCode(this);
				}

				@Override
				public boolean equals(Object obj) {
					return (this == obj);
				}
			});
			
			List<Object> ss = DataSource.getInstance().exectuteSelect(Object.class,uQ);
			
			String udatep = "update portfolio set isin = ? where symbol = ?";
			for (Object o : ss) {
				String ssfs = "select isin from shares where symbol = ?";
						
				final Query uQ2 = new Query(ssfs) {
					@Override
					public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
						if (null != rs.getString(1))
							retour.add(rs.getString(1));
						else
							throw new SQLException("Error !!");
					}
				};
				
				uQ2.addValue(o);
				portfoliosymbols.add(new StockToDB() {
					@Override
					public Query toDataBase() {
						return uQ2;
					}
					@Override
					public int hashCode() {
						return System.identityHashCode(this);
					}

					@Override
					public boolean equals(Object obj) {
						return (this == obj);
					}
				});
				List<Object> ssn = DataSource.getInstance().exectuteSelect(Object.class,uQ2);
				if (ssn.size() > 0) {
					String ns = (String) ssn.get(0);
					final Query uQ3 = new Query(ssfs);
					uQ3.addValue(ns);
					uQ3.addValue((String) o);
					updatePortfolio.add(new StockToDB() {
						@Override
						public Query toDataBase() {
							return uQ3;
						}
						@Override
						public int hashCode() {
							return System.identityHashCode(this);
						}

						@Override
						public boolean equals(Object obj) {
							return (this == obj);
						}
					});

				}
			}
			
			try {
				if (!justCheck)
					DataSource.getInstance().executeBlock(updatePortfolio, udatep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void addPortfolioNames(Boolean justCheck) {
		List<Validatable> portfoliosymbols = new ArrayList<Validatable>();
		List<Validatable> updatePortfolio = new ArrayList<Validatable>();
		String ssfp = "select distinct name from portfolio";
		{
			final Query uQ = new Query(ssfp) {

				@Override
				public void resultParse(List<Object> retour, ResultSet rs) throws SQLException {
					if (null != rs.getString(1))
						retour.add(rs.getString(1));
					else
						throw new SQLException("Error !!");
				}
				
			};
			
			portfoliosymbols.add(new StockToDB() {
				@Override
				public Query toDataBase() {
					return uQ;
				}
				@Override
				public int hashCode() {
					return System.identityHashCode(this);
				}

				@Override
				public boolean equals(Object obj) {
					return (this == obj);
				}
			});
			
			List<Object> ss = DataSource.getInstance().exectuteSelect(Object.class,uQ);
			
			String udatep = "insert into portfolio_name (name) values (?)";
			for (Object o : ss) {
				
					final Query uQ3 = new Query(udatep);
					uQ3.addValue((String) o);
					updatePortfolio.add(new StockToDB() {
						@Override
						public Query toDataBase() {
							return uQ3;
						}
						@Override
						public int hashCode() {
							return System.identityHashCode(this);
						}

						@Override
						public boolean equals(Object obj) {
							return (this == obj);
						}
					});

			}
			
			try {
				if (!justCheck)
					DataSource.getInstance().executeBlock(updatePortfolio, udatep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//FIXME
//	private static void addPortfolioAmountIn(Boolean boolean1) {
//		
//		List<Portfolio> list = PortfolioMgr.getInstance().getVisiblePortfolios();
//		
//		for (AbstractSharesList portfolio: list) {
//			BigDecimal moneyIn = (new BigDecimal(0)).setScale(2);
//			BigDecimal moneyOut = (new BigDecimal(0)).setScale(2);
//			for (PortfolioShare portfolioShare: portfolio.getListShares().values()) {
//				moneyIn = moneyIn.add(portfolioShare.getCashin());
//				moneyOut = moneyOut.add(portfolioShare.getCashout());
//			}
//			portfolio.setTotalInAmountEver(moneyIn.setScale(2,RoundingMode.HALF_EVEN));
//			portfolio.setTotalOutAmountEver(moneyOut.setScale(2,RoundingMode.HALF_EVEN));
//		}
//		
//		if (!boolean1) PortfolioMgr.getInstance().hibStorePortfolio();
//	}
	
	private static void addBuyPriceStopLoss(Boolean hibernate) {
		
		List<Portfolio> list = PortfolioMgr.getInstance().getVisiblePortfolios();
		
		for (AbstractSharesList portfolio: list) {
			for (PortfolioShare portfolioShare: portfolio.getListShares().values()) {
				System.out.println(portfolioShare.toString());
				//FIXME portfolioShare.addBuyAlerts();
			}
		}
		
		if (!hibernate) PortfolioMgr.getInstance().hibStorePortfolio();
	}
}
