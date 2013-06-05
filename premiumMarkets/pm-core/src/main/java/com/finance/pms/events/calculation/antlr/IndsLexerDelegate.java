package com.finance.pms.events.calculation.antlr;

public interface IndsLexerDelegate {

	boolean runtimeHistoryOpAhead();
	boolean runtimeOpAhead();
	boolean runtimeOutputSelectorAhead();

}
