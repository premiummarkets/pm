package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.calculation.DateFactory;

public class DayQuoteInvestingFormater extends LineFormater {
	
	private static PatternProperties PATTERNS = null;
	
	private Stock stock;
	
	private Long date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private Long volume;
	
	int cpt = 0;
	
	private Pattern uniquePattern;
	
	public DayQuoteInvestingFormater(MyUrl myUrl, Stock stock) {
		super(myUrl);
		this.stock = stock;
		
		try {
			if (null == PATTERNS)
				PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		uniquePattern = Pattern.compile(PATTERNS.getProperty("investingPattern"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		List<Validatable> ret = new ArrayList<>();
		
		try {
			Matcher fit = uniquePattern.matcher(line);
			switch (cpt) {
				case 0 :
					if (fit.find()) {
						System.out.println("Date : "+line+" at cpt : "+cpt);
						//date = LocalDateTime.ofEpochSecond(new Long(fit.group(1)), 0, ZoneOffset.UTC).toLocalDate();
						date = new Long(fit.group(1));
						cpt ++;
					}
					break;
				case 1 :
					if (fit.find()) {
						System.out.println("Close : "+line+" at cpt : "+cpt);
						close = new BigDecimal(fit.group(1));
						cpt ++;
					}
					break;
				case 2 :
					if (fit.find()) {
						System.out.println("Open : "+line+" at cpt : "+cpt);
						open = new BigDecimal(fit.group(1));
						cpt ++;
					}
					break;
				case 3 :
					if (fit.find()) {
						System.out.println("High : "+line+" at cpt : "+cpt);
						high = new BigDecimal(fit.group(1));
						cpt ++;
					}
					break;
				case 4 :
					if (fit.find()) {
						System.out.println("Low : "+line+" at cpt : "+cpt);
						low = new BigDecimal(fit.group(1));
						cpt ++;
					}
					break;
				case 5 :
					if (fit.find()) {
						System.out.println("Volume : "+line+" at cpt : "+cpt);
						volume = new Long(fit.group(1));
						LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(date*1000);
						mainQuery.add(DateFactory.midnithDate(calendar.getTime()));
						mainQuery.add(open);
						mainQuery.add(high);
						mainQuery.add(low);
						mainQuery.add(close);
						mainQuery.add(volume);
						ret.add(new DailyQuotation(mainQuery, stock, stock.getMarketValuation().getCurrency().name()));
						cpt=0;
					}
					break;
			}
		} catch (Exception e) {
			System.out.println(line+" at cpt : "+cpt);
			cpt = 0;
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
