package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;
import com.finance.pms.events.calculation.DateFactory;

public class DayQuote1818Formater extends LineFormater {

	private static PatternProperties PATTERNS = null;
	
	private Stock stock;

	private Pattern datePattern;
	private Pattern quotationPattern;
	private Pattern volumePattern;
	private int cpt;
	
	private LocalDate date;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;

	public DayQuote1818Formater(MyUrl myUrl, Stock stock) {
		super(myUrl);
		this.stock = stock;
		
		try {
			if (null == DayQuote1818Formater.PATTERNS)
				DayQuote1818Formater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
		}

		datePattern = Pattern.compile(DayQuote1818Formater.PATTERNS.getProperty("1818dateline"));
		quotationPattern = Pattern.compile(DayQuote1818Formater.PATTERNS.getProperty("1818quotationline"));
		volumePattern = Pattern.compile(DayQuote1818Formater.PATTERNS.getProperty("1818volumeline"));
		
		cpt = 0;
	}

//	<th class="date">DATE</th>
//	<th class="last">Premier</th>
//	<th class="last">+ Haut</th>
//	<th class="last">+ Bas</th>
//	<th class="last">Dernier</th>
//	<th class="last">Volume</th>
	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		List<Validatable> ret = new ArrayList<>();
	
		try {
			switch (cpt) {
			case 0 :
				Matcher fitDate = datePattern.matcher(line);
				if (fitDate.find()) {
					if (LOGGER.isDebugEnabled()) LOGGER.debug("Date : "+line+" at cpt : "+cpt);
					date = LocalDate.parse(fitDate.group(1), DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE));
					cpt ++;
				}
				break;
			case 1 :
				Matcher fitOpen = quotationPattern.matcher(line);
				if (fitOpen.find()) {
				    if (LOGGER.isDebugEnabled()) LOGGER.debug("open : "+line+" at cpt : "+cpt);
					open = new BigDecimal(numberTransform(fitOpen));
					cpt ++;
				}
				break;
			case 2 :
				Matcher fitHigh = quotationPattern.matcher(line);
				if (fitHigh.find()) {
				    if (LOGGER.isDebugEnabled()) LOGGER.debug("high : "+line+" at cpt : "+cpt);
					high = new BigDecimal(numberTransform(fitHigh));
					cpt ++;
				}
				break;
			case 3 :
				Matcher fitLow = quotationPattern.matcher(line);
				if (fitLow.find()) {
				    if (LOGGER.isDebugEnabled()) LOGGER.debug("low : "+line+" at cpt : "+cpt);
					low = new BigDecimal(numberTransform(fitLow));
					cpt ++;
				}
				break;
			case 4 :
				Matcher fitClose = quotationPattern.matcher(line);
				if (fitClose.find()) {
				    if (LOGGER.isDebugEnabled()) LOGGER.debug("close : "+line+" at cpt : "+cpt);
					close = new BigDecimal(numberTransform(fitClose));
					cpt ++;
				}
				break;
			case 5 :
				Matcher fitVolume = volumePattern.matcher(line);
				if (fitVolume.find()) {
				    if (LOGGER.isDebugEnabled()) LOGGER.debug("volume : " + line + " at cpt : " + cpt);
					LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(date.atTime(0, 0).toEpochSecond(ZoneOffset.UTC)*1000);
					mainQuery.add(DateFactory.midnithDate(calendar.getTime()));
					mainQuery.add(open);
					mainQuery.add(high);
					mainQuery.add(low);
					mainQuery.add(close);
					mainQuery.add(Long.valueOf(numberTransform(fitVolume)));
					mainQuery.add(null); //Split
					ret.add(new DailyQuotation(mainQuery, stock, stock.getMarketValuation().getCurrency().name()));
					cpt = 0;
				}
				break;
			}
		} catch (Exception e) {
		    if (LOGGER.isDebugEnabled()) LOGGER.debug(line + " at cpt : " + cpt);
			cpt = 0;
			e.printStackTrace();
		}
		
		return ret;
	}

	protected String numberTransform(Matcher matcher) {
		return matcher.group(1).replaceAll("\\p{javaSpaceChar}","").replace("&nbsp;", "").replace(" ", "").replace(',', '.');
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

}
