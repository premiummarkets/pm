package com.finance.pms.datasources.files;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface CsvImportExport<T> {

	void exportData(String baseFileName, String exportFileNameExt, Map<T, double[]> map);

	default SortedMap<T, double[]> importData(File exportFile) {
		throw new UnsupportedOperationException();
	}
	
	default SortedMap<T, double[]> importData(File exportFile, List<String> headers) {
		throw new UnsupportedOperationException();
	}

}
