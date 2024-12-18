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
package com.finance.pms.datasources.shares;

import java.security.InvalidAlgorithmParameterException;

import com.finance.pms.admin.install.logging.MyLogger;



// TODO: Auto-generated Javadoc
//ISIN
//
//An International Securities Identification Number (ISIN) is an alphanumeric code that uniquely identifies a security.
//
//An ISIN consists of a two character country code, a nine digit number and one check digit.
//
//Each country where ISIN numbers are used has a single national numbering agency to assign numbers using that country code. This guarantees that the same ISIN cannot be issued to two different securities.
//
//ISINs are not the only codes used to identify securities. They are not even always the most useful. One problem is that nine digit numbers are not very memorable. For this reason, most exchanges have other coding systems that are easier for human beings to use (to call up quotes or enter trades), while the computers that handle clearing and settlement are more likely to use ISIN codes.
//
//Other important codes are TIDM (used for UK listed securities), SEDOL (London Stock Exchange Codes), CUISIP (the US and Canadian equivalent of a SEDOL code), Reuters codes, and Bloomberg codes.
//
//For example Marks and Spencer's ordinary shares have the TIDM MKS, Reuters code MKS.L, Bloomberg code MKS LN, SEDOL 3127489 and ISIN GB0031274896. It also has an ADR with a CUISIP of 570912105.
//
//It can be seen from this example that Reuters and Bloomberg codes are based on the EPIC together with an extension to identify the London market, and that the ISIN for UK companies is based on the SEDOL together with a country code (GB) and a check digit (the 6 at the end in the example above).



/**
 * How identification does work? To identify a stock or a share you have two
 * options : either you have the isin code and in that case everything is fine,
 * or you don't have it then good luck.
 * In the second case my assumption is now that you can use some kind of symbol (TIDM, SEDOL or what ever) combine
 * with the market where that symbol is used.
 * The market value and the download url have to be linked together.
 * For the display the symbol will preferably
 * being used so a fake one as to be produced form the isin code when non symbol
 * as been provided at download time (which could be the isin it self?).
 * Another matter is that every quotation provider uses its own symbol set.
 * This symbol value should then be calculated when needed using the symbol, isin and the provider type.
 * Hence we need
 * - Market definition including :
 * a name (and an extension) that will be added to the symbol to qualify it,
 * an url (or a Provider) to pick up the list of shares from.
 * - The stock should have :
 * an isin attribute (wish could be null?),
 * a symbol wish as to be calculated either using the isin and/or adding the market extension.
 * a quotation provider type to calculate the symbol used for quotation retrieval
 * a market provider to indicating the update list the stock comes from
 */
 
/**
 * The Enum Markets.
 * 
 * @author Guillaume Thoreton
 */
public abstract class SymbolNameResolver {

	private static MyLogger LOGGER = MyLogger.getLogger(SymbolNameResolver.class);
	
	public static String UNKNOWNEXTENSIONCLUE = "NN";
	public static String UNKNOWNEXTENSION = "";
	
	public abstract String resolveExtensionFromClue(String extensionClue);
	
	public abstract String resolveClueFromExtension(String extension);
	
	public String getFullSymbol(String symbol, String extensionClue) throws InvalidAlgorithmParameterException {
		
		String newSymbol;

		if (symbol == null || symbol.equals(Stock.MISSINGCODE)) {
			//LOGGER.error("ERROR : don't want to set a null symbol, find somthing else :).");
			//throw new InvalidAlgorithmParameterException("ERROR : don't want to set a null symbol, find somthing :).",new Throwable());
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Warning : don't want to set a null symbol, can't you find something else :).");
			return null;
		}

		if (SymbolNameResolver.UNKNOWNEXTENSIONCLUE.equals(extensionClue)) {
			if ((symbol.contains("."))) {
				//TODO Throw exception??
				//symbol = symbol.substring(0, symbol.length() - 3);
			}
			return symbol;
		}
		
		String extension = this.resolveExtensionFromClue(extensionClue);
		String dotSuffixe = ("".equals(extension))?"":"." + this.resolveExtensionFromClue(extensionClue);

		if ((symbol.contains("."))) {
			String symbolDotSuffix = symbol.substring(symbol.indexOf("."), symbol.length());
			if (!symbolDotSuffix.equals(dotSuffixe)) {
				LOGGER.warn("WARN : Invalid match between symbol :" + symbol
						+ " suffix - extension Clue :" + dotSuffixe+" - "+extensionClue
						+ " while setting symbol");
				newSymbol = symbol.substring(0, symbol.indexOf(".")) + dotSuffixe;
			} else {
				newSymbol = symbol;
			}
		} else {
			newSymbol = symbol + dotSuffixe;
		}
		return newSymbol;
	}
	

	public String getExtensionClueFromSymbol(String symbol) {
		String extensionClue = SymbolNameResolver.UNKNOWNEXTENSIONCLUE;
		if ((symbol.contains("."))) {
			String extension = getExtension(symbol);
			extensionClue = this.resolveClueFromExtension(extension);
		}
		return extensionClue;
	}

	public String getExtension(String symbol) {
		int dotpos  = symbol.lastIndexOf(".");
		String extension = symbol.substring(dotpos+1, symbol.length());
		return extension;
	}
	
	
}
