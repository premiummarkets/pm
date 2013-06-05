package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;


public interface IndsParserDelegate {

	RecognizerSharedState getState();

	void checkParenthesis(String type) throws UnfinishedNestedCondition;

	void checkOperationValidity(Token opName) throws InvalidOperationException;

}
