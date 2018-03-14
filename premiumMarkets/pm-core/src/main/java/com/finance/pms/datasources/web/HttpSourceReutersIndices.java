package com.finance.pms.datasources.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.StockCategories;

public class HttpSourceReutersIndices extends HttpSourceMarket {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HttpSourceReutersIndices.class);

	public HttpSourceReutersIndices(String pathToprops, MyBeanFactoryAware beanFactory) {
		super(pathToprops, beanFactory);
	}

	@Override
	public String getStockInfoPageURL(String refName) throws UnsupportedEncodingException {
		//https://uk.finance.yahoo.com/quote/ORA.PA?ltr=1
		return "http://uk.finance.yahoo.com/quote/"+ URLEncoder.encode(refName,"UTF-8") + "?ltr=1";
	}
	
	public String getStockInfoPageProfilURL(String isin) throws UnsupportedEncodingException {
		//https://uk.finance.yahoo.com/quote/ORA.PA/profile?ltr=1
		return "http://uk.finance.yahoo.com/quote/"+ URLEncoder.encode(isin,"UTF-8") + "/profile?ltr=1";
	}

	@Override
	public String getCategoryStockListURL(StockCategories category, String... params) {
		
		//https://www.reuters.com/finance/markets/index/.FTMC
		//https://www.reuters.com/finance/markets/index/.FTMC?sortBy=&sortDir=&pn=2
		
		String urlRet = "https://www.reuters.com/finance/markets/index/";

		try {
			if (params[1].equals("0")) {
				urlRet = urlRet + URLEncoder.encode(params[0],"UTF-8");
			} else {
				urlRet = urlRet + URLEncoder.encode(params[0],"UTF-8") + String.format("?sortBy=&sortDir=&pn=%s", params[1]);
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		}

		return urlRet;
	}

}
