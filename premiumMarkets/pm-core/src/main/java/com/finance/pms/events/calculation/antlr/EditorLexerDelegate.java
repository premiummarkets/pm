package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognizerSharedState;

import com.tictactec.ta.lib.MAType;

public class EditorLexerDelegate {
	
	public static final List<String> CONSTANT_TOKENS = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
	public static final List<String> HISTORICALDATA_TOKENS = new ArrayList<String>(Arrays.asList("close","open","high","low","volume"));
	
	public static final List<String> MATYPES_TOKENS;	
	static {
		MATYPES_TOKENS = new ArrayList<String>();		
		MAType[] maTypes = MAType.values();
		for (int i = 0; i < maTypes.length; i++) {
			MATYPES_TOKENS.add(maTypes[i].name());
		}
	}
	
	PartialTokenStatus partialTokenStatus;
	
	RecognizerSharedState state;
	CharStream input;

	public EditorLexerDelegate(RecognizerSharedState state, CharStream input) {
		super();
		this.partialTokenStatus = new PartialTokenStatus("", "", new int[]{0,0}, false,false);
		this.state = state;
		this.input = input;
	}

	public boolean runtimeHistoryOpAhead() {
		return lookAheadFor(HISTORICALDATA_TOKENS);
	}
	
	public boolean runtimeMATypeOpAhead() {
		return lookAheadFor(MATYPES_TOKENS);
	}

	private boolean lookAheadFor(List<String> histoTok) {
	
		for(String token : histoTok) {
			if(ahead(token)) {
				//System.out.println("Look ahead (histo) is true for "+this.partialTokenStatus + " and " + token);
				return true;
			}
		}
		//System.out.println("Look ahead (histo) is false at "+this.partialTokenStatus +" with ops "+histoTok);
		return false;
	}

	protected boolean ahead(String token) {
	
		if (!isSamePosition()) {
			this.partialTokenStatus = new PartialTokenStatus("", "", new int[]{state.tokenStartLine, state.tokenStartCharPositionInLine}, false, false);
		}
			
		String partialTok = "";
		for(int i = 0; i < token.length(); i++) {
			partialTok = partialTok + (char)input.LA(i+1);
			if(input.LA(i+1) != token.charAt(i)) {
				if (input.LA(i+1) == -1 &&  this.partialTokenStatus.getPartialToken().length() <= partialTok.length())  { 
					//End of line reach and Lexer first attempt at this location (Apparently, the Lexer tries the long token first then drop letters from the start of the token)???
					this.partialTokenStatus.setValid(true);
					this.partialTokenStatus.setIncomplete(true);
					this.partialTokenStatus.setPartialToken(partialTok);
					if (token.length() > this.partialTokenStatus.getLongestMatch().length()) this.partialTokenStatus.setLongestMatch(token);
				} else {
					if (this.partialTokenStatus.getPartialToken().length() <= partialTok.length()) {
						this.partialTokenStatus.setPartialToken(partialTok);
						//this.partialTokenStatus.setLongestMatch(token);
					}
				}
				return false;
			} 
		} 
		this.partialTokenStatus.setValid(true);
		this.partialTokenStatus.setIncomplete(false);
		this.partialTokenStatus.setPartialToken(token);
		if (token.length() > this.partialTokenStatus.getLongestMatch().length()) this.partialTokenStatus.setLongestMatch(token);
		return true; 
	}

	private Boolean isSamePosition() {
		return (state.tokenStartLine == this.partialTokenStatus.getTokenStartLocation()[0] && state.tokenStartCharPositionInLine <= this.partialTokenStatus.getTokenStartLocation()[1] + this.partialTokenStatus.getPartialToken().length());
	}

	protected boolean lookAheadFor(Set<EditorOpDescr> ops) {
	
		boolean foundCompleteMatch = false;
		for(EditorOpDescr op : ops) {
			if(ahead(op.getName())) {
				//System.out.println("Look ahead match found for "+this.partialTokenStatus + " and " + op);
				foundCompleteMatch = foundCompleteMatch || (this.partialTokenStatus.getValid() && !this.partialTokenStatus.getIncomplete());
			}
		}
		
		if (foundCompleteMatch) {
			//System.out.println("Look ahead is true for "+this.partialTokenStatus + " and " + ops);
		} else {
			//System.out.println("Look ahead is false at "+this.partialTokenStatus +" with ops "+ops);
		}
		
		return foundCompleteMatch;
	}

	public boolean runtimeOutputSelectorAhead() {
		return false;
	}

}
