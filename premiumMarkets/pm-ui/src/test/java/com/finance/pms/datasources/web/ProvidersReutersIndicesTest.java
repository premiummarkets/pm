package com.finance.pms.datasources.web;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.http.HttpException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.finance.pms.SpringContext;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.Stock;

public class ProvidersReutersIndicesTest {

	private ProvidersReutersIndices p;

    private static SpringContext springContext;

    @BeforeClass
    public static void oneTimeSetup() {
        springContext = new SpringContext("/home/guil/Developpement/newEclipse/premiumMarkets/pm-forecast/dbDerby.properties");
        springContext.loadBeans("/connexions.xml", "/swtclients.xml");
        springContext.refresh();
    }

    @AfterClass
    public static void ontTimeTearDown() {
        springContext.close();
    }

	@Before
	public void setup() {
		p = springContext.getBean(ProvidersReutersIndices.class);
		SortedSet<Indice> indices = new TreeSet<>();
		indices.add(new Indice("FTMC", Market.LSE));
		p.addIndices(indices, false);
	}

	//@Test
	public void testFetchStockList() throws HttpException {
		Set<Stock> stockList = p.fetchStockList(MarketQuotationProviders.YAHOO);
		System.out.println(stockList.size());
		Optional<String> reduce = stockList.stream().map(s -> s.toString()).reduce( (r,e) -> r + "\n" + e);
		System.out.println(reduce);
	}
	
	//@Test 
	public void testSuppelement() {
		Stock stock = new Stock("ORA.PA", "ORA.PA");
		p.supplement(stock);
		System.out.println(stock);
	}

}
