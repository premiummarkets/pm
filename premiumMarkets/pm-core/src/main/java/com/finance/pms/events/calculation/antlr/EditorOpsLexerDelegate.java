package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognizerSharedState;

public class EditorOpsLexerDelegate extends EditorLexerDelegate implements OpsLexerDelegate {
	
	public static final List<String> SYNTAX_TOKENS = new ArrayList<String>(Arrays.asList("(",")",",",":"));


	private Set<EditorOpDescr> runtimeNativeOps;
	private Set<EditorOpDescr> runtimeUserOps;

	
	public EditorOpsLexerDelegate(CharStream input, RecognizerSharedState state, Set<EditorOpDescr> runtimeNativeOps, Set<EditorOpDescr> runtimeUserOps) {
		super(state, input);
		
		this.runtimeNativeOps = runtimeNativeOps;
		this.runtimeUserOps = runtimeUserOps;

	}

	public boolean runtimeUserOpAhead() {
		return lookAheadFor(runtimeUserOps);
	}

	public boolean runtimeNativeOpAhead() {
		return lookAheadFor(runtimeNativeOps);
	}

	public PartialTokenStatus getPartialTokenStatus() {
		return partialTokenStatus;
	}

}
