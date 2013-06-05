package com.finance.pms.events.calculation.antlr;

public interface OpsLexerDelegate {
	

	public boolean runtimeUserOpAhead();
	public boolean runtimeHistoryOpAhead();
	public boolean runtimeNativeOpAhead();
	public boolean runtimeOutputSelectorAhead();

}
