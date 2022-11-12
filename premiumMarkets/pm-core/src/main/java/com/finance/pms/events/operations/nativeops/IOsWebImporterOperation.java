package com.finance.pms.events.operations.nativeops;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.api.WebDelegate;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.TargetStockInfo;
import com.finance.pms.events.operations.Value;

public class IOsWebImporterOperation extends StringerOperation {
	
	private static MyLogger LOGGER = MyLogger.getLogger(IOsWebImporterOperation.class);

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
		
		synchronized(WebDelegate.class) {
			try {
				
				filePath = web.httpGetFile(filePath);
				
				String fileCopy = filePath + "_" + UUID.randomUUID();
				Files.copy(Paths.get(filePath), Paths.get(fileCopy));
				getOperands().get(0).setParameter(new StringValue(fileCopy));
				
				return new StringValue(fileCopy);
				
			} catch (IOException e) {
				LOGGER.error(e, e);
				return new StringValue("NAN");
			} finally {
				web.deleteLocalFile(filePath);
			}
		}
		
		
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... addtionalParams) {
		new File(((StringValue) getOperands().get(0).getParameter()).getValueAsString()).delete();
	}
	
	@Override
	public void interrupt() throws IOException {
		if (web.getHttpClient() != null) {
			web.getHttpClient().close();
		}
		web.setHttpClient(null);
	}

}
