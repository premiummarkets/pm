package com.finance.pms.events.operations.nativeops;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.files.CsvImportExport;
import com.finance.pms.datasources.files.MapCsvImportExport;
import com.finance.pms.events.calculation.util.MapUtils;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;
import com.finance.pms.events.operations.conditional.MultiValuesOutput;

public class FileOperation extends PMWithDataOperation implements MultiValuesOutput, CachableOperation {

	private static MyLogger LOGGER = MyLogger.getLogger(FileOperation.class);

	public FileOperation() {
		super("fileOperation", "Loads precalculated outputs from a file",
			new StringOperation("string","filePath", "CSV File Path", new StringValue("autoPortfolioLogs/input.csv")),
			new NumberOperation("number","mainColumnIndex", "Main Index of the column to be displayed.", new NumberValue(1.0)),
			new NumberOperation("number","columnIndex", "Index of the columns to include in the output ([1..n] || 0 for all). The first column is indexed 0 and must contain dates.", new NumberValue(0.0)));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	//Can't use MultiSelectorsValue as selectors are not known in advance (this will fail in the editor).
	public NumericableMapValue calculate(TargetStockInfo targetStock, String thisCallStack, int parentRequiredStartShift, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {

		StringValue pathStringValue = (StringValue) inputs.get(0);
		String filePath = pathStringValue.getValue(targetStock);
		if (!filePath.startsWith(File.separator)) {//Relative
			filePath = System.getProperty("installdir") + File.separator + filePath;
		}
		
		int mainIndex = ((NumberValue) inputs.get(1)).getNumberValue().intValue();
		Integer firstVariableIdx = 2;
		List<Integer> includedIndexes = inputs.subList(firstVariableIdx, inputs.size()).stream().map(v -> ((NumberValue) v).getNumberValue().intValue()).collect(Collectors.toList());

		if (includedIndexes.contains(0)) {
			includedIndexes.clear();
		} else {
			if (!includedIndexes.contains(mainIndex)) throw new RuntimeException("The column index list must contain the main index.");
		}
		
		List<String> columnRefs = new ArrayList<String>();
		SortedMap<Date, double[]> calculationResults = new TreeMap<Date, double[]>();
		Date startDate = null;
		try {
			
			startDate =  targetStock.getStartDate(thisStartShift);
			
			CsvImportExport<Date> csvImporter = new MapCsvImportExport();
			List<String> headers = new ArrayList<>();
			SortedMap<Date, double[]> importedData = csvImporter.importData(new File(filePath), headers);
			
			if (includedIndexes.isEmpty()) {
				includedIndexes.addAll(IntStream.range(1, headers.size()).boxed().collect(Collectors.toList()));
			}

			columnRefs = includedIndexes.stream()
						.map(columnIndex -> {
							String key = (headers.isEmpty())? "Column" + columnIndex : headers.get(columnIndex);
							return key;
						})
						.collect(Collectors.toList());
			calculationResults =
					importedData.entrySet().stream()
						.map(e -> {
							double[] nuOutput = new double[includedIndexes.size()];
							IntStream.range(0, nuOutput.length).forEach(i -> nuOutput[i] = e.getValue()[includedIndexes.get(i)-1]);
							return new AbstractMap.SimpleImmutableEntry<>(e.getKey(), nuOutput);
						})
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, TreeMap::new));

			SortedMap<Date, double[]> subMapInclusiveResults = MapUtils.subMapInclusive(calculationResults, startDate, targetStock.getEndDate());			
			return new DoubleArrayMapValue(subMapInclusiveResults, columnRefs, includedIndexes.indexOf(mainIndex));
			
		} catch (Exception e) {
			LOGGER.error("Unreadable file for period " + startDate + " - " + targetStock.getEndDate() + ": "+ filePath, e);
			return new DoubleArrayMapValue(calculationResults, columnRefs, includedIndexes.indexOf(mainIndex));
		}

	}

	@Override
	public int mainInputPosition() {
		return ((NumberValue)getOperands().get(1).getOrRunParameter(null).orElseThrow()).getValue(null).intValue();
		//return 0; //FIXME
	}
	
	@Override
	public Integer operationNaturalShift() {
		return 0;
	}
	
	@Override
	public boolean isParameterDataSensitive() {
		return true;
	}

}
