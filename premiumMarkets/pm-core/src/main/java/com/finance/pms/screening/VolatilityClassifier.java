package com.finance.pms.screening;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;
import com.finance.pms.portfolio.IndepShareList;
import com.finance.pms.portfolio.PortfolioDAO;

@Component
public class VolatilityClassifier {

	private static MyLogger LOGGER = MyLogger.getLogger(VolatilityClassifier.class);

	private final String EXPORT_PATH = System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator;
	private final String TMP_PATH = System.getProperty("installdir") + File.separator + "tmp" + File.separator;
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
			LOGGER.info("Nb of entries for currency " + currency + ": " + entries.size());
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
		File supportsFile = new File(EXPORT_PATH + UUID.randomUUID() + "_" + supportFilePath);
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
		
		Map<String, List<Stock>> invalidityAcc = new HashMap<>();
		Map<Stock, double[]> splitTrace = new HashMap<>();
		
		Boolean allowQuotesUpdate = false;

		List<Predicate<Stock>> predicates = new ArrayList<Predicate<Stock>>();

		//N years minimum length.
		Predicate<Stock> nYearsPredicate = s -> {
			int minYears = 9;
			Boolean match = false;
			for (int i = 0; !match && i <= 1; i++) {
				Date lastQuotationDateFromShares = DataSource.getInstance().getLastQuotationDateFromShares(s);
				Date firstQuotationDateFromQuotations = DataSource.getInstance().getFirstQuotationDateFromQuotations(s);
				Duration diff = Duration.between(new Date(firstQuotationDateFromQuotations.getTime()).toInstant(), new Date(lastQuotationDateFromShares.getTime()).toInstant());
				match = diff.toDays()/365 > minYears;
				if (!allowQuotesUpdate) break;
				if (!match && i < 1) { //Quote updates and then retry
					LOGGER.info(s + " may not be up to date for 'minimum length " + minYears + " years'. Will try again.");
					try {
						QuotationUpdate quotationUpdate = new QuotationUpdate();
						quotationUpdate.getQuotesFor(s);
					} catch (QuotationUpdateException e) {
						LOGGER.warn("Can't update quotation for: " + s + " because " + e);
					}
				}
			}
			if (!match) {
				LOGGER.info(s + " does not match 'minimum length " + minYears + " years'.");
				List<Stock> predicateAcc = invalidityAcc.getOrDefault("nYearsPredicate", new ArrayList<>());
				predicateAcc.add(s);
				invalidityAcc.put("nYearsPredicate", predicateAcc);
			}
			return match;
		};
		
		Predicate<Stock> ohlcPredicate = stock -> {
			try {
				Quotations ohlcQuotations = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				Quotations closeQuotations = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
				double ratio = 0.80;
				boolean match = (double)ohlcQuotations.size()/(double)closeQuotations.size() >= ratio;
				if (!match) {
					LOGGER.info(stock + " does not match 'ohlc validity >= " + ratio + "'.");
					List<Stock> predicateAcc = invalidityAcc.getOrDefault("ohlcPredicate", new ArrayList<>());
					predicateAcc.add(stock);
					invalidityAcc.put("ohlcPredicate", predicateAcc);
				}
				return match;
			} catch (Exception e) {
				LOGGER.error(e);
				return false;
			}
		};	
		
		Predicate<Stock> splitMergePredicate = stock -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
				
				double mergeMax = 100d;
				double splitMax = 100d;
				double maxSplitPerYear = 1d;
				
				List<QuotationUnit> quotationsData = quotations.getQuotationUnits(0, quotations.size()-1);
				List<BigDecimal> splits = quotationsData.stream()
						.map(qu -> qu.getSplit())
						.collect(ArrayList::new, (a, split) -> {
							if (a.isEmpty() || split.compareTo(a.get(a.size()-1)) != 0) {
								a.add(split);
							}
						}, (a,b) -> a.addAll(b));
				double yearsSpan = ((double) java.time.temporal.ChronoUnit.DAYS.between(
						new Date(quotationsData.get(0).getDate().getTime()).toInstant(),
						new Date(quotationsData.get(quotationsData.size()-1).getDate().getTime()).toInstant()))/365d;
				Boolean doNotMatch = 
						((double)splits.size())/yearsSpan > maxSplitPerYear || 
						splits.stream().anyMatch( split -> split.compareTo(new BigDecimal(splitMax)) > 0 || 1d/split.doubleValue() > mergeMax );
				if (doNotMatch) {
					LOGGER.info(
							stock + " with years span " + yearsSpan + " does not match 'splits constrains' " +
							"maxSplitPerYear: " + maxSplitPerYear + ", splitMax:" + splitMax + ", mergeMin:" + 1d/mergeMax + " predicate. " + splits);
					List<Stock> predicateAcc = invalidityAcc.getOrDefault("splitMergeValidity", new ArrayList<>());
					predicateAcc.add(stock);
					invalidityAcc.put("splitMergeValidity", predicateAcc);
				}
				splitTrace.put(stock, new double[] {
							(double)splits.size(), yearsSpan, 
							splits.stream().map(s -> s.doubleValue()).reduce(0d, (a, s) -> {if (s > a) return s; else return a;}),
							splits.stream().map(s -> 1d/s.doubleValue()).reduce(0d, (a, s) -> {if (s > a) return s; else return a;})
						});
				return !doNotMatch;
			} catch (NoQuotationsException e) {
				LOGGER.warn(e);
				return false;
			} catch (Exception e) {
				LOGGER.error(e);
				return false;
			}
		};
//		predicates.add(Last quote was n years ago and minimum length is m years);
		predicates.add(nYearsPredicate);
		predicates.add(ohlcPredicate);
		predicates.add(splitMergePredicate);
//		predicates.add(maxUpPredicate);
//		predicates.add(maxDownPredicate);

		Set<Stock> filteredStocks = filterStocks(predicates);
		
		LOGGER.info("Validitiy accounting: " + invalidityAcc.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())));
		LOGGER.info("Validitiy accounting: " + invalidityAcc);
		LOGGER.info("Split checks:\n"+
		"stock, size, nbyears, split max, merge max\n" +
				splitTrace.entrySet().stream()
				.map(e -> e.getKey().getSymbol() + ", " + Arrays.toString(e.getValue()).substring(1, Arrays.toString(e.getValue()).length()-1) + "\n")
				.reduce("", (a, e) -> a + e));
		
		calculateFor(filteredStocks, DateFactory.dateAtZero(), new Date());

	}

	List<Entry<Stock, Double[]>> calculateFor(Set<Stock> allStocks, Date start, Date end) throws Exception {

		Map<Stock, Double[]> stockVolatilities = allStocks.stream().collect(Collectors.toMap(s -> s, s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(s, start, end, true, s.getMarketValuation().getCurrency(), 1, ValidityFilter.CLOSE);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
				HistoricalVolatilityCalculator historicalVolatilityCalculator = new HistoricalVolatilityCalculator(closeQuotations);
				Double averageAnnualisedVolatility = historicalVolatilityCalculator.averageAnnualisedVolatility(0, closeQuotations.size());
				Double threeMonthAnnualisedVolatility = historicalVolatilityCalculator.movingVolatiltityAt(closeQuotations.size()-1);
				return new Double[]{averageAnnualisedVolatility, threeMonthAnnualisedVolatility};
			} catch (NoQuotationsException e) {
				LOGGER.warn(s + " volatility failed :" + e.toString());
				return new Double[] {Double.NaN, Double.NaN};
			} catch (NotEnoughDataException | IndexOutOfBoundsException e) {
				LOGGER.warn(s + " volatility failed :" + e.toString());
				return new Double[] {Double.NaN, Double.NaN};
			}
		}));

		//Sort
		List<Entry<Stock, Double[]>> sorted = sortVolatilities(stockVolatilities);

		//Export to "volatilities.csv"
		try (FileWriter fileWriter = new FileWriter(new File(EXPORT_PATH + UUID.randomUUID() + "_" + VOLATILITIES_CSV))) {
			sorted.stream()
			.forEach(e -> {
				try {
					Stock key = e.getKey();
					String line = 
							key.getSymbol() + ", " + key.getIsin() + ", " +
									Arrays.toString(e.getValue()).replace("[", "").replace("]", "");
					//Arrays.stream(e.getValue()).map(eOe -> eOe.toString()).reduce((r, eOe) -> r + ", " + eOe);
					fileWriter.write(line + "\n");
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
		int listMiddle = sorted.size()/2;
		int halfRange = nbRows/2;
		createOnePortfolioShareList(sorted, listMiddle - halfRange, listMiddle + halfRange, "VOLATILITY,MEDIUMVOLATILITY:" + listMName);
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

	private Set<Stock> filterStocks(List<Predicate<Stock>> predicates) {

		Set<Stock> allStocks = portfolioDAO.loadPortfolioSharesExUnknown().stream().map(ps -> ps.getStock()).collect(Collectors.toSet());
		//List<Stock> allStocks = shareDAO.loadAllStocks();

		Set<Stock> filtered = allStocks.stream()
				.filter(s -> predicates.stream().allMatch(p -> p.test(s)))
				.collect(Collectors.toSet());
		LOGGER.info("Valid stock filtered : " + filtered.size() + " out of " + allStocks.size());
		String validLines = filtered.stream()
				.map(s -> s.toString())
				.distinct()
				.reduce((r, e) -> r + "\n" + e)
				.orElse("None");
		LOGGER.info("Valid stocks: \n" + validLines);
		try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + UUID.randomUUID() + "_validStocks.txt"))) {
			fileWriter.write(validLines+"\n");
		} catch (IOException e) {
			LOGGER.error(e, e);
		}

		Set<Stock> rejected = allStocks.stream()
				.filter(s -> !filtered.contains(s))
				.collect(Collectors.toSet());
		LOGGER.info("Invalid stocks : " + rejected.size() + " out of " + allStocks.size());
		String invalidLines = rejected.stream()
				.map(s -> s.toString())
				.distinct()
				.reduce((r, e) -> r + "\n" + e)
				.orElse("None");
		LOGGER.info("Affected stocks: \n" + invalidLines);
		try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + UUID.randomUUID() + "_invalidStocks.txt"))) {
			fileWriter.write(invalidLines+"\n");
		} catch (IOException e) {
			LOGGER.error(e, e);
		}

		return filtered;
	}

}
