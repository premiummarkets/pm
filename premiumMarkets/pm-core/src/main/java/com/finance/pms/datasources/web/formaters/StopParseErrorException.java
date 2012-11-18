/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock market technical analysis
 * major indicators, portfolio management and historical data charting.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock market technical analysis and indexes rotation,
 * With around 80% of forecasted trades above buy and hold, while back testing over DJI, 
 * FTSE, DAX and SBF, Back testing, 
 * Buy sell email notifications with automated markets and user defined portfolios scanning.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview and a free workable demo.
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
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
package com.finance.pms.datasources.web.formaters;

import java.util.List;

import com.finance.pms.datasources.db.Validatable;

// TODO: Auto-generated Javadoc
/**
 * The Class StopParseErrorException.
 * 
 * @author Guillaume Thoreton
 */
public class StopParseErrorException extends StopParseException {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3170615490536259926L;
	
	/** The message. */
	String message;
	
	/** The reason. */
	String reason;
	
	
	/**
	 * Instantiates a new stop parse error exception.
	 * 
	 * @param last the last
	 * @param message the message
	 * @param reason the reason
	 * 
	 * @author Guillaume Thoreton
	 */
	public StopParseErrorException(Validatable last, String message, String reason) {
		super(last);
		this.message = message;
		this.reason = reason;
	}
	
	/**
	 * Instantiates a new stop parse error exception.
	 * 
	 * @param lastList the last
	 * @param message the message
	 * @param reason the reason
	 * 
	 * @author Guillaume Thoreton
	 */
	public StopParseErrorException(List<Validatable> lastList, String message, String reason) {
		super(lastList.get(lastList.size()));
		this.message = message;
		this.reason = reason;
	}
	
	public StopParseErrorException(String message, String reason) {
		super(null);
		this.message = message;
		this.reason = reason;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * Gets the reason.
	 * 
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
}
