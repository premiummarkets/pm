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
package com.finance.pms.portfolio;





// TODO: Auto-generated Javadoc
/**
 * The Class RandomPortfolio.
 * 
 * @author Guillaume Thoreton
 */
//FIXME
public class RandomPortfolio {
	
	/** The LOGGER. */
//	protected static MyLogger LOGGER = MyLogger.getLogger(RandomPortfolio.class);
//	
//	/** The NBSHARES. */
//	private final int NBSHARES=10;
//	
//	/** The MAXCASHIN. */
//	private final int MAXCASHIN=300;
//	
//	/** The RANDOM. */
//	private final String RANDOM="RanDom";
//	//private final float[] SELLTHREESHOLD = {10,20,30,40,50,60,70,80,90};
//	//private final float[] SELLTHREESHOLD = {25,50,75,100};
//	/** The SELLTHREESHOLD. */
//	private final float[] SELLTHREESHOLD = {25,25,50,50,50,75,75,75,100};
//	
//	/** The list stocks. */
//	private Collection<String> listStocks;
//	
//	/** The random. */
//	private Random random;
//	
//	/** The dispo. */
//	private float dispo;
//
//	/**
//	 * Instantiates a new random portfolio.
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	public RandomPortfolio() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * The main method.
//	 * 
//	 * @param args the arguments
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	public static void main(String[] args) {
//		
//		LOGGER.info("Usage : .RandomPortfolio -init");
//		LOGGER.info("Usage : .RandomPortfolio -run");
//		
//		RandomPortfolio rp= new RandomPortfolio();
//		
//		new DataSource("/home/guil/Developpement/Quotes/com.finance.pms/db.properties");
//		
//		if (args[0].equals("-init")) {
//			rp.initPortfolio();
//		}
//		
//		if (args[0].equals("-run")) {
//			for (int k=0;k < 30;k++) {
//				rp.updatePortfolio();
//			}
//		}
//	}
//	
//	/**
//	 * Inits the portfolio.
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private void initPortfolio() {
//		
////		AbstractSharesList  rp =new UserPortfolio(RANDOM);
////		this.listStocks = DataSource.getInstance().loadLowerSymbolsByCategory(StockCategories.DEFAULT_CATEGORY.getCategory());
////		this.random = new Random();
////		
////		for (int i=0;i<NBSHARES;i++){
////			PortfolioShare ps = this.newShare(MAXCASHIN);
////			rp.addNewShare(ps);
////		}
////		
////		//FIXME 
////		//PortfolioMgr.getInstance().addPortfolio(rp);
////		PortfolioMgr.getInstance().storePortfolios();
//		
//	}
//	
//	/**
//	 * Update portfolio.
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private void updatePortfolio() { 
//		
//		this.random = new Random();
//		List<Portfolio> rpL =  PortfolioMgr.getInstance().getPortfolios();
//		AbstractSharesList rp = rpL.get(rpL.indexOf(new UserPortfolio(RANDOM)));
//		List<PortfolioShare> shares=new ArrayList<PortfolioShare>(rp.getListShares().values());
//		
//		this.listStocks = DataSource.getInstance().loadLowerSymbolsByCategory(StockCategories.DEFAULT_CATEGORY.getCategory());
//		
//		float totalcashin=0;
//		float totalcashout=0;
//		int zeroed=0;
//		
//		//Suppretion des stock d�j� achet�es.
//		Iterator<PortfolioShare> psLIt = shares.iterator();
//		while (psLIt.hasNext()) {
//			PortfolioShare pstmp = psLIt.next();
//			//this.listStocks.remove(new Stock(null,pstmp.getSymbol()));
//			totalcashin=totalcashin+pstmp.getCashin().floatValue();
//			totalcashout=totalcashout+pstmp.getCashout().floatValue();
//			zeroed=(pstmp.getQuantity().floatValue() == 0)?zeroed+1:zeroed;
//		}
//		this.dispo = MAXCASHIN*NBSHARES-(totalcashin-totalcashout);
//		LOGGER.info("Cash In before : "+totalcashin);
//		LOGGER.info("Cash Out before : "+totalcashout);
//		LOGGER.info("Sum before : "+(totalcashin-totalcashout));
//		LOGGER.info("Num Zeroed : "+zeroed);
//		
//		//Ventes
//		int nbventes=this.random.nextInt(shares.size());
//		int nbtrop=shares.size()-zeroed-NBSHARES;
//		LOGGER.info("Num Sell : "+nbventes);
//		Collections.shuffle(shares,this.random);
//		Iterator<PortfolioShare> sIt = shares.iterator();
//		while (sIt.hasNext() && ((nbventes > 0) || (nbtrop >0))) {
//			PortfolioShare pstmp = sIt.next();	
//
//			if ((pstmp.getQuantity().floatValue() != 0) && (nbtrop >0)) {
//				nbtrop--;
//				pstmp = sellShare(pstmp,true);
//				zeroed++;
//				rp.addNewShare(pstmp);
//			} else 			
//				if  ((pstmp.getQuantity().floatValue() != 0) && (nbventes > 0)) {
//				//this.listStocks.remove(new Stock(null,pstmp.getSymbol()));
//				nbventes--;
//				pstmp = sellShare(pstmp,false);
//				if (pstmp.getQuantity().floatValue() == 0 ) zeroed++;
//				rp.addNewShare(pstmp);
//			}
//		}
//		
//		//Achats
//		int nbachats=this.random.nextInt(NBSHARES); //+zeroed);
//		LOGGER.info("Num Zeroed : "+zeroed);
//		LOGGER.info("Num Buy : "+nbachats);
//		Collections.shuffle(shares,this.random);
//		for (int k=0;k<nbachats;k++) {
//			//PortfolioShare pstmp = (PortfolioShare) shares.get(k);
//			float cash;
//			cash = (SELLTHREESHOLD[this.random.nextInt(SELLTHREESHOLD.length)]/100)*(dispo/(nbachats-k));
//			//cash = random.nextFloat()*(dispo/(nbachats-k));
//			this.dispo = this.dispo - cash;
//			
//			PortfolioShare pstmp = this.newShare(cash);
//			
//			if  (rp.getListShares().containsKey(pstmp.getStock())) {
//					pstmp = rp.getListShares().get(pstmp.getStock());
//					pstmp = this.renforceShare(pstmp,cash);
//			}
//			
//			if (k==(nbachats-1) && zeroed <= 0) {//derni�re valeur et pas de nouvelle valeur : on claque tout
//				cash = this.dispo;
//				this.dispo=0;
//				pstmp = this.renforceShare(pstmp,cash);
//			}
//			
//			rp.addNewShare(pstmp);
//
//		}
//		
////		//Nouvelles
////		for (int k=0;k< zeroed;k++) {
////			float cash;
////			if (k < zeroed-1) {
////				//cash = random.nextFloat()*(dispo/(zeroed-k));
////				cash = (SELLTHREESHOLD[this.random.nextInt(SELLTHREESHOLD.length)]/100)*(dispo/(zeroed-k));
////				//cash = random.nextFloat()*(dispo/(zeroed-k));
////				this.dispo = this.dispo - cash;
////			} else {
////				cash = this.dispo;
////				this.dispo = 0;
////			}
////			PortfolioShare pstmp = this.newShare(cash);
////			
////			rp.addShare(pstmp);
////		}
//		
//		psLIt = rp.getListShares().values().iterator();
//		totalcashin=0;
//		totalcashout=0;
//		while (psLIt.hasNext()) {
//			PortfolioShare pstmp = psLIt.next();
//			totalcashin=totalcashin+pstmp.getCashin().floatValue();
//			totalcashout=totalcashout+pstmp.getCashout().floatValue();
//		}
//		LOGGER.info("Cash In after : "+totalcashin);
//		LOGGER.info("Cash Out after : "+totalcashout);
//		LOGGER.info("Sum after : "+(totalcashin-totalcashout));
//		
//		
//	
//		PortfolioMgr.getInstance().storePortfolios();
//	}
//	
//	/**
//	 * Renforce share.
//	 * 
//	 * @param ps the ps
//	 * @param cash the cash
//	 * 
//	 * @return the portfolio share
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private PortfolioShare renforceShare(PortfolioShare ps, float cash) {
//		
//		//G�n�ration d'une nouvelle quantit�
//		float quantity = cash/PortfolioMgr.getInstance().loadLastDayClosed(ps.getSymbol(),ps.getIsin()).floatValue();
//		
//		try {
//			//ps.setCashin(Float.valueOf(ps.getCashin().floatValue()+cash));
//			//ps.setQuantity(Float.valueOf(ps.getQuantity().floatValue()+quantity));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return ps;	
//	}
//	
//	/**
//	 * Sell share.
//	 * 
//	 * @param ps the ps
//	 * @param all the all
//	 * 
//	 * @return the portfolio share
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private PortfolioShare sellShare(PortfolioShare ps,boolean all) {
//		
//		//G�n�ration d'une nouvelle quantit�
//		float quantity;
//		if (all || ps.getQuantity().floatValue() < 1) {
//			quantity=ps.getQuantity().floatValue();
//		} else {
//			quantity= ps.getQuantity().floatValue() * (SELLTHREESHOLD[this.random.nextInt(SELLTHREESHOLD.length)]/100);
//		}
//		//float quantity = ps.getQuantity().floatValue() * this.random.nextInt(100)/100;
////		float r = this.random.nextFloat();
////		float quantity = (r < 0.01)?0:ps.getQuantity().floatValue() * r;
//		
//		
//		//G�n�ration du cashout
//		float cashout = quantity * 
//					PortfolioMgr.getInstance().loadLastDayClosed(ps.getSymbol(),ps.getIsin()).floatValue();
//		
//		this.dispo = this.dispo + cashout;
//		
//		try {
//			//ps.setCashout(Float.valueOf(ps.getCashout().floatValue()+cashout));
//			//ps.setQuantity(Float.valueOf(ps.getQuantity().floatValue() - quantity));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return ps;
//	}
//	
//	/**
//	 * New share.
//	 * 
//	 * @param cash the cash
//	 * 
//	 * @return the portfolio share
//	 * 
//	 * @author Guillaume Thoreton
//	 */
//	private PortfolioShare newShare(float cash) {
//		PortfolioShare retour;
//		
//		//S�l�ction d'une valeur
//		String s=new String();
//		//int interStock = listStocks.size() / NBPORTFOLIO;
//		//int interInf = i*interStock;
//		int interInf = 0;
//		//int interSup = random.nextInt(NBPORTFOLIO);
//		int interSup = random.nextInt(listStocks.size());
//		
//		Iterator<String> listStockIt = this.listStocks.iterator();
//		//for(int j=interInf;j<interInf+interSup;j++) {
//		for(int j=interInf;j <= interInf+interSup;j++) {
//			s = listStockIt.next().toUpperCase();
//		}
//		listStocks.remove(s);
//		
//		//R�cup�ration du cashin
//		//float cashIn = PortfolioMgr.getInstance().loadLastDayClosed(s).floatValue()*quantity;
//		//float cashIn = random.nextInt(MAXCASHIN);
//		float cashIn = cash;
//		
//		//G�n�ration d'un quantit�
//		//int quantity = random.nextInt(MAXQUANTIY);
//		//FIXME
////		float quantity = cashIn/PortfolioMgr.getInstance().loadLastDayClosed(s).floatValue();
////		
////		//cashout
////		float cashOut = 0;
////		
////		retour = new PortfolioShare(s,Float.valueOf(quantity),new Date(),Float.valueOf(cashIn),Float.valueOf(cashOut),0); //,0F);
////		
////		return retour;
//		return null;
//	}
	
}
