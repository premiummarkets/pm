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
		
		String[] keywordBeforeCR = new String[]{"is bullish when ", "is bearish when ", "is bearish if not bullish and ", "is bearish if not bullish or ", ";", "(", ")"};
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
		return formula.replaceAll("_-_", "\n");
	}
	
	public static String bearishClause(String formula) {
		formula = formula.replaceAll("\n", "_-_");
		formula = formula.replaceAll(".*;.*is bearish", "is bearish").replaceAll(";.*", "");
		return formula.replaceAll("_-_", "\n");
	}

}
