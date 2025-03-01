package com.finance.pms.datasources.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.api.WebDelegate;
import com.finance.pms.events.quotations.QuotationUnit;

public class ProvidersYahooPythonWeb extends ProvidersYahooPython {
	
	private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPythonWeb.class);
	
	private WebDelegate web = new WebDelegate();
	
	protected ProvidersYahooPythonWeb(String pathToProps) {
		super(pathToProps);
	}

	@Override
	protected InputStream readInput(String symbol, Date start, Date end, Boolean isPeriod, Boolean isIntraday) throws IOException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LOGGER.info(String.format("Running Yahoo web py: %s %s %s %s", "main.py", symbol, dateFormat.format(start), dateFormat.format(end)));
		return web.runAny(new ArrayList<>(),"main.py", symbol, dateFormat.format(start), dateFormat.format(end));
	}

	@Override
	protected QuotationUnit readPythonIntradayPage(Stock stock, Date start, Date tomorrowPlusOne) {
		throw new UnsupportedOperationException("FIXME: factorise with YahooPythonLocal");
	}

}
