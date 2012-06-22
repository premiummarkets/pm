/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
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
package com.finance.pms.datasources.db;

import java.math.BigDecimal;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.PortfolioShare;


// TODO: Auto-generated Javadoc
/**
 * The Interface StripedCloseFunction.
 * 
 * @author Guillaume Thoreton
 */
public abstract class StripedCloseFunction {
	
	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseFunction.class);
	
	protected Date arbitraryStartDate;
	protected Date arbitraryEndDate;
	
	/** The stock quotations. */
	protected Quotations stockQuotations;
	
	protected Integer startDateQuotationIndex;
	protected Integer endDateQuotationIndex;
	

	public StripedCloseFunction() {
		super();
	}

	public StripedCloseFunction(Date arbitraryEndDate) {
		super();
		this.arbitraryEndDate = arbitraryEndDate;
	}

	/**
	 * Sets the internal ref.
	 * 
	 * @param stockDef the stock def
	 * @param stockQuotations the stock quotations
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract void targetShareData(PortfolioShare ps, Quotations stockQuotations);
	
	/**
	 * Related close.
	 * 
	 * @return the double[]
	 * 
	 * @author Guillaume Thoreton
	 */
	public abstract BigDecimal[] relatedClose();

	public void updateEndDate(Date date) {
		this.arbitraryEndDate = date;
	
	}

	public void updateStartDate(Date date) {
		this.arbitraryStartDate = date;
	
	}

	public Integer getStartDateQuotationIndex() {
		return startDateQuotationIndex;
	}

	public Integer getEndDateQuotationIndex() {
		return endDateQuotationIndex;
	}

	/**
	 * @param stockQuotations
	 */
	protected Date getStartDate(Quotations stockQuotations) {
		Date startDate = this.arbitraryStartDate;
		startDate = (startDate.before(stockQuotations.getDate(0)))?stockQuotations.getDate(0):startDate;
		LOGGER.debug("The start date is : "+startDate);
		
		return startDate;
	}

	/**
	 * @param stockQuotations
	 */
	protected Date getEndDate(Quotations stockQuotations) {
		Date endDate = this.arbitraryEndDate;
		Integer lastQuoteI = stockQuotations.size()-1;
		endDate = (endDate.after(stockQuotations.getDate(lastQuoteI)))?stockQuotations.getDate(lastQuoteI):endDate;
		LOGGER.debug("The end date is : "+endDate);
		return endDate;
	}
	
	public Date getArbitraryStartDate() {
		return arbitraryStartDate;
	}

	public Date getArbitraryEndDate() {
		return arbitraryEndDate;
	}
	
	
	
}
