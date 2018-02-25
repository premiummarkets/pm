package com.finance.pms.screening;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;
import com.finance.pms.portfolio.IndepShareList;
import com.finance.pms.portfolio.PortfolioDAO;

@Component
public class VolatilityClassifier {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityClassifier.class);

	@Autowired
	ShareDAO shareDAO;

	@Autowired
	PortfolioDAO portfolioDAO;

	public void generate(List<Stock> allStocks, Date start, Date end) {

		Map<Stock, Double[]> stockValatilities = allStocks.stream().collect(Collectors.toMap(s -> s, s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(s, start, end, true, s.getMarketValuation().getCurrency(), 900, ValidityFilter.OHLCV);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size() -1);
				HistoricalVolatilityCalculator historicalVolatilityCalculator = new HistoricalVolatilityCalculator(closeQuotations);
				Double averageAnnualisedVolatility = historicalVolatilityCalculator.averageAnnualisedVolatility(0, quotations.size());
				Double threeMonthAnnualisedVolatility = historicalVolatilityCalculator.annualisedVolatilityAt(quotations.size()-1);
				return new Double[]{averageAnnualisedVolatility, threeMonthAnnualisedVolatility};
			} catch (Exception e) {
				LOGGER.warn(s, e);
				return new Double[] {Double.NaN, Double.NaN};
			}
		}));

		List<Entry<Stock, Double[]>> sorted = stockValatilities.entrySet().stream()
				.filter(e -> !Double.isNaN(e.getValue()[0]))
				.sorted((e0, e1) -> e0.getValue()[0].compareTo(e1.getValue()[0]))
				.collect(Collectors.toList());

		try (FileWriter fileWriter = new FileWriter(new File("volatilities.csv"))) {
			sorted.stream()
			.forEach(e -> {
				try {
					String line = e.getKey().getSymbol() + ", " + Arrays.toString(e.getValue()).replace("[", "").replace("]", "");
					fileWriter.write(line+"\n");
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
			});
		} catch (Exception e1) {
			LOGGER.error(e1);
		}

		createPortfolioList(sorted, sorted.size()/2 - 50, sorted.size()/2 + 50, "VOLATILITY,Miscellaneous:MediumVolatility");
		createPortfolioList(sorted, 0, 100, "VOLATILITY,Miscellaneous:LowVolatility");
		createPortfolioList(sorted, sorted.size() - 100, sorted.size(), "VOLATILITY,Miscellaneous:HighVolatility");

	};

	//TODO Reset Share list portfolios and IndepShareList + move INDICES in this category
	private void createPortfolioList(List<Entry<Stock, Double[]>> sorted, int from, int to, String shareListName) {

		List<Entry<Stock, Double[]>> volatilitiesSubSet = sorted.subList(from, to);

		LOGGER.info("Range of "+shareListName+": "+from+", "+to);

		IndepShareList shareList = portfolioDAO.loadIndepShareList(shareListName);
		shareList.getListShares().clear();
		volatilitiesSubSet.stream().forEach(e -> {
			shareList.addShare(e.getKey());
		});
		portfolioDAO.saveOrUpdatePortfolio(shareList);
	}

}
