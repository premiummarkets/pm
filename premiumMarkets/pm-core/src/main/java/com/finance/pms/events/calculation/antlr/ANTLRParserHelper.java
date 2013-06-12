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
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.antlr.runtime.tree.CommonTree;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;
import com.finance.pms.events.operations.Operation;
import com.finance.pms.events.operations.nativeops.DoubleMapOperation;
import com.finance.pms.events.operations.nativeops.LeafOperation;

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
			parsedLine = ((line = bufferedReader.readLine()) != null)?line:"";
			while ((line = bufferedReader.readLine()) != null) {
				parsedLine = parsedLine + "\n"+ line;
			}
			inputStream.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return parsedLine;
	}

	protected SortedSet<EditorOpDescr> userCurrentOpEditorDescrs;
	protected SortedSet<EditorOpDescr> nativeOpEditorDescrs;

	public abstract CommonTree buildTree(InputStream inputStream)  throws Exception;
	
	public abstract NextToken checkNextToken(InputStream inputStream) throws IllegalArgumentException ;

	public void updateEditableOperationLists(Map<String, Operation> nativeOperations, Map<String, Operation> userCurrentOperations) {
		
		this.nativeOpEditorDescrs = new TreeSet<EditorOpDescr>();
		updateNativeOperationList(nativeOperations, this.nativeOpEditorDescrs);
		LOGGER.info("available native ops : "+this.nativeOpEditorDescrs);
		
		this.userCurrentOpEditorDescrs = new TreeSet<EditorOpDescr>();
		updateUserOperationList(userCurrentOperations, this.userCurrentOpEditorDescrs);
		LOGGER.info("available user ops : "+this.userCurrentOpEditorDescrs);
	}

	//XXX Native operation are supposed NOT to be a composition of other ops (is parameters can only be LeafOperation : Double or MapOfDouble). hence non reentrant either
	private void updateNativeOperationList(Map<String, Operation> userCurrentOperations, Set<EditorOpDescr> userCurrentOperationList) {
		
		for (String operationRef : userCurrentOperations.keySet()) {
			
			//Op
			Operation operation = userCurrentOperations.get(operationRef);
			EditorOpDescr editorOpDescr = new EditorOpDescr(operation.name(), operation.getDescription(), operation.synoptic(), operation.shortSynoptic());
			
			//Params
			List<Operation> operands = operation.getOperands();
			if (operands.size() == 0) {//undeterministic nb of operands
				//TODO infer type operand from the empty list
				editorOpDescr.addParam(editorOpDescr.new Param("undeterministic", DoubleMapOperation.class, "undeterministic", "undeterministic" ,null));
			} else {
				for (Operation operand : operands) {
					if (operand.getParameter() != null && !(operand instanceof LeafOperation)) {
						//XXX
						throw new UnsupportedOperationException(
								"Native operation are supposed NOT to be a composition of other ops (is parameters can only be LeafOperation : Double or MapOfDouble). hence non reentrant either :"+operation);
					}
					if (operand.getParameter() == null) {
						String defaultAsString = (operand.getDefaultValue() != null)?operand.getDefaultValue().getValue(null).toString():null;
						editorOpDescr.addParam(editorOpDescr.new Param(operand.getReferenceAsOperand(), operand.getClass(), operand.synoptic(), operand.getDescription(), defaultAsString));
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
			EditorOpDescr editorOpDescr = new EditorOpDescr(operation.name(), "Your operation.", operation.name()+"\nFormula : "+ operation.getFormula(), operation.name());
			
			//1rts level Operands sanity check
			for (Operation operand : operation.getOperands()) {
				if (operand.getParameter() == null) LOGGER.warn("Operands should be parameterized for user operation : "+operation);
			}
			
			userCurrentOperationList.add(editorOpDescr);
		}
	}

	protected LinkedList<Alternative> altPrioListForTokType(Map< AltType, SortedMap<Integer, LinkedList<Alternative>>> priorityList, AltType altType, int priority) {
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
				alternatives.add(new Alternative(AltType.SUGGESTION,TokenType.DATA, editorOpDescr.getName(), editorOpDescr.getDescription(), editorOpDescr.getSynoptic(), null, highLighPosition));
				foundMatch = true;
			}
		}
		for (EditorOpDescr editorOpDescr : userCurrentOpEditorDescrs) {
			if (currentTyping.equals(editorOpDescr.getName())) {
				foundMatch = true;
			} else if (editorOpDescr.getName().startsWith(currentTyping) && !currentTyping.equals(editorOpDescr.getName())) {
				alternatives.add(new Alternative(AltType.SUGGESTION,TokenType.DATA, editorOpDescr.getName(), editorOpDescr.getDescription(),editorOpDescr.getSynoptic(), null, highLighPosition));
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
			if (equalsMatch) {//exact match : tally but no adding
				foundMatch = true;
			} else {
				//boolean partialMatch = parsedText.length() >=3 && htoken.contains(parsedText);
				boolean startsWithMatch = htoken.startsWith(parsedText);
				if ((startsWithMatch) && !equalsMatch) {//partial match : adding
					alternatives.add(new Alternative(AltType.SUGGESTION,TokenType.DATA, htoken, descreptionPrefix + htoken, null, null, highLighPosition));
					foundMatch = true;
				}
			} 
		}
		return foundMatch;
	}

}
