package com.finance.pms.screening;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.quotations.NoQuotationsException;
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
	private final String VOLATILITIES_CSV = "volatilities.csv";

	@Autowired
	ShareDAO shareDAO;

	@Autowired
	PortfolioDAO portfolioDAO;

	void generateFromFileLMHToDB(String volatiliesCsvPath, Currency currency, int nbRows) throws Exception {

		File volatilitiesCsv = new File(volatiliesCsvPath);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if (existsCsvFile) {
			List<Entry<Stock, Double[]>> sorted = uploadFromFile(volatilitiesCsv);
			List<Entry<Stock, Double[]>> entries = filterSubListSameCurrency(currency, sorted);
			exportToLowMedHighVolsShareLists(currency.name(), nbRows, entries);
		}

	}

	private List<Entry<Stock, Double[]>> filterSubListSameCurrency(Currency currency, List<Entry<Stock, Double[]>> sorted) throws IOException {
		return sorted.stream().filter(e -> e.getKey().getMarketValuation().getCurrency().equals(currency)).collect(Collectors.toList());
	}

	/**
	 * Generate a volatilities file around the reference stock volatility using existing calculations
	 * @param referenceStock
	 * @param nbRows
	 * @param supportFile
	 * @param sameCurrency 
	 * @throws Exception
	 */
	void generateFromFileInRangeOfToFile(String volatiliesCsvPath, Stock referenceStock, Boolean sameCurrency, int nbRows, String supportFile) throws Exception {

		File volatilitiesCsv = new File(volatiliesCsvPath);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if (existsCsvFile) {
			List<Entry<Stock, Double[]>> sorted = uploadFromFile(volatilitiesCsv);
			List<Entry<Stock, Double[]>> entries = filterSubListInRange(referenceStock, nbRows, sorted);
			if (sameCurrency) entries = filterSubListSameCurrency(referenceStock.getMarketValuation().getCurrency(), entries);
			exportToFile(referenceStock.getFriendlyName(), supportFile, entries);
		}

	}

	private List<Entry<Stock, Double[]>> filterSubListInRange(Stock referenceStock, int nbRows, List<Entry<Stock, Double[]>> sorted) throws IOException {

		//Find i
		int i = 0;
		Iterator<Entry<Stock, Double[]>> iterator = sorted.iterator();
		while(iterator.hasNext() && !referenceStock.equals(iterator.next().getKey())) i++;
		if (!referenceStock.equals(sorted.get(i).getKey())) throw new IOException(referenceStock+" not found in calculated volatilities. Please rerun generateNewCalculationFiltered?");

		//SubList
		return sorted.subList(Math.max(0, i - nbRows/2), Math.min(i + nbRows/2, sorted.size()));

	}

	private void exportToFile(String title, String supportFilePath, List<Entry<Stock, Double[]>> subList) throws IOException {

		String reduce = subList.stream().map(e -> e.getKey().getSymbol() + " " + e.getKey().getIsin()).reduce(title, (r, e) -> r + "," + e);
		File supportsFile = new File(NEURAL_PATH + UUID.randomUUID() + "_" + supportFilePath);
		Files.write(supportsFile.toPath(), Arrays.asList(new String[]{reduce}), Charset.defaultCharset());
	}

	/**
	 * Will create or recreate 3 portfolios of low, medium and high volatilities using pre calculated volatilities
	 * @param nbRows
	 * @throws Exception
	 */
	public void generateFromFileLMHToDB(String volatiliesCsvPath, int nbRows) {
		File volatilitiesCsv = new File(volatiliesCsvPath);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if (existsCsvFile) {
			List<Entry<Stock, Double[]>> sorted = uploadFromFile(volatilitiesCsv);
			//Create Indep Portfolios
			exportToLowMedHighVolsShareLists("MISCELLANEOUS", nbRows, sorted);
		}
	}

	private List<Entry<Stock, Double[]>> uploadFromFile(File volatilitiesCsv) {

		Map<Stock, Double[]> stockVolatilities = new HashMap<>();

		try(BufferedReader fileReader = new BufferedReader(new FileReader(volatilitiesCsv))) {

			String line = null;
			while((line = fileReader.readLine()) != null) {
				String[] lineSplit = line.split(",");
				String symbol = lineSplit[0].trim();
				String isin = lineSplit[1].trim();
				Stock stock = shareDAO.loadStockBy(symbol, isin);
				LOGGER.info("Adding " + stock + " with velocities " + lineSplit[2] + " and " + lineSplit[3]);
				stockVolatilities.put(stock, new Double[]{Double.valueOf(lineSplit[2].trim()), Double.valueOf(lineSplit[3].trim())});
			}

		} catch (Exception e1) {
			LOGGER.error(e1);
		}
		return sortVolatilities(stockVolatilities);

	}

	void generateNewCalculationFilteredToFile() throws Exception {

		List<Predicate<Stock>> predicates = new ArrayList<Predicate<Stock>>();

		//n years ago
		{
			Date nYearsAgo = Date.from(LocalDate.now().minus(Period.ofYears(10)).atStartOfDay(ZoneId.systemDefault()).toInstant());
			Predicate<Stock> predicate = s -> {
				Boolean match = DataSource.getInstance().getFirstQuotationDateFromQuotations(s).before(nYearsAgo);
				if (!match) LOGGER.info(s + " does not match 'before " + nYearsAgo + " years ago' predicate.");
				return match;
			};
			predicates.add(predicate);
		}
		//daily change max
		{
			Predicate<Stock> predicate = stock -> {
				try {
					//We use the values before split as counter split are messing up data split fixes
					Quotations quotations = QuotationsFactories.getFactory().getRawQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 900, ValidityFilter.CLOSE);
					SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
					List<Double> values = new ArrayList<>(closeQuotations.values());
					List<Date> keys = new ArrayList<>(closeQuotations.keySet());
					double maxReturn = Math.log(200d/100d);
					Boolean match = IntStream
							.range(1, closeQuotations.size())
							.allMatch(i -> {
								//Daily change <= 100%
								//Boolean hasHugeDailyChange = Math.abs(Math.log(values.get(i)/values.get(i-1))) <= maxReturn;

								//Change <= 100% with an exponentially decreasing daily increase maximum estimated starting from 10%.
								//Also called CounterSplitPattern as in QuotationFixer.
								double span = Math.min(5, QuotationsFactories.getFactory().nbOpenIncrementBetween(keys.get(i-1), keys.get(i)));
								double delta = span*Math.pow(1.5, 1d-span)*0.10;
								double valueIm1 = values.get(i-1);
								double valueI = values.get(i);
								double adjustedDIm1 = valueIm1 + valueIm1*delta;
								double counterSplit = valueI/adjustedDIm1;
								Boolean noCounterSplitPattern = counterSplit <= 2;
								return noCounterSplitPattern;

							});
					if (!match) LOGGER.info(stock + " does not match 'daily change max < " + maxReturn + "' predicate.");
					return match;
				} catch (NoQuotationsException e) {
					LOGGER.warn(e);
					return false;
				} catch (Exception e) {
					LOGGER.error(e);
					return false;
				}
			};
			predicates.add(predicate);
		}

		calculateFor(loadStocks(predicates), DateFactory.dateAtZero(), new Date());

	}

	List<Entry<Stock, Double[]>> calculateFor(List<Stock> allStocks, Date start, Date end) throws Exception {

		Map<Stock, Double[]> stockVolatilities = allStocks.stream().collect(Collectors.toMap(s -> s, s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getQuotationsInstance(s, start, end, true, s.getMarketValuation().getCurrency(), 900, ValidityFilter.CLOSE);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
				HistoricalVolatilityCalculator historicalVolatilityCalculator = new HistoricalVolatilityCalculator(closeQuotations);
				Double averageAnnualisedVolatility = historicalVolatilityCalculator.averageAnnualisedVolatility(0, closeQuotations.size());
				Double threeMonthAnnualisedVolatility = historicalVolatilityCalculator.annualisedVolatilityAt(closeQuotations.size()-1);
				return new Double[]{averageAnnualisedVolatility, threeMonthAnnualisedVolatility};
			} catch (Exception e) {
				LOGGER.warn(s +" volatility failed :" + e.toString());
				return new Double[] {Double.NaN, Double.NaN};
			}
		}));

		//Sort
		List<Entry<Stock, Double[]>> sorted = sortVolatilities(stockVolatilities);

		//Export to "volatilities.csv"
		try (FileWriter fileWriter = new FileWriter(new File(NEURAL_PATH + UUID.randomUUID() + "_" + VOLATILITIES_CSV))) {
			sorted.stream()
			.forEach(e -> {
				try {
					Stock key = e.getKey();
					String line = 
							key.getSymbol() + ", " + key.getIsin() + ", " +
									Arrays.toString(e.getValue()).replace("[", "").replace("]", "");
					//Arrays.stream(e.getValue()).map(eOe -> eOe.toString()).reduce((r, eOe) -> r + ", " + eOe);
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

	private List<Entry<Stock, Double[]>> sortVolatilities(Map<Stock, Double[]> stockVolatilities) {
		List<Entry<Stock, Double[]>> sorted = stockVolatilities.entrySet().stream()
				.filter(e -> !Double.isNaN(e.getValue()[0]))
				.sorted((e0, e1) -> e0.getValue()[0].compareTo(e1.getValue()[0]))
				.collect(Collectors.toList());
		return sorted;
	};


	private void exportToLowMedHighVolsShareLists(String listMName, int nbRows, List<Entry<Stock, Double[]>> sorted) {
		createOnePortfolioShareList(sorted, sorted.size()/2 - nbRows/2, sorted.size()/2 + nbRows/2, "VOLATILITY,MEDIUMVOLATILITY:" + listMName);
		createOnePortfolioShareList(sorted, 0, nbRows, "VOLATILITY,LOWVOLATILITY:" + listMName);
		createOnePortfolioShareList(sorted, sorted.size() - nbRows, sorted.size(), "VOLATILITY,HIGHVOLATILITY:" + listMName);
	}

	private void createOnePortfolioShareList(List<Entry<Stock, Double[]>> sorted, int from, int to, String shareListName) {

		List<Entry<Stock, Double[]>> volatilitiesSubSet = sorted.subList(from, to);

		LOGGER.info("Range of " + shareListName + ": " + from + ", " + to);

		IndepShareList shareList = portfolioDAO.loadIndepShareList(shareListName);
		shareList.getListShares().clear();
		volatilitiesSubSet.stream().forEach(e -> {
			shareList.addShare(e.getKey());
		});
		portfolioDAO.saveOrUpdatePortfolio(shareList);

	}

	private List<Stock> loadStocks(List<Predicate<Stock>> predicates) {
		List<Stock> allStocks = shareDAO.loadAllStocks();

		List<Stock> filtered = allStocks.stream()
				.filter(s -> predicates.stream().allMatch(p -> p.test(s)))
				.collect(Collectors.toList());

		LOGGER.info("Valid stock filtered : " + filtered.size() + " out of " + allStocks.size());

		return filtered;
	}

}
