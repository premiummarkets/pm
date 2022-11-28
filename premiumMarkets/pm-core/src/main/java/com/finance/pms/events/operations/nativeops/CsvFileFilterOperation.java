package com.finance.pms.events.operations.nativeops;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class CsvFileFilterOperation extends StringerOperation {
	
	private static Logger LOGGER = Logger.getLogger(CsvFileFilterOperation.class);

	public CsvFileFilterOperation(String reference, String description, Operation ... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public CsvFileFilterOperation() {
		this("csvFilterOperation", "Loads filtered data from a csv file. Only the last match is returned.",
			new StringOperation("string","filePath", "CSV File Path", new StringValue("autoPortfolioLogs/input.csv")),
			new NumberOperation("number","columnIndex", "Index of the column to retrieve", new NumberValue(0.0)),
			new StringOperation("string","pattern", "Simple pattern (.*[]{,}+ accepted) or contained characters sequence, used to filter one line.", new StringValue("NONE")),
			new StringOperation("string","default", "Default value.", new StringValue("NONE")));
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		String filePath = ((StringValue) inputs.get(0)).getValue(targetStock);
		if (!filePath.startsWith(File.separator)) {//Relative
			filePath = System.getProperty("installdir") + File.separator + filePath;
		}
		int retrievedColumnIndex = ((NumberValue) inputs.get(1)).getNumberValue().intValue();
		String filterPatternString = ((StringValue) inputs.get(2)).getValue(targetStock);
		String defaultValue = ((StringValue) inputs.get(3)).getValue(targetStock);

		List<String> matchingLines = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

			String line;
			int prevLength = 0;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty() || line.startsWith("#")) continue;
				String[] rowSplit = line.split(",");
				
				// file check
				if (prevLength > 0 && prevLength != rowSplit.length)
					throw new RuntimeException("Invalid file");
				
				//Compute
				try {
					prevLength = rowSplit.length;
					boolean anyMatchPattern = Arrays.stream(rowSplit).anyMatch(c -> c.matches(filterPatternString));
					boolean anyMatchContains = Arrays.stream(rowSplit).anyMatch(c -> c.contains(filterPatternString));
					if (anyMatchPattern || anyMatchContains) {
						matchingLines.add(rowSplit[retrievedColumnIndex]);
					}
				} catch (Exception e) {
					LOGGER.warn("Unreadable line in " + filePath + ": " + line); //+ ". Cause: " + e);
				}
				
			}

		} catch (Exception e) {
			throw new RuntimeException("File: " + filePath, e);
		}
	
		if (matchingLines.size() > 1) {
			LOGGER.warn("Dubious results retrieved (multiple matches): " + matchingLines + " for " + filterPatternString + " in " + filePath);
		}

		String result = defaultValue;
		if (!matchingLines.isEmpty()) {
			result = matchingLines.get(matchingLines.size()-1); //The last match prevails
			LOGGER.info("Results: " + result + " for " + filterPatternString + " in " + filePath);
		} else {
			LOGGER.info("No results (empty matches), using default: " + result + " for " + filterPatternString + " in " + filePath);
		}
		
		return new StringValue(result);

	}

	@Override
	public Boolean isIdemPotent() {
		return false;
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, int thisParentStartShift) {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
		//Nothing
	}

}
