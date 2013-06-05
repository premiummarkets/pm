package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.CharStream;

public class IndsBlindLexerDelegate implements IndsLexerDelegate {
	
	public static final List<String> HISTORICALDATA_TOKENS = new ArrayList<String>(Arrays.asList("close","open","high","low","volume"));
	
	CharStream input;
	
	public IndsBlindLexerDelegate(CharStream input) {
		super();
		this.input = input;
	}

	@Override
	public boolean runtimeHistoryOpAhead() {
		return true;
	}

	@Override
	public boolean runtimeOpAhead() {
		int i = 0;
		String token = "";
		while(Character.isLetter(input.LA(i+1))) {
			token = token + (char) input.LA(i+1);
			i++;
		}
		return  !HISTORICALDATA_TOKENS.contains(token);
	}

	@Override
	public boolean runtimeOutputSelectorAhead() {
		return true;
	}

}
