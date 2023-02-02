package com.finance.pms.datasources.web.currency;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.finance.pms.SpringContext;
import com.finance.pms.admin.config.EventSignalConfig;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.db.DataSource.SHARES;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareFilter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.portfolio.PortfolioDAO;
import com.finance.pms.threads.ConfigThreadLocal;

public class QuotationFixerTest {

	private static SpringContext springContext;
	private QuotationFixer currencyFixer;

	@BeforeClass
	public static void oneTimeSetup() {
		springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/db.properties");
		springContext.loadBeans("/connexions.xml", "/swtclients.xml");
		springContext.refresh();

		EventSignalConfig config = new EventSignalConfig();
		ConfigThreadLocal.set("eventSignal", config);

	}

	@AfterClass
	public static void ontTimeTearDown() {
		springContext.close();
	}

	@Before
	public void setup() {
		currencyFixer = new QuotationFixer();
	}

	private List<Stock> loadStocksUK() {

		List<Stock> loadShares = DataSource.getInstance().getShareDAO().loadShares(new ShareFilter() {
			@Override
			public String getRequestConstraint(String... params) {
				return " "+SHARES.CURRENCY+" = '"+Currency.GBP.name()+"' AND "+SHARES.CURRENCYFACTOR+" > 1";
			}
		});

		//loadShares = loadShares.subList(0, 20); //Test
		//loadShares = loadShares.stream().filter(s -> s.getSymbol().equals("TLW.L")).collect(Collectors.toList()); //Test

		return loadShares;

	}

	private void updateStocksQuotations(List<Stock> loadShares) {
		try {
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			quotationUpdate.getQuotesFor(loadShares);
		} catch (QuotationUpdateException e1) {
			e1.printStackTrace();
		}
	}

	//@Test
	public void testUpdateNCheckPennyPound() {
		List<Stock> loadShares = loadStocksUK();
		updateStocksQuotations(loadShares);
		currencyFixer.checkPennyPound(loadShares);
	}

	//@Test
	public void testCheckPennyPound() {
		List<Stock> loadShares = loadStocksUK();
		currencyFixer.checkPennyPound(loadShares);
	}

	//@Test
	public void testFixPennyPound() {
		List<Stock> loadShares = loadStocksUK();
		currencyFixer.fixPennyPound(loadShares);
	}

	//@Test
	public void split() throws NoQuotationsException {
		List<Stock> stocks = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol("AAPL");
		QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stocks.get(0), DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);
	}

	//@Test
	@Deprecated
	public void testCheckCounterSplit() {
		//List<Stock> loadShares = loadStocksUK();
		//loadShares = loadShares.subList(0, 50); //Test
		//List<Stock> loadShares = DataSource.getInstance().getShareDAO().loadAllStocks();
		List<Stock> loadShares = ((PortfolioDAO) springContext.getBean("portfolioDAO")).loadIndicesSharesListContentExUnknown().stream().map(ps -> ps.getStock()).collect(Collectors.toList());
		currencyFixer.checkCounterSplitFailFast(loadShares, 0.5);
	}

//	//@Test
//	public void testFixCounterSplit() {
//		List<Stock> loadShares = loadStocks();
//		loadShares = loadShares.subList(0, 50); //Test
//		currencyFixer.fixAntiSplit(loadShares);
//	}

}
