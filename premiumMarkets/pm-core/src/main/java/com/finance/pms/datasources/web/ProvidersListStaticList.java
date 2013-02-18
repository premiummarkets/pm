package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.httpclient.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.web.formaters.LineFormater;
import com.finance.pms.datasources.web.formaters.StockComplementSectorYahooFormater;
import com.finance.pms.datasources.web.formaters.StockComplementYahooFormater;
import com.finance.pms.datasources.web.formaters.StockListStaticListFormater;
import com.finance.pms.screening.TrendSupplementedStock;

public class ProvidersListStaticList extends ProvidersList {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooIndices.class);
	
	private Set<Indice> indices;
	

	public ProvidersListStaticList(String pathToProps) {
		super();
		this.httpSource = new HttpSourceStaticList(pathToProps, this);
		this.indices = new TreeSet<Indice>();
	}

	@Override
	public String getStockRefName(Stock stock) {
		return stock.getSymbol();
	}

	//TODO this should come in a delegate as it used in several providers eg ProvidersYahooIndices
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
				String url = this.getStockInfoPageProfilURL(stock.getSymbol());
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
	
	public String getStockInfoPageProfilURL(String isin) throws UnsupportedEncodingException {
		return "http://uk.finance.yahoo.com/q/pr?s="+ URLEncoder.encode(isin,"UTF-8");
	}

	@Override
	protected Comparator<Stock> getNewStockComparator() {
		return new Comparator<Stock>() {

			public int compare(Stock o1, Stock o2) {
				return o1.getSymbol().compareTo(o2.getSymbol());
			}

		};
	}

	@Override
	protected LineFormater getFormater(String url, Market market, MarketQuotationProviders marketQuotationsProviders) {
		return new StockListStaticListFormater(url, StockCategories.DEFAULT_CATEGORY, new MarketValuation(market));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Set<Stock> fetchStockList(MarketQuotationProviders marketQuotationsProviders) throws HttpException {
		Set<Stock> listFromWeb = new TreeSet<Stock>(getNewStockComparator());

		for (Indice indice : indices) {

				String url = this.httpSource.getCategoryStockListURL(StockCategories.DEFAULT_CATEGORY, indice.getMarket().getFriendlyName());
				LOGGER.info("All market Url : " + url);
				LineFormater staticListFormater = this.getFormater(url, indice.getMarket(), marketQuotationsProviders);
				@SuppressWarnings("rawtypes")
				List listOfIndiceStocks = new ArrayList();
				try {
					listOfIndiceStocks = ((HttpSourceMarket) this.httpSource).readURL(staticListFormater);
				} catch (HttpException e) {
					LOGGER.error(e, e);
					throw e;
				}  catch (Exception e) {
					LOGGER.error(e, e);
				}
				listFromWeb.addAll(listOfIndiceStocks);
		}

		return listFromWeb;
	}

	@Override
	public void retrieveScreeningInfoForShare(TrendSupplementedStock trendSupStock) {
		//throw new NotImplementedException();

	}

	@Override
	public void addIndice(Indice indice) {
		this.indices.add(indice);
		MainPMScmd.getPrefs().put("quotes.listproviderindices", Indice.formatSet(indices));
	}

	@Override
	public void addIndices(Set<Indice> indices, Boolean replace) {
		System.out.println("idx :"+indices);
		if (replace) {
			this.indices=indices;
		} else {
			for (Indice indice : indices) {
				this.indices.add(indice);
			}
		}
		
		MainPMScmd.getPrefs().put("quotes.listproviderindices", Indice.formatSet(this.indices));
	}
	
	@Override
	public Set<Indice> getIndices() {
		return indices;
	}

}
