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
package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import com.finance.pms.datasources.shares.Currency;


//TODO Merge the rest of transaction and Portfolio Gnu parser commons.
public class GnuCashParserHelper {

	protected BigDecimal calculateBigDecimal(String textContent) throws ParseException {
		try {
			Number number = extractNumber(textContent);
			return new BigDecimal(number.doubleValue()).setScale(4, BigDecimal.ROUND_HALF_EVEN);
		} catch (ParseException e) {
//			GnuCashAdvPortfolioParser.LOGGER.error("can't format to BigD",e);
			throw e;
		}
	}

	protected Currency extractCurrency(String columnTxt) {
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
	
	protected Number extractNumber(String textContent) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getInstance();
		Number number = numberFormat.parse(textContent.replaceAll("($|\u00A3)","").replaceAll("[A-Z][A-Z][A-Z]( |\n)*", "").trim());
		return number;
	}

	public StringWriter deleteDocType(String filePath) throws FileNotFoundException, IOException {
		//"http://www.w3.org/TR/2000/REC-xhtml1-20000126/DTD/xhtml1-strict.dtd"
		//change html to xml
		String firstLine = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 TRANSITIONAL//EN\">";
		String firstLine2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"";
		String firstLine3 = "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		String firstLine4 = "xmlns=\"http://www.w3.org/1999/xhtml\"";
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringWriter outputWriter = new StringWriter();

		String line = null;

		//Read from the original file and write to the new
		//unless content matches data to be removed.
		int startOfFile = 0;
		outputWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
		while ((line = br.readLine()) != null) {
			
			if (startOfFile < 10) {
				line = line.replaceAll(firstLine, "");
				line = line.replaceAll(firstLine2, "");
				line = line.replaceAll(firstLine3, "");
				line = line.replaceAll(firstLine4, "");
			}
			
			//if (!line.trim().equals(firstLine)) {
			if (!line.trim().isEmpty()) {
				outputWriter.write(line);
				outputWriter.flush();
			}
			
			startOfFile++;
		}
		outputWriter.close();
		br.close();
		return outputWriter;
	}
}
