package com.finance.pms.events.operations.nativeops;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
				
				Path localFilePath = Path.of(URI.create("file://" + filePath));
				boolean exist = Files.exists(localFilePath);
				if (exist) {
					String remoteFile = web.httpGetFile(filePath, true);
					Path remoteFilePathCopy = Path.of(URI.create("file://" + remoteFile));
					if (localFilePath.toFile().length() != remoteFilePathCopy.toFile().length()) {
						Files.copy(remoteFilePathCopy, localFilePath);
						Files.delete(remoteFilePathCopy);
					}
				} else {
					filePath = web.httpGetFile(filePath, false);
				}
				
				return new StringValue(filePath);
				
			} catch (IOException e) {
				LOGGER.error(e, e);
				return new StringValue("NAN");
			}
		}
		
		
	}

	@Override
	public int operandsRequiredStartShift() {
		return 0;
	}

	@Override
	public void invalidateOperation(String analysisName, Optional<Stock> stock, Object... webFilesCopies) {
		if (webFilesCopies != null) {
			for (int i = 0; i < webFilesCopies.length; i++) {
				try {
					Path deltaFile = Path.of(URI.create("file://" + webFilesCopies[i]));
					LOGGER.info("Deleting file local copy: " + deltaFile.toString());
					boolean exist = Files.exists(deltaFile);
					if (exist) {
						try {
							Files.delete(deltaFile);
						} catch (IOException e) {
							LOGGER.error(e, e);
						}
					}
				} catch (Exception e1) {
					LOGGER.error("Can't create path from " + webFilesCopies[i], e1);
				}
			}
		}
	}
	
	@Override
	public void interrupt() throws IOException {
		if (web.getHttpClient() != null) {
			web.getHttpClient().close();
		}
		web.setHttpClient(null);
	}

}
