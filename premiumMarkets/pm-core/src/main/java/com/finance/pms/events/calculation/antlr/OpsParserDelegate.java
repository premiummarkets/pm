package com.finance.pms.events.calculation.antlr;

import java.util.List;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Token;

public interface OpsParserDelegate {
	
	public Boolean checkParamExhaust(Token opName, List<Object> params) throws ParamsCountException, UnfinishedParameterException, InvalidOperationException;
	
	public EditorOpDescr doesNeedClosing();

	public void outputSelectorHint(Token opToken, Token outputSelector) throws MissingOutputSelectorException, InvalidOperationException, NoViableAltException;
	
}
