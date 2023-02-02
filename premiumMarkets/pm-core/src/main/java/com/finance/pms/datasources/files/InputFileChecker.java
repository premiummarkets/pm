package com.finance.pms.datasources.files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.calculation.NotEnoughDataException;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.QuotationDataType;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.Quotations.ValidityFilter;
import com.finance.pms.events.quotations.QuotationsFactories;

public class InputFileChecker {
	
	private static MyLogger LOGGER = MyLogger.getLogger(InputFileChecker.class);


	public static void main(String[] args) throws Exception {
		InputFileChecker.compareInputsIntersections(Double.valueOf(args[0]), Boolean.valueOf(args[1]), Arrays.copyOfRange(args,2,args.length));
	}
	
	
	public static void checkInputAgainstQuotations(String inputPath, Stock stock, ValidityFilter validityFilter, int headingNans, int trailingNans, Set<Date> missingKeys) throws NotEnoughDataException, NoQuotationsException {
		
		CsvImportExport<Date> csvImporter = new MapCsvImportExport();
		List<String> headers = new ArrayList<>();
		SortedMap<Date, double[]> importedData = csvImporter.importData(new File(inputPath), headers);
		
		Date firstDate = importedData.firstKey();
		Date lastDate = importedData.lastKey();
		
		Quotations quotations  = QuotationsFactories.getFactory().getSpliFreeQuotationsInstance(stock, firstDate, lastDate, true, stock.getMarketValuation().getCurrency(), 0, validityFilter);
		SortedMap<Date, Double> exactMapFromQuotations = QuotationsFactories.getFactory().buildExactSMapFromQuotations(quotations, QuotationDataType.CLOSE, 0, quotations.size()-1);
		
		ArrayList<Date> expectedkeySet = new ArrayList<Date>(exactMapFromQuotations.keySet());
		LOGGER.info("Removing known missing keys from operands for " + stock + ": " + missingKeys);
		expectedkeySet.removeAll(missingKeys);
		
		boolean allMatch = expectedkeySet.subList(0, headingNans).stream().allMatch(k -> importedData.containsKey(k));
		if (!allMatch) throw new NotEnoughDataException(stock, firstDate, lastDate, "Output is not matching: heading NaNs", null);
		allMatch = expectedkeySet.subList(headingNans, expectedkeySet.size()-trailingNans).stream()
				.allMatch(k -> importedData.containsKey(k) && Arrays.stream(importedData.get(k)).allMatch(d -> !Double.isNaN(d)));
		if (!allMatch) {
			List<Date> notMatching = expectedkeySet.subList(headingNans, expectedkeySet.size()-trailingNans).stream()
					.filter(k -> !importedData.containsKey(k) || Arrays.stream(importedData.get(k)).filter(d -> Double.isNaN(d)).count() > 0)
					.collect(Collectors.toList());
			throw new NotEnoughDataException(stock, firstDate, lastDate, "Output is not matching quotations with missing keys or rows with NaNs: " + notMatching.toString(), null);
		}
		allMatch = expectedkeySet.subList(expectedkeySet.size()-trailingNans, expectedkeySet.size()).stream().allMatch(k -> importedData.containsKey(k));
		if (!allMatch) throw new NotEnoughDataException(stock, firstDate, lastDate, "Output is not matching: trailing NaNs", null);
		
		Set<Date> actualKeySet = importedData.keySet();
		allMatch = actualKeySet.stream().allMatch(k -> exactMapFromQuotations.containsKey(k));
		if (!allMatch) throw new NotEnoughDataException(stock, firstDate, lastDate, "Output is not matching quotations keys set with additionnal keys.", null);
		
	}
	
	public static void compareInputsIntersections(Double precision, boolean sameRange, String...inputs) {
		
		if (inputs.length < 2) return;
		
		List<String> headers = new ArrayList<>();
		List<SortedMap<Date, double[]>> inputsData = Arrays.stream(inputs).map(in -> {
			CsvImportExport<Date> csvImporter = new MapCsvImportExport();
			return csvImporter.importData(new File(in), headers);
		}).collect(Collectors.toList());
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 0; i < inputs.length-1; i++) {
			LOGGER.info(
					"Comparing: " +
					"\nfrom " + df.format(inputsData.get(i).firstKey()) + " to " + df.format(inputsData.get(i).lastKey()) + ": " +  inputs[i] + 
					"\nfrom " + df.format(inputsData.get(i+1).firstKey()) + " to " + df.format(inputsData.get(i+1).lastKey()) + ": " + inputs[i+1]);
			SortedMap<Date, double[]> aMap = inputsData.get(i);
			SortedMap<Date, double[]> bMap = inputsData.get(i+1);
			
			Set<Date> aKeySet = aMap.keySet();
			Set<Date> bKeySet = bMap.keySet();
			if (sameRange) {
				boolean allMatch = aKeySet.stream().allMatch(k -> bKeySet.contains(k)) && bKeySet.stream().allMatch(k -> aKeySet.contains(k));
				if (!allMatch) {
					LOGGER.error("Maps " + i + " and " + (i+1) + " don't contain the exact same key sets: " + 
						"additionnal in " + i + ": " + aKeySet.stream().filter(k -> !bKeySet.contains(k)).collect(Collectors.toList()) + "; " +
						"additionnal in " + (i+1) + ": " + bKeySet.stream().filter(k -> !aKeySet.contains(k)).collect(Collectors.toList()));
				}
			}
			
			final SortedMap<Date, double[]> aMapInter = MapUtils.subMapInclusive(aMap, bMap.firstKey(), bMap.lastKey());
			final SortedMap<Date, double[]> bMapInter = MapUtils.subMapInclusive(bMap, aMap.firstKey(), aMap.lastKey());
			if (aMapInter.isEmpty() || bMapInter.isEmpty()) {
				LOGGER.error("Maps " + i + " and " + (i+1) + " don't intersect.");
			}
			int fi = i;
			long noMatch = aMapInter.keySet().stream().filter(k -> {
				if (bMapInter.containsKey(k)) {
					double[] ads = aMapInter.get(k);
					double[] bds = bMapInter.get(k);
					for (int j = 0; j < bds.length; j++) {
						if (Double.isNaN(ads[j])) {
							if (!Double.isNaN(bds[j])) {
								LOGGER.error("Maps " + fi + " and " + (fi+1) + " differ at " + k + ", column " + headers.get(j) + ", with " + ads[j] + " and " + bds[j]);
								return true;
							}
						} else {
							if (Double.isNaN(bds[j]) || (Math.abs(ads[j]-bds[j]) > precision))  {
								LOGGER.error("Maps " + fi + " and " + (fi+1) + " differ at " + k + ", column " + headers.get(j) + ", with " + ads[j] + " and " + bds[j]);
								return true;
							}
						}
					}
					return false;
				} else {
					return false;
				}
			}).count();
			
			if(noMatch > 0) LOGGER.error("Maps " + i + " and " + (fi+1) + " differ.");
			
			LOGGER.info("done: " + 
					"\n" + i + ": from " + df.format(inputsData.get(i).firstKey()) + " to " + df.format(inputsData.get(i).lastKey()) + ": " +  inputs[i] + 
					"\n" + (i+1) + ": from " + df.format(inputsData.get(i+1).firstKey()) + " to " + df.format(inputsData.get(i+1).lastKey()) + ": " + inputs[i+1]);
			
		}
		
	}

}
