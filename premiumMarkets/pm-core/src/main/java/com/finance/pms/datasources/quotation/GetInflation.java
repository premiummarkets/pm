package com.finance.pms.datasources.quotation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersInflation;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;

/**
 * Note: Inflation is stored compounded.
 * @author Gheeyom Thor
 *
 */
@Service
public class GetInflation {

	private static MyLogger LOGGER = MyLogger.getLogger(GetInflation.class);

	private static GetInflation getInflation;

	public static GetInflation geInstance() {
		if (getInflation == null) getInflation = new GetInflation();
		return getInflation;
	}

	private Quotations inflationQs;

	public GetInflation() {
		super();
	}

	private void init() {
		try {
			Stock inflationStock = ProvidersInflation.inflationStock();
			this.inflationQs = QuotationsFactories.getFactory().getQuotationsInstance(inflationStock, DateFactory.dateAtZero(), DateFactory.getNowEndDate(), true, Currency.NAN, 1, Quotations.ValidityFilter.CLOSE);
		} catch (NoQuotationsException e) {
			LOGGER.error(e);
		}
	}

	public BigDecimal inflationRateWithinDateRange(Date firstDate, Date secondDate) throws NotEnoughDataException, NoQuotationsException {

		if (inflationQs == null) init();

		Integer secondDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, secondDate);
		QuotationUnit secondQs = inflationQs.get(secondDateQsIdx);
		Integer firstDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, firstDate);
		QuotationUnit firstQs;
		if (firstDateQsIdx < secondDateQsIdx) {
			firstQs = inflationQs.get(firstDateQsIdx);
		} else
		if (0 < firstDateQsIdx && firstDateQsIdx.equals(secondDateQsIdx)) {
			firstQs = inflationQs.get(--firstDateQsIdx);
		} else {
			throw new NotEnoughDataException(inflationQs.getStock(), "No inflation data between : " + firstDate + " and " + secondDate + ": ! " + firstDateQsIdx + "==" + secondDateQsIdx + " && " + firstDateQsIdx + "> 0", null);
		}

		BigDecimal inflationAtFirst = firstQs.getClose();
		BigDecimal inflationAtSecond = secondQs.getClose();
		BigDecimal knowQsInflationRate = inflationAtSecond.subtract(inflationAtFirst).divide(inflationAtFirst, 10, BigDecimal.ROUND_HALF_EVEN);

		long knownQsSpan = TimeUnit.DAYS.convert(secondQs.getDate().getTime() - firstQs.getDate().getTime(), TimeUnit.MILLISECONDS);
		long requestedDatesSpan = TimeUnit.DAYS.convert(secondDate.getTime() - firstDate.getTime(), TimeUnit.MILLISECONDS);

		double inflationRate =  knowQsInflationRate.doubleValue() * ( (double)requestedDatesSpan/ (double)knownQsSpan );

		LOGGER.debug("Inflation rate between " + firstDate + " and " + secondDate + " is : " + inflationRate);
		return BigDecimal.valueOf(inflationRate);

	}

}
