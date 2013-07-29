package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class EditorParserDelegate {

	protected Set<EditorOpDescr> allNativeOps;
	TokenStream input;

	public EditorParserDelegate(TokenStream input, Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		this.allNativeOps =  aggAllOperations(runtimeNativeOps, runtimeUserOps);
		this.input = input;
	}

	public EditorParserDelegate(CommonTokenStream input, Set<EditorOpDescr> allOps) {
		this.allNativeOps =  allOps;
		this.input = input;
	}

	protected EditorOpDescr grabOpForToken(String parsedOp) {
		for(EditorOpDescr op : allNativeOps) {
			if (op.getName().equals(parsedOp)) {
				try {
					return (EditorOpDescr) op.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	protected EditorOpDescr grabOpForToken(Token parsedOp) {
		return grabOpForToken(parsedOp.getText());
	}

	protected Set<EditorOpDescr> aggAllOperations(Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		Set<EditorOpDescr> allOps = new TreeSet<EditorOpDescr>();
		allOps.addAll(runtimeNativeOps);
		allOps.addAll(runtimeUserOps);
		return allOps;
	}

	public void outputSelectorHint(Token opName, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException, NoViableAltException {
		
	
		EditorOpDescr currentOp = grabOpForToken(opName);
		String outputSelectorStr = (outputSelector != null)? outputSelector.getText():null;
		
		if (currentOp == null) {
			//Partial op matching
			throw new InvalidOperationException(input, opName, null);
		}
		
		List<String> outputSelectors = currentOp.getOutputSelectors();
		if (outputSelectors != null && !outputSelectors.isEmpty()) {//output selector needed
			List<String> editSelector = new ArrayList<String>();
			for (String selector : outputSelectors) {
				editSelector.add(":"+selector);
			}
			if (!editSelector.contains(outputSelectorStr)) {
				throw new MissingOutputSelectorException(input, currentOp, outputSelector, editSelector.toArray(new String[0]));
			}
		} else {//no output selector needed
			if (outputSelectorStr != null) {
				throw new MissingOutputSelectorException(input, currentOp, outputSelector);
			}
		}
		
	}

}
