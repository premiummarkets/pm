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