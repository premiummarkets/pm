package com.finance.pms.events.calculation.antlr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.finance.pms.events.calculation.antlr.ANTLRParserHelper.AltType;

public class NextToken {
	
	public enum TokenType {CONSTANTTOKEN,DATATOKEN,SYNTAX,DELETE,KEYWORDS};
	
	private List<Alternative> alternatives;
	
	public NextToken() {
		this.alternatives = new ArrayList<Alternative>();
	}

	public void addAlternative(Alternative alternative) {
		this.alternatives.add(alternative);
	}
	
	public void addAllAlternative(Collection<Alternative> alternatives) {
		for (Alternative alternative : alternatives) {
			if (!this.alternatives.contains(alternative)) this.alternatives.add(alternative);
		}
	}

	public List<Alternative> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
	}
	
	public List<Alternative> extractSyntaxErrors() {
		List<Alternative> ret = new ArrayList<Alternative>();
		for (Alternative alternative : alternatives) {
			if (alternative.getTokenType().equals(TokenType.SYNTAX)) ret.add(alternative);
		}
		return ret;
	}

	@Override
	public String toString() {
		String ret = "";
		AltType altType = null;
		for (Alternative alternative : alternatives) {
				if (altType == null) {
					altType = alternative.getAltType();
					ret = altType.name().toLowerCase()+":\n";
				} else if (!altType.equals(alternative.getAltType())) {
					altType = alternative.getAltType();
					ret = ret +  altType.name().toLowerCase()+":\n";
				}
				ret = ret + alternative.toString() + "\n";
		}
		return ret;
	}
	
	
	
}
