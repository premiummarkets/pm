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
package com.finance.pms.events.calculation.antlr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.antlr.runtime.tree.CommonTree;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.LeafOperation;
import com.finance.pms.events.operations.nativeops.MapOperation;
import com.finance.pms.events.operations.nativeops.StringableValue;

public abstract class ANTLRParserHelper {

	static MyLogger LOGGER = MyLogger.getLogger(ANTLRParserHelper.class);


	public enum AltType {SUGGESTION, DELETE};

	public static int translatePositionToCaret(String formula, int lineNum, int lineOffset) {
		String[] editorTextLines = formula.split("\n", -1);
		int caretPos = 0;
		if (lineNum == -1) lineNum = editorTextLines.length; //Short cut to add up all lines when no line number is specified
		if (lineOffset == -1) {//Empty start of the next line must be shifted to end of previous line?
			lineNum = lineNum -1; 
			lineOffset = (lineNum > 0)?editorTextLines[lineNum-1].length()-1:-1;
		}
		for(int j = 0; j < lineNum -1; j++) {
			caretPos = caretPos + editorTextLines[j].length() +1;
		}
		int caretOffset = caretPos + lineOffset +1;
		return caretOffset;
	}

	public static int[] translateCaretToPosition(String formula, int caretPosition) {
		String[] editorTextLines = formula.split("\n", -1);
		int lineNum = 0;
		int lineOffset = 0;
		int length = 0;
		for(lineNum = 1; lineNum <= editorTextLines.length; lineNum++) {
			lineOffset = caretPosition - length;
			length = length + editorTextLines[lineNum-1].length() +1;
			if (caretPosition < length) break;
		}
		return new int[]{lineNum,lineOffset};
	}

	public static String parsedLine(InputStream inputStream) {
		String parsedLine = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			parsedLine = ((line = bufferedReader.readLine()) != null)? line : "";
			while ((line = bufferedReader.readLine()) != null) {
				parsedLine = parsedLine + "\n" + line;
			}
			inputStream.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return parsedLine;
	}

	protected ConcurrentSkipListSet<EditorOpDescr> userCurrentOpEditorDescrs;
	protected ConcurrentSkipListSet<EditorOpDescr> nativeOpEditorDescrs;

	public abstract CommonTree buildTree(InputStream inputStream)  throws Exception;

	public abstract NextToken checkNextToken(InputStream inputStream) throws IllegalArgumentException ;

	public void updateEditableOperationLists(Map<String, Operation> nativeOperations, Map<String, Operation> userCurrentOperations) {
		this.nativeOpEditorDescrs = new ConcurrentSkipListSet<EditorOpDescr>();
		updateNativeOperationList(nativeOperations, this.nativeOpEditorDescrs);
		//if (LOGGER.isDebugEnabled()) LOGGER.debug("available native ops : " + this.nativeOpEditorDescrs);

		this.userCurrentOpEditorDescrs = new ConcurrentSkipListSet<EditorOpDescr>();
		updateUserOperationList(userCurrentOperations, this.userCurrentOpEditorDescrs);
		//if (LOGGER.isDebugEnabled()) LOGGER.debug("available user ops : " + this.userCurrentOpEditorDescrs);
	}
	
	public boolean isInitialised() {
		return nativeOpEditorDescrs != null && userCurrentOpEditorDescrs != null;
	}

	//XXX Native operation are supposed NOT to be a composition of other ops (is parameters can only be LeafOperation : Double or MapOfDouble). hence non reentrant either
	private void updateNativeOperationList(Map<String, Operation> userCurrentOperations, Set<EditorOpDescr> userCurrentOperationList) {

		for (String operationRef : userCurrentOperations.keySet()) {

			//Op
			Operation operation = userCurrentOperations.get(operationRef);
			EditorOpDescr editorOpDescr = new EditorOpDescr(operation.name(), operation.getDescription(), operation.synoptic(), operation.shortSynoptic());

			//Params
			List<Operation> operands = operation.getOperands();
			if (operands.size() == 0) {//Indeterministic nb of operands
				//TODO infer type operand from the empty list
				editorOpDescr.addParam(editorOpDescr.new Param("undeterministic", MapOperation.class, "undeterministic", "undeterministic", null, false));
			} else {
				for (Operation operand : operands) {
					if (operand.getParameter() != null && !(operand instanceof LeafOperation)) {
						//XXX
						throw new UnsupportedOperationException(
								"A native operation is supposed NOT to be a composition of other ops (its parameters can only be LeafOperation : Double or MapOfDouble). Hence it is not reentrant either :" + operation);
					}
					if (operand.getParameter() == null) {
						String defaultAsString = (operand.getDefaultValue() != null)?((StringableValue) operand.getDefaultValue()).getAsStringable():null;
						try {
							editorOpDescr.addParam(editorOpDescr.new Param(operand.getReferenceAsOperand(), operand.getClass(), operand.synoptic(), operand.getDescription(), defaultAsString, operand.getIsVarArgs()));
						} catch (Exception e) {
							LOGGER.error("Failed parsing operation " + operation.toString(), e);
							throw e;
						}
					}
				}
			}

			//Output selector
			editorOpDescr.setOutputSelectors(operation.getAvailableOutputSelectors());

			userCurrentOperationList.add(editorOpDescr);
		}
	}

	//XXX User operation can't have empty parameters : we don't add any param editable by the user.
	private void updateUserOperationList(Map<String, Operation> userCurrentOperations, Set<EditorOpDescr> userCurrentOperationList) {

		for (String operationRef : userCurrentOperations.keySet()) {

			Operation operation = userCurrentOperations.get(operationRef);
			EditorOpDescr editorOpDescr = new EditorOpDescr(operation.name(), "Your operation.", operation.name()+"\nFormula : "+ operation.getFormulae(), operation.name());

			//			//1rts level Operands sanity check
			//			for (Operation operand : operation.getOperands()) {
			//				if (operand.getParameter() == null) LOGGER.warn("Operands should be parameterised for user operation : operand " + operand + " as no parameter for "+operation);
			//			}

			userCurrentOperationList.add(editorOpDescr);
		}
	}

	protected LinkedList<Alternative> altPrioListForTokType(Map<AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList, AltType altType, int priority) {
		SortedMap<Integer, LinkedList<Alternative>> tokenTypePrioList = priorityList.get(altType);
		if (tokenTypePrioList == null) {
			tokenTypePrioList = new TreeMap<Integer, LinkedList<Alternative>>();
			priorityList.put(altType, tokenTypePrioList);
		}
		LinkedList<Alternative> linkedList = tokenTypePrioList.get(priority);
		if (linkedList == null) {
			linkedList = new LinkedList<Alternative>();
			tokenTypePrioList.put(priority, linkedList);
		}
		return linkedList;
	}

	protected List<Alternative> addAllOpsAsAlts(String currentTyping, int[] position) throws IllegalArgumentException {
		List<Alternative> alternatives = new ArrayList<Alternative>();
		int[] highLighPosition = new int[]{position[0], position[1]};
		Boolean foundMatch = false;
		for (EditorOpDescr editorOpDescr : nativeOpEditorDescrs) {
			if (currentTyping.equals(editorOpDescr.getName())) {//exact match
				foundMatch = true;
			} else if (editorOpDescr.getName().startsWith(currentTyping) && !currentTyping.equals(editorOpDescr.getName())) {//partial match
				alternatives.add(new Alternative(AltType.SUGGESTION,TokenType.DATATOKEN, editorOpDescr.getName(), editorOpDescr.getDescription(), editorOpDescr.getSynoptic(), null, highLighPosition));
				foundMatch = true;
			}
		}
		for (EditorOpDescr editorOpDescr : userCurrentOpEditorDescrs) {
			if (currentTyping.equals(editorOpDescr.getName())) {
				foundMatch = true;
			} else if (editorOpDescr.getName().startsWith(currentTyping) && !currentTyping.equals(editorOpDescr.getName())) {
				alternatives.add(new Alternative(AltType.SUGGESTION,TokenType.DATATOKEN, editorOpDescr.getName(), editorOpDescr.getDescription(), editorOpDescr.getSynoptic(), null, highLighPosition));
				foundMatch = true;
			} 
		}
		if (!foundMatch) throw new IllegalArgumentException();
		return alternatives;
	}

	protected Boolean addSuggsAsAlts(List<Alternative> alternatives, String parsedText, int[] highLighPosition, String descreptionPrefix,  String...tokens) {
		return addSuggsAsAlts(alternatives, parsedText, highLighPosition, Arrays.asList(tokens), descreptionPrefix);
	}

	protected Boolean addSuggsAsAlts(List<Alternative> alternatives, String parsedText, int[] highLighPosition, Collection<String> tokens, String descreptionPrefix) {
		boolean foundMatch = false;
		for (String htoken : tokens) {
			boolean equalsMatch = parsedText.equals(htoken);
			if (equalsMatch) {	//exact match : taly but no adding
				foundMatch = true;
			} else {
				//boolean partialMatch = parsedText.length() >=3 && htoken.contains(parsedText);
				boolean startsWithMatch = htoken.startsWith(parsedText);
				if ((startsWithMatch) && !equalsMatch) {//partial match : adding
					alternatives.add(new Alternative(AltType.SUGGESTION, TokenType.DATATOKEN, htoken, descreptionPrefix + htoken, null, null, highLighPosition));
					foundMatch = true;
				}
			} 
		}
		return foundMatch;
	}

}
