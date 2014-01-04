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
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StockComplementEstimatesYahooFormater;
import com.finance.pms.datasources.web.formaters.StockComplementFinancialsReutersFormater;
import com.finance.pms.datasources.web.formaters.StockComplementOpinionBoursoramaFormater;
import com.finance.pms.datasources.web.formaters.StockComplementOpinionYahooFormater;
import com.finance.pms.datasources.web.formaters.StockComplementSectorYahooFormater;
import com.finance.pms.datasources.web.formaters.StockComplementSummaryBoursoramaFormater;
import com.finance.pms.datasources.web.formaters.StockComplementSummaryReutersFormater;
import com.finance.pms.datasources.web.formaters.StockComplementSummaryYahooFormater;
import com.finance.pms.datasources.web.formaters.StockComplementYahooFormater;
import com.finance.pms.datasources.web.formaters.StockListYahooIncideHtmlScrapFormater;
import com.finance.pms.portfolio.SharesList;
import com.finance.pms.screening.ScreeningSupplementedStock;

//TODO move that out to create a list provider as this is not a market => multi urls
public class ProvidersYahooIndices extends ProvidersList {

	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooIndices.class);

	private Set<Indice> indices;

	public ProvidersYahooIndices(String pathToProps) {
		super();
		this.httpSource = new HttpSourceYahooIndices(pathToProps, this);
		this.indices = new TreeSet<Indice>();
	}

	public ProvidersYahooIndices(String pathToProps,List<Indice> indices) {
		this(pathToProps);
		this.indices = new TreeSet<Indice>(indices);
	}
	
	
	@Override
	public SharesList loadSharesListForThisListProvider() {
		String extention = Indice.formatSet(indices);
		return super.initSharesList(this.getSharesListIdEnum().name(),extention);
	}


	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return new StockListYahooIncideHtmlScrapFormater(url, new MarketValuation(market));
	}


	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	@Override
	protected Stock supplement(Stock stock) {
		
		try {
			
			//isin && name
			try {
				try {
					String url = this.httpSource.getStockInfoPageURL(stock.getSymbol());
					LOGGER.debug(" Will parse url : "+url);
					LineFormater dsf = new StockComplementYahooFormater(url, stock);
					Stock nameIsincompletedStock = (Stock) this.httpSource.readURL(dsf).get(0);
					stock.resetStock(nameIsincompletedStock);
				} catch (IndexOutOfBoundsException e) {
					LOGGER.warn("Can't supplement symbol with isin or name : "+stock.getSymbol());
					if (stock.getIsin() == null ) stock.setIsin(stock.getSymbol());
					if (stock.getName() == null ) stock.setName(stock.getIsin());
				} catch (HttpException e) {
					LOGGER.warn("Can't supplement symbol with isin or name : "+stock.getSymbol(),e);
					if (stock.getIsin() == null ) stock.setIsin(stock.getSymbol());
					if (stock.getName() == null ) stock.setName(stock.getIsin());
				}
			} catch (InvalidAlgorithmParameterException e1) {
				LOGGER.warn("Can't supplement symbol with isin or name : "+stock.getSymbol(),e1);
			}
			
			//sector
			try {
				String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageProfilURL(stock.getSymbol());
				LineFormater dsf = new StockComplementSectorYahooFormater(url, stock);
				Stock completedStock = (Stock) this.httpSource.readURL(dsf).get(0);
				stock.resetStock(completedStock);
			} catch (IndexOutOfBoundsException e) {
				LOGGER.warn("Can't supplement symbol with sector : "+stock.getSymbol());
				stock.setSectorHint("unknown");
			} catch (HttpException e) {
				LOGGER.warn("Can't supplement symbol with sector : "+stock.getSymbol());
				stock.setSectorHint("unknown");
			}
		}catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement symbol : "+stock.getSymbol(),e);
		}
		
		LOGGER.guiInfo("Updating stock list : "+stock.getSymbol());
		return stock;
	}
	
	@Override
	protected Comparator<Stock> getNewStockComparator() {
		return new Comparator<Stock>() {

			
			public int compare(Stock o1, Stock o2) {
				return o1.getSymbol().compareTo(o2.getSymbol());
			}
			
		};
	}
	

	/**
	 * @param marketQuotationsProviders
	 * @param passTheBucketList
	 * @param sharesListStocks
	 * @throws HttpException 
	 */
	@SuppressWarnings("unchecked")
	@Override//TODO merge with nse indices => create market indices related Provider : probably add an interface alike MarketListProvider
	protected Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
		
		Set<Stock> listFromWeb = new TreeSet<Stock>(getNewStockComparator());
		
		for (Indice indice : indices) {
			
			for (Integer i = 0; i < 50; i++) {
				String url = this.httpSource.getCategoryStockListURL(StockCategories.INDICES_OTHER, "^" + indice.getName(), i.toString());
				LOGGER.info("Yahoo Indice Url : " + url);
				LineFormater yahooIndiceFormater = this.getFormater(url, indice.getMarket(), marketQuotationsProviders);
				@SuppressWarnings("rawtypes")
				List listOfIndiceStocks = new ArrayList();
				try {
					listOfIndiceStocks = ((HttpSourceMarket) this.httpSource).readURL(yahooIndiceFormater);
				} catch (HttpException e) {
					LOGGER.error(e, e);
					throw e;
				} catch (Exception e) {
					LOGGER.error(e, e);
				}
				if (listOfIndiceStocks.size() == 0) break;
				listFromWeb.addAll(listOfIndiceStocks);
			}
		}
		
		return listFromWeb;
	}

	@Override
	public void retrieveScreeningInfoForShare(ScreeningSupplementedStock trendSupStock) {
		
		//XXX yahoo must before reuters and bourso last for the dividend setting
		yahooSummary(trendSupStock);
		yahooOpinions(trendSupStock);  
		yahooEstimates(trendSupStock);
		
		reutersSummary(trendSupStock);
		reutersFinancials(trendSupStock);
		
		boursoSummary(trendSupStock);
		boursoramaOpinions(trendSupStock);
		
	}

	private void yahooSummary(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : yahoo Dividendes for "+trendSupStock.getStock());
			String url = this.httpSource.getStockInfoPageURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementSummaryYahooFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement dividend and PEG for symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with dividends and PEG : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with dividends and PEG : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setYahooEPS(BigDecimal.ZERO);
		}
	}

	private void yahooEstimates(ScreeningSupplementedStock trendSupStock) {

		try {
			LOGGER.guiInfo("Updating screening info : yahoo PEG for "+trendSupStock.getStock());
			String url;
			LineFormater dsf;
			if (trendSupStock.getStock().getMarketValuation().getCurrency().equals(Currency.USD)) {
				url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageEstimatesURL(trendSupStock.getStock().getSymbol());
				dsf = new StockComplementEstimatesYahooFormater(url, trendSupStock);
			} else {
				url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageUKEstimatesURL(trendSupStock.getStock().getSymbol());;
				dsf = new StockComplementEstimatesYahooFormater(url, trendSupStock);
			}
			
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);		
			
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement yahoo PEG for symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with yahoo PEG : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with yahoo PEG : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setYahooEstEPS(BigDecimal.ZERO);
		}	
	}
	
	private void boursoSummary(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : bourso PEG and DIV for "+trendSupStock.getStock());
			String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageBOResumeURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementSummaryBoursoramaFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement bourso PEG or DIV for symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with bourso PEG or DIV : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with bourso PEG or DIV : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setBoursoBNA(BigDecimal.ZERO);
			trendSupStock.setBoursoEstBNA(BigDecimal.ZERO);
		}
	}
	
	
	private void reutersFinancials(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : reuters PEG for "+trendSupStock.getStock());
			String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageReutersFinancialsURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementFinancialsReutersFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement Reuters PEG for symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with Reuters PEG : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with Reuters PEG : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setReutersEstEPS(BigDecimal.ZERO);
			trendSupStock.setReutersPayoutRatio(BigDecimal.ZERO);
			trendSupStock.setReutersYield(BigDecimal.ZERO);
		}
	}
	
	private void reutersSummary(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : reuters PEG for "+trendSupStock.getStock());
			String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageReutersOverViewURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementSummaryReutersFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement Reuters PEG for symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with Reuters PEG : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with Reuters PEG : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setReutersEPS(BigDecimal.ZERO);
		}
	}

	private void boursoramaOpinions(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : Boursorama opinions for "+trendSupStock.getStock());
			String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageBOpinionsURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementOpinionBoursoramaFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = trendSupStock;
			try {
				completedStock = (ScreeningSupplementedStock) httpSource.readURL(dsf).get(0);
			} catch (HttpException e) {
				try {
					completedStock = (ScreeningSupplementedStock) httpSource.readURL(dsf).get(0);
					LOGGER.info("Supplement done at second try for "+trendSupStock.getStock().getSymbol());
				} catch (Exception e1) {
					LOGGER.debug("Can't supplement symbol with bourso opinions : "+trendSupStock.getStock().getSymbol(),e);
					trendSupStock.setBoursoMeanRecommendations(BigDecimal.ZERO);
					trendSupStock.setBoursoTargetPrice(BigDecimal.ZERO);
				}
			}
			trendSupStock.resetStock(completedStock);
		}  catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement bourso opinion symbol : "+trendSupStock.getStock().getSymbol(),e);
		}  catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with bourso opinions : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with bourso opinions : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setBoursoMeanRecommendations(BigDecimal.ZERO);
			trendSupStock.setBoursoTargetPrice(BigDecimal.ZERO);
		}
	}

	private void yahooOpinions(ScreeningSupplementedStock trendSupStock) {
		try {
			LOGGER.guiInfo("Updating screening info : Yahoo opinions for "+trendSupStock.getStock());
			String url = ((HttpSourceYahooIndices)this.httpSource).getStockInfoPageOpinionsURL(trendSupStock.getStock().getSymbol());
			LineFormater dsf = new StockComplementOpinionYahooFormater(url, trendSupStock);
			ScreeningSupplementedStock completedStock = (ScreeningSupplementedStock) this.httpSource.readURL(dsf).get(0);
			trendSupStock.resetStock(completedStock);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Can't supplement yahoo opinions symbol : "+trendSupStock.getStock().getSymbol(),e);
		} catch (Exception e) {
			LOGGER.debug("Can't supplement symbol with yahoo opinions : "+trendSupStock.getStock().getSymbol(),e);
			LOGGER.warn("Can't supplement symbol with yahoo opinions : "+trendSupStock.getStock().getSymbol());
			trendSupStock.setYahooMeanRecommendations(BigDecimal.ZERO);
			trendSupStock.setYahooTargetPrice(BigDecimal.ZERO);
		}
	}
	

	public void setIndices(Set<Indice> indices) {
		this.indices = indices;
	}
	
	@Override
	public Set<Indice> getIndices() {
		return indices;
	}
	
	@Override
	public void addIndice(Indice indice) {
		this.indices.add(indice);
		MainPMScmd.getPrefs().put("quotes.listproviderindices",Indice.formatSet(indices));
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		if (replace) {
			this.indices=indices;
		} else {
			for (Indice indice : indices) {
				this.indices.add(indice);
			}
		}
		
		MainPMScmd.getPrefs().put("quotes.listproviderindices",Indice.formatSet(this.indices));
	}
}
