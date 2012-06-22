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
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.PortfolioShare;


// TODO: Auto-generated Javadoc
/**
 * The Class StripedCloseIndexRelative.
 * 
 * @author Guillaume Thoreton
 */
public class StripedCloseIndexRelative extends StripedCloseFunction {

	protected static MyLogger LOGGER = MyLogger.getLogger(StripedCloseIndexRelative.class);

	/** The relative quotations. */
	Quotations relativeQuotations;

	Date startDate;
	Date endDate;

	Integer startDateRelativeIndex;

	/**
	 * Instantiates a new striped close index relative.
	 * 
	 * @param relativeSQ the relative sq
	 * 
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public StripedCloseIndexRelative(Quotations relativeQuotations,Date arbitraryStartDate,Date arbitraryEndDate) throws InvalidAlgorithmParameterException {
		super(arbitraryEndDate);

		if (null == relativeQuotations || relativeQuotations.size() == 0) 
			throw new  InvalidAlgorithmParameterException("Referee must have quotations!");

		this.relativeQuotations = relativeQuotations;
		this.arbitraryStartDate = arbitraryStartDate;
		LOGGER.debug("The arbitrary date is : "+arbitraryStartDate);

	}


	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.db.StripedCloseFunction#setInternalRef(com.finance.pms.portfolio.PortfolioShare, double[])
	 */
	@Override
	public void targetShareData(PortfolioShare ps, Quotations stockQuotations) {

		this.stockQuotations = stockQuotations;

		startDate = getStartDate(stockQuotations);
		startDateRelativeIndex = this.relativeQuotations.getClosestIndexForDate(0,startDate);
		startDateQuotationIndex = this.stockQuotations.getClosestIndexForDate(0,startDate);
		
		endDate = getEndDate(stockQuotations);
		endDateQuotationIndex = this.stockQuotations.getClosestIndexForDate(startDateQuotationIndex, endDate);

	}


	/* (non-Javadoc)
	 * @see com.finance.pms.datasources.db.StripedCloseFunction#relatedClose()
	 */
	@Override
	public BigDecimal[] relatedClose() {

		BigDecimal[] ret = new BigDecimal[0];
		ArrayList<BigDecimal>  retA = new ArrayList<BigDecimal>();

		BigDecimal relativeQuotationsRoot =  relativeQuotations.get(startDateRelativeIndex).getClose();
		BigDecimal realCloseRoot = stockQuotations.get(startDateQuotationIndex).getClose();

		for (int i = startDateQuotationIndex; i <= this.endDateQuotationIndex; i++) {

			Date dq = this.stockQuotations.get(i).getDate();
			QuotationUnit relQuotationUnit = relativeQuotations.get(relativeQuotations.getClosestIndexForDate(0,dq));

			BigDecimal relativeQuotation = (relQuotationUnit.getClose().subtract(relativeQuotationsRoot)).divide(relativeQuotationsRoot,10,BigDecimal.ROUND_DOWN);
			BigDecimal quotation = this.stockQuotations.get(i).getClose().subtract(realCloseRoot).divide(realCloseRoot,10,BigDecimal.ROUND_DOWN);
			BigDecimal relatedCloseValue = quotation.subtract(relativeQuotation);

			retA.add(relatedCloseValue);
		}

		return  retA.toArray(ret);

	}
	


}
