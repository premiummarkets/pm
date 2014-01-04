/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With in mind beating buy and hold, Back testing, 
 * Automated buy sell email notifications on trend change signals calculated over markets 
 * and user defined portfolios. See Premium Markets FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com for documentation and a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.calculation.antlr;

import com.finance.pms.events.calculation.antlr.ANTLRParserHelper.AltType;
import com.finance.pms.events.calculation.antlr.NextToken.TokenType;

public class Alternative {
	
	private String altString;
	private String synoptic;
	private String description;
	private String defaultValue;
	
	private int[] highLighPosition;
	
	private TokenType tokenType;
	private AltType altType;

	
	public Alternative(AltType altType, TokenType tokenType, String altString, String description, String synoptic, String defaultValue, int[] highLighPosition) {
		super();
		this.altString = altString;
		this.description = description;
		this.synoptic = synoptic;
		this.tokenType = tokenType;
		this.defaultValue = defaultValue;
	
		this.highLighPosition= highLighPosition;
		this.altType = altType;
		
	}


	public String getAltString() {
		return altString;
	}

	public String getDescription() {
		return description;
	}

	public TokenType getTokenType() {
		return tokenType;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public String toString() {
		return "@ line " + highLighPosition[0] + ", start column "+ highLighPosition[1]  +" : '" + altString + "' : " + description;
	}

	public String getSynoptic() {
		return synoptic;
	}

	public int[] getHighLighPosition() {
		return highLighPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altString == null) ? 0 : altString.hashCode());
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((synoptic == null) ? 0 : synoptic.hashCode());
		result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alternative other = (Alternative) obj;
		if (altString == null) {
			if (other.altString != null)
				return false;
		} else if (!altString.equals(other.altString))
			return false;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (synoptic == null) {
			if (other.synoptic != null)
				return false;
		} else if (!synoptic.equals(other.synoptic))
			return false;
		if (tokenType != other.tokenType)
			return false;
		return true;
	}


	public void setHighLighPosition(int[] highLighPosition) {
		this.highLighPosition = highLighPosition;
	}


	public AltType getAltType() {
		return altType;
	}
	
}