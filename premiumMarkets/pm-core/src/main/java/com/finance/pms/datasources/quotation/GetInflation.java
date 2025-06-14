package com.finance.pms.datasources.quotation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
			this.inflationQs = QuotationsFactories.getFactory().getRawQuotationsInstance(
					inflationStock, DateFactory.dateAtZero(), DateFactory.getNowEndDate(),
					true, Currency.NAN, 1, Quotations.SplitOption.RAW, Quotations.ValidityFilter.CLOSE);
		} catch (NoQuotationsException e) {
			LOGGER.error(e);
		}
	}

	public BigDecimal inflationRateWithinDateRange(Date firstDate, Date secondDate) throws NotEnoughDataException {

		if (inflationQs == null) init();

		Integer secondDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, secondDate);
		QuotationUnit secondQs = inflationQs.get(secondDateQsIdx);
		Integer firstDateQsIdx = inflationQs.getClosestIndexBeforeOrAtDateOrIndexZero(0, firstDate);
		QuotationUnit firstQs;
		if (0 < firstDateQsIdx && firstDateQsIdx < secondDateQsIdx) {
			firstQs = inflationQs.get(firstDateQsIdx);
		} 
		else if (0 < firstDateQsIdx && firstDateQsIdx.equals(secondDateQsIdx)) {
			firstQs = inflationQs.get(--firstDateQsIdx);
		} else {
			throw new NotEnoughDataException(inflationQs.getStock(), "No inflation data between: " + firstDate + " and " + secondDate + ": ! " + firstDateQsIdx + "==" + secondDateQsIdx + " && " + firstDateQsIdx + "> 0", null);
		}

		BigDecimal inflationAtFirst = firstQs.getCloseSplit();
		BigDecimal inflationAtSecond = secondQs.getCloseSplit();
		BigDecimal knowQsInflationRate = inflationAtSecond.subtract(inflationAtFirst).divide(inflationAtFirst, 10, RoundingMode.HALF_EVEN);

		//Update sliding start date
		ZoneId osZoneId = ZoneId.systemDefault();
		// Convert java.util.Date to ZonedDateTime
		ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(firstQs.getDate().toInstant(), osZoneId);
		ZonedDateTime endZonedDateTime = ZonedDateTime.ofInstant(secondQs.getDate().toInstant(), osZoneId);
		Long knownQsSpan = ChronoUnit.DAYS.between(startZonedDateTime, endZonedDateTime);
		
		startZonedDateTime = ZonedDateTime.ofInstant(firstDate.toInstant(), osZoneId);
		endZonedDateTime = ZonedDateTime.ofInstant(secondDate.toInstant(), osZoneId);
		Long requestedDatesSpan = ChronoUnit.DAYS.between(startZonedDateTime, endZonedDateTime);

		double inflationRate =  knowQsInflationRate.doubleValue() * ( (double)requestedDatesSpan/ (double)knownQsSpan );

		if (LOGGER.isDebugEnabled()) LOGGER.debug("Inflation rate between " + firstDate + " and " + secondDate + " is : " + inflationRate);
		return BigDecimal.valueOf(inflationRate);

	}

}
