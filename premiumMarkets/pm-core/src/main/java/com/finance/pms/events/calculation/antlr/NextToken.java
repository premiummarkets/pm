/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
