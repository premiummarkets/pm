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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.currency.CurrencyRate;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.web.MyUrl;

public class CurrencyOandaHistoryFormater extends LineFormater {
	
	private static PatternProperties PATTERNS;
	private static MyLogger LOGGER = MyLogger.getLogger(CurrencyOandaHistoryFormater.class);

	private Pattern oandaTableStartLine;
	private Pattern oandaTableEndLine;
	
	private Currency fromCurrency;
	private Currency toCurrency;

	private Boolean started = Boolean.FALSE;

	Validatable lastParsed;

	public CurrencyOandaHistoryFormater(Currency fromCurrency, Currency toCurrency, String url) {
		super(new MyUrl(url));
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		
		try {
			if (null == CurrencyOandaHistoryFormater.PATTERNS) CurrencyOandaHistoryFormater.PATTERNS = new PatternProperties("patterns.properties");
		} catch (IOException e) {
			LOGGER.debug("", e);
		}

		oandaTableStartLine = Pattern.compile(CurrencyOandaHistoryFormater.PATTERNS.getProperty("oandatablestartline"));
		oandaTableEndLine = Pattern.compile(CurrencyOandaHistoryFormater.PATTERNS.getProperty("oandatableendline"));
	}

	@Override
	public List<Validatable> formatLine(String line) throws StopParseException {
		
//		System.out.println(line);
		if (started) {

			List<Validatable> ret = new ArrayList<Validatable>();

			Matcher fit = oandaTableEndLine.matcher(line.toUpperCase());
			if (fit.find()) {
				throw new StopParseFoundException(lastParsed);
			} else {
				if (lastParsed != null) ret.add(lastParsed);
			}

			//03.06.1997,5.74730
			try {

				String netLine = line.toUpperCase().replaceAll("<PRE>", "");
				String[] splits = netLine.split(",");

				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(splits[0].trim());
				BigDecimal value = new BigDecimal(splits[1].trim());
				Validatable rateLine = new CurrencyRate(fromCurrency, toCurrency, date, value);
				lastParsed = rateLine;
				return ret;

			} catch (Exception e) {
				LOGGER.warn("Unparsable line : "+line, e);
			}
			
		} else {
			Matcher fit = oandaTableStartLine.matcher(line.toUpperCase());
			if (fit.find()) {
				started = true;
			}
		}
		
		return null;
	
	}

	@Override
	public Boolean canHaveNoResultsFound() {
		return false;
	}

}
