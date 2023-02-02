package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.web.api.WebDelegate;

public class ProvidersYahooPythonWeb extends ProvidersYahooPython {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPythonWeb.class);
	
	private WebDelegate web = new WebDelegate();
	
	protected ProvidersYahooPythonWeb(String pathToProps) {
		super(pathToProps);
	}

	@Override
	protected InputStream readInput(String symbol, Date start, Date end) throws IOException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LOGGER.info(String.format("Running Yahoo web py: %s %s %s %s", "main.py", symbol, dateFormat.format(start), dateFormat.format(end)));
		return web.runAny("main.py", symbol, dateFormat.format(start), dateFormat.format(end));
	}

}
