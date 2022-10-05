package com.finance.pms.events.operations.nativeops;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.api.WebDelegate;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class IOsWebImporterOperation extends StringerOperation {
	
	private WebDelegate web = new WebDelegate();
	
	public IOsWebImporterOperation(String reference, String description, Operation... operands) {
		super(reference, description, new ArrayList<Operation>(Arrays.asList(operands)));
	}

	public IOsWebImporterOperation() {
		this("iosWebImporter", "Imports a file from http", new StringOperation("string","filePath", "CSV File Path", new StringValue("input.csv")));
		this.getOperands().get(this.getOperands().size()-1).setIsVarArgs(true);
	}

	@Override
	public StringValue calculate(TargetStockInfo targetStock, int thisStartShift, @SuppressWarnings("rawtypes") List<? extends Value> inputs) {
		
		String filePath = ((StringValue) inputs.get(0)).getValue(targetStock);
		filePath = web.httpGetFile(filePath);
		
		return new StringValue(filePath);
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock) {
		
	}
	
	@Override
	public void interrupt() throws IOException {
		if (web.getHttpClient() != null) {
			web.getHttpClient().close();
		}
		web.setHttpClient(null);
	}

}
