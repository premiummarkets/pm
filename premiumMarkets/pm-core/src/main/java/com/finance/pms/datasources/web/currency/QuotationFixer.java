package com.finance.pms.datasources.web.currency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class QuotationFixer {

	private static MyLogger LOGGER = MyLogger.getLogger(QuotationFixer.class);

	public List<Optional<List<QuotationUnit>>> checkPennyPound(List<Stock> loadShares) {
		List<Optional<List<QuotationUnit>>> map = loadShares.stream().map(stock -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);
				double cFactDelta = stock.getMarketValuation().getCurrencyFactor().doubleValue()*0.50; //This is assuming the currency factor is > qU*1.50 (arbitrary daily% return maximum)

				List<QuotationUnit> match = IntStream
						.range(1, quotations.size())
						.filter(i -> {
							return Math.abs(Math.log(quotations.get(i).getClose().doubleValue()/quotations.get(i-1).getClose().doubleValue())) > Math.log(cFactDelta);
						})
						.mapToObj(i -> {//Returns the qUs to be change by the CurrencyFactor
							return adjacents(i, quotations, cFactDelta);
						})
						.flatMap(adj -> adj.stream())
						.distinct()
						.collect(Collectors.toList());

				if (!match.isEmpty())
				LOGGER.warn("Invalid qU for " + stock + " :\n" + match.stream().map(qU -> qU.toString()).reduce((r, e) -> r + "\n" + e).orElse("None"));

				return Optional.of(match);

			} catch (NoQuotationsException e) {
				LOGGER.warn(e);
			} catch (Exception e) {
				LOGGER.error(e);
			}

			Optional<List<QuotationUnit>> empty = Optional.empty();
			return empty;
		})
				.collect(Collectors.toList());

		return map;
	}

	private List<QuotationUnit> adjacents(int i, Quotations quotations, double cFactDelta) {
		List<QuotationUnit> adjacents = new ArrayList<>();
		double qI = quotations.get(i).getClose().doubleValue();
		double qIm1 = quotations.get(i-1).getClose().doubleValue();
		if (qI > qIm1*cFactDelta) {
			int j = i-1;
			while(j >= 0 && (qI > quotations.get(j).getClose().doubleValue()*cFactDelta)) {
				adjacents.add(quotations.get(j));
				j--;
			}
			//LOGGER.info("Escape condition for " + stock + " : " + quotations.get(i) +" > "+ quotations.get(j+1)*cFactDelta+ " with " + quotations.get(i) + " and " + quotations.get(j+1));
		}
		else {
			int j = i;
			while(j < quotations.size() && (qIm1 > quotations.get(j).getClose().doubleValue()*cFactDelta)) {
				adjacents.add(quotations.get(j));
				j++;
			}
		}
		return adjacents;
	}

	public void fixPennyPound(List<Stock> loadShares) {

		List<Optional<List<QuotationUnit>>> check = checkPennyPound(loadShares);

		check.stream()
		.forEach(qUList -> {
			List<QuotationUnit> orElse = qUList.orElse(new ArrayList<QuotationUnit>());
			if (!orElse.isEmpty()) {

				QuotationUnit firstQU = orElse.get(0);
				Stock stock = firstQU.getStock();
				Quotations.refreshCaches(stock);

				List<QuotationUnit> collect = orElse.stream()
						.map(qU -> {
							BigDecimal cF = stock.getMarketValuation().getCurrencyFactor();
							QuotationUnit nqU = new QuotationUnit(stock, qU.getCurrency(), qU.getDate(),
									qU.getOpen().multiply(cF),
									qU.getHigh().multiply(cF),
									qU.getLow().multiply(cF),
									qU.getClose().multiply(cF),
									qU.getVolume(),
									ORIGIN.USER, BigDecimal.ONE);
							return nqU;
						})
						.collect(Collectors.toList());

				DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnits(collect);

				stock.setOverrideUserQuotes(false);
				DataSource.getInstance().getShareDAO().saveOrUpdateStock(stock);
			}

		});

	}

	public List<Optional<List<QuotationUnit>>> checkAntiSplit(List<Stock> loadShares) {

		return loadShares.stream().map(stock -> {

			List<QuotationUnit> collect = null;
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);

				collect = IntStream
						.range(1, quotations.size())
						.mapToObj(i -> {
							QuotationUnit qjm1 = quotations.get(i-1);
							QuotationUnit qj = quotations.get(i);
							double span = Math.min(5, QuotationsFactories.getFactory().nbOpenIncrementBetween(qjm1.getDate(), qj.getDate()));
							double dj = qj.getClose().doubleValue();
							double djm1 =  qjm1.getClose().doubleValue();
							double delta = span*Math.pow(1.5, 1d-span)*0.10;
							double antiSplit = dj/(djm1-djm1*delta);//djm1/(dj-dj*delta);

							List<QuotationUnit> adjacents = new ArrayList<>();
							if ( antiSplit > 2 ) {
								double dI = quotations.get(i).getClose().doubleValue();
								double dIm1 = quotations.get(i-1).getClose().doubleValue();
								if (dI/(dIm1-dIm1*delta) > 2) {
									int j = i;
									while(j < quotations.size()) {
										double dJ = quotations.get(j).getClose().doubleValue();
										if (dJ/(dIm1-dIm1*delta) > 2) {
											adjacents.add(quotations.get(j));
											j++;
										} else {
											break;
										}
									}
								}
							}
							return adjacents;
						})
						.flatMap(adj -> adj.stream())
						.distinct()
						.collect(Collectors.toList());

				if (!collect.isEmpty())
				LOGGER.warn("Anti split detected qU for " + stock + " :\n" + collect.stream().map(qU -> qU.toString()).reduce((r, e) -> r + "\n" + e).orElse("None"));

			} catch (NoQuotationsException e) {
				LOGGER.warn(e);
			} catch (Exception e) {
				LOGGER.error(e);
			}

			return Optional.ofNullable(collect);
		})
				.collect(Collectors.toList());
	}

	public void fixAntiSplit(List<Stock> loadShares) {

		List<Optional<List<QuotationUnit>>> check = checkAntiSplit(loadShares);

		check.stream()
		.forEach(qUList -> {
			List<QuotationUnit> orElse = qUList.orElse(new ArrayList<QuotationUnit>());
			if (!orElse.isEmpty()) {
				QuotationUnit firstQU = orElse.get(0);
				Stock stock = firstQU.getStock();
				Quotations.refreshCaches(stock);

				List<QuotationUnit> webQUs = orElse.stream().collect(Collectors.toList());

				List<QuotationUnit> delQUs = orElse.stream()
						.map(qU -> {
							QuotationUnit delQU = new QuotationUnit(stock, qU.getCurrency(), qU.getDate(),
									qU.getOpen(),
									qU.getHigh(),
									qU.getLow(),
									qU.getClose(),
									qU.getVolume(),
									ORIGIN.DEL, BigDecimal.ONE);
							return delQU;
						})
						.collect(Collectors.toList());

				LOGGER.info("Quotations to be marked deleted: " + delQUs);
				DataSource.getInstance().getShareDAO().saveOrUpdateQuotationUnits(delQUs);
				LOGGER.info("Quotations to effectivelly delete (replaced by marked deleted): " + webQUs);
				DataSource.getInstance().getShareDAO().deleteQuotationUnits(webQUs);
			}
		});

	}

}
