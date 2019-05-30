package com.finance.pms.datasources.web.currency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
import com.finance.pms.events.quotations.SplitData;

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
							return Math.abs(Math.log(quotations.get(i).getCloseRaw().doubleValue()/quotations.get(i-1).getCloseRaw().doubleValue())) > Math.log(cFactDelta);
						})
						.mapToObj(i -> {//Returns the qUs to be change by the CurrencyFactor
							return currencyFatcorAdjacents(i, quotations, cFactDelta);
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

	private List<QuotationUnit> currencyFatcorAdjacents(int i, Quotations quotations, double cFactDelta) {
		List<QuotationUnit> adjacents = new ArrayList<>();
		double qI = quotations.get(i).getCloseRaw().doubleValue();
		double qIm1 = quotations.get(i-1).getCloseRaw().doubleValue();
		if (qI > qIm1*cFactDelta) {
			int j = i-1;
			while(j >= 0 && (qI > quotations.get(j).getCloseRaw().doubleValue()*cFactDelta)) {
				adjacents.add(quotations.get(j));
				j--;
			}
			//LOGGER.info("Escape condition for " + stock + " : " + quotations.get(i) +" > "+ quotations.get(j+1)*cFactDelta+ " with " + quotations.get(i) + " and " + quotations.get(j+1));
		}
		else {
			int j = i;
			while(j < quotations.size() && (qIm1 > quotations.get(j).getCloseRaw().doubleValue()*cFactDelta)) {
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
									qU.getOpenRaw().multiply(cF),
									qU.getHighRaw().multiply(cF),
									qU.getLowRaw().multiply(cF),
									qU.getCloseRaw().multiply(cF),
									qU.getVolumeRaw(),
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

	/**
	 * 
	 * @param loadShares
	 * @param deltaUnit ex : 0.5 (i.e. 50%)
	 * @return
	 */
	public List<Optional<List<QuotationUnit>>> checkCounterSplit(List<Stock> loadShares, double deltaUnit) {

		List<Optional<List<QuotationUnit>>> collected = loadShares.stream().map(stock -> {

			List<QuotationUnit> collect = null;
			try {
				Quotations quotations = QuotationsFactories.getFactory().getRawQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);

				collect = IntStream
						.range(1, quotations.size())
						.mapToObj(i -> {
							QuotationUnit qIm1 = quotations.get(i-1);
							QuotationUnit qI = quotations.get(i);
//							double span = Math.min(5, QuotationsFactories.getFactory().nbOpenIncrementBetween(qIm1.getDate(), qI.getDate()));
//							double dI = qI.getCloseRaw().doubleValue();
//							double dIm1 = qIm1.getCloseRaw().doubleValue();
//							double delta = span*Math.pow(1.5, 1d-span)*deltaUnit;
//							double adjustedDIm1 = dIm1 + dIm1*delta;
//							double counterSplit = dI/adjustedDIm1;
							SplitData counterSplitData = Quotations.calculateSplit(qI.getDate(), qI.getCloseRaw().doubleValue(), qIm1.getDate(), qIm1.getCloseRaw().doubleValue());
							double counterSplit = counterSplitData.getSplit();

							List<QuotationUnit> adjacents = new ArrayList<>();
							if ( counterSplit > 1 ) {
								int iPrim = i;
								//We check adjacent forward until condition is false
								while(iPrim < quotations.size() && (counterSplit > 1)) {
									//FIXME counterSplit = quotations.get(iPrim).getCloseRaw().doubleValue()/adjustedDIm1;
									adjacents.add(quotations.get(iPrim));
									iPrim++;
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

		LOGGER.info("Affected stocks: " + collected.stream().filter(qL -> qL.isPresent() && qL.get().size() > 0).count() + " out of " + loadShares.size());
		String counterSplitedStocks = collected.stream()
				.flatMap(qL -> (qL.isPresent())?qL.get().stream():Stream.empty())
				.map(qU -> qU.getStock().toString())
				.distinct()
				.reduce((r, e) -> r + "\n" + e)
				.orElse("None");
		LOGGER.info("Affected stocks: \n" + counterSplitedStocks);
		try (FileWriter fileWriter = new FileWriter(new File(System.getProperty("installdir") + File.separator + "tmp" + File.separator + UUID.randomUUID() + "_counterSplitedStocks.txt"))) {
			fileWriter.write(counterSplitedStocks+"\n");
		} catch (IOException e) {
			LOGGER.error(e, e);
		}

		return collected;
	}

	public List<Optional<List<QuotationUnit>>> checkCounterSplitFailFast(List<Stock> loadShares, double deltaUnit) {

		List<Optional<List<QuotationUnit>>> collected = loadShares.stream().map(stock -> {

			List<QuotationUnit> collect = null;
			try {
				Quotations quotations = QuotationsFactories.getFactory().getRawQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);

				collect = IntStream
						.range(1, quotations.size())
						.mapToObj(i -> {
							QuotationUnit qIm1 = quotations.get(i-1);
							QuotationUnit qI = quotations.get(i);
//							double span = Math.min(5, QuotationsFactories.getFactory().nbOpenIncrementBetween(qIm1.getDate(), qI.getDate()));
//							double delta = span*Math.pow(1.5, 1d-span)*deltaUnit;
//							double dI = qI.getCloseRaw().doubleValue();
//							double dIm1 = qIm1.getCloseRaw().doubleValue();
//							double adjustedDIm1 = dIm1 + dIm1*delta;
//							double counterSplit = dI/adjustedDIm1;
							SplitData counterSplitData = Quotations.calculateSplit(qI.getDate(), qI.getCloseRaw().doubleValue(), qIm1.getDate(), qIm1.getCloseRaw().doubleValue());
							double counterSplit = counterSplitData.getSplit();
							List<QuotationUnit> adjacents = new ArrayList<>();
							if ( counterSplit > 1 ) {
								adjacents.add(qI);
							}
							return adjacents;
						})
						.flatMap(adj -> adj.stream())
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

		LOGGER.info("Affected stocks: " + collected.stream().filter(qL -> qL.isPresent() && qL.get().size() > 0).count() + " out of " + loadShares.size());
		LOGGER.info("Affected stocks: \n" +
				collected.stream()
		.flatMap(qL -> (qL.isPresent())?qL.get().stream():Stream.empty())
		.map(qU -> qU.getStock().toString())
		.distinct()
		.reduce((r, e) -> r + "\n" + e)
		.orElse("None")
				);

		return collected;
	}

	@Deprecated //This won't work? <= instead we exclude these from calculations
	public void fixCounterSplit(List<Stock> loadShares) {

		List<Optional<List<QuotationUnit>>> check = checkCounterSplit(loadShares, 0.5);

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
									qU.getOpenRaw(),
									qU.getHighRaw(),
									qU.getLowRaw(),
									qU.getCloseRaw(),
									qU.getVolumeRaw(),
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
