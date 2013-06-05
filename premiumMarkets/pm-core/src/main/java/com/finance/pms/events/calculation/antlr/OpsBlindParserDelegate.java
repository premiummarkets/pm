package com.finance.pms.events.calculation.antlr;

import java.util.List;

import org.antlr.runtime.Token;

public class OpsBlindParserDelegate implements OpsParserDelegate {

	@Override
	public Boolean checkParamExhaust(Token opName, List<Object> params) throws ParamsCountException, UnfinishedParameterException {
		return true;
	}

	@Override
	public EditorOpDescr doesNeedClosing() {
		return null;
	}

	@Override
	public void outputSelectorHint(String opToken, Token outputSelector) throws MissingOutputSelectorException {
		
	}

}
