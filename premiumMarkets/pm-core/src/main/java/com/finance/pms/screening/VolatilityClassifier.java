package com.finance.pms.screening;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.finance.pms.events.calculation.DateFactory;
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

	private final String NEURAL_PATH = System.getProperty("installdir") + File.separator + "neural" + File.separator;
	private final String VOLATILITIES_CSV = NEURAL_PATH + "volatilities.csv";

	@Autowired
	ShareDAO shareDAO;

	@Autowired
	PortfolioDAO portfolioDAO;

	public void generateSupportsInRangeOf(Stock referenceStock, int threshold, String supportFile) throws Exception {

		List<Entry<Stock, Double[]>> sorted;

		File volatilitiesCsv = new File(VOLATILITIES_CSV);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if(existsCsvFile) {
			sorted = uploadFromFile(volatilitiesCsv);
		} else {
			sorted = regenerate(shareDAO.loadAllStocks(), DateFactory.dateAtZero(), new Date());
		}

		//Find i
		int i = 0;
		Iterator<Entry<Stock, Double[]>> iterator = sorted.iterator();
		while(iterator.hasNext() && !referenceStock.equals(iterator.next().getKey())) i++;
		if (!referenceStock.equals(sorted.get(i).getKey())) throw new IOException(referenceStock+" not found in volatilities:"+VOLATILITIES_CSV+". Please regenerate.");

		//Export subList
		List<Entry<Stock, Double[]>> subList = sorted.subList(Math.max(0, i - threshold/2), Math.min(i + threshold/2, sorted.size()));
		String reduce = subList.stream().map(e -> e.getKey().getSymbol() + " " + e.getKey().getIsin()).reduce(referenceStock.getFriendlyName(), (r, e) -> r + "," + e);

		File supportsFile = new File(NEURAL_PATH + supportFile);
		Files.write(supportsFile.toPath(), Arrays.asList(new String[]{reduce}), Charset.defaultCharset());

	}

	public void generateVolatilitiesPortfolios(int threshold) throws Exception {

		List<Entry<Stock, Double[]>> sorted;

		File volatilitiesCsv = new File(VOLATILITIES_CSV);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if(existsCsvFile) {
			sorted = uploadFromFile(volatilitiesCsv);
		} else {
			sorted = regenerate(shareDAO.loadAllStocks(), DateFactory.dateAtZero(), new Date());
		}

		//Create Indep Portfolios
		createPortfolioList(sorted, sorted.size()/2 - threshold/2, sorted.size()/2 + threshold/2, "VOLATILITY,MEDIUMVOLATILITY:MISCELLANEOUS");
		createPortfolioList(sorted, 0, threshold, "VOLATILITY,LOWVOLATILITY:MISCELLANEOUS");
		createPortfolioList(sorted, sorted.size() - threshold, sorted.size(), "VOLATILITY,HIGHVOLATILITY:MISCELLANEOUS");
	}

	private List<Entry<Stock, Double[]>> uploadFromFile(File volatilitiesCsv) {

		Map<Stock, Double[]> stockValatilities = new HashMap<>();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(volatilitiesCsv))) {

			String line = null;
			while((line = fileReader.readLine()) != null) {
				String[] lineSplit = line.split(",");
				String symbol = lineSplit[0].trim();
				String isin = lineSplit[1].trim();
				Stock stock = shareDAO.loadStockBy(symbol, isin);
				stockValatilities.put(stock, new Double[]{Double.valueOf(lineSplit[2].trim()), Double.valueOf(lineSplit[3].trim())});
			}

		} catch (Exception e1) {
			LOGGER.error(e1);
		}
		return sortVolatilities(stockValatilities);

	}

	public List<Entry<Stock, Double[]>> regenerate(List<Stock> allStocks, Date start, Date end) throws Exception {

		Map<Stock, Double[]> stockValatilities = allStocks.stream().collect(Collectors.toMap(s -> s, s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(s, start, end, true, s.getMarketValuation().getCurrency(), 900, ValidityFilter.OHLCV);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size() -1);
				HistoricalVolatilityCalculator historicalVolatilityCalculator = new HistoricalVolatilityCalculator(closeQuotations);
				Double averageAnnualisedVolatility = historicalVolatilityCalculator.averageAnnualisedVolatility(0, quotations.size());
				Double threeMonthAnnualisedVolatility = historicalVolatilityCalculator.annualisedVolatilityAt(quotations.size()-1);
				return new Double[]{averageAnnualisedVolatility, threeMonthAnnualisedVolatility};
			} catch (Exception e) {
				LOGGER.warn(s +" volatility failed :" + e.toString());
				return new Double[] {Double.NaN, Double.NaN};
			}
		}));

		//Sort
		List<Entry<Stock, Double[]>> sorted = sortVolatilities(stockValatilities);

		//Export to "volatilities.csv"
		try (FileWriter fileWriter = new FileWriter(new File(VOLATILITIES_CSV))) {
			sorted.stream()
			.forEach(e -> {
				try {
					Stock key = e.getKey();
					String line = key.getSymbol() + ", " + key.getIsin() + ", " + Arrays.toString(e.getValue()).replace("[", "").replace("]", "");
					fileWriter.write(line+"\n");
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
			});
		} catch (Exception e1) {
			LOGGER.error(e1);
			throw e1;
		}

		return sorted;
	}

	private List<Entry<Stock, Double[]>> sortVolatilities(Map<Stock, Double[]> stockValatilities) {
		List<Entry<Stock, Double[]>> sorted = stockValatilities.entrySet().stream()
				.filter(e -> !Double.isNaN(e.getValue()[0]))
				.sorted((e0, e1) -> e0.getValue()[0].compareTo(e1.getValue()[0]))
				.collect(Collectors.toList());
		return sorted;
	};

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