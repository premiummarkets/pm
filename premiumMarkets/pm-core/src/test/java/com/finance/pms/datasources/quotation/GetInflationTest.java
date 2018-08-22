package com.finance.pms.datasources.quotation;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetInflationTest {

    GetInflation getInflation;
    private Quotations inflationQs;

    @Before
    public void setUp() throws Exception {
        getInflation = new GetInflation();
        Stock inflationStock = ProvidersInflation.inflationStock();
        inflationQs = QuotationsFactories.getFactory()
                .getQuotationsInstance(inflationStock, DateFactory.dateAtZero(), DateFactory.now().getTime(), true, Currency.NAN, 1, Quotations.ValidityFilter.CLOSE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void inflationRateWithinDateRange() {
        //inflationQs
    }
}