package com.finance.pms.events.calculation.antlr;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class UnfinishedNestedCondition extends RecognitionException {

	private static final long serialVersionUID = -2641005753375489777L;
	
	private String type;
	private String parsed;
	private Boolean wrongToken;
	private Boolean unfinishToken;
	
	public UnfinishedNestedCondition(IntStream input, String type, String parsed, Boolean wrongToken, Boolean unfinishToken) {
		super(input);
		this.type = type;
		this.parsed = parsed;
		this.wrongToken = wrongToken;
		this.unfinishToken = unfinishToken;
		
	}

	public String getType() {
		return type;
	}
	public String getParsed() {
		return parsed;
	}

	public Boolean getWrongToken() {
		return wrongToken;
	}

	public Boolean getUnfinishToken() {
		return unfinishToken;
	}

}
