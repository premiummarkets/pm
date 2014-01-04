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
