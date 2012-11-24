/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * Around 80% of predicted trades more profitable than buy and hold, leading to 4 times 
 * more profit, while back testing over NYSE, NASDAQ, EURONEXT and LSE, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.datasources.files;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import com.finance.pms.datasources.shares.Currency;

public class GnuCashParserHelper {

	BigDecimal calculateBigDecimal(String textContent) throws ParseException {
		Number number = extractNumber(textContent);
		try {
			return new BigDecimal(number.doubleValue()).setScale(2, BigDecimal.ROUND_DOWN);
		} catch (NumberFormatException e) {
			GnuCashAdvPortfolioParser.LOGGER.error("can't format to BigD",e);
		}
		throw new ParseException("",0);
	}

	Currency extractCurrency(String columnTxt) {
		GnuCashAdvPortfolioParser.LOGGER.debug("Checking if :"+columnTxt+" contains $?");
		if (columnTxt.contains("$")) {
			GnuCashAdvPortfolioParser.LOGGER.debug(columnTxt+" contains $!");
			return Currency.USD;
		}
		GnuCashAdvPortfolioParser.LOGGER.debug("Checking if :"+columnTxt+" contains \u00A3?");
		if (columnTxt.contains("\u00A3")) {
			GnuCashAdvPortfolioParser.LOGGER.debug(columnTxt+"contains \u00A3!");
			return Currency.GBP;
		}
		return   Currency.valueOf(columnTxt.replace("\n","").trim().split("( |\n)")[0]);
	}
	
	/**
	 * @param textContent
	 * @return
	 * @throws ParseException
	 */
	Number extractNumber(String textContent) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getInstance();
		Number number = numberFormat.parse(textContent.replaceAll("($|\u00A3)","").replaceAll("[A-Z][A-Z][A-Z]( |\n)*", "").trim());
		return number;
	}

	/**
	 * @param amount
	 * @param transactionCurrency
	 * @return
	 */
	BigDecimal unitConvertion(BigDecimal amount, Currency transactionCurrency) {
		return transactionCurrency.translateToQuotationUnit(amount);
	}
}
