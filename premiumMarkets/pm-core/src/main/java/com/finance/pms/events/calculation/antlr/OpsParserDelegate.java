package com.finance.pms.events.calculation.antlr;

import java.util.List;

import org.antlr.runtime.Token;

public interface OpsParserDelegate {
	
	public Boolean checkParamExhaust(Token opName, List<Object> params) throws ParamsCountException, UnfinishedParameterException, InvalidOperationException;
	
	public EditorOpDescr doesNeedClosing();

	public void outputSelectorHint(String opToken, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException;
	
}
