package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.RecognizerSharedState;

public class EditorIndsLexerDelegate extends EditorLexerDelegate implements IndsLexerDelegate {
	
	public static final List<String> SYNTAX_TOKENS = new ArrayList<String>(Arrays.asList("\n"," ",";","(",")","%"));

	private Set<EditorOpDescr> runtimeOps;

	public EditorIndsLexerDelegate(ANTLRInputStream in, RecognizerSharedState state, Set<EditorOpDescr> runtimeOps) {
		super(state, in);
		this.runtimeOps = runtimeOps;
	}

	@Override
	public boolean runtimeOpAhead() {
		return lookAheadFor(runtimeOps);
	}

}
