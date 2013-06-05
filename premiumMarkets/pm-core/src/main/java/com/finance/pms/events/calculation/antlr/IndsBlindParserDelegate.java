package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

public class IndsBlindParserDelegate implements IndsParserDelegate {

	@Override
	public RecognizerSharedState getState() {
		return null;
	}

	@Override
	public void checkParenthesis(String type) throws UnfinishedNestedCondition {
	
	}

	@Override
	public void checkOperationValidity(Token opName) {
		
	}

}
