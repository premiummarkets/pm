package com.finance.pms.events.calculation.antlr;

import java.util.Arrays;

public class PartialTokenStatus {
	
	private int[] tokenStartLocation;
	private Boolean valid;
	private Boolean incomplete;
	private String partialToken;
	private String longestMatch;
	
	public PartialTokenStatus(String token, String longestMatch, int[] tokenStartLocation, Boolean valid, Boolean incomplete) {
		super();
		this.partialToken = token;
		this.longestMatch = longestMatch;
		this.tokenStartLocation = tokenStartLocation;
		this.valid = valid;
		this.incomplete = incomplete;
		
	}


	public String getPartialToken() {
		return partialToken;
	}

	public int[] getTokenStartLocation() {
		return tokenStartLocation;
	}

	public Boolean getValid() {
		return valid;
	}

	public Boolean getIncomplete() {
		return incomplete;
	}

	public void setTokenStartLocation(int[] tokenStartLocation) {
		this.tokenStartLocation = tokenStartLocation;
	}


	public void setValid(Boolean valid) {
		this.valid = valid;
	}


	public void setIncomplete(Boolean incomplete) {
		this.incomplete = incomplete;
	}


	public void setPartialToken(String partialToken) {
		this.partialToken = partialToken;
	}


	public String getLongestMatch() {
		return longestMatch;
	}


	public void setLongestMatch(String longestMatch) {
		this.longestMatch = longestMatch;
	}


	@Override
	public String toString() {
		return "PartialTokenStatus [tokenStartLocation=" + Arrays.toString(tokenStartLocation) + ", valid=" + valid + ", incomplete=" + incomplete + ", partialToken=" + partialToken + ", longestMatch=" + longestMatch + "]";
	}


}
