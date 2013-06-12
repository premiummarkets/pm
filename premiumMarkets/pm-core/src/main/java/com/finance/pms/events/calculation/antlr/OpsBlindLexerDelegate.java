package com.finance.pms.events.calculation.antlr;

public class OpsBlindLexerDelegate implements OpsLexerDelegate {

	@Override
	public boolean runtimeUserOpAhead() {
		return true;
	}

	@Override
	public boolean runtimeHistoryOpAhead() {
		return true;
	}

	@Override
	public boolean runtimeNativeOpAhead() {
		return true;
	}

	@Override
	public boolean runtimeOutputSelectorAhead() {
		return true;
	}

	@Override
	public boolean runtimeMATypeOpAhead() {
		return true;
	}

}
