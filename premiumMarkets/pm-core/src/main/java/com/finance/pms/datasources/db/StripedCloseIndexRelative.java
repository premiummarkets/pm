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
package com.finance.pms.datasources.db;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.events.quotations.QuotationUnit;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.portfolio.PortfolioShare;


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
	public StripedCloseIndexRelative(Quotations relativeQuotations, Date arbitraryStartDate, Date arbitraryEndDate) throws InvalidAlgorithmParameterException {
		super(arbitraryEndDate);

		if (null == relativeQuotations || relativeQuotations.size() == 0) 
			throw new  InvalidAlgorithmParameterException("Referee must have quotations!");

		this.relativeQuotations = relativeQuotations;
		this.arbitraryStartDate = arbitraryStartDate;
		LOGGER.debug("The arbitrary date is : "+arbitraryStartDate);

	}

	@Override
	public void targetShareData(PortfolioShare ps, Quotations stockQuotations) {

		this.stockQuotations = stockQuotations;

		startDate = getStartDate(stockQuotations);
		startDateRelativeIndex = this.relativeQuotations.getClosestIndexForDate(0,startDate);
		startDateQuotationIndex = this.stockQuotations.getClosestIndexForDate(0,startDate);
		
		endDate = getEndDate(stockQuotations);
		endDateQuotationIndex = this.stockQuotations.getClosestIndexForDate(startDateQuotationIndex, endDate);

	}

	@Override
	public BigDecimal[] relativeCloses() {

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

		return  retA.toArray(new BigDecimal[0]);

	}
	


}
