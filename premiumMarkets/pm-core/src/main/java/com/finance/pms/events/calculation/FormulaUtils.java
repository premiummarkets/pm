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
package com.finance.pms.events.calculation;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FormulaUtils {
	
	
	public static String[] indentOperationFormula(String formula, int previousCaretPosition) {
		
		int newCarretPosition = previousCaretPosition;
		String preCaret = formula.substring(0, previousCaretPosition);
		String postCaret = formula.substring(previousCaretPosition, formula.length());
		
		String formatedPreCaret = preCaret.replaceAll("[\t\n]", "").replaceAll(" +", " ");
		String formatedPostCaret = postCaret.replaceAll("[\t\n]", "").replaceAll(" +", " ");
		
		newCarretPosition = previousCaretPosition - (preCaret.length() - formatedPreCaret.length());
		String formatedF = formatedPreCaret + formatedPostCaret;
		
		String[] keywordBeforeCR = new String[]{"override start shift with", "also display", "is bullish when ", "is bearish when ", "is bearish if not bullish and ", "is bearish if not bullish or ", ";", "(", ")"};
		String[] keyWordAfterCR = new String[]{" or ", " and ", "(", ")"};

		String indentStr = "";
		Stack<String> indentStack = new Stack<String>();
		indentStack.push(indentStr);
		
		List<String> openingIdent = Arrays.asList(new String[]{ "("});
		List<String> closingIdent = Arrays.asList(new String[]{")"});

		for (int i = 0; i < formatedF.length(); i++) {
			
			for (String keyw : keyWordAfterCR) {

				int kLength = keyw.length();
				if (i <= formatedF.length() - kLength && formatedF.substring(i, i+kLength).equals(keyw)) {
					
					//indent
					if (openingIdent.contains(keyw)) {
						indentStr = indentStack.peek()+"\t";
						indentStack.push(indentStr);
					}

					if (closingIdent.contains(keyw)) {
						indentStack.pop();
						indentStr = indentStack.peek();
					}
					
					//cr
					String prefix = formatedF.substring(0,i);
					String suffix = formatedF.substring(i);
					
					int lastCridx = prefix.length()-1;
					while (lastCridx >= 0  && (prefix.charAt(lastCridx) == '\t') || prefix.charAt(lastCridx) == ' ') {
						lastCridx--;
					}
					if (prefix.length() > 1 && prefix.charAt(lastCridx) != '\n') {
						formatedF = prefix + "\n"+ indentStr + suffix;
						if (i < newCarretPosition) newCarretPosition++;
						i = i+indentStr.length()+1;
					}
				}
			}
			
			for (String keyw : keywordBeforeCR) {

				int kLength = keyw.length();
				if (i < formatedF.length() - kLength && formatedF.substring(i, i+kLength).equals(keyw)) {
					String prefix = formatedF.substring(0,i+kLength);
					String suffix = formatedF.substring(i+kLength);
					if (openingIdent.contains(keyw)) {
						formatedF = prefix + "\n"+ indentStr + suffix;
					} else {
						formatedF = prefix + "\n" + suffix;
					}
					if (i < newCarretPosition) newCarretPosition++;
					i = i + kLength;
				}
			}
			
		}
		
		return new String[]{formatedF.substring(0, newCarretPosition), formatedF.substring(newCarretPosition)};
	}
	
	public static String bullishClause(String formula) {
		formula = formula.replaceAll("\n", "_-_");
		formula = formula.replaceAll("_-_;.*", "").replaceAll(";.*", "");
		return formula.replaceAll("_-_", "\n").replaceAll("\t", "\t\t");
	}
	
	public static String bearishClause(String formula) {
		formula = formula.replaceAll("\n", "_-_");
		formula = formula.replaceAll(".*;.*is bearish", "is bearish").replaceAll(";.*", "");
		return formula.replaceAll("_-_", "\n").replaceAll("\t", "\t\t");
	}
	
	public static String alsoDisplayClause(String formula) {
		
		if (!formula.contains("also display")) return "";
		
		formula = formula.replaceAll("\n", "_-_");
		formula = formula.replaceAll(".*;.*also display", "Also available for display :").replaceAll(";.*", "");
		return formula.replaceAll("_-_", "\n").replaceAll("\t", "\t\t");
	}

}
