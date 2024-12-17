package com.finance.pms.events.operations.nativeops.data;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.StackElement;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.nativeops.StringOperation;
import com.finance.pms.events.operations.nativeops.StringValue;
import com.finance.pms.events.operations.nativeops.Value;

public class IfExistsExporterOperation extends FileExporter {
	
	private static final int FILE_PATH_IDX = 0;

	public IfExistsExporterOperation(String reference, String description, Operation...operands ) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}
	
	public IfExistsExporterOperation() {
		this("ifExistsExporter", "Check if a file exists, if so passes its path as return else NONE", 
				new StringOperation("string","filePath", "CSV File Path", new StringValue("autoPortfolioLogs/input.csv")));
	}

	@Override
	public Operation getFilePathOperand() {
		return this.getOperands().get(FILE_PATH_IDX);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, List<StackElement> thisCallStack, int parentRequiredStartShift, int thisFullStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		String filePrefixPath = ((StringValue) inputs.get(FILE_PATH_IDX)).getValue(targetStock);
		
		if (!filePrefixPath.startsWith(File.separator)) {
			filePrefixPath = System.getProperty("installdir") + File.separator + filePrefixPath;
		}
		
		boolean exists = Files.exists(Path.of(filePrefixPath));
		
		return (exists)?new StringValue(filePrefixPath):new StringValue("NONE");
	}

	@Override
	public int operandsRequiredStartShift(TargetStockInfo targetStock, List<StackElement> thisCallStack, int thisParentStartShift) {
		return 0;
	}
	
	@Override
	public Operation getHeaderPrefixOperand() {
		return null;
	}

}
