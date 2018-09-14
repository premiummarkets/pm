package com.finance.pms.datasources.quotation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;

@Service
public class GetInflation {

    private static MyLogger LOGGER = MyLogger.getLogger(GetInflation.class);

    public BigDecimal inflationRateWithinDateRange(Date firstDate, Date secondDate) {

        try {
            Stock inflationStock = ProvidersInflation.inflationStock();
            Quotations inflationQs = QuotationsFactories.getFactory().getQuotationsInstance(inflationStock, firstDate, secondDate, true, Currency.NAN, 1, Quotations.ValidityFilter.CLOSE);

            Integer secondDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, secondDate);
            QuotationUnit secondQs = inflationQs.get(secondDateQsIdx);
            Integer firstDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, firstDate);
            QuotationUnit firstQs;
            if (firstDateQsIdx < secondDateQsIdx) {
                firstQs = inflationQs.get(firstDateQsIdx);
            } else if (firstDateQsIdx == secondDateQsIdx && firstDateQsIdx > 0) {
                firstQs = inflationQs.get(firstDateQsIdx--);
            } else {
                throw new Exception();
            }

            BigDecimal inflationAtFirst = firstQs.getClose();
            BigDecimal inflationAtSecond = secondQs.getClose();
            BigDecimal knowQsInflationRate = inflationAtSecond.subtract(inflationAtFirst).divide(inflationAtFirst, 10, BigDecimal.ROUND_HALF_EVEN);

            long knownQsSpan = TimeUnit.DAYS.convert(secondQs.getDate().getTime() - firstQs.getDate().getTime(), TimeUnit.MILLISECONDS);
            long requestedDatesSpan = TimeUnit.DAYS.convert(secondDate.getTime() - firstDate.getTime(), TimeUnit.MILLISECONDS);

            double inflationRate = ((double)requestedDatesSpan) * (knowQsInflationRate.doubleValue() / (double)knownQsSpan);

            LOGGER.debug("Inflation rate between " + firstDate + " and " + secondDate + " is : " + inflationRate);
            return BigDecimal.valueOf(inflationRate);

        } catch (Exception e) {
            LOGGER.warn(e,e);
        }

        return BigDecimal.ZERO;
    }

}
