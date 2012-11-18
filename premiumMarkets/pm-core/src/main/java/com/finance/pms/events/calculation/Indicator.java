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
package com.finance.pms.events.calculation;

import java.util.Date;

import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;


public abstract class Indicator {

	protected Stock stock;
	private Quotations quotations;

	protected Indicator(Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency, Integer firstIdxShift, Integer lastIdxShift) throws NoQuotationsException {
		super();
		this.stock = stock;
		this.quotations  = QuotationsFactories.getFactory().getQuotationsInstance(stock, firstDate, lastDate, true, calculationCurrency, firstIdxShift, lastIdxShift);
	}
	
	protected Indicator(Quotations quotations) {
		super();
		this.stock = quotations.getStock();
		this.quotations  = quotations;
	}
	
	public Quotations getIndicatorQuotationData() {
		return this.quotations;
	}
	
	protected String getStockName() {
		return this.stock.getName();
	}
	
	protected Integer startIdx() {
		return this.quotations.getFirstDateShiftedIdx();
	}
	
	protected Integer endIdx() {
		return  this.quotations.getLastDateIdx();
	}
	
	protected Boolean hasQuotations() {
		return this.quotations.hasQuotations();
	}
	
	protected Stock getStock() {
		return stock;
	}
	
	public abstract void exportToCSV();
	
	

}
