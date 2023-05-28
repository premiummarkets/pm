package com.finance.pms.screening;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.quotation.GetInflation;
import com.finance.pms.datasources.quotation.QuotationUpdate;
import com.finance.pms.datasources.quotation.QuotationUpdate.QuotationUpdateException;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.ShareDAO;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockList;
import com.finance.pms.events.calculation.DateFactory;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;
import com.finance.pms.events.scoring.functions.HistoricalVolatilityCalculator;
import com.finance.pms.events.scoring.functions.RocSmoother;
import com.finance.pms.portfolio.IndepShareList;
import com.finance.pms.portfolio.PortfolioDAO;


/**
 * From yahoo screener to volatilities beta share list:
 * yahoo screener 
 * --screnner.py/betaScreener (when update is true)--> yahoo beta share list (YAHOOINDICES,BETA:SCREENER)
 * --filterStocks and its predicates filters--> volatilities beta csv 
 * --generateFromFileToDB--> volatilities beta share list (VOLATILITY,BETA:UNKNOWN)
 * 
 * VOLATILITY,BETA:UNKNOWN is a subset of YAHOOINDICES,BETA:SCREENER
 * 
 * @author guil
 *
 */
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
	
	//Predicates
	private Boolean allowQuotesUpdate = false;
	private int minRecentQuoteInDays = 7;
	private int minDataInYears = 10 + 10; //years before input + years input
	private double minValidDataToTimeRatio = 0.80;
	private double minOHLCToQuotesRatio = 0.80;
	
	//Splits and merges predicates
	private double maxMergeValue = 10d;
	private double maxSplitValue = 10d;
	private double maxSplitPerYear = 1d;

	

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
	
	void generateFromFileToDB(String volatiliesCsvPath, String volatilityIndiceName, Double lowerMeanOfReturn, Double higherMeanOfReturn) throws Exception {

		File volatilitiesCsv = new File(volatiliesCsvPath);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if (existsCsvFile) {
			List<Entry<Stock, Double[]>> sorted = uploadFromFile(volatilitiesCsv);
			List<Entry<Stock, Double[]>> filteredMeanOReturn = sorted.stream().filter(e -> e.getValue()[2] >= lowerMeanOfReturn && e.getValue()[2] <= higherMeanOfReturn).collect(Collectors.toList());
			
			//Create Indep Portfolios //XXX sort does not matter here as we pass the whole list.
			createOnePortfolioShareList(filteredMeanOReturn, 0, filteredMeanOReturn.size(), "VOLATILITY," + volatilityIndiceName + ":" + "UNKNOWN");
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
	void generateFromFileInRankOfToFile(String volatiliesCsvPath, Stock referenceStock, Boolean sameCurrency, int nbRows, String supportFile) throws Exception {

		File volatilitiesCsv = new File(volatiliesCsvPath);
		boolean existsCsvFile = volatilitiesCsv.exists();
		if (existsCsvFile) {
			List<Entry<Stock, Double[]>> sorted = uploadFromFile(volatilitiesCsv);
			List<Entry<Stock, Double[]>> entries = filterSubListInRank(referenceStock, nbRows, sorted);
			if (sameCurrency) entries = filterSubListSameCurrency(referenceStock.getMarketValuation().getCurrency(), entries);
			exportToFile(referenceStock.getFriendlyName(), supportFile, entries);
		}

	}

	private List<Entry<Stock, Double[]>> filterSubListInRank(Stock referenceStock, int nbRows, List<Entry<Stock, Double[]>> sorted) throws IOException {

		//Find i
		int i = 0;
		Iterator<Entry<Stock, Double[]>> iterator = sorted.iterator();
		while(iterator.hasNext() && !referenceStock.equals(iterator.next().getKey())) i++;
		if (!referenceStock.equals(sorted.get(i).getKey())) throw new IOException(referenceStock + " not found in calculated volatilities. Please rerun generateNewCalculationFiltered?");

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

			String line = fileReader.readLine(); //header
			while((line = fileReader.readLine()) != null) {
				String[] lineSplit = line.split(",");
				String symbol = lineSplit[0].trim();
				String isin = lineSplit[1].trim();
				Stock stock = shareDAO.loadStockBy(symbol, isin);
				LOGGER.info("Adding " + stock + " with velocities " + lineSplit[2] + " and " + lineSplit[3]  + " and " + lineSplit[4]);
				stockVolatilities.put(stock, new Double[]{Double.valueOf(lineSplit[2].trim()), Double.valueOf(lineSplit[3].trim()),  Double.valueOf(lineSplit[4].trim())});
			}

		} catch (Exception e1) {
			LOGGER.error(e1);
		}
		return sortVolatilitiesByMeansOfReturn(stockVolatilities);

	}
	
	public void generateFilteredCloseToDB(String sourceList, Boolean updateSourceList) throws Exception {
		String generatedNewCalculationFilteredToFile = generateFilteredCloseToFile(sourceList, updateSourceList);
		String path = System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator;
		generateFromFileToDB(path + generatedNewCalculationFilteredToFile, "BETA", -0.01, 0.01);
	}

	String generateFilteredCloseToFile(String sourceList, Boolean updateSourceList) throws Exception {
		
		Map<String, List<Stock>> invalidStockAccumulator = new HashMap<>();
		Map<Stock, double[]> stockSplitsTracer = new HashMap<>();

		List<Predicate<Stock>> predicates = buildPredicates(invalidStockAccumulator, stockSplitsTracer);

		UUID randomUUID = UUID.randomUUID();
		Set<Stock> filteredStocks = filterStocks(randomUUID, predicates, sourceList, updateSourceList);
		
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())));
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator);
		LOGGER.info("Split checks:\n"+
		"stock, size, nbyears, split max, merge max\n" +
				stockSplitsTracer.entrySet().stream()
				.map(e -> e.getKey().getSymbol() + ", " + Arrays.toString(e.getValue()).substring(1, Arrays.toString(e.getValue()).length()-1) + "\n")
				.reduce("", (a, e) -> a + e));
		
		Function<Stock, SortedMap<Date, Double>> quotationsWrapper = s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(s, DateFactory.dateAtZero(), new Date(), true, s.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
				return closeQuotations;
				} catch (NoQuotationsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			} catch (NotEnoughDataException | IndexOutOfBoundsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			}
		};
		
		calculateFor(randomUUID.toString(), quotationsWrapper, filteredStocks);
		
		return randomUUID + "_" + VOLATILITIES_CSV;

	}
	
	void generateFilteredInflatUnSkewedCalculationToFile() throws Exception {
		
		Map<String, List<Stock>> invalidStockAccumulator = new HashMap<>();
		Map<Stock, double[]> stockSplitsTracer = new HashMap<>();

		List<Predicate<Stock>> predicates = buildPredicates(invalidStockAccumulator, stockSplitsTracer);

		UUID randomUUID = UUID.randomUUID();
		Set<Stock> filteredStocks = filterStocks(randomUUID, predicates, "default", false);
		
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())));
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator);
		LOGGER.info("Split checks:\n"+
		"stock, size, nbyears, split max, merge max\n" +
				stockSplitsTracer.entrySet().stream()
				.map(e -> e.getKey().getSymbol() + ", " + Arrays.toString(e.getValue()).substring(1, Arrays.toString(e.getValue()).length()-1) + "\n")
				.reduce("", (a, e) -> a + e));
		
		GetInflation getInflation = GetInflation.geInstance();
		Function<Stock, SortedMap<Date, Double>> quotationsWrapper = s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(s, DateFactory.dateAtZero(), new Date(), true, s.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
				Date firstKey = closeQuotations.firstKey();
				TreeMap<Date, Double> unSkewedQuotations = closeQuotations.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> {
					try {
						Double inflationRate = getInflation.inflationRateWithinDateRange(firstKey, e.getKey()).doubleValue();
						Double temperedValue = (e.getValue()) / (1 + inflationRate);
						return temperedValue;
					} catch (NotEnoughDataException e1) {
						LOGGER.warn(e1);
						return e.getValue();
					}
				}, (a, b) -> a, TreeMap::new));
				return unSkewedQuotations;
				} catch (NoQuotationsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			} catch (NotEnoughDataException | IndexOutOfBoundsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			}
		};
		
		calculateFor(randomUUID + "_inflatRatio", quotationsWrapper, filteredStocks);

	}
	
	void generateFilteredDJIUnSkewedCalculationToFile() throws Exception {
		
		Map<String, List<Stock>> invalidStockAccumulator = new HashMap<>();
		Map<Stock, double[]> stockSplitsTracer = new HashMap<>();

		List<Predicate<Stock>> predicates = buildPredicates(invalidStockAccumulator, stockSplitsTracer);

		UUID randomUUID = UUID.randomUUID();
		Set<Stock> filteredStocks = filterStocks(randomUUID, predicates, "default", false);
		
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())));
		LOGGER.info("Invaliditiy counting: " + invalidStockAccumulator);
		LOGGER.info("Split checks:\n"+
		"stock, size, nbyears, split max, merge max\n" +
				stockSplitsTracer.entrySet().stream()
				.map(e -> e.getKey().getSymbol() + ", " + Arrays.toString(e.getValue()).substring(1, Arrays.toString(e.getValue()).length()-1) + "\n")
				.reduce("", (a, e) -> a + e));
		
		Stock dji = DataSource.getInstance().loadStockBySymbol("DJI");
		RocSmoother rocDJISmoother = new RocSmoother(dji, Arrays.asList(ValidityFilter.CLOSE.toQuotationDataType()));
		Quotations djiQuotatations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(dji, DateFactory.dateAtZero(), new Date(), true, dji.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
		SortedMap<Date, double[]> djiClose = QuotationsFactories.getFactory().buildExactMapFromQuotations(djiQuotatations, 0, djiQuotatations.size()-1, QuotationDataType.CLOSE);
		SortedMap<Date, double[]> djiRocs = rocDJISmoother.smooth(djiClose, false);
		
		Function<Stock, SortedMap<Date, Double>> quotationsWrapper = s -> {
			try {
				Quotations quotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(s, DateFactory.dateAtZero(), new Date(), true, s.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				SortedMap<Date, Double> closeQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
				TreeMap<Date, Double> unSkewedQuotations = closeQuotations.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey(), e -> {
						if (djiRocs.containsKey(e.getKey())) {
							Double roc = djiRocs.get(e.getKey())[0];
							Double temperedValue = e.getValue() / (1 + roc);
							return temperedValue;
						} else {
							return e.getValue();
						}
				}, (a, b) -> a, TreeMap::new));
				return unSkewedQuotations;
				} catch (NoQuotationsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			} catch (NotEnoughDataException | IndexOutOfBoundsException e) {
				LOGGER.warn(s + " volatility failed: " + e.toString());
				return new TreeMap<>();
			}
		};
		
		calculateFor(randomUUID + "_roc", quotationsWrapper, filteredStocks);

	}

	private List<Predicate<Stock>> buildPredicates(Map<String, List<Stock>> invalidStockAccumulator, Map<Stock, double[]> stockSplitsTracer) {
		List<Predicate<Stock>> predicates = new ArrayList<Predicate<Stock>>();
		
		
		//Has recent quotations
		Predicate<Stock> lastQuotePredicate = stock -> {
			boolean hasRecentQuotes = false;
			for (int i = 0; !hasRecentQuotes && i <= 1; i++) {
				Date lastQuote = stock.getLastQuote();
				Duration diff = Duration.between(new Date(lastQuote.getTime()).toInstant(), new Date().toInstant());
				hasRecentQuotes = diff.toDays() <= minRecentQuoteInDays;
				if (!hasRecentQuotes && allowQuotesUpdate) {
					try {	
						QuotationUpdate quotationUpdate = new QuotationUpdate();
						quotationUpdate.getQuotesFor(stock);
					} catch (QuotationUpdateException e) {
						LOGGER.warn("Can't update quotation for: " + stock + " because " + e);
						break;
					}
				} else {
					break;
				}
			}
			if (!hasRecentQuotes) {
				LOGGER.info(stock + " does not match 'last quote is within " + minRecentQuoteInDays + " days'.");
				List<Stock> predicateAcc = invalidStockAccumulator.getOrDefault("lastQuotePredicate", new ArrayList<>());
				predicateAcc.add(stock);
				invalidStockAccumulator.put("lastQuotePredicate", predicateAcc);
			}
			return hasRecentQuotes;
		};

		//N years minimum length.
		Predicate<Stock> nYearsPredicate = stock -> {
			Boolean match = false;
			for (int i = 0; !match && i <= 1; i++) {
				try {
					Quotations ohlcQuotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
					Date firstQuote = ohlcQuotations.getDate(0);
					Date lastQuote = ohlcQuotations.getDate(ohlcQuotations.size()-1);
					Duration diff = Duration.between(new Date(firstQuote.getTime()).toInstant(), new Date(lastQuote.getTime()).toInstant());
					match = ((double)diff.toDays())/365d > minDataInYears;
				} catch (NoQuotationsException e1) {
					LOGGER.warn(stock + " has no quotations: " + e1);
					match = false;
				}
				if (!match && i < 1) { //Quote updates and then retry
					LOGGER.info(stock + " may not be up to date for 'minimum length " + minDataInYears + " years'. Will update quotations and try again: " + allowQuotesUpdate);
					if (allowQuotesUpdate) {
						try {
							
							QuotationUpdate quotationUpdate = new QuotationUpdate();
							//quotationUpdate.getQuotesFor(stock);  //XXX should we do a reset of the quote here?
							DataSource.getInstance().cleanQuotationsFor(stock);
							quotationUpdate.getQuotes(new StockList(stock), true, false);
							
						} catch (QuotationUpdateException | SQLException e) {
							LOGGER.warn("Can't update quotation for: " + stock + " because " + e);
							break;
						}
					} else {
						break;
					}
				}
			}
			
			if (!match) {
				LOGGER.info(stock + " does not match 'minimum length " + minDataInYears + " years'.");
				List<Stock> predicateAcc = invalidStockAccumulator.getOrDefault("nYearsPredicate", new ArrayList<>());
				predicateAcc.add(stock);
				invalidStockAccumulator.put("nYearsPredicate", predicateAcc);
			}
			return match;
		};
		
		Predicate<Stock> maxQuotationsGapPredicate = stock -> {
			try {
				Quotations ohlcQuotations = QuotationsFactories.getFactory().getSplitFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				int nbOpenIncrementBetween = QuotationsFactories.getFactory().nbOpenIncrementBetween(
						stock.getTradingMode().getDataPointFactor(), ohlcQuotations.get(0).getDate(), ohlcQuotations.get(ohlcQuotations.size()-1).getDate());
				boolean match = (double)ohlcQuotations.size()/(double)nbOpenIncrementBetween >= minValidDataToTimeRatio;
				if (!match) {
					LOGGER.info(stock + " does not match 'max Quotations Gap <= " + (1-minValidDataToTimeRatio) + "'.");
					List<Stock> predicateAcc = invalidStockAccumulator.getOrDefault("maxQuotationsGapPredicate", new ArrayList<>());
					predicateAcc.add(stock);
					invalidStockAccumulator.put("maxQuotationsGapPredicate", predicateAcc);
				}
				return match;
				
			} catch (Exception e) {
				LOGGER.error(e);
				return false;
			}
			
		};
		
		Predicate<Stock> ohlcPredicate = stock -> {
			try {
				Currency currency = stock.getMarketValuation().getCurrency();
				Quotations ohlcQuotations = QuotationsFactories.getFactory()
						.getSplitFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, currency, 1, ValidityFilter.OHLCV);
				Quotations closeQuotationsWithinRange = QuotationsFactories.getFactory()
						.getSplitFreeQuotationsInstance(stock, ohlcQuotations.getDate(0), ohlcQuotations.getDate(ohlcQuotations.size()-1), true, currency, 1, ValidityFilter.CLOSE);
				boolean match = (double) ohlcQuotations.size()/(double) closeQuotationsWithinRange.size() >= minOHLCToQuotesRatio;
				if (!match) {
					LOGGER.info(stock + " does not match 'ohlc validity >= " + minOHLCToQuotesRatio + "'.");
					List<Stock> predicateAcc = invalidStockAccumulator.getOrDefault("ohlcPredicate", new ArrayList<>());
					predicateAcc.add(stock);
					invalidStockAccumulator.put("ohlcPredicate", predicateAcc);
				}
				return match;
			} catch (Exception e) {
				LOGGER.error(e);
				return false;
			}
		};	
		
		Predicate<Stock> splitMergePredicate = stock -> {
			try {
				Quotations ohlcQuotations = QuotationsFactories.getFactory()
						.getSplitFreeQuotationsInstance(stock, DateFactory.dateAtZero(), new Date(), true, stock.getMarketValuation().getCurrency(), 1, ValidityFilter.OHLCV);
				
				List<QuotationUnit> ohlcData = ohlcQuotations.getQuotationUnits(0, ohlcQuotations.size()-1);
				List<BigDecimal> splits = ohlcData.stream()
						.map(qu -> qu.getSplit())
						.collect(ArrayList::new, (a, split) -> {
							if (a.isEmpty() || split.compareTo(a.get(a.size()-1)) != 0) {
								a.add(split);
							}
						}, (a,b) -> a.addAll(b));
				double yearsSpan = ((double) java.time.temporal.ChronoUnit.DAYS.between(
						new Date(ohlcData.get(0).getDate().getTime()).toInstant(),
						new Date(ohlcData.get(ohlcData.size()-1).getDate().getTime()).toInstant()))/365d;
				double[] flatSplits = IntStream
							.range(0, splits.size())
							.mapToDouble(i -> splits.get(i).doubleValue()/((i < splits.size()-1)?splits.get(i+1).doubleValue():1d))
							.toArray();
				Boolean doNotMatch = 
						((double) splits.size())/yearsSpan > maxSplitPerYear || 
						Arrays.stream(flatSplits).anyMatch(split -> split > maxSplitValue || 1d/split > maxMergeValue);
				if (doNotMatch) {
					LOGGER.info(
							stock + " with years span " + yearsSpan + " does not match 'splits constrains' " +
							"maxSplitPerYear: " + maxSplitPerYear + ", splitMax: " + maxSplitValue + ", mergeMin: " + 1d/maxMergeValue + " predicate. " + flatSplits);
					List<Stock> predicateAcc = invalidStockAccumulator.getOrDefault("splitMergeValidity", new ArrayList<>());
					predicateAcc.add(stock);
					invalidStockAccumulator.put("splitMergeValidity", predicateAcc);
				}
				stockSplitsTracer.put(stock, new double[] {
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
		
		predicates.add(lastQuotePredicate);
		predicates.add(nYearsPredicate);
		predicates.add(maxQuotationsGapPredicate);
		predicates.add(ohlcPredicate);
		predicates.add(splitMergePredicate);
//		predicates.add(maxUpPredicate);
//		predicates.add(maxDownPredicate);
		return predicates;
	}

	List<Entry<Stock, Double[]>> calculateFor(String randomUUID, Function<Stock, SortedMap<Date, Double>> quotationsWraper, Set<Stock> allStocks) throws Exception {

		Map<Stock, Double[]> stockVolatilities = allStocks.stream().collect(Collectors.toMap(s -> s, s -> {
			try {
				SortedMap<Date, Double> closeQuotations = quotationsWraper.apply(s);
				HistoricalVolatilityCalculator historicalVolatilityCalculator = new HistoricalVolatilityCalculator(closeQuotations, 1, 63, true);
				Double annualisedVolatilityAveragedAllDataSet = historicalVolatilityCalculator.averageVolatility(0, closeQuotations.size());
				Double annualisedMeanOfReturnAveragedAllDataSet = historicalVolatilityCalculator.averageMeanOfReturns(0, closeQuotations.size());
				Double annualisedVolatilityLastThreeMonth = historicalVolatilityCalculator.movingVolatiltityAt(closeQuotations.size()-1);
				return new Double[]{annualisedVolatilityAveragedAllDataSet, annualisedVolatilityLastThreeMonth, annualisedMeanOfReturnAveragedAllDataSet};
			} catch (Exception e) {
				return new Double[] {Double.NaN,Double.NaN, Double.NaN};
			}
		}));

		//Sort
		List<Entry<Stock, Double[]>> sorted = sortVolatilitiesByMeansOfReturn(stockVolatilities);

		//Export to "volatilities.csv"
		try (FileWriter fileWriter = new FileWriter(new File(EXPORT_PATH + randomUUID + "_" + VOLATILITIES_CSV))) {
			fileWriter.write("Symbol, Isin, annualisedVolatilityAveragedAllDataSet, annualisedVolatilityLastThreeMonth, annualisedMeanOfReturnAveragedAllDataSet" + "\n");
			sorted.stream()
			.forEach(e -> {
				try {
					Stock key = e.getKey();
					String line = key.getSymbol() + ", " + key.getIsin() + ", " + Arrays.toString(e.getValue()).replace("[", "").replace("]", "");
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

	private List<Entry<Stock, Double[]>> sortVolatilitiesByMeansOfReturn(Map<Stock, Double[]> stockVolatilities) {
		List<Entry<Stock, Double[]>> sorted = stockVolatilities.entrySet().stream()
				.filter(e -> !Double.isNaN(e.getValue()[0]) && !Double.isNaN(e.getValue()[2]))
				.sorted((e0, e1) -> {
					Double roundE0 = Double.valueOf(Precision.round(e0.getValue()[2], 4, BigDecimal.ROUND_HALF_EVEN));
					Double roundE1 = Double.valueOf(Precision.round(e1.getValue()[2], 4, BigDecimal.ROUND_HALF_EVEN));
					if (roundE0.compareTo(roundE1) == 0) {
						return e0.getValue()[0].compareTo(e1.getValue()[0]);
					} else {
						return roundE0.compareTo(roundE1);
					}
				})
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

	//FIXME use as sorted Collection instead of a list
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

	private Set<Stock> filterStocks(UUID randomUUID, List<Predicate<Stock>> predicates, String sourceList, Boolean updateSourceList) {

		Set<Stock> allStocks= new HashSet<>();
		switch (sourceList) {
		case "VOLATILITY,BETA":
			YahooStockScreener yahooStockScreener = new YahooStockScreener(updateSourceList);
			allStocks.addAll(yahooStockScreener.calculate());
			break;
		default:
			//allStocks = portfolioDAO.loadSharesListContent(null, null).stream().map(ps -> ps.getStock()).collect(Collectors.toSet());
			//allStocks = shareDAO.loadAllStocks();
		}
		
		try {	
			QuotationUpdate quotationUpdate = new QuotationUpdate();
			quotationUpdate.getQuotesFor(allStocks);
		} catch (QuotationUpdateException e) {
			LOGGER.error("Can't update quotation for screener because " + e);
		}

		Set<Stock> filtered = allStocks.stream()
				.filter(s -> predicates.stream().allMatch(p -> p.test(s)))
				.collect(Collectors.toSet());
		LOGGER.info("Valid stock filtered: " + filtered.size() + " out of " + allStocks.size());
		
		String validLines = filtered.stream()
				.map(s -> s.toString())
				.distinct()
				.reduce((r, e) -> r + "\n" + e)
				.orElse("None");
		LOGGER.info("Valid stocks: \n" + validLines);
		
		try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + randomUUID + "_validStocks.txt"))) {
			fileWriter.write(validLines + "\n");
		} catch (IOException e) {
			LOGGER.error(e, e);
		}

		Set<Stock> rejected = allStocks.stream()
				.filter(s -> !filtered.contains(s))
				.collect(Collectors.toSet());
		LOGGER.info("Invalid stocks: " + rejected.size() + " out of " + allStocks.size());
		String invalidLines = rejected.stream()
				.map(s -> s.toString())
				.distinct()
				.reduce((r, e) -> r + "\n" + e)
				.orElse("None");
		LOGGER.info("Affected stocks: \n" + invalidLines);
		
		try (FileWriter fileWriter = new FileWriter(new File(TMP_PATH + randomUUID + "_invalidStocks.txt"))) {
			fileWriter.write(invalidLines + "\n");
		} catch (IOException e) {
			LOGGER.error(e, e);
		}

		return filtered;
	}

}
