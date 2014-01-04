/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.MyUrl;

public class DayQuoteInvestirFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
	
	private Pattern quotationPattern;
	private Pattern datePattern;
	private Pattern volumePattern;
	private Pattern endTable;
	private Pattern noData;
	
	private Date date;
	private Integer cpt;
	private BigDecimal close;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal open;
	private Long volume;
	
	private List<Validatable> validatables;
	//private Currency currency;
	private Pattern endRow;
	
	
	public DayQuoteInvestirFormater(MyUrl url, Stock stock, String currency) {
		super(url);
		params.add(stock);
		params.add(currency);
		//this.currency = Currency.valueOf(currency);
		this.validatables = new ArrayList<Validatable>();
		
		try {
			if (null == DayQuoteInvestirFormater.PATTERNS)
				DayQuoteInvestirFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		datePattern = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirdateline"));
		quotationPattern = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirquotationline"));
		volumePattern = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirvolumeline"));
		endTable = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirendtableline"));
		noData = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirnodataline"));
		endRow = Pattern.compile(DayQuoteInvestirFormater.PATTERNS.getProperty("investirendrow"));
		cpt = 0;
	
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
		LOGGER.trace(line);
		LinkedList<Comparable<?>> mainQuery = new LinkedList<Comparable<?>>();
		line = line.trim();
		
		try {
			switch (cpt) {
			case 0 :
				
				Matcher fitNoData = noData.matcher(line);
				if (fitNoData.find()) {
					throw new StopParseErrorException("No data for "+params.get(0)+" at "+params.get(1)+"\n","");
				}
				Matcher fitDate = datePattern.matcher(line);
				if (fitDate.find()) {
					date = dateFormat.parse(fitDate.group(1));
					cpt++;
				}
				break;
			case 1 :
				Matcher fitCloseQuotation = quotationPattern.matcher(line);
				if (fitCloseQuotation.find()) {
					close = new BigDecimal(numberFormat.parse(fitCloseQuotation.group(1)).toString()).setScale(2,BigDecimal.ROUND_DOWN);
//					Currency quotationCurrency = Currency.valueOf(fitCloseQuotation.group(2));
//					if (!quotationCurrency.equals(currency)) {
//						throw new StopParseErrorException("Currency inconsitency with "+params.get(0)+" "+params.get(1)+" "+myUrl, "");
//					}
					cpt++;
				}
				
				break;
			case 2 :
				Matcher fitHighQuotation = quotationPattern.matcher(line);
				if (fitHighQuotation.find()) {
					high = new BigDecimal(numberFormat.parse(fitHighQuotation.group(1)).toString()).setScale(2,BigDecimal.ROUND_DOWN);
					cpt++;
				}
				break;
			case 3 :
				Matcher fitLowQuotation = quotationPattern.matcher(line);
				if (fitLowQuotation.find()) {
					low = new BigDecimal(numberFormat.parse(fitLowQuotation.group(1)).toString()).setScale(2,BigDecimal.ROUND_DOWN);
					cpt++;
				}
				break;
			case 4 :
				Matcher fitOpenQuotation = quotationPattern.matcher(line);
				if (fitOpenQuotation.find()) {
					open = new BigDecimal(numberFormat.parse(fitOpenQuotation.group(1)).toString()).setScale(2,BigDecimal.ROUND_DOWN);
					cpt++;
				}
				break;
			case 5 :
				Matcher fitEndRow = endRow.matcher(line);
				if (fitEndRow.find()) {
					volume = 0L;
					endRow(mainQuery);
				}
				Matcher fitVolumeQuotation = volumePattern.matcher(line);
				if (fitVolumeQuotation.find()) {
					String volumeString = fitVolumeQuotation.group(1);
					if (volumeString.isEmpty()) {
						volume = 0L;
					} else {
						volume = numberFormat.parse(volumeString).longValue();
					}
					endRow(mainQuery);
				}
				
			}
		} catch (NumberFormatException e) {
			throw new StopParseErrorException("Invalid investir quotations for "+params.get(0)+"\n", e.getMessage());
		} catch (ParseException e) {
			throw new StopParseErrorException("Invalid investir quotations for "+params.get(0)+"\n", e.getMessage());
		}
		

		if (validatables.size() > 0)	{
			Matcher fitEnd = endTable.matcher(line);
			if (fitEnd.find()) {
				return validatables;
			}
		}

		return null;
	
	}

	/**
	 * @param mainQuery
	 */
	private void endRow(LinkedList<Comparable<?>> mainQuery) {
		cpt = 0;
		mainQuery.add(date);
		mainQuery.add(open);
		mainQuery.add(high);
		mainQuery.add(low);
		mainQuery.add(close);
		mainQuery.add(volume);
		validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
	}

	@Override
	public Boolean canHaveEmptyResults() {
		return false;
	}

}
