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
import com.finance.pms.datasources.db.DataSource.SHARES;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareFilter;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.QuotationUnit.ORIGIN;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class PennyCurrencyFixer {

	private static MyLogger LOGGER = MyLogger.getLogger(PennyCurrencyFixer.class);

	public List<Optional<List<QuotationUnit>>> check() {
		List<Stock> loadShares = DataSource.getInstance().getShareDAO().loadShares(new ShareFilter() {
			@Override
			public String getRequestConstraint(String... params) {
				return " "+SHARES.CURRENCY+" = '"+Currency.GBP.name()+"' AND "+SHARES.CURRENCYFACTOR+" > 1";
			}
		});

		//loadShares = loadShares.subList(0, 20); //Test
		//loadShares = loadShares.stream().filter(s -> s.getSymbol().equals("IGLT.L")).collect(Collectors.toList()); //Test

		List<Optional<List<QuotationUnit>>> map = loadShares.stream().map(stock -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, Currency.NAN, 0, ValidityFilter.CLOSE);
				double cFactDelta = stock.getMarketValuation().getCurrencyFactor().doubleValue()*0.50; //This is assuming the currency factor is > qU*1.50 (arbitrary daily% return maximum)

				List<QuotationUnit> match = IntStream
						.range(1, quotations.size())
						.filter(i -> {
							return Math.abs(Math.log(quotations.get(i).getClose().doubleValue()/quotations.get(i-1).getClose().doubleValue())) > Math.log(cFactDelta);
						})
						.mapToObj(i -> {//Returns the qU to be change by the CurrencyFactor
							List<QuotationUnit> adjacents = new ArrayList<>();
							double qI = quotations.get(i).getClose().doubleValue();
							double qIm1 = quotations.get(i-1).getClose().doubleValue();
							if (qI > qIm1*cFactDelta) {
								//adjacents.add(quotations.get(i-1));
								int j = i-1;
								while(j >= 0 && (qI > quotations.get(j).getClose().doubleValue()*cFactDelta)) {
									adjacents.add(quotations.get(j));
									j--;
								}
								//LOGGER.info("Escape condition for " + stock + " : " + quotations.get(i) +" > "+ quotations.get(j+1)*cFactDelta+ " with " + quotations.get(i) + " and " + quotations.get(j+1));
							}
							else {
								//adjacents.add(quotations.get(i));
								int j = i;
								while(j < quotations.size() && (qIm1 > quotations.get(j).getClose().doubleValue()*cFactDelta)) {
									adjacents.add(quotations.get(j));
									j++;
								}
							}
							return adjacents;
						})
						.flatMap(adj -> adj.stream())
						.distinct()
						.collect(Collectors.toList());

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

	public void fix() {

		List<Optional<List<QuotationUnit>>> check = check();

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
//							qU.setOpen(qU.getOpen().multiply(cF));
//							qU.setHigh(qU.getHigh().multiply(cF));
//							qU.setLow(qU.getLow().multiply(cF));
//							qU.setClose(qU.getClose().multiply(cF));
//							qU.setOrigin(ORIGIN.USER);
//							return qU;
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

}
