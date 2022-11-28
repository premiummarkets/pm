/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.datasources.web.formaters;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private Pattern endRow;
	
	private Date date;
	private Integer cpt;
	private BigDecimal close;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal open;
	private Long volume;
	
	
	private Date firstValidDate;
	private List<Validatable> validatables;

	
	
	public DayQuoteInvestirFormater(MyUrl url, Stock stock, String currency, Date firstValidDate) {
		super(url);
		params.add(stock);
		params.add(currency);
		this.validatables = new ArrayList<Validatable>();
		this.firstValidDate = firstValidDate;
		
		try {
			if (null == DayQuoteInvestirFormater.PATTERNS)
				DayQuoteInvestirFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("", e);
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
			{
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
			}
			case 1 :
			{
				Matcher fitCloseQuotation = quotationPattern.matcher(line);
				if (fitCloseQuotation.find()) {
					close = new BigDecimal(numberFormat.parse(noNbsp(fitCloseQuotation.group(1))).toString()).setScale(4, RoundingMode.HALF_EVEN);
					cpt++;
				}
				
				break;
			}
			case 2 :
			{
				Matcher fitHighQuotation = quotationPattern.matcher(line);
				if (fitHighQuotation.find()) {
					high = new BigDecimal(numberFormat.parse(noNbsp(noNbsp(fitHighQuotation.group(1)))).toString()).setScale(4, RoundingMode.HALF_EVEN);
					cpt++;
				}
				break;
			}
			case 3 :
			{
				Matcher fitLowQuotation = quotationPattern.matcher(line);
				if (fitLowQuotation.find()) {
					low = new BigDecimal(numberFormat.parse(noNbsp(fitLowQuotation.group(1))).toString()).setScale(4, RoundingMode.HALF_EVEN);
					cpt++;
				}
				break;
			}
			case 4 :
			{
				Matcher fitOpenQuotation = quotationPattern.matcher(line);
				if (fitOpenQuotation.find()) {
					open = new BigDecimal(numberFormat.parse(noNbsp(fitOpenQuotation.group(1))).toString()).setScale(4, RoundingMode.HALF_EVEN);
					cpt++;
				}
				break;
			}
			case 5 :
			{
				Matcher fitEndRow = endRow.matcher(line);
				if (fitEndRow.find()) {
					volume = 0L;
					endRow(mainQuery);
				}
				Matcher fitVolumeQuotation = volumePattern.matcher(line);
				if (fitVolumeQuotation.find()) {
					String volumeString = noNbsp(fitVolumeQuotation.group(1));
					if (volumeString.isEmpty()) {
						volume = 0L;
					} else {
						volume = numberFormat.parse(volumeString).longValue();
					}
					endRow(mainQuery);
				}
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

	protected String noNbsp(String group1) {
		return group1.replace("&nbsp;","").replace("%20","").replaceAll("\\s", "");
	}

	private void endRow(LinkedList<Comparable<?>> mainQuery) {
		cpt = 0;
		if (date.after(firstValidDate) || date.equals(firstValidDate)) {
			mainQuery.add(date);
			mainQuery.add(open);
			mainQuery.add(high);
			mainQuery.add(low);
			mainQuery.add(close);
			mainQuery.add(volume);
			validatables.add(new DailyQuotation(mainQuery, (Stock) params.get(0), (String) params.get(1)));
		}
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return true;
	}

	@Override
	public Boolean isResultValueEqNA() {
		return validatables.isEmpty();
	}

}
