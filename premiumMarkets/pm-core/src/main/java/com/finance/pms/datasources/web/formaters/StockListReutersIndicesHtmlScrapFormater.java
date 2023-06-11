package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.MarketQuotationProviders;
import com.finance.pms.datasources.shares.MarketValuation;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.shares.StockCategories;
import com.finance.pms.datasources.shares.SymbolMarketQuotationProvider;
import com.finance.pms.datasources.shares.SymbolNameResolver;
import com.finance.pms.datasources.shares.TradingMode;
import com.finance.pms.datasources.web.MyUrl;

public class StockListReutersIndicesHtmlScrapFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	
	private MarketValuation market;
	private Pattern symbolPattern;
	private Pattern namePattern;

	private String currentSymbol;

	public StockListReutersIndicesHtmlScrapFormater(String myUrl, MarketValuation market) {
		super(new MyUrl(myUrl));
		this.market = market;
		
		try {
			if (null == PATTERNS) PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}
		symbolPattern = Pattern.compile(PATTERNS.getProperty("reutersindicestocksymbol"));
		namePattern = Pattern.compile(PATTERNS.getProperty("reutersindicestockname"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		LOGGER.trace(line);
		List<Validatable> retour = new ArrayList<Validatable>();
		
		if (currentSymbol == null) {
			Matcher symbolMatcher;
			symbolMatcher = symbolPattern.matcher(line);
			if (symbolMatcher.find()) {
				currentSymbol = sanitize(symbolMatcher.group(1).trim());
			}
		} else {
			Matcher nameMatcher;
			nameMatcher = namePattern.matcher(line);
			if (nameMatcher.find()) {
				String name = nameMatcher.group(1).trim();
				try {
					retour.add(
							new Stock(currentSymbol, currentSymbol, name, true, StockCategories.DEFAULT_CATEGORY,
							new SymbolMarketQuotationProvider(MarketQuotationProviders.YAHOO,SymbolNameResolver.UNKNOWNEXTENSIONCLUE),
							market,"", TradingMode.CONTINUOUS, 0l));
				} catch (InvalidAlgorithmParameterException e) {
					throw new StopParseErrorException(retour, e.toString(), "");
				}
				currentSymbol = null;
			} else {
				throw new StopParseErrorException(retour, "Error retriving "+ market.getMarket()+ ". Name line not matched after symbol "+currentSymbol, "Name line not matched after symbol "+currentSymbol);
			}
		}

		return retour;
	}

	private String sanitize(String symbolWithReutersExt) {
		//TODO
		//All : (_?[lowercase]). -> .
		symbolWithReutersExt = symbolWithReutersExt.replaceAll("_?[a-z]\\.", ".");
		//DAX : G(.*).DE -> .DE Non greedy
		symbolWithReutersExt = symbolWithReutersExt.replaceAll("G\\.DE", ".DE");

		//All : (d). -> .
		symbolWithReutersExt = symbolWithReutersExt.replaceAll("[0-9]\\.", ".");

		//NDX : ".OQ" ->  ""
		symbolWithReutersExt = symbolWithReutersExt.replace(".OQ", "");
		//DJI : ".N" -> ""
		symbolWithReutersExt = symbolWithReutersExt.replace(".UN", "").replace(".N", "");
		//FTSE : <ok?>
		//GSPTSE (.TO): <ok?>
 		return symbolWithReutersExt;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

}
